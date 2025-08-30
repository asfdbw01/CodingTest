/*  
 * 문제 요약
 *     - 원형으로 연결된 스티커에서 인접한 스티커를 동시에 뜯을 수 없을 때,  
 *       뜯어낸 스티커의 숫자 합의 최댓값을 구하는 문제  
 *
 * 입력  
 *     - int[] sticker : 각 스티커에 적힌 정수 (길이 N, 1 ≤ N ≤ 100,000)  
 *
 * 출력  
 *     - 뜯어낸 스티커 숫자의 최댓값 (int)  
 *
 * 핵심 포인트  
 *     - 원형 구조 → 첫 번째 스티커를 포함한 경우와 제외한 경우로 분리  
 *     - 각 경우는 일반적인 1차원 DP로 처리 (House Robber 패턴)  
 *     - dp[i] = max(dp[i - 1], dp[i - 2] + sticker[i])  
 */

class Solution {
	
	public int solution(int[] sticker) {
		int n = sticker.length;
		
		// 스티커가 1개뿐인 경우는 그대로 반환
		if (n == 1) return sticker[0];
		
		// case 1: 0번째 스티커를 포함하고 마지막은 제외
		int[] dp1 = new int[n];
		dp1[0] = sticker[0];
		dp1[1] = Math.max(sticker[0], sticker[1]);
		for (int i = 2; i < n - 1; i++) {
			dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
		}
		
		// case 2: 0번째 스티커를 제외하고 마지막은 포함 가능
		int[] dp2 = new int[n];
		dp2[0] = 0;
		dp2[1] = sticker[1];
		for (int i = 2; i < n; i++) {
			dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
		}
		
		// 두 경우 중 최대값 반환
		return Math.max(dp1[n - 2], dp2[n - 1]);
	}
	
}
