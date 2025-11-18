/*  
 * 문제 요약  
 *     - 삼각형 꼭대기에서 시작하여 아래로 내려오는 경로 중 숫자의 합이 가장 큰 값을 구하는 문제  
 *
 * 입력  
 *     - triangle: 삼각형 정보 (triangle[i]는 i+1개의 정수를 포함)  
 *
 * 출력  
 *     - 최댓값 경로의 합  
 *
 * 핵심 포인트  
 *     - 각 지점까지 도달 가능한 최댓값을 누적하는 DP  
 *     - dp[i][j] = 현재 값 + max(왼쪽 위, 오른쪽 위)  
 */

class Solution {
	
	public int solution(int[][] triangle) {
		int n = triangle.length;
		
		int[][] dp = new int[n][n];
		
		// 초기값 세팅
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				dp[i][j] = triangle[i][j];
			}
		}
		
		// DP 점화식 적용
		for (int i = 1; i < n; i++) {
			dp[i][0] += dp[i - 1][0]; // 왼쪽 모서리
			for (int j = 1; j <= i; j++) {
				// 왼쪽 위 또는 오른쪽 위 중 더 큰 값 선택
				dp[i][j] += Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
			}
		}
		
		// 마지막 줄에서 최댓값 반환
		return Arrays.stream(dp[n - 1]).max().getAsInt();
	}
}
