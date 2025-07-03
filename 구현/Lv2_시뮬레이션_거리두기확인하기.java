/*  
 * 문제 요약  
 *     - 5x5 크기의 대기실 5개에 대해 거리두기 규칙을 지키고 있는지 확인  
 *     - 규칙: 사람(P) 간의 맨해튼 거리가 2 이하인 경우 파티션(X)이 없으면 거리두기 실패  
 *
 * 입력  
 *     - places: 5개의 대기실 배열 (각 대기실은 String[5], 각 문자열은 길이 5)  
 *
 * 출력  
 *     - 거리두기를 지킨 대기실은 1, 하나라도 위반 시 0으로 구성된 int[5] 배열  
 *
 * 핵심 포인트  
 *     - 맨해튼 거리 1, 2에 대해 각각 규칙 위반 여부 검사  
 *     - 중간이 빈 테이블(O)인 경우 추가 검사 필요  
 *     - 5x5 고정 크기이므로 완전탐색으로 충분히 해결 가능
 */

class Solution {
	
	int[] dx = {-1, 0, 1, 0};  // 상우하좌
	int[] dy = {0, 1, 0, -1};
	
	public int[] solution(String[][] places) {
		int[] answer = new int[places.length];
		Arrays.fill(answer, 1);  // 초기값은 모두 거리두기 지킴 (1)

		Outter : for (int i = 0; i < places.length; i++) {
			for (int j = 0; j < places[i].length; j++) {
				for (int k = 0; k < places[i][j].length(); k++) {
					if (places[i][j].charAt(k) != 'P') continue;

					// 현재 사람 기준으로 4방향 확인
					for (int l = 0; l < 4; l++) {
						int nx = j + dx[l];
						int ny = k + dy[l];
						if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;

						// 인접한 곳에 사람 있을 경우 → 거리두기 실패
						if (places[i][nx].charAt(ny) == 'P') {
							answer[i] = 0;
							continue Outter;
						}
						// 파티션이면 막혀있으므로 스킵
						if (places[i][nx].charAt(ny) == 'X') continue;

						// 빈 테이블이면 → 한 칸 더 뻗어서 확인 (거리 2)
						for (int m = 0; m < 4; m++) {
							if ((m + 2) % 4 == l) continue;  // 반대방향은 제외

							int nnx = nx + dx[m];
							int nny = ny + dy[m];
							if (nnx < 0 || nnx >= 5 || nny < 0 || nny >= 5) continue;

							if (places[i][nnx].charAt(nny) == 'P') {
								answer[i] = 0;
								continue Outter;
							}
						}
					}
				}
			}
		}
		
		return answer;
	}
}
