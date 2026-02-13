/*
문제
방향그래프가 주어지면 주어진 시작점에서 
다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오. 
단, 모든 간선의 가중치는 10 이하의 자연수이다.

입력
첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다. 
(1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000) 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다.
둘째 줄에는 시작 정점의 번호 K(1 ≤ K ≤ V)가 주어진다. 
셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다. 
이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다. 
u와 v는 서로 다르며 w는 10 이하의 자연수이다. 
서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.

출력
첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다. 
시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static List<List<Edge>> graph = new ArrayList();
    final static int inf = 1000000000;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int root = Integer.parseInt(br.readLine());
        int[] dist = new int[V+1];
        for(int i=0;i<=V;i++)dist[i] = inf;
        dist[root] = 0;

        makeGraph(V, E, br);
        //findDist(V, E, root, dist,0);
        dijkstra(V, E, root, dist);
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=V;i++){
            if(dist[i]==inf){
                sb.append("INF\n");
                continue;
            }
            sb.append(dist[i]).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dijkstra(int V,int E,int root,int[] dist){
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        pq.offer(new Edge(root,0));

        while(!pq.isEmpty()){
            Edge curr = pq.poll();

            
            if (curr.cost != dist[curr.to]) continue;//가지치기

            for(Edge next: graph.get(curr.to)){
                int nd = dist[curr.to]+next.cost;
                if(nd < dist[next.to]){
                    dist[next.to] = nd;
                    pq.offer(new Edge(next.to,nd));
                }
            }
        }
    }

    static class Edge{
        int to,cost;
        Edge(int to,int cost){this.to=to;this.cost=cost;}
    }

    private static void makeGraph(int V,int E , BufferedReader br) throws IOException{
        for(int i=0;i<=V;i++)graph.add(new ArrayList<>());

        for(int i=0;i<E;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(vertex).add(new Edge(edge, cost));
        }
    }
}