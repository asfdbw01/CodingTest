/*  
 * 문제 요약  
 *     - 주어진 rows x columns 크기의 행렬에서, 쿼리마다 직사각형 테두리 숫자를 시계 방향으로 회전함  
 *     - 각 회전에 대해, 이동한 숫자들 중 가장 작은 값을 구해서 순서대로 반환함  
 *
 * 입력  
 *     - rows: 행의 개수 (2 이상 100 이하)  
 *     - columns: 열의 개수 (2 이상 100 이하)  
 *     - queries: 회전 쿼리 목록 (각 쿼리는 [x1, y1, x2, y2] 형식, 최대 10,000개)  
 *
 * 출력  
 *     - 각 회전에서 위치가 바뀐 숫자 중 최솟값들을 순서대로 담은 배열  
 *
 * 핵심 포인트  
 *     - 회전 범위는 테두리에 한정되므로 시계방향으로 순차 이동하며 회전 구현  
 *     - 값 이동 시 이전 값(prev)을 기억하여 덮어쓰기 방식으로 처리  
 *     - 동시에 회전에 포함된 숫자 중 최솟값을 추적  
 */

class Solution {
	
	private final int INF = 100 * 100 + 1;
	
	public int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];
		int[][] table = new int[rows][columns];
		
		// 초기 행렬 값 채우기: 1부터 시작해 가로 방향으로 채움
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				table[i][j] = i * columns + j + 1;
			}
		}
		
		// 각 회전에 대해 테두리 회전 실행 및 최솟값 기록
		for (int i = 0; i < queries.length; i++) {
			answer[i] = getRotatedMin(queries[i], table);
		}
		
		return answer;
	}
	
	public int getRotatedMin(int[] query, int[][] table) {
		int x1 = query[0] - 1, y1 = query[1] - 1, x2 = query[2] - 1, y2 = query[3] - 1;
		int min = INF;

		// 회전 전 테두리 값을 순서대로 이동하며 prev-cur 방식으로 회전 처리
		int prev = table[x1][y1], cur;

		// 상단 테두리 오른쪽으로 이동
		for (int y = y1 + 1; y <= y2; y++) {
			min = Math.min(min, (cur = table[x1][y]));
			table[x1][y] = prev;
			prev = cur;
		}

		// 우측 테두리 아래로 이동
		for (int x = x1 + 1; x <= x2; x++) {
			min = Math.min(min, (cur = table[x][y2]));
			table[x][y2] = prev;
			prev = cur;
		}

		// 하단 테두리 왼쪽으로 이동
		for (int y = y2 - 1; y >= y1; y--) {
			min = Math.min(min, (cur = table[x2][y]));
			table[x2][y] = prev;
			prev = cur;
		}

		// 좌측 테두리 위로 이동
		for (int x = x2 - 1; x >= x1; x--) {
			min = Math.min(min, (cur = table[x][y1]));
			table[x][y1] = prev;
			prev = cur;
		}
		
		return min;
	}
}
