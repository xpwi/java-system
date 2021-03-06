package com.xpwi.datastructures.graph.connect;

/**
 * 用来描述顶点的对象
 *
 * @author github.com/xpwi
 */
public class Vertex {
	private String label;
	private boolean wasVisited;

	public Vertex(String label) {
		super();
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isWasVisited() {
		return wasVisited;
	}
	public void setWasVisited(boolean wasVisited) {
		this.wasVisited = wasVisited;
	}
}
