/*
문제
수빈이는 동생과 숨바꼭질을 하고 있다. 
수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 
수빈이는 걷거나 순간이동을 할 수 있다. 만약, 
수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 
순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.

수빈이와 동생의 위치가 주어졌을 때, 
수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

입력
첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

출력
수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[1000001];
        int result = bfs(N, K,visited );
        System.out.println(result);
    }

    private static int bfs(int start,int target, boolean[] visited ){
        
        Queue<Integer> q = new LinkedList();
        int cnt =0;
        q.offer(start);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int cur = q.poll();
                if(cur==target)return cnt;
                nextSearch(cur, q,visited );
            }
            cnt++;
        }
        return cnt;
    }

    private static void nextSearch(int cur,Queue<Integer> q, boolean[] visited ){
        if(!outOfRange(cur+1)&&!visited[cur+1]){q.offer(cur+1);visited[cur+1]=true;}
        if(!outOfRange(cur-1)&&!visited[cur-1]){q.offer(cur-1);visited[cur-1]=true;}
        if(!outOfRange(cur*2)&&!visited[cur*2]){q.offer(cur*2);visited[cur*2]=true;}
    }

    private static boolean outOfRange(int loc){
        return !(loc >=0 && loc <= 100000);
    }
}