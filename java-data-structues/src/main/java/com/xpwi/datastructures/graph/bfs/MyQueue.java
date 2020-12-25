package com.xpwi.datastructures.graph.bfs;

/**
 * 演示基本的队列，循环队列
 *
 * @author github.com/xpwi
 */
public class MyQueue {
	/**
	 * 存放队列数据的数组
	 */
	private int[] queue;
	/**
	 * 记录队头的索引位置
	 */
	private int front;
	/**
	 * 记录队尾的索引位置
	 */
	private int end;
	/**
	 * 记录当前队列里面存放的元素个数
	 */
	private int nItems;

	public MyQueue(int length){
		queue = new int[length];
		front = 0;
		end = -1;
		nItems = 0;
	}

	public void insert(int data){
		//检查是否已经到数组最大索引项
		if(end == queue.length -1){
			end = -1;
		}
		end++;

		queue[end] = data;

		nItems++;

		if(nItems > queue.length){
			nItems = queue.length;
		}
	}
	public int remove(){
		if(nItems==0){
			return 0;
		}
		int temp = queue[front];
		queue[front] = 0;
		//维护front
		if(front==queue.length-1){
			front = 0;
		}
		front++;

		nItems--;
		return temp;
	}
	public int peekFront(){
		return queue[front];
	}
	public boolean isEmpty(){
		return nItems==0;
	}
	public boolean isFull(){
		return nItems==queue.length;
	}
	public void printQueue(){
		System.out.println("=======================>");
		for(int d : queue){
			System.out.println(d);
		}
	}
	public static void main(String[] args) {
		MyQueue t = new MyQueue(5);

		t.insert(1);
		t.insert(2);
		t.insert(3);
		t.insert(4);
		t.insert(5);

		t.printQueue();

		int ret = t.peekFront();
		System.out.println("now ret=="+ret);

		t.remove();
		int ret2 = t.remove();
		System.out.println("now ret2=="+ret2);

		t.printQueue();

		t.insert(6);
		t.insert(7);
		t.insert(8);
		t.printQueue();
	}
}
