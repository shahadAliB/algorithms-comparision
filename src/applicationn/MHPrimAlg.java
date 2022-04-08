
package applicationn;

import java.util.LinkedList;


public class MHPrimAlg extends MSTAlgorithm {
    Graph graph = new Graph( );

    

        /**
     * remove the min value from the heap 
     * @param minHeap = All min heap values 
     * @param newKey = Index
     * @param vertex = wanted vertex
     */
    public void decreaseKey(MinHeap minHeap, int newKey, int vertex) {

        //get the index which key's needs a decrease;
        int index = minHeap.indexes[vertex];

        //get the node and update its value
        HeapNode node = minHeap.MinHeapArr[index];
        node.key = newKey;
        minHeap.bubbleUp(index);
    }
   

    }

