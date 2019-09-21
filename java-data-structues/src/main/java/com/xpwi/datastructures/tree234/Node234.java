package com.xpwi.datastructures.tree234;

/**
 * 用来封装2-3-4树节点的对象
 *
 * @author XiaoPengwei.com
 */
public class Node234 {
    /**
     * 存放多个识别数据项
     */
    private KeyItem[] items = new KeyItem[3];
    /**
     * 存放多个子节点对象
     */
    private Node234[] children = new Node234[4];
    /**
     * 父节点对象
     */
    private Node234 parent = null;
    /**
     * 用来记录本节点到底存放了几个识别数据项
     */
    private int numItems;


    /**
     * 给本节点，新加入一个识别数据项
     *
     * @param newItem
     * @return
     */
    public int insertKeyItem(KeyItem newItem) {
        //维护存放的识别数据项的 个数
        numItems++;

        //从后向前进行数据查找和比较
        for (int i = 2; i >= 0; i--) {
            if (this.items[i] == null) {
                continue;
            } else {
                if (newItem.getId() > items[i].getId()) {
                    items[i + 1] = newItem;
                    return i + 1;
                } else {
                    items[i + 1] = items[i];
                }
            }
        }
        items[0] = newItem;

        return 0;
    }

    /**
     * 从本节点，删除一个识别数据项
     *
     * @return
     */
    public KeyItem removeKeyItem() {
        KeyItem ret = items[this.numItems - 1];
        items[this.numItems - 1] = null;
        this.numItems--;
        return ret;
    }

    /**
     * 查找本节点是否有值为key的识别数据项
     *
     * @param key
     * @return
     */
    public int findKeyItem(int key) {
        for (int i = 0; i < 3; i++) {
            if (items[i] == null) {
                break;
            } else if (items[i].getId() == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 给本节点连接一个子节点
     *
     * @param childIndex
     * @param childNode
     */
    public void connectChild(int childIndex, Node234 childNode) {
        this.children[childIndex] = childNode;
        if (childNode != null) {
            childNode.setParent(this);
        }
    }

    /**
     * 从本节点断开某个子节点的连接
     *
     * @param childIndex
     * @return
     */
    public Node234 disconnectChild(int childIndex) {
        Node234 ret = this.children[childIndex];
        this.children[childIndex] = null;
        return ret;
    }

    /**
     * 获取某个子节点
     *
     * @param childIndex
     * @return
     */
    public Node234 getChild(int childIndex) {
        return this.children[childIndex];
    }

    /**
     * 得到父节点对象
     *
     * @return
     */
    public Node234 getParent() {
        return this.parent;
    }

    /**
     * 判断是否叶子节点
     *
     * @return
     */
    public boolean isLeaf() {
        return this.children[0] == null;
    }

    /**
     * 判断本节点的识别数据项是否已经满了
     *
     * @return
     */
    public boolean isFull() {
        return this.numItems == 3;
    }

    public void displayNode() {
        for (int i = 0; i < this.numItems; i++) {
            System.out.println(this.items[i].getId() + ",");
        }
        System.out.println(" --- ");
    }

    public KeyItem[] getItems() {
        return items;
    }

    public void setItems(KeyItem[] items) {
        this.items = items;
    }

    public Node234[] getChildren() {
        return children;
    }

    public void setChildren(Node234[] children) {
        this.children = children;
    }

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public void setParent(Node234 parent) {
        this.parent = parent;
    }
}
