/*
 * 문제 요약
 *     - 초항이 k인 우박수열을 꺾은선 그래프로 만들고,
 *       주어진 구간 ranges[i] = [a, b]에 대해 정적분 결과(면적)를 계산한다.
 *     - ranges[i][1]이 음수일 경우, 끝점은 수열의 길이에서 해당 값을 더한 위치로 계산한다.
 *     - start > end인 구간은 유효하지 않다고 보고 결과는 -1로 처리한다.
 *
 * 입력
 *     - int k: 우박수열의 시작값 (2 ≤ k ≤ 10,000)
 *     - int[][] ranges: 정적분 구간 리스트 (1 ≤ 길이 ≤ 10,000)
 *         · ranges[i] = [a, b], 0 ≤ a < 200, -200 < b ≤ 0
 *
 * 출력
 *     - double[]: 각 구간에 대한 우박수열 정적분 결과 (단, 유효하지 않은 구간은 -1)
 *
 * 핵심 포인트
 *     - 우박수열의 각 인접한 두 점을 사다리꼴로 보고 넓이를 계산
 *     - 누적합을 미리 구해두고 각 쿼리에 대해 O(1) 시간으로 계산
 *     - 구간 종료 조건은 n + b로 해석되고, 누적합 배열을 통해 정적분 처리
 */

class Solution {

    // 우박수열 그래프에서 주어진 ranges에 대한 정적분 결과 반환
    public double[] solution(int k, int[][] ranges) {
        // 우박수열 구하기
        int[] hailstoneSequence = getHailstoneSequence(k);
        int n = hailstoneSequence.length - 1;  // 구간 수는 수열 길이 - 1

        // 사다리꼴 넓이를 누적합으로 저장
        double[] prefixSum = new double[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = (hailstoneSequence[i] + hailstoneSequence[i - 1]) / 2d + prefixSum[i - 1];
        }

        double[] answer = new double[ranges.length];

        // 각 구간별 정적분 계산
        for (int i = 0; i < ranges.length; i++) {
            int start = ranges[i][0];
            int end = n + ranges[i][1];  // 끝에서부터 계산

            // 유효하지 않은 구간은 -1, 나머지 구간은 누적합으로 면적 계산
            answer[i] = (start > end) ? -1 : prefixSum[end] - prefixSum[start];
        }

        return answer;
    }

    // 초항 k로 시작하는 우박수열을 생성하여 int 배열로 반환
    public int[] getHailstoneSequence(int k) {
        List<Integer> list = new ArrayList<>();

        // 1이 나올 때까지 우박수열 생성
        while (k != 1) {
            list.add(k);
            k = (k % 2 == 0) ? k / 2 : k * 3 + 1;
        }
        list.add(1);  // 마지막 1 포함

        // List를 int[]로 변환하여 반환
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
