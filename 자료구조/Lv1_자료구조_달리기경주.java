/*
 * 문제 요약
 *     - 매년 열리는 달리기 경주에서, 해설진은 앞선 선수를 추월한 선수의 이름을 부름
 *     - 불린 선수는 자기 앞의 선수를 추월하여 두 선수의 순위가 바뀜
 *     - 주어진 호출(callings) 순서에 따라 최종 등수 배열을 구하는 문제
 *
 * 입력
 *     - String[] players : 초기 등수 순서대로 나열된 선수 이름 배열 (중복 없음, 최대 50,000명)
 *     - String[] callings : 추월이 발생한 선수 이름들이 순서대로 담긴 배열 (최대 1,000,000건)
 *
 * 출력
 *     - String[] : 최종 등수 순서대로 나열된 선수 이름 배열
 *
 * 핵심 포인트
 *     - 호출된 선수는 반드시 앞 사람이 존재함 (즉, 최소 2등 이상)
 *     - 호출된 선수와 앞 사람을 배열과 Map을 통해 O(1) 시간에 swap
 *     - 선수 이름 → 현재 인덱스를 Map으로 관리해 빠른 인덱스 조회 및 업데이트
 */

class Solution {
	
	public String[] solution(String[] players, String[] callings) {
		
		Map<String, Integer> playerMap = new HashMap<>();
		for (int i = 0; i < players.length; i++) {
			playerMap.put(players[i], i);
		}
		
		for (String calling : callings) {
			int calledIdx = playerMap.get(calling);
			
			String calledPlayer = players[calledIdx];
			String passedPlayer = players[calledIdx - 1];
			
			players[calledIdx - 1] = calledPlayer;
			players[calledIdx] = passedPlayer;
			
			playerMap.put(calledPlayer, calledIdx - 1);
			playerMap.put(passedPlayer, calledIdx);
		}
		
		return players;
	}
}
