/*  
 * 문제 요약
 *     - 두 자연수를 입력받아 최대공약수(GCD)와 최소공배수(LCM)를 구해 배열로 반환
 *
 * 입력  
 *     - int n, m : 1 이상 1,000,000 이하의 자연수
 *
 * 출력  
 *     - int[] : [최대공약수, 최소공배수]
 *
 * 핵심 포인트  
 *     - GCD: 유클리드 호제법 사용
 *     - LCM: (n * m) / GCD 공식 사용 (곱셈 시 long 처리 주의)
 */

class Solution {
	
	public int[] solution(int n, int m) {
		int gcd = gcd(n, m);
		return new int[] {gcd, (int)((long) n * m / gcd)}; // long으로 캐스팅하여 오버플로우 방지
	}
	
	// 유클리드 호제법을 이용한 최대공약수 계산
	public int gcd(int a, int b) {
		return (b == 0) ? a : gcd(b, a % b);
	}
	
}
