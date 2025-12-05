/*  
 * 문제 요약
 *     - 각 코어는 고유의 처리시간을 갖고, 선입선출로 작업을 처리함  
 *     - 여러 코어가 동시에 비면 앞 번호 코어가 우선 처리  
 *     - 총 n개의 작업 중 마지막 작업을 처리하는 코어 번호를 반환  
 *
 * 입력  
 *     - int n: 처리해야 할 작업 개수 (1 이상 50,000 이하)  
 *     - int[] cores: 각 코어의 작업 처리 시간 (길이 2 이상 10,000 이하, 값은 10,000 이하)  
 *
 * 출력  
 *     - 마지막 작업을 처리한 코어의 번호 (1-based index)  
 *
 * 핵심 포인트  
 *     - 이분 탐색으로 작업 처리 시간 중 가능한 최소 시점을 찾음  
 *     - 해당 시점까지 처리된 작업 수를 기반으로 마지막 작업 시뮬레이션  
 *     - 여러 코어가 동시에 작업 가능할 경우 앞의 코어가 우선 처리됨  
 */

class Solution {

	public int solution(int n, int[] cores) {
		int left = 0, right = 250_000_000; // 가능한 시간 범위
		int time = 0, worked = 0;    // 확정된 시간과 작업 수

		// 이분 탐색으로 마지막 작업이 시작되기 전의 최대 시각 탐색
		while (left <= right) {
			int mid = (left + right) / 2;

			int cur = 0;
			for (int core : cores) {
				cur += Math.ceil((double) mid / core); // mid 시간까지 처리 가능한 총 작업 수
			}

			if (cur >= n) {
				right = mid - 1;
			} else {
				left = mid + 1;
				time = mid;
				worked = cur; // 마지막 작업 이전까지 완료된 작업 수
			}
		}

		int index = 0;

		// time 시점부터 작업 처리 가능한 코어를 순회하며 마지막 작업 찾기
		Outter: while (worked < n) {
			for (int i = 0; i < cores.length; i++) {
				if (time % cores[i] == 0) { // 해당 시점에 작업 가능한 코어
					if (++worked == n) {   // 마지막 작업 도달
						index = i;
						break Outter;
					}
				}
			}
			time++;
		}

		return index + 1; // 코어 번호는 1부터 시작
	}
	
}
