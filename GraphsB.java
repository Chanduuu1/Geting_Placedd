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


    public static void dfs(ArrayList<Edge>[] graph, int curr, boolean[] visited){
        // source toh by default visited na
        System.out.print(curr + " ");
        visited[curr] = true;
        // neighbour ke sath 
        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!visited[e.dest]){
                dfs(graph,e.dest,visited);
            }
        }
        return;
        
        
    }



    public static boolean hasPath(ArrayList<Edge>[] graph, int s, int d, boolean[] visited){
        // if src = dest yeah buddy
        if(s == d){
            return true;
        }

        visited[s] = true;

        // if not above, can my neigbour take me to the node?
        for(int i = 0; i < graph[s].size(); i++){
            Edge e = graph[s].get(i);
            if(!visited[e.dest] && hasPath(graph, e.dest, d, visited)){
                return true; // mtlb e.dest ke pass ek path hai, i trusted recurssion check bhi kr sakta hai ek baaar dw
            }
        }

        return false;
    }



    public static void bfsConnectedGraph(ArrayList<Edge>[] graph){
        boolean vis[] = new boolean[graph.length];
        for(int i = 0; i < graph.length; i++){
            if(!vis[i]){
                bfsUtil(graph, vis);
            }
        }
    }
    public static void bfsUtil(ArrayList<Edge>[] graph, boolean[] vis){
        Queue<Integer> q = new LinkedList<>();
        
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

    public static void dfsConnectedGraph(ArrayList<Edge>[] graph){
        boolean vis[] = new boolean[graph.length];
        for(int i = 0; i < graph.length; i++){
            if(!vis[i]){
                dfsUtil(graph,i, vis);
            }
        }
    }
    public static void dfsUtil(ArrayList<Edge>[] graph, int curr, boolean[] visited){
        // source toh by default visited na
        System.out.print(curr + " ");
        visited[curr] = true;
        // neighbour ke sath 
        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!visited[e.dest]){
                dfsUtil(graph,e.dest,visited);
            }
        }
        return;
        
        
    }



    public static void main(String args[]){
        int V = 7;
        ArrayList<Edge>[] graph = new ArrayList[V]; // array of arraylist array ka naam graph hai and uske andar ds AL hai - dekha kya khel khela!?
        createGraph(graph);
        boolean[] visited = new boolean[V];
        System.out.println(hasPath(graph, 0, 7,visited));
        

    }
}