/*
 * 문제 요약
 *     - 보드판에서 특정 칸 (h, w)의 상하좌우 인접한 칸 중 같은 색으로 칠해진 칸의 개수를 구함
 *
 * 입력
 *     - board: 색깔이 문자열로 저장된 정사각형 2차원 배열 (크기: n x n, 1 ≤ n ≤ 7)
 *     - h, w: 기준이 되는 좌표 (0 ≤ h, w < n)
 *
 * 출력
 *     - 인접한 같은 색 칸의 개수 (정수)
 *
 * 핵심 포인트
 *     - 상하좌우 방향 벡터를 사용한 탐색
 *     - 배열 경계 조건 체크
 *     - 문자열 비교는 equals() 사용
 */

class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        int len = board.length;
        String color = board[h][w]; // 기준 칸의 색상 저장

        // 상, 하, 좌, 우 방향 벡터
        int[][] dirs = {
            {-1, 0}, // 상
            {1, 0},  // 하
            {0, -1}, // 좌
            {0, 1}   // 우
        };

        // 각 방향을 순회하며 인접한 칸 탐색
        for (int[] dir : dirs) {
            int nx = h + dir[0]; // 다음 행 좌표
            int ny = w + dir[1]; // 다음 열 좌표

            // 배열 범위 체크 + 같은 색상인지 확인
            if (0 <= nx && nx < len &&
                0 <= ny && ny < len &&
                board[nx][ny].equals(color)) {
                answer++; // 같은 색이면 카운트 증가
            }
        }

        return answer;
    }
}
