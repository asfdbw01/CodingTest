/*
 * 문제 요약
 *     - n x n 격자에 시계가 있고, 각 시계는 12, 3, 6, 9시 방향 중 하나를 가리킨다 (0~3).
 *     - 하나의 시계를 회전시키면 자신과 상하좌우 인접한 시계가 모두 함께 시계방향으로 90도 회전한다.
 *     - 각 시계를 몇 번 돌려서 모든 시계를 12시(0)로 맞춰야 하며, 최소 회전 횟수를 구하는 문제.
 * 
 * 입력
 *     - int[][] clockHands : 2 ≤ clockHands.length ≤ 8
 *     - clockHands[i][j]의 값은 0 이상 3 이하 (시계 방향 각도 / 90도 단위)
 * 
 * 출력
 *     - 모든 시계를 12시로 맞추기 위한 최소 회전 횟수 (int)
 * 
 * 핵심 포인트
 *     - 첫 줄의 회전 조합만 완전탐색(4^n), 이후 줄은 상단 시계에 따라 유도
 *     - 회전 시 영향을 받는 5칸(자기 자신 + 상하좌우)을 정확히 갱신
 *     - 반복 시마다 회전 상태를 재사용 가능한 배열로 유지하며, 조기 탈출 최적화 수행
 */

class Solution {

	int[] dx = {0, -1, 1, 0, 0};
	int[] dy = {0, 0, 0, -1, 1};

	public int solution(int[][] clockHands) {
		int min = 200;
		int n = clockHands.length;
		int[][] spinMap = new int[n][n];  // 각 칸의 누적 회전 상태 기록

		// 첫 줄 회전 조합을 비트마스크로 완전탐색 (2비트 * n칸 = 4^n)
		Outter : for (int spins = 0; spins < (1 << (2 * n)); spins++) {
			Arrays.stream(spinMap).forEach(row -> Arrays.fill(row, 0));
			int sum = 0;

			// 첫 줄은 회전 조합으로, 나머지 줄은 위쪽 시계를 12시로 맞추기 위한 회전 유도
			for (int row = 0; row < n; row++) {
				for (int col = 0; col < n; col++) {
					int spin = row == 0 ? (spins >> (col * 2)) & 3 : (4 - ((clockHands[row - 1][col] + spinMap[row - 1][col]) & 3)) & 3;

					// 회전 시 자신과 인접 시계에 영향 적용
					for (int i = 0; i < 5; i++) {
						int nx = row + dx[i];
						int ny = col + dy[i];
						if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
							spinMap[nx][ny] = (spinMap[nx][ny] + spin) & 3;
						}
					}

					sum += spin;
					if (sum >= min) continue Outter;  // pruning
				}
			}

			// 마지막 줄이 모두 12시 방향인지 확인
			for (int col = 0; col < n; col++) {
				if (((clockHands[n - 1][col] + spinMap[n - 1][col]) & 3) != 0) continue Outter;
			}

			min = Math.min(min, sum);
		}

		return min;
	}
}
