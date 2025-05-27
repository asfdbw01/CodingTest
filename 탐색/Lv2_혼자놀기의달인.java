/*
 * 문제 설명
 * - 1부터 N까지 적힌 카드가 상자 안에 1장씩 들어 있음
 * - cards[i]는 i+1번 상자 안에 들어 있는 카드 번호 (즉, 다음에 열 상자 번호)

 * 게임 규칙
 * 1. 임의의 상자 하나를 열고, 그 안의 숫자를 따라가며 상자를 계속 엶
 * 2. 이미 연 상자를 만나면 사이클 종료 → 이게 1번 상자 그룹
 * 3. 이 상자들을 제외하고, 또 같은 방식으로 다른 사이클을 구성 → 2번 상자 그룹
 * 4. 점수 = 두 그룹의 크기 곱. 단, 한 그룹밖에 못 만들면 점수는 0

 * 목표
 * - cards 배열이 주어졌을 때, 만들 수 있는 **최대 점수**를 return

 * 제한 사항
 * - 2 ≤ cards.length ≤ 100
 * - cards 배열은 1부터 N까지의 순열 (중복 없음, 유일한 순열)

 * 핵심 알고리즘
 * - 사이클 탐색 (DFS 또는 반복문)
 * - 사이클 2개를 선택해 곱의 최댓값 계산
 */


import java.util.*;

class Solution {
    public int solution(int[] cards) {
        boolean[] visited = new boolean[cards.length];
        List<Integer> groupSizes = new ArrayList<>();

        for (int i = 0; i < cards.length; i++) {
            if (!visited[i]) {
                int size = 0;
                int curr = i;
                while (!visited[curr]) {
                    visited[curr] = true;
                    curr = cards[curr] - 1; 
                    size++;
                }
                groupSizes.add(size);
            }
        }

        // 사이클 크기 리스트에서 가장 큰 두 개 선택
        if (groupSizes.size() < 2) return 0;
        Collections.sort(groupSizes, Collections.reverseOrder());

        return groupSizes.get(0) * groupSizes.get(1);
    }
}
