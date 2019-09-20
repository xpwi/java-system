package com.xiaopengwei.datastructures.graph2.mst;

/**
 * 演示带权图的最小生成树
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
	 * 当前的顶点
	 */
	private int currentVertex;
	/**
	 * 优先级队列
	 */
	private PriorityQueue pq;
	/**
	 * 放入树当中的顶点个数
	 */
	private int numTree;

	public Graph(int n){
		this.vertexList = new Vertex[n];
		matrix = new int[n][n];
		this.pq = new PriorityQueue(n);
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
	public void addEdge(int start,int end,int distance){
		this.matrix[start][end] = distance;
		this.matrix[end][start] = distance;
	}

	/**
	 * 构建最小生成树
	 */
	public void mst(){
		currentVertex = 0;
		while(numTree<numVertex-1){
			//1:
			this.vertexList[currentVertex].setInTree(true);
			numTree++;
			//2:
			for(int i=0;i<numVertex;i++){
				if(i==currentVertex
						|| this.vertexList[i].isInTree()
						|| this.matrix[currentVertex][i]==0
				){
					continue;
				}
				this.putInPQ(i, this.matrix[currentVertex][i]);
			}
			//3:
			Edge minEdge = pq.remove();
			int srcVertex = minEdge.getSrcVertex();
			currentVertex = minEdge.getDestVertex();

			System.out.println(this.vertexList[srcVertex].getLabel()
					+"-->"+this.vertexList[currentVertex].getLabel());

		}
		//4：恢复所有的 inTree的状态
		for(int i=0;i<numVertex;i++){
			this.vertexList[i].setInTree(false);
		}
	}

	private void putInPQ(int newVertex,int distance){
		int queueIndex = pq.findDestVertex(newVertex);
		if(queueIndex!=-1){
			Edge tempEdge = pq.peekIndex(queueIndex);
			if(tempEdge.getDistance() > distance){
				pq.removeIndex(queueIndex);
				Edge newEdge = new Edge(currentVertex,newVertex,distance);
				pq.insert(newEdge);
			}
		}else{
			Edge newEdge = new Edge(currentVertex,newVertex,distance);
			pq.insert(newEdge);
		}
	}
	public static void main(String[] args) {
		Graph t = new Graph(10);

		t.addVertex("A");
		t.addVertex("B");
		t.addVertex("C");
		t.addVertex("D");

		t.addEdge(0, 1,30);
		t.addEdge(0, 2,35);
		t.addEdge(1, 2,50);
		t.addEdge(2, 3,40);

		t.mst();
	}
}
