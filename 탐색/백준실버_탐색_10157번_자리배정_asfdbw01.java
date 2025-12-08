/*
어떤 공연장에는 가로로 C개, 세로로 R개의 좌석이 C×R격자형으로 배치되어 있다. 
각 좌석의 번호는 해당 격자의 좌표 (x,y)로 표시된다. 

예를 들어보자. 아래 그림은 가로 7개, 세로 6개 좌석으로 구성된 7×6격자형 좌석배치를 보여주고 있다.
그림에서 각 단위 사각형은 개별 좌석을 나타내며, 
그 안에 표시된 값 (x,y)는 해당 좌석의 번호를 나타낸다. 
가장 왼쪽 아래의 좌석번호는 (1,1)이며, 가장 오른쪽 위 좌석의 번호는 (7,6)이다. 
이 공연장에 입장하기 위하여 많은 사람이 대기줄에 서있다. 기다리고 있는 사람들은 제일 앞에서부터 
1, 2, 3, 4, . 순으로 대기번호표를 받았다. 
우리는 대기번호를 가진 사람들에 대하여 (1,1)위치 좌석부터 시작하여 
시계방향으로 돌아가면서 비어있는 좌석에 관객을 순서대로 배정한다.

여러분은 공연장의 크기를 나타내는 자연수 C와 R이 주어져 있을 때, 
대기 순서가 K인 관객에게 배정될 좌석 번호 (x,y)를 찾는 프로그램을 작성해야 한다. 
*/
import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static boolean[][] hall;
    static int[] dx ={0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C =  Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        st = st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        hall = new boolean[C][R];
        if(K > C*R){
            System.out.println(0);
            return;
        }
        dfs(C, R, K, 0, 0, 0, 1);
    }

    static void dfs(int C,int R,int K,int x,int y,int direction,int depth){
        hall[x][y] = true;
        if(depth==K){
            System.out.println((x+1)+" "+(y+1));
            return;
        }
        int nx = x+dx[direction];
        int ny = y+dy[direction];
        if(nx<0 || nx>=C || ny <0 || ny >= R || hall[nx][ny]==true){
            direction = (direction+1)%4;
            nx = x+dx[direction];
            ny = y+dy[direction];
        }
        dfs(C, R, K, nx, ny, direction, depth+1);
    }

}