/**
 * [카카오 인턴] 경주로 건설
 *
 * 문제 설명:
 * - 0과 1로 이루어진 N x N 도면에서 (0,0) → (N-1,N-1)까지 도로를 건설한다.
 * - 0은 빈 칸, 1은 벽이며 벽에는 도로를 지을 수 없다.
 * - 직선 도로는 100원, 코너(방향 전환)는 500원이 추가된다.
 * - 최소 비용으로 목적지까지 도달하는 경로의 비용을 구하라.
 *
 * 제한 사항:
 * - board는 3 <= N <= 25인 정사각 배열
 * - board[i][j]는 0 또는 1 (0은 도로 건설 가능, 1은 불가능)
 * - 출발지와 도착지는 항상 0
 * - 항상 도착점까지 도달 가능한 보드만 주어진다
 *
 * 풀이 요약:
 * - 방향별 비용이 다르므로 BFS가 아닌 다익스트라(Dijkstra) 사용
 * - 상태(Node): 좌표(y,x), 방향(dir), 누적 비용(cost)
 * - dist[y][x][dir]로 방향별 최소 비용을 저장
 * - 시작점에서는 아래(dir=1), 오른쪽(dir=3)만 가능 (초기 비용 100)
 * - 이동할 때 같은 방향이면 +100, 방향 바뀌면 +600 (코너 비용 포함)
 * - 도착지에 가장 먼저 도달한 경우가 최소 비용임 → 바로 return
 */
import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        
        answer = dijkstra(board);
        return answer;
    }
    static class Node implements Comparable<Node>{
        int y,x,dir,cost;
        
        Node(int y,int x,int dir, int cost){
            this.y = y;
            this.x=x;
            this.dir = dir;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node other){
            return this.cost - other.cost;
        }
    }
    
    private int dijkstra(int[][] board){
        
        //상하 좌우
        int dy[] = {-1,1,0,0};
        int dx[] = {0,0,-1,1};
        
        int[][][] dist = new int[board.length][board.length][4];  //각 좌표별 비용
        for (int[][] d : dist) for (int[] row : d) Arrays.fill(row, Integer.MAX_VALUE);//초기화
        PriorityQueue<Node> pq = new PriorityQueue<>();
        if(board[0][1]==0) pq.offer(new Node(0, 1, 3, 100)); // 오른쪽 (dir=3)
        if(board[1][0]==0) pq.offer(new Node(1,0,1,100)); //아래 (dir= 1)
        
        
        while(!pq.isEmpty()){
            Node curr = pq.poll();
            if(curr.y==board.length-1 && curr.x == board[0].length-1)return curr.cost;
            
            for(int i=0;i<4;i++){
                int ny = curr.y+dy[i];
                int nx = curr.x+dx[i];
                
                if(ny<0 || ny>=board.length || nx <0 || nx>= board[0].length)continue;
                if(board[ny][nx] ==1)continue;
                int cost = (i==curr.dir)?curr.cost+100 : curr.cost+600;
                
                if (dist[ny][nx][i] > cost) {
                    dist[ny][nx][i] = cost;
                    pq.offer(new Node(ny, nx, i, cost));
                }
            }
        }
        return -1;
    }
}
