/*  
 * 문제 요약
 *     - 단어 s의 가운데 글자를 반환
 *     - 단어 길이가 홀수면 가운데 글자 1개, 짝수면 가운데 글자 2개 반환
 *
 * 입력  
 *     - s: 길이 1 이상 100 이하의 문자열
 *
 * 출력  
 *     - 문자열 s의 가운데 글자(1개 또는 2개)
 *
 * 핵심 포인트  
 *     - 문자열 길이의 홀짝에 따라 시작 인덱스와 반환 길이가 달라짐
 *     - substring(start, end) 메서드를 활용
 */

class Solution {
	
	public String solution(String s) {
		return s.substring(s.length() / 2 - ((s.length() % 2 == 0) ? 1 : 0), s.length() / 2 + 1);
	}
	
}
