package com.xpwi.datastructures.tree234;

/**
 * 演示2-3-4树的操作
 *
 * @author github.com/xpwi
 */
public class Tree234 {
    private Node234 root = new Node234();

    /**
     * 根据key查找对象
     *
     * @param key
     * @return
     */
    public int find(int key) {
        Node234 current = root;
        int childIndex = -1;
        while (true) {
            childIndex = current.findKeyItem(key);
            if (childIndex >= 0) {
                return childIndex;
            } else if (current.isLeaf()) {
                return -1;
            } else {
                current = this.getNext(current, key);
            }
        }
    }

    /**
     * 查找下一个节点
     *
     * @param node
     * @param key
     * @return
     */
    private Node234 getNext(Node234 node, int key) {
        int i = 0;
        for (i = 0; i < node.getNumItems(); i++) {
            if (key < node.getItems()[i].getId()) {
                return node.getChild(i);
            }
        }
        return node.getChild(i);
    }

    /**
     * 插入一个识别数据
     *
     * @param id
     */
    public void insert(int id) {
        Node234 current = root;
        KeyItem newItem = new KeyItem(id);
        //查找要插入的位置
        while (true) {
            if (current.isFull()) {
                //满了情况
                //1：做节点分裂
                this.splitNode(current);
                //2：查找current
                //2.1 先得到current的父节点，因为本节点分裂了
                //2.2再从新查找下一个节点
                current = this.getNext(current.getParent(), id);
            } else {
                if (current.isLeaf()) {
                    break;
                } else {
                    current = this.getNext(current, id);
                }
            }
        }
        //真的加入
        current.insertKeyItem(newItem);
    }

    /**
     * 分裂节点
     *
     * @param node
     */
    private void splitNode(Node234 node) {
        //把原始的数据记录下来，并断开他们的关系
        KeyItem key2, key3;
        Node234 parent, child3, child4;

        key3 = node.removeKeyItem();
        key2 = node.removeKeyItem();

        child3 = node.disconnectChild(2);
        child4 = node.disconnectChild(3);

        //创建一个兄弟节点
        Node234 rightNode = new Node234();

        //如果是根节点，需要创建新的parent，并维护关系
        if (node == root) {
            root = new Node234();
            parent = root;
            root.connectChild(0, node);
        } else {
            parent = node.getParent();
        }

        //中间数据项移动到父节点
        int itemIndex = parent.insertKeyItem(key2);
        //维护parnet中的child节点的关系
        int num = parent.getNumItems();
        for (int i = num - 1; i > itemIndex; i--) {
            //先断开，再重新加入
            Node234 temp = parent.disconnectChild(i);
            parent.connectChild(i + 1, temp);
        }
        //把新的兄弟节点加入父节点
        parent.connectChild(itemIndex + 1, rightNode);

        //维护新的兄弟节点
        rightNode.insertKeyItem(key3);
        rightNode.connectChild(0, child3);
        rightNode.connectChild(1, child4);
    }

    public void displayTree(Node234 node) {
        node.displayNode();
        for (int i = 0; i < node.getNumItems() + 1; i++) {
            Node234 c = node.getChild(i);
            if (c == null) {
                return;
            } else {
                displayTree(c);
            }
        }
    }

    public static void main(String[] args) {
        Tree234 t = new Tree234();

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

        t.displayTree(t.root);
    }
}
