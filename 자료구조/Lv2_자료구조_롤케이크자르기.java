/*
 * 문제 요약
 *     롤케이크 위에 토핑들이 순서대로 놓여 있고, 하나의 위치에서 자를 수 있다.
 *     자른 후 두 조각의 '토핑 종류 수'가 같다면 공평하게 나눈 것으로 간주한다.
 *     가능한 공평한 자르기 방법의 수를 구하라.
 *
 * 입력
 *     int[] topping : 롤케이크 위 토핑 순서 (1 ≤ topping.length ≤ 1,000,000, 1 ≤ topping[i] ≤ 10,000)
 *
 * 출력
 *     int : 공평하게 자를 수 있는 방법의 수
 *
 * 핵심 포인트
 *     - 왼쪽은 HashSet으로 토핑 종류만 추적
 *     - 오른쪽은 HashMap으로 토핑 개수 관리 (값이 0 되면 제거)
 *     - 초기에는 오른쪽이 전체 토핑을 가지고 있음
 *     - 자르면서 왼쪽에 토핑 추가, 오른쪽에서 감소
 *     - 두 쪽의 종류 수가 같을 때 카운트
 *     - O(N)으로 해결 가능
 */

class Solution {
    
    public int solution(int[] topping) {
        Set<Integer> left = new HashSet<>();                 // 왼쪽 조각의 토핑 종류
        Map<Integer, Integer> right = new HashMap<>();       // 오른쪽 조각의 토핑 개수
        
        // 처음엔 오른쪽이 모든 토핑을 가지고 있음
        Arrays.stream(topping).forEach(t -> right.merge(t, 1, Integer::sum));

        int answer = 0;

        // 한 칸씩 자르며 왼쪽에 토핑 이동
        for (int t : topping) {
            left.add(t);                             // 왼쪽에 토핑 추가
            right.merge(t, -1, Integer::sum);        // 오른쪽에서 개수 감소
            if (right.get(t) <= 0) right.remove(t);  // 개수가 0되면 제거

            // 두 조각의 토핑 종류 수가 같으면 공평한 자르기
            if (left.size() == right.size()) answer++;
            
            // 왼쪽 조각의 토핑 종류 수가 오른쪽 조각의 토핑 종류 수보다 커지면 종료
            if (left.size() > right.size()) break;
        }

        return answer;
    }
}
