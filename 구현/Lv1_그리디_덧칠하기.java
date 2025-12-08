/*
 * 문제 요약
 *     - 다시 칠해야 하는 구역들이 주어질 때, 롤러를 이용해 최소 횟수로 모든 구역을 칠해야 한다.
 *     - 롤러 길이는 m이며, 칠할 때 구역을 부분적으로 포함하면 안 된다.
 *
 * 입력
 *     - int n: 벽의 총 길이(구역 수)
 *     - int m: 롤러의 길이
 *     - int[] section: 다시 칠해야 할 구역 번호 목록(오름차순)
 *
 * 출력
 *     - int: 롤러로 칠해야 하는 최소 횟수
 *
 * 핵심 포인트
 *     - section이 오름차순이므로 앞에서부터 순차적으로 칠하면 된다.
 *     - 현재 롤러로 칠해진 마지막 구역을 저장하며, 다음 미도색 구역을 만나면 새로 칠한다.
 */

class Solution {

	public int solution(int n, int m, int[] section) {
		int painted = 0; // 현재 롤러로 칠해진 마지막 구역 번호
		int cnt = 0;     // 칠한 횟수 누적
		
		for (int sec : section) {
			// 이미 칠해진 범위 내라면 패스
			if (sec <= painted) continue;
			
			// 아직 칠해지지 않은 구역을 만나면 해당 구역부터 롤러 길이만큼 칠함
			painted = sec + m - 1; 
			cnt++; // 롤러 사용 횟수 증가
		}
		
		return cnt;
	}

}
