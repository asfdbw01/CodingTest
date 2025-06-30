/*
 * 문제 요약
 *     - 각 라운드마다 enemy[i]만큼 병사를 소모하여 막는다
 *     - 병사가 부족하면 무적권을 사용하여 병사 소모 없이 막을 수 있다
 *     - 무적권은 최대 k번만 사용할 수 있다
 *     - 무적권은 가능한 큰 적에게 쓰는 것이 이득
 *
 * 입력
 *     - n: 병사 수 (1 ≤ n ≤ 1e9)
 *     - k: 무적권 사용 가능 횟수 (0 ≤ k ≤ 5e5)
 *     - enemy: 적의 수 배열 (1 ≤ length ≤ 1e6)
 *
 * 출력
 *     - 막을 수 있는 최대 라운드 수
 *
 * 핵심 포인트
 *     - 지금까지 막은 라운드 중 가장 큰 enemy[i]에 무적권 사용
 *     - 무적권은 최대 힙을 통해 가장 큰 적에게만 사용
 */

class Solution {
	
	public int solution(int n, int k, int[] enemy) {
		int len = enemy.length;
		if (k >= len) return len; // 무적권만으로 다 막을 수 있는 경우
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // 최대 힙
		
		for (int i = 0; i < len; i++) {
			n -= enemy[i];
			pq.offer(enemy[i]);
			
			if (n < 0) {
				if (k == 0) return i; // 무적권도 다 썼고 병사도 부족
				n += pq.poll(); // 가장 큰 enemy[i]에 무적권 사용하여 병사 복구
				k--;
			}
		}
		
		return len; // 끝까지 막은 경우
	}
}
