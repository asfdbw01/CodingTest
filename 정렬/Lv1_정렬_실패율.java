/*  
 * 문제 요약
 *     - 유저들이 멈춰있는 스테이지 정보를 통해 각 스테이지의 실패율을 구하고,
 *       실패율이 높은 순으로 정렬된 스테이지 번호를 반환
 *
 * 입력  
 *     - int N: 전체 스테이지 수 (1 ≤ N ≤ 500)
 *     - int[] stages: 유저가 도전 중인 스테이지 번호 배열 (길이 ≤ 200,000, 값은 1 ~ N+1)
 *
 * 출력  
 *     - int[]: 실패율이 높은 순으로 정렬된 스테이지 번호 배열
 *
 * 핵심 포인트  
 *     - 실패율 = (해당 스테이지에 도달했으나 클리어하지 못한 사람 수) / (해당 스테이지에 도달한 전체 인원)
 *     - 누적합을 통해 시도 인원(tried)을 뒤에서부터 구함
 *     - 실패율이 같으면 스테이지 번호가 작은 것이 우선
 *     - 정렬을 위해 List<double[]> 사용하고, 실패율 기준 내림차순 + 스테이지 번호 오름차순 정렬
 */

class Solution {

	public int[] solution(int N, int[] stages) {
		int max = Math.max(N, Arrays.stream(stages).max().getAsInt());
		int[] tried = new int[max + 1];  // 스테이지에 도달한 인원
		int[] failed = new int[max + 1]; // 스테이지에 실패한 인원

		// 각 스테이지별 도달 인원 및 실패 인원 기록
		for (int stage : stages) {
			tried[stage]++;
			failed[stage]++;
		}

		// 뒤에서부터 누적합으로 도달 인원 계산
		for (int i = max - 1; i >= 1; i--) {
			tried[i] += tried[i + 1];
		}

		// 실패율 리스트 구성: [스테이지 번호, 실패율]
		List<double[]> list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			double failureRate = (tried[i] == 0) ? 0 : (double) failed[i] / tried[i];
			list.add(new double[] {i, failureRate});
		}

		// 실패율 내림차순, 같으면 스테이지 번호 오름차순
		list.sort(Comparator.comparing((double[] i) -> i[1])
							.reversed()
							.thenComparing(i -> i[0]));

		// 정답 배열 구성
		int[] answer = new int[N];
		for (int i = 0; i < N; i++) {
			answer[i] = (int) list.get(i)[0];
		}

		return answer;
	}
}
