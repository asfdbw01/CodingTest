/*  
 * 문제 요약  
 *     - n명의 권투 선수들 간 일부 경기 결과가 주어질 때, 승패 관계를 전파해 정확한 순위를 매길 수 있는 선수 수를 구하는 문제  
 *
 * 입력  
 *     - n: 선수의 수 (1 ≤ n ≤ 100)  
 *     - results: [A, B] 형태의 배열 (A가 B를 이김) (1 ≤ results.length ≤ 4,500)  
 *
 * 출력  
 *     - 정확한 순위를 매길 수 있는 선수의 수  
 *
 * 핵심 포인트  
 *     - 승패 관계는 전이성이 있다 (A > B, B > C ⇒ A > C)  
 *     - 플로이드-워셜을 통해 모든 간접 관계를 전파  
 *     - 각 선수에 대해 (이긴 선수 수 + 진 선수 수 == n-1)인지 확인
 */

class Solution {
	
	public int solution(int n, int[][] results) {
		// 승패 관계를 저장하는 배열 (win[i][j] = i가 j를 이김)
		boolean[][] win = new boolean[n][n];
		
		// 초기 결과 입력
		for (int[] result : results) {
			win[result[0] - 1][result[1] - 1] = true;
		}
		
		// 플로이드-워셜로 승패 관계 전파
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (win[i][k] && win[k][j]) {
						win[i][j] = true;
					}
				}
			}
		}
		
		int count = 0;
		// 각 선수별로 이긴 수와 진 수를 계산
		for (int i = 0; i < n; i++) {
			int winCount = 0;
			int loseCount = 0;
			for (int j = 0; j < n; j++) {
				if (win[i][j]) winCount++;
				if (win[j][i]) loseCount++;
			}
			if (winCount + loseCount == n - 1) count++;
		}
		
		return count;
	}
}
