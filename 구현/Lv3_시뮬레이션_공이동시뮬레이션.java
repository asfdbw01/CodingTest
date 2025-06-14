/* 
 * 문제 요약
 *     - n x m 격자에서 공이 주어지는 쿼리 순서대로 이동할 때, x행 y열에 도착할 수 있는 모든 시작점의 개수를 구하는 문제
 *     - 공은 격자 바깥으로는 나갈 수 없으며, 쿼리 방향대로 최대 dx칸까지 이동한다
 *
 * 입력
 *     - int n, m: 격자의 크기 (1 ≤ n, m ≤ 10^9)
 *     - int x, y: 최종 목적지 좌표
 *     - int[][] queries: [이동 방향, 이동 거리] 쿼리 목록 (최대 200,000개)
 *
 * 출력
 *     - x, y에 도착할 수 있는 가능한 시작점의 개수 (long)
 *
 * 핵심 포인트  
 *     - 정방향 시뮬레이션은 불가능하므로, (x, y)에서 가능한 시작점을 역추적해야 함
 *     - 행/열 이동은 독립적이므로 x축과 y축을 별도로 역방향으로 추적
 *     - 모든 쿼리를 역순으로 순회하면서 도달 가능한 min~max 범위를 확장
 *     - 최소 범위 > 최대 범위이면 더 이상 유효한 시작점 없음
 */

class Solution {
	
	public long solution(int n, int m, int x, int y, int[][] queries) {
		// 도착지점 (x, y)에서부터 가능한 시작점 범위를 초기화
		long minX = x, maxX = x, minY = y, maxY = y;
		
		// 쿼리를 역순으로 순회하며 시작 가능한 범위 역추적
		for (int i = queries.length - 1; i >= 0; i--) {
			int mode = queries[i][0];
			int move = queries[i][1];
			
			switch (mode) {
			case 0:  // 좌로 dx칸 이동 → 역방향은 우측으로 확장
				minY = (minY == 0) ? 0 : minY + move;  // 범위가 0에 걸려 있으면 확장 X
				maxY = Math.min(maxY + move, m - 1);   // 우측 확장은 최대 m-1까지
				break;
				
			case 1:  // 우로 dx칸 이동 → 역방향은 좌측으로 확장
				minY = Math.max(minY - move, 0);       			// 좌측으로 확장 (0 이상)
				maxY = (maxY == m - 1) ? m - 1 : maxY - move;  	// 끝 벽에 붙어있으면 이동 X
				break;
				
			case 2:  // 상으로 dx칸 이동 → 역방향은 하단으로 확장
				minX = (minX == 0) ? 0 : minX + move;
				maxX = Math.min(maxX + move, n - 1);
				break;
				
			case 3:  // 하로 dx칸 이동 → 역방향은 상단으로 확장
				minX = Math.max(minX - move, 0);
				maxX = (maxX == n - 1) ? n - 1 : maxX - move;
				break;
			}
			
			// 유효 범위를 벗어나는 경우 도달 불가능
			if (minX > maxX || minY > maxY) return 0;
		}
		
		// 가능한 시작점 개수 = 범위의 넓이
		return (maxX - minX + 1) * (maxY - minY + 1);
	}
}
