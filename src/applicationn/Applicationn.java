
package applicationn;

import java.util.Scanner;


public class Applicationn {

  
    public static void main(String[] args) {
         Scanner input = new Scanner(System.in);
         PQPrimAlg PQPrimAlg=new PQPrimAlg();
                
         System.out.println("\t -------Test and compare Different Minimum Spanning Tree Algorithms-------\n");
        System.out.println("\t1- Kruskal's Algorithm& Prim's Algorithm (based on Priority Queue)\n"
                + "\t2- Prim's Algorithm (based on Min Heap)& Prim's Algorithm(based on Priority Queue)");
        System.out.print(">> Enter your choice (1 or 2): ");
        int Algochoice = input.nextInt();
        System.out.println(">>Test  cases : (where n is the number of vertices "
                + "and m is the number of edges: ");
        System.out.println(" 1:  n=1,000 ,  m=10,000");
        System.out.println(" 2:  n=1,000 ,  m=15,000");
        System.out.println(" 3:  n=1,000 ,  m=25,000");
        System.out.println(" 4:  n=5,000 ,  m=15,000");
        System.out.println(" 5:  n=5,000 ,  m=25,000");
        System.out.println(" 6:  n=10,000 , m=15,000");
        System.out.println(" 7:  n=10,000 , m=25,000");
        System.out.println(" 8:  n=20,000 , m=200,000");
        System.out.println(" 9:  n=20,000 , m=300,000");
        System.out.println("10:  n=50,000 , m=1,000,000");
        System.out.print("> Enter a case to test: ");
        int uChoice = input.nextInt();
        
        
        switch (uChoice) {
            case 1:
                createGraph(1000, 10000, Algochoice);
                break;
            case 2:
                createGraph(1000, 15000, Algochoice);
                break;
            case 3:
                createGraph(1000, 25000, Algochoice);
                break;
            case 4:
                createGraph(5000, 15000, Algochoice);
                break;
            case 5:
                createGraph(5000, 25000, Algochoice);
                break;
            case 6:
                createGraph(10000, 15000, Algochoice);
                break;
            case 7:
                createGraph(10000, 25000, Algochoice);
                break;
            case 8:
                createGraph(20000, 200000, Algochoice);
                break;
            case 9:
                createGraph(20000, 300000, Algochoice);
                break;
            case 10:
                createGraph(50000, 1000000, Algochoice);
                break;
            default:
                System.out.println("Invalid input!");
                System.out.println("Thank you for comming by!");
                break;
        }
        
    }
          //-----------------------to create Graph object---------------------------------

    /**
     * Create Graph object randomly generated
     * @param vertNum   vertices number 
     * @param EdNum     edge number 
     * @param Algochoice  which algorithms to compare
     */
    public static void createGraph(int vertNum, int EdNum, int Algochoice) {
        Scanner input = new Scanner(System.in);
        Graph graph = new Graph(vertNum, EdNum);
        KruskalAlg KruskalAlg= new KruskalAlg();
        PQPrimAlg PQPrimAlg = new PQPrimAlg();
        MHPrimAlg MHPrimAlg =new MHPrimAlg();
        System.out.println("Is the graph directed?\n enter (true) or (false) ");
        String isdi = input.next();
        if (isdi.equalsIgnoreCase("true")) {
            System.out.println(" Prim’s and Kruskal’s  MST Algorithms don't work on directed graphs");
            System.out.println(" Would you like to quit(enter quit) ,or continue considering the graph is undirected (enter cont) ");
            String contOrQuit = input.next();
            if (contOrQuit.equalsIgnoreCase("quit")) {
                System.out.println("Thank you for comming by!");
                System.exit(0);
            }
            } else {
            graph.make_graph(graph);
        }
        // to perform Task 1
        switch (Algochoice) {
            // to perform Task 2
            case 1:
             KruskalAlg.kruskalMST();
               graph.prim_PQ();
                break;
            case 2:
            graph.prim_MH();
                graph.prim_PQ();
                break;
            default:
                System.out.println("Invalid input!");
                System.out.println("Thank you for comming by!");
                break;
        }
    }
    
}
