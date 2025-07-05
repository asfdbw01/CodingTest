/*  
 * 문제 요약
 *     - 좌표평면 위에서 캐릭터를 주어진 U, D, R, L 명령어로 이동시키며
 *       처음 방문한 경로(간선)의 개수를 구하는 문제.
 *
 * 입력
 *     - dirs: 최대 길이 500의 문자열, U/D/R/L만 포함.
 *
 * 출력
 *     - 처음 걸어본 길의 총 개수(int).
 *
 * 핵심 포인트
 *     - 같은 두 점 사이의 경로를 역방향으로 다시 이동해도 이미 방문한 것으로 처리.
 *     - 경계 밖으로 이동하는 명령은 무시.
 *     - 경로를 (작은 좌표, 큰 좌표)로 표현해 방향성을 제거해야 함.
 */

class Solution {
	
	public int solution(String dirs) {
		int count = 0;
		Set<String> visited = new HashSet<>();
		
		int x = 0, y = 0;
		for (char dir : dirs.toCharArray()) {
			int nx = x, ny = y;
			switch (dir) {
			case 'U': ny++; break;
			case 'D': ny--; break;
			case 'R': nx++; break;
			case 'L': nx--; break;
			}
			
			// 경계 밖이면 이동 무시
			if (nx < -5 || nx > 5 || ny < -5 || ny > 5) continue;
			
			// 현재 위치와 다음 위치를 작은 순서로 정렬해 경로 방향성을 제거
			String path = String.format("%d,%d-%d,%d",
				Math.min(x, nx), Math.min(y, ny), Math.max(x, nx), Math.max(y, ny));
			
			// 처음 지나가는 경로라면 카운트 증가
			if (visited.add(path)) count++;
			
			// 좌표 갱신
			x = nx;
			y = ny;
		}
		
		return count;
	}
	
}
