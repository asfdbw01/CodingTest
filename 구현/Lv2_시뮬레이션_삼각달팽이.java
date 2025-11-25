/*  
 * 문제 요약
 *     - 밑변과 높이가 n인 삼각형을 반시계 방향으로 달팽이처럼 채운 후, 1차원 배열로 반환하는 문제
 *
 * 입력  
 *     - int n: 삼각형의 높이 및 밑변 길이 (1 이상 1,000 이하)
 *
 * 출력  
 *     - int[]: 삼각형을 반시계 방향으로 채운 후 0을 제외한 값들만 순서대로 나열한 배열
 *
 * 핵심 포인트  
 *     - 삼각형 달팽이 채우기에서는 방향이 3가지(↓ → ↖)로 고정됨
 *     - 방향 전환 조건: 배열의 범위를 벗어나거나 이미 채워진 칸일 때
 *     - 2차원 배열을 1차원으로 평탄화하며 0을 제거해야 최종 결과가 됨
 */

class Solution {
	
	int[] dx = {1, 0, -1};  // ↓ → ↖ 방향
	int[] dy = {0, 1, -1};
	
	public int[] solution(int n) {
		if (n == 1) return new int[] {1};  // n = 1인 경우 예외 처리
		
		int[][] snail = new int[n][n];
		
		int x = 0, y = 0, dir = 0, num = 1;
		
		// 빈 칸일 때만 채우기 진행
		while (snail[x][y] == 0) {
			snail[x][y] = num++;
			
			int nx = x + dx[dir], ny = y + dy[dir];
			
			// 경계 초과 또는 이미 방문한 칸이면 방향 전환
			if (nx >= n || ny >= n || snail[nx][ny] != 0) {
				dir = (dir + 1) % 3;
				x += dx[dir];
				y += dy[dir];
			} else {
				x = nx;
				y = ny;
			}
		}
		
		// 2차원 배열 평탄화 후 0 제거
		return Arrays.stream(snail)
		             .flatMapToInt(Arrays::stream)
		             .filter(i -> i != 0)
		             .toArray();
	}
	
}
