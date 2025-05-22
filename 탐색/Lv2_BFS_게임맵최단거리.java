
/*
문제 요약 - 게임 맵 최단거리

- 캐릭터는 (0,0) 위치에서 시작해, (n-1,m-1) 위치의 적 진영까지 이동해야 함
- 이동은 상하좌우 4방향, 벽(0)은 통과 불가, 길(1)만 이동 가능
- 이동 가능한 칸 중 가장 빠른 경로를 찾아, 지나야 하는 칸 수(거리)를 리턴
- 만약 도착 불가능하면 -1 리턴

제한 사항
- maps 크기: n x m (1 ≤ n, m ≤ 100)
- 시작점: maps[0][0] == 1
- 도착점: maps[n-1][m-1]
- BFS로 최단거리 탐색하는 문제 (가중치 없는 그래프에서 최단거리 = BFS)

입출력 예시
1. maps = [[1,0,1,1,1], ..., [0,0,0,0,1]] → 결과: 11 (최단 경로 존재)
2. maps = [[1,0,1,1,1], ..., [0,0,0,0,1]] → 결과: -1 (길이 막힘)
*/


import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        int[] start = new int[]{0,0};
        int[] end = new int[]{maps.length-1,maps[0].length-1};
        answer = bfs (maps,start,end);
        return answer;
    }
    
    private int bfs (int[][]maps,int []start,int []end){
        int[] dy = {-1,1,0,0};
        int[] dx = {0,0,-1,1};
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        
        queue.add(new int[]{start[0], start[1]});
        visited[start[0]][start[1]] = true;
        
        while(!queue.isEmpty()){
            int now[] = queue.poll();
            int y = now[0];
            int x = now[1];
            for(int d=0;d<4;d++){
                int ny = y+dy[d];
                int nx = x+dx[d];
                
                if(ny<0 || ny>=maps.length || nx<0 || nx>= maps[0].length) continue;
                if(maps[ny][nx] == 0)continue;
                if(visited[ny][nx]==true)continue;
                visited[ny][nx] = true;
                maps[ny][nx] = maps[x][y]+1;
                System.out.println(ny+" "+nx+" "+maps[ny][nx]);
                if(ny==maps.length-1 && nx==maps[0].length-1)return maps[ny][nx];
                queue.add(new int[]{ny,nx});
                
            }
        }
        
        return -1;
    }
}
