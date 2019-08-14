package com.xiaopengwei.datastructures.linklist;

/**
 * 演示使用链表来实现栈的操作
 *
 * @author XiaoPengwei.com
 */
public class MyLinkListStack {
	private SingleLinkList theList = new SingleLinkList();
	
	public void push(int id){
		theList.insertFirst(id);
	}
	public int pop(){
		return theList.removeFirst().getId();
	}
	public int peek(){
		return theList.peekFirst().getId();
	}
	
	public boolean isEmpty(){
		return theList.isEmpty();
	}
	public boolean isFull(){
		return false;
	}
	public void printStack(){
		theList.displayList();
	}
	
	public static void main(String[] args) {
		MyLinkListStack t = new MyLinkListStack();
		
		t.push(3);
		t.push(5);
		t.push(9);
		t.push(2);
		
		t.printStack();
		
		int ret1 = t.peek();
		System.out.println("ret1=="+ret1);
		
		t.pop();
		
		t.printStack();
		int ret2 = t.pop();
		System.out.println("ret2=="+ret2);
		
		t.printStack();
	}
}
