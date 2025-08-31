/*  
 * 문제 요약
 *     - 1부터 n 사이에 존재하는 소수의 개수를 반환  
 *     - 소수: 1과 자기 자신만으로 나누어지는 수 (1은 소수가 아님)  
 *
 * 입력  
 *     - 정수 n (2 이상 1,000,000 이하)  
 *
 * 출력  
 *     - 1부터 n까지의 소수 개수 (정수형)  
 *
 * 핵심 포인트  
 *     - 소수 판별을 위해 √n까지 나누어지는 수가 있는지 확인  
 *     - 짝수는 미리 제외하고 홀수만 검사하여 효율 향상  
 *     - Stream과 메서드 분리를 통해 가독성과 재사용성 확보  
 */

class Solution {
	
	public int solution(int n) {
		// 1부터 n까지의 수 중 소수만 필터링하여 개수 반환
		return (int) IntStream.rangeClosed(1, n)
							  .filter(i -> isPrime(i))
							  .count();
	}
	
	// 주어진 수가 소수인지 판별하는 메서드
	public boolean isPrime(int num) {
		if (num == 2) return true;                    // 2는 유일한 짝수 소수
		if (num <= 1 || num % 2 == 0) return false;   // 1 이하 또는 짝수는 소수 아님
		
		// 3부터 √num까지 홀수만 검사
		for (int i = 3; i <= Math.sqrt(num); i += 2) {
			if (num % i == 0) return false;
		}
		
		return true;
	}
	
}
