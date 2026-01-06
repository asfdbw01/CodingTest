/*
적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다. 
따라서, 적록색약인 사람이 보는 그림은 아닌 사람이 보는 그림과는 좀 다를 수 있다.

크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다. 
그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다. 
또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다. 
(색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다)

그림이 입력으로 주어졌을 때, 
적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N이 주어진다. (1 ≤ N ≤ 100)

둘째 줄부터 N개 줄에는 그림이 주어진다.

출력
적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을
때의 구역의 수를 공백으로 구분해 출력한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static char[][] cBoard;
    static boolean[][] cVisited;
    static char[][] clBoard;
    static boolean[][] clVisited;
    final static int[] dx = {0,-1,0,1};
    final static int[] dy = {1,0,-1,0}; 
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        makeBoard(N, br);
        StringBuilder sb = new StringBuilder();
        int cArea=0;
        int clArea=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(cVisited[i][j]==false){
                    dfs(cBoard, cVisited, i, j, cBoard[i][j]);
                    cArea++;
                }
                if(clVisited[i][j]==false){
                    dfs(clBoard, clVisited, i, j, clBoard[i][j]);
                    clArea++;
                }
            }
        }
        sb.append(cArea).append(" ").append(clArea);
        System.out.println(sb.toString());
    }

    private static void dfs(char[][] board, boolean[][] visited,int x,int y,char target){
        visited[x][y]=true;
        int N = board.length;
        for(int i=0;i<4;i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx <0 || nx >=N || ny <0 || ny >= N)continue;
            if(visited[nx][ny]==true)continue;
            if(board[nx][ny]!=target)continue;
            dfs(board, visited, nx, ny, target);
        }
    }

    private static void makeBoard(int N,BufferedReader br) throws IOException{
        cBoard = new char[N][N];
        cVisited = new boolean[N][N];
        clBoard = new char[N][N];
        clVisited = new boolean[N][N];

        for(int i=0;i<N;i++){
            String line = br.readLine();
            for(int j=0;j<N;j++){
                char c = line.charAt(j);
                cBoard[i][j] = c;
                clBoard[i][j] = c=='B'?'B':'R';
            }
        }
    }
}