/*  
 * 문제 요약
 *     - 명함을 모두 수납할 수 있도록 가로와 세로 방향 중 더 긴 쪽을 가로로, 짧은 쪽을 세로로 정렬해
 *       모든 명함이 들어가는 최소 크기의 지갑 면적을 계산하는 문제
 *
 * 입력  
 *     - sizes: 명함 크기 배열 (각 원소는 [가로, 세로] 쌍), 길이 1 이상 10,000 이하
 *
 * 출력  
 *     - 모든 명함을 수납할 수 있는 가장 작은 지갑의 면적 (int)
 *
 * 핵심 포인트  
 *     - 각 명함을 회전할 수 있으므로, 가로/세로 중 큰 쪽을 가로로 통일하는 것이 유리
 *     - 모든 명함 중 가장 큰 가로와 세로를 찾아 곱한 면적이 최소 크기
 */

class Solution {
	
	public int solution(int[][] sizes) {
		int wMax = 0, hMax = 0;
		
		// 각 명함을 가로/세로 중 큰 값이 앞으로 오게 정렬
		for (int[] size : sizes) {
			int w = Math.max(size[0], size[1]);
			int h = Math.min(size[0], size[1]);
			
			// 가로, 세로 각각의 최대값을 갱신
			wMax = Math.max(wMax, w);
			hMax = Math.max(hMax, h);
		}
		
		// 가장 큰 가로 * 가장 큰 세로가 최소 지갑 크기
		return wMax * hMax;
	}
	
}
