package com.xiaopengwei.datastructures.btree;

/**
 * 用来封装数据识别项的对象
 *
 * @author XiaoPengwei.com
 */
public class KeyItem {
	private int id;
	private int businessData;

	public KeyItem(int id) {
		super();
		this.id = id;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBusinessData() {
		return businessData;
	}
	public void setBusinessData(int businessData) {
		this.businessData = businessData;
	}
	@Override
	public String toString() {
		return "KeyItem [id=" + id + ", businessData=" + businessData + "]";
	}
}
