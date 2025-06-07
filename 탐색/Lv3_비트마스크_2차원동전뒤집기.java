/*
 * 문제 요약
 *     - 행 또는 열 단위로 한 번에 동전들을 뒤집을 수 있음
 *     - 초기 상태에서 목표 상태로 만들기 위해 필요한 최소 동전 뒤집기 횟수를 구함
 *     - 동전은 0(앞면), 1(뒷면)로 표현되며, 각 행/열은 최대 1번만 뒤집을 수 있음
 *
 * 입력
 *     - beginning: int[][], 초기 상태 (1 ≤ n, m ≤ 10)
 *     - target: int[][], 목표 상태 (same shape)
 *
 * 출력
 *     - 최소 뒤집기 횟수 (행 + 열), 만들 수 없다면 -1 반환
 *
 * 핵심 포인트
 *     - 행/열을 뒤집는 조합은 2^n × 2^m → 완전탐색 가능
 *     - 행과 열 각각을 비트마스크로 표현하여 탐색
 *     - beginning[row][col] ^ rowMask ^ colMask == target[row][col] 여부 확인
 *     - 조기 탈출을 통해 성능 최적화
 */

class Solution {
	
	public int solution(int[][] beginning, int[][] target) {
		int n = target.length;
		int m = target[0].length;
		
		int min = 21;

		// 모든 행 뒤집기 조합
		for (int rows = 0; rows < (1 << n); rows++) {

			// 각 행 조합에 대해 열 뒤집기 조합도 탐색
			Outter : for (int cols = 0; cols < (1 << m); cols++) {

				// 각 위치의 최종 상태 계산 및 target과 비교
				for (int col = 0; col < m; col++) {
					for (int row = 0; row < n; row++) {
						int face = beginning[row][col] ^ ((rows >> row) & 1) ^ ((cols >> col) & 1);
						if (target[row][col] != face) continue Outter;
					}
				}

				// 일치하면 최소 뒤집기 횟수 갱신
				min = Math.min(min, Integer.bitCount(rows) + Integer.bitCount(cols));
			}
		}

		return (min == 21) ? -1 : min;
	}
}
