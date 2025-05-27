/*
 * 문제 요약
 *     - 호텔 예약 시간표가 주어질 때, 청소 시간 10분을 고려하여 최소 몇 개의 객실이 필요한지 구하라.
 * 
 * 입력
 *     - book_time: 예약 시간 ["HH:MM", "HH:MM"] 형태의 문자열 배열 (1 ≤ 길이 ≤ 1000)
 *     - 각 원소는 [입실 시각, 퇴실 시각]이며 24시간 형식이고 자정을 넘지 않음
 * 
 * 출력
 *     - 최소 객실 수 (int)
 * 
 * 핵심 포인트
 *     - 객실은 퇴실 시각 + 10분 후에야 다음 손님 배정 가능
 *     - 시작 시각 기준으로 정렬하여 순차적으로 처리
 *     - 가장 빨리 비는 객실을 우선 확인 → 우선순위 큐 활용
 */

class Solution {
    
    public int solution(String[][] book_time) {
        // 예약 시작 시간 기준 정렬
        Arrays.sort(book_time, (o1, o2) -> timeToMin(o1[0]) - timeToMin(o2[0]));
        
        // 객실 종료 시간(청소 포함)을 관리하는 최소 힙
        PriorityQueue<Integer> endTimeQue = new PriorityQueue<>();
        
        for (String[] time : book_time) {
            int start = timeToMin(time[0]);
            int end = timeToMin(time[1]) + 10;  // 퇴실 후 10분 청소 포함
            
            // 가장 빨리 끝나는 방이 새 예약 시작 전에 끝났다면 재사용
            if (!endTimeQue.isEmpty() && endTimeQue.peek() <= start) {
                endTimeQue.poll();
            }
            
            // 새 방을 할당 (혹은 재사용한 방의 종료 시간 갱신)
            endTimeQue.add(end);
        }
        
        // 큐에 남은 방의 수가 최소 필요한 객실 수
        return endTimeQue.size();
    }
    
    // "HH:MM"을 분 단위로 변환
    public int timeToMin(String time) {
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int min = Integer.parseInt(times[1]);
        return hour * 60 + min;
    }
}
