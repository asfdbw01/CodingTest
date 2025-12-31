/*

*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static int[][] board;
    static int[] dx = {0,-1,0,1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        makeBoard(br, N, M);
        System.out.println(bfs(N, M, 0, 0));
    }

    private static int bfs(int N,int M,int sx,int sy){
        int cnt=1;
        boolean[][] visited = new boolean[N][M];
        visited[sx][sy]=true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx,sy});

        while (!q.isEmpty()) {
            int size = q.size();
            for(int i=0;i<size;i++){
                int[] cur = q.poll();
                int x = cur[0],y = cur[1];
                if(x==N-1 && y==M-1)return cnt;
                for(int j=0;j<4;j++){
                    int nx = x+dx[j];
                    int ny = y + dy[j];
                    if(nx <0 || nx >= N || ny <0 || ny>= M)continue;
                    if(visited[nx][ny])continue;
                    if(board[nx][ny]!=1)continue;
                    q.offer(new int[]{nx,ny});
                    visited[nx][ny]=true;
                }
            }
            cnt++;
        }

        return -1;
    }

    private static void makeBoard(BufferedReader br,int N,int M) throws IOException {
        board = new int[N][M];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j=0;j<M;j++){
                board[i][j] = line.charAt(j)-'0';
            }
        }
    }
}