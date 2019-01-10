package console;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import graph.Graph;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

public class Draw<V,E> {
	
	private MutableGraph g = null;
	
	public Draw() {
		
	}
	
	public void drawGraph(Graph<V,E> graph, String title) throws IOException {
		List<String> verticesVisited = new ArrayList<>();
		g = null;
		g = mutGraph(title).setDirected(graph.isOriented());
		
		graph.getVertices().forEach( vertex -> {
			verticesVisited.add(vertex.getId());
			MutableNode node = mutNode(vertex.getId());
			
			vertex.getEdges().forEach( edge -> {
				if(graph.isOriented()) {
					node.addLink(mutNode(edge.getVertex().getId()));
				} else if(!contains(verticesVisited, edge.getVertex().getId())) {
					node.addLink(mutNode(edge.getVertex().getId()));
				}
			});
			g.add(node);
		});
                
		Graphviz.fromGraph(g).width(400).height(600).render(Format.PNG).toFile(new File(title));
		Graphviz.releaseEngine();
	}
	
	private boolean contains(List<String> visited, String id){
		return visited.stream().filter( v -> v.equals(id)).findFirst().isPresent();
	}

}
