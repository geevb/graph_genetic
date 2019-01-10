package graph.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.Graph;

public class Prim <V,E extends Number> {
	
	private static final String SPLIT = "-";
	private Double lowest;
	private String lowestV1;
	private String lowestV2;
	
	private Graph<V,E> graph;
	private List<V> visited;
	private Map<String,E> matrix; 
	
	public Prim(Graph<V,E> graph) {
		this.graph = graph;
		visited = new ArrayList<>();
		matrix = new HashMap<>();
	}
	
	public void search() {
		setupMatrix();
		getValues();
	}
	
	private void setupMatrix() {
		graph.getVertices().forEach(v -> {
			v.getEdges().forEach(e -> {
				add(v.getId(), e.getVertex().getId(), e.getValue());
			});			
		});	
	}
	
	private void getValues() {
		int countEdges = 0;
		List<String> visited = new ArrayList<>();
		List<Double> values = new ArrayList<>();
		List<String> edges = new ArrayList<>();
		while(visited.size() < graph.getVertices().size()) {
			lowest = Double.MAX_VALUE;
			matrix.forEach( (k,v) -> {
				String keys[] = k.split(SPLIT);
				String v1 = keys[0];
				String v2 = keys[1];
				
				if(lowest > v.doubleValue()) {
					if(visited.size() == 0 && !contains(v1, visited) && !contains(v2,visited)) {
						lowest = v.doubleValue();
						lowestV1 = v1;
						lowestV2 = v2;
					} else if(visited.size() > 0 && !contains(v1, visited) && contains(v2,visited)) {
						lowest = v.doubleValue();
						lowestV1 = v1;
						lowestV2 = v2;
					} else if(visited.size() > 0 && contains(v1, visited) && !contains(v2,visited)) {
						lowest = v.doubleValue();
						lowestV1 = v1;
						lowestV2 = v2;
					}					
				}
			});
			values.add(lowest);
			if(!contains(lowestV1, visited)) {
				visited.add(lowestV1);
			}
			if(!contains(lowestV2, visited)) {
				visited.add(lowestV2);
			}
			edges.add(lowestV1 + "<->" + lowestV2); 
		}
		Double acumulate = new Double(0.0);
		for(Double value : values) {
			acumulate += value;
			countEdges++;
		}
                
		System.err.println("AGM Prim: " + acumulate);
		System.err.println("Total Vertices: " +  visited.size());
		System.err.println("Total Edges: " + countEdges);
		
		StringBuilder edgesBuilder = new StringBuilder();
		edgesBuilder.append("Involved Edges : {");
                edges.forEach((edge) -> {
                    edgesBuilder.append(edge + " ");
                });
		edgesBuilder.append("}");
		System.err.println(edgesBuilder.toString());
	}
	
	public void add(String v1, String v2, E value) {
		if(!matrix.containsKey(v1+SPLIT+v2) && !matrix.containsKey(v2+SPLIT+v1)) {
			String key = v1 +SPLIT+ v2;
			matrix.put(key, value);
		}
	}
	
	public boolean contains(String id, List<String> list) {
		return list.stream().filter( v -> v.equals(id)).findFirst().isPresent();
	}
	
	public List<V> getVisited() {
		return visited;
	}

	public void setVisited(List<V> visited) {
		this.visited = visited;
	}

	public Map<String,E> getMatrix() {
		return matrix;
	}

	public void setMatrix(Map<String,E> matrix) {
		this.matrix = matrix;
	}

}
