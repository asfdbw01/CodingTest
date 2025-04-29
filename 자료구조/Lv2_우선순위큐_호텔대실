// 문제: 호텔 대실
// 최소한의 객실 수로 예약 손님을 받는 문제 (퇴실 후 10분 청소시간 고려)
// 입력: 예약시간 book_time[i] = ["HH:MM", "HH:MM"]
// 출력: 최소 객실 수 (int)

// 방법:
// 1. 예약을 시작 시간 기준으로 정렬
// 2. 최소 힙(PriorityQueue)로 가장 빨리 비는 방 관리
// 3. 시작 시간이 힙 최소값 이상이면 방 재사용, 아니면 새 방 추가
// 4. 마지막 힙 크기가 필요한 방 수

import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[][] intTime = new int[book_time.length][2];
        for(int i=0;i<book_time.length;i++){
            String[] start = book_time[i][0].split(":");
            String[] end = book_time[i][1].split(":");
            intTime[i][0] = Integer.parseInt(start[0])*60+Integer.parseInt(start[1]);
            intTime[i][1] = Integer.parseInt(end[0])*60+Integer.parseInt(end[1])+10;
        }
        
        
        Arrays.sort(intTime, (a, b) -> a[0] - b[0]);
        
        PriorityQueue<Integer> roomNum = new PriorityQueue<>();
        
        roomNum.add(intTime[0][1]);
        for(int i=1;i<intTime.length;i++){
            if(intTime[i][0] >= roomNum.peek())roomNum.poll();
            roomNum.add(intTime[i][1]);
        }
        
        answer = roomNum.size();
        
        return answer;
    }
}
