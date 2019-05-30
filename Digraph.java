import java.util.*;
/**
 * Topological Sorting
 * April 2019
 * 
 * This class represents a digraph. It also contains methods to add a vertex, 
 * add successors to a vertex, remove a vertex, and print a graph.
 *
 * @author Jill Eliceiri
 */

public class Digraph {

    //INSTANCE VARIABLES

    //Number of edges
    private int numberOfEdges;
    //Number of vertices
    private int numberOfVertices;
    
    /* A Linked Hash Map (keys, values) is used to represent the adjacency list. 
    The keys are the String vertices and the values are the list of successors.
    */
    private LinkedHashMap<String, ArrayList<String>> adjacencyList;
 
    //CONSTRUCTOR
    public Digraph() {

        adjacencyList = new LinkedHashMap<>();
    }
    //GETTERS AND SETTERS
    public int getNumberOfEdges() {
        return numberOfEdges;
    }
    public int getNumberOfVertices() {
        return numberOfVertices;
    }
    public LinkedHashMap<String, ArrayList<String>> getAdjacencyList() {
        return adjacencyList;
    }
    /**
     * This method checks if a given vertex is present in graph. 
     * If not present, add it and increase number of vertices.
     */
    public void addVertex(String vertex) {
        
        //put there if not in there
        if (!adjacencyList.containsKey(vertex)){
            adjacencyList.put(vertex, new ArrayList<>());
            numberOfVertices++;                
        }
    }
    /**
     * This method adds a given successor to a given vertex and increases the
     * number of edges.
     */
    public void addSuccessor(String v1, String v2) {
        if(adjacencyList.containsKey(v1)){
            adjacencyList.get(v1).add(v2);
            numberOfEdges++;
        }
    }
    /**
     * This method returns the list of successors.
     */
    public List<String> getSuccessors(String v) {
        return adjacencyList.get(v);
    }
    /**
     * This method deletes a successor.
     */
    public void deleteSuccessor(String v1, String v2) {
        adjacencyList.get(v1).remove(v2);
    }
    /**
     * This method deletes a vertex.
     */
    public void deleteVertex(String str) {
        adjacencyList.remove(str);
    }
    /**
     * This method prints a graph, used for testing if the graph was populated
     * correctly from the input file.
     */
    public void print() {
        System.out.println("\n+ Graph: ");
        for (Map.Entry<String, ArrayList<String>> entry : 
                adjacencyList.entrySet()) {
            System.out.println("Key = " + entry.getKey() 
                    + ", Value = " + entry.getValue());
        }
        System.out.println(" ");
    }
}
