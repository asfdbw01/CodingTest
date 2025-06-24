/*  
 * 문제 요약  
 *     - user_id 목록 중에서 banned_id의 마스킹 패턴과 일치하는 사용자 조합을 구함  
 *     - banned_id 순서에 맞춰 user_id를 매칭하되, 동일한 user_id를 여러 번 사용할 수 없음  
 *     - 제재 아이디 조합이 내용만 같으면(순서 무관) 같은 것으로 간주하여 경우의 수를 센다  
 *
 * 입력  
 *     - user_id: 응모자 아이디 배열 (1~8명)  
 *     - banned_id: 불량 사용자 마스킹 패턴 배열 (1~user_id.length 이하)  
 *
 * 출력  
 *     - 제재 아이디 목록의 가능한 서로 다른 경우의 수  
 *
 * 핵심 포인트  
 *     - 각 banned_id에 대해 가능한 user_id 인덱스 사전 매핑  
 *     - DFS로 가능한 모든 매핑 조합을 탐색 (중복 없이)  
 *     - 중복된 조합은 비트마스크로 표현하여 Set에 저장 → 순서와 중복 문제 해결
 */

class Solution {
	
	Set<Integer> maskSet;

	public int solution(String[] user_id, String[] banned_id) {
		this.maskSet = new HashSet<>();
		
		// banned_id마다 매칭 가능한 user_id 인덱스 리스트 구성
		Map<Integer, List<Integer>> matchMap = new HashMap<>();
		
		for (int i = 0; i < banned_id.length; i++) {
			for (int j = 0; j < user_id.length; j++) {
				if (isMatched(banned_id[i], user_id[j])) {
					matchMap.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
				}
			}
		}
		
		// DFS로 가능한 조합 탐색 (비트마스크 사용)
		dfs(matchMap, 0, 0);
		
		return maskSet.size();
	}

	// DFS로 banned_id의 각 위치에 가능한 user_id 인덱스를 매핑
	public void dfs(Map<Integer, List<Integer>> matchMap, int mask, int depth) {
		// 모든 banned_id에 매핑을 완료한 경우
		if (depth == matchMap.size()) {
			maskSet.add(mask);  // 조합 저장 (순서 무시됨)
			return;
		}
		
		// 현재 banned_id 위치에 가능한 user_id 인덱스를 순회
		for (int index : matchMap.get(depth)) {
			if ((mask & (1 << index)) == 0) {  // 이미 사용된 인덱스가 아니면
				dfs(matchMap, mask | (1 << index), depth + 1);  // 인덱스를 추가하고 다음 단계로
			}
		}
	}

	// banned_id와 user_id가 패턴이 일치하는지 확인
	public boolean isMatched(String mask, String str) {
		if (mask.length() != str.length()) return false;
		
		for (int i = 0; i < mask.length(); i++) {
			if (mask.charAt(i) != str.charAt(i) && mask.charAt(i) != '*') return false;
		}
		
		return true;
	}
}
