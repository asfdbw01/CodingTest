/*  
 * 문제 요약
 *     - N시간 동안 작업량을 줄여서 야근 피로도(작업량 제곱의 합)를 최소화  
 *     - 한 시간에 작업량을 1만큼 줄일 수 있고, 가장 큰 작업부터 우선적으로 줄이는 것이 핵심  
 *
 * 입력  
 *     - int n: 남은 야근 시간 (1 이상 1,000,000 이하)  
 *     - int[] works: 각 작업의 작업량 (1 이상 50,000 이하, 길이 최대 20,000)  
 *
 * 출력  
 *     - 최소 야근 피로도 (작업량들의 제곱의 합)  
 *
 * 핵심 포인트  
 *     - 가장 작업량이 큰 것부터 줄이는 그리디 전략
 *     - 최대 힙(PriorityQueue)을 사용하여 O(n log w)의 효율적 처리
 *     - 야근 시간이 모든 작업량보다 많으면 결과는 0
 */

class Solution {
	
	public long solution(int n, int[] works) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // 최대 힙
		
		// 작업량 큐에 삽입
		for (int work : works) pq.add(work);
		
		// N시간 동안 가장 큰 작업부터 1씩 줄이기
		for (int i = 0; i < n && !pq.isEmpty(); i++) {
			int work = pq.poll() - 1;
			if (work > 0) pq.add(work);
		}
		
		long answer = 0L;
		
		// 남은 작업량의 제곱 합산
		while (!pq.isEmpty()) {
			long work = pq.poll();
			answer += work * work;
		}
		
		return answer;
	}
	
}
