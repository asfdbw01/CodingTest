/*
 * 문제 요약
 * 		- n개의 퍼즐을 제한 시간 내에 풀어야 함
 * 		- 숙련도에 따라 퍼즐을 틀리는 횟수와 걸리는 시간이 달라짐
 * 		- 모든 퍼즐을 제한 시간 내에 풀 수 있는 최소 숙련도를 구하는 문제
 *
 * 입력
 * 		- diffs: 퍼즐의 난이도 배열 (길이 n, diffs[0] = 1)
 * 		- times: 퍼즐의 풀이 시간 배열 (길이 n)
 * 		- limit: 전체 제한 시간 (long, ≤ 10^15)
 *
 * 출력
 * 		- 퍼즐을 모두 풀 수 있는 최소 숙련도 (정수)
 *
 * 핵심 포인트
 * 		- 숙련도를 기준으로 가능한지를 판단하는 단조 함수 → 이분 탐색 적용 가능
 * 		- 각 숙련도마다 퍼즐 전체를 시뮬레이션하여 소요 시간 계산
 * 		- i == 0인 경우에도 times[i-1]는 조건상 실행되지 않으므로 IndexOutOfBounds 발생하지 않음
 */

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int minDiff = 1;  // 숙련도는 최소 1부터 시작
        int maxDiff = Arrays.stream(diffs).max().getAsInt();  // 퍼즐 중 최대 난이도

        return BSearch(diffs, times, limit, minDiff, maxDiff);  // 이분 탐색 수행
    }

    // 제한 시간 내에 가능한 최소 숙련도 탐색
    public int BSearch(int[] diffs, int[] times, long limit, int left, int right) {
        if (left == right) return left;  // 탐색 종료

        int mid = (left + right) / 2;  // 현재 숙련도 후보
        long time = 0;

        for (int i = 0; i < diffs.length; i++) {
            // 숙련도가 부족하면 틀린다 → 되돌아가서 재도전
            // 틀릴 때마다 현재 퍼즐 + 이전 퍼즐 시간 소비
            // 마지막에는 성공 시도 시간 포함
            time += ((mid < diffs[i]) ? (times[i] + times[i - 1]) * (diffs[i] - mid) : 0)
                    + times[i];
        }

        if (time > limit) return BSearch(diffs, times, limit, mid + 1, right);  // 시간 초과 → 숙련도 ↑
        else if (time < limit) return BSearch(diffs, times, limit, left, mid);  // 더 낮은 숙련도로도 가능할지 확인
        else return mid;  // 정확히 일치하면 현재 숙련도가 정답
    }
}
