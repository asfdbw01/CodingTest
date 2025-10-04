/*  
 * 문제 요약
 *     - 숫자와 연산자(+, -, *)로 이루어진 수식에서 연산자 우선순위를 임의로 정해 계산했을 때
 *       나올 수 있는 결과값의 절댓값 중 최댓값을 구하는 문제
 *
 * 입력  
 *     - expression: 수식 문자열 (길이 3 이상 100 이하, 피연산자는 0 이상 999 이하의 정수)
 *
 * 출력  
 *     - 가능한 연산자 우선순위 조합 중 가장 큰 절댓값 결과를 long으로 반환
 *
 * 핵심 포인트  
 *     - 연산자의 우선순위는 중복 없이 순열로 적용해야 함 (3! = 6가지)
 *     - 연산을 진행할 때마다 리스트 복사 후 해당 우선순위 연산자만 처리
 *     - 계산 결과는 항상 절댓값으로 처리하며 최대값 갱신
 */

class Solution {
	
	Set<Long> operSet;
	
	public long solution(String expression) {
		this.operSet = new HashSet<>();
		
		List<Long> list = new ArrayList<>();
		
		// 수식을 숫자와 연산자로 분리해 리스트에 저장
		StringBuilder sb = new StringBuilder();
		for (char c : expression.toCharArray()) {
			if (Character.isDigit(c)) {
				sb.append(c); // 숫자 누적
			} else {
				list.add(Long.parseLong(sb.toString())); // 숫자 추가
				list.add((long) c);                      // 연산자 추가 (long으로 저장)
				operSet.add((long) c);                   // 연산자 종류 수집
				sb.setLength(0);                         // 숫자 버퍼 초기화
			}
		}
		list.add(Long.parseLong(sb.toString())); // 마지막 숫자 추가
		
		// DFS로 연산자 우선순위 조합을 순열로 탐색
		return dfs(list, new HashSet<Long>());
	}
	
	public long dfs(List<Long> list, Set<Long> visited) {
		// 수식이 하나의 값으로 줄어들면 절댓값 반환
		if (list.size() == 1) return Math.abs(list.get(0));
		long max = 0;
		
		// 아직 처리하지 않은 연산자에 대해 우선순위 적용
		for (long oper : operSet) {
			if (visited.contains(oper)) continue;
			
			// 리스트 복사 후 현재 연산자에 대해 계산
			List<Long> next = new ArrayList<>(list);
			int index = 1;
			
			// 리스트를 순회하면서 해당 연산자 계산 수행
			while (index < next.size()) {
				if (next.get(index) == oper) {
					// 계산 결과로 앞 숫자 자리에 덮어쓰기
					next.set(index - 1, calc(next.get(index - 1), next.get(index + 1), next.get(index)));
					
					// 연산자와 오른쪽 숫자 제거
					next.remove(index);     // 연산자 제거
					next.remove(index);     // 오른쪽 숫자 제거 (덮어쓴 결과 오른쪽으로 이동했으므로 index 그대로)
					
					// index는 그대로 유지하여 연속된 같은 연산자 처리 가능
				} else {
					index += 2; // 다음 연산자 위치로 이동
				}
			}

			// 현재 연산자 우선순위로 처리 완료
			visited.add(oper);
			max = Math.max(max, dfs(next, visited)); // 재귀 탐색
			visited.remove(oper); // 백트래킹
		}
		
		return max;
	}
	
	public long calc(long a, long b, long oper) {
		// 연산자에 따라 계산 수행
		return switch ((int) oper) {
			case '*' -> a * b;
			case '+' -> a + b;
			case '-' -> a - b;
			default -> 0;
		};
	}
}
