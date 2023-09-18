import java.util.*;
public class GraphsB{

    static class Edge{
        int src;
        int dest;
        int wt;
        public Edge(int s, int d, int w){
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    public static void main(String args[]){
        /* 
                (5)
            0 ----- 1
                   / \
               (1)/   \  (2)
                 /     \ 
                2-------3
                |  (1)
            (2) |  
                |
                4
        
         */

        int V = 5;
        ArrayList<Edge>[] graph = new ArrayList[V]; // array of arraylist array ka naam graph hai and uske andar ds AL hai - dekha kya khel khela!?
        for(int i = 0; i < V; i++){
            graph[i] = new ArrayList<>(); // array ke har box ke andar jakr we gave life to the AL
        }

        // 0's vertex
        graph[0].add(new Edge(0,1,5));

        // 1 vertex
        graph[1].add(new Edge(1,1,5));
        graph[1].add(new Edge(1,2,1));
        graph[1].add(new Edge(1,3,2));

        //2 vertex
        graph[2].add(new Edge(2,1,1));
        graph[2].add(new Edge(2,3,1));
        graph[2].add(new Edge(2,4,2));

        //3 vertex
        graph[3].add(new Edge(3,2,1));
        graph[3].add(new Edge(3,1,2));

        //4 vertex
        graph[4].add(new Edge(4,2,2));


        //  now if i want to find 2's neighbour
        for(int i = 0; i < graph[2].size(); i++){
            Edge e = graph[2].get(i);
            // sara details transfered to e!
            System.out.println(e.dest);
            
        }

    }
}