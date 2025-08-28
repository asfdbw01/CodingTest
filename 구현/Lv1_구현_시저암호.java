/*  
 * 문제 요약
 *     - 주어진 문자열 s의 각 알파벳을 n만큼 밀어 암호화된 문자열을 반환 (시저 암호)  
 *     - 알파벳 대소문자는 각각의 범위 내에서만 밀리며, z 이후는 다시 a부터 시작됨  
 *     - 공백은 유지됨  
 *
 * 입력  
 *     - 문자열 s (길이 1 이상 8000 이하, 알파벳 대소문자 및 공백으로만 구성)  
 *     - 정수 n (1 이상 25 이하)  
 *
 * 출력  
 *     - 시저 암호로 변환된 문자열  
 *
 * 핵심 포인트  
 *     - 알파벳인지 여부 판단 후 대소문자 각각 분기 처리  
 *     - 모듈러 연산으로 z/a를 넘어갈 경우 알파벳 순환 처리  
 *     - Stream + StringBuilder를 활용하여 성능과 가독성 확보
 */

class Solution {
	
	public String solution(String s, int n) {
		return s.chars() // 문자열을 문자 코드 스트림으로 변환
				.map(c -> 
					// 알파벳이 아닌 경우 (공백 등)은 그대로 유지
					!Character.isAlphabetic(c) ? c :

					// 소문자인 경우 'a'를 기준으로 밀기
					c >= 'a' ? (c - 'a' + n) % ('z' - 'a' + 1) + 'a' :

					// 대문자인 경우 'A'를 기준으로 밀기
					(c - 'A' + n) % ('Z' - 'A' + 1) + 'A'
				)
				// 문자 코드들을 StringBuilder에 appendCodePoint로 조합
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString(); // 최종 문자열 반환
	}
	
}
