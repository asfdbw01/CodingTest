/*  
 * 문제 요약  
 *     - N x N 격자 보드에서 출발점 (0,0) → 도착점 (N-1,N-1)까지 경주로를 건설할 때 최소 비용을 계산  
 *     - 직선 도로는 100원, 코너가 생기면 500원이 추가로 발생함  
 *
 * 입력  
 *     - board: 벽(1)과 빈칸(0)으로 구성된 2차원 정사각 배열 (3 ≤ N ≤ 25)  
 *
 * 출력  
 *     - 도착지까지 도달할 수 있는 최소 건설 비용 (int)  
 *
 * 핵심 포인트  
 *     - 방향 전환 여부에 따라 코너 비용이 발생하므로, 방향 상태를 포함한 최소 비용 경로 탐색 필요  
 *     - 각 좌표마다 4방향별 최소 비용을 따로 저장 (3차원 배열 costs[x][y][dir])  
 *     - 우선순위 큐를 이용해 현재까지 누적 비용이 가장 적은 경로를 먼저 탐색 (다익스트라 변형)  
 */

class Solution {
	
	int[] dx = {-1, 0, 1, 0};  // 상, 우, 하, 좌
	int[] dy = {0, 1, 0, -1};
	
	public int solution(int[][] board) {
		int n = board.length;
		
		// 각 위치에서 각 방향으로 도달했을 때의 최소 비용을 저장
		int[][][] costs = new int[n][n][4];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Arrays.fill(costs[i][j], Integer.MAX_VALUE);
			}
		}
		
		// 우선순위 큐: 비용이 낮은 순으로 처리
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[3] - o2[3]);
		pq.add(new int[] {0, 0, 1, 0});  // 초기: (0,0)에서 오른쪽
		pq.add(new int[] {0, 0, 2, 0});  // 초기: (0,0)에서 아래쪽
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int x = cur[0], y = cur[1], dir = cur[2], cost = cur[3];
			
			// 도착 지점 도달 시 탐색 종료
			if (x == n - 1 && y == n - 1) break;
			
			// 4방향으로 이동 시도
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i], ny = y + dy[i];
				int nCost = (i != dir) ? cost + 600 : cost + 100;
				
				// 범위 초과 또는 벽이면 무시
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == 1) continue;
				
				// 해당 방향으로의 최소 비용 갱신 가능하면 큐에 삽입
				if (nCost < costs[nx][ny][i]) {
					costs[nx][ny][i] = nCost;
					pq.add(new int[] {nx, ny, i, nCost});
				}
			}
		}
		
		// 도착 지점까지의 방향별 최소 비용 중 가장 작은 값 반환
		return Arrays.stream(costs[n - 1][n - 1]).min().getAsInt();
	}
}
