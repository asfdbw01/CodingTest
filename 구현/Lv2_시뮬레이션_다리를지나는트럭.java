/*  
 * 문제 요약  
 *     - 트럭들이 주어진 순서대로 일차선 다리를 지나야 하며,  
 *       다리는 최대 bridge_length대의 트럭이 동시에 올라갈 수 있고,  
 *       총 weight 이하의 무게만 버틸 수 있음  
 *     - 트럭이 다리를 모두 건너는 데 걸리는 최소 시간을 구하는 문제  
 *
 * 입력  
 *     - bridge_length: 다리 길이 (트럭이 다리를 지나가는 데 걸리는 시간)  
 *     - weight: 다리가 견딜 수 있는 최대 무게  
 *     - truck_weights: 트럭들의 무게 배열  
 *
 * 출력  
 *     - 모든 트럭이 다리를 건너는 데 걸리는 최소 시간 (int)  
 *
 * 핵심 포인트  
 *     - 다리 위에 올라간 트럭은 bridge_length초 후에 내려가므로  
 *       트럭마다 [무게, 내릴 시각] 형태로 큐에 저장  
 *     - 무게나 길이 조건을 만족하지 않으면, 먼저 올라간 트럭부터 내리고 시간 점프  
 *     - 트럭 올린 직후, 같은 시간에 내려야 할 트럭이 있는지 추가로 체크  
 */

class Solution {
	
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		Deque<int[]> bridge = new ArrayDeque<>();
		
		int time = 1;
		int totalWeight = 0;
		
		for (int truck : truck_weights) {
			// 다리에 올릴 수 없는 경우: 트럭을 내리며 시간 점프
			while (totalWeight + truck > weight || bridge.size() >= bridge_length) {
				int[] passed = bridge.poll();
				totalWeight -= passed[0];
				time = passed[1];
			}
			
			// 트럭을 다리에 올리고, 내릴 시각을 저장
			bridge.add(new int[] {truck, time++ + bridge_length});
			totalWeight += truck;
			
			// 트럭을 올린 후, 동시에 내려야 할 트럭이 있는지 확인
			if (time >= bridge.peek()[1]) {
				totalWeight -= bridge.poll()[0];
			}
		}
		
		// 마지막 트럭의 내릴 시각이 곧 전체 시간
		return bridge.peekLast()[1];
	}
	
}
