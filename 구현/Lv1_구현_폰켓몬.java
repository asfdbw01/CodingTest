/*  
 * 문제 요약
 *     - N마리 폰켓몬 중 N/2마리를 선택할 때, 가장 다양한 종류의 폰켓몬을 선택하는 경우의 종류 수를 구하는 문제  
 *
 * 입력  
 *     - int[] nums : 폰켓몬의 종류 번호 배열 (길이 N, 항상 짝수)  
 *
 * 출력  
 *     - int : 선택할 수 있는 폰켓몬 종류 개수의 최댓값  
 *
 * 핵심 포인트  
 *     - 선택 가능한 최대 마리 수는 N/2  
 *     - 실제 가능한 종류 수는 nums 배열의 고유한 값 개수  
 *     - 두 값의 최소값이 정답  
 */

class Solution {
	
	public int solution(int[] nums) {
		// distinct()로 고유한 폰켓몬 종류 수 계산
		// N/2와 고유 종류 수 중 작은 값을 반환
		return Math.min(nums.length / 2, (int) Arrays.stream(nums).distinct().count());
	}
	
}
