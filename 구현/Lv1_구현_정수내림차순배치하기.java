/*  
 * 문제 요약
 *     - 정수 n의 각 자릿수를 큰 숫자부터 작은 순서로 정렬하여 새로운 정수 생성  
 *
 * 입력  
 *     - 정수 n (1 이상 8,000,000,000 이하)  
 *
 * 출력  
 *     - 자릿수를 내림차순으로 정렬한 정수  
 *
 * 핵심 포인트  
 *     - 정수를 문자열로 변환 후 각 자릿수를 정렬  
 *     - Stream API로 문자 정렬 및 문자열 재조합  
 */

class Solution {
	
	public long solution(long n) {
		// 1. long을 문자열로 변환 후 char stream으로 자릿수 분리
		// 2. char → Character → 문자열로 변환하면서 내림차순 정렬
		// 3. 재조합한 문자열을 long으로 변환
		return Long.parseLong(String.valueOf(n)
								    .chars()
								    .mapToObj(c -> (char) c)
								    .sorted(Comparator.reverseOrder())
								    .map(String::valueOf)
								    .collect(Collectors.joining()));
	}
	
}
