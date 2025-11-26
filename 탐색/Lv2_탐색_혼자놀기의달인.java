/*
 * 문제 요약  
 *     - 상자들 안에 카드를 넣고, 상자를 따라가며 연결된 그룹을 찾음  
 *     - 두 개의 상자 그룹을 만든 뒤, 각각의 크기를 곱한 최대값이 점수임
 *  
 * 입력  
 *     - cards: 길이 N (2 ≤ N ≤ 100), cards[i]는 i+1번 상자에 든 카드 숫자  
 *     - 모든 값은 중복되지 않음 (순열)
 *  
 * 출력  
 *     - 가능한 최고 점수 반환 (int)
 *  
 * 핵심 포인트  
 *     - 순열을 따라가며 사이클 탐색  
 *     - 가장 큰 사이클 두 개 선택 후 길이 곱하기  
 *     - 방문체크는 Set을 이용해 간결하게 처리
 */

class Solution {

    public int solution(int[] cards) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        Set<Integer> visited = new HashSet<>();
        
        for (int card : cards) {
            int cur = card;
            int len = 0;
            
            // 방문한 상자는 다시 방문하지 않도록 Set에 기록
            while (visited.add(cur)) {
                cur = cards[cur - 1];
                len++;
            }

            pq.add(len);
        }

        return pq.poll() * pq.peek();  // 가장 큰 두 그룹 길이 곱
    }
}
