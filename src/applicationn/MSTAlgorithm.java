
package applicationn;


public class MSTAlgorithm {
     Graph graph=new Graph();
     /**
     * calculate and display the minimum spanning tree cost and display it , called in prim's algorithm
     * @param resultSet
     */
     void displayResultingMST(ResultSet[] resultSet) {
        int total_min_weight = 0;
        System.out.println("Minimum Spanning Tree: ");
        for (int i = 1; i < graph.veticesNo; i++) {
            total_min_weight += resultSet[i].weight;
        }
        System.out.println("Total cost: " + total_min_weight);
    }

    
    
}
