/*
 * 문제 요약
 *     - 사과는 1~k점의 품질 점수를 가지며, 한 상자에 m개씩 담아 판매한다.
 *     - 한 상자의 가격은 그 상자 내에서 가장 낮은 사과 점수(p) * m.
 *     - 점수가 높은 사과부터 차례대로 상자를 구성할 때, 얻을 수 있는 최대 이익을 계산한다.
 *     - 남는 사과는 버림.
 *
 * 입력
 *     - int k: 사과 점수의 최대값 (3 ≤ k ≤ 9)
 *     - int m: 한 상자에 담기는 사과 수 (3 ≤ m ≤ 10)
 *     - int[] score: 사과들의 점수 배열 (7 ≤ score.length ≤ 1,000,000, score[i] ∈ [1, k])
 *
 * 출력
 *     - int: 판매 가능한 상자들을 최대한 구성했을 때 얻는 총 이익
 *
 * 핵심 포인트
 *     - 사과 점수를 내림차순으로 정렬하여 높은 점수부터 차곡차곡 상자 구성
 *     - m개 단위로 묶어 최소값을 계산 → 최댓값이 아닌 **최소값을 기준으로 이익 계산**
 *     - 우선순위 큐 또는 정렬 후 인덱스 슬라이딩으로 해결 가능
 *     - 시간복잡도: O(n log n)
 */

class Solution {
	
	public int solution(int k, int m, int[] score) {
		int answer = 0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		for (int s : score) pq.add(s); // 높은 점수부터 내림차순 정렬
		
		int cnt = 0;
		while (!pq.isEmpty()) {
			int min = pq.poll(); // 현재 사과 점수
			cnt++;
			
			if (cnt == m) { // 한 상자가 채워지면 최소 점수 기준으로 가격 계산
				answer += min * m;
				cnt = 0;
			}
		}
		
		return answer;
	}
}
