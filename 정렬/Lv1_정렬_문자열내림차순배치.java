/*  
 * 문제 요약
 *     - 문자열 s의 문자를 내림차순으로 정렬하여 새로운 문자열로 반환
 *     - 대문자 < 소문자이므로 소문자가 더 큼 ('Z' < 'a')
 *
 * 입력  
 *     - String s: 길이 1 이상, 영문 대소문자만 포함
 *
 * 출력  
 *     - 내림차순 정렬된 문자열
 *
 * 핵심 포인트  
 *     - Java 문자열은 직접 정렬이 불가능하므로 IntStream(char 코드) 활용
 *     - 두 가지 방식 모두 내림차순 정렬을 수행함
 *         (1) 박싱 후 reverseOrder() 정렬
 *         (2) 오름차순 정렬 후 StringBuilder.reverse()
 */

class Solution {
	
	public String solution(String s) {
		// 방식 ①: 박싱 후 reverseOrder 정렬 (직관적인 내림차순 정렬)
		/*
		return s.chars()                            		// IntStream으로 변환 (char -> int)
				.boxed()                            		// int -> Integer로 박싱
				.sorted(Comparator.reverseOrder()) 			// 내림차순 정렬
				.collect(StringBuilder::new,       			// StringBuilder에
						 StringBuilder::appendCodePoint,
						 StringBuilder::append)     		// 문자 조합
				.toString();                        		// 문자열로 변환
		*/

		// 방식 ②: 오름차순 정렬 후 reverse() 사용 (비박싱, 더 빠름)
		return s.chars()                             		// IntStream 생성
				.sorted()                           		// 오름차순 정렬
				.collect(StringBuilder::new,        		// StringBuilder에
						 StringBuilder::appendCodePoint,
						 StringBuilder::append)     		// 문자 조합
				.reverse()                          		// 역순 변환
				.toString();                        		// 문자열로 반환
	}
	
}
