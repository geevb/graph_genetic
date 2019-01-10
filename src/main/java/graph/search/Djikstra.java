package graph.search;

import java.util.ArrayList;
import java.util.List;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class Djikstra<V,E  extends Number>{
	
	private final Graph<V,E> graph;
	
	private List<Predecessor<V,E>> predecessors;
	private List<String> openedVertices; 
	
	public Djikstra(Graph<V,E> g){
		this.graph = g;
		predecessors = new ArrayList<>();
		openedVertices = new ArrayList<>();

		initializeVertices(g);
	}
	
	public void algorithm(String startId, String stopId){
		initPredecessors(startId);
		while(openedVertices.size() > 0){
			Predecessor<V,E> predecessor = getBestPredecessor();
			updatePredecessors(predecessor);
			showPredecessors();
		}
		List<Predecessor<V,E>> predecessorsResult = new ArrayList<>();
		getBest(startId, stopId, predecessorsResult);
		showResult(predecessorsResult);
	}
	
	private void showPredecessors() {
		for(Predecessor<V,E> p : predecessors){
			System.out.print(p.getVertex().getId()+"|");
			System.out.print((p.getDistance() != null ? +p.getDistance().doubleValue() : "-")+"|");
			System.out.print((p.getLastVertex() != null ? p.getLastVertex().getId() : "-")+"|");
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	private void showResult(List<Predecessor<V,E>> predecessorsResult){
		int count = 1;
		for(Predecessor<V,E> p : predecessorsResult){
			if(count != predecessorsResult.size())
				System.out.print(p.getVertex().getId() + " -> ");
			else
				System.out.print(p.getVertex().getId());
			count++;
		}
		System.out.println("\nDistance: " + predecessorsResult.get(predecessorsResult.size()-1).getDistance().doubleValue());
	}
	
	private void getBest(String startId, String stopId, List<Predecessor<V,E>> predecessorsResult){
		if(stopId.equals(startId)){
			Predecessor<V,E> predecessor = getPredecessor(stopId);
			predecessorsResult.add(0, new Predecessor<V, E>(predecessor.getDistance(), predecessor.getVertex()));
			return;
		}
		Predecessor<V,E> predecessor = getPredecessor(stopId);
		predecessorsResult.add(0, new Predecessor<V, E>(predecessor.getDistance(), predecessor.getVertex()));
		stopId = predecessor.getLastVertex().getId();
		getBest(startId, stopId, predecessorsResult);

	}
	
	private Predecessor<V,E> getPredecessor(String id){
		return predecessors.stream().filter( p -> p.getVertex().getId().equals(id)).findFirst().get();
	}
	
	@SuppressWarnings("unchecked")
	private void updatePredecessors(Predecessor<V,E> predecessor){
		List<Edge<V,E>> edges = predecessor.getVertex().getEdges();
		
		System.err.println("update:" + predecessor.getVertex().getId() + "-"+predecessor.getDistance().doubleValue());
		for(Edge<V,E> e : edges){
			for(Predecessor<V,E> p :predecessors){
				if( e.getVertex().getId().equals(p.getVertex().getId())){
					Double total = e.getValue().doubleValue() + predecessor.getDistance().doubleValue();
					if(total < p.getDistance().doubleValue()){
						p.setDistance((E)total);
						p.setLastVertex(predecessor.getVertex());

					}
				}
			}
		}
	}
	
	private Predecessor<V,E> getBestPredecessor(){
		Double bestValue = 999.0;
		Predecessor<V,E> bestPredecessor = null;
		for(Predecessor<V,E> p :predecessors){
			if(p.getDistance().doubleValue() < bestValue && contains(p.getVertex().getId(), openedVertices)){
				bestPredecessor = p;
				bestValue = p.getDistance().doubleValue();
			}
		}
		openedVertices.remove(bestPredecessor.getVertex().getId());
		return bestPredecessor;
	}
	
	public boolean contains(String id, List<String> list) {
		return list.stream().filter( s -> s.equals(id)).findFirst().isPresent();
	}
	
	@SuppressWarnings("unchecked")
	private void initPredecessors(String startId){
		List<Vertex<V,E>> vertices = graph.getVertices();
		for(Vertex<V,E> v : vertices){
			if(!v.getId().equals(startId)){
				predecessors.add(new Predecessor<V,E>((E)new Double(999.0), v.clone()));
			} else {
				predecessors.add(new Predecessor<V,E>((E)new Double(0.0), v.clone()));
			}
		}
		showPredecessors();
	}
	
	private void initializeVertices(Graph<V,E> g){
		for(int i=0; i< g.getVertices().size(); i++){
			openedVertices.add(g.getVertices().get(i).getId());
		}
	}

	public List<Predecessor<V,E>> getPredecessors() {
		return predecessors;
	}

	public void setPredecessors(List<Predecessor<V,E>> predecessors) {
		this.predecessors = predecessors;
	}

	public List<String> getOpenedVertices() {
		return openedVertices;
	}

	public void setOpenedVertices(List<String> openedVertices) {
		this.openedVertices = openedVertices;
	}
	
//	public static void main(String[] args){
//		Graph<String,Integer> g = new Graph<>(true);
//		g.addVertex("s", "1");
//		g.addVertex("u", "1");
//		g.addVertex("v", "1");
//		g.addVertex("x", "1");
//		g.addVertex("y", "1");
//		
//		g.addEdge("su", 10, "s", "u");
//		g.addEdge("sx", 5, "s", "x");		
//		g.addEdge("ux", 2, "u", "x");
//		g.addEdge("xu", 3, "x", "u");		
//		g.addEdge("uv", 1, "u", "v");		
//		g.addEdge("xv", 9, "x", "v");
//		g.addEdge("xy", 2, "x", "y");	
//		g.addEdge("vy", 6, "v", "y");
//		g.addEdge("yv", 4, "y", "v");
//		g.addEdge("ys", 7, "y", "s");
//		
//		Djikstra djikstra = new Djikstra(g);
//		djikstra.algorithm("s", "v");
//		
//	}


}
