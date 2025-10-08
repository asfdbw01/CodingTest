/**
 * 문제 요약
 *     - 문자열 s의 각 위치마다, 자신보다 앞에 등장했던 동일한 문자가 가장 가까이에 있다면
 *       그 거리(현재 인덱스 - 이전 인덱스)를 구하고, 없으면 -1로 처리하는 문제
 *
 * 입력
 *     - s: 영어 소문자로 이루어진 문자열 (1 ≤ s.length() ≤ 10,000)
 *
 * 출력
 *     - 각 문자 위치마다의 최근 동일 문자로부터의 거리 배열 (int[])
 *
 * 핵심 포인트
 *     - 문자 a~z의 마지막 등장 위치를 기록하는 배열 recent[] 사용
 *     - 처음 등장하는 문자는 -1로 처리
 *     - 이후 등장 시 현재 인덱스 - 마지막 등장 인덱스를 거리로 계산
 *     - 시간복잡도 O(n), 공간복잡도 O(1) 수준
 */

class Solution {

	public int[] solution(String s) {
		int[] answer = new int[s.length()];
		int[] recent = new int['z' - 'a' + 1]; // a~z 등장 인덱스 저장
		Arrays.fill(recent, -1); // 등장한 적 없는 문자는 -1

		for (int i = 0; i < s.length(); i++) {
			int index = s.charAt(i) - 'a';
			
			// 이전에 등장한 적이 없다면 -1, 있다면 거리 계산
			answer[i] = recent[index] == -1 ? -1 : i - recent[index];

			// 현재 위치를 최근 등장 위치로 갱신
			recent[index] = i;
		}

		return answer;
	}
}
