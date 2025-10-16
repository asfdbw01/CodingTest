/*
 * 문제 요약
 *     - 원점으로부터 거리 d 이하의 위치에, x, y가 모두 k의 배수인 격자점만 찍는다
 *     - 즉, (x, y) = (a*k, b*k) 형태의 점 중 x² + y² ≤ d²를 만족하는 점의 개수 구하기
 *
 * 입력
 *     - k: 격자 단위 간격 (1 ≤ k ≤ 1,000,000)
 *     - d: 최대 거리 (1 ≤ d ≤ 1,000,000)
 *
 * 출력
 *     - 조건을 만족하는 점의 개수 (long)
 *
 * 핵심 포인트
 *     - y를 0부터 d까지 k 간격으로 반복하면서, 가능한 x의 최대값 계산
 *     - 조건: x² + y² ≤ d² → x² ≤ d² - y² → x ≤ sqrt(d² - y²)
 *     - 가능한 x는 0부터 maxX까지 k 간격으로 존재하므로 (maxX / k + 1)개
 *     - 모든 y에 대해 누적 합산하여 결과 반환
 */

class Solution {
	
	public long solution(int k, int d) {
		long cnt = 0L;
		long dd = (long) d * d;

		// y축을 0부터 d까지 k 간격으로 이동
		for (long y = 0; y <= d; y += k) {
			// 해당 y에 대해 가능한 최대 x값 계산 (x² ≤ d² - y²)
			long maxX = (long) Math.sqrt(dd - y * y);

			// x는 0부터 maxX까지 k 간격으로 존재하므로 (maxX / k + 1)개
			cnt += maxX / k + 1;
		}
		
		// 누적된 점의 개수 반환
		return cnt;
	}
}
