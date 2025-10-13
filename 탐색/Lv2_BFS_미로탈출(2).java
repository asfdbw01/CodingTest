/*
 * 문제 요약
 *     - 시작 지점(S)에서 레버(L)를 당긴 후, 출구(E)로 이동하여 탈출해야 함
 *     - 이동은 상하좌우 인접한 통로(O), 레버(L), 출구(E)만 가능하며 벽(X)은 불가
 *     - 시작 → 레버까지 최소 시간 + 레버 → 출구까지 최소 시간의 합을 구해야 하며, 도달 불가능하면 -1 반환
 * 
 * 입력
 *     - maps: 문자열 배열 (5 ≤ maps.length ≤ 100, maps[i].length() ≤ 100)
 *     - 'S', 'E', 'L' 은 각각 하나씩만 존재
 * 
 * 출력
 *     - 미로를 탈출하기 위한 최소 시간 (int), 탈출 불가능 시 -1
 * 
 * 핵심 포인트
 *     - 경로는 두 구간으로 분리됨: S → L, L → E (BFS 두 번)
 *     - BFS는 한 칸 이동에 1초 소요되므로 가장 먼저 도착한 시간이 최단 경로
 *     - visited는 큐에 넣을 때 체크해야 중복 방문 및 시간초과 방지
 */

class Solution {
	
	int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상우하좌 이동

	public int solution(String[] maps) {
		int[] startLoc = null;
		int[] leverLoc = null;

		// 시작 위치와 레버 위치 탐색
		for (int i = 0; i < maps.length; i++) {
			for (int j = 0; j < maps[i].length(); j++) {
				char c = maps[i].charAt(j);
				if (c == 'S') startLoc = new int[] {i, j};
				else if (c == 'L') leverLoc = new int[] {i, j};
			}
		}

		// S → L, L → E 각각 BFS 수행
		int startToLever = bfs(maps, startLoc, 'L');
		int leverToEnd = bfs(maps, leverLoc, 'E');

		if (startToLever == -1 || leverToEnd == -1) return -1; // 둘 중 하나라도 불가능하면 -1
		return startToLever + leverToEnd;
	}

	// BFS: 시작 좌표(start)에서 목표 문자인 end까지 최소 거리 탐색
	public int bfs(String[] maps, int[] start, char end) {
	    int xLen = maps.length;
	    int yLen = maps[0].length();

	    boolean[][] visited = new boolean[xLen][yLen];
	    Queue<int[]> que = new LinkedList<>();

	    // 초기 위치 큐에 삽입 및 방문 처리
	    que.add(new int[] {start[0], start[1], 0});
	    visited[start[0]][start[1]] = true;

	    while (!que.isEmpty()) {
	        int[] loc = que.poll();

	        for (int[] dir : dirs) {
	            int nx = loc[0] + dir[0];
	            int ny = loc[1] + dir[1];

	            // 범위 초과 or 이미 방문한 경우
	            if (nx < 0 || nx >= xLen || ny < 0 || ny >= yLen) continue;
	            if (visited[nx][ny]) continue;

	            char c = maps[nx].charAt(ny);

	            if (c == 'X') continue; // 벽은 통과 불가
	            if (c == end) return loc[2] + 1; // 목적지 도달 시 시간 반환

	            visited[nx][ny] = true; // 방문 처리 및 큐 삽입
	            que.add(new int[] {nx, ny, loc[2] + 1});
	        }
	    }

	    return -1; // 도달 불가능
	}
}
