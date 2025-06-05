/*
 * 문제 요약
 *     - 조카가 발음할 수 있는 소리는 "aya", "ye", "woo", "ma" 4가지뿐이다.
 *     - 이 소리들을 조합해 만든 문자열 중 연속해서 같은 소리는 발음하지 못한다.
 *     - 주어진 문자열 배열에서 조카가 발음할 수 있는 문자열의 개수를 구하라.
 *
 * 입력
 *     - String[] babbling: 문자열 배열 (1 ≤ babbling.length ≤ 100, 각 원소 길이 ≤ 30)
 *
 * 출력
 *     - 발음 가능한 문자열의 개수 (int)
 *
 * 핵심 포인트
 *     - 유효 발음을 모두 지웠을 때 문자열이 비어야만 발음 가능한 것
 *     - 같은 발음을 연속으로 사용하는 경우는 불가능 (예: ayaaya, mama 등)
 *     - 연속 발음에 대한 사전 필터링 + 나머지 발음 제거 후 검증
 */

class Solution {
	
	String[] possibleSound = {"aya", "ye", "woo", "ma"};
	String[] impossibleSound = {"ayaaya", "yeye", "woowoo", "mama"};
	
	public int solution(String[] babbling) {
		int answer = 0;
		
		Outter: for (String b : babbling) {
			// 연속된 발음이 포함되어 있으면 제외
			for (String ips : impossibleSound) {
				if (b.contains(ips)) continue Outter;
			}
			
			// 가능한 발음들을 공백으로 치환
			for (String ps : possibleSound) {
				b = b.replace(ps, " ");
			}
			
			// 모든 발음을 제거하고 공백만 남았다면 유효한 발음
			if (b.trim().isEmpty()) answer++;
		}
		
		return answer;
	}
}
