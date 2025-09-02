/*
 * 문제 요약
 *     - 1번부터 n번까지 상자가 메인 벨트에 순서대로 도착함
 *     - 트럭에 기사님이 원하는 순서(order)에 맞게 실어야 함
 *     - 원하는 순서가 아니면 보조 벨트(stack)에 넣고, 이후 필요할 때 꺼냄
 *
 * 입력
 *     - int[] order: 트럭에 실어야 할 순서
 *
 * 출력
 *     - int: 트럭에 실을 수 있는 상자 개수
 *
 * 핵심 포인트
 *     - 메인 벨트는 순차적 (1 ~ n)
 *     - 보조 벨트는 후입선출 (스택)
 *     - 상자를 하나씩 push하고, 실을 수 있는 한 계속 pop
 */

class Solution {
	
    public int solution(int[] order) {
        int cnt = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        // 메인 벨트에서 상자 도착
        for (int box = 1; box <= order.length; box++) {
            stack.push(box);

            // 트럭에 실을 수 있을 때까지 보조 벨트에서 pop
            while (!stack.isEmpty() && stack.peek() == order[cnt]) {
                stack.pop();
                cnt++;
            }
        }

        return cnt;
    }
}
