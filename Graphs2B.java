import java.util.*;
public class Graphs2B{

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



    static void createGraph5(ArrayList<Edge> graph[]){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[2].add(new Edge(2,3,1));
        
        graph[3].add(new Edge(3,1,1));

        graph[4].add(new Edge(4,0,1));
        graph[4].add(new Edge(4,1,1));

        graph[5].add(new Edge(5,0,1));  
        graph[5].add(new Edge(5,2,1));
    
    }
    public static void indegCalculator(ArrayList<Edge>[] graph, int[] indeg){
        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph[i].size(); j++){
                Edge e = graph[i].get(j);
                indeg[e.dest]++; // e.dest pr jaa raha so e.dest ko ++ ache se dry run kr samjega
            }
        }
    }
    public static void kahnsTopologicalSort(ArrayList<Edge>[] graph){
        int[] indeg = new int[graph.length];
        indegCalculator(graph,indeg);
        Queue<Integer> q = new LinkedList<>();

        // i should add those vertices whoes in deg = 0, initia default step
        for(int  i = 0; i < indeg.length; i++){
            if(indeg[i] == 0){
                q.add(i);
            }
        }

        // now bfs step
        while(!q.isEmpty()){
            int curr = q.remove();
            System.out.print(curr+ " ");
            // abh inke neighbour ko reduce
            for(int i = 0; i < graph[curr].size(); i++){
                Edge e = graph[curr].get(i);
                indeg[e.dest]--;
                if(indeg[e.dest] == 0){
                    q.add(e.dest);
                }
            }

        }



    }

    public static void main(String args[]){
        int V = 6;
        ArrayList<Edge>[] graph = new ArrayList[V]; 
        createGraph5(graph);
        kahnsTopologicalSort(graph);
    }
}