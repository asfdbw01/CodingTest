/*
 * 문제 요약
 *     - A와 B가 각각 n/2개의 주사위를 선택하여, 각자 주사위를 모두 굴려 나온 합으로 승패를 결정한다.
 *     - 각 주사위는 6개의 면을 가지고 있고, 면의 숫자는 정해져 있다.
 *     - A가 먼저 주사위를 고르고, B는 남은 주사위를 가져간다.
 *     - A는 승리할 확률이 가장 높은 조합을 선택하려 한다.
 * 
 * 입력
 *     - int[][] dice: n개의 주사위 정보가 담긴 2차원 배열 (각 행은 주사위 1개, 각 열은 6개의 눈)
 *     - 2 ≤ n ≤ 10, n은 짝수
 *     - 1 ≤ dice[i][j] ≤ 100
 * 
 * 출력
 *     - A가 선택했을 때 승리 확률이 가장 높은 주사위 조합 (1-based 인덱스, 오름차순)
 * 
 * 핵심 포인트
 *     - nC(n/2) 조합을 비트마스킹으로 생성
 *     - 조합의 대칭성을 이용해 탐색을 절반으로 줄임 (mask < complement)
 *     - DP로 주사위 합 분포를 계산 (countSums)
 *     - 누적합을 이용해 A의 합 > B의 합인 경우만 빠르게 계산
 *     - 승리 횟수가 가장 높은 조합을 선택
 */

class Solution {

    int[][] dice;      // 입력 주사위 저장용 전역 변수
    int fullMask;      // 전체 주사위 선택 상태를 나타내는 비트마스크 (111...1)

    public int[] solution(int[][] input) {
        this.dice = input;

        int n = dice.length;
        int diceCnt = n / 2;               // A가 선택할 주사위 개수
        this.fullMask = (1 << n) - 1;      // n개의 비트를 모두 1로 채운 fullMask

        // 현재까지 가장 승률이 높은 조합 저장
        Map.Entry<Integer, Integer> bestCase = Map.entry(0, -1);

        // nC(n/2) 모든 조합을 비트마스킹으로 탐색
        for (int i = 0; i < (1 << n); i++) {
            if (Integer.bitCount(i) == diceCnt) {
                int mask = i;
                int complement = ~mask & fullMask;  // A의 보완 조합 = B의 조합

                if (mask < complement) {  // 대칭 제거
                    Map.Entry<Integer, Integer> nowCase = getBest(mask, complement);
                    if (nowCase.getValue() > bestCase.getValue()) {
                        bestCase = nowCase;
                    }
                }
            }
        }

        // bestCase의 주사위 번호를 1-based 배열로 반환
        int[] answer = new int[diceCnt];
        int mask = bestCase.getKey();
        for (int i = 0, index = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) {
                answer[index++] = i + 1;   // 1-based로 변환
            }
        }

        return answer;
    }

    /**
     * 두 조합(mask, complement) 중 승률이 더 높은 쪽을 반환
     * 반환값은 Map.Entry<mask, 승리횟수>
     */
    public Map.Entry<Integer, Integer> getBest(int mask, int complement) {
        int[] sumsA = countSums(mask);
        int[] sumsB = countSums(complement);

        int winA = countWins(sumsA, sumsB.clone());  // A vs B
        int winB = countWins(sumsB, sumsA.clone());  // B vs A

        return (winA > winB) ? Map.entry(mask, winA) : Map.entry(complement, winB);
    }

    /**
     * A가 선택한 주사위의 합 분포(sumsA)와 B의 합 분포(sumsB)를 비교해
     * A가 B보다 더 큰 합을 가질 경우의 수를 계산
     */
    public int countWins(int[] sumsA, int[] sumsB) {
        int wins = 0;

        // B의 합에 대한 누적합 계산
        for (int sum = 1; sum < sumsB.length; sum++) {
            sumsB[sum] += sumsB[sum - 1];
        }

        int bMax = sumsB.length - 1;

        // A의 합이 sum일 때, B가 그보다 작은 경우 수를 모두 곱하여 승리 수 계산
        for (int sum = 3; sum < sumsA.length; sum++) {
            int sumB = sumsB[Math.min(sum - 1, bMax)];
            wins += sumB * sumsA[sum];
        }

        return wins;
    }

    /**
     * 선택된 주사위 마스크로 만들 수 있는 모든 합의 경우 수를 DP로 계산
     * dp[sum] = sum이 만들어질 수 있는 경우의 수
     */
    public int[] countSums(int mask) {
        int maxSum = getMaxSum(mask);
        int[] dp = new int[maxSum + 1];
        dp[0] = 1;  // 합이 0인 경우 초기화

        // 주사위 하나씩 추가하면서 가능한 합 갱신
        for (int i = 0; i < dice.length; i++) {
            if ((mask & (1 << i)) == 0) continue;

            int[] next = new int[maxSum + 1];
            for (int sum = 0; sum <= maxSum; sum++) {
                if (dp[sum] == 0) continue;

                for (int face : dice[i]) {
                    next[sum + face] += dp[sum];
                }
            }
            dp = next;
        }

        return dp;
    }

    /**
     * 선택된 마스크에 포함된 주사위들로 만들 수 있는 최대 합 반환
     * → DP 배열의 크기를 설정하기 위해 사용
     */
    public int getMaxSum(int mask) {
        int sum = 0;

        for (int i = 0; i < dice.length; i++) {
            if ((mask & (1 << i)) != 0) {
                int max = 0;
                for (int n : dice[i]) {
                    max = Math.max(max, n);
                }
                sum += max;
            }
        }

        return sum;
    }
}
