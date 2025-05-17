/*
 * 문제 요약
 *     - 머쓱이는 항상 같은 위치에서 공을 쳐서 목표 공을 맞추는 "원쿠션 연습"을 하고 있음
 *     - 벽에 반드시 한 번 부딪힌 후 목표 공에 도달해야 하며, 이때 굴러간 거리의 최솟값의 제곱을 구해야 함
 *
 * 입력
 *     - m, n: 당구대의 가로/세로 길이 (3 ≤ m, n ≤ 1,000)
 *     - startX, startY: 머쓱이 공의 시작 위치 (0 < startX < m, 0 < startY < n)
 *     - balls: 맞춰야 할 목표 공들의 좌표 배열, 각 원소는 [a, b]
 *
 * 출력
 *     - 각 목표 공까지의 최소 이동 거리의 제곱을 배열로 반환
 *
 * 핵심 포인트
 *     - 목표 공을 상하좌우 4방향으로 반사시켜 머쓱이 공에서의 거리 계산
 *     - 목표 공을 정통으로 통과하는 경로는 제외해야 하므로 조건문으로 필터링
 *     - 정수 제곱 연산으로 정확한 거리 제곱 계산
 */

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {
            answer[i] = Integer.MAX_VALUE;

            int ballX = balls[i][0];
            int ballY = balls[i][1];

            // 목표 공을 상하좌우로 반사한 좌표를 구함
            int[][] reflections = {
                {ballX, ballY + (n - ballY) * 2},   // 상
                {ballX + (m - ballX) * 2, ballY},   // 우
                {ballX, -ballY},                    // 하
                {-ballX, ballY}                     // 좌
            };

            for (int[] reflection : reflections) {
                int rx = reflection[0];
                int ry = reflection[1];

                // 수직선상에서 정통으로 통과하는 경우 제거
                if (startX == ballX && rx == ballX) {
                    if (startY < ballY && ry > ballY) continue;
                    if (startY > ballY && ry < ballY) continue;
                }

                // 수평선상에서 정통으로 통과하는 경우 제거
                if (startY == ballY && ry == ballY) {
                    if (startX < ballX && rx > ballX) continue;
                    if (startX > ballX && rx < ballX) continue;
                }

                // 거리의 제곱 계산 (정수 연산)
                int dx = startX - rx;
                int dy = startY - ry;
                int distance = dx * dx + dy * dy;

                answer[i] = Math.min(answer[i], distance);
            }
        }

        return answer;
    }
}
