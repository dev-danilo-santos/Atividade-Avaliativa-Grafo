package br.com.gomide.data_structures.graph.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import br.com.gomide.data_structures.graph.model.Aresta;
import br.com.gomide.data_structures.graph.model.DirectedGraph;
import br.com.gomide.data_structures.graph.model.Graph;
import br.com.gomide.data_structures.graph.model.NonDirectedGraph;
import br.com.gomide.data_structures.graph.model.Vertice;

public class GraphService implements IGraphService {

	@Override
	public void addNodes(List<String> nodes, Graph graph) {
		for (String node : nodes) {
			if(graph.getVerticesString().contains(node)) {
				// não adiciona repetido
			} else {
				graph.getVerticesString().add(node);
				Vertice vertice = new Vertice(node);
				graph.getVertices().add(vertice);
			}
		}
	}

	@Override
	public void addNode(String node, Graph graph) {
		if(graph.getVerticesString().contains(node)) {
			// O grafo já contém um nó com este valor
		}
		else {
			graph.getVerticesString().add(node);
			Vertice vertice = new Vertice(node);
			graph.getVertices().add(vertice);
		}
	}

	@Override
	public void connectNode(String firstNode, String secondNode, Graph graph) {
		for (Vertice vertice : graph.getVertices()) {
			if(vertice.getValor().equals(firstNode)) {
				// verificar se já existe uma aresta igual a inserida
				if(vertice.getArestas() != null && vertice.getArestas().size() != 0) {
					for (Aresta aresta : vertice.getArestas()) {
						if(aresta.getOrigem() == firstNode && aresta.getDestino() == secondNode) {
							graph.adicionaMultLink();
						}
					}
				}
				// adiciona a aresta
				vertice.getArestas().add(new Aresta(firstNode, secondNode));
			}
		}
	}
	
	@Override
	public void connectNode(String firstNode, String secondNode, Integer weight, Graph graph) {
		for (Vertice vertice : graph.getVertices()) {
			if(vertice.getValor().equals(firstNode)) {
				// verificar se já existe uma aresta igual a inserida
				if(vertice.getArestas() != null && vertice.getArestas().size() != 0) {
					for (Aresta aresta : vertice.getArestas()) {
						if(aresta.getOrigem() == firstNode && aresta.getDestino() == secondNode) {
							graph.adicionaMultLink();
						}
					}
				}
				// adiciona a aresta
				vertice.getArestas().add(new Aresta(firstNode, secondNode,weight));
			}
		}
	}

	@Override
	public int countLoops(Graph graph) {
		int contador = 0;
		if (graph.getVertices() != null && graph.getVertices().size() != 0) {
		for (Vertice vertice : graph.getVertices()) {
			if(vertice.getArestas() != null && vertice.getArestas().size() != 0) {
				for (Aresta aresta : vertice.getArestas()) {
					if(aresta.getOrigem() == aresta.getDestino()) {
						++contador;
						}	
					}
				}
			}
		}
		return contador;
	}

	@Override
	public int countMultipleLink(Graph graph) {
		
		return graph.getMultipleLinks();
	}

	@Override
	public int nodeDegree(String node, Graph graph) {
		int contador = 0;
		if(verificarListas(graph.getVertices())) {
			for (Vertice vertice : graph.getVertices()) {
				if(verificarListas(vertice.getArestas())) {
					for (Aresta aresta : vertice.getArestas()) {
						if(node.equals(aresta.getOrigem()) || node.equals(aresta.getDestino())) {
						 	++contador;
						}
					}
				}
			}
		}
		return contador;
	}

	@Override
	public boolean isConnected(Graph graph) {
		String origemBusca = graph.getVertices().get(0).getValor();
		String destino = graph.getVertices().get(graph.getVertices().size()-1).getValor();
		ArrayList<String> encontrados = new ArrayList<>();
		int contador = 0;
		
		for(;;) {
			if(encontrados.contains(destino)) return true;
			if(contador ==100) break;
			encontrados = percorrer(origemBusca, graph);
			origemBusca = encontrados.get(0);
			contador++;
		}
		
		
		return false;
	}

