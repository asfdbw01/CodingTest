/*
문제
방향 없는 그래프가 주어졌을 때, 
연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. 
(1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. 
(1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.

출력
첫째 줄에 연결 요소의 개수를 출력한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        makeGraph(N, M, br);
        System.out.println(returnConnectedComponent(N));
    }

    private static int returnConnectedComponent(int N){
        int cnt=0;
        for(int i=1;i<=N;i++){
            if(visited[i]==false){
                dfs(i);
                cnt++;
            }
        }
        return cnt;
    }

    private static void dfs(int node){
        visited[node] = true;
        for(int i : graph.get(node)){
            if(visited[i]==false)dfs(i);
        }
    }

    private static void makeGraph(int N,int M, BufferedReader br) throws IOException{
        visited = new boolean[N+1];
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(e).add(v);
            graph.get(v).add(e);
        }
    }
}