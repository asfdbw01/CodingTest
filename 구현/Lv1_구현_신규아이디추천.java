/*  
 * 문제 요약
 *     - 신규 유저가 입력한 아이디가 규칙에 맞지 않으면, 7단계 과정을 통해 유사하면서 규칙에 맞는 아이디를 추천
 *
 * 입력
 *     - new_id: 길이 1 이상 1,000 이하의 문자열 (알파벳 대소문자, 숫자, 특수문자 포함)
 *
 * 출력
 *     - 7단계를 거친 최종 추천 아이디 (조건을 만족하는 문자열)
 *
 * 핵심 포인트
 *     - 대문자 → 소문자 치환
 *     - 유효한 문자만 필터링
 *     - 연속된 마침표는 하나로 줄이기
 *     - 마침표가 처음/끝에 오면 제거
 *     - 빈 문자열 → "a" 보정
 *     - 길이 제한 (최대 15자)
 *     - 최소 길이 보장 (3자 미만이면 마지막 문자 반복)
 */

class Solution {
	
	public String solution(String new_id) {
		StringBuilder sb = new StringBuilder(new_id);
		
		// 1. 대문자 -> 소문자
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (Character.isUpperCase(c)) sb.setCharAt(i, Character.toLowerCase(c));
		}
		
		// 2. 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자 제거
		for (int i = sb.length() - 1; i >= 0; i--) {
			char c = sb.charAt(i);
			if (c == '-' || c == '_' || c == '.' || ('a' <= c && c <= 'z') || ('0' <= c && c <= '9')) continue;
			sb.deleteCharAt(i);
		}
		
		// 3. .. → .
		sb = new StringBuilder(sb.toString().replaceAll("[.]{2,}", "."));
		
		// 4. 마침표(.)가 맨 앞이거나 맨 뒤에 있다면 제거
		if (sb.length() > 0 && sb.charAt(0) == '.') sb.deleteCharAt(0);
		if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '.') sb.setLength(sb.length() - 1);
		
		// 5. 빈 문자열이라면 "a" 삽입
		if (sb.length() == 0) sb.append('a');
		
		// 6. 길이가 16 이상이면 16번째부터 끝까지 제거
		if (sb.length() > 15) sb.setLength(15);
		
		// 6-1. 마침표(.)가 맨 뒤에 있다면 제거
		while (sb.charAt(sb.length() - 1) == '.') sb.setLength(sb.length() - 1);
		
		// 7. 길이가 2 이하라면 마지막 문자를 길이가 3이 될 때까지 삽입
		while (sb.length() < 3) sb.append(sb.charAt(sb.length() - 1));
		
		return sb.toString();
	}
	
}
