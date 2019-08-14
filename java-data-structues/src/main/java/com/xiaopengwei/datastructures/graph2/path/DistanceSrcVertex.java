package com.xiaopengwei.datastructures.graph2.path;

/**
 * 用来封装源点和距离的对象
 *
 * @author XiaoPengwei.com
 */
public class DistanceSrcVertex {
	private int srcVertex;
	private int distance;

	public DistanceSrcVertex(int srcVertex, int distance) {
		this.srcVertex = srcVertex;
		this.distance = distance;
	}
	public int getSrcVertex() {
		return srcVertex;
	}
	public void setSrcVertex(int srcVertex) {
		this.srcVertex = srcVertex;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}

}
