/*
 * 문제 요약
 *     - n x m 격자 미로에서 출발점 (x, y)에서 도착점 (r, c)까지 정확히 k번 이동하여 도달하는 경로를 찾아야 함
 *     - 이동은 상하좌우 한 칸씩 가능하고, 같은 칸을 여러 번 방문해도 무방함
 *     - 이동 경로를 문자열로 나타냈을 때 사전순으로 가장 빠른 경로를 구해야 함
 *     - 이동 가능하지 않은 경우 "impossible"을 반환
 * 
 * 입력
 *     - n, m: 격자 크기 (2 ≤ n, m ≤ 50)
 *     - x, y: 출발 좌표 (1-based)
 *     - r, c: 도착 좌표
 *     - k: 정확히 이동해야 하는 횟수 (1 ≤ k ≤ 2500)
 * 
 * 출력
 *     - 사전순으로 가장 빠른 이동 경로 (정확히 k번 이동하여 도착점 도달)
 *     - 불가능한 경우 "impossible"
 * 
 * 핵심 포인트
 *     - DFS를 사용하여 경로를 탐색하되, 방향 순서를 'd → l → r → u'로 설정하면 사전순 우선 탐색 가능
 *     - 백트래킹 시 가지치기를 통해 효율적 탐색: 현재 위치에서 도착지까지의 거리 > 남은 이동수인 경우 return
 *     - (남은 이동수 - 도착지까지의 거리) % 2 != 0인 경우 도달 불가능 (홀짝성 조건) → return
 *     - StringBuilder를 활용해 성능 최적화 및 경로 구성
 */

class Solution {

    int[] dx = {1, 0, 0, -1};          // d, l, r, u
    int[] dy = {0, -1, 1, 0};
    char[] dir = {'d', 'l', 'r', 'u'};

    int n, m, r, c, k;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.k = k;

        StringBuilder sb = new StringBuilder();

        return dfs(x, y, sb) ? sb.toString() : "impossible";
    }

    // DFS로 사전순 경로를 탐색하며 유효한 경로를 찾으면 true 반환
    public boolean dfs(int x, int y, StringBuilder sb) {
        if (sb.length() == k) {
            return x == r && y == c;
        }

        int remain = k - sb.length();
        int distance = Math.abs(x - r) + Math.abs(y - c);

        // 가지치기: 남은 거리보다 많이 이동해야 하거나, 도달 가능성이 없는 경우
        if (remain < distance || (remain - distance) % 2 != 0) return false;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 1 || nx > n || ny < 1 || ny > m) continue;

            sb.append(dir[i]);
            if (dfs(nx, ny, sb)) return true;
            sb.deleteCharAt(sb.length() - 1);  // 백트래킹
        }

        return false;
    }
}
