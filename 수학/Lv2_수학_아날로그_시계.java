/*
//하다하다 안되서 그냥 gpt 씀
//해냈다 이 갈비지 문제 소수점 보정을 15자리 이런식으로 하지 말고 대충 10자리쯤에서 하세
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


import java.util.*;

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        // 디버깅용으로 테스트 19 입력값 강제로 세팅
        //h1 = 0; m1 = 0; s1 = 0;
       // h2 = 23; m2 = 0; s2 = 0;
        int answer = 0;
        TreeSet<Double> set = new TreeSet<>();
        makeBeepSet( set);
        answer = countBeep(h1,m1,s1, h2, m2, s2, set);
        //System.out.println(answer);
        return answer;
    }
    
    private void makeBeepSet(TreeSet<Double> set){
        final double periodMinute = Math.round((360.0 / (6.0 - 0.1)) * 1e10) / 1e10;
        final double periodHour = Math.round((360.0 / (6.0 - (1.0 / 120.0))) * 1e10) / 1e10;
        double m=0,h=0;

        double[] correctionPoints = {0, 21600, 43200, 64800, 86400};
        double correctionRange = 1.0;  // 1초 이내

        while(m <= 86400 || h <= 86400){
            // m 보정
            for(double point : correctionPoints){
                if(Math.abs(m - point) < correctionRange){
                    m = Math.round(m * 1e5) / 1e5;
                    break;
                }
            }
            set.add(m);

            // h 보정
            for(double point : correctionPoints){
                if(Math.abs(h - point) < correctionRange){
                    h = Math.round(h * 1e5) / 1e5;
                    break;
                }
            }
            set.add(h);

            m += periodMinute;
            h += periodHour;
        }
    }


    
    private int countBeep(int h1, int m1, int s1, int h2, int m2, int s2,TreeSet<Double> set){
        double startTime = h1*60*60+m1*60+s1;
        double endTime = h2*60*60+m2*60+s2;
        int result=0;
        while (!set.isEmpty() && set.first() <= endTime) {
            double nowTime = set.pollFirst();  // 가장 작은 원소 제거하며 반환
            if (nowTime >= startTime && nowTime <= endTime) {
                //System.out.println(nowTime);
                result++;
            }
        }

        return result;
    }
}
