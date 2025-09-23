/*  
 * 문제 요약
 *     - 주어진 문자열 s에서 각 단어의 짝수 번째 문자는 대문자, 홀수 번째 문자는 소문자로 변환  
 *     - 단어는 공백을 기준으로 나뉘며, 단어마다 인덱스를 새로 매김  
 *
 * 입력  
 *     - 문자열 s (1개 이상의 단어, 공백이 여러 개 연속될 수 있음)  
 *
 * 출력  
 *     - 각 단어의 짝수 인덱스 문자는 대문자, 홀수 인덱스 문자는 소문자로 변환된 문자열  
 *
 * 핵심 포인트  
 *     - 전체 문자열을 순회하면서 공백 기준으로 단어 위치를 판단  
 *     - 공백이 연속되는 경우도 정확히 처리  
 */

class Solution {
	
	public String solution(String s) {
		StringBuilder sb = new StringBuilder();
		int index = 0; // 각 단어 내 인덱스 초기화

		for (char c : s.toCharArray()) {
			if (c == ' ') {
				sb.append(c);   // 공백은 그대로 추가
				index = 0;      // 단어 인덱스 초기화
			} else {
				// 짝수 인덱스는 대문자, 홀수는 소문자
				sb.append((index++ % 2 == 0) ? Character.toUpperCase(c) : Character.toLowerCase(c));
			}
		}
		
		return sb.toString();
	}
	
}
