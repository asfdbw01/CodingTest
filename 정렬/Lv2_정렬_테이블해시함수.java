/*
 * 문제 요약
 *     - 2차원 테이블 data가 주어질 때, col번째 컬럼을 기준으로 오름차순 정렬하되,
 *       값이 같으면 첫 번째 컬럼(기본키)을 기준으로 내림차순 정렬
 *     - 정렬된 테이블에서 row_begin번째 행부터 row_end번째 행까지
 *       각 행의 원소를 해당 행 번호로 나눈 나머지들의 합을 S_i라고 할 때
 *       모든 S_i를 bitwise XOR한 결과를 반환
 *
 * 입력
 *     - data: 정수로 구성된 2차원 배열 (1 ≤ data.length ≤ 2,500, 1 ≤ data[0].length ≤ 500)
 *     - col: 정렬 기준이 되는 컬럼 번호 (1-based)
 *     - row_begin, row_end: S_i를 계산할 시작/끝 행 번호 (1-based)
 *
 * 출력
 *     - 정렬된 테이블에서 row_begin ~ row_end까지 S_i의 XOR 결과값
 *
 * 핵심 포인트
 *     - 복합 정렬: col 기준 오름차순 + 기본키 기준 내림차순
 *     - S_i 계산에서 i는 1-based로 인덱스 처리
 *     - 누적 XOR 연산을 통해 최종 결과 도출
 */

class Solution {
	
	public int solution(int[][] data, int col, int row_begin, int row_end) {
		int answer = 0;
		int c = col - 1; // 0-based 인덱스 보정

		// 정렬: col 기준 오름차순, 같으면 기본키(0번) 기준 내림차순
		Arrays.sort(data, (o1, o2) -> (o1[c] != o2[c]) ? o1[c] - o2[c] : o2[0] - o1[0]);

		// row_begin ~ row_end 구간에 대해 S_i 계산 후 XOR 누적
		for (int i = row_begin - 1; i <= row_end - 1; i++) {
			int si = 0;
			for (int val : data[i]) si += val % (i + 1);
			answer ^= si;
		}
		
		return answer;
	}
}
