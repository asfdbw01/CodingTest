/*
 * 문제 요약
 *     - 아날로그 시계에서 초침이 시침이나 분침과 정확히 겹칠 때마다 알람이 울림
 *     - 주어진 시간 구간 동안 알람이 몇 번 울렸는지 구하는 문제
 *
 * 입력
 *     - 정수 h1, m1, s1: 시작 시각 (0 ≤ h1 ≤ 23, 0 ≤ m1 ≤ 59, 0 ≤ s1 ≤ 59)
 *     - 정수 h2, m2, s2: 종료 시각 (h1시 m1분 s1초 < h2시 m2분 s2초)
 *
 * 출력
 *     - 알람이 울린 총 횟수 (초침이 시침 또는 분침과 겹친 경우 포함, 단 동일 시점 겹침은 1회만 카운트)
 *
 * 핵심 포인트
 *     - 초침과 시침이 겹치는 주기: 약 60.0834초 (360 * 120 / 719)
 *     - 초침과 분침이 겹치는 주기: 약 61.0169초 (360 * 10 / 59)
 *     - 시작 시각에 알람이 울렸는지 별도 처리
 *     - 12시 정각에 세 바늘이 겹칠 경우 중복 카운트 제거 필요
 */

class Solution {

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int alarmCnt = 0;

        // 초침과 시침이 겹치는 주기
        double hCycle = 360d * (120d / 719d);
        // 초침과 분침이 겹치는 주기
        double mCycle = 360d * (10d / 59d);

        // 시작 시간과 종료 시간을 초 단위로 환산
        double start = toSec(h1, m1, s1);
        double end = toSec(h2, m2, s2);
        double runtime = end - start;
        double noon = toSec(12, 0, 0);  // 12:00:00 기준

        // 사이클 수만큼 겹침 계산
        int hCycleCount = (int) (runtime / hCycle);
        int mCycleCount = (int) (runtime / mCycle);

        double hCycleRemainSec = runtime % hCycle;
        double mCycleRemainSec = runtime % mCycle;

        int hDegree = 360 / 12;  // 시침 1시간당 30도
        int mDegree = 360 / 60;  // 분침 1분당 6도
        int sDegree = 360 / 60;  // 초침 1초당 6도

        // 시작 시각에 세 바늘이 겹친 경우 (알람 1회)
        if (m1 == 0 && s1 == 0) alarmCnt++;

        // 전체 싸이클 개수만큼 알람 추가
        alarmCnt += hCycleCount + mCycleCount;

        // 중간에 정확히 12:00:00을 지난 경우 중복 알람 제거
        if (start < noon && noon < end) alarmCnt--;

        // 종료 시각이 12:00:00인 경우 예외적으로 추가
        if (end == noon) alarmCnt++;

        // 시침-초침이 겹치는 마지막 부분 처리
        if (hCycleRemainSec != 0) {
            double lastSec = (s1 + (hCycle * hCycleCount)) % 60;
            double lastHour = ((h1 * 3600) + (m1 * 60) + s1 + (hCycle * hCycleCount)) / 3600 % 12;

            double lastSecDegree = roundAt(lastSec * sDegree, 8) % 360;
            double lastHourDegree = roundAt(lastHour * hDegree, 8) % 360;

            if (lastSecDegree != lastHourDegree) {
                double finalSecDegree = roundAt(lastSec * sDegree + hCycleRemainSec * sDegree, 8);
                double finalHourDegree = roundAt(lastHour * hDegree + (hCycleRemainSec / 3600) * hDegree, 8) % 360;
                finalHourDegree += lastSecDegree > lastHourDegree ? 360 : 0;

                if (finalSecDegree >= finalHourDegree) alarmCnt++;
            }
        }

        // 분침-초침이 겹치는 마지막 부분 처리
        if (mCycleRemainSec != 0) {
            double lastSec = (s1 + (mCycle * mCycleCount)) % 60;
            double lastMin = ((m1 * 60) + s1 + (mCycle * mCycleCount)) / 60 % 60;

            double lastSecDegree = roundAt(lastSec * sDegree, 8) % 360;
            double lastMinDegree = roundAt(lastMin * mDegree, 8) % 360;

            if (lastSecDegree != lastMinDegree) {
                double finalSecDegree = roundAt(lastSec * sDegree + mCycleRemainSec * sDegree, 8);
                double finalMinDegree = roundAt(lastMin * mDegree + (mCycleRemainSec / 60) * mDegree, 8) % 360;
                finalMinDegree += lastSecDegree > lastMinDegree ? 360 : 0;

                if (finalSecDegree >= finalMinDegree) alarmCnt++;
            }
        }

        return alarmCnt;
    }

    // 시간을 초 단위로 환산
    public double toSec(int hour, int min, int sec) {
        return 3600 * hour + 60 * min + sec;
    }

    // 소수점 자리 반올림
    public double roundAt(double num, int pos) {
        double digit = (int) Math.pow(10, pos);
        return Math.round(num * digit) / digit;
    }
}
