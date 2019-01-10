package graph.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import graph.Edge;
import graph.Vertex;

public abstract class Search <V, E>{
	
	private final Vertex<V,E> initialVertex;
	private final List<Vertex<V,E>> finalVertices;
	
	private List<Vertex<V,E>> openedVertices;
	private List<Vertex<V,E>> closedVertices;
	
	private List<Predecessor<V,E>> predecessors;
	
	public Search(Vertex<V,E> initialVertex, List<Vertex<V,E>> finalVertices) {
		
		this.initialVertex  = initialVertex;
		this.finalVertices = finalVertices;
		this.openedVertices = new LinkedList<>();
		this.closedVertices = new ArrayList<>();
		this.predecessors = new ArrayList<>();
		
		this.openedVertices.add(initialVertex);
	}
	
	protected abstract void getBestFinal();
	protected abstract void  calculateMinDistance(Vertex<V,E> currentVertex, Edge<V,E> adjacent);
	
	public Vertex<V,E> getInitialVertex() {
		return initialVertex;
	}
	
	public List<Vertex<V,E>> getFinalVertices(){
		return finalVertices;
	}
	
	public List<Vertex<V,E>> getOpenedVertices(){
		return openedVertices;
	}
	
	public void setOpenedVertices(List<Vertex<V,E>> openedVertices) {
		this.openedVertices = openedVertices;
	}
	
	public List<Vertex<V,E>> getClosedVertices(){
		return closedVertices;
	}
	
	public List<Predecessor<V,E>> getPredecessors(){
		return predecessors;
	}
	
	public Predecessor<V,E> getPredecessor(V id){
		for( Predecessor<V,E> p : predecessors) {
			if(p.getVertex().getId().equals(id)) {
				return p;
			}
		}
		return null;
	}
	
	public Predecessor<V,E> getPredecessor(V id, V lastid){
		for( Predecessor<V,E> p : predecessors) {
			if(p.getVertex().getId().equals(id) && p.getLastVertex().getId().equals(lastid)) {
				return p;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void add(Vertex<V,E> v, List<Vertex<V,E>> list) {
		if(!contains((V)v.getId(), list)){
			list.add(v);
		}
	}
	
	public boolean contains(V id, List<Vertex<V,E>> list) {
		return list.stream().filter( v -> v.getId().equals(id)).findFirst().isPresent();
	}
	
	public int getIndex(V id, List<Vertex<V,E>> list) {
		return IntStream.range(0, list.size())
			     .filter(v -> id.equals(list.get(v).getId()))
			     .findFirst().orElse(-1);
	}

}
