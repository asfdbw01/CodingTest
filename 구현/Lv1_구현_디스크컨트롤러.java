/*
 * 문제 요약
 *   - 작업마다 [요청 시각, 소요 시간]이 주어지며, 디스크 컨트롤러는 
 *     "소요 시간 → 요청 시각 → 작업 번호" 우선순위로 작업을 수행한다.
 *   - 반환 시간(turnaround time)은 "종료 시각 - 요청 시각"
 *   - 모든 작업의 평균 반환 시간의 정수 부분을 구하라.

 * 입력
 *   - int[][] jobs: 작업 배열 (각 원소는 [요청 시각, 소요 시간])
 *     - 1 ≤ jobs.length ≤ 500
 *     - 0 ≤ 요청 시각 ≤ 1000, 1 ≤ 소요 시간 ≤ 1000

 * 출력
 *   - int: 평균 반환 시간의 정수 부분

 * 핵심 포인트
 *   - 작업은 요청 시각 순으로 대기 큐에 들어감
 *   - standby 큐는 소요 시간이 가장 짧은 작업을 우선시함 (SJF: Shortest Job First)
 *   - 현재 시간과 요청 시각을 비교하며 시뮬레이션 수행
 *   - 작업이 끝난 시점(endTime) 기준으로 다음 작업을 선택함
 */

class Job {
	private int requestTime;
	private int runTime;
	
	public Job(int[] job) {
		this.requestTime = job[0];
		this.runTime = job[1];
	}
	
	public int getRequestTime() {
		return this.requestTime;
	}
	
	public int getRunTime() {
		return this.runTime;
	}
}

class Solution {
	public int solution(int[][] jobs) {
		int answer = 0;

		// 실행 시간 기준 오름차순 정렬 (최소 실행 시간 우선)
		PriorityQueue<Job> standbyQue = new PriorityQueue<>(Comparator.comparingInt(Job::getRunTime));

		// 요청 시각 기준 오름차순 정렬 (입고 시점 순서)
		PriorityQueue<Job> jobQue = new PriorityQueue<>(Comparator.comparingInt(Job::getRequestTime).thenComparingInt(Job::getRunTime));
		Arrays.stream(jobs).forEach(job -> jobQue.offer(new Job(job)));

		// 첫 작업 초기화
		standbyQue.offer(jobQue.poll());
		int endTime = standbyQue.peek().getRequestTime(); // 현재 시간

		// 대기 큐에 작업이 있는 동안 반복
		while (!standbyQue.isEmpty()) {
			// 실행할 작업 꺼내기
			Job job = standbyQue.poll();
			endTime += job.getRunTime(); // 현재 시간 증가
			answer += endTime - job.getRequestTime(); // 반환 시간 누적

			// endTime까지 도착한 작업들을 대기 큐에 추가
			while (!jobQue.isEmpty() && jobQue.peek().getRequestTime() <= endTime) {
				standbyQue.offer(jobQue.poll());
			}

			// 대기 큐가 비었고, 아직 남은 작업이 있으면 다음 작업 넣기 (시간 점프)
			if (standbyQue.isEmpty() && !jobQue.isEmpty()) {
				standbyQue.offer(jobQue.poll());
				endTime = standbyQue.peek().getRequestTime(); // 시간을 다음 작업 요청 시점으로 이동
			}
		}

		return answer / jobs.length;
	}
}
