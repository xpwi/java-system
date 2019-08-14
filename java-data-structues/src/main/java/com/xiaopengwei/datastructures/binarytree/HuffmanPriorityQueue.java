package com.xiaopengwei.datastructures.binarytree;

/**
 * Huffman算法使用的优先级队列
 *
 * @author XiaoPengwei.com
 */
public class HuffmanPriorityQueue {
	/**
	 * 存放队列数据的数组
	 */
	private HuffmanNode[] queue;
	/**
	 * 记录当前队列里面存放的元素个数，也相当于是索引
	 */
	private int nItems ;

	private int length;

	public int size(){
		return nItems;
	}

	public HuffmanPriorityQueue(int length){
		this.length = length;
		queue = new HuffmanNode[length];
		nItems = 0;
	}

	public void insert(HuffmanNode data){
		//1：队列里面没有数据项的话，直接赋值
		if(nItems == 0){
			queue[nItems] = data;
			nItems++;
		}else{
			//2：队列里面有数据项的话，就需要进行比较，排序后插入数据
			int i = 0;
			for(i=nItems-1;i>=0;i--){
				if(data.getCount() > queue[i].getCount()){
					queue[i+1] = queue[i];
				}else{
					break;
				}
			}
			queue[i+1] = data;
			nItems++;
		}
	}
	public HuffmanNode remove(){
		nItems--;
		HuffmanNode temp = queue[nItems];
		queue[nItems] = null;
		return temp;
	}
	public HuffmanNode peekFront(){
		return queue[nItems-1];
	}
	public boolean isEmpty(){
		return nItems==0;
	}
	public boolean isFull(){
		return nItems==queue.length;
	}
	public void printQueue(){
		System.out.println("=======================>");
		for(HuffmanNode d : queue){
			System.out.println(d);
		}
	}

	public static void main(String[] args) {
		HuffmanPriorityQueue t = new HuffmanPriorityQueue(5);

		t.insert(new HuffmanNode('a',5));
		t.insert(new HuffmanNode('b',6));
		t.insert(new HuffmanNode('c',3));
		t.insert(new HuffmanNode('d',4));
		t.insert(new HuffmanNode('e',1));

		t.printQueue();

		HuffmanNode ret = t.peekFront();
		System.out.println("now ret=="+ret);

		t.remove();
		HuffmanNode ret2 = t.remove();
		System.out.println("now ret2=="+ret2);

		t.printQueue();

	}
}
