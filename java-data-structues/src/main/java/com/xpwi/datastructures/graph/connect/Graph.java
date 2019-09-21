package com.xpwi.datastructures.graph.connect;

/**
 * 演示有向图的连通性
 *
 * @author xpwi
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
//		this.matrix[end][start] = 1;
	}
	/**
	 * 进行深度优先的遍历
	 */
	public void dfs(int begin){
		//1：
		this.vertexList[begin].setWasVisited(true);
		this.theStack.push(begin);
		System.out.print(this.vertexList[begin].getLabel()+" ");

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
	/**
	 * warshall算法
	 * @return
	 */
	public int[][] warshall(){
		int[][] newMartrix = this.matrix;

		for(int i=0;i<newMartrix.length;i++){
			for(int j=0;j<newMartrix[i].length;j++){
				if(i==j){
					continue;
				}
				if(newMartrix[i][j]==1){
					for(int k=0;k<newMartrix.length;k++){
						if(k==i||k==j){
							continue;
						}
						if(newMartrix[k][i]==1){
							newMartrix[k][j] = 1;
						}
					}
				}
			}
		}

		return newMartrix;
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

		for(int i=0;i<6;i++){
			t.dfs(i);
			System.out.println("");
		}
		System.out.println("---------------------------------->");

		int[][] ret = t.warshall();
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				System.out.println("i="+i+",j="+j+" , la="+ret[i][j]);
			}
		}

	}
}
