/*  
 * 문제 요약  
 *     - 진열대에 나열된 보석들 중 모든 종류의 보석을 최소 1개씩 포함하는 가장 짧은 구간을 찾아 반환하는 문제  
 *
 * 입력  
 *     - gems: 보석 이름이 나열된 문자열 배열 (1 ≤ gems.length ≤ 100,000)  
 *
 * 출력  
 *     - 모든 종류의 보석을 포함하는 가장 짧은 구간 [시작 진열대 번호, 끝 진열대 번호] (1-based)  
 *
 * 핵심 포인트  
 *     - 보석 종류 수를 구한 후, 투 포인터와 해시맵을 이용해 윈도우 내 보석 상태를 관리  
 *     - gemMap에 현재 구간의 보석 개수를 저장하며, 모든 종류를 포함할 경우마다 최소 길이 갱신  
 *     - while 조건을 명시적으로 설정해 무한루프 없이 안정적으로 탐색  
 */

class Solution {
	
	public int[] solution(String[] gems) {
		int kind = (int) Arrays.stream(gems).distinct().count();  // 전체 보석 종류 수
		Map<String, Integer> gemMap = new HashMap<>();  // 현재 윈도우 내 보석 개수
		
		int[] answer = new int[] {1, gems.length};  // 초기값: 전체 구간
		int left = 0, right = 0;
		
		gemMap.merge(gems[0], 1, Integer::sum);  // 첫 보석 추가
		
		while (left <= right) {
			// 모든 종류 포함 + 구간 길이가 더 짧으면 갱신
			if (gemMap.size() == kind && right - left < answer[1] - answer[0]) {
				answer[0] = left + 1;
				answer[1] = right + 1;
			}
			
			// 아직 모든 종류를 포함하지 못했다면 오른쪽 확장
			if (gemMap.size() < kind) {
				if (++right == gems.length) break;
				gemMap.merge(gems[right], 1, Integer::sum);
			} 
			// 모든 종류 포함 중이면 왼쪽 줄여보기
			else {
				gemMap.merge(gems[left], -1, Integer::sum);
				if (gemMap.get(gems[left]) == 0) gemMap.remove(gems[left]);
				left++;
			}
		}
		
		return answer;
	}
}
