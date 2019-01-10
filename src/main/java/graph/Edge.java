package graph;

public class Edge <V,E>{
	
	private String id;
	private final Vertex<V,E> vertex;
	private E value;
	
	public Edge(String id, E value, Vertex<V,E> vertex) {
		this.id = id;
		this.value = value;
		this.vertex = vertex;
	}
	
	public Edge(String id, Vertex<V,E> vertex) {
		this.id = id;
		this.vertex = vertex;
	}
	
	public Edge(Vertex<V,E> vertex) {
		this.vertex = vertex;
	}
	
	public Vertex<V,E> getVertex() {
		return vertex;
	}
	
	public E getValue() {
		return value;
	}
	
	public void setValue(E value) {
		this.value = value;
	}
	
	public String getId(){
		return id;
	}

}
