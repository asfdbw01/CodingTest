/*  
 * 문제 요약
 *     - 가장 맵지 않은 두 음식을 섞어 새로운 음식을 만들고, 모든 음식의 스코빌 지수가 K 이상이 되도록 최소 횟수를 구한다.
 *     - 섞은 음식의 스코빌 지수는 (가장 맵지 않은 음식) + (두 번째로 맵지 않은 음식 * 2)로 계산된다.
 *     - 모든 음식이 K 이상이 될 수 없는 경우 -1을 반환한다.
 *
 * 입력  
 *     - int[] scoville : 음식들의 스코빌 지수 배열 (길이 2 이상 1,000,000 이하)
 *     - int K : 목표 스코빌 지수 (0 이상 1,000,000,000 이하)
 *
 * 출력  
 *     - int : 스코빌 지수를 K 이상으로 만들기 위한 최소 섞는 횟수 (불가능하면 -1)
 *
 * 핵심 포인트  
 *     - 항상 가장 맵지 않은 두 개를 선택해야 하므로 최소 힙(PriorityQueue)을 사용한다.
 *     - 섞을 때마다 힙의 크기가 1씩 줄어들기 때문에, 초기 길이 - 현재 크기로 섞은 횟수를 계산할 수 있다.
 *     - peek() 값이 K 이상이 되는 시점에 반복을 종료하고 횟수를 반환한다.
 */

class Solution {
	
	public int solution(int[] scoville, int K) {
		// 최소 힙을 생성하고 모든 스코빌 지수를 삽입
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.naturalOrder());
		for (int i : scoville) pq.add(i);
		
		// 가장 작은 두 수를 꺼내 섞은 값을 다시 삽입 (조건: 최소 2개 이상 있고, 가장 작은 값이 K 미만일 때)
		while (pq.size() >= 2 && pq.peek() < K) {
			pq.add(pq.poll() + pq.poll() * 2);
		}
		
		// 가장 작은 값이 K 미만이면 실패 (-1), 아니면 섞은 횟수 = 초기 개수 - 현재 크기
		return (pq.peek() < K) ? -1 : scoville.length - pq.size();
	}
}
