/*  
 * 문제 요약
 *     - 4열로 구성된 N행 땅에서, 각 행에서 한 칸씩 내려가며 점수를 누적
 *     - 같은 열은 연속으로 밟을 수 없다는 조건이 있음
 *     - 마지막 행까지 내려왔을 때 얻을 수 있는 최대 점수를 구함
 *
 * 입력  
 *     - int[][] land: N x 4 크기의 정수 배열 (N ≤ 100,000, 점수 ≤ 100)
 *
 * 출력  
 *     - int: 마지막 행까지 도달했을 때의 최대 점수
 *
 * 핵심 포인트  
 *     - 같은 열을 연속으로 밟을 수 없음 → 이전 행에서 다른 열의 최댓값 선택
 *     - land 배열 자체를 DP 테이블로 활용 (메모리 절약)
 *     - 최종 결과는 마지막 행에서의 최댓값
 */

class Solution {
	
	public int solution(int[][] land) {
		int n = land.length;

		// 각 행마다 4개의 열 중 선택 (단, 같은 열 연속 선택 불가)
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 4; j++) {
				int max = 0;
				// 이전 행의 다른 열 중 최대값 탐색
				for (int k = 0; k < 4; k++) {
					if (k == j) continue;
					max = Math.max(max, land[i - 1][k]);
				}
				// 현재 위치에 누적
				land[i][j] += max;
			}
		}
		
		// 마지막 행의 최대값이 정답
		return Arrays.stream(land[n - 1]).max().getAsInt();
	}
	
}
