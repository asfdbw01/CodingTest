/*
 * 문제 요약
 *     - 1억 x 1억의 곱셈 표에서, 수 s 이상 e 이하 중 가장 많이 등장하는 수를 구해야 한다.
 *     - 어떤 수 n은 억억단에서 약수의 개수만큼 등장한다 (n = i * j일 때 i, j ∈ [1, 10^8])
 *     - 따라서 각 수의 약수 개수를 구해두고, 구간 [s, e] 중 약수 개수가 가장 많은 수를 찾는다.
 *     - 가장 많이 등장하는 수가 여러 개일 경우 가장 작은 수를 정답으로 한다.
 *
 * 입력
 *     - int e: 최대 범위 (1 ≤ e ≤ 5,000,000)
 *     - int[] starts: 질의 시작점 배열 (1 ≤ starts.length ≤ min{e, 100,000}, 1 ≤ starts[i] ≤ e)
 *
 * 출력
 *     - int[]: 각 starts[i]에 대해 s 이상 e 이하에서 가장 많이 등장한 수
 *
 * 핵심 포인트
 *     - 약수 개수를 O(e log e)로 미리 전처리
 *     - e부터 1까지 역순으로 내려가며 각 위치에서의 최대 등장 수를 기록
 *     - starts 배열을 기반으로 미리 준비된 maxNum에서 O(1)로 조회
 */

class Solution {
	
	public int[] solution(int e, int[] starts) {
		int[] answer = new int[starts.length];
		int[] divisorCounts = new int[e + 1];
		
		// 약수 개수 전처리
		for (int i = 1; i <= e; i++) {
			for (int j = 1; j <= e / i; j++) {
				divisorCounts[i * j]++;
			}
		}
		
		// 각 구간에서 약수 개수가 가장 많은 수를 기록
		int[] maxNum = new int[e + 1];
		maxNum[e] = e;
		for (int i = e - 1; i >= 1; i--) {
			maxNum[i] = (divisorCounts[i] >= divisorCounts[maxNum[i + 1]]) ? i : maxNum[i + 1];
		}
		
		// 각 질의에 대해 빠르게 정답 조회
		for (int i = 0; i < answer.length; i++) {
			answer[i] = maxNum[starts[i]];
		}
		
		return answer;
	}
}
