/*
문제
재난방재청에서는 많은 비가 내리는 장마철에 대비해서 다음과 같은 일을 계획하고 있다. 
먼저 어떤 지역의 높이 정보를 파악한다.그 다음에 그 지역에 많은 비가 내렸을 때 
물에 잠기지 않는 안전한 영역이 최대로 몇 개가 만들어 지는 지를 조사하려고 한다. 
이때, 문제를 간단하게 하기 위하여, 
장마철에 내리는 비의 양에 따라 일정한 높이 이하의 모든 지점은 물에 잠긴다고 가정한다.

어떤 지역의 높이 정보는 행과 열의 크기가 각각 N인 2차원 배열 형태로 주어지며 
배열의 각 원소는 해당 지점의 높이를 표시하는 자연수이다.

입력
첫째 줄에는 어떤 지역을 나타내는 2차원 배열의 행과 열의 개수를 나타내는 수 N이 입력된다. 
N은 2 이상 100 이하의 정수이다. 
둘째 줄부터 N개의 각 줄에는 2차원 배열의 첫 번째 행부터 N번째 행까지 순서대로 
한 행씩 높이 정보가 입력된다. 
각 줄에는 각 행의 첫 번째 열부터 N번째 열까지 N개의 높이 정보를 나타내는 자연수가 
빈 칸을 사이에 두고 입력된다. 높이는 1이상 100 이하의 정수이다.

출력
첫째 줄에 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수를 출력한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0,-1,0,1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int maxHight = makeMapAndReturnMaxHight(N, br);
        System.out.println(findMaxNoneFloodedArea(N, maxHight));
    }

    private static int findMaxNoneFloodedArea(int N,int maxHight){
        int maxArea = 0;
        for(int i=0;i<maxHight;i++){
            visited = new boolean[N][N];
            int floodedArea = findFloodedArea(N, i);
            maxArea = maxArea>floodedArea?maxArea:floodedArea;
        }
        return maxArea;
    }

    private static int findFloodedArea(int N, int flooded){
        int area =0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(visited[i][j]==false && map[i][j]>flooded){
                    dfs(N, i, j, flooded);
                    area++;
                }
            }
        }
        return area;
    }

    private static void dfs(int N ,int x,int y,int flooded){
        visited[x][y] = true;
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx <0 || nx >= N || ny <0 || ny >= N)continue;
            if(visited[nx][ny]==true)continue;
            if(map[nx][ny] <= flooded)continue;
            dfs(N, nx, ny, flooded);
        }
    }

    private static int makeMapAndReturnMaxHight(int N, BufferedReader br)  throws IOException{
        int maxHight = -1;
        map = new int[N][N];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int hight = Integer.parseInt(st.nextToken());
                map[i][j] = hight;
                maxHight = maxHight>hight?maxHight:hight;
            }
        }
        return maxHight;
    }
}