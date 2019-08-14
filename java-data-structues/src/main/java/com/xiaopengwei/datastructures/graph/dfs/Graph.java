package com.xiaopengwei.datastructures.graph.dfs;

/**
 * 演示图(无向)的深度优先遍历
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
	 * 栈
	 */
	private MyStack theStack;

	public Graph(int n){
		this.vertexList = new Vertex[n];
		matrix = new int[n][n];
		this.theStack = new MyStack(100);
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
		this.matrix[end][start] = 1;
	}
	/**
	 * 进行深度优先的遍历
	 */
	public void dfs(){
		//1：
		this.vertexList[0].setWasVisited(true);
		this.theStack.push(0);
		System.out.print(this.vertexList[0].getLabel()+" ");

		//2:3:
		while(!theStack.isEmpty()){
			//找到下一个未访问的邻接顶点
			int v = this.getNextUnvisitedVertex(theStack.peek());
			if(v==-1){
				//找不到下一个了，就弹出
				this.theStack.pop();
			}else{
				this.vertexList[v].setWasVisited(true);
				this.theStack.push(v);
				System.out.print(this.vertexList[v].getLabel()+" ");
			}
		}

		//3:把所有的访问状态还原，这样就不会影响到后续的操作
		for(int i=0;i<numVertex;i++){
			this.vertexList[i].setWasVisited(false);
		}
	}
	/**
	 * 找到下一个未访问的邻接顶点
	 * @param index
	 * @return
	 */
	private int getNextUnvisitedVertex(int index){
		for(int i=0;i<numVertex;i++){
			if(this.matrix[index][i] == 1
					&& !this.vertexList[i].isWasVisited()
			){
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Graph t = new Graph(10);

		t.addVertex("A");
		t.addVertex("B");
		t.addVertex("C");
		t.addVertex("D");
		t.addVertex("E");
		t.addVertex("F");

		t.addEdge(0, 1);
		t.addEdge(0, 2);
		t.addEdge(0, 3);
		t.addEdge(1, 2);
		t.addEdge(1, 3);
		t.addEdge(2, 4);
		t.addEdge(4, 5);

		t.dfs();

	}
}
