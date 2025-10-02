/*
 * 문제 요약
 *     - 'X'는 바다, 숫자는 무인도 (식량량 의미)
 *     - 상하좌우로 연결된 숫자칸들은 하나의 무인도
 *     - 각 무인도의 총 식량량을 구해 오름차순 정렬하여 반환
 *     - 무인도가 없으면 [-1] 반환
 *
 * 입력
 *     - maps: String[] (지도 정보)
 *         - 3 ≤ maps.length ≤ 100
 *         - maps[i].length()는 모두 동일, 3 ≤ 길이 ≤ 100
 *         - 각 문자는 'X' 또는 '1'~'9'
 *
 * 출력
 *     - 각 무인도의 총 식량량을 오름차순 정렬한 int 배열
 *     - 무인도가 없으면 [-1]
 *
 * 핵심 포인트
 *     - DFS로 연결된 땅(숫자 칸)을 하나의 무인도로 탐색
 *     - 방문 여부를 visited로 관리
 *     - 누적 식량량은 DFS 호출 중 합산
 *     - 무인도마다 DFS 한 번 호출 → 결과 리스트에 저장
 */

class Solution {
	
	private String[] map;
	private int xLen, yLen;
	private boolean[][] visited;
	private final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public int[] solution(String[] maps) {
		this.map = maps;
		this.xLen = maps.length;
		this.yLen = maps[0].length();
		this.visited = new boolean[xLen][yLen];
		
		List<Integer> foods = new ArrayList<>();
		
		// 모든 칸을 순회하며 미방문 숫자칸이면 DFS 시작
		for (int i = 0; i < xLen; i++) {
			for (int j = 0; j < yLen; j++) {
				if (visited[i][j] || map[i].charAt(j) == 'X') continue;
				foods.add(dfs(i, j));
			}
		}
		
		// 무인도가 없는 경우
		if (foods.isEmpty()) return new int[] {-1};
		
		// 오름차순 정렬 후 int 배열로 반환
		foods.sort(Comparator.naturalOrder());
		return foods.stream().mapToInt(Integer::intValue).toArray();
	}
	
	// DFS로 무인도 하나의 식량량 합산
	private int dfs(int x, int y) {
		visited[x][y] = true;
		int food = map[x].charAt(y) - '0';
		
		for (int[] dir : DIRS) {
			int nx = x + dir[0];
			int ny = y + dir[1];
			
			if (nx < 0 || nx >= xLen || ny < 0 || ny >= yLen
					|| visited[nx][ny] || map[nx].charAt(ny) == 'X') continue;
			
			food += dfs(nx, ny);
		}
		
		return food;
	}
}
