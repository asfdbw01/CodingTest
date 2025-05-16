/*
 * 문제 요약
 *     - x축 개구간 (s, e)로 표현된 폭격 미사일들을 모두 요격해야 한다.
 *     - 한 요격 미사일은 특정 실수 x에서 수직 발사되며, 해당 x를 포함하는 모든 개구간을 요격할 수 있다.
 *     - 최소 개수의 요격 미사일로 모든 폭격 미사일을 요격해야 한다.
 *
 * 입력
 *     - targets: int[][], 각 행은 [s, e] 형태의 개구간 (0 ≤ s < e ≤ 100,000,000), 길이 최대 500,000
 *
 * 출력
 *     - int: 모든 구간을 커버하는 요격 미사일의 최소 개수
 *
 * 핵심 포인트
 *     - 종료지점(e) 기준으로 정렬한 후, 겹치지 않는 구간이 나올 때마다 요격 지점 갱신
 *     - 종료지점을 기준으로 탐욕적 선택을 하면 항상 최적임이 보장됨 (Greedy Choice + Optimal Substructure)
 *     - 시간 복잡도: O(N log N), N은 구간 개수
 */

class Solution {
    public int solution(int[][] targets) {
        // 종료 지점 기준 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);

        int answer = 1;                      // 최소 한 번은 요격 필요
        int point = targets[0][1];          // 첫 번째 요격 미사일을 e1 - ε에 쏨

        for (int i = 1; i < targets.length; i++) {
            // 현재 구간이 이전 요격 지점에 포함되지 않으면 새로운 요격 미사일 발사
            if (targets[i][0] >= point) {
                point = targets[i][1];      // 새로운 요격 지점 갱신
                answer++;
            }
        }

        return answer;
    }
}
