package com.xiaopengwei.datastructures.btree;

/**
 * 用来封装B树节点的对象
 *
 * @author XiaoPengwei.com
 */
public class BTreeNode {
	/**
	 * B树的阶数
	 */
	private int m = 0;

	/**
	 *存放多个识别数据项
	 */
	private KeyItem[] items =  null;
	/**
	 * 存放多个子节点对象
	 */
	private BTreeNode[] children = null;
	/**
	 * 父节点对象
	 */
	private BTreeNode parent = null;
	/**
	 * 用来记录本节点到底存放了几个识别数据项
	 */
	private int numItems;


	public BTreeNode(int m){
		this.m = m;
		items = new KeyItem[m-1];
		children = new BTreeNode[m];
	}


	/**
	 * 根据索引获取相应的识别数据项
	 * @param index
	 * @return
	 */
	public KeyItem getItem(int index){
		return items[index];
	}
	/**
	 * 给本节点，新加入一个识别数据项
	 * @param newItem
	 * @return
	 */
	public int insertKeyItem(KeyItem newItem){
		//维护存放的识别数据项的 个数
		numItems++;

		//从后向前进行数据查找和比较
		for(int i=(m-2);i>=0;i--){
			if(this.items[i]==null){
				continue;
			}else{
				if(newItem.getId() > items[i].getId()){
					items[i+1] = newItem;
					return i+1;
				}else{
					items[i+1] = items[i];
				}
			}
		}
		items[0] = newItem;

		return 0;
	}
	/**
	 * 从本节点，删除一个识别数据项
	 * @return
	 */
	public KeyItem removeKeyItem(){
		KeyItem ret = items[this.numItems-1];
		items[this.numItems-1] = null;
		this.numItems--;
		return ret;
	}
	/**
	 * 按照索引来删除某个keyitem
	 * @param index
	 */
	public void removeKeyItem(int index){
		//依次向前移动一个
		for(int i=index+1;i<this.numItems;i++){
			this.items[i-1] = this.items[i];
		}
		//把最后一个置为null
		this.items[this.numItems-1] = null;

		this.numItems--;
	}
	/**
	 * 删除节点所有的keyItem
	 */
	public void removeAllItems(){
		for(int i=0;i<this.numItems;i++){
			this.items[i] = null;
		}
		this.numItems = 0;
	}

	/**
	 * 查找本节点是否有值为key的识别数据项
	 * @param key
	 * @return
	 */
	public int findKeyItem(int key){
		for(int i=0;i<(m-1);i++){
			if(items[i]==null){
				break;
			}else if(items[i].getId() == key){
				return i;
			}
		}
		return -1;
	}
	/**
	 * 给本节点连接一个子节点
	 * @param childIndex
	 * @param childNode
	 */
	public void connectChild(int childIndex,BTreeNode childNode){
		this.children[childIndex] = childNode;
		if(childNode!=null){
			childNode.setParent(this);
		}
	}
	/**
	 * 从本节点断开某个子节点的连接
	 * @param childIndex
	 * @return
	 */
	public BTreeNode disconnectChild(int childIndex){
		BTreeNode ret = this.children[childIndex];
		this.children[childIndex] = null;
		return ret;
	}
	/**
	 * 获取某个子节点
	 * @param childIndex
	 * @return
	 */
	public BTreeNode getChild(int childIndex){
		return this.children[childIndex];
	}
	/**
	 * 得到父节点对象
	 * @return
	 */
	public BTreeNode getParent(){
		return this.parent;
	}
	/**
	 * 判断是否叶子节点
	 * @return
	 */
	public boolean isLeaf(){
		return  this.children[0]==null ;
	}
	/**
	 * 判断本节点的识别数据项是否已经满了
	 * @return
	 */
	public boolean isFull(){
		return this.numItems==(m-1);
	}

	public void displayNode(){
		for(int i=0;i<this.numItems;i++){
			System.out.print(this.items[i].getId()+",");
		}
		System.out.println(" --- ");
	}

	public KeyItem[] getItems() {
		return items;
	}
	public void setItems(KeyItem[] items) {
		this.items = items;
	}
	public BTreeNode[] getChildren() {
		return children;
	}
	public void setChildren(BTreeNode[] children) {
		this.children = children;
	}
	public int getNumItems() {
		return numItems;
	}
	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}
	public void setParent(BTreeNode parent) {
		this.parent = parent;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
}
