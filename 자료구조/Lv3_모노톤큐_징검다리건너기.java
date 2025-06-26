/*  
 * 문제 요약  
 *     - 디딤돌마다 숫자가 적혀 있고, 한 명이 건널 때마다 해당 디딤돌의 숫자가 1씩 줄어든다.  
 *     - 연속된 k개의 디딤돌이 0 이하가 되는 순간, 이후 사람은 건널 수 없다.  
 *     - 최대 몇 명까지 디딤돌을 무사히 건널 수 있는지를 구해야 한다.  
 *
 * 입력  
 *     - int[] stones: 각 디딤돌의 초기 내구도 (1 이상 200,000 이하)  
 *     - int k: 한 번에 건너뛸 수 있는 최대 디딤돌 수 (1 이상 stones.length 이하)  
 *
 * 출력  
 *     - int: 디딤돌을 무사히 건널 수 있는 최대 인원 수  
 *
 * 핵심 포인트  
 *     - 연속된 k개의 디딤돌 중에서 모두 0 이하가 되는 구간이 처음 등장하는 시점이 인원수 제한 조건이다.  
 *     - 이를 만족하는 최대 인원을 구하는 대신, k 길이 윈도우의 최대값들 중 최소값을 구하면 정답이다.  
 *     - 슬라이딩 윈도우의 최대값을 효율적으로 구하기 위해 모노톤 큐(Deque)를 사용하여 O(n)에 해결한다.
 */

class Solution {
	
	public int solution(int[] stones, int k) {
		int min = 200_000_001;
		Deque<Integer> dq = new ArrayDeque<>();

		for (int i = 0; i < stones.length; i++) {
			// 현재 돌보다 값이 작거나 같은 인덱스는 덱 뒤에서 제거
			while (!dq.isEmpty() && stones[dq.peekLast()] <= stones[i]) dq.pollLast();
			dq.addLast(i);

			// 윈도우를 벗어난 인덱스는 앞에서 제거
			if (dq.peekFirst() <= i - k) dq.pollFirst();

			// 윈도우가 완성되었을 때 최소값 후보로 갱신
			if (i >= k - 1) min = Math.min(min, stones[dq.peekFirst()]);
		}

		return min;
	}
}
