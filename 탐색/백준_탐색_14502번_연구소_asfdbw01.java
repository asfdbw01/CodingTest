/*
문제
인체에 치명적인 바이러스를 연구하던 연구소에서 바이러스가 유출되었다. 
다행히 바이러스는 아직 퍼지지 않았고, 바이러스의 확산을 막기 위해서 연구소에 벽을 세우려고 한다.

연구소는 크기가 N×M인 직사각형으로 나타낼 수 있으며, 
직사각형은 1×1 크기의 정사각형으로 나누어져 있다. 
연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다. 

일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다. 
새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.

입력
첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)

둘째 줄부터 N개의 줄에 지도의 모양이 주어진다. 
0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다. 2의 개수는 2보다 크거나 같고, 
10보다 작거나 같은 자연수이다.

빈 칸의 개수는 3개 이상이다.

출력
첫째 줄에 얻을 수 있는 안전 영역의 최대 크기를 출력한다.
*/


import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static int[][] labo;
    static List<int[]> startVirus = new ArrayList<>();
    static List<int[]> empty = new ArrayList<>();
    static int[] dx = new int[]{0,-1,0,1};
    static int[] dy = new int[]{1,0,-1,0};
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        makelabo(N, M, br);
        System.out.println(returnAnswer());
    }

    private static int returnAnswer(){
        int best=-1;
        int E = empty.size();
        for(int a=0;a<E-2;a++){
            for(int b=a+1;b<E-1;b++){
                for(int c=b+1;c<E;c++){
                    int[][] map = copyLabo();
                    
                    //벽세우기
                    int[] w1 = empty.get(a);
                    int[] w2 = empty.get(b);
                    int[] w3 = empty.get(c);
                    map[w1[0]][w1[1]] = 1;
                    map[w2[0]][w2[1]] = 1;
                    map[w3[0]][w3[1]] = 1;
                    
                    bfs(map);
                    //count
                    int cnt = countSafeErea(map);
                    best = Math.max(cnt, best);
                }
            }
        }
        return best;
    }

    private static int countSafeErea(int[][] map){
        int safe=0;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(map[i][j]==0)safe++;
            }
        }
        return safe;
    }

    private static void bfs(int[][] map){
        int N=map.length,M=map[0].length;
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();
        for(int[] v : startVirus)q.add(v);
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            visited[cur[0]][cur[1]] = true;
            for(int i=0;i<4;i++){
                int nx = cur[0]+dx[i];
                int ny = cur[1]+dy[i];
                if(nx<0 || nx>=N || ny<0 || ny>=M)continue;
                else if(visited[nx][ny]==true)continue;
                if(map[nx][ny]==0){
                    q.offer(new int[]{nx,ny});
                    map[nx][ny]=2;
                }
            }
        }
    }

    private static int[][] copyLabo(){
        int N = labo.length;
        int M = labo[0].length;
        int[][] copy = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                copy[i][j] = labo[i][j];
            }
        }
        return copy;
    }

    private static void makelabo(int N,int M,BufferedReader br ) throws IOException{
        labo = new int[N][M];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                int state = Integer.parseInt(st.nextToken());
                labo[i][j] = state;
                if(state == 0) empty.add(new int[]{i,j});
                else if(state==2)startVirus.add(new int[]{i,j});
            }
        }
    }
}