package br.com.gomide.data_structures.graph.model;

import java.util.ArrayList;

public abstract class Graph {
	private ArrayList<Vertice> vertices;
	private ArrayList<String> verticesString;
	private int multipleLinks;
	
	public Graph() {
		this.vertices = new ArrayList<>();
		this.verticesString = new ArrayList<>();
	}
	
	public abstract String toString(Graph graph);

	public void adicionaMultLink() {
		this.multipleLinks++;
	}
	
	
	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Vertice> vertices) {
		this.vertices = vertices;
	}

	public ArrayList<String> getVerticesString() {
		return verticesString;
	}

	public void setVerticesString(ArrayList<String> verticesString) {
		this.verticesString = verticesString;
	}

	public int getMultipleLinks() {
		return multipleLinks;
	}

	public void setMultipleLinks(int multipleLinks) {
		this.multipleLinks = multipleLinks;
	}

}
