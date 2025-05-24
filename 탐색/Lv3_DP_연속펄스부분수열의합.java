/*
 * 문제 요약
 *     - 주어진 정수 수열에서 연속된 부분 수열에 1, -1, 1, -1... 또는 -1, 1, -1, 1...로 구성된 펄스 수열을 곱함
 *     - 이때 얻을 수 있는 연속 펄스 부분 수열의 합 중 최댓값을 구함
 * 
 * 입력
 *     - int[] sequence : 길이 최대 500,000의 정수 수열, 각 원소는 -100,000 이상 100,000 이하
 * 
 * 출력
 *     - long : 연속 펄스 부분 수열의 합 중 최대값
 * 
 * 핵심 포인트
 *     - 펄스 수열 두 종류 (1,-1,1... / -1,1,-1...)를 모두 고려해야 함
 *     - 연속된 부분 수열을 고려하므로 Kadane's Algorithm(연속 최대 구간합) 응용
 *     - 펄스 수열을 곱한 후의 누적합을 동적 계획법으로 갱신
 *     - O(n) 시간, O(1) 공간으로 처리 가능
 */

class Solution {
	public long solution(int[] sequence) {
		// dp[2][0] : 펄스가 1, -1, 1,... 시작일 때
		// dp[2][1] : 펄스가 -1, 1, -1,... 시작일 때
		long[][] dp = new long[2][2];
		dp[0][0] = sequence[0];       // 펄스 1 시작
		dp[0][1] = -sequence[0];      // 펄스 -1 시작

		long answer = Math.max(dp[0][0], dp[0][1]);

		for (int i = 1; i < sequence.length; i++) {
			int now = i % 2;
			int before = (i + 1) % 2;

			// 현재 인덱스를 펄스 1로 곱하는 경우 (이전은 펄스 -1)
			dp[now][0] = Math.max(dp[before][1], 0) + sequence[i];

			// 현재 인덱스를 펄스 -1로 곱하는 경우 (이전은 펄스 1)
			dp[now][1] = Math.max(dp[before][0], 0) - sequence[i];

			// 현재까지의 최대값 갱신
			answer = Math.max(answer, Math.max(dp[now][0], dp[now][1]));
		}

		return answer;
	}
}
