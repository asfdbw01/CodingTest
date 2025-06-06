/*
//하다하다 안되서 그냥 gpt 씀
 * 아날로그 시계의 초침, 분침, 시침이 일정 속도로 회전합니다.
 * 초침이 시침 또는 분침과 겹칠 때 알람이 울립니다.
 *
 * - 시침은 12시간(43200초)에 360도 회전
 * - 분침은 60분(3600초)에 360도 회전
 * - 초침은 60초에 360도 회전
 *
 * 주어진 시작 시각(h1,m1,s1)부터 끝 시각(h2,m2,s2)까지
 * 알람이 울린 횟수를 구하는 함수 구현.
 *
 * 단, 초침이 시침과 분침과 동시에 겹쳐도 알람은 한 번만 울립니다.
 * 시간은 0시 0분 0초 ~ 23시 59분 59초 사이이며, 시작 시각 < 끝 시각 보장.
*/


class Solution {
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int alarmCnt = 0;

        // 초침이 시침, 분침을 추월하는 주기 (초침이 시침과 겹치는 주기, 초침이 분침과 겹치는 주기)
        double hCycle = 360d * (120d / 719d); // 시침-초침 겹침 주기 (초 단위)
        double mCycle = 360d * (10d / 59d);   // 분침-초침 겹침 주기 (초 단위)

        // 시작 시각과 종료 시각을 초 단위로 변환 (소수점으로)
        double start = toSec(h1, m1, s1);
        double end = toSec(h2, m2, s2);
        double runtime = end - start;

        // 하루 중 정오 시각을 초로 표현 (12시 0분 0초)
        double noon = toSec(12, 0, 0);

        // 각각 겹침 주기에 따라 전체 경과시간(runtime) 내 몇 번의 겹침 사이클이 있는지 계산 (정수 개수)
        int hCycleCount = (int) (runtime / hCycle); // 시침-초침 겹침 횟수
        int mCycleCount = (int) (runtime / mCycle); // 분침-초침 겹침 횟수

        // runtime을 각 주기로 나눈 나머지 시간 (잔여 시간)
        double hCycleRemainSec = runtime % hCycle; // 시침-초침 잔여 시간
        double mCycleRemainSec = runtime % mCycle; // 분침-초침 잔여 시간

        // 각 바늘 1단위 당 회전 각도 (도 단위)
        int hDegree = 360 / 12;  // 시침은 1시간에 30도 회전
        int mDegree = 360 / 60;  // 분침은 1분에 6도 회전
        int sDegree = 360 / 60;  // 초침은 1초에 6도 회전

        // 시작 시각이 00분 00초일 때는 세 바늘이 모두 겹치므로 알람 1회 추가
        if (m1 == 0 && s1 == 0) alarmCnt++;

        // 전체 사이클 수만큼 알람 횟수 더하기 (완전한 주기 내의 겹침 횟수)
        alarmCnt += hCycleCount + mCycleCount;

        // 시간 구간에 12:00:00 (정오) 시각이 포함되면 중복된 알람 1회 제거
        if (start < noon && noon < end) alarmCnt--;

        // 종료 시각이 정오일 경우 알람 1회 추가 (경계 포함 처리)
        if (end == noon) alarmCnt++;

        else {
            // 잔여 시간 구간에서 시침-초침 겹침 여부 체크
            if (hCycleRemainSec != 0) {
                // 잔여 시간 이전 시점의 초, 시간 각도 계산 (소수점 8자리 반올림)
                double lastSec = (s1 + (hCycle * hCycleCount)) % 60;
                double lastHour = ((h1 * 3600) + (m1 * 60) + s1 + (hCycle * hCycleCount)) / 3600 % 12;

                double lastSecDegree = roundAt(lastSec * sDegree, 8) % 360;
                double lastHourDegree = roundAt(lastHour * hDegree, 8) % 360;

                // 현재 시점에서 초침이 시침을 추월하는지 판단
                if (lastSecDegree != lastHourDegree) {
                    double finalSecDegree = roundAt(lastSec * sDegree + hCycleRemainSec * sDegree, 8);
                    double finalHourDegree = roundAt(lastHour * hDegree + (hCycleRemainSec / 3600) * hDegree, 8) % 360;

                    // 초침이 시침보다 뒤에 있으면 기준을 맞추기 위해 360도 더해줌 (회전 각도 특성상)
                    finalHourDegree += lastSecDegree > lastHourDegree ? 360 : 0;

                    // 잔여 시간 내에 초침이 시침을 추월했으면 알람 1회 추가
                    if (finalSecDegree >= finalHourDegree) alarmCnt++;
                }
            }

            // 잔여 시간 구간에서 분침-초침 겹침 여부 체크 (시침-초침과 같은 로직)
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
        }

        return alarmCnt;
    }

    // 시, 분, 초를 초 단위로 변환하는 함수
    public double toSec(int hour, int min, int sec) {
        return 3600 * hour + 60 * min + sec;
    }

    // 부동소수점 오차를 방지하기 위해 소수점 자리수를 제한해 반올림하는 함수
    public double roundAt(double num, int pos) {
        double digit = Math.pow(10, pos);
        return Math.round(num * digit) / digit;
    }
}
