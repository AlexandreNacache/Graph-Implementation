import java.util.*;
import java.io.*;


public class graph {

    class edge implements Comparable<edge>{
        int from,to,weight;
        public int compareTo(edge e){
            return this.weight-e.weight;
        }
    };


    static int E;
    static  LinkedList<Integer> adj[] = new LinkedList[100005];
    static boolean visited[]=new boolean[100005];
    static int parent[] =new int[100005];
    static edge Edge[];
    static edge now;
    graph(int m){
        Edge=new edge[m];
        E=m;
        now =new edge();
        for(int i=0;i<m;i++){
            Edge[i]=new edge();
        }
    }


    public static void dfs(int u){  // depth first search function
        visited[u]=true;  // marking current node as visited
        System.out.print(u+" "); // printing current node
        Iterator <Integer> i=adj[u].listIterator();  // declaring iterator
        while(i.hasNext()){ // loop for getting every adjacent node of u
            int v=i.next();  // v as adjacent node of u
            if(visited[v]==false){  // checking if the adjacent node is already visited or not
                dfs(v); // if not visited then calling dfs function for v
            }
        }
    }


    public static void bfs(int u){  // breadth first function
        Queue <Integer> q=new LinkedList<>();  // declaring queue for BFS
        q.add(u); // adding the source node to queue
        visited[u]=true; // marking current node as visited
        while(q.isEmpty()==false){  // checking if the current node is empty or not
            int now=q.poll(); // getting the front element of the queue and removing it
            System.out.print(now+" "); // printing the current node
            Iterator <Integer> i=adj[now].listIterator(); // declaring iterator
            while(i.hasNext()){  // loop for getting every adjacent node of u
                int v=i.next(); // v as adjacent node of u
                if(visited[v]==false){ // checking visited or not
                    visited[v]=true; // marked as visited
                    q.add(v); // adding the adjacent node to queue
                }
            }
        }
    }


    public static int find(int x){ // function to find the root of a particular node
        if(parent[x]==x) return x;
        return parent[x]=find(parent[x]); // recursion to find the root
    }


    public static void kruskal(){	// function for kruskal algorithm


        for(int j=0;j<100000;j++){  // initializing the nodes with root to itself
            parent[j]=j;
        }
        for(int j=0;j<E;j++){  // loop for checking every nodes
            now=Edge[j];


            int X=find(now.from); // finding the parent of current edge's starting point
            int Y=find(now.to); // finding the parent of current edge's ending point
            if(X!=Y){ // checking if they are same or not
                System.out.println(now.from +" "+now.to +" "+now.weight); // if not same then we take this edge as MST edge
                parent[X]=Y; // updating parent
            }
        }
    }

    public static void main(String args[]) {

        for(int i=0;i<100000;i++){
            adj[i]=new LinkedList();
        }
        System.out.print("Enter the number of edges: ");
        Scanner input=new Scanner(System.in);
        int m=input.nextInt();
        System.out.print("Enter "+m +" Edges in the format of: source_node dest_node weight. ie: 0 1 5\n");
        graph g=new graph(m);
        int source=0;
        for(int i=0;i<m;i++){
            int from,to,weight;
            from=input.nextInt(); // taking input
            to=input.nextInt(); // taking input
            weight=input.nextInt(); //taking input
            if(i==0) source=from;
            adj[from].add(to);  // building the graph
            adj[to].add(from); // building the graph

            g.Edge[i].from=from;
            g.Edge[i].to=to;
            g.Edge[i].weight=weight;

        }
        System.out.print("The order of nodes in DFS Traversal is: ");
        dfs(source);  // calling dfs function
        System.out.println();


        for(int i=0;i<100000;i++){  // setting visited values to false for BFS
            visited[i]=false;
        }


        System.out.print("The order of nodes in BFS Traversal is: ");
        bfs(source); // calling BFS from source
        System.out.println();


        System.out.println("The Edges of MST are: ");
        Arrays.sort(Edge); // sorting the edges increasingly by their weight
        kruskal(); // calling kruskal function



    }

}



