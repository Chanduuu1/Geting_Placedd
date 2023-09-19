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

    static void createGraph(ArrayList<Edge> graph[]){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,1,1));
        graph[0].add(new Edge(0,2,1));

        graph[1].add(new Edge(1,0,1));
        graph[1].add(new Edge(1,3,1));

        graph[2].add(new Edge(2,0,1));
        graph[2].add(new Edge(2,4,1));

        graph[3].add(new Edge(3,1,1));
        graph[3].add(new Edge(3,4,1));
        graph[3].add(new Edge(3,5,1));

        graph[4].add(new Edge(4,2,1));
        graph[4].add(new Edge(4,3,1));
        graph[4].add(new Edge(4,5,1));

        graph[5].add(new Edge(5,3,1));
        graph[5].add(new Edge(5,4,1));
        graph[5].add(new Edge(5,6,1));

        graph[6].add(new Edge(6,5,1));

    }


    public static void bfs(ArrayList<Edge>[] graph){
        Queue<Integer> q = new LinkedList<>();
        boolean vis[] = new boolean[graph.length]; // visited array bana li
        // default adding source node
        q.add(0);

        // main funda
        while(!q.isEmpty()){
            // step1 usse q se nikalna
            int curr = q.remove();

            if(!vis[curr]){
                // step 2 - abh kyuki wo visited nahi tha, toh hum usse print karwa denge and uska vis status T  kardenge
                System.out.print(curr + " ");
                vis[curr] = true;
                //step 3 - curr ke saare neighbour ko add to q
                for(int i = 0; i < graph[curr].size(); i++){ // dekh bhai aaray ke andar edge stored hai ye edge stored hone ka ds, AL hai. Confusion hai toh aaram se feel kar :)
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
                               
            }

            // dont panic aaram se le
        }
    }

    public static void main(String args[]){
        int V = 7;
        ArrayList<Edge>[] graph = new ArrayList[V]; // array of arraylist array ka naam graph hai and uske andar ds AL hai - dekha kya khel khela!?
        createGraph(graph);
        bfs(graph);

    }
}