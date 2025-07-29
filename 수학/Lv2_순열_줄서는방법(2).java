/*  
 * 문제 요약
 *     - 1번부터 n번까지 번호가 매겨진 n명이 줄을 서는 모든 경우 중,
 *       사전 순으로 k번째에 해당하는 줄 서는 방법을 반환
 *
 * 입력  
 *     - int n : 사람 수 (1 이상 20 이하)
 *     - long k : 사전 순으로 몇 번째 줄인지 (1 이상 n! 이하)
 *
 * 출력  
 *     - int[] : k번째 줄서기 방법을 담은 배열
 *
 * 핵심 포인트  
 *     - 사전 순으로 줄을 서는 순열 중 k번째를 찾기 위해, 각 자리마다 경우의 수(factorial)를 활용
 *     - 현재 자리에 어떤 수가 올지 정하고, 해당 수를 제거한 뒤 다음 자리 탐색
 *     - 팩토리얼 감소와 k 갱신을 반복하면서 위치를 결정
 */

class Solution {
	
	public int[] solution(int n, long k) {
		int[] answer = new int[n];
		
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= n; i++) list.add(i);
		
		long fact = 1;
		for (int i = 2; i <= n; i++) fact *= i;
		
		k--; // 0-based 인덱스로 변환
		for (int i = 0; i < n; i++) {
			fact /= (n - i);				// 현재 자리에서의 그룹 크기
			int index = (int) (k / fact);	// 현재 자리에서 선택할 숫자 인덱스
			
			answer[i] = list.get(index);	// 해당 숫자 선택
			list.remove(index);				// 사용한 숫자 제거
			k %= fact;						// 남은 k 업데이트
		}
		
		return answer;
	}
	
}
