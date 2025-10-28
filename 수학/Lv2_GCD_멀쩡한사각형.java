/*  
 * 문제 요약
 *     - W×H 크기의 격자에서 (0,0)에서 (W,H)까지 대각선으로 잘렸을 때
 *       사용할 수 있는 1×1 정사각형의 개수를 구하는 문제
 *
 * 입력  
 *     - w: 가로 길이 (1 이상 10^8 이하)
 *     - h: 세로 길이 (1 이상 10^8 이하)
 *
 * 출력  
 *     - 대각선에 의해 잘리지 않은 1×1 정사각형의 개수 (long형 반환)
 *
 * 핵심 포인트  
 *     - 전체 격자 수는 w * h
 *     - 잘리는 사각형 수는 w + h - gcd(w, h)
 *     - 따라서 정답은 w * h - (w + h - gcd)
 *     - 오버플로우 방지를 위해 long 캐스팅 필요
 */

class Solution {
	
	public long solution(int w, int h) {
		// 전체 사각형 수에서 대각선이 지나가며 잘리는 사각형 수를 뺌
		return (long) w * h - (w + h - gcd(w, h));
	}
	
	// 유클리드 호제법을 이용한 최대공약수 계산
	public int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
	
}
