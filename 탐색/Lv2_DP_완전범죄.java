/*
 * 문제 요약
 *   - 도둑 A, B가 모든 물건을 하나씩 나눠 훔쳐야 함
 *   - 각 물건은 A 또는 B 둘 중 한 명만 훔칠 수 있음
 *   - 각 도둑이 남긴 흔적이 각각 n 이상, m 이상이 되면 경찰에 잡힘
 *   - 두 도둑 모두 경찰에 잡히지 않으면서 모든 물건을 훔칠 수 있는 경우,
 *     A 도둑의 흔적 누적합을 최소화하여 return
 *   - 불가능하면 -1 반환
 *
 * 입력
 *   - info[i][0]: i번째 물건을 A가 훔칠 때 남기는 흔적 (1 ≤ ≤ 3)
 *   - info[i][1]: i번째 물건을 B가 훔칠 때 남기는 흔적 (1 ≤ ≤ 3)
 *   - n: A 도둑의 흔적 허용 상한 (1 ≤ n ≤ 120)
 *   - m: B 도둑의 흔적 허용 상한 (1 ≤ m ≤ 120)
 *
 * 출력
 *   - 모든 물건을 훔칠 수 있고, A의 흔적 누적합 < n이면 최소값 반환
 *   - 불가능하면 -1 반환
 *
 * 핵심 포인트
 *   - 상태를 [i][j]로 표현 (i: 물건 개수, j: B의 누적 흔적)
 *   - 각 상태에서 A가 훔치면 A 흔적 누적, B가 훔치면 B 흔적 누적
 *   - 모든 경우를 비교하며 A의 누적 흔적을 최소화
 */

class Solution {
    // 매우 큰 수로 초기화용 상수 (아직 도달하지 않은 상태 표시)
    static final int INF = 100000;

    public int solution(int[][] info, int n, int m) {

        int size = info.length;

        // dp[i][j]: i개의 물건을 처리했고, B의 흔적이 j일 때 A의 최소 누적 흔적
        int[][] dp = new int[size + 1][m];

        // 모든 상태를 INF로 초기화 (아직 도달하지 않은 상태)
        for (int i = 0; i <= size; i++) Arrays.fill(dp[i], INF);

        // 초기 상태: 아직 아무것도 훔치지 않았을 때는 A, B 흔적 모두 0
        dp[0][0] = 0;

        // 물건 하나씩 처리
        for (int i = 1; i <= size; i++) {
            int a = info[i - 1][0];  // i번째 물건을 A가 훔칠 때 흔적
            int b = info[i - 1][1];  // i번째 물건을 B가 훔칠 때 흔적

            for (int j = 0; j < m; j++) {
                if (dp[i - 1][j] == INF) continue;

                // A가 i번째 물건 훔치는 경우: B 흔적 유지, A 흔적 추가
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);

                // B가 i번째 물건 훔치는 경우: A 흔적 유지, B 흔적 증가 (단, m 이내)
                if (j + b < m)
                    dp[i][j + b] = Math.min(dp[i][j + b], dp[i - 1][j]);
            }
        }

        // 가능한 모든 B 흔적 케이스 중 A 흔적의 최소값 찾기
        int min = INF;
        for (int i = 0; i < m; i++) min = Math.min(dp[size][i], min);

        // A 흔적이 n 이상이면 실패, 아니면 최소값 반환
        return (min < n) ? min : -1;
    }
}
