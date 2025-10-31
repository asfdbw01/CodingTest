/*  
 * 문제 요약
 *     - 부서별로 신청한 금액이 주어지고, 총 예산 안에서 최대한 많은 부서에 물품을 지원하려는 문제
 *
 * 입력  
 *     - int[] d: 부서별 신청 금액 배열
 *     - int budget: 전체 예산
 *
 * 출력  
 *     - int: 예산 내에서 지원 가능한 최대 부서 수
 *
 * 핵심 포인트  
 *     - 더 적은 금액의 부서부터 지원하면 최대한 많은 부서를 도울 수 있음
 *     - 금액 배열을 오름차순 정렬 후, 예산이 허용되는 한 부서 순차적으로 지원
 */

class Solution {
	
	public int solution(int[] d, int budget) {
		Arrays.sort(d);  // 신청 금액 오름차순 정렬
		
		int index = 0;
		
		// 예산이 허용되는 한 부서를 순차적으로 지원
		while (index < d.length && d[index] <= budget) {
			budget -= d[index++];
		}
		
		return index;  // 지원한 부서 수
	}
}
