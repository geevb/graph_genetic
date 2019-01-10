package graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex <V,E> implements Cloneable {
	
	private String id;
	private V value;
	private boolean visited;
	private List<Edge<V,E>> edges;
	
	public Vertex(String id) {
		this.id = id;
		this.edges = new ArrayList<>();
		this.setVisited(false);
	}
	
	public String getId() {
		return id;
	}
	
	public List<Edge<V,E>> getEdges(){
		return edges;
	}
	
	public void setEdges(List<Edge<V,E>> edges){
		this.edges = edges;
	}
	
	public V getValue(){
		return value;
	}
	
	public void setValue(V value){
		this.value = value;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public E getEdgeByVertexId(String id){
		for(Edge<V,E> edge : edges){
			if(edge.getVertex().getId().equals(id)){
				return edge.getValue(); 
			}
		}
		return null;
	}

	public List<Vertex<V,E>> getNeighbors() {
		List<Vertex<V,E>> neighbors = new ArrayList<>();
		edges.forEach( e -> {
			neighbors.add(e.getVertex());
		});
		return neighbors;
	}
	
	@Override
	public Vertex<V,E> clone(){
		Vertex<V,E> vertexClone = new Vertex<>(this.id);
		vertexClone.setEdges(this.edges);
		vertexClone.setVisited(this.visited);
		return vertexClone;
	}

}
