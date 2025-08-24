/*
 * 문제 요약
 *     - 문자열 s를 규칙에 따라 가능한 많이 분해
 *     - 규칙: 처음 글자를 x라 할 때, x의 등장 횟수와 다른 글자의 등장 횟수가 같아지는 순간 분리
 *     - 남은 문자열에 대해서 같은 과정을 반복
 *
 * 입력
 *     - s: 영어 소문자로 이루어진 문자열 (1 ≤ s.length() ≤ 10,000)
 *
 * 출력
 *     - 조건에 따라 분해된 문자열의 개수 (int)
 *
 * 핵심 포인트
 *     - x와 x가 아닌 글자의 등장 횟수를 따로 세며 진행
 *     - xCnt == notXCnt가 되는 순간을 분할 기준으로 사용
 *     - 반복문 종료 후 마지막 조각도 포함해야 하므로 +1
 */

class Solution {
	
	public int solution(String s) {
		int cnt = 0;
		
		char x = s.charAt(0);
		int xCnt = 0;
		int notXCnt = 0;
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			if (xCnt == notXCnt) {
				if (xCnt != 0) cnt++; // 구간 종료 시점
				x = c; // 새로운 구간의 기준 문자
				xCnt = notXCnt = 0;
			}
			
			if (x == c) xCnt++;
			else notXCnt++;
		}
		
		return cnt + 1; // 마지막 구간 포함
	}
}
