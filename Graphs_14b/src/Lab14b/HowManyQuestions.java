package Lab14b;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A class that implements a solution
 * utilizing greedy algorithm for graph coloring
 * The undirected graph is represented by adjacency list
 *
 * @author Anna Bieszczad
 * @version 5/1/2018
 */

class HowManyQuestions
{
    private int numberOfVertices;
    private LinkedList<Integer> adjacencyList[]; //graph represented as Adjacency List

    /**
     * Takes the input matrix and creates the graph represented as adjancency list
     *
     * @param graph two dimesional array of booleans, true indicates
     *              that the corresponding vertexes are neighbors
     */
    public HowManyQuestions(boolean[][] graph)
    {
        //TODO Lab14b #2.1
        this.numberOfVertices = graph.length;

        this.adjacencyList = new LinkedList[numberOfVertices];
        for (int i=0; i<this.numberOfVertices; ++i) {
            this.adjacencyList[i] = new LinkedList();
        }
        for (int u = 0; u < graph.length; u++) {
            for (int v = 0; v < graph[u].length; v++) {
                if(graph[u][v]){
                    this.adjacencyList[u].add(v);
                }
            }
        }


    }

    /**
     * Traverses the adjacencyList and displays all neighbors of each vertex
     */
    public void displayNeighbors()
    {
        for (int u = 0; u < this.numberOfVertices; u++) {
            System.out.print("Vertex " + u + " --->  has neighbors ");
            this.adjacencyList[u].forEach(result -> System.out.print(result + " "));
            System.out.println();
        }
        //TODO Lab14b #2.2
    }

    /**
     * Assigns questions to all vertices and
     * prints the assignment of questions
     * If the solution is not possible the method throws an Exception
     * with appropriate message. The possible exception is handled by this method as well
     */
    public boolean greedyQuestionChooser(int numberOfQuestions)
    {
        //Got help from this website
        //https://www.geeksforgeeks.org/graph-coloring-set-2-greedy-algorithm/
        boolean solved = false;

        int assignedQuesions[] = new int[numberOfVertices]; //int result[] = new int[V];

        // Initializes all vertices as unassigned -1
        Arrays.fill(assignedQuesions, -1); //Arrays.fill(result, -1);

        // A temporary array to store the available questions.
        // Initially, all questions are not taken
        boolean taken[] = new boolean[numberOfQuestions]; //boolean available[] = new boolean[V];

        // Assign the first question to first vertex - first question is 0
        assignedQuesions[0] = 0; //result[0]  = 0;
        try
        {
            for (int u = 1; u < numberOfVertices; u++)
            {
                // Process all adjacent vertices and flag their colors
                // as unavailable
                Iterator<Integer> it = this.adjacencyList[u].iterator() ;
                while (it.hasNext()) {
                    int i = it.next();
                    if (assignedQuesions[i] != -1)
                        taken[assignedQuesions[i]] = true;
                }
                int c = 0;
                boolean found = false;
                while((c < numberOfQuestions) && !found){
                    if (!taken[c]){
                        found = true;
                    }
                    else{
                        c++;
                    }
                }

                Arrays.fill(taken, false);
                if(found) {
                    assignedQuesions[u] = c;
                }
                else{
                    throw new Exception("--> The solution does not exist - not enough choices");
                }
            }
            solved = true;
            for (int i = 0; i < numberOfVertices; i++) {
                System.out.println("Student " + i + " --->  Question " + assignedQuesions[i]);
            }
            //TODO Lab14b #2.3

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println();
        }
        return solved;
    }

