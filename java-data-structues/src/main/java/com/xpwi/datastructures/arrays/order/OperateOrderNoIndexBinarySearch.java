package com.xpwi.datastructures.arrays.order;

/**
 * 用来演示直接使用数据操作有序数组,存放不重复的值
 *
 * @author github.com/xpwi
 */
public class OperateOrderNoIndexBinarySearch {
	private int[] datas = null;
	private int currentIndex = 0;

	public OperateOrderNoIndexBinarySearch(int length){
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
	private int binarySearch(int data){
		int index = -1;
		//用来表示小的这边的索引
		int lowIndex = 0;
		//用来表示大的这边的索引
		int highIndex = currentIndex - 1;

		while(true){
			//1：找到中间的索引位置
			index = (lowIndex + highIndex)/2;
			//2：把要查找的数据和中间索引位置的数据进行比较
			if(lowIndex > highIndex){
				//没有找到数据
				return -1;
			}else if(datas[index]==data){
				return index;
			}else{
				if(data < datas[index]){
					highIndex = index - 1;
				}else{
					lowIndex = index + 1;
				}
			}
		}
	}

	public void remove(int data){
		//1：查找这个数据对应的索引
		int index = this.binarySearch(data);
		//2：同前一个演示
		for(int i=index;i<currentIndex;i++){
			datas[i] = datas[i+1];
		}
		currentIndex--;
	}
	public int searchOne(int data){
		//1：查找这个数据对应的索引
		int index = this.binarySearch(data);
		//2：如果有，就返回datas中的数据
		if(index >=0 ){
			return datas[index];
		}
		//3：如果没有，就返回0；
		return 0;
	}
	public void printDatas(){
		System.out.println("======================================>");
		for(int d : datas){
			System.out.println(d);
		}
	}
	public static void main(String[] args) {
		OperateOrderNoIndexBinarySearch t = new OperateOrderNoIndexBinarySearch(10);

		t.insert(3);
		t.insert(6);
		t.insert(1);
		t.insert(2);

		t.printDatas();

		t.remove(1);
		t.printDatas();

		int ret = t.searchOne(3);
		System.out.println("ret=="+ret);
	}
}
