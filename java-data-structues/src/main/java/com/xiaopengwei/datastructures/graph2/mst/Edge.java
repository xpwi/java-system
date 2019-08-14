package com.xiaopengwei.datastructures.graph2.mst;

/**
 * 封装带权图的边的对象
 *
 * @author XiaoPengwei.com
 */
public class Edge {
	/**
	 * 源顶点
	 */
	private int srcVertex;
	/**
	 * 目的地顶点
	 */
	private int destVertex;
	/**
	 * 权值，是业务数据，这里表示的是距离
	 */
	private int distance;

	public Edge(int srcVertex, int destVertex, int distance) {
		super();
		this.srcVertex = srcVertex;
		this.destVertex = destVertex;
		this.distance = distance;
	}
	public int getSrcVertex() {
		return srcVertex;
	}
	public void setSrcVertex(int srcVertex) {
		this.srcVertex = srcVertex;
	}
	public int getDestVertex() {
		return destVertex;
	}
	public void setDestVertex(int destVertex) {
		this.destVertex = destVertex;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
}
