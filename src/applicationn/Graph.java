
package applicationn;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
import javafx.util.Pair;


public class Graph {
    
    
    /**
     * variable to take the number of vertices from the main method depend on user choice
     */
  public  int veticesNo=1;

    /**
     * variable to take the number edges from the main method depend on user choice
     */
   public int edgeNo;
    
    /**
     * a linked list that save every vertex adjacent 
     */
   public static LinkedList<Edge> []adjList ;
    
    boolean isDiagraph;
  

    /**
     * constructor 
     * @param veticesNo = number of vertices 
     * @param edgeNo = number of edges 
     */
    Graph(int veticesNo, int edgeNo) {
        this.veticesNo = veticesNo;
        this.edgeNo = edgeNo;
        adjList = new LinkedList[veticesNo];//veticesNo
        //initialize adjacency lists for all the vertices
      for (int i = 0; i < veticesNo; i++) {
            adjList[i] = new LinkedList<>();
       }
    }
     public Graph() {
    }

    public int getVeticesNo() {
        return veticesNo;
    }

    public int getEdgeNo() {
        return edgeNo;
    }

    public LinkedList<Edge>[] getAdjList() {
        return adjList;
    }

    public void setVeticesNo(int veticesNo) {
        this.veticesNo = veticesNo;
    }

    public void setEdgeNo(int edgeNo) {
        this.edgeNo = edgeNo;
    }

    public void setAdjList(LinkedList<Edge>[] adjList) {
        this.adjList = adjList;
    }
    
    
    
    //----------------------------------------------------------------------

    /**
     * randomly generate graph with given int weight the range is from (0,20)
     * @param graph = data structure made of vertices and edges 
     */
    public void make_graph(Graph graph) {
        // instance of Random class
        Random random = new Random();
        // ensure that all vertices are connected
        for (int i = 0; i < veticesNo - 1; i++) {
            int RandomNum = random.nextInt(10) + 1;
            addEdge(i, i + 1, RandomNum);

        }

        // generate random graph with the remaining edges
        int remaning = edgeNo - (veticesNo - 1);

        for (int i = 0; i < remaning; i++) {
            int source = random.nextInt(veticesNo);
            int Destination = random.nextInt(veticesNo);
            if (Destination == source || isConnected(source, Destination, graph.adjList)) { // to avoid self loops and duplicate edges
                i--;
                continue;
            }
            // generate random weights in range 0 to 20
            int weight = random.nextInt(20) + 1;
            // add edge to the graph
            addEdge(source, Destination, weight);
        }
       
    }
    
