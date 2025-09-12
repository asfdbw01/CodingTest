/*
 * 문제 요약
 *     - 매일 한 명의 가수가 점수를 받는다.
 *     - 상위 k명의 점수를 명예의 전당에 올리며, 매일 최하위 점수를 발표한다.
 *     - 초기 k일까지는 모두 등재, 이후엔 k번째보다 높은 점수만 등록됨.
 *
 * 입력
 *     - int k: 명예의 전당 목록의 최대 크기 (3 ≤ k ≤ 100)
 *     - int[] score: 각 날의 가수 점수 (7 ≤ score.length ≤ 1000, 0 ≤ score[i] ≤ 2000)
 *
 * 출력
 *     - int[]: 매일 발표된 명예의 전당의 최하위 점수 배열
 *
 * 핵심 포인트
 *     - 상위 k개의 점수를 관리하는 자료구조 사용
 *     - 매일 현재 명예의 전당의 최소값 추출
 *     - 최소 힙(PriorityQueue)을 이용해 O(n log k)로 해결
 */
class Solution {
	
	public int[] solution(int k, int[] score) {
		int[] answer = new int[score.length];
		
		PriorityQueue<Integer> topK = new PriorityQueue<>(); // 명예의 전당 (최소 힙)

		for (int i = 0; i < score.length; i++) {
			if (topK.size() < k) {
				topK.add(score[i]); // 아직 k개 미만이면 무조건 추가
			} else if (score[i] > topK.peek()) {
				topK.poll();        // 최하위 제거
				topK.add(score[i]); // 새 점수 추가
			}
			answer[i] = topK.peek(); // 현재 명예의 전당의 최하위 점수 저장
		}
		
		return answer;
	}
}
