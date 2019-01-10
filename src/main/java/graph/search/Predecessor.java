package graph.search;

import graph.Vertex;

public class Predecessor<T,N> {
	
	private Vertex<T,N> vertex;
	private Vertex<T,N> lastVertex;
	private N distance;
	
	public Predecessor(N distance,Vertex<T,N> vertex){
		this.distance = distance;
		this.vertex = vertex;
	}
	
	public Predecessor(N distance,Vertex<T,N> vertex, Vertex<T,N> lastVertex){
		this.distance = distance;
		this.vertex = vertex;
		this.lastVertex = lastVertex;
	}
	
	public N getDistance() {
		return distance;
	}
	
	public void setDistance(N distance) {
		this.distance = distance;
	}
	
	public Vertex<T,N> getLastVertex(){
		return lastVertex;
	}
	
	public void setLastVertex(Vertex<T,N> lastVertex) {
		this.lastVertex = lastVertex;
	}
	
	public Vertex<T,N> getVertex(){
		return vertex;
	}

}