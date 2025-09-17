/*  
 * 문제 요약
 *     - 주어진 정수 n의 모든 약수를 찾아 그 합을 반환  
 *
 * 입력  
 *     - 정수 n (0 이상 3000 이하)  
 *
 * 출력  
 *     - n의 모든 약수의 합  
 *
 * 핵심 포인트  
 *     - 1부터 n까지 순회하며 n을 나누어떨어지게 하는 수들을 합산  
 *     - Java Stream API의 rangeClosed + filter + sum 활용  
 */

class Solution {
	
	public int solution(int n) {
		// 1부터 n까지의 수 중 n의 약수인 것만 필터링 후 합산
		return IntStream.rangeClosed(1, n)
						.filter(i -> n % i == 0)
						.sum();
	}
	
}
