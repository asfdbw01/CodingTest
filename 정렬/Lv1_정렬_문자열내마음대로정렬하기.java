/*  
 * 문제 요약
 *     - 문자열 배열 strings를 정수 n번째 인덱스의 문자를 기준으로 정렬
 *     - n번째 문자가 같을 경우 전체 문자열 기준으로 사전순 정렬
 *
 * 입력  
 *     - String[] strings: 길이 1~50, 각 문자열은 길이 1~100의 소문자 알파벳
 *     - int n: 모든 문자열의 길이는 n보다 크며 유효한 인덱스
 *
 * 출력  
 *     - n번째 문자를 기준으로 정렬된 문자열 배열
 *
 * 핵심 포인트  
 *     - 첫 번째 기준: 문자열의 n번째 문자
 *     - 두 번째 기준: 전체 문자열을 사전순으로 비교
 *     - 두 가지 방식 모두 동일한 정렬 로직을 구현함
 *         (1) 직접 Comparator 작성 (삼항 연산자 활용)
 *         (2) Comparator 체이닝 (comparingInt + thenComparing)
 */

class Solution {
	
	public String[] solution(String[] strings, int n) {
		// 방법 ①: 람다식 + 삼항 연산자로 직접 정렬 기준 지정
		/*
		return Arrays.stream(strings)
					 .sorted((s1, s2) -> s1.charAt(n) == s2.charAt(n)
							 			 ? s1.compareTo(s2)            	// n번째 문자가 같으면 전체 문자열 사전순 비교
							 			 : s1.charAt(n) - s2.charAt(n)) // n번째 문자가 다르면 해당 문자 기준 오름차순
					 .toArray(String[]::new);
		*/

		// 방법 ②: Comparator 체이닝 방식 (더 가독성 높고 정석적인 방식)
		return Arrays.stream(strings)
					 .sorted(Comparator.comparingInt((String s) -> s.charAt(n))  	// 1차 정렬 기준: n번째 문자
					 				   .thenComparing(Comparator.naturalOrder())) 	// 2차 정렬 기준: 전체 문자열 사전순
					 .toArray(String[]::new);
	}
	
}
