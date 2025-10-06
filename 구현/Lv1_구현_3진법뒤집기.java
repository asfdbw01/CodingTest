/*  
 * 문제 요약
 *     - 자연수 n을 3진법으로 변환한 뒤, 이를 뒤집고 다시 10진법으로 변환한 결과를 구하는 문제
 *
 * 입력  
 *     - int n: 1 이상 100,000,000 이하의 자연수
 *
 * 출력  
 *     - 3진법으로 변환 후 뒤집은 값을 10진법으로 변환한 결과 (int)
 *
 * 핵심 포인트  
 *     - Integer.toString(n, 3)으로 3진법 문자열로 변환
 *     - 뒤집힌 3진수를 각 자리마다 가중치를 곱해 합산하면 10진수로 변환 가능
 *     - Stream 처리 시 누적 곱(`mul *= 3`)을 유지해야 하므로 AtomicInteger를 활용하여 내부 상태 유지
 */

class Solution {
	
	public int solution(int n) {
		AtomicInteger mul = new AtomicInteger(1);
		
		// n을 3진법 문자열로 변환 → 문자 스트림으로 변환
		// 각 문자(digit)를 정수로 바꾸고, 자리값에 따라 누적 곱을 적용해 10진수로 환산
		return Integer.toString(n, 3)
				.chars()
				.map(c -> (c - '0') * mul.getAndAccumulate(3, (prev, f) -> prev * f))
				.sum();
	}
	
}
