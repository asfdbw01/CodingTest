/*  
 * 문제 요약
 *     - m×n 격자에서 (0,0) → (m-1,n-1)로 이동하는 경로 수 계산
 *     - 이동은 오른쪽/아래 방향만 가능
 *     - 0: 자유 이동, 1: 진입 불가, 2: 직진만 가능
 *     - 경로 수를 20170805로 나눈 나머지 반환
 *
 * 입력
 *     - m: 세로 길이 (1 ≤ m ≤ 500)
 *     - n: 가로 길이 (1 ≤ n ≤ 500)
 *     - cityMap: m×n 배열, 도로 상태
 *
 * 출력
 *     - int: 가능한 경로 수 % 20170805
 *
 * 핵심 포인트
 *     - 방향 DP (위→아래, 왼→오른쪽 분리)
 *     - road==2: 같은 방향만 전달
 *     - road==1: 진입 차단
 */

class Solution {
	
	int MOD = 20170805;
	
	public int solution(int m, int n, int[][] cityMap) {
		long[][][] dp = new long[m][n][2]; // 0: 위→아래, 1: 왼→오른쪽
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 시작점에서 바로 오른쪽/아래 초기화
				if (i == 0 && j == 0) {
					if (m > 1) dp[1][0][0] = 1;
					if (n > 1) dp[0][1][1] = 1;
				}
				
				int road = cityMap[i][j];
				if (road == 1) continue; // 진입 불가
				
				// 아래 방향으로 전달
				if (i + 1 < m) {
					dp[i + 1][j][0] += dp[i][j][0] + ((road == 0) ? dp[i][j][1] : 0);
					dp[i + 1][j][0] %= MOD;
				}
				
				// 오른쪽 방향으로 전달
				if (j + 1 < n) {
					dp[i][j + 1][1] += dp[i][j][1] + ((road == 0) ? dp[i][j][0] : 0);
					dp[i][j + 1][1] %= MOD;
				}
			}
		}
		
		return (int) ((dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD);
	}
}
