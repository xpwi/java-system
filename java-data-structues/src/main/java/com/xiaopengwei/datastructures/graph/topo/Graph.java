package com.xiaopengwei.datastructures.graph.topo;

/**
 * 演示有向图的拓扑排序
 *
 * @author XiaoPengwei.com
 */
public class Graph {
	/**
	 * 顶点数组
	 */
	private Vertex vertexList[];
	/**
	 * 邻接矩阵
	 */
	private int[][] matrix;
	/**
	 * 记录实际的顶点的个数
	 */
	private int numVertex;
	/**
	 * 记录拓扑排序的结果
	 */
	private String[] sortedArray;

	public Graph(int n){
		this.vertexList = new Vertex[n];
		matrix = new int[n][n];
		this.sortedArray = new String[n];
	}

	/**
	 * 新增顶点
	 * @param label
	 */
	public void addVertex(String label){
		this.vertexList[numVertex] = new Vertex(label);
		numVertex++;
	}
	/**
	 * 添加边
	 * @param start
	 * @param end
	 */
	public void addEdge(int start,int end){
		this.matrix[start][end] = 1;
	}
	/**
	 * 实现topo排序
	 */
	public void topo(){
		while(numVertex>0){
			//1：找到一个没有后继的顶点
			int currentVertex = noSuccessors();
			if(currentVertex == -1){
				System.out.println("估计图里面有环，不能做拓扑排序");
				return;
			}
			//2：把它加入到排序列表中
			this.sortedArray[numVertex-1] = this.vertexList[currentVertex].getLabel();
			//3；删除这个顶点
			this.deleteVertex(currentVertex);
		}
		//输出结果
		for(String s : sortedArray){
			System.out.print(s+" ");
		}
		System.out.println("");
	}
	/**
	 * 删除一个顶点，这里只是删除末尾的那个
	 * @param index
	 */
	private void deleteVertex(int index){
		//1：从顶点数组中删除顶点
		for(int i=index;i<numVertex-1;i++){
			this.vertexList[i] = vertexList[i+1];
		}
		//2：维护邻接矩阵的 行
		for(int row = index;row<numVertex-1;row++){
			this.moveRowUp(row, numVertex);
		}
		//3：维护邻接矩阵的 列
		for(int col=index;col<numVertex-1;col++){
			this.moveColLeft(col, numVertex - 1);
		}
		//4:整个的顶点个数-1
		numVertex--;
	}
	/**
	 * 把整列向左移动一列
	 * @param col
	 * @param length
	 */
	private void moveColLeft(int col,int length){
		for(int i=0;i<length;i++){
			this.matrix[i][col] = this.matrix[i][col+1];
		}
	}
	/**
	 * 把整行向上提升一行
	 * @param row
	 * @param length
	 */
	private void moveRowUp(int row,int length){
		for(int i=0;i<length;i++){
			this.matrix[row][i] = this.matrix[row+1][i];
		}
	}

	/**
	 * 寻找没有后继的顶点
	 * @return
	 */
	private int noSuccessors(){
		boolean hasEdge = false;

		for(int i=0;i<numVertex;i++){
			hasEdge = false;
			for(int j=0;j<numVertex;j++){
				if(this.matrix[i][j]>0){
					hasEdge = true;
					break;
				}
			}
			if(!hasEdge){
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Graph t = new Graph(100);

		t.addVertex("A");
		t.addVertex("B");
		t.addVertex("C");
		t.addVertex("D");
		t.addVertex("E");
		t.addVertex("F");
		t.addVertex("G");

		t.addEdge(0, 3);
		t.addEdge(0, 4);
		t.addEdge(1, 4);
		t.addEdge(2, 5);
		t.addEdge(3, 6);
		t.addEdge(5, 6);
		t.addEdge(4, 6);

		t.topo();

	}
}