    /**
     * this method is used in make_graph() method to add a new edge to the graph
     * @param source source vertex
     * @param destination destination vertex
     * @param weight weight of the edge  
     */
    public void addEdge(int source, int target, int weight) {
        //object
        Edge edge = new Edge(source, target, weight);
       // edge.totalE.add(edge);
       adjList[source].addFirst(edge);

        edge = new Edge(target, source, weight);
        adjList[target].addFirst(edge); //for undirected graph

    }
    /**
     *checks if the edge is already existed and connected
     * @param Source
     * @param Destination
     * @param allEdges
     * @return
     */
    public boolean isConnected(int Source, int Destination, LinkedList<Edge> []allEdges) {
        for (LinkedList<Edge> i : allEdges) {
            for (Edge edge : i) {
                if ((edge.source == Source && edge.target == Destination) || (edge.source == Destination && edge.target == Source)) {
                    return true;
                }
            }
        }
        return false;
    }
  public void prim_PQ(){
      MSTAlgorithm MSTAlgorithm=new MSTAlgorithm();
     //start time
        double StartTime = System.currentTimeMillis();
        boolean[] MinSpanTree = new boolean[veticesNo];
        ResultSet[] resultSet = new ResultSet[veticesNo];
        int[] key = new int[veticesNo];  //keys are used to store the key to know whether priority queue update is required

        //Initialize all the keys to infinity and
        //initialize resultSet for all the vertices
        for (int i = 0; i < veticesNo; i++) {
            key[i] = Integer.MAX_VALUE;
            resultSet[i] = new ResultSet();
        }

        //Initialize priority queue
        //override the comparator to do the sorting based keys
        PriorityQueue<Pair<Integer, Integer>> priorityQueue = new PriorityQueue<>(veticesNo, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> point1, Pair<Integer, Integer> point2) {
                //sort using key values
                int key1 = point1.getKey();
                int key2 = point2.getKey();
                return key1 - key2;
            }
        });

        //create the pair for for the first index, 0 key 0 index
        key[0] = 0;
        Pair<Integer, Integer> point0 = new Pair<>(key[0], 0);
        //add it to pq
        priorityQueue.offer(point0);
        resultSet[0] = new ResultSet();
        resultSet[0].parent = -1;

        //while priority queue is not empty
        while (!priorityQueue.isEmpty()) {
            //extract the min
            Pair<Integer, Integer> extractedMinPair = priorityQueue.poll();

            //extracted vertex
            int extractedVertex = extractedMinPair.getValue();
            MinSpanTree[extractedVertex] = true;

            //iterate through all the adjacent vertices and update the keys
            LinkedList<Edge> list = Graph.adjList[extractedVertex];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //only if edge destination is not present in mst
                if (MinSpanTree[edge.target] == false) {
                } else {
                    int destination = edge.target;
                    int newKey = edge.weight;
                    //check if updated key < existing key, if yes, update if
                    if (key[destination] > newKey) {
                        //add it to the priority queue
                        Pair<Integer, Integer> NewPair = new Pair<>(newKey, destination);
                        priorityQueue.offer(NewPair);
                        //update the resultSet for destination vertex
                        resultSet[destination].parent = extractedVertex;
                        resultSet[destination].weight = newKey;
                        //update the key[]
                        key[destination] = newKey;
                    }
                }
            }
        }
        //finish time of the algorithm
        double FinishTime = System.currentTimeMillis();
        //print the total time consumed by the algorithm
        System.out.println("Total runtime of Prim's Algorithm (Usin PQ): " + (FinishTime - StartTime) + " ms.");
        //print mst
      MSTAlgorithm.displayResultingMST(resultSet);
    }

    

    
 
    
        public void prim_MH(){
            MSTAlgorithm MSTAlgorithm=new MSTAlgorithm();
             MHPrimAlg MHPrimAlg =new MHPrimAlg();
    //start time
        double StartTime = System.currentTimeMillis();
        
        boolean[] inHeap = new boolean[veticesNo];
        ResultSet[] resultSet = new ResultSet[veticesNo];
        //keys[] used to store the key to know whether min hea update is required
        int[] key = new int[veticesNo];
        //create heapNode for all the vertices
        HeapNode[] heapNodes = new HeapNode[veticesNo];
        for (int i = 0; i < veticesNo; i++) {
            heapNodes[i] = new HeapNode();
            heapNodes[i].vertex = i;
            heapNodes[i].key = Integer.MAX_VALUE;
            resultSet[i] = new ResultSet();
            resultSet[i].parent = -1;
            inHeap[i] = true;
            key[i] = Integer.MAX_VALUE;
        }
         //decrease the key for the first index
        heapNodes[0].key = 0;

        //add all the vertices to the MinHeap
        MinHeap minHeap = new MinHeap(veticesNo);
        //add all the vertices to priority queue
        for (int i = 0; i < veticesNo; i++) {
            minHeap.insert(heapNodes[i]);
        }
        
        //while minHeap is not empty
        while (!minHeap.isEmpty()) {
            //extract the min
            HeapNode extractedMinNode = minHeap.extractMin();

            //extracted vertex
            int extractedVertex = extractedMinNode.vertex;
            inHeap[extractedVertex] = false;

            //iterate through all the adjacent vertices
            LinkedList<Edge> list = adjList[extractedVertex];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //only if edge destination is present in heap
                if (inHeap[edge.target]) {
                    int destination = edge.target;
                    int newKey = edge.weight;
                    //check if updated key < existing key, if yes, update if
                    if (key[destination] > newKey) {
                        MHPrimAlg.decreaseKey(minHeap, newKey, destination);
                        //update the parent node for destination
                        resultSet[destination].parent = extractedVertex;
                        resultSet[destination].weight = newKey;
                        key[destination] = newKey;
                    }
                }
            }
        }
        
           //finish time of the algorithm
        double FinishTime = System.currentTimeMillis();
        //print the total time consumed by the algorithm
        System.out.println("Total runtime of Prim's Algorithm (Usin Min Heap): " + (FinishTime - StartTime) + " ms.");
        //print mst
      MSTAlgorithm.displayResultingMST(resultSet);

}



    
}
