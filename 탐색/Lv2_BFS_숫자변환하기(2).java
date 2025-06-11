/*
 * 문제 요약
 *     - 자연수 x를 y로 변환하기 위해 최소 연산 횟수를 구함
 *     - 가능한 연산: x에 n을 더하기, x에 2 곱하기, x에 3 곱하기
 * 
 * 입력
 *     - x: 시작 자연수 (1 ≤ x ≤ y ≤ 1,000,000)
 *     - y: 목표 자연수
 *     - n: 더할 수 있는 자연수 (1 ≤ n < y)
 * 
 * 출력
 *     - x를 y로 만들기 위한 최소 연산 횟수
 *     - 만들 수 없다면 -1 반환
 * 
 * 핵심 포인트
 *     - 최소 연산 횟수이므로 BFS 탐색 사용
 *     - 방문 체크 배열을 활용해 중복 탐색 방지
 *     - 연산 결과가 y를 넘지 않도록 조건 처리
 */

class Solution {

	public int solution(int x, int y, int n) {
		// 시작과 목표가 같다면 연산 필요 없음
		if (x == y) return 0;

		// 방문 체크 배열 초기화
		boolean[] visited = new boolean[y + 1];

		// BFS를 위한 큐: [현재 숫자, 연산 횟수]
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {x, 0});
		visited[x] = true;

		while (!que.isEmpty()) {
			int[] cur = que.poll();
			int num = cur[0];
			int cnt = cur[1];

			// 가능한 연산 3가지 시도
			int[] nexts = {num + n, num * 2, num * 3};

			for (int next : nexts) {
				// 목표값 도달 시 연산 횟수 반환
				if (next == y) return cnt + 1;

				// 범위 초과 또는 중복 방문은 제외
				if (next > y || visited[next]) continue;

				// 방문 처리 후 큐에 삽입
				visited[next] = true;
				que.offer(new int[] {next, cnt + 1});
			}
		}

		// 모든 경우를 탐색했으나 도달 불가 시 -1 반환
		return -1;
	}
}
