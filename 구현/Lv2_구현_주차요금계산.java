/*
문제 요약 - 주차 요금 계산

[입력]
- fees: [기본 시간, 기본 요금, 단위 시간, 단위 요금]
- records: "HH:MM 차량번호 IN/OUT" 형식의 문자열 배열

[요구사항]
1. 입/출차 기록 기반으로 차량별 누적 주차 시간 계산
2. 출차 기록이 없으면 23:59 출차로 간주
3. 누적 주차 시간이 기본 시간 이하 → 기본 요금만
4. 초과 시 → 기본 요금 + 올림((초과시간 / 단위시간)) * 단위요금
5. 차량 번호 오름차순으로 결과 배열 생성

[주의사항]
- 같은 차량의 IN/OUT은 짝이 맞음
- 출차 없이 하루 끝난 경우 반드시 23:59 처리
- records는 시각 기준 정렬되어 있음

[예시]
- 입차: "05:34 5961 IN" → 입차 시간 저장
- 출차: "07:59 5961 OUT" → 누적시간 += (07:59 - 05:34)
- 누적 시간 계산 후 요금 산정하여 정렬된 순서대로 리턴

[출력]
- 차량번호 오름차순으로 주차 요금 배열 반환
*/

import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        TreeMap<Integer, Integer> totalTime = new TreeMap<>();
        Map<Integer, Integer> inTime = new HashMap<>();
        final int lastTime = 23 * 60 + 59;

        for (String record : records) {
            String[] parts = record.split(" ");
            int time = toMinutes(parts[0]);
            int carNum = Integer.parseInt(parts[1]);
            String action = parts[2];

            if (action.equals("IN")) {
                inTime.put(carNum, time);
            } else {
                int in = inTime.remove(carNum);
                totalTime.put(carNum, totalTime.getOrDefault(carNum, 0) + (time - in));
            }
        }

        // 출차 기록 없는 차량은 23:59 처리
        for (int carNum : inTime.keySet()) {
            int in = inTime.get(carNum);
            totalTime.put(carNum, totalTime.getOrDefault(carNum, 0) + (lastTime - in));
        }

        int[] answer = new int[totalTime.size()];
        int i = 0;
        for (int car : totalTime.keySet()) {
            answer[i++] = calculateFee(fees, totalTime.get(car));
        }
        return answer;
    }

    private int toMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    private int calculateFee(int[] fees, int time) {
        if (time <= fees[0]) return fees[1];
        return fees[1] + (int)Math.ceil((double)(time - fees[0]) / fees[2]) * fees[3];
    }
}
