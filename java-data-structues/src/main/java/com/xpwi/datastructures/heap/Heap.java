package com.xpwi.datastructures.heap;

/**
 * 演示堆操作的对象
 *
 * @author github.com/xpwi
 */
public class Heap {
    private Node[] heapArray;
    private int maxSize;

    private int currentSize = 0;

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        this.heapArray = new Node[this.maxSize];
    }

    /**
     * 新增一个节点
     *
     * @param node
     */
    public void insert(Node node) {
        //1:插入到数组末尾
        if (currentSize == maxSize) {
            System.out.println("已经到末尾了，不能插入新数据");
            return;
        }

        this.heapArray[currentSize] = node;

        //2:向上筛选
        this.upNode(currentSize);

        currentSize++;
    }

    /**
     * 向上筛选节点的位置
     *
     * @param index
     */
    private void upNode(int index) {
        int parent = (index - 1) / 2;
        Node bottom = this.heapArray[index];

        while (index > 0 && heapArray[parent].getId() < bottom.getId()) {
            this.heapArray[index] = this.heapArray[parent];
            index = parent;
            parent = (index - 1) / 2;
        }

        this.heapArray[index] = bottom;
    }

    /**
     * 删除最大根
     *
     * @return
     */
    public Node remove() {
        Node root = this.heapArray[0];
        //把最后一个节点移动到根的位置
        this.currentSize -= 1;
        this.heapArray[0] = this.heapArray[this.currentSize];
        this.heapArray[this.currentSize] = null;
        //向下筛选
        this.downNode(0);

        return root;
    }

    /**
     * 向下筛选节点的位置
     *
     * @param index
     */
    private void downNode(int index) {
        int largerChildIndex = -1;
        Node top = this.heapArray[index];

        while (index < this.currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;

            if (rightChild < this.currentSize
                    && this.heapArray[leftChild].getId() < this.heapArray[rightChild].getId()
            ) {
                largerChildIndex = rightChild;
            } else {
                largerChildIndex = leftChild;
            }

            if (top.getId() >= this.heapArray[largerChildIndex].getId()) {
                break;
            }
            this.heapArray[index] = this.heapArray[largerChildIndex];

            index = largerChildIndex;
        }

        this.heapArray[index] = top;
    }

    public void display() {
        for (Node n : this.heapArray) {
            if (n != null) {
                System.out.print(n.getId() + " , ");
            }
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Heap t = new Heap(10);

        t.insert(new Node(5, 5));
        t.insert(new Node(9, 5));
        t.insert(new Node(6, 5));
        t.insert(new Node(3, 5));
        t.insert(new Node(4, 5));
        t.insert(new Node(1, 5));
        t.insert(new Node(10, 5));
        t.insert(new Node(7, 5));
        t.insert(new Node(2, 5));
        t.insert(new Node(8, 5));

        t.display();

//		Node n1 = t.remove();
//		System.out.println("n1==="+n1.getId());
//		t.display();
//		Node n2 = t.remove();
//		System.out.println("n2==="+n2.getId());
//		t.display();

        System.out.println("----------------------------->");

        for (int i = 0; i < t.maxSize; i++) {

            Node n = t.remove();
            if (n == null) {
                break;
            }
            System.out.print(n.getId() + " , ");
        }

    }
}
