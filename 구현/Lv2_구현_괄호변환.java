/*  
 * 문제 요약
 *     - '('와 ')'로 이루어진 "균형잡힌 괄호 문자열"을 "올바른 괄호 문자열"로 변환하는 문제
 *     - 주어진 절차에 따라 문자열을 재귀적으로 분해하고 재조합
 *
 * 입력
 *     - p: 균형잡힌 괄호 문자열 (길이 2 이상 1,000 이하, 짝수)
 *
 * 출력
 *     - 변환된 올바른 괄호 문자열
 *
 * 핵심 포인트
 *     - 재귀적으로 문자열을 분리해 u, v로 나눈 뒤 올바른 괄호 여부를 판단
 *     - u가 올바르면 v를 재귀 처리해 붙임
 *     - u가 올바르지 않으면 규칙에 따라 새 문자열 생성:
 *         '(' + transform(v) + ')' + u의 첫/마지막 문자를 제거한 뒤 괄호 방향 뒤집기
 *     - substring 처리 및 괄호 변환 로직 정확성
 */

class Solution {
	
	public String solution(String p) {
		return transform(p);
	}
	
	public String transform(String w) {
		if (w.isEmpty()) return w;
		
		int index = getIndex(w);
		String u = w.substring(0, index);
		String v = w.substring(index);
		
		// u가 올바른 괄호라면 v를 변환해 u 뒤에 붙인다
		// 아니라면 규칙에 따라 새 문자열 생성
		return isCorrect(u) 
			? u + transform(v) 
			: '(' + transform(v) + ')' + reverseString(u);
	}
	
	// u의 첫/마지막 문자 제거 후 괄호 방향을 뒤집는다
	public String reverseString(String u) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < u.length() - 1; i++) {
			sb.append((u.charAt(i) == '(') ? ')' : '(');
		}
		return sb.toString();
	}
	
	// 균형잡힌 괄호 문자열의 최소 분리 인덱스를 구한다
	public int getIndex(String w) {
		for (int i = 0, count = 0; i < w.length(); i++) {
			count += (w.charAt(i) == '(') ? 1 : -1;
			if (count == 0) return i + 1;
		}
		return w.length();
	}
	
	// 문자열이 올바른 괄호 문자열인지 확인
	public boolean isCorrect(String u) {
		int count = 0;
		for (char c : u.toCharArray()) {
			count += (c == '(') ? 1 : -1;
			if (count < 0) return false;
		}
		return true;
	}
}
