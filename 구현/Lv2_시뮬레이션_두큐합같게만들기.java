/*
 * 문제 요약
 *     - 두 큐(queue1, queue2)의 원소 합을 같게 만들기 위해 pop과 insert 작업을 수행함
 *     - 한 번의 pop + insert = 작업 1회로 간주되며, 최소 작업 횟수를 구함
 *     - 어떤 방법으로도 합을 같게 만들 수 없으면 -1을 반환
 * 
 * 입력
 *     - queue1, queue2: 길이가 같은 두 개의 정수 배열 (1 ≤ 길이 ≤ 300,000, 각 원소 ≤ 10^9)
 * 
 * 출력
 *     - 최소 작업 횟수 또는 불가능할 경우 -1
 * 
 * 핵심 포인트
 *     - 총합이 홀수면 절대 두 큐의 합을 같게 만들 수 없음 → -1
 *     - 값을 실제로 옮기지 않고, 투포인터로 범위만 조절 → O(N) 시간 복잡도
 *     - 합을 같게 만들 수 있는 최대 시도 횟수는 2 * N (최대 600,000번)
 *     - 큐를 별도로 생성하지 않고 인덱스로 시뮬레이션 처리
 */

class Solution {
	
	private final int INF = 600_001;  // 최대 연산 횟수 + 1 (실패 판단 기준)

	public int solution(int[] queue1, int[] queue2) {
		// 각 큐의 총합을 long으로 계산하여 오버플로 방지
		long q1Sum = Arrays.stream(queue1).asLongStream().sum();
		long q2Sum = Arrays.stream(queue2).asLongStream().sum();
		
		// 총합이 홀수이면 두 큐의 합을 같게 만드는 것이 불가능
		if ((q1Sum + q2Sum) % 2 != 0) return -1;
		
		// 목표 합은 두 큐의 총합의 절반
		long half = (q1Sum + q2Sum) / 2;
		
		// 큐 길이 및 전체 범위 설정
		int q1Len = queue1.length;
		int end = q1Len * 2;
		
		// 투포인터 초기화
		int left = 0, right = q1Len - 1;
		long sum = q1Sum;
		int answer = INF;
		
		// 투포인터 탐색
		while (left <= right) {
			if (sum > half) {
				// 합이 너무 크면 왼쪽 원소를 제거
				if (left < q1Len) sum -= queue1[left++];
				else sum -= queue2[left++ - q1Len];
			} else {
				// 합이 목표와 같으면 정답 후보 갱신
				if (sum == half) {
					answer = Math.min(answer, left + (right - (q1Len - 1)));
				}
				// 합이 부족하면 오른쪽에서 원소를 추가
				if (right + 1 < end) sum += queue2[++right - q1Len];
				else break;  // 더 이상 추가 불가 시 종료
			}
		}
		
		// 정답 반환 (최소 연산 횟수 or 불가능 시 -1)
		return answer == INF ? -1 : answer;
	}
}
