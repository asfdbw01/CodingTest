/*  
 * 문제 요약
 *     - 자연수 n의 각 자릿수를 뒤집어서 배열로 반환  
 *
 * 입력  
 *     - 자연수 n (1 이상 10,000,000,000 이하)  
 *
 * 출력  
 *     - n의 자릿수를 뒤집은 int 배열  
 *
 * 핵심 포인트  
 *     - StringBuilder를 이용해 문자열을 뒤집고  
 *     - char형 숫자를 '0'을 빼서 정수로 변환  
 *     - Stream으로 int 배열로 반환  
 */

class Solution {
	
	public int[] solution(long n) {
		// 1. 숫자를 문자열로 바꾼 뒤 뒤집기
		// 2. 뒤집은 문자열을 char stream으로 변환
		// 3. 각 문자를 숫자로 바꿔 int 배열로 반환
		return new StringBuilder(String.valueOf(n)).reverse()
												   .toString()
												   .chars()
												   .map(i -> i - '0')
												   .toArray();
	}
	
}
