/*  
 * 문제 요약  
 *     - 마라톤 참가자 중 완주하지 못한 단 1명의 이름을 찾아 반환한다.
 *
 * 입력  
 *     - participant: 마라톤 참가자 이름 배열 (n명)
 *     - completion: 완주한 참가자 이름 배열 (n - 1명)
 *
 * 출력  
 *     - 완주하지 못한 1명의 이름
 *
 * 핵심 포인트  
 *     - 동명이인이 존재할 수 있으므로 이름을 key, 등장 횟수를 value로 관리해야 함
 *     - participant에서 +1, completion에서 -1을 하여 최종적으로 count가 남는 이름을 반환
 *     - HashMap을 통해 O(n) 시간복잡도 보장
 */

class Solution {
	
	public String solution(String[] participant, String[] completion) {
		Map<String, Integer> playerMap = new HashMap<>();
		
		// 참가자 이름 카운트
		for (String player : participant) {
			playerMap.merge(player, 1, Integer::sum);
		}
		
		// 완주한 사람은 카운트 감소
		for (String player : completion) {
			playerMap.merge(player, -1, Integer::sum);
			if (playerMap.get(player) == 0) playerMap.remove(player); // 0이 되면 제거
		}
		
		// 남은 하나가 완주하지 못한 선수
		return playerMap.keySet().stream().findFirst().get();
	}
	
}
