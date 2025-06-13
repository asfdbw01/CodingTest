/*  
 * 문제 요약
 *     - 유저는 일정 피로도를 소모해 던전을 탐험할 수 있음
 *     - 각 던전은 [최소 필요 피로도, 소모 피로도]로 주어짐
 *     - 하루에 한 번씩 던전을 탐험할 수 있으며, 각 던전은 1회만 탐험 가능
 *     - 현재 피로도 k에서 출발해 탐험할 수 있는 최대 던전 수를 구해야 함
 *  
 * 입력
 *     - int k: 유저의 현재 피로도 (1 이상 5,000 이하)
 *     - int[][] dungeons: 던전 정보 배열 (1 ≤ 길이 ≤ 8), 각 원소는 [최소 필요 피로도, 소모 피로도]
 *  
 * 출력
 *     - int: 탐험 가능한 최대 던전 수
 *  
 * 핵심 포인트
 *     - 던전 수가 작으므로 DFS를 통한 모든 순열 탐색 가능
 *     - 방문 여부를 비트마스크로 표현하여 추가 배열 없이 관리
 *     - 방문 상태를 매번 복사하며 넘기므로 전통적 백트래킹은 아님
 */

class Solution {
	
	public int solution(int k, int[][] dungeons) {
		// DFS 탐색 시작: 현재 피로도 k, 방문 상태 0, 탐험 개수 0
		return dfs(k, dungeons, 0, 0);
	}
	
	public int dfs(int cur, int[][] dungeons, int visited, int cnt) {
		int max = cnt;
		
		for (int i = 0; i < dungeons.length; i++) {
			// 아직 방문하지 않았고, 현재 피로도로 i번 던전 탐험 가능할 때
			if ((visited & (1 << i)) == 0 && dungeons[i][0] <= cur) {
				// 방문 처리: visited에 i번째 비트를 더한 값을 다음 호출에 넘김
				max = Math.max(max, dfs(cur - dungeons[i][1], dungeons, visited | (1 << i), cnt + 1));
			}
		}
		
		return max;
	}
	
}
