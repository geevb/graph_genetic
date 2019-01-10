package graph.search;

import java.util.LinkedList;
import java.util.List;

import graph.Edge;
import graph.Vertex;

public class BreadthFirstSearch <V, E extends Number> extends Search <V, E>{
	
	
	public BreadthFirstSearch(Vertex<V,E> initialVertex, List<Vertex<V,E>> finalVertices) {
		super(initialVertex, finalVertices);
	}
	
	public void search() {
		Vertex<V,E> v = null;
		while(getOpenedVertices().size() > 0) {
			v = getOpenedVertices().get(0).clone();
			List<Edge<V,E>> adjacents = v.getEdges();
			getClosedVertices().add(v);
			getOpenedVertices().remove(0);
			
			for(Edge<V,E> adjacent : adjacents) {
				getOpenedVertices().add(adjacent.getVertex());
				calculateMinDistance(v, adjacent);
			}
		}
		getBestFinal();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void calculateMinDistance(Vertex<V,E> currentVertex, Edge<V,E> adjacent) {
		E distance = adjacent.getValue();
		
		Predecessor <V,E> predecessor = getPredecessor((V) adjacent.getVertex().getId());
		Predecessor <V,E> lastPredecessor = getPredecessor((V) currentVertex.getId());
		
		if(lastPredecessor != null) {
			distance = (E) new Double(lastPredecessor.getDistance().doubleValue() + distance.doubleValue());
		}
		
		if( predecessor == null) {
			getPredecessors().add(new Predecessor<V,E>(distance, adjacent.getVertex(), currentVertex));
		} else if (predecessor.getDistance().doubleValue() > distance.doubleValue()) {
			predecessor.setDistance(distance);
			predecessor.setLastVertex(currentVertex);
		}

	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected final void getBestFinal() {
		Predecessor<V,E> bestFinal = null;
		
		for(Vertex<V,E> finalVertex : getFinalVertices()) {
			Predecessor<V,E> currentP = getPredecessor((V)finalVertex.getId());
			if( bestFinal == null || 
					currentP.getDistance().doubleValue() < bestFinal.getDistance().doubleValue()) {
				bestFinal = currentP;
			}
		}
		List<Predecessor<V,E>> linkedPredecessors = new LinkedList<>();
		getBestWay((V)bestFinal.getVertex().getId(), linkedPredecessors);
		
		System.err.println("Best Final: Vertex -> " + bestFinal.getVertex().getId() +
				" - Distance ->" + bestFinal.getDistance().doubleValue() + "\n");
		System.err.print("Best Way: " );
		for(Predecessor<V,E> predecessor : linkedPredecessors) {
			System.err.print( "g(" +predecessor.getVertex().getId() + ") ");
		}
		System.err.println("\n");
	}
	
	@SuppressWarnings("unchecked")
	private void getBestWay(V id, List<Predecessor<V,E>> linkedPredecessors) {
		Predecessor<V,E> current = getPredecessor(id);
		if( current == null) {
			return;
		}
		linkedPredecessors.add(0, current);
		getBestWay((V)current.getLastVertex().getId(), linkedPredecessors);
	}

}
