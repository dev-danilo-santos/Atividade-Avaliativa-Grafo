package br.com.gomide.data_structures.graph.model;

import java.util.ArrayList;

public class Vertice {
	private String valor;
	private ArrayList<Aresta> arestas;
	
	
	public Vertice(String valor) {
		this.valor = valor;
		this.arestas = new ArrayList<>();
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public ArrayList<Aresta> getArestas() {
		return arestas;
	}

	public void setArestas(ArrayList<Aresta> arestas) {
		this.arestas = arestas;
	}

	
	
	
}
