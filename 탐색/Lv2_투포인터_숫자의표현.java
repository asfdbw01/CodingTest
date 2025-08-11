/*  
 * 문제 요약
 *     - 주어진 자연수 n을 연속된 자연수들의 합으로 표현하는 방법의 수를 구함  
 *
 * 입력  
 *     - 자연수 n (1 이상 10,000 이하)  
 *
 * 출력  
 *     - n을 연속된 자연수의 합으로 표현할 수 있는 경우의 수  
 *
 * 핵심 포인트  
 *     - 연속된 자연수의 합 = 부분 구간 합  
 *     - 누적합 배열과 투 포인터를 활용하여 구간 합을 빠르게 탐색  
 *     - left ~ right 사이 구간합이 n이면 count 증가  
 */

class Solution {
	
	public int solution(int n) {
		int[] prefixSum = new int[n + 1]; // 누적합 배열 생성

		// 1부터 n까지 누적합 계산
		for (int i = 1; i < prefixSum.length; i++) {
			prefixSum[i] = prefixSum[i - 1] + i;
		}
		
		int left = 0, right = 1; // 투 포인터 초기화
		int count = 0;

		// 구간합이 n과 같은 경우를 셈
		while (left < right) {
			int sum = prefixSum[right] - prefixSum[left]; // 구간합 계산

			if (sum < n) right++;         // n보다 작으면 오른쪽 확장
			else {
				if (sum == n) count++;    // n과 같으면 경우의 수 증가
				left++;                   // 크거나 같으면 왼쪽 이동
			}
		}
		
		return count;
	}
	
}
