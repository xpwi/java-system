package com.xpwi.datastructures.arrays.order;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来演示直接使用数据操作有序数组,可存放重复值
 *
 * @author xpwi
 */
public class OperateOrderNoIndexRepeatable {
	private int[] datas = null;
	private int currentIndex = 0;

	public OperateOrderNoIndexRepeatable(int length){
		datas = new int[length];
	}

	public int insert(int data){
		//假设顺序是升序
		int index = 0;
		//1：查找数据data应该存放的位置
		for(index=0;index<currentIndex;index++){
			if(datas[index] > data){
				break;
			}
		}
		//2：把这个位置及其后面的数据，向后移动一位
		for(int i=currentIndex;i>index;i--){
			datas[i] = datas[i-1];
		}
		//3：把data设置到应该存放的位置
		datas[index] = data;

		currentIndex++;
		return currentIndex-1;
	}
	/**
	 * 获取数据data在数组中的索引位置
	 * @param begin 开始查找的位置
	 * @param data 要查找索引的数据
	 * @return 在这个开始位置往后第一个索引
	 */
	private int getIndex(int begin,int data){
		int index = -1;
		for(int i=begin;i<currentIndex;i++){
			if(datas[i] == data){
				index = i;
				break;
			}
		}
		return index;
	}

	public void remove(int data){
		//1：查找这个数据对应的索引
		int index = this.getIndex(0,data);
		//循环向后查找这个数据，直到找不到位置
		while(index>=0){
			//2：同前一个演示，删除一个数据
			for(int i=index;i<currentIndex;i++){
				datas[i] = datas[i+1];
			}
			currentIndex--;
			//再次查找后续的数据对应的索引
			index = this.getIndex(index,data);
		}
	}
	public List<Integer> searchOne(int data){
		List<Integer> retList = new ArrayList<Integer>();
		//1：查找这个数据对应的索引
		int index = this.getIndex(0,data);
		while(index >= 0){
			//2：如果有，就加入到要返回的集合中
			retList.add(datas[index]);
			//再次查找后续的数据对应的索引
			index = this.getIndex(index+1,data);
		}
		return retList;
	}
	public void printDatas(){
		System.out.println("======================================>");
		for(int d : datas){
			System.out.println(d);
		}
	}
	public static void main(String[] args) {
		OperateOrderNoIndexRepeatable t = new OperateOrderNoIndexRepeatable(10);

		t.insert(3);
		t.insert(6);
		t.insert(1);
		t.insert(2);
		t.insert(2);
		t.insert(6);

		t.printDatas();

		t.remove(6);
		t.printDatas();

		List<Integer> ret = t.searchOne(2);
		System.out.println("ret=="+ret);
	}
}
