/*
 * 문제 요약
 *     - 정수 n을 k진수로 변환한 후, 0을 기준으로 나눈 각 부분 문자열이 소수(P)인지 확인
 *     - 단, 다음 조건을 만족하는 P만 유효:
 *         1. P의 양쪽에 0이 있는 경우 (0P0)
 *         2. P의 왼쪽이 비어 있고 오른쪽에 0이 있는 경우 (P0)
 *         3. P의 왼쪽에 0이 있고 오른쪽이 비어 있는 경우 (0P)
 *         4. P의 양쪽이 모두 비어 있는 경우 (P)
 * 입력
 *     - n: 변환할 10진수 정수 (1 ≤ n ≤ 1,000,000)
 *     - k: 변환할 진법 (3 ≤ k ≤ 10)
 * 출력
 *     - 변환된 수 안에서 위 조건을 만족하는 소수의 개수
 * 핵심 포인트
 *     - Integer.toString(n, k)로 k진수 변환
 *     - split("0")으로 0 기준 분할 후, 빈 문자열과 "1" 제거
 *     - Long.parseLong으로 숫자 변환 (큰 소수 대응)
 *     - 소수 판별을 스트림 내에서 직접 수행
 * 스스로에게 거는 제약 사항
 *     - Stream 활용하기
 */

class Solution {
	
	public int solution(int n, int k) {
		// 1. n을 k진수로 변환 후, 0을 기준으로 문자열 분할
		// 2. 빈 문자열 또는 "1"은 소수가 아니므로 필터링
		// 3. Long으로 변환하여 큰 숫자 대응
		// 4. 각 수에 대해 소수 판별 로직을 스트림 내에서 실행
		// 5. 최종적으로 소수인 개수 count 후 반환
		return (int) Arrays.stream(Integer.toString(n, k).split("0"))
				.filter(s -> !s.isEmpty() && !s.equals("1"))
				.mapToLong(Long::parseLong)
				.filter(num -> {
					for (long i = 2; i * i <= num; i++)
						if (num % i == 0) return false;
					return true;
				})
				.count();
	}
	
}
