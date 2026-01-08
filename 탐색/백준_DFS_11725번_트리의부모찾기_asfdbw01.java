/*
문제
루트 없는 트리가 주어진다. 
이때, 트리의 루트를 1이라고 정했을 때, 
각 노드의 부모를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다. 
둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.

출력
첫째 줄부터 N-1개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static List<List<Integer>> tree = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        makeTree(br, N);
        int[] parent = new int[N+1];
        dfs(1,1, parent);
        StringBuilder sb = new StringBuilder();
        for(int i=2;i<parent.length;i++){
            sb.append(parent[i]).append("\n");
        }
        System.out.println(sb.toString());
    }


    private static void dfs(int cur,int prev,int[] parent ){
        for(int next:tree.get(cur)){
            if(next == prev) continue;
            parent[next] = cur;
            dfs(next,cur, parent);
        }
    }


    private static void makeTree(BufferedReader br ,int N) throws IOException{
        for(int i=0;i<=N;i++){
            tree.add(new ArrayList<>());
        }

        for(int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(e).add(v);
            tree.get(v).add(e);
        }
    }
}