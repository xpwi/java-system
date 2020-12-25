package com.xpwi.datastructures.heap;

/**
 * 演示堆排序操作的对象
 *
 * @author github.com/xpwi
 */
public class HeapSort {
    private Node[] heapArray;
    private int maxSize;

    private int currentSize = 0;

    public HeapSort(int maxSize) {
        this.maxSize = maxSize;
        this.heapArray = new Node[this.maxSize];
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

    public void insertAt(int index, Node node) {
        this.heapArray[index] = node;
    }

    public void setCurrentSize() {
        this.currentSize++;
    }

    public Node[] sort() {
        for (int i = (maxSize / 2 - 1); i >= 0; i--) {
            this.downNode(i);
        }
        for (int i = maxSize - 1; i >= 0; i--) {
            Node top = this.remove();
            insertAt(i, top);
        }
        return this.heapArray;
    }

    public static void main(String[] args) {
        HeapSort t = new HeapSort(10);

        t.insertAt(0, new Node(5, 5));
        t.setCurrentSize();
        t.insertAt(1, new Node(9, 5));
        t.setCurrentSize();
        t.insertAt(2, new Node(6, 5));
        t.setCurrentSize();
        t.insertAt(3, new Node(3, 5));
        t.setCurrentSize();
        t.insertAt(4, new Node(4, 5));
        t.setCurrentSize();
        t.insertAt(5, new Node(1, 5));
        t.setCurrentSize();
        t.insertAt(6, new Node(10, 5));
        t.setCurrentSize();
        t.insertAt(7, new Node(7, 5));
        t.setCurrentSize();
        t.insertAt(8, new Node(2, 5));
        t.setCurrentSize();
        t.insertAt(9, new Node(8, 5));
        t.setCurrentSize();


        t.display();

        t.sort();
        t.display();

    }
}
