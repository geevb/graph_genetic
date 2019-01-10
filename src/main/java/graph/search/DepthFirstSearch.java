package graph.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import graph.Edge;
import graph.Vertex;

public class DepthFirstSearch<V,E  extends Number> extends Search <V, E>{
	
	private static double k = 8;
	private static double r = 4;
	
	private List<Vertex<V,E>> auxList;
	
	private Predecessor<V,E> bestFinal = null;
	
	public DepthFirstSearch(Vertex<V,E> initialVertex, List<Vertex<V,E>> finalVertices) {
		super(initialVertex, finalVertices);
		this.auxList = new ArrayList<>();
	}
	
	@SuppressWarnings("unchecked")
	public void search() {
		Vertex<V,E> v = null;
		Predecessor<V,E> p = null;
		while(getOpenedVertices().size() > 0) {

			v = getOpenedVertices().get(getOpenedVertices().size()-1).clone(); //lifo
			List<Edge<V,E>> adjacents = v.getEdges();
			
			add(v, getClosedVertices());
			getOpenedVertices().remove(getOpenedVertices().size()-1); //lifo
			
			p = getPredecessor((V)v.getId());
			if(p == null || p.getDistance().doubleValue() < k) {
				if(p != null && contains((V) v.getId(), getFinalVertices())) {
					bestFinal = p;
					break;
				}
				for(Edge<V,E> adjacent : adjacents) {
					getOpenedVertices().add(adjacent.getVertex());
					calculateMinDistance(v, adjacent);
					removeVertexFromAux(v);
				}
			} else if( p.getDistance().doubleValue() > k){
				add(v, getAuxList());
			}
			treatmentOpenedVerticlesEmpty();
		}
		getBestFinal();
	}
	
	/**
	 * if list(a) empty => a = v; k = k + r; v = null
	 */
	private void treatmentOpenedVerticlesEmpty() {
		if(getOpenedVertices().size() == 0) {
			for(Vertex<V,E> vAux : auxList) {
				getOpenedVertices().add(vAux.clone());
			}
			auxList = new ArrayList<>();
			k = k + r;
		}
	}
	
	/**
	 * aux = {aux} - vertex 
	 * @param vertexToRemove
	 */
	private void removeVertexFromAux(Vertex<V,E> vertexToRemove) {
		@SuppressWarnings("unchecked")
		int index = getIndex((V) vertexToRemove.getId(), auxList);
		if(index != -1) {
			auxList.remove(index);
		}
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
		if(bestFinal != null) {
			
			List<Predecessor<V,E>> linkedPredecessors = new LinkedList<>();
			getBestWay((V)bestFinal.getVertex().getId(), linkedPredecessors);
			
			System.err.println("Best Final: Vertex -> " + bestFinal.getVertex().getId() +
					" - Distance ->" + bestFinal.getDistance().doubleValue() + "\n");
			System.err.print("Best Way: " );
			for(Predecessor<V,E> predecessor : linkedPredecessors) {
				System.err.print( "g(" +predecessor.getVertex().getId() + ") ");
			}
			System.err.println("\n");
			
		} else {
			System.err.println("Best Way Not Found");
		}
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
	
	public List<Vertex<V,E>> getAuxList() {
		return auxList;
	}
	
	public double getK() {
		return k;
	}
	
	public double getR() {
		return r;
	}

}
