/**
 * 문제 요약 - 셔틀버스
 *
 * ▪ 셔틀은 09:00부터 n회, t분 간격으로 도착함
 * ▪ 한 셔틀에는 최대 m명이 탑승 가능
 * ▪ 크루는 timetable[]에 주어진 시각에 도착함 (HH:MM 형식)
 * ▪ 같은 시각 도착 시, 크루는 먼저 도착한 순으로 탑승 / '콘'은 가장 마지막에 탐
 * ▪ 셔틀이 도착한 시각까지 대기 중인 크루는 셔틀에 탑승 가능 (동시 도착 포함)
 * ▪ 목표: 콘이 셔틀을 타고 사무실로 갈 수 있는 **가장 늦은 시각**을 구하라
 *
 * 조건
 * ▪ 콘은 타야 하며, 가장 늦은 시각을 원함
 * ▪ m명이 다 찼다면 가장 마지막 크루보다 1분 빨리 도착해야 함
 *
 * 입력
 * ▪ n (셔틀 횟수), t (간격), m (최대 인원)
 * ▪ timetable[]: 크루들의 도착 시각 (최대 2000명)
 *
 * 출력
 * ▪ HH:MM 형식의 콘이 도착할 수 있는 가장 늦은 시각
 *
 * 예시
 * n=2, t=10, m=2, timetable=["09:10", "09:09", "08:00"]
 * → 콘의 도착 시각: "09:09"
 */
import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        //n회 t분 m명 탑승 가능 09:00 출발
        int[] arrival =sortTimeTable(timetable);
        answer = lastBus( n,  t, m,arrival);
        return answer;
    }
    
    private String lastBus(int n, int t, int m, int[] arrival) {
        Queue<Integer> crew = new LinkedList<>();
        for (int time : arrival) {
            crew.offer(time);
        }

        int shuttleTime = 540;
        int answerTime = 0;

        for (int i = 0; i < n; i++) {
            int count = 0;
            int lastBoarded = 0;

            while (!crew.isEmpty() && crew.peek() <= shuttleTime && count < m) {
                lastBoarded = crew.poll();
                count++;
            }

            if (i == n - 1) {
                if (count < m) {
                    answerTime = shuttleTime;
                } else {
                    answerTime = lastBoarded - 1;
                }
            }

            shuttleTime += t;
        }

        return toHHMM(answerTime);
    }

    
    private int toMinutes(String time){
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour*60+minute;
    }
    
    private String toHHMM(int minutes){
        int hour = minutes/60;
        int minute = minutes%60;
        return String.format("%02d:%02d",hour,minute);
    }
    
    
    private int[] sortTimeTable(String[] timetable){
        int[] arrival = Arrays.stream(timetable)
            .mapToInt(this::toMinutes)
            .sorted()
            .toArray();
        return arrival;
    }
}
