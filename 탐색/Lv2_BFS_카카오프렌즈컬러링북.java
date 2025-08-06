/*  
 * 문제 요약
 *     - m×n 그림에서 같은 색상이 상하좌우로 연결된 영역 개수와 가장 큰 영역의 넓이를 구하는 문제
 *
 * 입력
 *     - m: 그림의 세로 길이 (1 ≤ m ≤ 100)
 *     - n: 그림의 가로 길이 (1 ≤ n ≤ 100)
 *     - picture: m×n 2차원 배열, 색상 정보 (0은 색칠 안 함)
 *
 * 출력
 *     - int[] : [영역 개수, 가장 큰 영역의 넓이]
 *
 * 핵심 포인트
 *     - BFS로 같은 색상이 연결된 영역 탐색
 *     - visited 배열로 중복 방문 방지
 *     - 각 영역의 넓이를 계산하여 최대값 갱신
 */

class Solution {
	
	int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	int[] dy = {0, 1, 0, -1};
	
	public int[] solution(int m, int n, int[][] picture) {
		int[] answer = new int[2]; // answer[0]: 영역 개수, answer[1]: 가장 큰 영역 넓이
		
		boolean[][] visited = new boolean[m][n];
		Queue<int[]> que = new LinkedList<>();
		
		// 전체 좌표 순회
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 색칠된 영역이고 아직 방문하지 않았다면 BFS 시작
				if (picture[i][j] != 0 && !visited[i][j]) {
					que.add(new int[] {i, j});
					visited[i][j] = true; // 시작점 방문 처리
					int count = 1; // 현재 영역 넓이 (시작점 포함)
					
					// BFS 탐색
					while (!que.isEmpty()) {
						int[] cur = que.poll();
						int x = cur[0], y = cur[1];
						
						// 상하좌우 인접 칸 확인
						for (int k = 0; k < 4; k++) {
							int nx = x + dx[k], ny = y + dy[k];
							
							// 범위를 벗어나면 무시
							if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
							// 이미 방문했거나 색이 다르면 무시
							if (visited[nx][ny] || picture[x][y] != picture[nx][ny]) continue;
							
							// 같은 색이고 방문 안 했으면 큐에 추가
							que.add(new int[] {nx, ny});
							visited[nx][ny] = true;
							count++; // 영역 넓이 증가
						}
					}
					
					// 영역 개수 증가
					answer[0]++;
					// 최대 영역 넓이 갱신
					if (count > answer[1]) answer[1] = count;
				}
			}
		}
		
		return answer;
	}
	
}
