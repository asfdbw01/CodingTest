/*  
 * 문제 요약
 *     - 주어진 수가 1이 될 때까지 콜라츠 추측 과정을 반복하며, 그 횟수를 구하는 문제
 *     - 500번 이상 반복해도 1이 되지 않으면 -1 반환
 *
 * 입력  
 *     - int num : 1 이상 8,000,000 미만의 정수
 *
 * 출력  
 *     - int : 몇 번 만에 1이 되었는지 반환 (단, 500회 초과 시 -1)
 *
 * 핵심 포인트  
 *     - num * 3 + 1 연산에서 오버플로우 발생 가능 → long 사용 필요
 *     - 종료 조건: num이 1이 되었거나, count가 500을 넘는 경우
 */

class Solution {
	
	public int solution(int num) {
		return collatz((long) num, 0);  // long으로 형변환하여 시작
	}
	
	public int collatz(long num, int count) {
		if (num == 1) return count;				 // 1에 도달 → 현재 count 반환
		if (count >= 500) return -1;			 // 500번 초과 → 실패

		// 짝수면 /2, 홀수면 *3 + 1 후 재귀 호출
		return collatz((num % 2 == 0) ? num / 2 : num * 3 + 1, count + 1);
	}
	
}
