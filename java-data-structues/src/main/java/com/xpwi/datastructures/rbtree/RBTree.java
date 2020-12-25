package com.xpwi.datastructures.rbtree;

/**
 * 演示红黑树的操作
 *
 * @author github.com/xpwi
 */
public class RBTree {
    /**
     * 根节点
     */
    private RBNode root;
    /**
     * 用来标示找到的节点对象是父节点的左子还是右子
     */
    private boolean isLeftChild = true;
/////////////////////////////////////////////////////////////////////////////

    /**
     * 在加入一个新节点后，对红黑树进行校验并调整
     *
     * @param nowNode
     */
    private void afterInsert(RBNode nowNode) {
//		1：如果插入的是根节点，那么违反规则2，就直接把节点修改为黑色
        if (nowNode.getParent() == null) {
            nowNode.setRed(false);
            root = nowNode;
        } else if (!nowNode.getParent().isRed()) {
//		2：如果插入节点的父节点是黑色的，说明符合规则，什么都不做
        } else if (nowNode.getParent().isRed()) {
            RBNode g = nowNode.getParent().getParent();
            RBNode u = null;
            if (g != null) {
                u = (g.getLeftChild() == nowNode.getParent()) ? g.getRightChild() : g.getLeftChild();
            }
            if (u != null && u.isRed()) {
//		3：如果插入节点的父节点是红色的，且祖父结点的另一个子节点（叔叔节点）也是红色的，
//			那么：将祖父节点变红，而父和叔节点变黑，然后设置祖父节点为当前节点，然后重新开始判断。
                g.setRed(true);
                u.setRed(false);
                nowNode.getParent().setRed(false);

                nowNode = g;
                this.afterInsert(nowNode);
            } else if ((u == null || !u.isRed())
                    && nowNode == nowNode.getParent().getLeftChild()
                    && (g != null && nowNode.getParent() == g.getLeftChild())
            ) {
//		4：如果插入节点的父节点是红色，而叔节点是黑色，且插入节点是其父的左子节点，而父节点是祖父节点的左子节点，
//			那么：把父节点变为黑色，祖父节点变为红色，然后对祖父节点进行右旋，然后重新开始判断。
                nowNode.getParent().setRed(false);
                g.setRed(true);

                this.rightRotate(g);

                this.afterInsert(nowNode);
            } else if ((u == null || !u.isRed())
                    && nowNode == nowNode.getParent().getRightChild()
                    && (g != null && nowNode.getParent() == g.getRightChild())
            ) {
//		5：如果插入节点的父节点是红色，而叔节点是黑色，且插入节点是其父的右子节点，而父节点是祖父节点的右子节点，
//			那么：把父节点变为黑色，祖父节点变为红色，然后对祖父节点进行左旋，然后重新开始判断。
                nowNode.getParent().setRed(false);
                g.setRed(true);

                this.leftRotate(g);

                this.afterInsert(nowNode);
            } else if ((u == null || !u.isRed())
                    && nowNode == nowNode.getParent().getRightChild()
                    && (g != null && nowNode.getParent() == g.getLeftChild())
            ) {
//		6：如果插入节点的父节点是红色，而叔节点是黑色，且插入节点是其父的右子节点，而父节点是祖父节点的左子节点，
//			那么：把当前节点的父节点做为新的当前节点，对新的当前节点进行左旋，然后重新开始判断。
                RBNode oldParent = nowNode.getParent();

                this.leftRotate(oldParent);

                this.afterInsert(oldParent);
            } else if ((u == null || !u.isRed())
                    && nowNode == nowNode.getParent().getLeftChild()
                    && (g != null && nowNode.getParent() == g.getRightChild())
            ) {
//		7：如果插入节点的父节点是红色，而叔节点是黑色，且插入节点是其父的左子节点，而父节点是祖父节点的右子节点，
//			那么：把当前节点的父节点做为新的当前节点，对新的当前节点进行右旋，然后重新开始判断
                RBNode oldParent = nowNode.getParent();

                this.rightRotate(oldParent);

                this.afterInsert(oldParent);
            }
        }

    }

