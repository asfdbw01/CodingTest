/*
 * 문제 요약
 *     - n x n 격자에 시계가 있고, 시계마다 0(12시), 1(3시), 2(6시), 3(9시)를 가리킨다.
 *     - 시계를 90도씩 시계방향으로 회전할 수 있고, 회전 시 상하좌우 인접한 시계도 함께 회전한다.
 *     - 모든 시계를 12시 방향(0)으로 맞추기 위해 필요한 최소 회전 횟수를 구한다.
 * 
 * 입력
 *     - int[][] clockHands : 2 ≤ clockHands.length ≤ 8
 *     - clockHands[i][j]는 0 ~ 3
 * 
 * 출력
 *     - 최소 회전 횟수 (int)
 * 
 * 핵심 포인트
 *     - 첫 줄의 회전 조합(4^n)을 완전탐색하고, 나머지 줄은 유도해 결정
 *     - 회전 시 영향을 받는 5칸(자기자신, 상하좌우)에 회전 효과를 누적
 *     - 마지막 줄이 모두 0이 되면 유효한 케이스
 */

class Solution {
	
	public int solution(int[][] clockHands) {
		int min = 200;
		int n = clockHands.length;
		
		// 첫 줄 회전 조합을 비트마스크로 완전탐색 (2비트 * n칸 → 4^n)
		Outter : for (int spins = 0; spins < (1 << n * 2); spins++) {
			int[][] spinMap = new int[n][n];  // 각 칸이 누적 몇 번 회전했는지 기록
			int sum = 0;  // 전체 회전 횟수 누적합
			
			// 첫 번째 줄 회전 조합 적용
			for (int col = 0; col < n; col++) {
				int spin = spins >> (col * 2) & 3;  // 각 칸의 회전 횟수 추출 (2비트)
				spinning(0, col, spin, spinMap);   // 회전 영향 적용
				sum += spin;
			}
			
			// 아래 줄은 위 칸을 12시로 맞추기 위해 필요한 회전 횟수를 유도
			for (int row = 1; row < n; row++) {
				for (int col = 0; col < n; col++) {
					int upperState = (clockHands[row - 1][col] + spinMap[row - 1][col]) % 4;
					int spin = (4 - upperState) % 4;  // 위 칸을 12시로 맞추기 위한 회전 횟수
					spinning(row, col, spin, spinMap);
					sum += spin;
				}
			}
			
			// 마지막 줄이 모두 12시 방향(0)이 되어야 유효한 케이스
			for (int col = 0; col < n; col++) {
				if ((clockHands[n - 1][col] + spinMap[n - 1][col]) % 4 != 0) continue Outter;
			}
			
			min = Math.min(min, sum);  // 최소 회전 횟수 갱신
		}
		
		return min;
	}
	
	// (row, col) 위치의 시계를 spin번 돌릴 때, 자신과 상하좌우 시계에 영향 반영
	public void spinning(int row, int col, int spin, int[][] spinMap) {
		int n = spinMap.length;
		
		int[] dx = {0, -1, 1, 0, 0};
		int[] dy = {0, 0, 0, -1, 1};
		
		for (int i = 0; i < 5; i++) {
			int nx = row + dx[i];
			int ny = col + dy[i];
			if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
				spinMap[nx][ny] = (spinMap[nx][ny] + spin) % 4;
			}
		}
	}
}
