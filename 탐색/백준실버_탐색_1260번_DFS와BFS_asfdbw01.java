/*
문제
그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 
단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 
더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

입력
첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 
탐색을 시작할 정점의 번호 V가 주어진다. 
다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 
어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.

출력
첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. 
V부터 방문된 점을 순서대로 출력하면 된다.
*/


import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static List<List<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        makegraph(N, M, br);
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[N+1];
        dfs(visited,sb , V);
        System.out.println(sb.toString());

        visited = new boolean[N+1];
        sb = new StringBuilder();
        bfs(visited, sb, V);
        System.out.println(sb.toString());
    }

    private static void bfs(boolean[] visited,StringBuilder sb,int idx){
        Queue<Integer> q = new LinkedList<>();
        visited[idx] = true;
        q.add(idx);
        
        while(!q.isEmpty()){
            int n = q.poll();
            sb.append(n).append(" ");
            List<Integer> nodes = graph.get(n);
            for(int node : nodes){
                if(visited[node]==false){
                    visited[node] = true;
                    q.add(node);
                }
            }
        }
    }

    private static void dfs(boolean[] visited,StringBuilder sb,int idx){
        visited[idx] = true;
        sb.append(idx+" ");
        for(int node : graph.get(idx)){
            if(visited[node]==false){
                dfs(visited, sb, node);
            }
        }
    }

    private static void makegraph(int N,int M,BufferedReader br)throws IOException {
        for(int i=0;i<=N;i++)graph.add(new ArrayList<>());

        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            graph.get(e).add(v);
            graph.get(v).add(e);
        }

        for(int i=0;i<=N;i++)Collections.sort(graph.get(i));
    }
}