	@Override
	public boolean isComplete(Graph graph) {
		ArrayList<String> ElementosGrafo = graph.getVerticesString();
		int quantidadeElementos = graph.getVertices().size();

		for (Vertice vertice : graph.getVertices()) {
			int contador = 0;
			ArrayList<String> encontrados = new ArrayList<>();
			ArrayList<String> resultadoBusca = new ArrayList<>();
			String busca = vertice.getValor();
			
			while(encontrados.size() < quantidadeElementos) {
				if(contador>=500) return false;
				resultadoBusca = percorrer(busca, graph);
				if (verificarListas(resultadoBusca)) {
					busca = resultadoBusca.get(0);					
				}
				for (String string : resultadoBusca) {
					if(!encontrados.contains(string)) {
						encontrados.add(string);
					}
				}
				contador++;
			}	
		}

		return true;
	}

	@Override
	public String showPath(String origin, String destination, DirectedGraph graph) {
		ArrayList<String> encontrados = new ArrayList<>();
		ArrayList<String> pathEcontrado = new ArrayList<>();
		StringBuilder path = new StringBuilder();
		
		int contador = 0;
		String busca = origin;
		
		for(;;) {
			if(encontrados.contains(destination)) {
				break;
			};
			if(contador == 200) break;
			encontrados = percorrer(busca, graph);
			if(verificarListas(encontrados)) {
				pathEcontrado.add(busca+" "+encontrados.get(0));
				busca = encontrados.get(0);
			}
			contador ++;
		}
		for (String string : pathEcontrado) {
			path.append(string);
		}
		if(!path.toString().contains(origin) || !path.toString().contains(destination)) {
			return "INVALID PATH";
		}
		
		return "Start -> "+path+" -> End";
	}
	

	@Override
	public String showPath(String origin, String destination, NonDirectedGraph graph) {
		ArrayList<String> encontrados = new ArrayList<>();
		ArrayList<String> pathEcontrado = new ArrayList<>();
		StringBuilder path = new StringBuilder();
		
		int contador = 0;
		String busca = origin;
		
		for(;;) {
			if(encontrados.contains(destination)) {
				break;
			};
			if(contador == 200) break;
			encontrados = percorrer(busca, graph);
			if(verificarListas(encontrados)) {
				pathEcontrado.add(busca+" "+encontrados.get(0));
				busca = encontrados.get(0);
			}
			contador ++;
		}
		for (String string : pathEcontrado) {
			path.append(string);
		}
		if(!path.toString().contains(origin) || !path.toString().contains(destination)) {
			return "INVALID PATH";
		}
		
		return "Start -> "+path+" -> End";
	}

	@Override
	public String toString(Graph graph) {
		return graph.toString(graph);
	}
	
	@Override
	public String showShortestPath(String origin, String destination, DirectedGraph graph) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String showShortestPath(String origin, String destination, NonDirectedGraph graph) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public  <T> boolean verificarListas( ArrayList<T> lista) {
		if(lista.size() != 0 && lista != null) return true;
		else return false;
	}
	
	public ArrayList<String> percorrer(String origem, Graph graph){
		ArrayList<String> destinos = new ArrayList<>();
		if (verificarListas(graph.getVertices())) {
			for (Vertice vertice : graph.getVertices()) {
				if(vertice.getValor().equals(origem)) {
					
					if(verificarListas(vertice.getArestas())){
						for (Aresta aresta : vertice.getArestas()) {
							destinos.add(aresta.getDestino());
						}
					}
					
				}
			}
		}
	  return destinos;
	}
	
	public String percorrerComPeso(String origem, Graph graph){
		ArrayList<Aresta> arestas = new ArrayList<>();
		ArrayList<Integer> weights = new ArrayList<>();
		String destino = ""; 
		if (verificarListas(graph.getVertices())) {
			for (Vertice vertice : graph.getVertices()) {
				if(vertice.getValor().equals(origem)) {
					
					if(verificarListas(vertice.getArestas())){
						for (Aresta aresta : vertice.getArestas()) {
							arestas.add(aresta);
							weights.add(aresta.getWeight());
						}
						
						
					}
				}
			}
		}
	  return destino;
	}
	
}
