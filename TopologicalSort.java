import java.io.*;
import java.util.*;
/**
 * Topological Sorting
 * April 2019
 * 
 * This class contains the main method where the user has the option to enter 
 * a file name on the command line. Then, the graph is populated by calling 
 * appropriate methods to read and parse the input file. Finally,a method is 
 * called to topologically sort the graph and print the results to the screen.
 *
 * @author Jill Eliceiri
 */
public class TopologicalSort {

    public static void main(String[] args) {

        //instantiate a new Digraph object
        Digraph graph = new Digraph();
        //instance variable represents the filename
        String filename;

        //create scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the prereqs filename "
                + "(or simply press return to use ./project2-testA.tab)");
        filename = scanner.nextLine();
        scanner.close();
        if (filename.isEmpty()) {
            filename = "project2-testA.tab";

            /*COMMENT OUT BELOW FOR ADDITIONAL TESTS or enter at command line*/
            //filename = "project2-testB-containsDisconnected.tab";
            //filename = "project2-testC.tab";
            //filename = "project2-testD-containsNONE.tab";
        }
        //System.out.println("Filename: " + filename + " end");

        readInputFile(filename, graph);

        System.out.println("Found " + graph.getNumberOfVertices() 
                + " vertices and " + graph.getNumberOfEdges() 
                + " edges in ./" + filename);
        System.out.println("Course order:");

        //Call topological sort and send it the graph
        topologicalSort(graph);
    }

    /**
     * This method creates a scanner object and reads the lines of a file. If a
     * line is not an empty string, then call the method to parse the line.
     */
    static void readInputFile(String fileName, Digraph graph) {
        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            //check if there is more input
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (!"".equals(str)) {
                    parseLine(str, graph);
                }
            }
            //handle exception
        } catch (IOException exp) {
            exp.printStackTrace();
        }
        //close scanner
        scanner.close();
    }

    /**
     * This method parses a line using a tab delimiter and calls methods
     * to populate a graph using the file input.
     */
    private static void parseLine(String str, Digraph graph) throws IOException{

        Scanner scanner = new Scanner(str);
        scanner.useDelimiter("\t");
        while (scanner.hasNext()) {

            String a = scanner.next();
            String b = scanner.next();
            if (!a.equals("NONE")){
                graph.addVertex(a);
            }  
            graph.addVertex(b);
            graph.addSuccessor(a, b);
        }
        scanner.close();
    }

    /**
     * This method performs topological sorting on a digraph and prints the
     * results to screen.
     */
    public static void topologicalSort(Digraph graph) {

        //a stack to hold vertices that do not have successors
        Stack<String> stackWithNoSuccessors = new Stack();

        //list to hold final result that will be printed later
        ArrayList<String> finalResult = new ArrayList<>();

        //make copy of graph
        Digraph myGraph = graph;

        //loop through adjList to initially populate the stack
        for (Map.Entry<String, ArrayList<String>> entry : 
                myGraph.getAdjacencyList().entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            //if vertex does not have successors, add to stack
            if (value.isEmpty()) {
                stackWithNoSuccessors.push(key);
            }
        }
        //loop through stack while it contains vertices with no successors
        while (stackWithNoSuccessors.size() > 0) {
            //pop off a vertex
            String toRemove = stackWithNoSuccessors.pop();
            //add it to final result
            finalResult.add(0, toRemove);
            //remove it from the adjList
            myGraph.getAdjacencyList().remove(toRemove);

            //remove this vertex from other other successor lists (remove edges)
            for (Map.Entry<String, ArrayList<String>> entry : 
                    myGraph.getAdjacencyList().entrySet()) {
                String key = entry.getKey();
                ArrayList<String> value = entry.getValue();
                if (value.contains(toRemove)) {
                    myGraph.deleteSuccessor(key, toRemove);
                    //System.out.println("Deleted: " + toRemove + " from 
                    //successor list: " + key);
                    if (value.isEmpty()) {
                        if (!stackWithNoSuccessors.contains(key)) {
                            stackWithNoSuccessors.add(key);
                            //System.out.println("Added to no successor list: " 
                            //+ key + "\n");
                        }
                    }
                }
            }
        }
        //print final results to screen
        for (String result : finalResult) {
            System.out.println(result);
        }
    }
}

//TESTING BEFORE READ INPUT FILE
/*graph.addVertex("2010");
graph.addVertex("2020");
graph.addVertex("3830");
graph.addVertex("4000");
graph.addVertex("4230");
graph.addVertex("4240");
graph.addVertex("4010");
graph.addVertex("4260");

graph.addSuccessor("2010", "2020");
graph.addSuccessor("2010", "3830");
graph.addSuccessor("3830", "4000");
graph.addSuccessor("3830", "4230");
graph.addSuccessor("3830", "4240");
graph.addSuccessor("4000", "4010");
graph.addSuccessor("4240", "4260");
System.out.println("INITIAL GRAPH");
graph.print();

System.out.println();
graph.deleteSuccessor("4000", "4010");
graph.deleteSuccessor("3830", "4230");
graph.print();
graph.deleteVertex("4230");
graph.deleteVertex("2010");
System.out.println();*/

