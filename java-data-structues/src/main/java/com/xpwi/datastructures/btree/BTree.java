package com.xpwi.datastructures.btree;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示B树的操作
 *
 * @author xpwi
 */
public class BTree {
	/**
	 * B树的阶数
	 */
	private int m = 0;

	private BTreeNode root = null;

	public BTree(int m){
		this.m = m;
		root = new BTreeNode(m);
	}

	/**
	 * 根据key查找对象
	 * @param key
	 * @return
	 */
	public Object[] find(int key){
		Object[] ret = new Object[2];
		BTreeNode current = root;
		int childIndex = -1;
		while(true){
			childIndex = current.findKeyItem(key);
			if(childIndex >=0 ){
				ret[0] = current;
				ret[1] = childIndex;
				return ret;
			}else if(current.isLeaf()){
				return null;
			}else{
				current = this.getNext(current, key);
			}
		}
	}
	/**
	 * 查找下一个节点
	 * @param node
	 * @param key
	 * @return
	 */
	private BTreeNode getNext(BTreeNode node,int key){
		int i = 0;
		for(i=0;i<node.getNumItems();i++){
			if(key < node.getItems()[i].getId()){
				return node.getChild(i);
			}
		}
		return node.getChild(i);
	}
	/**
	 * 插入一个识别数据
	 * @param id
	 */
	public void insert(int id){
		BTreeNode current = root;
		KeyItem newItem = new KeyItem(id);
		boolean needInsert = true;
		//查找要插入的位置
		while(true){
			current = this.findCurrent(current, id);

			if(current.isFull()){
				needInsert = false;
				//满了情况
				//1：做节点分裂
				this.splitNode(current,newItem);
				//2：查找current
				//2.1 先得到current的父节点，因为本节点分裂了
				//2.2再从新查找下一个节点
				current = current.getParent();
			}else if(current.isLeaf()){
				break;
			}
		}
		if(needInsert){
			//真的加入
			current.insertKeyItem(newItem);
		}
	}
	/**
	 * 查找需要添加数据的叶子节点
	 * @param current
	 * @param id
	 * @return
	 */
	private BTreeNode findCurrent(BTreeNode current,int id){
		while(true){
			if(current.isLeaf()){
				break;
			}else{
				current = this.getNext(current, id);
			}
		}
		return current;
	}

	private KeyItem[] insertKeyItem(KeyItem[] items,KeyItem newItem){
		//从后向前进行数据查找和比较
		for(int i=(items.length-1);i>=0;i--){
			if(items[i]==null){
				continue;
			}else{
				if(newItem.getId() > items[i].getId()){
					items[i+1] = newItem;
					return items;
				}else{
					items[i+1] = items[i];
				}
			}
		}
		items[0] = newItem;

		return items;
	}


