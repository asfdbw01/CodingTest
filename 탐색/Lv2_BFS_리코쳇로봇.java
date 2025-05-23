/*
 * 문제 요약
 *     - 격자 보드에서 로봇이 미끄러지며 움직일 수 있는 보드게임
 *     - 시작 위치 'R'에서 목표 위치 'G'까지 도달하는 최소 이동 횟수를 구함
 *     - 로봇은 한 방향으로 장애물이나 벽을 만날 때까지 계속 이동 (이를 한 번의 이동으로 간주)
 *
 * 입력
 *     - String[] board : 보드 상태를 나타내는 문자열 배열
 *       ('.' = 빈 칸, 'D' = 장애물, 'R' = 시작 위치, 'G' = 목표 위치)
 *       board.length, board[i].length는 3 이상 100 이하
 *
 * 출력
 *     - int : 목표 위치까지의 최소 이동 횟수 (도달 불가 시 -1)
 *
 * 핵심 포인트
 *     - 일반적인 BFS가 아니라 "슬라이딩 이동"이 핵심
 *     - 이동마다 끝점까지 미끄러지며, visited 체크는 도착 지점 기준
 *     - 최소 횟수로 도달이므로 BFS 사용
 */

class Solution {

    // 상, 우, 하, 좌 방향
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    public int solution(String[] board) {
        int[] r = null, g = null;

        // 시작점 R과 도착점 G 위치 파악
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                char c = board[i].charAt(j);
                if (c == 'R') r = new int[] {i, j, 0};  // 위치 + 이동 횟수
                else if (c == 'G') g = new int[] {i, j};
            }
        }

        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[board.length][board[0].length()];

        que.add(r); // 시작 위치 큐에 삽입

        while (!que.isEmpty()) {
            int[] loc = que.poll();
            visited[loc[0]][loc[1]] = true;

            // 4방향으로 미끄러지기
            for (int[] dir : dirs) {
                int[] next = move(loc, dir, board); // 끝까지 이동한 위치

                // 이미 방문한 위치는 스킵
                if (visited[next[0]][next[1]]) continue;

                // 목표 위치 도달 시 정답 반환
                if (next[0] == g[0] && next[1] == g[1]) return next[2];

                que.add(next); // 큐에 다음 위치 추가
            }
        }

        // 도달 불가능한 경우
        return -1;
    }

    /**
     * 주어진 방향으로 장애물/벽을 만날 때까지 이동하는 함수
     * @param loc 현재 위치 및 이동 횟수 [x, y, count]
     * @param dir 이동 방향 벡터
     * @param board 보드 상태
     * @return 이동한 도착 위치 및 이동 횟수 증가된 배열
     */
    public int[] move(int[] loc, int[] dir, String[] board) {
        int x = loc[0];
        int y = loc[1];
        int count = loc[2];

        int xLen = board.length;
        int yLen = board[0].length();

        // 한 방향으로 미끄러지며 이동
        while (true) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            // 벽 또는 장애물 만나면 중단
            if (nx < 0 || nx >= xLen) break;
            if (ny < 0 || ny >= yLen) break;
            if (board[nx].charAt(ny) == 'D') break;

            // 이동 가능 시 위치 갱신
            x = nx;
            y = ny;
        }

        return new int[] {x, y, count + 1}; // 도착 위치 + 이동 횟수 1 증가
    }
}
