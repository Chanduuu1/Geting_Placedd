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


    // all paths from src to target
    static void createGraph6(ArrayList<Edge> graph[]){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,3,1));
        
        graph[2].add(new Edge(2,3,1));
        
        graph[3].add(new Edge(3,1,1));

        graph[4].add(new Edge(4,0,1));
        graph[4].add(new Edge(4,1,1));

        graph[5].add(new Edge(5,0,1));  
        graph[5].add(new Edge(5,2,1));
    
    }
    public static void allPaths(ArrayList<Edge>[] graph, int curr, int dest, String path){
        if(curr == dest){
            System.out.println(path +dest);
            return;
        }

        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            allPaths(graph, e.dest, dest, path+curr);
        }   

    }



    // Dijkstras
    static void createGraph7(ArrayList<Edge> graph[]){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,1,2));
        graph[0].add(new Edge(0,2,4));
        
        graph[1].add(new Edge(1,3,7));
        graph[1].add(new Edge(1,2,1));

        graph[2].add(new Edge(2,4,3));
        
        graph[3].add(new Edge(3,5,1));

        graph[4].add(new Edge(4,3,2));
        graph[4].add(new Edge(4,5,5));

    
    }

    static class Pair implements Comparable<Pair>{
        int vertex;
        int distToVertex;

        public Pair(int v, int d){
            this.vertex = v;
            this.distToVertex = d;
        } 

        @Override
        public int compareTo(Pair p2){
            return this.distToVertex - p2.distToVertex; // this will create min heap of pairs on the basis of dist
        }
    }

    public static void dij(ArrayList<Edge> graph[], int src){ // O(V + E logV)
        int[] dis = new int[graph.length];
        for(int i = 0; i < graph.length; i++){
            if(i != src){
                dis[i] = Integer.MAX_VALUE; //+infinity
            }
        }
         boolean[] vis = new boolean[graph.length];
         PriorityQueue<Pair> pq = new PriorityQueue<>();
         pq.add(new Pair(src, 0));
         // loop
         while(!pq.isEmpty()){
            // yaha pq ka function itna hi hai ki jo neighbour me jaa rahe hai, unme se sabse kam dist kiska hai
            // kam dist wala node top pr hoga
            // so removing it will give me that
            Pair curr = pq.remove();

            // kya curr ke neigbours visited hai?
            if(!vis[curr.vertex]){
                vis[curr.vertex] = true;
                // neighbour
                for(int i = 0; i < graph[curr.vertex].size(); i++){
                    Edge e = graph[curr.vertex].get(i);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;


                    
                    if(dis[u] + wt < dis[v]){ // update dist
                        dis[v] = dis[u] + wt;
                        pq.add(new Pair(v, dis[v]));
                    }
                }
            }
         }

         // printing all dist
         for(int i = 0; i < dis.length; i++){
            System.out.print(dis[i] + " ");
            
         }
    }

    // dijksrta practice - khud se // o(v + elogn) this is because finding min dist Pq me is(o)1
    static class AnInfo implements Comparable<AnInfo> {
        int vertex;
        int distToVertex;
        public  AnInfo(int v, int c){
            this.vertex = v;
            this.distToVertex = c;
        }

        @Override
        public int compareTo(AnInfo p2){
            return this.distToVertex - p2.distToVertex;
        }
    }
    public static void dijksrta(ArrayList<Edge> graph[], int src){
        int dist[] = new int[graph.length];
        for(int i = 0; i < graph.length; i++){
            if(i != src){
                dist[i] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<AnInfo> pq = new PriorityQueue<>();
        pq.add(new AnInfo(src,0));
        while(!pq.isEmpty()){
            AnInfo curr = pq.remove();
            for(int i = 0; i < graph[curr.vertex].size(); i++){
                Edge e = graph[curr.vertex].get(i);
                int u = e.src;
                int v = e.dest;
                int wt = e.wt;

                // relaxation step
                if(dist[u] + wt < dist[v]){ // if this condition is not met no need to do anything
                    dist[v] = dist[u] + wt;
                    pq.add(new AnInfo(v,dist[v])); // v tak jana ke abh tak ka shortest dist
                }

            }
        }
        for(int i = 0; i < dist.length; i++){
            System.out.print(dist[i]);
            
        }
    }

    //dij using array #khud se pq nahi toh queue toh use krna hi padega so cmplt it  later.
    public static void dijksrtaArr(ArrayList<Edge> graph[], int src){
        int dist[] = new int[graph.length];
        for(int i = 0; i < graph.length; i++){
            if(i != src){
                dist[i] = Integer.MAX_VALUE;
            }
        }

        Queue<AnInfo>
        

    }



    //bellman ford
    static void createGraph8(ArrayList<Edge> graph[]){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,1,2));
        graph[0].add(new Edge(0,2,4));
        
        graph[1].add(new Edge(1,2,-4));

        graph[2].add(new Edge(2,3,2));
        
        graph[3].add(new Edge(3,4,4));

        graph[4].add(new Edge(4,1,-1));

    
    } 
    public static void bellmanFord(ArrayList<Edge>[] graph, int src){
        int dist[] = new int[graph.length];
        for(int i = 0; i < dist.length; i++){
            if(i!= src){
                dist[i] = Integer.MAX_VALUE;
            }
        }

        // main algo

        int V = graph.length;
        for(int i = 0; i < V-1; i++){ // O(VE) cube sab mat samajna abh thoda pro ban gaya hai
            for(int j = 0; j < graph.length; j++){
                for(int k = 0; k < graph[j].size(); k++){
                    Edge e = graph[j].get(k);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;

                    // relaxation
                    if(dist[u] != Integer.MAX_VALUE /* yeh isiliye likha hai ki incase kisi edge u->v pr u infi ho toh 
                    usme kuch bhi add karenege to kuch problem ho jaegi so yeh dhyan rakhna */ && dist[u] + wt < dist[v]){
                        dist[v] = dist[u] + wt;

                    }

                }
            }
        }


        // now print
        for(int i = 0 ; i < dist.length; i++){
            System.out.print(dist[i] + " ");    
        }
    }


    //prims algo - this code just returns the final cost of the MST and not the edges, try to get edges later when you have time
    static void createGraph9(ArrayList<Edge> graph[]){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,1,10));
        graph[0].add(new Edge(0,2,15));
        graph[0].add(new Edge(0,3,30));
        
        graph[1].add(new Edge(1,0,10));
        graph[1].add(new Edge(1,3,40));

        graph[2].add(new Edge(2,0,15));
        graph[2].add(new Edge(2,3,50));
        
        graph[3].add(new Edge(3,1,40));
        graph[3].add(new Edge(3,2,50));


    
    } 
    static class Pairs implements Comparable<Pairs> {
        int v; // the vertex
        int c; // its cost
        public Pairs(int v, int c){
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Pairs p2){
            return this.c - p2.c;
        }
    }
    public static void prims(ArrayList<Edge>[] graph){
        boolean vis[] = new boolean[graph.length];
        PriorityQueue<Pairs> pq = new PriorityQueue<>();
        pq.add(new Pairs(0,0));
        int finalCost = 0;

        while(!pq.isEmpty()){
            Pairs curr = pq.remove();
            if(!vis[curr.v]){
                vis[curr.v] = true;
                finalCost += curr.c; // kyuki yeh top of pq tha mtlb yeh min hi tha isikiye cost ko add kra

                for(int i = 0; i < graph[curr.v].size(); i++){
                    Edge e = graph[curr.v].get(i);
                    pq.add(new Pairs(e.dest, e.wt));
                }
            }
        }
        System.out.println("min count of the MST is " + finalCost);
        
    }

    
    public static void main(String args[]){
        int V = 6;
        ArrayList<Edge>[] graph = new ArrayList[V]; 
        createGraph7(graph);
        dijksrta(graph,0);
    }
}