	/**
	 * 分裂节点
	 * @param node
	 */
	private KeyItem splitNode(BTreeNode node,KeyItem newItem){

		//1：先把newItem加入node，，好计算中间元素
		KeyItem[] newKeyItems = new KeyItem[node.getNumItems()+1];
		//先把node原来的keyItem加入
		for(int i=0;i<node.getNumItems();i++){
			newKeyItems = this.insertKeyItem(newKeyItems, node.getItem(i));
		}
		//再加入新的
		newKeyItems = this.insertKeyItem(newKeyItems, newItem);
		//计算中间索引位置
		int middleIndex = (node.getM()+1)/2 - 1;

		//2：开始移动识别数据

		//把原始的数据记录下来，并断开他们的关系
		KeyItem[] rightItems = new KeyItem[node.getM()-middleIndex-1];
		BTreeNode[] childs = new BTreeNode[node.getM()-middleIndex-1];

		BTreeNode parent;

		//将一半较大的数据移动到新节点
		for(int i=0;i<rightItems.length;i++){
			rightItems[i] = newKeyItems[middleIndex+i+1];
		}
		//一半较小的数据不动，还在node里面
		node.removeAllItems();
		for(int i=0;i<middleIndex;i++){
			node.insertKeyItem(newKeyItems[i]);
		}
		//设置移动到右边的child
		for(int i=(childs.length-1);i>=0;i--){
			childs[i] = node.disconnectChild(middleIndex+i+1);
		}

		//创建一个兄弟节点
		BTreeNode rightNode = new BTreeNode(m);

		//如果是根节点，需要创建新的parent，并维护关系
		if(node == root){
			root = new BTreeNode(m);
			parent = root;
			root.connectChild(0, node);
		}else{
			parent = node.getParent();
		}

		//中间数据项移动到父节点
		boolean needSplitParent = false;
		KeyItem parentOldKeyItem = null;
		BTreeNode parentOldChildNode = null;

		if(parent.isFull()){
			needSplitParent = true;
			//把parent的最后一个keyItem和最后一个child都先删除了，在分裂parent节点的时候再加入回来
			parentOldKeyItem = parent.removeKeyItem();
			parentOldChildNode = parent.getChild(m-1);
		}

		KeyItem upKeyItem = newKeyItems[middleIndex];
		int itemIndex = parent.insertKeyItem(upKeyItem);

		//维护parnet中的child节点的关系
		int num = parent.getNumItems();
		for(int i=num-1;i>itemIndex;i--){
			//先断开，再重新加入
			BTreeNode temp = parent.disconnectChild(i);
			parent.connectChild(i+1,temp);
		}
		//把新的兄弟节点加入父节点
		parent.connectChild(itemIndex+1, rightNode);

		//维护新的兄弟节点
		for(int i=0;i<rightItems.length;i++){
			rightNode.insertKeyItem(rightItems[i]);
		}
		for(int i=0;i<childs.length;i++){
			rightNode.connectChild(i, childs[i]);
		}

		//3：处理父节点满的情况
		if(needSplitParent){
			KeyItem lastUpKeyItem = this.splitNode(parent, parentOldKeyItem);
			if(lastUpKeyItem.getId() == parentOldKeyItem.getId()){
				//说明是上次删除的parent的最后一个，作为upKeyItem上升了
				//那么，parentOldChildNode就应该作为upKeyItem的第一个child
				Object[] objs = this.find(upKeyItem.getId());
				BTreeNode tempNode = (BTreeNode)objs[0];

				//把原来这个位置后面的数据都向后移动一位
				for(int i=0;i<tempNode.getNumItems();i++){
					tempNode.connectChild(i+1, tempNode.getChild(i));
				}
				tempNode.connectChild(0, parentOldChildNode);
			}else{
				Object[] objs = this.find(parentOldKeyItem.getId());
				BTreeNode tempNode = (BTreeNode)objs[0];
				int tempIndex = (Integer)objs[1];

				//把原来这个位置后面的数据都向后移动一位
				for(int i=(tempIndex+1);i<tempNode.getNumItems();i++){
					tempNode.connectChild(i+1, tempNode.getChild(i));
				}
				tempNode.connectChild(tempIndex+1, parentOldChildNode);
			}
		}

		return upKeyItem;
	}

	public void displayTree(BTreeNode node,int level,int childNumber){
		System.out.print("level=="+level+" , child=="+childNumber+"  ");
		node.displayNode();

		for(int i=0;i<node.getNumItems()+1;i++){
			BTreeNode c = node.getChild(i);
			if(c==null){
				return;
			}else{
				displayTree(c,level+1,i);
			}
		}
	}
////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 删除某个keyItem
	 * @param keyItemValue
	 */
	public void removeKeyItem(int keyItemValue){
//		1：若删除结点为非叶结点，且被删关键字为该结点中第i个关键字key[i]，
//		则可从C[i]所指的子树中找出最小关键字Y，代替key[i]的位置，然后在叶结点中删去Y。
//		这样一来，就把在非叶结点删除关键字k的问题就变成了删除叶子结点中的关键字的问题了。
		Object[] delObj = this.find(keyItemValue);
		if(delObj!=null){
			BTreeNode delNode = (BTreeNode)delObj[0];
			int delKeyIndex = (Integer)delObj[1];

			if(!delNode.isLeaf()){
				BTreeNode nowDelNode = this.findMinKeyItemNode(delNode.getChild(delKeyIndex+1));

				KeyItem minKeyItem = nowDelNode.getItem(0);
				//代替key[i]的位置
				delNode.getItems()[delKeyIndex] = minKeyItem;
				//然后在叶结点中删去Y
				this.removeFromLeaf(nowDelNode, 0);
			}else{
				this.removeFromLeaf(delNode, delKeyIndex);
			}
		}
	}

