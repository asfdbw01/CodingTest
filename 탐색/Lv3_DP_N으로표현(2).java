/*  
 * 문제 요약
 *     - 숫자 N을 최대 8번까지 사용해 사칙연산과 숫자 이어붙이기만으로 목표 수 number를 만들 수 있는지 확인하고,
 *       가능하다면 N을 최소 몇 번 사용해야 하는지를 구하는 문제
 *
 * 입력  
 *     - N: 사용 가능한 숫자 (1 이상 9 이하)
 *     - number: 만들고자 하는 수 (1 이상 32,000 이하)
 *
 * 출력  
 *     - N을 최소 몇 번 사용해야 number를 만들 수 있는지 (최대 8까지), 만들 수 없다면 -1
 *
 * 핵심 포인트  
 *     - 숫자 반복 (예: N=5일 때 5, 55, 555...)을 고려해야 함  
 *     - 사칙연산 조합으로 DP 확장: dp[i]는 dp[j] + dp[i-j] 형태로 모든 조합 수행  
 *     - 중복 계산 방지를 위해 Set 사용  
 *     - 정수 나눗셈만 허용되며, 나머지는 무시함
 */

class Solution {
	
	public int solution(int N, int number) {
		List<Set<Integer>> dp = new ArrayList<>();
		for (int i = 0; i <= 8; i++) {
			dp.add(new HashSet<>());
		}
		
		for (int i = 1; i <= 8; i++) {
			// N을 i번 반복한 수 (예: 5, 55, 555 ...)
			int repeatN = Integer.parseInt(String.valueOf(N).repeat(i));
			dp.get(i).add(repeatN);
			
			// j + (i - j) 형태로 사칙연산 조합 수행
			for (int j = 1; j < i; j++) {
				for (int a : dp.get(j)) {
					for (int b : dp.get(i - j)) {
						dp.get(i).add(a + b);
						dp.get(i).add(a - b);
						dp.get(i).add(b - a);
						dp.get(i).add(a * b);
						
						if (b != 0) dp.get(i).add(a / b);
						if (a != 0) dp.get(i).add(b / a);
					}
				}
			}
			
			// 목표 숫자를 만들었으면 현재 사용 횟수 반환
			if (dp.get(i).contains(number)) return i;
		}
		
		// 8번 이내로 만들 수 없으면 -1
		return -1;
	}
	
}
