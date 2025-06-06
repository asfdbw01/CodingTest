/*
 * 문제 요약
 *     - 아날로그 시계에서 초침이 시침이나 분침과 겹칠 때마다 알람이 울림
 *     - 주어진 시간 구간 동안 알람이 몇 번 울렸는지 계산
 *
 * 입력
 *     - h1, m1, s1: 시작 시각 (0 ≤ h1 ≤ 23, 0 ≤ m1 ≤ 59, 0 ≤ s1 ≤ 59)
 *     - h2, m2, s2: 종료 시각 (h1시 m1분 s1초 < h2시 m2분 s2초)
 *
 * 출력
 *     - 알람이 울린 총 횟수
 *
 * 핵심 포인트
 *     - 초침이 시침을 추월하는 주기: 360 * (120 / 719) ≈ 60.0834초
 *     - 초침이 분침을 추월하는 주기: 360 * (10 / 59) ≈ 61.0169초
 *     - 시작 시점, 12:00:00, 종료 시점 등의 경계 처리를 정확히 해야 함
 *     - 겹치는 순간은 각도로 비교하며 부동소수점 오차 처리 필요
 */

class Solution {
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int alarmCnt = 0;

        // 초침이 시침, 분침을 추월하는 주기
        double hCycle = 360d * (120d / 719d);
        double mCycle = 360d * (10d / 59d);

        // 시작 시각과 종료 시각을 초 단위로 변환
        double start = toSec(h1, m1, s1);
        double end = toSec(h2, m2, s2);
        double runtime = end - start;
        double noon = toSec(12, 0, 0);

        // 각각 몇 번의 사이클이 발생하는지 계산
        int hCycleCount = (int) (runtime / hCycle);
        int mCycleCount = (int) (runtime / mCycle);

        // 남은 시간 동안 겹침이 발생했는지 확인 필요
        double hCycleRemainSec = runtime % hCycle;
        double mCycleRemainSec = runtime % mCycle;

        // 각 바늘의 1단위당 회전 각도
        int hDegree = 360 / 12;  // 시침: 시간당 30도
        int mDegree = 360 / 60;  // 분침: 분당 6도
        int sDegree = 360 / 60;  // 초침: 초당 6도

        // 시작 시각이 00분 00초인 경우 (세 바늘 겹침)
        if (m1 == 0 && s1 == 0) alarmCnt++;

        // 정수 사이클 개수만큼 알람 추가
        alarmCnt += hCycleCount + mCycleCount;

        // 중간에 12:00:00 시점이 포함되면 중복 알람 제거
        if (start < noon && noon < end) alarmCnt--;

        // 종료 시점이 정확히 12:00:00이면 알람 1회 추가
        if (end == noon) alarmCnt++;

        else {
            // 시침-초침 잔여 구간 겹침 확인
            if (hCycleRemainSec != 0) {
                double lastSec = (s1 + (hCycle * hCycleCount)) % 60;
                double lastHour = ((h1 * 3600) + (m1 * 60) + s1 + (hCycle * hCycleCount)) / 3600 % 12;

                double lastSecDegree = roundAt(lastSec * sDegree, 8) % 360;
                double lastHourDegree = roundAt(lastHour * hDegree, 8) % 360;

                // 겹치지 않았을 경우 → 다음 겹침 지점까지 비교
                if (lastSecDegree != lastHourDegree) {
                    double finalSecDegree = roundAt(lastSec * sDegree + hCycleRemainSec * sDegree, 8);
                    double finalHourDegree = roundAt(lastHour * hDegree + (hCycleRemainSec / 3600) * hDegree, 8) % 360;

                    // 초침이 시침을 추월하는 구조이므로 기준 조정
                    finalHourDegree += lastSecDegree > lastHourDegree ? 360 : 0;

                    if (finalSecDegree >= finalHourDegree) alarmCnt++;
                }
            }

            // 분침-초침 잔여 구간 겹침 확인
            if (mCycleRemainSec != 0) {
                double lastSec = (s1 + (mCycle * mCycleCount)) % 60;
                double lastMin = ((m1 * 60) + s1 + (mCycle * mCycleCount)) / 60 % 60;

                double lastSecDegree = roundAt(lastSec * sDegree, 8) % 360;
                double lastMinDegree = roundAt(lastMin * mDegree, 8) % 360;

                // 겹치지 않았을 경우 → 다음 겹침 지점까지 비교
                if (lastSecDegree != lastMinDegree) {
                    double finalSecDegree = roundAt(lastSec * sDegree + mCycleRemainSec * sDegree, 8);
                    double finalMinDegree = roundAt(lastMin * mDegree + (mCycleRemainSec / 60) * mDegree, 8) % 360;

                    // 초침이 분침을 추월하는 구조이므로 기준 조정
                    finalMinDegree += lastSecDegree > lastMinDegree ? 360 : 0;

                    if (finalSecDegree >= finalMinDegree) alarmCnt++;
                }
            }
        }

        return alarmCnt;
    }

    // 시, 분, 초 → 초 단위로 변환
    public double toSec(int hour, int min, int sec) {
        return 3600 * hour + 60 * min + sec;
    }

    // 부동소수점 오차 방지를 위한 반올림 함수
    public double roundAt(double num, int pos) {
        double digit = (int) Math.pow(10, pos);
        return Math.round(num * digit) / digit;
    }
}
