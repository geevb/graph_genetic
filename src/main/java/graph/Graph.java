package graph;

import java.util.ArrayList;
import java.util.List;

import graph.search.AStar;
import graph.search.BreadthFirstSearch;
import graph.search.Coloring;
import graph.search.DepthFirstSearch;
import graph.search.Djikstra;
import graph.search.Floyd;
import graph.search.Prim;

public class Graph <V,E>{
	
	private List<Vertex<V,E>> vertices;
	private final boolean oriented;
	
	public Graph(boolean oriented){
		vertices = new ArrayList<>();
		this.oriented = oriented;
	}
	
	public List<Vertex<V,E>> getVertices(){
		return vertices;
	}
	
	public List<Edge<V,E>> getEdges() {
		List<Vertex<V,E>> vertexes = this.getVertices();
		List<Edge<V, E>> edges = new ArrayList<Edge<V,E>>();
		vertexes.forEach(e -> {
			edges.addAll(e.getEdges());
		});
		
		return edges;
	}
	
	public void setVertices(List<Vertex<V,E>> vertices){
		this.vertices = vertices; 
	}
	
	public void addVertex(String id){
		if(contains(id)){
			throw new IllegalArgumentException("Vertex ("+ id +") already exists");
		}
		Vertex<V,E> vertex = new Vertex<V,E>(id);
		vertices.add(vertex);
	}
	
	public void addVertex(String id, V value){
		if(contains(id)){
			throw new IllegalArgumentException("Vertex ("+ id +") already exists");
		}
		Vertex<V,E> vertex = new Vertex<V,E>(id);
		vertex.setValue(value);
		vertices.add(vertex);
	}
	
	public Boolean removeVertex(String id){
		if(!contains(id)){
                    return false;
		}
		vertices.removeIf(v -> v.getId().equals(id));
		vertices.forEach( v -> {
			v.getEdges().removeIf(e -> e.getVertex().getId().equals(id));
		});
                return true;
	}
	
	public void addEdge(String id, E value, String id1, String id2){
		if(contains(id1) && contains(id2)){
			Vertex<V,E> v1 = getVertex(id1);
			Vertex<V,E> v2 = getVertex(id2);
			v1.getEdges().add(new Edge<V,E>(id, value, v2));
			v1.getNeighbors().add(v2);
			if(!oriented) {
				v2.getEdges().add(new Edge<V,E>(id, value, v1));
			}
		}
	}
	
	public Boolean removeEdge(String id1, String id2){
		if(contains(id1) && contains(id2)){
			Vertex<V,E> v1 = getVertex(id1);
			v1.getEdges().removeIf(e -> e.getVertex().getId().equals(id2));
			if(!oriented) {
				Vertex<V,E> v2 = getVertex(id2);
				v2.getEdges().removeIf(e -> e.getVertex().getId().equals(id1));
			}
                    return true;
		}
                
                return false;
	}
	
	public boolean adjacent(String id1, String id2){
		if(contains(id1) && contains(id2)){
			Vertex<V,E> v1 = getVertex(id1);
			return v1.getEdges().stream().filter( e -> e.getVertex().getId().equals(id2)).findFirst().isPresent();
		} else {
			return false;
		}
	}
	
	public Boolean isPlanar(boolean cyclesGreaterThanThree) {
		int verticesCount = vertices.size();
                Boolean isPlanar = true;
		if(verticesCount > 3) {
			int edgesCount = countEdges();
			if(!(edgesCount <= (((3 * verticesCount) - 6)) && cyclesGreaterThanThree)) {
				System.err.println("Answer: Non Planar -> theorem on the number of vertices and edges doesn't occur ");
                                isPlanar = false;
			} else if(!((edgesCount <= ((2 * verticesCount) - 4)) && !cyclesGreaterThanThree)){
				System.err.println("Answer: Non Planar -> theorem on the number of vertices and edges doesn't occur ");
                                isPlanar = false;
			} 
		}                
        return isPlanar;
	}
	
	private int countEdges() {
		int edges = 0;
		List<String> visited = new ArrayList<>();
		
		for(Vertex<V,E> v : vertices) {
			for(Edge<V,E> e : v.getEdges()) {
				if(!oriented && !contains(e.getVertex().getId(), visited)) {
					visited.add(e.getVertex().getId());
					edges++;
				} else if(oriented){
					edges++;
				}
			}
		}
		return edges;
	}
	
	public void showGraph(){
		StringBuilder sb = new StringBuilder();
		vertices.forEach( v -> {
			sb.append(v.getId() + "("+ v.getValue() + ") -> ");
			v.getEdges().forEach( e -> {
				sb.append("(" + e.getId() + "-" + e.getValue() +") " + e.getVertex().getId() + " - ");
			});
			sb.append("\n");
		});
		System.err.println(sb.toString());
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void bfs(String id, List<String> finalIds) {
		Vertex<V,E> initialVertex = null;
		List<Vertex<V,E>> finalVertices = new ArrayList<>();
		if(contains(id)) {
			initialVertex = getVertex(id);
		} else {
			System.err.println(" ** initial id(" + id + ") não encontrado");
		}
		for(String finalId : finalIds) {
			if(contains(finalId)) {
				finalVertices.add(getVertex(finalId));
			} else {
				System.err.println(" ** final id(" + finalId + ") não encontrado");
			}
		}
		new BreadthFirstSearch(initialVertex, finalVertices).search();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void dfs(String id, List<String> finalIds) {
		Vertex<V,E> initialVertex = null;
		List<Vertex<V,E>> finalVertices = new ArrayList<>();
		if(contains(id)) {
			initialVertex = getVertex(id);
		} else {
			System.err.println(" ** initial id(" + id + ") não encontrado");
		}
		for(String finalId : finalIds) {
			if(contains(finalId)) {
				finalVertices.add(getVertex(finalId));
			} else {
				System.err.println(" ** final id(" + finalId + ") não encontrado");
			}
		}
		new DepthFirstSearch(initialVertex, finalVertices).search();
	}
	
	public void prim() {
		new Prim(this).search();
	}
	
	public void floyd(String startId, String stopId) {
		Floyd floyd = new Floyd(this);
		floyd.algorithm(startId, stopId);
	}
	
	public void djikstra(String startId, String stopId) {
		Djikstra djikstra = new Djikstra(this);
		djikstra.algorithm(startId, stopId);
	}
        
        public void coloring(int numVertices, int[][] matrix) {
            Coloring color = new Coloring(numVertices, matrix);
            color.graphColor();
        }
        
        public void aStar(String startId, String stopId){
            AStar aStar = new AStar(this);
            aStar.algorithm(startId, stopId);
        }
	
	public boolean contains(String id){
		return vertices.stream().filter( v -> v.getId().equals(id)).findFirst().isPresent();
	}
	
	private Vertex<V,E> getVertex(String id){
		return vertices.stream().filter( v -> v.getId().equals(id)).findFirst().get();
	}
	
	public boolean contains(String id, List<String> list) {
		return list.stream().filter( s -> s.equals(id)).findFirst().isPresent();
	}
	
	public boolean isOriented() {
		return oriented;
	}

	public Vertex<V,E> getVertexById(String id){
            for(Vertex<V,E> v : vertices){
                if(v.getId().equals(id)){
                        return v; 
                }
            }
            return null;
	}
}
