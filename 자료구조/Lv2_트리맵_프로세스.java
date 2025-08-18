/*  
 * 문제 요약
 *     - 프로세스가 우선순위에 따라 실행되며, 특정 위치의 프로세스가 몇 번째로 실행되는지 구하는 문제.
 *     - 실행 대기 큐에서 가장 앞의 프로세스를 꺼내고, 큐 내에 더 높은 우선순위가 있다면 다시 뒤에 넣는다.
 *     - 그렇지 않으면 실행하며, 실행된 순서를 카운팅한다.
 *
 * 입력  
 *     - int[] priorities : 프로세스들의 우선순위 (1~9)
 *     - int location : 실행 순서를 알고 싶은 프로세스의 인덱스
 *
 * 출력  
 *     - int : 해당 프로세스가 몇 번째로 실행되는지
 *
 * 핵심 포인트  
 *     - 현재 큐 내 최댓값을 빠르게 조회하기 위해 TreeMap을 사용한다.
 *     - 프로세스의 우선순위와 인덱스를 함께 큐에 저장하여 특정 프로세스 추적이 가능하다.
 *     - 실행된 프로세스는 카운팅하고, 목표 인덱스를 만나면 종료한다.
 */

class Solution {
	
	public int solution(int[] priorities, int location) {
		TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
		Queue<int[]> que = new LinkedList<>();
		
		// 큐에 우선순위와 인덱스를 함께 저장하고, 각 우선순위별 등장 횟수 기록
		for (int i = 0; i < priorities.length; i++) {
			map.merge(priorities[i], 1, Integer::sum);
			que.add(new int[] {priorities[i], i});
		}
		
		// 큐가 비지 않는 동안 반복
		while (!que.isEmpty()) {
			// 현재 큐의 맨 앞 요소가 최고 우선순위가 아닐 경우 뒤로 보냄
			while (que.peek()[0] != map.firstKey()) que.add(que.poll());

			// 현재 프로세스가 목표 인덱스면 종료
			if (que.peek()[1] == location) break;
			
			// 그렇지 않으면 프로세스 실행 처리 및 우선순위 개수 감소
			que.poll();
			map.merge(map.firstKey(), -1, Integer::sum);
			if (map.firstEntry().getValue() == 0) map.remove(map.firstKey());
		}
		
		// 실행 순서 = 전체 프로세스 수 - 큐에 남은 수 + 1
		return priorities.length - que.size() + 1;
	}
}
