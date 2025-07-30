/*  
 * 문제 요약
 *     - 길이 10억의 도로에 1부터 1천만까지의 블록 번호를 특정 규칙에 따라 설치했을 때,
 *       주어진 구간 [begin, end]에서 각 위치에 설치된 블록 번호를 구함
 *
 * 입력  
 *     - long begin: 구간 시작 위치 (1 이상 1,000,000,000 이하)
 *     - long end: 구간 끝 위치 (begin ≤ end ≤ 1,000,000,000)
 *
 * 출력  
 *     - int[]: begin부터 end까지 각 위치에 설치된 블록 번호
 *
 * 핵심 포인트  
 *     - 위치 num에 설치되는 최종 블록 번호는 num의 약수 중 가장 큰 값 (자기 자신 제외, 단 1~10,000,000 사이)
 *     - 주어진 범위가 작기 때문에 각 위치마다 √num 까지 약수 탐색 가능
 *     - 블록 번호가 1인 경우는 2부터 모든 위치에 깔리기 때문에 num=1인 경우 예외 처리
 */

class Solution {

	static final int MAX = 10_000_000;

	public int[] solution(long begin, long end) {
		int[] answer = new int[(int) (end - begin) + 1];

		// 각 위치에 대해 블록 번호 계산
		for (int i = 0; i < answer.length; i++) {
			answer[i] = getNum(begin + i);
		}

		return answer;
	}

	// 해당 위치에 가장 마지막으로 설치된 블록 번호 반환
	public int getNum(long num) {
		if (num == 1) return 0;

		int result = 1;

		// 2부터 √num까지의 약수만 탐색
		for (long i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				long div = num / i;

				// 더 큰 약수부터 먼저 반환
				if (div <= MAX) return (int) div;
				if (i <= MAX) result = (int) i;
			}
		}

		return result;
	}
}
