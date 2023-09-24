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



    /*//Connecting cities
    static class Edge1 implements Comparable<Edge1> {
        int src;
        int dest;
        int wt;
        public Edge(int s, int d, int w){
            this.src = s;
            this.dest = d;
            this.wt = w;
        }

        @Override
        public int compareTo(Edge1 e2){
            return this.wt - e2.wt; // because pq me edg1 store karne wale hai
        }
    }
    public static void createGraphForCities(int[][] cities, ArrayList<Edge1>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph[i].length; i++){
                graph[i].add(new Edge(i,j,cities[i][j]));
            }
            
        }

    }
    public static int connectingCities(int[][] cities){
        // number of graph nodes = cities ka length
        ArrayList<Edge1>[] graph = new ArrayList[cities.length];
        createGraphForCities(cities, graph);

        // start prim
        PriorityQueue<Edge1> pq = new PriorityQueue<>();
        boolean vis[] = new boolean[cities.length];
        int finalCost = 0;
        pq.add(new Edge1(0,0,0));

        while(!pq.isEmpty()){
            Edge1 curr = pq.remove();
            if(!vis[curr.dest]){
                vis[curr.dest] = true;
                finalCost += curr.wt;
                for(int i = 0; i < graph[curr.src].size();i++){
                    Edge1 e = graph[curr.src].get(i);
                    pq.add(new Edge1(src,))
                }
                    
            }
            

        }
    }
    */ // finish this by implementing graph first filal me shradha di method

    // connecting cities
    static class Edge1 implements Comparable<Edge1>{
        int dest;
        int cost;
        public Edge1(int d, int c){
            this.dest = d;
            this.cost = c;
        }

        @Override
        public int compareTo(Edge1 e2){
            return this.cost - e2.cost;
        }
    }
    public static int connectingCities(int[][] cities){
        // start prim
        PriorityQueue<Edge1> pq = new PriorityQueue<>();
        boolean vis[] = new boolean[cities.length];
        
        pq.add(new Edge1(0,0));
        int finalCost = 0;

        while(!pq.isEmpty()){
            Edge1 curr = pq.remove();
            if(!vis[curr.dest]){
                vis[curr.dest] = true;
                finalCost += curr.cost;

                for(int i = 0; i < cities[curr.dest].length; i++){
                    if(cities[curr.dest][i] != 0){
                        pq.add(new Edge1(i, cities[curr.dest][i]));
                    }
                }
            }
        }

        return finalCost;

    }


    // disjoint set ds
    static int n = 7;
    static int par[] = new int[n];
    static int rank[] = new int[n];

    public static void init(){ //initalizing par and rank
        for(int i = 0; i < n; i++){
            par[i] = i;
            rank[i] = 0;
        }
    }

    public static int find(int x){
        if(x == par[x]){
            return x;
        }

        return find(par[x]);
    }

    public static void union(int a, int b){
        int parA = find(a);
        int parB = find(b); // agar dono ke par same mtlb cycle condition!
        if(rank[parA] == rank[parB]){
            par[parB] = parA;
            rank[parA]++;
        }
        else if(rank[parA] < rank[parB]){
            par[parA] = parB;
        }   
        else{
            par[parB] = parA;
        }
    }


    public static void main(String args[]){
        init();
        union(1,3);
        System.out.println(find(3));
        union(2,4);
        union(3,6);
        union(1,4);
        System.out.println(find(3));
        System.out.println(find(4));
        union(1,5);
    }

}