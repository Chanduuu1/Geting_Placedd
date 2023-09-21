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


    // thoda compli hai shekhar, but aaram se karna samaj jaega!
    static void createGraph2(ArrayList<Edge> graph[]){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,1,1));
        graph[0].add(new Edge(0,2,1));
        graph[0].add(new Edge(0,3,0));

        graph[1].add(new Edge(1,0,1));
        graph[1].add(new Edge(1,2,1));

        graph[2].add(new Edge(2,0,1));
        graph[2].add(new Edge(2,1,1));

        graph[3].add(new Edge(3,0,1));
        graph[3].add(new Edge(3,4,1));
        
        graph[4].add(new Edge(4,3,1));
    }
    public static boolean detectCycleUn(ArrayList<Edge>[] graph){
        boolean vis[] = new boolean[graph.length];
        for(int i = 0; i < graph.length; i++){// this will manage ki koi disjoint hai toh udar bhi traversal ho
            if(!vis[i]){
                if(detectCycleUn_Util(graph, vis, i, -1)){  // yeh ek particular disjoint componennt me dfs krkr pata lagagea ki cycle h ya nahi, -1 liya pata nahi time nahi mila sochne ko
                    return true; // iss functn ne true ret kiya to ret true, thoda bounce hua hoga but dry run, you can do it shekhar!
                }
            }
        }

        return false; // agar kahi se kuch nahi mila toh (saare components me cycle chaan marne ka baad)
    }
    public static boolean detectCycleUn_Util(ArrayList<Edge>[] graph, boolean[] vis, int curr, int parent){
        vis[curr] = true;

        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            // case 3
            if(!vis[e.dest]){
                //toh.. kuch nahi try to detect cycle aage by dfs traversing ->
                if(detectCycleUn_Util(graph,vis,e.dest,curr)){ // dekh yaha curr ko par bana diya
                    return true;
                }
            }

            // case 1 - neigh visited and not parent!
            else if(vis[e.dest] && e.dest != parent){
                return true;
            }

            //  case 2 -> eat 5 star, do nothing
        }

        return false;
    }
    


    // is bipartite
    static void createGraph3(ArrayList<Edge> graph[]){
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
        graph[3].add(new Edge(3,5,1));
        
        graph[4].add(new Edge(4,2,1));
        graph[4].add(new Edge(4,5,1));

        //graph[5].add(new Edge(5,3,1)); eben cycle karna hoto
        //graph[5].add(new Edge(5,4,1));

    }
    public static boolean isBipartite(ArrayList<Edge>[] graph){
        // yaha visited ke jaga pr color wala aar banage
        int col[] = new int[graph.length];
        for(int i = 0; i < col.length; i++){
            col[i] = -1; // initializong no color
        }

        Queue<Integer> q = new LinkedList<>();

        // checking for all components
        for(int i = 0; i < graph.length; i++){
            if(col[i] == -1){ // if node is not coloured yet
                q.add(i);
                col[i] = 0; // 0 => yellow
                while(!q.isEmpty()){
                    int curr = q.remove();
                    for(int j = 0; j < graph[curr].size(); j++){
                        Edge e = graph[curr].get(j);
                        if(col[e.dest] == -1){ // color not assigned, then assign color different than its parent
                            int nextCol = col[curr] == 0 ? 1 : 0; // bohot logical
                            col[e.dest] = nextCol;
                            q.add(e.dest);
                        }
                        else if(col[e.dest] == col[curr] ){// aiyyo, problem!
                            return false;
                        }
                    }
                    
                }
            }
        }

        return true; // thoda tricky tha but overall dekha jae toh medium difficulty is justified, aasan hi hai bass 2-3 baato ko dhyaan rakhna h
    }



    // cycle in directional graph
    static void createGraph4(ArrayList<Edge> graph[]){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,2,1));
        
        graph[1].add(new Edge(1,0,1));

        graph[2].add(new Edge(2,3,1));

        graph[3].add(new Edge(3,4,1));

        graph[4].add(new Edge(4,1,1));

    
    }
    public static boolean detectCycleDi(ArrayList<Edge>[] graph){
        boolean[] vis = new boolean[graph.length];
        boolean[] tracked = new boolean[graph.length];

        for(int i = 0; i < graph.length; i++){
            if(!vis[i]){
                if(detectCycleDiUtil(graph, tracked, i, vis)){ // yeh sab kaam karega
                    return true;
                }
            }
        }

        return false;
    }
    public static boolean detectCycleDiUtil(ArrayList<Edge>[] graph, boolean[] tracked, int curr, boolean[] vis){
        vis[curr] = true;
        tracked[curr] = true;

        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(tracked[e.dest] == true){ // tracked aray me already mera dest hai mtlb cycle!!
                return true;
            }
            if(!vis[e.dest]){ // agar dest abhi tak visit hi nahi hua hai toh visit krna
                if(detectCycleDiUtil(graph, tracked, e.dest, vis)){ // aur dekh ki kya iske neighbour ka kya scene hai. tricky hai bt i think 2-3 mahine ragde ga toh hojaega
                    return true;
                }
            }
        }
        tracked[curr] = false; // tracked se nikaal
        return false;
    }



    // topological sort
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

    public static void topologicalSort(ArrayList<Edge> graph[]){
        Stack<Integer> s = new Stack<>();
        boolean vis[] = new boolean[graph.length];
        for(int i = 0; i < graph.length; i++){
            if(!vis[i]){
                topologicalSortUtil(graph, s, vis, i);
            }
        }

        for(int i = 0; i < graph.length; i++){
            System.out.print(s.pop()+ " ");
        }
    }
    public static void topologicalSortUtil(ArrayList<Edge> graph[], Stack<Integer> s, boolean vis[], int curr){
        vis[curr] = true;
        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){ // if child not visited then add it to stack
                topologicalSortUtil(graph,s,vis,e.dest);
            }

        }
        s.add(curr);
    }


    public static void main(String args[]){
        /*
                    0 ------ 3
                   / \       |
                  /   \      |
                 /     \     |
                1       \    |
                         \   |   
                          \  |
                           \ |
                             2
         
         
         */ 
        
        
        
        
        int V = 6;
        ArrayList<Edge>[] graph = new ArrayList[V]; // array of arraylist array ka naam graph hai and uske andar ds AL hai - dekha kya khel khela!?
        createGraph5(graph);
        topologicalSort(graph);
        // aaram se shekhar dont worry! be a man, be strong!

    }
}