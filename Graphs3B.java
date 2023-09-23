import java.util.*;
public class Graphs3B{

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

    public static class Info{
        int vertex;
        int pathPrice;
        int stopNo;
        public Info(int v, int p, int sN){
            this.vertex = v;
            this.pathPrice = p;
            this.stopNo = sN;
        }

    }
    public static void createGraph(int[][] flights, ArrayList<Edge>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        } 
        for(int i = 0; i < flights.length; i++){
            int s = flights[i][0];
            int d = flights[i][1];
            int c = flights[i][2];
            graph[s].add(new Edge(s,d,c)); // this will create my graph
        }
    }
    public static int cheapestFlight(int n,int[][] flights,int src, int dest, int k){ 
       // get a graph ready forst of the flight path
       ArrayList<Edge>[] graph = new ArrayList[n];
       createGraph(flights, graph);
        // modieifed dijkstra begins
        int dist[] = new int[graph.length];
        for(int i = 0; i < graph.length; i++){
            if(i != src){
                dist[i] = Integer.MAX_VALUE;
            }
        }
       
       Queue<Info> q = new LinkedList<>();
        q.add(new Info(src,0,0));
        while(!q.isEmpty()){
            Info curr = q.remove();
            // abh neighbour ke pass jaa
            for(int i = 0; i < graph[curr.vertex].size(); i++){
                Edge e = graph[curr.vertex].get(i);
                int u = e.src;
                int v = e.dest;
                int w = e.wt;

                if(dist[u] + w < dist[v] && curr.stopNo <= k ){
                     dist[v] = dist[u] + w;
                     q.add(new Info(e.dest,dist[v],curr.stopNo+1));
                }
            }
        }

        // as we saw above now the nodes cost is changed only when 2 conditions are met, i,e relaxation condition and also the fact ki agar me k se zyada baar toh nahi ruka
        if(dist[dest] == Integer.MAX_VALUE){// mtlb abh tak update hi nahi hua because 1) ya to path nahi, 2) ya fir k stops me not achievable
            return -1;
        }
        else{
            return dist[dest];
        }

    }

    public static void main(String args[]){
        int flights[][] = {{0,1,100}, {1,2,100}, {2,0,100}, {1,3,600}, {2,3,200}};
        int n = 4, src = 0, dst = 3, k = 1;
        System.out.println(cheapestFlight(n,flights, src, dst, k));
        
    }

}