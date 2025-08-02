/*  
 * 문제 요약
 *     - 문자열로 주어진 숫자 조각들을 조합하여 만들 수 있는 모든 수 중에서
 *       소수인 수의 개수를 구하는 문제
 *
 * 입력  
 *     - numbers: 1자리 이상 7자리 이하의 문자열 (0~9로 구성)
 *
 * 출력  
 *     - 조합으로 만들 수 있는 수들 중 소수의 개수 (중복 제거)
 *
 * 핵심 포인트  
 *     - 숫자 조합은 순서가 있는 순열로 모든 자리수를 생성해야 함 (1자리 ~ n자리)
 *     - 중복된 숫자 생성 방지를 위해 visited Set 사용
 *     - 이미 검사된 소수를 다시 검사하지 않도록 Set<Integer> prime으로 관리
 *     - 비트마스크를 이용해 사용된 숫자 인덱스를 효율적으로 체크
 *     - 소수 판별 시 짝수 제외, √n까지만 검사
 */

class Solution {
	
	String[] numberArr;
	Set<Integer> prime;
	
	public int solution(String numbers) {
		this.numberArr = numbers.split("");
		this.prime = new HashSet<>();
		
		// 각 숫자를 시작점으로 DFS 수행 (모든 자리수 조합 생성)
		for (int i = 0; i < numbers.length(); i++) {
			String num = numberArr[i];
			Set<String> visited = new HashSet<>();
			
			visited.add(num);
			dfs(num, 1 << i, visited);
		}
		
		return prime.size();
	}
	
	// DFS로 숫자 조합을 만들며 소수 판별 및 저장
	public void dfs(String num, int mask, Set<String> visited) {
		int n = Integer.parseInt(num);
		if (!prime.contains(n) && isPrime(n)) prime.add(n);
		
		for (int i = 0; i < numberArr.length; i++) {
			if (((1 << i) & mask) != 0) continue; // 이미 사용된 숫자면 건너뜀
			
			String nextNum = num + numberArr[i];
			if (visited.contains(nextNum)) continue; // 동일 숫자 조합 중복 방지
			
			visited.add(nextNum);
			dfs(nextNum, mask | (1 << i), visited);
			visited.remove(nextNum); // 백트래킹
		}
	}
	
	// 소수 판별
	public boolean isPrime(int num) {
		if (num == 2) return true;
		if (num == 1 || num % 2 == 0) return false;
		
		for (int i = 3; i <= Math.sqrt(num); i += 2) {
			if (num % i == 0) return false;
		}
		
		return true;
	}
	
}
