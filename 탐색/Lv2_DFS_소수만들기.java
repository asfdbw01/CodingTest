/*  
 * 문제 요약
 *     - 주어진 정수 배열에서 서로 다른 3개의 수를 더한 값이 소수인 경우의 개수를 구하는 문제  
 *
 * 입력  
 *     - int[] nums : 서로 다른 정수들이 담긴 배열 (길이 최대 50)  
 *
 * 출력  
 *     - 세 수의 합이 소수가 되는 조합의 개수 (정수)  
 *
 * 핵심 포인트  
 *     - 조합(C(n, 3))을 재귀로 구하되, depth == 3일 때만 소수 판별  
 *     - 소수 판별 시 √num까지만 검사하여 최적화  
 *     - 중복 조합 방지를 위해 인덱스를 기준으로 다음 숫자부터 탐색  
 */

class Solution {
	
	public int solution(int[] nums) {
		return dfs(0, -1, 0, nums);
	}

	// nums 배열에서 3개의 수를 선택하여 그 합이 소수인지 판별
	public int dfs(int sum, int index, int depth, int[] nums) {
		if (depth == 3) return isPrime(sum) ? 1 : 0;

		int count = 0;
		for (int i = index + 1; i < nums.length; i++) {
			count += dfs(sum + nums[i], i, depth + 1, nums);
		}
		
		return count;
	}

	// 소수 판별 함수
	public boolean isPrime(int num) {
		if (num == 2) return true;
		if (num <= 1 || num % 2 == 0) return false;

		for (int i = 3; i <= Math.sqrt(num); i += 2) {
			if (num % i == 0) return false;
		}
		
		return true;
	}
	
}
