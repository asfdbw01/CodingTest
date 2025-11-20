/*  
 * 문제 요약
 *     - 직원은 자신이 설정한 출근 희망 시각(schedules) + 10분 이내 출근해야 상품을 받을 수 있다.
 *     - 출근 기록은 timelogs[i][j]에 저장되며, hhmm 정수 형식이다.
 *     - 이벤트는 startday 요일부터 7일간 진행되며, 주말(토/일)은 검사 대상에서 제외된다.
 *     - 모든 평일 출근 기록이 기준 시간 이하인 직원만 상품을 받을 수 있다.
 *
 * 입력
 *     - int[] schedules: 직원별 출근 희망 시각 (n명)
 *     - int[][] timelogs: 직원별 7일간 출근 기록
 *     - int startday: 이벤트 시작 요일 (1=월 ~ 7=일)
 *
 * 출력
 *     - int: 상품을 받을 수 있는 직원 수
 *
 * 핵심 포인트
 *     - hhmm 형태의 시간을 분 단위로 보정하여 10분 추가 처리
 *     - 요일 계산은 (j + startday - 1) % 7 + 1 로 순환
 *     - 주말(6, 7)은 검사 제외
 *     - 평일 기록 중 하나라도 마감 시각을 초과하면 탈락
 */

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        Outter: for (int i = 0; i < schedules.length; i++) {

            // 희망 시각에서 10분 더해 마감 시각 계산
            int hour = schedules[i] / 100;
            int min = schedules[i] % 100;

            min += 10;
            if (min >= 60) {
                hour++;
                min -= 60;
            }

            int deadline = hour * 100 + min; // 마감 시각 (hhmm)

            // 7일 동안 평일 출근 기록 검사
            for (int j = 0; j < 7; j++) {
                int today = (j + startday - 1) % 7 + 1; // 요일 순환 계산 (1~7)

                if (today >= 6) continue; // 주말은 검사 제외

                // 평일 중 하루라도 지각이면 탈락
                if (timelogs[i][j] > deadline) continue Outter;
            }

            // 모든 평일을 통과한 경우
            answer++;
        }

        return answer;
    }
}
