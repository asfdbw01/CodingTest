/*
 * 문제 요약
 *     - 각 건물의 내구도가 주어진 게임 맵에서, 공격 및 회복 스킬들을 순서대로 적용한 뒤,
 *       내구도가 1 이상인 '파괴되지 않은 건물'의 개수를 구하는 문제
 * 
 * 입력
 *     - int[][] board : N x M 크기의 내구도 배열 (1 ≤ N, M ≤ 1000)
 *     - int[][] skills : 각 스킬은 [type, r1, c1, r2, c2, degree] 형식
 *         · type = 1이면 공격 (내구도 감소), type = 2이면 회복 (내구도 증가)
 *         · r1~r2, c1~c2는 스킬 범위, degree는 증감량
 * 
 * 출력
 *     - 스킬 적용 이후 내구도가 1 이상인 칸의 개수
 * 
 * 핵심 포인트
 *     - 최대 25만 개 스킬이 전체 보드에 영향을 줄 수 있어, 단순 적용 방식은 시간 초과 발생
 *     - 2D 누적합(Imos 기법)을 사용해 스킬을 O(1) 시간에 누적하고, 한 번의 누적합 계산으로 반영
 *     - O(K + NM) 시간에 모든 스킬 처리 및 결과 계산 가능
 */

class Solution {
	
	public int solution(int[][] board, int[][] skills) {
		int n = board.length;
		int m = board[0].length;
		int[][] imos2d = new int[n + 1][m + 1];
		
		// 스킬을 2D 누적합 배열에 표시 (O(1) 방식)
		for (int[] skill : skills) {
			int type = skill[0], r1 = skill[1], c1 = skill[2], r2 = skill[3], c2 = skill[4];
			int degree = (type == 1) ? -skill[5] : skill[5];
			
			imos2d[r1][c1] += degree;
			imos2d[r1][c2 + 1] -= degree;
			imos2d[r2 + 1][c1] -= degree;
			imos2d[r2 + 1][c2 + 1] += degree;
		}
		
		// 행 방향 누적합
		for (int r = 0; r < n + 1; r++) {
			for (int c = 1; c < m + 1; c++) {
				imos2d[r][c] += imos2d[r][c - 1];
			}
		}
		
		// 열 방향 누적합
		for (int c = 0; c < m + 1; c++) {
			for (int r = 1; r < n + 1; r++) {
				imos2d[r][c] += imos2d[r - 1][c];
			}
		}
		
		// 누적된 값을 board에 반영하면서 살아남은 건물 개수 세기
		int count = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (board[r][c] + imos2d[r][c] > 0) count++;
			}
		}
		
		return count;
	}
}
