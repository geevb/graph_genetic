package graph.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import console.Logger;
import graph.Graph;
import graph.Vertex;
import javafx.util.Pair;

public class GeneticAlgorithm <V  extends Number, E  extends Number> implements Algorithm {

        @Override
        public void execute() {}

	public enum StopMode {
            GENERATIONS_MAX, BEST_VALUE, CONVERGENCE
	}
	
	private Logger logger = new Logger();
	
	private static int currentGeneration;
	private int numberOfGenerations;
	
	private final int populationSize;
	private StopMode stopMode;
	private double crossingRate;
	private double mutationRate;
        
	private double bestValue;
	private String bestPath;
	
	private Graph<V,E> graph;
	private List<String[]> population;
	
	public GeneticAlgorithm(Graph<V,E> graph, int populationSize, int numberOfGenerations, double crossingRate, double mutationRate, StopMode stopMode){
		this.populationSize = populationSize;
		this.numberOfGenerations = numberOfGenerations;
		this.crossingRate = crossingRate;
		this.mutationRate = mutationRate;
		this.stopMode = stopMode;
		
		this.setGraph(graph);
		population = new ArrayList<>();
	}
	
	public double run(){
		createInitialPopulation();
		currentGeneration = 0;
                
		do {
			naturalSelection();
            crossover(2,5);
            mutation(2);
            bestValue = result();
		} while(checkStop(++currentGeneration));
		
		Logger.close();
		return bestValue;
	}
	
	private boolean checkStop(int currentGeneration){
		switch(stopMode){
			case GENERATIONS_MAX: return currentGeneration > numberOfGenerations ? false : true;
			//case BEST_VALUE: return bestValue.doubleValue() < 
			//case CONVERGENCE: break;
		}
		return true;
	}
	
	private double result(){
		Logger.log("*******GERACAO NUMERO (" + currentGeneration + ")*******\n");
		
		String[] currentWay, bestWay = null;
		double bestWayValue = 999999999999.0;
		double currentWayValue = 0.0;
		
		for(int i = 0; i < population.size(); i++){
            currentWay = population.get(i);

            currentWayValue = calculateDistance(currentWay);
            if(currentWayValue < bestWayValue){
                    bestWayValue = currentWayValue;
                    bestWay = currentWay;
            }
            Logger.log( Arrays.toString(currentWay) + " " + currentWayValue + "\n");
		}
		System.out.println("Geracao numero (" + currentGeneration + "): " + Arrays.toString(bestWay) + " "+bestWayValue);
		Logger.log("Melhor da geracaoo (" + currentGeneration + "): " + Arrays.toString(bestWay) + " "+ bestWayValue + "\n");
		return bestWayValue; 
	}
	
	private double calculateDistance(String[] chromosome){
		double currentWayValue = 0.0;
		String gene1, gene2 = "";
		for(int j = 0; j <= chromosome.length-2; j++){
			gene1 = chromosome[j];
			gene2 = chromosome[j+1];
			
			if(graph.adjacent(gene1, gene2)){
				currentWayValue += graph.getVertexById(gene1).getEdgeByVertexId(gene2).doubleValue();
			} else {
				currentWayValue += 999.0;
			}
		}
		
		gene1 = chromosome[0];
		gene2 = chromosome[chromosome.length-1];
		if(graph.adjacent(gene1, gene2)){
			currentWayValue += graph.getVertexById(gene1).getEdgeByVertexId(gene2).doubleValue();
		} else {
			currentWayValue += 999.0;
		}
		return currentWayValue;
	}
	
	private void createInitialPopulation(){
		String[] vertices = new String[graph.getVertices().size()];
		int index = 0;
		for(Vertex<V,E> v : graph.getVertices()){
			vertices[index] = v.getId();
			index++;
		}
		
		List<String> verticesSorted = Arrays.asList(vertices.clone());
		String[] verticesSortedArr = null;
		for(int i = 0; i < populationSize; i++){
			do {
				Collections.shuffle(verticesSorted);
				verticesSortedArr = new String[verticesSorted.size()];
				verticesSorted.toArray(verticesSortedArr);
			} while(!isNewChromosome(verticesSortedArr));
			population.add(verticesSortedArr);
		}
	}
	
	private void naturalSelection(){
		List<String[]> newPopulation = new ArrayList<>();
		final double K = 0.75;
		
		for(int i = 0; i < populationSize*2; i++){
			
			String[] chromosome1 = population.get( ThreadLocalRandom.current().nextInt(0, population.size()));
			String[] chromosome2 = population.get( ThreadLocalRandom.current().nextInt(0, population.size()));
			double result1 = calculateDistance(chromosome1);
			double result2 = calculateDistance(chromosome2);
			
			int r = ThreadLocalRandom.current().nextInt(0, 1);
			
			if(r < K){ // Torneio
				newPopulation.add(result1 > result2 ? chromosome1: chromosome2);
			} else {
				newPopulation.add(result1 > result2 ? chromosome2: chromosome1);
			}
			
		}
	}

