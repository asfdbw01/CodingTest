/*  
 * 문제 요약
 *     - 세 개의 기둥과 n개의 원판이 있을 때, 1번 기둥의 모든 원판을 3번 기둥으로 옮기는 최소 이동 과정을 구함
 *     - 단, 한 번에 하나의 원판만 옮길 수 있고, 더 큰 원판은 더 작은 원판 위에 놓을 수 없음
 *
 * 입력  
 *     - 정수 n (1 ≤ n ≤ 15): 1번 기둥에 쌓인 원판의 개수
 *
 * 출력  
 *     - int[][]: 각 이동을 [from, to] 형태로 기록한 이동 경로 배열
 *
 * 핵심 포인트  
 *     - 하노이 탑의 이동 규칙은 재귀적 구조로 정의됨
 *         1. n-1개를 보조 기둥으로 옮김
 *         2. 가장 큰 원판을 목적지 기둥으로 옮김
 *         3. 보조 기둥에 있는 n-1개를 목적지로 옮김
 *     - 재귀 호출을 통해 이동 순서를 정렬하고 기록
 */

class Solution {
    
    List<int[]> answer = new ArrayList<>();
    
    public int[][] solution(int n) {
        hanoi(n, 1, 2, 3);
        return answer.toArray(int[][]::new);
    }
    
    public void hanoi(int n, int from, int via, int to) {
        if (n == 1) {
            answer.add(new int[] {from, to});
            return;
        }
        
        hanoi(n - 1, from, to, via);    // 1단계: n-1개를 보조로 옮김
        answer.add(new int[] {from, to}); // 2단계: 가장 큰 원판 이동
        hanoi(n - 1, via, from, to);    // 3단계: 보조에 있던 원판들을 목적지로 옮김
    }
}
