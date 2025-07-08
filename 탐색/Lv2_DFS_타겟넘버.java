/*  
 * 문제 요약
 *     - n개의 음이 아닌 정수 배열 numbers에서 각 숫자에 + 또는 - 연산을 적용해 target 숫자를 만드는 방법의 수를 구하는 문제  
 *
 * 입력  
 *     - numbers: 2 이상 20 이하 크기의 정수 배열 (각 요소 1 이상 50 이하)  
 *     - target: 1 이상 1000 이하 자연수  
 *
 * 출력  
 *     - target을 만들 수 있는 방법의 수 (int)  
 *
 * 핵심 포인트  
 *     - DFS로 각 숫자에 +, - 연산을 적용한 모든 조합을 탐색 (최대 2^20 가지 경우)  
 *     - sum == target인 경우를 카운트  
 */

class Solution {
	
	int[] numbers;
	int target;
	
	public int solution(int[] numbers, int target) {
		this.numbers = numbers;
		this.target = target;
		return dfs(0, 0);
	}
	
	public int dfs(int index, int sum) {
		// 모든 숫자를 사용했을 때 sum이 target과 같으면 1, 아니면 0 반환
		if (index == numbers.length) {
			return (sum == target) ? 1 : 0;
		}
		
		// 현재 숫자를 + 또는 -로 적용한 두 경우를 탐색
		return dfs(index + 1, sum + numbers[index]) + dfs(index + 1, sum - numbers[index]);
	}
	
}
