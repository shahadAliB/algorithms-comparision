
package applicationn;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class KruskalAlg extends MSTAlgorithm{
    //constructer
    public KruskalAlg() {
    }
     
    
   public void kruskalMST() {
       
         //object 
       Graph graph=new Graph();
         Edge edge=new Edge()  ;
          ResultSet[] resultSet = new ResultSet[graph.veticesNo];
          MSTAlgorithm MSTAlgorithm=new MSTAlgorithm();
        //start time
        double startTime = System.currentTimeMillis();
        // to used in tracing
        String treeV = "";
        // change data type from ArrayList to LinkedList
       
     
       
           

        LinkedList<Edge> []Edges =  Graph.adjList.clone();
 PriorityQueue<Edge> priorityQueueVar = new PriorityQueue<>(graph.veticesNo,Comparator.comparingInt(o -> o.weight ));
   
//priority queue holds  all the edges
        //sort the edges by its weights
        for (LinkedList<Edge> Edge : Edges) {
            for (int j = 0; j < Edge.size(); j++) {
                priorityQueueVar.add(Edge.get(j));
            }
        }
        
       
        
        //create a parent []
        int[] parent = new int[graph.veticesNo];

        //makeset
        makeSet(parent);

        LinkedList<Edge> MinSpanTree = new LinkedList<>();

        //process vertices - 1 edges
        int index = 0;
        while (index < graph.veticesNo - 1 && !priorityQueueVar.isEmpty()) {
             edge = priorityQueueVar.remove();
            //check if adding this edge creates a cycle
            int x_set = findParent(parent, edge.source);
            int y_set = findParent(parent, edge.target);

            if (x_set == y_set) {
                //ignore, will create cycle
            } else {
                //add it to our final result
                MinSpanTree.add(edge);
                treeV += edge.toString() + "\n";
              // System.out.println("\n Tree Vertex:");
              //  System.out.println(treeV);
                index++;
                union(parent, x_set, y_set);
            }
//             System.out.println("list of Edges:");
//             for (Edge e : pq) {
//             System.out.println(e.toString());
//             }

        }

        //finish time of the algorithm
        double ftime = System.currentTimeMillis();
        //print the total time consumed by the algorithm
        System.out.println("Total runtime of Kruskal's Algorithm: " + (ftime - startTime) + " ms.");
        //print MST
        //System.out.println("Minimum Spanning Tree: ");
        MSTAlgorithm.displayResultingMST(resultSet);
        
        
        
        
        
    }

    /**
     * makeSet method used in Kruskal's 
     * @param parent
     */
    private void makeSet(int[] parent) {
          //object 
        Graph graph=new Graph();
        
        //Make set: creating a new element with a parent pointer to itself.
        for (int i = 0; i < graph.veticesNo; i++) {
            parent[i] = i;
        }
    }

    /**
     *union method used by Kruskal's
     * @param parent 
     * @param x first node
     * @param y  second node
     */
    private void union(int[] parent, int x, int y) {
        int setXparent = findParent(parent, x);
        int setYparent = findParent(parent, y);
        //make x as parent of y
        parent[setYparent] = setXparent;
    }

    /**
     *findParent method used by union method 
     * @param parent parent list
     * @param vertex the vertex
     * @return
     */
    private int findParent(int[] parent, int vertex) {
        //chain of parent pointers from x upwards through the tree
        // until an element is reached whose parent is itself (ROOT)
        if (parent[vertex] == vertex) {
            return vertex;
        }
        return findParent(parent, parent[vertex]);
    }
    //----------------------------To print Graph------------------------------------

    /**
     *print Graph method 
     * @param edgeList list of the edges in the graph 
    
    private void printGraph(LinkedList<Edge> edgeList) {
        int cost = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            cost += edge.weight;
        }
        System.out.println("Minimum Spanning Tree Cost = " + cost);
    } */

    
    
    
    
        }

  
 
  
    

    