    /**
     * 右旋操作
     *
     * @param node
     */
    private void rightRotate(RBNode node) {
        //记录右旋节点 原始的左子节点
        RBNode oldLeftChild = node.getLeftChild();
        //记录右旋节点 原始的左子节点 的 右子节点
        RBNode oldLeftRightChild = null;

        if (oldLeftChild != null) {
            oldLeftRightChild = oldLeftChild.getRightChild();
        }

        if (node.getParent() != null) {
            //判断是父节点的左子还是右子
            boolean isLeftChild = (node.getParent().getLeftChild() == node);
            if (isLeftChild) {
                node.getParent().setLeftChild(oldLeftChild);
            } else {
                node.getParent().setRightChild(oldLeftChild);
            }
            if (oldLeftChild != null) {
                oldLeftChild.setParent(node.getParent());
            }
        } else {
            oldLeftChild.setParent(null);
            oldLeftChild.setRed(false);
            root = oldLeftChild;
        }

        if (oldLeftChild != null) {
            oldLeftChild.setRightChild(node);
        }
        node.setParent(oldLeftChild);

        node.setLeftChild(oldLeftRightChild);
        if (oldLeftRightChild != null) {
            oldLeftRightChild.setParent(node);
        }
    }

    /**
     * 左旋操作
     *
     * @param node
     */
    private void leftRotate(RBNode node) {
        RBNode oldRightChild = node.getRightChild();
        RBNode oldRightLeftChild = null;
        if (oldRightChild != null) {
            oldRightLeftChild = oldRightChild.getLeftChild();
        }

        if (node.getParent() != null) {
            //判断是父节点的左子还是右子
            boolean isLeftChild = (node.getParent().getLeftChild() == node);
            if (isLeftChild) {
                node.getParent().setLeftChild(oldRightChild);
            } else {
                node.getParent().setRightChild(oldRightChild);
            }
            if (oldRightChild != null) {
                oldRightChild.setParent(node.getParent());
            }
        } else {
            oldRightChild.setParent(null);
            oldRightChild.setRed(false);
            root = oldRightChild;
        }

        if (oldRightChild != null) {
            oldRightChild.setLeftChild(node);
        }
        node.setParent(oldRightChild);

        node.setRightChild(oldRightLeftChild);
        if (oldRightLeftChild != null) {
            oldRightLeftChild.setParent(node);
        }
    }
////////////////////////////////////////////////////////////////////////////

