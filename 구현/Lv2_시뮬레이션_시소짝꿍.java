/*
 * 문제 요약
 *     - 중심에서 2, 3, 4m 떨어진 시소 좌석에 두 명이 마주 보고 앉을 때,
 *       각자의 몸무게 * 거리 = 토크가 서로 같으면 '시소 짝꿍'으로 간주함
 *     - 주어진 weights 배열에서, 시소 짝꿍 쌍이 총 몇 개 존재하는지 구하는 문제
 * 
 * 입력
 *     - weights: 사람들의 몸무게 배열 (길이 2 이상, 최대 100,000)
 *     - 100 ≤ weights[i] ≤ 1,000
 * 
 * 출력
 *     - 시소 짝꿍 쌍의 총 개수 (long 타입 반환)
 * 
 * 핵심 포인트
 *     - 몸무게가 같으면 1:1 비율로 조합 수만큼 쌍 생성
 *     - 시소 거리 비율로 가능한 무게 비율은 {1:2, 2:3, 3:4} 등 정해진 경우만 고려
 *     - 오버플로우를 피하기 위해 wCnt를 long[]으로 선언
 *     - 몸무게가 100~1000 범위이므로 배열 기반 카운팅으로 O(1) 탐색 가능
 */

class Solution {

    // 시소 거리 비율에 따른 무게 비율 (왼쪽 : 오른쪽)
    private final int[][] RATIOS = {{1, 2}, {2, 3}, {3, 4}};

    public long solution(int[] weights) {
        long cnt = 0L;
        long[] wCnt = new long[1001]; // 몸무게별 사람 수 (100 ≤ w ≤ 1000)

        // 몸무게별 사람 수를 카운트
        for (int w : weights) {
            wCnt[w]++;
        }

        // 몸무게가 100부터 1000까지 가능한 조합을 순회
        for (int w = 100; w <= 1000; w++) {
            if (wCnt[w] == 0) continue;

            // 같은 몸무게끼리 시소 짝꿍 (1:1 비율)
            cnt += (wCnt[w] - 1) * wCnt[w] / 2;

            // 다른 비율의 시소 짝꿍 탐색
            for (int[] ratio : RATIOS) {
                if (w % ratio[0] != 0) continue;

                int target = (w / ratio[0]) * ratio[1];

                // 유효 범위 벗어나면 스킵
                if (target > 1000 || wCnt[target] == 0) continue;

                // 짝꿍 쌍 추가
                cnt += wCnt[w] * wCnt[target];
            }
        }

        return cnt;
    }

}
