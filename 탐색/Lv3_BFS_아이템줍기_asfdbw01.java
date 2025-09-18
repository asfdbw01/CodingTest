/*
문제: 아이템 줍기 (직사각형 테두리 최단 경로)
------------------------------------------------
- 축에 평행한 여러 직사각형이 겹쳐 만들어진 다각형의 "바깥 테두리" 위만 걸을 수 있다.
- 캐릭터 시작점 (cx, cy)과 아이템 위치 (ix, iy)는 모두 테두리 위의 점.
- 테두리만 따라 이동할 때 아이템까지의 "가장 짧은 거리"를 구하라.

입력
- rectangle: [x1, y1, x2, y2] (좌하단, 우상단) 좌표, 1 ≤ 좌표 ≤ 50
- 1 ≤ #rectangle ≤ 4, 서로 다른 직사각형끼리 x나 y 좌표가 일치하지 않음
- 분리된 지형 없음, 완전 포함 관계 없음
- (cx, cy), (ix, iy)는 테두리 위의 점이며 서로 다름

출력
- 캐릭터가 테두리를 따라 아이템까지 이동하는 최단 거리

핵심 포인트 / 함정
- 겹침/코너 접촉에서 테두리 연결성이 틀어지기 쉬움(코너 브릿지 이슈).
- 안전한 구현 패턴:
  1) 좌표를 전부 2배 스케일(코너 분리)
  2) 모든 직사각형을 면으로 채운 뒤 내부를 지워 테두리만 남김
  3) 테두리(=1)만 4방향으로 BFS → 도착 시 거리/2 반환
- 스케일 없이 라인만 그리면 코너/겹침에서 잘못 연결·단절될 수 있음.

제약 요약
- 좌표: 1..50, 직사각형 ≤ 4, 분리/포함 없음, 시작/목표는 테두리 위
- 시간 제한 여유: 보드 최대 (50*2)^2 수준에서 BFS 가능

정답 판정
- 최단 경로 길이(격자 단위). 스케일을 썼다면 마지막에 /2로 보정.
*/


import java.util.*;

class Solution {
    
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 2배 스케일 사용
        int N = 102; 
        int[][] map = new int[N][N]; // 0:빈칸/내부, 1:테두리, 2:아이템

        
        int[][] rect2 = new int[rectangle.length][4];
        for (int i = 0; i < rectangle.length; i++) {
            rect2[i][0] = rectangle[i][0] * 2;
            rect2[i][1] = rectangle[i][1] * 2;
            rect2[i][2] = rectangle[i][2] * 2;
            rect2[i][3] = rectangle[i][3] * 2;
        }

        
        drawRectangle(map, rect2);

        
        int sx = characterX * 2, sy = characterY * 2;
        int tx = itemX * 2, ty = itemY * 2;
        map[ty][tx] = 2;

        // 테두리(1)만 따라 BFS, 아이템(2) 도달 시 반환
        int dist2x = bfs(map, sx, sy);
        return dist2x / 2; // 스케일 보정
    }

    
    private void drawRectangle(int[][] map, int[][] rectangle){
       
        for (int[] r : rectangle) {
            int x1 = r[0], y1 = r[1], x2 = r[2], y2 = r[3];
            for (int y = y1; y <= y2; y++) {
                for (int x = x1; x <= x2; x++) {
                    map[y][x] = 1;
                }
            }
        }
        
        for (int[] r : rectangle) {
            int x1 = r[0], y1 = r[1], x2 = r[2], y2 = r[3];
            for (int y = y1 + 1; y <= y2 - 1; y++) {
                for (int x = x1 + 1; x <= x2 - 1; x++) {
                    map[y][x] = 0;
                }
            }
        }
    }

    private int bfs(int[][] map, int startX, int startY){
        int n = map.length;
        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, -1);

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{startX, startY});
        dist[startY][startX] = 0;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (map[ny][nx] != 1 && map[ny][nx] != 2) continue;
                if (dist[ny][nx] != -1) continue;

                dist[ny][nx] = dist[y][x] + 1;
                if (map[ny][nx] == 2) return dist[ny][nx];
                q.offer(new int[]{nx, ny});
            }
        }
        return 0; 
    }
}
