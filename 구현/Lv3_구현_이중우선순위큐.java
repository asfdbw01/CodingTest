/*
문제 요약:
- 이중 우선순위 큐를 구현한다.
- 연산 종류:
  - "I 숫자" : 숫자를 큐에 삽입
  - "D 1"   : 큐에서 최댓값 삭제
  - "D -1"  : 큐에서 최솟값 삭제
- 큐가 비어 있으면 삭제 연산은 무시
- 모든 연산 후 큐가 비어 있으면 [0, 0] 반환
  아니면 [최댓값, 최솟값] 반환

제한:
- 연산 수 최대 1,000,000
- 같은 값 여러 개 있어도 삭제는 1개만 수행

예시:
1) ["I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"]
   → 삽입 후 최소/최대 삭제 반복 → 최종적으로 큐는 비어 있음 → [0, 0]

2) ["I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"]
   → 삽입 후 최소/최대 삭제 → 최종 큐: [-45, 45, 333] → [333, -45] 반환
*/
import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        Map<Integer, Integer> count = new HashMap<>();

        for (String op : operations) {
            String[] parts = op.split(" ");
            String command = parts[0];
            int num = Integer.parseInt(parts[1]);

            if (command.equals("I")) {
                minQ.add(num);
                maxQ.add(num);
                count.put(num, count.getOrDefault(num, 0) + 1);
            } else {
                if (count.isEmpty()) continue;
                if (num == 1) remove(maxQ, count);
                else remove(minQ, count);
            }
        }

        if (count.isEmpty()) return new int[]{0, 0};

        int max = getValid(maxQ, count);
        int min = getValid(minQ, count);
        return new int[]{max, min};
    }

    private void remove(PriorityQueue<Integer> pq, Map<Integer, Integer> count) {
        while (!pq.isEmpty()) {
            int val = pq.poll();
            if (count.containsKey(val)) {
                count.put(val, count.get(val) - 1);
                if (count.get(val) == 0) count.remove(val);
                break;
            }
        }
    }

    private int getValid(PriorityQueue<Integer> pq, Map<Integer, Integer> count) {
        while (!pq.isEmpty()) {
            int val = pq.peek();
            if (count.containsKey(val)) return val;
            pq.poll();
        }
        return 0;
    }
}