    // Driver method
    public static void main(String args[])
    {
        HashMap<String, HowManyQuestions> graphsToCheck = new HashMap<>();

        graphsToCheck.put("g1",
                new HowManyQuestions(new boolean[][]{
                        {false, true, true, false, false},
                        {true, false, true, true, false},
                        {true, true, false, true, false},
                        {false, true, true, false, true},
                        {false, false, false, true, false}}));
        graphsToCheck.put("g2",
                new HowManyQuestions(new boolean[][]{
                        {false, true, true, true, false},
                        {true, false, true, true, true},
                        {true, true, false, false, true},
                        {true, true, false, false, true},
                        {false, true, true, true, false}}));
        graphsToCheck.put("g3",
                new HowManyQuestions(new boolean[][]{
                        {false, true, true, true, false},
                        {true, false, true, true, true},
                        {true, true, false, true, false},
                        {true, true, true, false, true},
                        {false, true, false, true, false}}));

        // circular
        boolean[][] g4 = new boolean[24][24];
        for (int i = 0; i < 24; i++)
        {
            g4[i][(i + 1) % 24] = true;
            g4[(i + 1) % 24][i] = true;
        }
        graphsToCheck.put("g4", new HowManyQuestions(g4));

        // SIE 1232
        graphsToCheck.put("g5",
                new HowManyQuestions(new boolean[][]{
                        {false, true, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false},
                        {true, false, true, false, false, false, false, false, false, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false},
                        {false, true, false, true, false, false, false, false, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false},
                        {false, false, true, false, true, false, false, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                        {false, false, false, true, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, true, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, true, true, false, true, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false},
                        {false, false, false, true, true, true, true, false, true, false, false, false, false, false, false, true, true, true, false, false, false, false, false, false},
                        {false, false, true, true, true, false, false, true, false, true, false, false, false, false, false, true, true, true, false, false, false, false, false, false},
                        {false, true, true, true, false, false, false, false, true, false, true, false, false, true, true, true, false, false, false, false, false, false, false, false},
                        {true, true, true, false, false, false, false, false, false, true, false, true, true, true, true, false, false, false, false, false, false, false, false, false},
                        {true, true, false, false, false, false, false, false, false, false, true, false, true, true, false, false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false, false, true, true, false, true, false, false, false, false, false, false, false, false, true, true},
                        {false, false, false, false, false, false, false, false, false, true, true, true, true, false, true, false, false, false, false, false, false, true, true, true},
                        {false, false, false, false, false, false, false, false, true, true, true, false, false, true, false, true, false, false, false, false, true, true, true, false},
                        {false, false, false, false, false, false, false, true, true, true, false, false, false, false, true, false, true, false, false, true, true, true, false, false},
                        {false, false, false, false, false, false, true, true, true, false, false, false, false, false, false, true, false, true, true, true, true, false, false, false},
                        {false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false, true, true, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, true, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, true, false, false, false},
                        {false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, false, false, true, false, true, false, false},
                        {false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, false, false, false, false, true, false, true, false},
                        {false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, false, false, false, false, false, false, true, false, true},
                        {false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false}}));


        final int NUMBER_OF_GRAPHS = 5;
        for (int i = 1; i <= NUMBER_OF_GRAPHS; i++)
        {
            System.out.println("Created graph " + i + ":");
            String key = "g" + i;
            graphsToCheck.get(key).displayNeighbors();
            System.out.println();
        }

        int numberOfQuestions = 1;
        boolean[] solutionFound = new boolean[NUMBER_OF_GRAPHS];
        boolean done = false;
        while (!done)
        {
            numberOfQuestions++;
            System.out.println("****** Checking if " + numberOfQuestions + " questions are enough ******");
            done = true;
            for (int i = 1; i <= NUMBER_OF_GRAPHS; i++)
            {
                if (!solutionFound[i - 1])
                {
                    System.out.println("   *** Checking graph " + i + " ***");
                    String key = "g" + i;
                    if (graphsToCheck.get(key).greedyQuestionChooser(numberOfQuestions))
                        solutionFound[i - 1] = true;
                    else
                        done = false;
                    System.out.println();
                }
            }
        }

        System.out.println("***** DONE - all graphs were assigned solutions *****");
    }
}