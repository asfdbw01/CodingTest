/*
 * 문제 요약
 * 		- players[i]: i시 ~ i+1시 사이 게임 이용자 수
 * 		- 서버 1대는 최대 m명 수용, 추가된 서버는 k시간 동안 유지
 * 		- 매 시각 필요한 서버 수 = ceil(players[i] / m)
 * 		- 운영 중인 서버가 부족하면 부족한 수만큼 추가
 * 		- 총 추가된 서버 수의 합을 구하라
 *
 * 입력 조건
 * 		- players.length = 24
 * 		- 0 ≤ players[i] ≤ 1,000
 * 		- 1 ≤ m ≤ 1,000
 * 		- 1 ≤ k ≤ 24
 *
 * 출력
 * 		- 총 추가된 서버 수 (int)
 *
 * 핵심 포인트
 * 		- 슬라이딩 윈도우 방식: k시간 동안 유지되는 서버를 큐로 추적
 * 		- 현재 운영 중인 서버 수를 매 시간 관리하며 그리디하게 필요한 만큼만 추가
 */

class Solution {
    public int solution(int[] players, int m, int k) {

        int answer = 0; // 최종 결과값: 총 추가된 서버 수
        int nowServer = 0; // 현재 운영 중인 서버 수
        Queue<Integer> addServers = new LinkedList<>(); // 각 시각에 추가한 서버 수 (k시간 지나면 제거)

        for (int i = 0; i < players.length; i++) {
            // k시간이 지나 만료되는 서버 제거
            if (i >= k) {
                nowServer -= addServers.remove(); // 큐에서 제거한 수만큼 현재 서버 수 감소
            }

            // 현재 시각에 필요한 서버 수 계산
            int required = (int) Math.ceil(players[i] / (double) m);
            int need = Math.max(required - nowServer, 0); // 부족한 수만큼만 추가

            // 서버 추가 및 상태 갱신
            addServers.offer(need); // 새로 추가한 서버 수 큐에 저장
            nowServer += need;
            answer += need;
        }

        return answer;
    }
}
