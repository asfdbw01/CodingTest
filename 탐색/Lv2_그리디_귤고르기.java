/*
 * 문제 요약
 *     - 귤 중에서 k개를 고를 때, 크기 종류 수를 최소화해야 한다.
 *     - 각 귤 크기별 개수를 세고, 개수가 많은 종류부터 선택하여 k개를 채운다.
 *
 * 입력
 *     - int k: 선택할 귤의 개수 (1 ≤ k ≤ 100,000)
 *     - int[] tangerine: 귤의 크기 배열 (1 ≤ tangerine.length ≤ 100,000, 1 ≤ tangerine[i] ≤ 10,000,000)
 *
 * 출력
 *     - int: 고른 귤에서 서로 다른 크기의 최소 개수
 *
 * 핵심 포인트
 *     - 크기별 개수를 집계한 후, 개수가 많은 종류부터 차례로 선택
 *     - 우선순위 큐 또는 정렬을 활용한 그리디 전략으로 해결
 *     - 전체 시간복잡도: O(n log n)
 */

class Solution {
	
	public int solution(int k, int[] tangerine) {
		int answer = 0;
		
		Map<Integer, Integer> sizeMap = new HashMap<>();
		
		for (int size : tangerine) {
			sizeMap.merge(size, 1, Integer::sum); // 귤 크기별 개수 집계
		}
		
		List<Integer> sizeCounts = new ArrayList<>(sizeMap.values());
		sizeCounts.sort(Comparator.reverseOrder()); // 개수 기준 내림차순 정렬
		
		for (int count : sizeCounts) {
			k -= count;
			answer++;             	// 현재 종류 선택
			if (k <= 0) break; 		// k개를 모두 고르면 종료
		}
		
		return answer;
	}
}
