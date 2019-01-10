package graph.search;

import java.util.ArrayList;
import java.util.List;

import graph.Graph;

public class Floyd <V,E  extends Number> {
	
	private static String[] vertices = null;
	
	private double[][] matrixD = null;
	private int[][] matrixP = null;
	
	public Floyd(Graph<V, E> g){
            int size = g.getVertices().size();
//            if(!g.isOriented())
//                throw new IllegalArgumentException("Graph must be oriented");

            matrixD = new double[size][size];
            setMatrixP(new int[size][size]);
            vertices = new String[size];

            initializeVertices(g);
            initializeMatrixD(g);
            initializeMatrixP();
	}
	
	private void initializeVertices(Graph<V,E> g){
            for(int i=0; i< g.getVertices().size(); i++){
                    vertices[i] = g.getVertices().get(i).getId();
            }
	}

	@SuppressWarnings("unchecked")
	private void initializeMatrixD(Graph<V,E> g){
            for(int i=0; i< matrixD.length; i++){
                    for(int j=0; j < matrixD.length; j++){
                            if(g.adjacent(vertices[i], vertices[j])){
                                    matrixD[i][j] = g.getVertexById(vertices[i]).getEdgeByVertexId(vertices[j]).doubleValue();
                            }else if( i == j){
                                    matrixD[i][j] = 0.0;
                            } else {
                                    matrixD[i][j] = 999.0;
                            }
                            System.out.print(matrixD[i][j] + " ");
                    }
                    System.out.println();
            }
	}
	
	private void initializeMatrixP(){
            for(int i=0; i< matrixP.length; i++){
                for(int j=0; j < matrixP.length; j++){
                    if( i != j){
                            matrixP[i][j] = i;
                    }else{
                            matrixP[i][j] = 0;
                    }
                }
            }
	}
	
	public void algorithm(String startId, String stopId){
            for(int k=0; k < vertices.length; k++){			
                for(int i=0; i< matrixD.length; i++){
                    for(int j=0; j < matrixD.length; j++){
                        if(i != k && j != k){
                            double valor = matrixD[k][j]+matrixD[i][k];
                            if(matrixD[i][j] > valor){
                                    matrixD[i][j] = valor;
                                    matrixP[i][j] = k;
                            }
                        }
                    }
                }
            }
            getBest(startId, stopId);
	}
	
	private void getBest(String startId, String stopId){
		System.out.println();
        for(int i=0; i< matrixP.length; i++){
            for(int j=0; j < matrixP.length; j++){
            	System.out.print(matrixP[i][j] + " ");
            }
            System.out.println();
        }
            int start = -1;
            int stop = -1;
            int count = 0;
            for(String v : vertices){
                if(v.equals(startId))
                        start = count;
                else if(v.equals(stopId))
                        stop = count;
                count++;
            }
            System.out.println("Distance: " + matrixD[start][stop]);

            List<String> predecessors = new ArrayList<>();
            while(stop != start){
            	predecessors.add(0, vertices[stop]);
                stop = matrixP[start][stop];
            }
            if(predecessors.size() > 0)
            	predecessors.add(0, vertices[start]);
            System.out.println("Best Way: " + predecessors);
	}

	public double[][] getMatrixD() {
            return matrixD;
	}

	public void setMatrixD(double[][] matrixD) {
            this.matrixD = matrixD;
	}

	public int[][] getMatrixP() {
            return matrixP;
	}

	public void setMatrixP(int[][] matrixP) {
            this.matrixP = matrixP;
    }
	
//	public static void main(String[] args){
//		Graph<String,Integer> g = new Graph<>(true);
//		g.addVertex("1", "1");
//		g.addVertex("2", "1");
//		g.addVertex("3", "1");
//		g.addVertex("4", "1");
//		g.addVertex("5", "1");
//		
//		g.addEdge("12", 2, "1", "2");
//		g.addEdge("21", 2, "2", "1");
//		
//		g.addEdge("13", 4, "1", "3");
//		g.addEdge("31", 4, "3", "1");
//		
//		g.addEdge("23", 3, "2", "3");
//		g.addEdge("32", 3, "3", "2");
//		
//		g.addEdge("24", 4, "2", "4");
//		g.addEdge("42", 4, "4", "2");
//		
//		g.addEdge("34", 5, "3", "4");
//		g.addEdge("43", 5, "4", "3");
//		
//		g.addEdge("35", 3, "3", "5");
//		g.addEdge("53", 3, "5", "3");
//		
//		g.addEdge("45", 6, "4", "5");
//		g.addEdge("54", 6, "5", "4");
//		
//		Floyd floyd = new Floyd(g);
//		floyd.algorithm("1", "5");
//		
//	}

}
