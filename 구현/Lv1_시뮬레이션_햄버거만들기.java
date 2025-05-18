/*
 * 문제 요약
 *     - 재료 배열이 주어질 때, [빵-야채-고기-빵] 순서로 쌓인 햄버거만 포장 가능
 *     - 포장한 햄버거는 재료 스택에서 제거됨
 *     - 재료는 순서대로 하나씩 쌓이며, 중간 삽입은 없음

 * 입력
 *     - ingredient: 재료 배열 (길이 ≤ 1,000,000), 값은 1(빵), 2(야채), 3(고기)

 * 출력
 *     - 완성된 햄버거의 개수

 * 핵심 포인트
 *     - 시간 복잡도는 O(N)이어야 함
 *     - 연속된 패턴 [1,2,3,1]이 나올 때마다 포장 및 제거 처리
 *     - Stack<Integer> 대신 수제 int[] 스택으로 처리해 성능 극대화
 */

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;

        // 수제 스택: 재료를 순서대로 쌓기 위한 배열
        int[] stack = new int[ingredient.length];
        int index = 0;  // 스택의 현재 위치 (포인터)

        // 모든 재료를 순서대로 처리
        for (int food : ingredient) {
            // 현재 재료를 스택 위에 쌓음 (push)
            stack[index++] = food;

            // 스택 위에 쌓인 마지막 4개가 [1,2,3,1] 패턴이면 햄버거 포장
            if (index >= 4 &&
                stack[index - 4] == 1 &&
                stack[index - 3] == 2 &&
                stack[index - 2] == 3 &&
                stack[index - 1] == 1) {
                
                // 햄버거 포장: 4칸 되돌리기 (재료 제거)
                index -= 4;
                answer++;  // 포장한 햄버거 수 증가
            }
        }

        return answer;
    }
}
