package com.xpwi.datastructures.binarytree;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 演示应用Haffman算法来实现压缩和解压
 *
 * @author xpwi
 */
public class HuffmanTree {
	public String decompress(String fileName){
		DataInputStream in = null;
		String srcContent = "";
		try{
			in = new DataInputStream(new FileInputStream(new File(fileName)));
//		1：读取码表的信息，构建出码表来
			Map<String,String> map = readCodes(in);
//		2：读回具体的数据内容
			byte[] datas = this.readDatas(in);
//		3：把读回的字节还原成对应的整型数据,都是char
			int[] dataInts = this.bytes2IntArray(datas);
//		4：根据码表，把内容组成的哈夫曼编码，依次转换回原始的字符，从而得到原始的内容
			srcContent = this.huffman2Char(map, dataInts);
		}catch(Exception err){
			err.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return srcContent;
	}
	/**
	 * 解压缩生成原始的内容
	 * @param map
	 * @param dataInts
	 * @return
	 */
	private String huffman2Char(Map<String,String> map,int[] dataInts){
		StringBuffer buffer = new StringBuffer();
		//1：把整型数组还原成为对应的字符串，也就是haffman编码串
		String str = this.int2BinaryString(dataInts);
		//2：然后把huffman编码串依次替换回字符
		while(str.length()>0){
			for(String code : map.keySet()){
				if(str.startsWith(code)){
					buffer.append(map.get(code));
					str = str.substring(code.length());
					break;
				}
			}
		}
		return buffer.toString();
	}
	/**
	 * 把int值转换回对应的 二进制编码表示的 字符串
	 * @param as
	 * @return
	 */
	private String int2BinaryString(int[] as){
		int len = as.length;
		//存放每个byte对应的二进制字符串
		String[] ss = new String[len];

		String binaryStr = "";

		for(int i=0;i<len-1;i++){
			//得到每个整数对应的二进制字符串
			ss[i] = Integer.toBinaryString(as[i]);
			ss[i] = this.addZero(ss[i]);
			binaryStr += ss[i];
		}
		//处理尾部原来补的零的个数，就是去掉这些补的0
		int zeros = as[len-1];
		if(zeros > 0){
			binaryStr = binaryStr.substring(0,binaryStr.length() - zeros);
		}

		return binaryStr;
	}
	/**
	 * 给每个二进制字符串补足成8位
	 * @param str
	 * @return
	 */
	private String addZero(String str){
		if(str.length()<8){
			int zeroNum = 8-str.length();
			for(int i=0;i<zeroNum;i++){
				str = "0"+str;
			}
		}
		return str;
	}

	/**
	 * 把这些byte转换回写出去的char值
	 * @param datas
	 * @return
	 */
	private int[] bytes2IntArray(byte[] datas){
		int[] as = new int[datas.length];
		for(int i=0;i<datas.length;i++){
			if(datas[i] >= 0){
				as[i] = datas[i];
			}else{
				as[i] = datas[i] + 256;
			}
		}

		return as;
	}
	/**
	 * 读取数据部分
	 * @param in
	 * @return
	 */
	private byte[] readDatas(DataInputStream in)throws Exception{
		//1：读回有多少个byte
		int dataByteNum =  in.readInt();
		//2：创建出byte数组
		byte[] bs = new byte[dataByteNum];
		//3：循环把每个byte读取回来
		for(int i=0;i<dataByteNum;i++){
			bs[i] = in.readByte();
		}
		return bs;
	}
	/**
	 * 读取码表
	 * @param in
	 * @return Map,key--是字符对应的Haffuman编码，value- 字符
	 */
	private Map<String,String> readCodes(DataInputStream in)throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		//1：读回编码的个数
		int codeNum = in.readInt();
		//2：读回每个字符、编码长度、Haffuman编码
		for(int i=0;i<codeNum;i++){
			char codeChar = in.readChar();
			int codeLen = in.readInt();
			String code = "";
			char[] cs = new char[codeLen];
			for(int j=0;j<cs.length;j++){
				code += in.readChar();
			}

			map.put(code, ""+codeChar);
		}
		return map;
	}

