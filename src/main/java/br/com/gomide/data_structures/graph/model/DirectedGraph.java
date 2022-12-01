package br.com.gomide.data_structures.graph.model;

public class DirectedGraph extends Graph {

	@Override
	public String toString(Graph graph) {
		String aux = "Nodes:";
		for (String node : graph.getVerticesString()) {
			aux+=" "+node;
		}
		for (Vertice vertice : graph.getVertices()) {
			if(vertice.getArestas() != null && vertice.getArestas().size() != 0) {
				for(int i = 0; i < vertice.getArestas().size(); i++) {
					if(vertice.getArestas().get(i).getWeight() != 0) {
						aux+="\n"+"Link: "+vertice.getArestas().get(i).getOrigem()+" -> "+vertice.getArestas().get(i).getDestino()+" Weight: "+vertice.getArestas().get(i).getWeight();
					} else {
						aux+="\n"+"Link: "+vertice.getArestas().get(i).getOrigem()+" -> "+vertice.getArestas().get(i).getDestino();						
					}
				}
			}
		}
		
		return aux;
	}

}
