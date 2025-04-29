/**
 * 문제 설명
 * - 1x1 미로에서 출발(S) → 레버(L) → 출구(E)까지 최단거리 찾는 문제
 * - 레버를 반드시 당기고 출구로 나가야 함
 * - 이동은 상하좌우 4방향, 벽(X)은 통과 불가
 * 
 * 알고리즘
 * - 시작 -> 레버까지 BFS
 * - 레버 -> 출구까지 BFS
 * - 둘 다 성공하면 거리 합, 하나라도 실패하면 -1
 */

import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] maps) {
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length();
        
        int[][] map = new int[n][m];
        int[] start = new int[2];
        int[] lever = new int[2];
        int[] exit = new int[2];

        // 맵 변환: 공백 0, 벽 -1, 시작 1, 레버 2, 출구 3
        for (int i = 0; i < n; i++) {
            char[] row = maps[i].toCharArray();
            for (int j = 0; j < m; j++) {
                if (row[j] == 'S') {
                    map[i][j] = 1;
                    start = new int[]{i, j};
                } else if (row[j] == 'L') {
                    map[i][j] = 2;
                    lever = new int[]{i, j};
                } else if (row[j] == 'E') {
                    map[i][j] = 3;
                    exit = new int[]{i, j};
                } else if (row[j] == 'O') {
                    map[i][j] = 0;
                } else { // 'X'
                    map[i][j] = -1;
                }
            }
        }
        // 1. 시작 → 레버까지 거리
        int toLever = bfs(map, start, lever, n, m);
        if (toLever == -1) return -1;
        
        // 2. 레버 → 출구까지 거리
        int toExit = bfs(map, lever, exit, n, m);
        if (toExit == -1) return -1;
        
        // 둘 다 성공했으면 거리 합 반환
        return toLever + toExit;
    }
    
    private int bfs(int[][] map, int[] start, int[] target, int n, int m) {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[]{start[0], start[1], 0}); // (x, y, 이동거리)
        visited[start[0]][start[1]] = true;
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int dist = now[2];
            
            System.out.println("현재 위치: (" + x + ", " + y + ")"+dist);
            
            if (x == target[0] && y == target[1]) {
                return dist;
            }
            // 4방향으로 탐색
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;// 범위 체크
                if (map[nx][ny] == -1) continue; // 벽
                if (visited[nx][ny]) continue;// 방문했으면 건너뛰기
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny, dist + 1});
            }
        }
        return -1;
    }
}
