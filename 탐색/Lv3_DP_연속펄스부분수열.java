/*
 * 📄 연속 펄스 부분 수열의 합 최대값
 *
 * 문제 요약
 * ----------
 * - 주어진 수열의 연속 부분 수열과 길이가 같은 펄스 수열을 곱해
 *   얻을 수 있는 합의 최댓값을 구한다.
 * - 펄스 수열: [1, -1, 1, -1, …] 또는 [-1, 1, -1, 1, …] 형태
 *
 * 입력
 * -----
 * - sequence: 정수 배열 (길이 ≤ 500,000, 원소 ≤ |100,000|)
 *
 * 출력
 * -----
 * - 연속 펄스 부분 수열의 합 중 최대값 (long)
 *
 * 조건
 * -----
 * - 연속된 부분 수열의 한 구간을 선택하고
 * - [1, -1, …] 또는 [-1, 1, …] 중 하나를 곱해 최대합 계산
 *
 * 예시
 * -----
 * sequence = [2, 3, -6, 1, 3, -1, 2, 4]
 * → 연속 부분 수열 [3, -6, 1] × [1, -1, 1] = [3, 6, 1] → 합 = 10 (최대)
 */
class Solution {
    public long solution(int[] sequence) {
        long max = Long.MIN_VALUE;
        long curr1 = 0;  // +1부터 시작
        long curr2 = 0;  // -1부터 시작

        for (int i = 0; i < sequence.length; i++) {
            int sign1 = (i % 2 == 0) ? 1 : -1;
            int sign2 = -sign1;

            long val1 = sequence[i] * sign1;
            long val2 = sequence[i] * sign2;

            curr1 = Math.max(val1, curr1 + val1);
            curr2 = Math.max(val2, curr2 + val2);

            max = Math.max(max, Math.max(curr1, curr2));
        }

        return max;
    }
}
