/*
 * 문제 요약
 *     - n x n 크기의 체스판에 n개의 퀸을 서로 공격하지 않도록 배치하는 경우의 수를 구하라.
 *     - 퀸은 가로, 세로, 대각선(↘, ↙) 방향으로 이동 가능하다.
 * 
 * 입력
 *     - int n: 체스판의 크기 (1 ≤ n ≤ 12)
 * 
 * 출력
 *     - n개의 퀸이 서로 공격하지 않도록 배치할 수 있는 경우의 수 (int)
 * 
 * 핵심 포인트
 *     - 한 행에 하나의 퀸만 놓을 수 있으므로 각 행마다 퀸의 열 위치만 결정하면 된다.
 *     - 열, ↘ 대각선(row + col), ↙ 대각선(row - col + n - 1)에 대해 사용 여부를 추적
 *     - DFS + 백트래킹으로 모든 유효한 배치 경우를 탐색
 *     - 재귀마다 상태를 복원(backtrack)하여 다음 경우의 수 탐색
 */

class Solution {
    
    public int solution(int n) {
        return patternCount(0, n, new boolean[n], new boolean[n * 2 - 1], new boolean[n * 2 - 1]);
    }

    // row번째 행에 퀸을 배치하고 다음 행으로 넘어가는 재귀 탐색
    public int patternCount(int row, int n, boolean[] colUsed, boolean[] diag1, boolean[] diag2) {
        if (row == n) return 1;  // 모든 행에 퀸 배치 완료 → 유효한 경우 1 반환

        int cnt = 0;

        for (int col = 0; col < n; col++) {
            // 열이나 대각선에 이미 퀸이 있다면 건너뜀
            if (colUsed[col] || diag1[row + col] || diag2[row - col + n - 1]) continue;

            // 현재 위치에 퀸 배치
            colUsed[col] = diag1[row + col] = diag2[row - col + n - 1] = true;

            // 다음 행으로 재귀 호출
            cnt += patternCount(row + 1, n, colUsed, diag1, diag2);

            // 상태 복원 (backtracking)
            colUsed[col] = diag1[row + col] = diag2[row - col + n - 1] = false;
        }

        return cnt;
    }
}
