/*
 * 📄 입국심사 - 최소 시간 계산 (이분 탐색)
 *
 * 문제 요약
 * ----------
 * - n명의 사람이 입국 심사를 받기 위해 기다린다.
 * - 심사관마다 한 사람을 심사하는 데 걸리는 시간이 다르다.
 * - 모든 사람이 심사를 끝내는 데 걸리는 최소 시간을 구하라.
 *
 * 입력
 * -----
 * - n: 사람 수 (1 ≤ n ≤ 1,000,000,000)
 * - times: 심사관별 심사 시간 배열 (길이 ≤ 100,000, 각 원소 ≤ 1,000,000,000)
 *
 * 출력
 * -----
 * - 모든 사람이 심사를 마치는 최소 시간 (long)
 *
 * 조건
 * -----
 * - 모든 사람은 비어 있는 심사대로 바로 가거나 기다렸다가 더 빨리 끝나는 곳으로 갈 수 있다.
 * - 한 심사대에서는 동시에 한 명만 심사 가능.
 *
 * 풀이
 * -----
 * ✔ 시간의 범위를 [1, 가장 느린 심사관 × n]으로 설정
 * ✔ mid 시간 동안 처리 가능한 사람 수를 계산
 * ✔ 처리 가능 인원이 n 이상이면 시간을 줄이고, 아니면 늘린다.
 * ✔ 이분 탐색으로 최소 시간을 찾는다.
 *
 * 예시
 * -----
 * n = 6, times = [7, 10] → 최소 시간 = 28
 */
import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long left = 1;
        long right = (long) Arrays.stream(times).min().getAsInt() * n;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (audit(mid, times) >= n) {
                answer = mid; // 가능한 시간 중 최소값 갱신
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private long audit(long time, int[] times) {
        long sum = 0;
        for (int t : times) {
            sum += time / t;
        }
        return sum;
    }
}