    /**
     * 删除节点后的调整操作
     *
     * @param node
     */
    private void afterDelete(RBNode nowNode) {
//		1：当前节点是红，那么：直接把当前节点变成黑色，结束
        if (nowNode.isRed()) {
            nowNode.setRed(false);
        } else if (nowNode.getParent() == null) {
//		2：当前节点是黑且是根节点，那么：什么都不用做，结束
            nowNode.setRed(false);
        } else if (!nowNode.isRed()) {
            RBNode g = nowNode.getParent().getParent();
            RBNode b = (nowNode == nowNode.getParent().getLeftChild()) ? nowNode.getParent().getRightChild() :
                    nowNode.getParent().getLeftChild();

            if (b.isRed() && nowNode == nowNode.getParent().getLeftChild()) {
//		3：当前节点是黑且兄弟节点为红色，当前节点为父节点的左子节点，
//			那么：把兄弟结点变成父节点的颜色，把父节点变成红色，然后在父节点上做左旋，再重新开始判断。
                b.setRed(nowNode.getParent().isRed());
                nowNode.getParent().setRed(true);

                this.leftRotate(nowNode.getParent());

                this.afterDelete(nowNode);
            } else if (b.isRed() && nowNode == nowNode.getParent().getRightChild()) {
//		4：当前节点是黑且兄弟节点为红色，当前节点为父节点的右子节点，
//			那么：把兄弟结点变成父节点的颜色，把父节点变成红色，然后在父节点上做右旋，再重新开始判断。
                b.setRed(nowNode.getParent().isRed());
                nowNode.getParent().setRed(true);

                this.rightRotate(nowNode.getParent());

                this.afterDelete(nowNode);
            } else if (!b.isRed() && !nowNode.getParent().isRed()
                    && (b.getLeftChild() == null || !b.getLeftChild().isRed())
                    && (b.getRightChild() == null || !b.getRightChild().isRed())
            ) {
//		5：当前节点是黑且父节点和兄弟节点都为黑色，兄弟节点的两个子节点全为黑色，
//			那么：把兄弟节点变红，然后把父节点当成新的当前节点，再重新开始判断
                b.setRed(true);
                nowNode = nowNode.getParent();

                this.afterDelete(nowNode);
            } else if (!b.isRed() && nowNode.getParent().isRed()
                    && (b.getLeftChild() == null || !b.getLeftChild().isRed())
                    && (b.getRightChild() == null || !b.getRightChild().isRed())) {
//		6：当前节点是黑且兄弟节点为黑色，兄弟节点的两个子节点都是黑色，但是父节点是红色，
//			那么：就把兄弟节点变红，父节点变黑，结束
                b.setRed(true);
                nowNode.getParent().setRed(false);

            } else if (!b.isRed() && nowNode == nowNode.getParent().getLeftChild()
                    && (b.getLeftChild() != null && b.getLeftChild().isRed())
                    && (b.getRightChild() == null || !b.getRightChild().isRed())
            ) {
//		7：当前节点是黑且兄弟节点为黑色，兄弟节点的左子是红色，右子是黑色，而且当前节点是父节点的左子节点，
//			那么：把兄弟节点变红，兄弟左子节点变黑，然后对兄弟节点进行右旋，再重新开始判断
                b.setRed(true);
                b.getLeftChild().setRed(false);

                this.rightRotate(b);

                this.afterDelete(nowNode);
            } else if (!b.isRed() && nowNode == nowNode.getParent().getRightChild()
                    && (b.getRightChild() != null && b.getRightChild().isRed())
                    && (b.getLeftChild() == null || !b.getLeftChild().isRed())) {
//		8：当前节点是黑且兄弟节点为黑色，兄弟节点的左子是黑色，右子是红色，而且当前节点是父节点的右子节点，
//			那么：把兄弟节点变红，兄弟右子节点变黑，然后对兄弟节点左旋，再重新开始判断
                b.setRed(true);
                b.getRightChild().setRed(false);

                this.leftRotate(b);

                this.afterDelete(nowNode);
            } else if (!b.isRed() && nowNode == nowNode.getParent().getLeftChild()
                    && (b.getRightChild() != null && b.getRightChild().isRed())
            ) {
//		9：当前节点是黑且兄弟节点为黑色，兄弟节点的右子是红色，左子的颜色任意，而且当前节点是父节点的左子节点，
//			那么：把兄弟节点变成当前节点父节点的颜色，把当前节点父节点变黑，兄弟节点右子变黑，然后以当前节点的父节点为支点进行左旋，结束。
                b.setRed(nowNode.getParent().isRed());
                nowNode.getParent().setRed(false);

                b.getRightChild().setRed(false);

                this.leftRotate(nowNode.getParent());
            } else if (!b.isRed() && nowNode == nowNode.getParent().getRightChild()
                    && (b.getLeftChild() != null && b.getLeftChild().isRed())) {
//		10：当前节点是黑且兄弟节点为黑色，兄弟节点的左子是红色，右子的颜色任意，而且当前节点是父节点的右子节点，
//			那么：把兄弟节点变成当前节点父节点的颜色，把当前节点父节点变黑，兄弟节点左子变黑，然后以当前节点的父节点为支点进行右旋，结束。
                b.setRed(nowNode.getParent().isRed());
                nowNode.getParent().setRed(false);

                b.getLeftChild().setRed(false);

                this.rightRotate(nowNode.getParent());
            }
        }
    }

