/*  
 * 문제 요약  
 *     - n명의 사람들이 입국심사를 기다리고 있으며, 각 심사관마다 심사 시간이 다름.  
 *     - 모든 사람이 심사를 받는 데 걸리는 최소 시간을 구하는 문제.  
 *
 * 입력  
 *     - int n: 입국심사를 기다리는 사람 수 (1 ≤ n ≤ 1,000,000,000)  
 *     - int[] times: 심사관별 심사 시간 배열 (1 ≤ 길이 ≤ 100,000, 각 원소 ≤ 1,000,000,000)  
 *
 * 출력  
 *     - long: 모든 사람이 심사를 받는 최소 시간  
 *
 * 핵심 포인트  
 *     - 시간의 경계(1 ~ max_time * n)를 기준으로 이분 탐색 수행  
 *     - mid 시간 동안 처리 가능한 인원수를 계산하여 충분 여부 판단  
 *     - 단조성을 활용해 최소 충분 시간을 탐색  
 */

class Solution {
	
	public long solution(int n, int[] times) {
		int max = Arrays.stream(times).max().getAsInt();
		
		long left = 1, right = (long) max * n;
		long answer = right;
		
		while (left <= right) {
			long mid = (left + right) / 2;
			
			// mid 시간 동안 처리 가능한 총 인원수 계산
			long count = Arrays.stream(times).mapToLong(i -> mid / i).sum();
			
			if (count < n) {
				// 부족하므로 시간을 늘림
				left = mid + 1;
			} else {
				// 충분하므로 시간을 줄이면서 더 짧은 시간 탐색
				answer = mid;
				right = mid - 1;
			}
		}
		
		return answer;
	}
	
}
