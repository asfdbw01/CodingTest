/*
과 같이 정사각형 모양의 지도가 있다. 
1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 
철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다.
여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 
대각선상에 집이 있는 경우는 연결된 것이 아니다. 
지도를 입력하여 단지수를 출력하고, 
각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.

입력
첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 
그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.

출력
첫 번째 줄에는 총 단지수를 출력하시오. 
그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
*/


import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> houseList = new ArrayList<>();
    static int[] dx = {0,-1,0,1};
    static int[] dy = {1,0,-1,0};
    static int N;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        makeMap(N, br);
        findApt();
        Collections.sort(houseList);
        System.out.println(houseList.size());
        for(int i : houseList)System.out.println(i);
    }

    private static void findApt(){
        for(int x=0;x<N;x++){
            for(int y=0;y<N;y++){
                if(map[x][y]==1 && visited[x][y]==false)houseList.add(dfs(x, y));
            }
        }
    }

    private static int dfs(int sx,int sy){
        int x= sx,y=sy;
        visited[x][y] = true;
        int cnt=1;
        for(int i=0;i<4;i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx <0 || nx >=N || ny<0 || ny>= N)continue;
            if(map[nx][ny]!=1)continue;
            if(visited[nx][ny]==true)continue;
            cnt += dfs(nx, ny);
        }
        return cnt;
    }

    private static void makeMap(int N,BufferedReader br  ) throws IOException{
        map = new int[N][N];
        visited = new boolean[N][N];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j=0;j<N;j++){
                map[i][j] = line.charAt(j)-'0';
            }
        }
    }
}