    /**
     * 删除的时候，用来查找需要删除的节点
     *
     * @param node
     * @param key
     * @return
     */
    private RBNode findOneNode(RBNode node, int key) {
        if (node != null) {
            if (node.getId() == key) {
                return node;
            }
            RBNode tempNode = findOneNode(node.getLeftChild(), key);
            if (tempNode != null) {
                if (tempNode == tempNode.getParent().getLeftChild()) {
                    this.isLeftChild = true;
                } else {
                    this.isLeftChild = false;
                }
                return tempNode;
            }

            tempNode = findOneNode(node.getRightChild(), key);
            if (tempNode != null) {
                if (tempNode == tempNode.getParent().getLeftChild()) {
                    this.isLeftChild = true;
                } else {
                    this.isLeftChild = false;
                }
                return tempNode;
            }
        }
        return null;
    }

///////////////////////////////////////////////////////////////////////////

    /**
     * 查找一个节点
     *
     * @param key 要查找的id值
     * @return
     */
    public RBNode find(int key) {
        //当前节点
        RBNode current = root;
        while (current.getId() != key) {
            if (current.getId() > key) {
                current = current.getLeftChild();
            } else if (current.getId() < key) {
                current = current.getRightChild();
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    /**
     * 插入一个节点
     *
     * @param id
     * @param data
     */
    public void insert(int id, int data) {
        //1：先创建一个新节点
        RBNode newNode = new RBNode(id, data, true);

        if (root == null) {
            root = newNode;
        } else {
            //2：查找要插入的位置
            RBNode current = root;
            RBNode parent = null;

            while (true) {
                parent = current;
                if (id < current.getId()) {
                    current = current.getLeftChild();
                    //如果没有左子节点
                    if (current == null) {
                        //3：修改相应的节点的属性
                        parent.setLeftChild(newNode);

                        //add
                        newNode.setParent(parent);
                        break;
                    }
                } else {
                    current = current.getRightChild();
                    if (current == null) {
                        //3：修改相应的节点的属性
                        parent.setRightChild(newNode);

                        //add
                        newNode.setParent(parent);
                        break;
                    }
                }
            }
        }

        //插入结束过后，开始调整
        this.afterInsert(newNode);
    }

    /**
     * 前序获取节点数据
     *
     * @param node
     */
    public void preOrder(RBNode node) {
        if (node != null) {
            String pId = "";
            if (node.getParent() != null && node.getParent().getId() > 0) {
                pId = "" + node.getParent().getId();
            }
            if (node.getId() >= 0) {
                System.out.println(node.getId() + "," + node.isRed() + "," + pId + " --- ");
            }
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());
        }
    }

    /**
     * 中序获取节点数据
     *
     * @param node
     */
    public void inOrder(RBNode node) {
        if (node != null) {
            inOrder(node.getLeftChild());
            System.out.println(node.getId() + " - ");
            inOrder(node.getRightChild());
        }
    }

    /**
     * 后序获取节点数据
     *
     * @param node
     */
    public void postOrder(RBNode node) {
        if (node != null) {
            postOrder(node.getLeftChild());
            postOrder(node.getRightChild());
            System.out.println(node.getId() + " - ");
        }
    }

    /**
     * 获取最小节点
     *
     * @return
     */
    public RBNode getMinNode() {
        RBNode current = root;
        RBNode lastNode = null;
        while (current != null) {
            lastNode = current;
            current = current.getLeftChild();
        }
        return lastNode;
    }

    /**
     * 获取最大节点
     *
     * @return
     */
    public RBNode getMaxNode() {
        RBNode current = root;
        RBNode lastNode = null;
        while (current != null) {
            lastNode = current;
            current = current.getRightChild();
        }
        return lastNode;
    }

    /**
     * 删除一个节点
     *
     * @param key
     * @return
     */
    public boolean delete(int key) {
        //1：找到要删除的节点
        RBNode current = root;
        RBNode parent = root;

        //用来记录顶替被删节点的那个节点
        RBNode nowNode = null;

        current = this.findOneNode(root, key);
        if (current == null) {
            return true;
        }
        parent = current.getParent();

        //2：没有子节点
        if ((current.getLeftChild() == null || current.getLeftChild().getId() < 0)
                && (current.getRightChild() == null || current.getRightChild().getId() < 0)) {
            this.noChildren(parent, current, isLeftChild);

            if (!current.isRed()) {
                nowNode = new RBNode(-1, -1, false);
                nowNode.setParent(current.getParent());

                if (parent != null) {
                    if (this.isLeftChild) {
                        parent.setLeftChild(nowNode);
                    } else {
                        parent.setRightChild(nowNode);
                    }
                }
            }

            current.setParent(null);
        }
        //3：只有一个子节点
        //只有左节点
        else if (current.getRightChild() == null || current.getRightChild().getId() < 0) {
            this.oneLeftChild(parent, current, isLeftChild);

            if (!current.isRed() && current.getLeftChild().isRed()) {
                current.getLeftChild().setRed(false);
            } else if (!current.isRed() && current.getLeftChild().isRed()) {
                nowNode = current.getLeftChild();
            }
        }
        //只有右节点
        else if (current.getLeftChild() == null || current.getLeftChild().getId() < 0) {
            this.oneRightChild(parent, current, isLeftChild);

            if (!current.isRed() && current.getRightChild().isRed()) {
                current.getRightChild().setRed(false);
            } else {
                nowNode = current.getRightChild();
            }
        }
        //4：有两个子节点
        else {
            //4.1：找到中序后继节点
            RBNode successor = this.getSuccessor(current);

            //4.2把这两个节点的数据交换一下，不要复制颜色，也不改变其原有的父子等关系，
            RBNode tempNode = new RBNode(successor.getId(), successor.getData(), successor.isRed());

            successor.setId(current.getId());
            successor.setData(current.getData());

            current.setId(tempNode.getId());
            current.setData(tempNode.getData());

            //4.3然后重新进行删除
            this.delete(successor.getId());
        }

        //删除节点后，进行树的平衡
        if (nowNode != null) {
            afterDelete(nowNode);
        }
        return true;
    }

    /**
     * 找到要删除节点的中序后继节点
     *
     * @param delNode
     * @return
     */
    private RBNode getSuccessor(RBNode delNode) {
        RBNode successor = delNode;
        RBNode successorParent = delNode;
        RBNode current = delNode.getRightChild();
        //查找节点
        while (current != null) {
            successorParent = successor;
            successor = current;
            //要过滤掉id=-1的空子节点
            if (current.getLeftChild() != null && current.getLeftChild().getId() > 0) {
                current = current.getLeftChild();
            } else {
                current = null;
            }
        }
        //设置相应的值
//		if(successor!=delNode.getRightChild()){
//			successorParent.setLeftChild(successor.getRightChild());
//
//			if(successor.getRightChild()!=null){
//				successor.getRightChild().setParent(successorParent);
//			}
//
//			successor.setRightChild(delNode.getRightChild());
//			delNode.getRightChild().setParent(successor);
//		}
        return successor;
    }

    private void oneRightChild(RBNode parent, RBNode current, boolean isLeftChild) {
        if (current == root) {
            root = current.getRightChild();

            current.getRightChild().setParent(null);
        } else {
            if (isLeftChild) {
                parent.setLeftChild(current.getRightChild());

                current.getRightChild().setParent(parent);
            } else {
                parent.setRightChild(current.getRightChild());

                current.getRightChild().setParent(parent);
            }
        }
    }

    private void oneLeftChild(RBNode parent, RBNode current, boolean isLeftChild) {
        if (current == root) {
            root = current.getLeftChild();

            current.getLeftChild().setParent(null);
        } else {
            if (isLeftChild) {
                parent.setLeftChild(current.getLeftChild());

                current.getLeftChild().setParent(parent);
            } else {
                parent.setRightChild(current.getLeftChild());

                current.getLeftChild().setParent(parent);
            }
        }
    }

    private void noChildren(RBNode parent, RBNode current, boolean isLeftChild) {
        if (current == root) {
            root = null;
        } else {
            if (isLeftChild) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
        }
    }

    public static void main(String[] args) {
        RBTree t = new RBTree();

        t.insert(6, 6);
        t.insert(5, 2);
        t.insert(8, 2433);
        t.insert(3, 5);
        t.insert(7, 77);
        t.insert(9, 233);


        t.delete(6);

        t.preOrder(t.root);


    }
}
