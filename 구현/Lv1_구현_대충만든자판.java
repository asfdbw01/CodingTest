/*
 * 문제 요약
 *     - 휴대폰 자판에서 각 키에 여러 문자가 순서대로 할당되어 있으며, 같은 키를 연속 눌러 문자를 입력
 *     - 자판 전체(keymap)를 바탕으로 주어진 문자열들(targets)을 작성하는 최소 키 누름 횟수를 계산
 *     - 어떤 문자열은 자판에 없는 문자가 포함되어 작성 불가능할 수 있음 → 이 경우 -1 반환
 * 
 * 입력
 *     - String[] keymap : 각 자판 키에 할당된 문자들을 문자열로 나타낸 배열 (길이 ≤ 100, 문자열 길이 ≤ 100)
 *     - String[] targets : 작성하려는 문자열 배열 (길이 ≤ 100, 각 문자열 길이 ≤ 100)
 * 
 * 출력
 *     - int[] : 각 문자열을 작성하는 데 필요한 최소 키 누름 수를 담은 배열 (작성 불가능한 경우 -1)
 * 
 * 핵심 포인트
 *     - keymap의 각 문자에 대해 누르는 최소 횟수를 사전에 Map에 저장
 *     - targets의 각 문자마다 해당 최소 횟수를 더하고, 없는 문자는 -1 처리
 *     - O(N * M) 수준의 간단한 구현 문제
 */

class Solution {
	public int[] solution(String[] keymap, String[] targets) {
		int[] answer = new int[targets.length];
		
		// 각 문자별 최소 키 누름 횟수를 저장하는 맵
		Map<Character, Integer> charMap = new HashMap<>();
		
		// 모든 keymap에 대해 문자별 최소 누름 횟수 갱신
		for (String key : keymap) {
			for (int i = 0; i < key.length(); i++) {
				char c = key.charAt(i);
				// 아직 등록되지 않았거나 더 적은 횟수로 입력 가능한 경우 갱신
				if (!charMap.containsKey(c)) {
					charMap.put(c, i + 1);
				} else {
					charMap.put(c, Math.min(charMap.get(c), i + 1));
				}
			}
		}
		
		// 각 target 문자열에 대해 최소 누름 횟수 계산
		for (int i = 0; i < targets.length; i++) {
			int cnt = 0;
			for (int j = 0; j < targets[i].length(); j++) {
				char c = targets[i].charAt(j);
				// 해당 문자를 입력할 수 없는 경우
				if (!charMap.containsKey(c)) {
					cnt = -1;
					break;
				}
				// 최소 횟수 누적
				cnt += charMap.get(c);
			}
			answer[i] = cnt;
		}
		
		return answer;
	}
}
