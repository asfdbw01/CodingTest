/*  
 * 문제 요약  
 *     - 표에서 현재 선택된 행을 기준으로 위/아래로 이동하거나, 삭제/복구 명령을 처리하며  
 *       마지막에 삭제된 행은 'X', 존재하는 행은 'O'로 표 상태를 출력하는 문제  
 *
 * 입력  
 *     - n: 표의 총 행 개수 (5 ≤ n ≤ 1,000,000)  
 *     - k: 처음 선택된 행 (0 ≤ k < n)  
 *     - cmd: 명령어 목록 (최대 200,000개)  
 *
 * 출력  
 *     - 명령 처리 후 각 행의 상태를 "O", "X"로 이어붙인 문자열  
 *
 * 핵심 포인트  
 *     - 삭제와 복구 시 인접 행을 추적하는 prev/next 배열 필요  
 *     - U/D는 이동, C는 삭제 + 스택 기록, Z는 복구 → 이중 연결리스트 형태로 관리  
 *     - 삭제 여부 추적용 boolean 배열 필요
 */

class Solution {
	
	public String solution(int n, int k, String[] cmd) {
		// 삭제 여부 체크용 배열 및 삭제 이력 스택
		boolean[] isDeleted = new boolean[n];
		Deque<Integer> deleted = new ArrayDeque<>();
		
		// prev[i]: i번째 행의 이전 행, next[i]: 다음 행
		int[] prev = IntStream.range(0, n).map(i -> i - 1).toArray();
		int[] next = IntStream.range(0, n).map(i -> i == n - 1 ? -1 : i + 1).toArray();
		
		// 명령어 처리
		for (String c : cmd) {
			String[] parts = c.split(" ");
			int nk = k;

			switch (parts[0]) {
			case "U":  // 위로 이동
				nk = move(k, Integer.parseInt(parts[1]), prev);
				k = (nk == -1) ? next[k] : nk;
				break;

			case "D":  // 아래로 이동
				nk = move(k, Integer.parseInt(parts[1]), next);
				k = (nk == -1) ? prev[k] : nk;
				break;

			case "C":  // 현재 행 삭제
				deleted.add(k);        // 삭제 이력 기록
				isDeleted[k] = true;   // 삭제 표시

				// 연결 끊기
				if (next[k] != -1) prev[next[k]] = prev[k];
				if (prev[k] != -1) next[prev[k]] = next[k];

				// 다음 선택 행 갱신
				k = (next[k] == -1) ? prev[k] : next[k];
				break;

			case "Z":  // 최근 삭제 복구
				int restore = deleted.pollLast();
				isDeleted[restore] = false;

				// 연결 복구
				if (next[restore] != -1) prev[next[restore]] = restore;
				if (prev[restore] != -1) next[prev[restore]] = restore;
				break;
			}
		}
		
		// 결과 문자열 구성 (삭제된 행은 'X', 남은 행은 'O')
		return IntStream.range(0, isDeleted.length)
				.mapToObj(i -> isDeleted[i] ? "X" : "O")
				.collect(Collectors.joining());
	}
	
	// 주어진 방향으로 move칸 이동, 중간에 끝나면 -1 반환
	public int move(int k, int move, int[] link) {
		for (int i = 0; i < move; i++) {
			if ((k = link[k]) == -1) return -1;
		}
		return k;
	}
}
