/*
 * 문제 요약
 *     - 카드 뭉치 2개(cards1, cards2)를 가지고 목표 단어 배열(goal)을 만들 수 있는지 판단
 *     - 각 카드 뭉치에서는 앞에서부터 순서대로만 사용할 수 있으며, 건너뛰기나 재사용은 불가능
 * 
 * 입력
 *     - String[] cards1 : 첫 번째 카드 뭉치 (길이 ≤ 10)
 *     - String[] cards2 : 두 번째 카드 뭉치 (길이 ≤ 10)
 *     - String[] goal : 만들고자 하는 목표 단어 배열 (길이 ≤ 20)
 * 
 * 출력
 *     - String : goal을 만들 수 있으면 "Yes", 없으면 "No"
 * 
 * 핵심 포인트
 *     - 각 카드 뭉치는 큐처럼 동작하며, 앞에서부터 순서대로만 접근 가능
 *     - 두 카드 뭉치에서 한 단어씩 순차적으로 소비하며 goal과 일치하는지 확인
 *     - 두 인덱스(idx1, idx2)를 활용하여 카드 뭉치 탐색을 진행
 */

class Solution {
	public String solution(String[] cards1, String[] cards2, String[] goal) {
		int idx1 = 0, idx2 = 0;  // 각 카드 뭉치의 현재 위치 포인터

		for (String word : goal) {
			// cards1에서 현재 단어 사용 가능하면 idx1 증가
			if (idx1 < cards1.length && cards1[idx1].equals(word)) idx1++;
			// cards2에서 현재 단어 사용 가능하면 idx2 증가
			else if (idx2 < cards2.length && cards2[idx2].equals(word)) idx2++;
			// 둘 다 불가능한 경우 순서대로 만들 수 없음
			else return "No";
		}

		// 모든 단어를 순서대로 만들 수 있으면 "Yes"
		return "Yes";
	}
}
