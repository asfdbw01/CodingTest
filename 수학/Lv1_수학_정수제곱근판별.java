/*  
 * 문제 요약
 *     - 주어진 양의 정수 n이 어떤 양의 정수 x의 제곱인지 판별  
 *     - 제곱수라면 (x+1)^2 반환, 아니라면 -1 반환  
 *
 * 입력  
 *     - 양의 정수 n (1 ≤ n ≤ 50조)  
 *
 * 출력  
 *     - 제곱수면 (x+1)^2, 아니면 -1 반환  
 *
 * 핵심 포인트  
 *     - Math.sqrt(n)으로 제곱근 확인 (소수점 없는지 확인)  
 *     - double -> long 변환 시 정확성 주의  
 */

class Solution {
	
	public long solution(long n) {
		// 제곱근이 정수인지 확인한 후 (x+1)^2 반환, 아니면 -1 반환
		return (Math.sqrt(n) % 1 == 0) ? 
				(long) Math.pow(Math.sqrt(n) + 1, 2) : -1L;
	}
	
}
