/*

*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static StringBuilder out = new StringBuilder();
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[N+1];
        int[] pick = new int[M];
        backTracking(N, M, visited, pick, 0,0);
        System.out.println(out.toString());
    }

    private static void backTracking(int N,int M,boolean[] visited,int[] pick,int depth,int prev){
        if(depth==M){
            for(int i=0;i<M;i++)out.append(pick[i]).append(" ");
            out.append("\n");
            return;
        }

        for(int i=1;i<=N;i++){
            if(visited[i]==true)continue;
            if(prev >i)continue;
            visited[i]=true;
            pick[depth]=i;
            backTracking(N, M, visited, pick, depth+1,i);
            visited[i]=false;
        }
    }
}