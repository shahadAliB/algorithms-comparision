
package applicationn;

import java.util.ArrayList;


public class Edge {
    /**
     * source vertex
     */
    int source;

  

    /**
     * edge weight
     */
    int weight=1;
    
    
    int target=0;
    
    
  //  ArrayList<Edge> totalE = new ArrayList<Edge>();
    /**
     * constructor 
     * @param source = vertex
     * @param target
     * @param weight = int value has a edge weight 
     */
    public Edge(int source, int target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    Edge() {
        
    }

    @Override
    public String toString() {
        return source + "-" + target + ": " + weight;
    }

    int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Edge get(int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