	/////////////////////////////////////////////////////////////////////////////
	public void compress(String str,String outFile){
//		1：统计：读入要压缩的源文件，统计字符出现的次数
		HuffmanPriorityQueue queue = this.statistics(str);
//		2：建树：构建哈夫曼树
		HuffmanNode tree = this.buidHuffmanTree(queue);
//		3：编码：对哈夫曼树的左边记0，右边记1，就可以得到字符的哈夫曼编码。
		Map<String,String> map = new HashMap<String,String>();
		this.buildHuffmanCode(map, tree, "");
//		System.out.println("map=="+map);
//		4：输出：把编码序列输出，这就是压缩后的数据
		this.outData(str, map,outFile);
	}
	/**
	 * 输出数据到文件
	 * @param str 原始内容
	 * @param map Huffman编码
	 * @param outFile 输出文件的路径和文件名
	 */
	private void outData(String str,Map<String,String> map,String outFileName){
		File outFile = new File(outFileName);
		DataOutputStream os = null;
		try{
			os = new DataOutputStream(new FileOutputStream(outFile));
			//1：输出码表
			this.outCodes(os, map);
			//2：输出源内容的每个字符对应的huffman编码
			String dataHuffmanCode = this.source2HumanStr(str, map);
			this.outDataHuffmanCode(os, dataHuffmanCode);
		}catch(Exception err){
			err.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 输出原始的内容转换成为huffman编码串
	 * @param os
	 * @param dataHuffmanCode
	 */
	private void outDataHuffmanCode(DataOutputStream os,String dataHuffmanCode)throws Exception{
		//1：要把这个huffman编码串 转换成为 对应的 byte[]
		byte[] bs = this.string2ByteArrays(dataHuffmanCode);
		//2：输出byte数组的个数
		os.writeInt(bs.length);
		//3：输出byte数组
		os.write(bs);
	}
	/**
	 * 把一个内容是二进制编码的串，转换成为真正的二进制数组
	 * @param dataHuffmanCode 内容是二进制编码的串
	 * @return
	 */
	private byte[] string2ByteArrays(String dataHuffmanCode){
		byte[] retBytes = null;

		char[] cs = dataHuffmanCode.toCharArray();
		int len = cs.length;
		int lenByte = 0;

		//1:判断整个串的长度是否能被8整除
		if(len % 8 == 0){
			//2：能整除的话，就8位作为一个byte
			lenByte = len/8 + 1;
			retBytes = new byte[lenByte];
			for(int i=0;i<lenByte -1;i++){
				String s = "";
				for(int j=i*8;j<(i+1)*8;j++){
					s += cs[j];
				}
				retBytes[i] = this.chars2byte(s);
			}
			//设置补0的个数
			retBytes[lenByte - 1] = 0;
		}else{
			//3：不能整除的话，最后一个字符串，后面补0，使其成为8位，作为一个byte，同时要记录补零的个数
			lenByte = len/8 + 2;
			retBytes = new byte[lenByte];
			int zeroNum = 8 - len % 8;
			//补0
			for(int i=0;i<zeroNum;i++){
				dataHuffmanCode +="0";
			}
			//重新计算char数组
			cs = dataHuffmanCode.toCharArray();
			for(int i=0;i<lenByte -1;i++){
				String s = "";
				for(int j=i*8;j<(i+1)*8;j++){
					s += cs[j];
				}
				retBytes[i] = this.chars2byte(s);
			}
			//设置补0的个数
			retBytes[lenByte - 1] = (byte)zeroNum;
		}

		return retBytes;
	}
	/**
	 * 把一个char字符串转换成byte
	 * @param s
	 * @return
	 */
	private byte chars2byte(String s){
		byte ret = 0;
		char[] cs = s.toCharArray();

		for(int i=0;i<cs.length;i++){
			//计算每一位char 代表的真正的byte值
			byte tempB = (byte)(Byte.parseByte(""+cs[i])*Math.pow(2,cs.length-i-1));
			ret = (byte)(ret+tempB);
		}
		return ret;
	}

	/**
	 * 把原始的内容转换成为huffman编码串
	 * @param str
	 * @param map
	 * @return
	 */
	private String source2HumanStr(String str,Map<String,String> map){
		StringBuffer buffer = new StringBuffer();
		char[] cs = str.toCharArray();
		for(char c : cs){
			buffer.append(map.get(""+c));
		}
		return buffer.toString();
	}
	/**
	 * 输出码表
	 * @param os
	 * @param map
	 */
	private void outCodes(DataOutputStream os,Map<String,String> map)throws Exception{
		//1：输出码的个数
		os.writeInt(map.size());
		for(String key : map.keySet()){
			//2：输出每个字符，以及编码的长度
			os.writeChar(key.charAt(0));
			os.writeInt(map.get(key).length());
			//3：输出每个字符对应的Huffman编码
			os.writeChars(map.get(key));
		}
	}

	/**
	 * 根据Huffman树来生成每个字符对应的Huffman编码
	 * @param map
	 * @param tree
	 * @param zeroOrOneStr
	 */
	private void buildHuffmanCode(Map<String,String> map,HuffmanNode tree,String zeroOrOneStr){
		//1：树没有子节点
		if(tree.getLeftChild() == null && tree.getRightChild()==null){
			map.put(""+tree.getC(), zeroOrOneStr);
		}
		//2：有左子节点
		if(tree.getLeftChild()!=null){
			this.buildHuffmanCode(map, tree.getLeftChild(), zeroOrOneStr+"0");
		}
		//3：有右子节点
		if(tree.getRightChild()!=null){
			this.buildHuffmanCode(map, tree.getRightChild(), zeroOrOneStr+"1");
		}
	}
	/**
	 * 构建Huffman树
	 * @param queue
	 * @return
	 */
	private HuffmanNode buidHuffmanTree(HuffmanPriorityQueue queue){
		while(queue.size() > 1){
			//1：先取两个最小权重的对象
			HuffmanNode n1 = queue.remove();
			HuffmanNode n2 = queue.remove();
			//2：构建这两个对象的父对象
			HuffmanNode n3 = new HuffmanNode((char)0,n1.getCount()+n2.getCount());
			n3.setLeftChild(n1);
			n3.setRightChild(n2);
			//3：把父对象加入回到队列里面
			queue.insert(n3);
		}
		return queue.peekFront();
	}
	/**
	 * 统计内容中字符出现的次数，并构建权重的优先级队列
	 * @param str 原始的要压缩的内容
	 * @return
	 */
	private HuffmanPriorityQueue statistics(String str){
		//1：统计次数
		Map<Character,Integer> map = new HashMap<Character,Integer>();
		char[] cs = str.toCharArray();
		for(char c : cs){
			Object obj = map.get(c);
			if(obj==null){
				map.put(c, 1);
			}else{
				map.put(c, ((Integer)obj)+1);
			}
		}
		//2：构建优先级队列
		HuffmanPriorityQueue queue = new HuffmanPriorityQueue(map.size());
		for(char c : map.keySet()){
			HuffmanNode node = new HuffmanNode(c, map.get(c));
			queue.insert(node);
		}
		return queue;
	}
	/**
	 * 读文件获取要压缩的内容
	 * @param fileName
	 * @return
	 */
	public String readFile(String fileName){
		StringBuffer buffer = new StringBuffer();
		DataInputStream in = null;

		try {
			in = new DataInputStream(new FileInputStream(new File(fileName)));
			String tempStr = "";
			while((tempStr=in.readLine())!=null){
				buffer.append(tempStr+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return buffer.toString();
	}
	public static void main(String[] args) {
		HuffmanTree t = new HuffmanTree();

		//t.compress(t.readFile("HuffmanTree.java"), "temp.txt");

		String s = t.decompress("temp.txt");
		System.out.println("s=="+s);
	}
}
