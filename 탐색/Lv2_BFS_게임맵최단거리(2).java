/*  
 * 문제 요약
 *     - 2D 게임 맵에서 (0,0) → (n-1,m-1)로 가는 최소 이동 칸 수 구하기
 *     - 이동은 상,하,좌,우 가능, 벽(0)은 이동 불가
 *
 * 입력  
 *     - int[][] maps : 1(이동 가능), 0(이동 불가)로 이루어진 n×m 맵
 *
 * 출력  
 *     - int : 최단 경로의 칸 수 (도달 불가능하면 -1)
 *
 * 핵심 포인트  
 *     - BFS로 최단 거리 탐색
 *     - 방문 여부 배열로 중복 방지
 */

class Solution {
	
	int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	int[] dy = {0, 1, 0, -1};
	
	public int solution(int[][] maps) {
		int row = maps.length;
		int col = maps[0].length;
		
		Queue<int[]> que = new LinkedList<>();
		boolean[][] visited = new boolean[row][col];
		
		// 시작 위치 (0,0)과 초기 거리(1) 설정
		que.add(new int[] {0, 0, 1});
		visited[0][0] = true;
		
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			
			// 현재 위치가 도착 지점이면 거리 반환
			if (cur[0] == row - 1 && cur[1] == col - 1) return cur[2];
			
			// 4방향 탐색
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				// 맵 범위를 벗어나면 무시
				if (nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
				// 이미 방문했거나 벽이면 무시
				if (visited[nx][ny] || maps[nx][ny] == 0) continue;
				
				// 다음 위치를 큐에 추가하고 방문 처리
				que.add(new int[] {nx, ny, cur[2] + 1});
				visited[nx][ny] = true;
			}
		}
		
		// 도착 불가능한 경우
		return -1;
	}
}
