/**
 * 문제 요약
 *     - 알고력(alp), 코딩력(cop)을 높여서 모든 문제를 풀 수 있는 능력을 갖추는 최소 시간을 구한다.
 *     - 알고리즘 공부는 알고력 +1에 시간 1, 코딩 공부는 코딩력 +1에 시간 1 소요.
 *     - 각 문제마다 필요 조건, 보상 능력치, 소요 시간이 주어지며, 같은 문제는 여러 번 풀 수 있다.
 *     - 알고력과 코딩력은 0 이상이며, 문제를 풀 수 없는 경우엔 먼저 능력을 키워야 한다.
 *
 * 입력
 *     - int alp, cop: 초기 알고력과 코딩력 (0 ≤ alp, cop ≤ 150)
 *     - int[][] problems: 문제 리스트 (최대 100개)
 *         - 각 문제는 [alp_req, cop_req, alp_rwd, cop_rwd, cost] 형태
 *
 * 출력
 *     - 모든 문제를 풀 수 있는 알고력/코딩력을 갖추는 데 걸리는 최소 시간 (int)
 *
 * 핵심 포인트
 *     - 목표는 모든 문제의 최대 요구치 이상을 만족하는 능력치를 갖는 것이다.
 *     - 목표치보다 더 높은 능력치를 얻는 것이 더 빠른 경우도 있음 (문제 보상이 크면)
 *     - 2차원 DP: dp[a][c] = (a, c) 상태에 도달하는 최소 시간
 *     - 공부 + 문제풀이를 상태 전이로 구성하고, 정방향으로 갱신
 *     - 문제 풀이 후 능력치가 max 범위를 초과하지 않도록 clamp 처리 필요
 */

class Solution {

    private final int INF = 30001;

    public int solution(int alp, int cop, int[][] problems) {
        int targetAlp = 0, targetCop = 0;

        // 모든 문제의 요구 알고력/코딩력 중 최댓값 계산
        for (int[] problem : problems) {
            targetAlp = Math.max(targetAlp, problem[0]);
            targetCop = Math.max(targetCop, problem[1]);
        }

        // 현재 상태가 이미 모든 문제를 풀 수 있으면 시간 0
        if (alp >= targetAlp && cop >= targetCop) return 0;

        // DP 테이블의 최대 크기 결정 (현재 능력치 이상으로 설정)
        int maxAlp = Math.max(targetAlp, alp);
        int maxCop = Math.max(targetCop, cop);

        // DP 테이블 초기화 (모든 상태를 INF로 초기화)
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int[] arr : dp) Arrays.fill(arr, INF);
        dp[alp][cop] = 0;

        // 정방향으로 상태 전이 수행
        for (int a = alp; a <= maxAlp; a++) {
            for (int c = cop; c <= maxCop; c++) {
                int cur = dp[a][c];

                // 알고력 1 증가 (공부)
                if (a + 1 <= maxAlp)
                    dp[a + 1][c] = Math.min(dp[a + 1][c], cur + 1);

                // 코딩력 1 증가 (공부)
                if (c + 1 <= maxCop)
                    dp[a][c + 1] = Math.min(dp[a][c + 1], cur + 1);

                // 문제 풀이를 통한 상태 전이
                for (int[] problem : problems) {
                    if (a >= problem[0] && c >= problem[1]) {
                        // 보상 반영 후 범위 초과하지 않도록 제한
                        int na = Math.min(maxAlp, a + problem[2]);
                        int nc = Math.min(maxCop, c + problem[3]);

                        // 해당 문제 풀었을 때 걸리는 시간 고려
                        dp[na][nc] = Math.min(dp[na][nc], cur + problem[4]);
                    }
                }
            }
        }

        // 최종 목표 상태에서 최소 시간 반환
        return dp[maxAlp][maxCop];
    }
}