	private boolean isNewChromosome(String[] chromosome){
		for(int i = 0; i < population.size(); i++){
			if( population.get(i).equals(chromosome)){
				return false;
			}
		}
		return true;
	}
	
	private void crossover(int start, int stop){
		int crossingSize = (int) ((int) population.size() * crossingRate); // qtd de individuos que sofreram cruzamento
        int diff = (population.size() - crossingSize);
        diff = diff > 0 ? diff : 1;
		int minRange = ThreadLocalRandom.current().nextInt(0, diff); //range de cruzamento
		int maxRange = minRange+crossingSize;
		
		List<String[]> newPopulation = new ArrayList<>();
		
		String[] father1;
		String[] father2;
		
		String[] child1;
		String[] child2;
		
		for(int i = 0; i <= (population.size() -2); i+=2){
			if( i >= minRange  && i <= (maxRange)){ //sofre cruzamento
				
				father1 = population.get(i);
				father2 = population.get(i+1);
				
				child1= createChild(father1, father2, start, stop);
				child2= createChild(father2, father1, start, stop);
				
				newPopulation.add(child1);
				newPopulation.add(child2);
				
			} else { //nao sofre cruzamento
				newPopulation.add(population.get(i));
				newPopulation.add(population.get(i+1));
			}
		}
		population = newPopulation;
	}
	
	private String[] createChild(String[] fatherX, String[] fatherY, int start, int stop){
		String[] child = new String[fatherX.length];
		List<String> visitedGenes = new ArrayList<>();
		String gene;
                
		for(int j = 0 ; j < fatherX.length; j++){
			
			gene = ((j <= start || j >= stop) ? fatherY[j] : fatherX[j]);
			
			if(visitedGenes.contains(gene)){
				gene = getFirstGeneNotVisitedFather(fatherX, visitedGenes);
			}
			child[j] = gene;
			visitedGenes.add(gene);
		}
		return child;
	}
	
	private String getFirstGeneNotVisitedFather(String[] father, List<String> visitedGenes){
            String gene;
                
            for (String father1 : father) {
                gene = father1;
                if(!visitedGenes.contains(gene)){
                    return gene;
                }
            }
            
            return null;
	}
	
	private void mutation(int countGenesMutation){
		List<Integer> visitedMutation = new ArrayList<>();
		if(countGenesMutation % 2 != 0){
			throw new IllegalArgumentException("Mutacao deve ocorrer em pares, ou seja parametro countGenesMutation deve ser par");
		}
		Integer mutationIndex = null;
		
		List<Integer> indexMutation = new ArrayList<>();
		for(int i = 0; i < countGenesMutation; i++){
			do {
				mutationIndex = ThreadLocalRandom.current().nextInt(0, 6);
			} while(visitedMutation.contains(mutationIndex));
			indexMutation.add(mutationIndex);
		}
		
		int mutationSize = (int) ((int) population.size() * mutationRate); // qtd de individuos que sofreram mutacao
        int diff = (population.size() - mutationSize);
        diff = diff > 0 ? diff : 1;
		int minRange = ThreadLocalRandom.current().nextInt(diff); //range de mutacao
		int maxRange = minRange + mutationSize;
		
		List<String[]> newPopulation = new ArrayList<>();
		
		String[] child, newChild;
		String auxGene1, auxGene2;
		
		for(int i = 0; i < (population.size()); i++){
			if( i >= minRange  && i <= (maxRange)){ //sofre cruzamento
				
				child = population.get(i);
				newChild = child.clone();
				for(int j = 0; j <= (indexMutation.size()-2); j+=2){
					auxGene1 = child[indexMutation.get(j)];
					auxGene2 = child[indexMutation.get(j+1)];
					
					newChild[indexMutation.get(j)] = auxGene2;
					newChild[indexMutation.get(j+1)] = auxGene1;
					
					newPopulation.add(newChild);
					
				}
				
			} else { //nao sofre cruzamento
				newPopulation.add(population.get(i));
			}
		}
		population = newPopulation;
	}

	public StopMode getStopMode() {
		return stopMode;
	}

	public void setStopMode(StopMode stopMode) {
		this.stopMode = stopMode;
	}

	public int getNumberOfGenerations() {
		return numberOfGenerations;
	}

	public void setNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
	}

	public double getCrossingRate() {
		return crossingRate;
	}

	public void setCrossingRate(double crossingRate) {
		this.crossingRate = crossingRate;
	}

	public double getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(double mutationRate) {
		this.mutationRate = mutationRate;
	}

	public List<String[]> getPopulation() {
		return population;
	}

	public void setPopulation(List<String[]> population) {
		this.population = population;
	}

	public Graph<V,E> getGraph() {
		return graph;
	}

	public void setGraph(Graph<V,E> graph) {
		this.graph = graph;
	}
}