	private BTreeNode findMinKeyItemNode(BTreeNode node){
		if(node!=null && node.isLeaf()){
			return node;
		}
		return this.findMinKeyItemNode(node.getChild(0));
	}

	private void removeFromLeaf(BTreeNode delNode,int delKeyIndex){
//	要删除的关键字在叶子节点上的情况
		//先把这个关键字删除掉
		delNode.removeKeyItem(delKeyIndex);

		//然后再调整
		this.afterRemove(delNode);
	}
	/**
	 * 删除识别码后的调整
	 * @param delNode
	 */
	private void afterRemove(BTreeNode delNode){
		int n = delNode.getNumItems();
		int minM = (int)(Math.ceil(m/2));
		if(m%2==0){
			minM = minM-1;
		}
//		1：如果被删关键字所在结点的原关键字个数n≥ceil(m/2)，直接删除，不做调整
		if(n>=minM){
			//
		}else if(n==(minM-1)){
//		2：如果其左右兄弟结点中有“多余”的关键字，即与该结点相邻的右（左）兄弟结点中的关键字数目大于ceil(m/2)。
//			则可将右（左）兄弟结点中最小（大）关键字上移至父结点，
//			而将父结点中小（大）于该上移关键字的关键字下移至被删关键字所在结点中
			BTreeNode rightBrotherNode = this.getRightBrotherNode(delNode);
			BTreeNode leftBrotherNode = this.getLeftBrotherNode(delNode);

			//先找右兄
			if(rightBrotherNode!=null && rightBrotherNode.getNumItems() > minM){
//				则可将右兄弟结点中最小关键字上移至父结点
				//而将父结点中小于该上移关键字的关键字下移至被删关键字所在结点中
				KeyItem rightMinKeyItem = rightBrotherNode.getItem(0);

				rightBrotherNode.removeKeyItem(0);
				//在父节点中查找要插入的位置
				int inParentIndex = delNode.getParent().getNumItems()-1;
				for(int i=0;i<delNode.getParent().getNumItems();i++){
					if(delNode.getParent().getItem(i).getId() > rightMinKeyItem.getId()){
						inParentIndex = i;
						break;
					}
				}

				delNode.insertKeyItem(delNode.getParent().getItem(inParentIndex));

				delNode.getParent().removeKeyItem(inParentIndex);

				delNode.getParent().insertKeyItem(rightMinKeyItem);

				//同时，原来rightBrotherNode的第一个child，就应该成为delNode里面最大的child
				delNode.getChildren()[delNode.getNumItems()] = rightBrotherNode.getChild(0);

				if(rightBrotherNode.getChild(0)!=null){
					delNode.getChildren()[delNode.getNumItems()].setParent(delNode);
				}

				for(int i=1;i<(rightBrotherNode.getNumItems()+2);i++){
					rightBrotherNode.getChildren()[i-1] = rightBrotherNode.getChild(i);
				}
			}else if(leftBrotherNode!=null && leftBrotherNode.getNumItems() > minM){
//				则可将左兄弟结点中最大关键字上移至父结点，
//				而将父结点中 大于该上移关键字的关键字下移至被删关键字所在结点中
				BTreeNode leftMaxChild = leftBrotherNode.getChild(leftBrotherNode.getNumItems());
				KeyItem leftMaxKeyItem = leftBrotherNode.removeKeyItem();

				//在父节点中查找要插入的位置
				int inParentIndex = delNode.getParent().getNumItems()-1;
				for(int i=0;i<delNode.getParent().getNumItems();i++){
					if(delNode.getParent().getItem(i).getId() > leftMaxKeyItem.getId()){
						inParentIndex = i;
						break;
					}
				}

				delNode.insertKeyItem(delNode.getParent().getItem(inParentIndex));
				delNode.getParent().removeKeyItem(inParentIndex);
				delNode.getParent().insertKeyItem(leftMaxKeyItem);

				//同时，原来leftBrotherNode的最后一个chid，应作为delNode的第一个child
				for(int i=(delNode.getNumItems()-1);i>=0;i--){
					delNode.getChildren()[i+1] = delNode.getChildren()[i];
				}
				delNode.getChildren()[0] = leftMaxChild;
				if(leftMaxChild!=null){
					leftMaxChild.setParent(delNode);
				}
			}else{
//		3：如果左右兄弟结点中没有“多余”的关键字，需把要删除关键字的结点与其左（或右）兄弟结点以及父结点中
//			分割二者的关键字合并成一个结点，即在删除关键字后，该结点中剩余的关键字，加上父结点中的关键字Ki一起，
//			合并到Ci所指的兄弟结点中去。如果因此使父结点中关键字个数小于ceil(m/2)，则对此父结点做同样处理。

				BTreeNode parent = delNode.getParent();
				if(leftBrotherNode!=null){
					//在父节点中查找分割关键字的位置
					int inParentIndex = 0;
					for(inParentIndex=0;inParentIndex<delNode.getParent().getNumItems();inParentIndex++){
						if(delNode.getParent().getItem(inParentIndex).getId() > leftBrotherNode.getItem(0).getId()){
							break;
						}
					}
					//由于是按左兄去查
					inParentIndex = (inParentIndex+1 > parent.getNumItems()) ? parent.getNumItems():inParentIndex+1;

					BTreeNode ci = parent.getChild(inParentIndex-1);
					//把delNode中剩余的关键字加入到ci
					for(int i=0;i<delNode.getNumItems();i++){
						if(delNode.getItem(i)!=null && delNode.getItem(i).getId()>0){
							ci.insertKeyItem(delNode.getItem(i));
						}
					}
					//把parent的分割关键字加入ci
					ci.insertKeyItem(parent.getItem(inParentIndex-1));

					//维护parent中的child
					int oldNumItems = parent.getNumItems();
					parent.removeKeyItem(inParentIndex-1);

					for(int i=inParentIndex;i<oldNumItems;i++){
						parent.getChildren()[i] = parent.getChild(i+1);
						parent.getChildren()[i+1] = null;
					}
					if(oldNumItems==inParentIndex){
						parent.getChildren()[oldNumItems] = null;
					}

					//ci的children应该是ci原来的child + delNode的child
					List<BTreeNode> tempList = new ArrayList<BTreeNode>();
					for(int i=0;i<ci.getChildren().length;i++){
						if(ci.getChild(i)==null || ci.getChild(i).getNumItems()==0){
							break;
						}
						tempList.add(ci.getChild(i));
					}
					for(int i=0;i<delNode.getChildren().length;i++){
						if(delNode.getChild(i)==null || delNode.getChild(i).getNumItems()==0){
							break;
						}
						tempList.add(delNode.getChild(i));
					}

					//设置新的ci的children
					BTreeNode[] ciChildren = new BTreeNode[ci.getNumItems()+1];

					for(int i=0;i<tempList.size();i++){
						ciChildren[i] = tempList.get(i);
						ciChildren[i].setParent(ci);
					}

					ci.setChildren(ciChildren);

					//如果父节点是根元素的话，需要设置新的根
					if(parent.getNumItems()==0 && parent==root){
						root = ci;
						return;
					}
				}else if(rightBrotherNode!=null){
					int inParentIndex = parent.getNumItems()-1;
					for(int i=0;i<delNode.getParent().getNumItems();i++){
						if(delNode.getParent().getItem(i).getId() > rightBrotherNode.getItem(0).getId()){
							inParentIndex = i;
							break;
						}
					}
					//由于是按左兄去查
					inParentIndex = (inParentIndex-1 <0) ? 0:inParentIndex-1;

					BTreeNode ci = parent.getChild(inParentIndex+1);
					//把delNode中剩余的关键字加入到ci
					for(int i=0;i<delNode.getNumItems();i++){
						if(delNode.getItem(i)!=null && delNode.getItem(i).getId()>0){
							ci.insertKeyItem(delNode.getItem(i));
						}
					}
					//把parent的分割关键字加入ci
					ci.insertKeyItem(parent.getItem(inParentIndex));

					//维护parent中的child
					int oldNumItems = parent.getNumItems();
					parent.removeKeyItem(inParentIndex);

					for(int i=inParentIndex+1;i<=oldNumItems;i++){
						parent.getChildren()[i-1] = parent.getChild(i);
						parent.getChildren()[i] = null;
					}
					//ci的child应该是原来delNode的child+ci的child
					List<BTreeNode> tempList = new ArrayList<BTreeNode>();

					for(int i=0;i<delNode.getChildren().length;i++){
						if(delNode.getChild(i)==null || delNode.getChild(i).getNumItems()==0){
							break;
						}
						tempList.add(delNode.getChild(i));
					}
					for(int i=0;i<ci.getChildren().length;i++){
						if(ci.getChild(i)==null || ci.getChild(i).getNumItems()==0){
							break;
						}
						tempList.add(ci.getChild(i));
					}

					//设置新的ci的children
					BTreeNode[] ciChildren = new BTreeNode[ci.getNumItems()+1];

					for(int i=0;i<tempList.size();i++){
						ciChildren[i] = tempList.get(i);
						ciChildren[i].setParent(ci);
					}

					ci.setChildren(ciChildren);

					//如果父节点是根元素的话，需要设置新的根
					if(parent.getNumItems()==0 && parent==root){
						root = ci;
						return;
					}
				}
				//递归处理父节点
				if(parent!=null){
					this.afterRemove(parent);
				}
			}
		}
	}
	/**
	 * 获取一个节点的右兄节点
	 * @param node
	 * @return
	 */
	private BTreeNode getRightBrotherNode(BTreeNode node){
		if(node!=root){
			int nodeIndex = -1;
			//在父节点里面找自己的child索引
			for(int i=0;i<node.getParent().getChildren().length;i++){
				if(node.getParent().getChild(i) == node){
					nodeIndex = i;
					break;
				}
			}
			if(nodeIndex < (m-1)){
				return node.getParent().getChild(nodeIndex+1);
			}

		}
		return null;
	}
	/**
	 * 获取一个节点的左兄节点
	 * @param node
	 * @return
	 */
	private BTreeNode getLeftBrotherNode(BTreeNode node){
		if(node!=root){
			int nodeIndex = -1;
			//在父节点里面找自己的child索引
			for(int i=0;i<node.getParent().getChildren().length;i++){
				if(node.getParent().getChild(i) == node){
					nodeIndex = i;
					break;
				}
			}
			if(nodeIndex > 0){
				return node.getParent().getChild(nodeIndex-1);
			}

		}
		return null;
	}


	public static void main(String[] args) {
		BTree t = new BTree(6);

		t.insert(20);
		t.insert(10);
		t.insert(15);
		t.insert(19);
		t.insert(8);
		t.insert(13);
		t.insert(30);
		t.insert(40);
		t.insert(50);
		t.insert(38);
		t.insert(9);

		t.removeKeyItem(8);
		t.removeKeyItem(9);
		t.removeKeyItem(20);
		t.removeKeyItem(19);

		t.displayTree(t.root,0,0);
	}
}