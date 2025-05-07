// 문제 요약:
// 트럭들이 순서대로 일차선 다리를 건너는 최소 시간을 구하는 문제.
// 조건:
// - 동시에 올라갈 수 있는 트럭 수는 bridge_length 이하.
// - 다리가 견딜 수 있는 총 무게는 weight 이하.
// - 다리를 건너는 데는 bridge_length초가 걸림.
// - 아직 다리에 오르지 않은 트럭은 무게 계산에 포함되지 않음.
// 목표:
// 모든 트럭이 다리를 건너는 데 걸리는 최소 시간 반환.

import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> bridge = new LinkedList<>();
        int totalWeight = 0;
        int idx = 0;

        // 다리 초기화
        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }

        while (idx < truck_weights.length) {
            answer++;

            int out = bridge.poll();
            totalWeight -= out;

            if (totalWeight + truck_weights[idx] <= weight) {
                bridge.add(truck_weights[idx]);
                totalWeight += truck_weights[idx];
                idx++;
            } else {
                bridge.add(0); // 자리 채우기
            }
        }

        return answer + bridge_length; // 마지막 트럭 빠질 때까지 시간 추가
    }
}
