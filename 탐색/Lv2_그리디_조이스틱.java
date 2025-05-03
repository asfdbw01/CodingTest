/*
 * 문제 요약
 *      "AAAA..."로 시작하는 문자열을 조이스틱으로 조작하여 원하는 name 문자열을 만들자.
 *      각 문자 위치에서 알파벳을 ▲(위), ▼(아래)로 바꾸고
 *      커서를 ◀(왼쪽), ▶(오른쪽)으로 움직여 모든 문자를 변경해야 한다.
 * 
 * 입력:
 *      name: 만들고자 하는 알파벳 대문자 문자열 (1 ≤ name.length() ≤ 20)
 * 
 * 출력:
 *      모든 문자를 변경하기 위한 최소 조작 횟수 (알파벳 변경 + 커서 이동)
 */

/*
 * 핵심 포인트
 *      1. 알파벳 변경은 위/아래 방향 중 더 짧은 방향으로 조작 (Greedy)
 *         - 'A' → 'B': 1번(▲)
 *         - 'A' → 'Z': 1번(▼)
 *         		→ Math.min(c - 'A', 'Z' - c + 1)
 *
 *      2. 커서 이동은 좌/우 중 하나를 기준으로 한 번만 꺾어서 가장 긴 A 구간을 우회할 수 있다면 최소 이동
 *         - 모든 위치에서 꺾는 경우를 완전탐색하여 가장 짧은 커서 이동 거리 계산
 *         - 오른쪽 먼저 이동 후 왼쪽 꺾기: idx + idx + (len - A구간 끝)
 *         - 왼쪽 먼저 이동 후 오른쪽 꺾기: (len - A구간 끝) * 2 + idx
 *
 *      3. 조작 횟수 = 알파벳 조작 횟수 + 커서 이동 최소 횟수
 */

/*
 * 스스로에게 주는 제약조건
 *      1. 모든 A 구간을 완전탐색하되 불필요한 반복은 줄인다
 *      2. 상태 변경 없이 수학적으로 경로를 계산 (시뮬레이션 회피)
 */

class Solution {
    public int solution(String name) {
        int strLen = name.length();               // 전체 문자열 길이
        int minMove = strLen - 1;                 // 커서 이동의 최대치는 직진 (오른쪽으로 쭉)
        int abcMove = 0;                          // 알파벳 위/아래 이동 총합

        // 모든 위치에서 A 덩어리를 기준으로 커서 꺾기 전략을 완전탐색
        for (int idx = 0; idx < strLen; idx++) {
            char nowChar = name.charAt(idx);      // 현재 문자

            // 알파벳 변경: 'A'는 변경할 필요 없으므로 생략
            if (nowChar != 'A') {
                int up = nowChar - 'A';           // 위로 이동 시 조작 수
                int down = 'Z' - nowChar + 1;     // 아래로 이동 시 조작 수
                abcMove += Math.min(up, down);    // 더 짧은 방향으로 이동
            }

            // 현재 위치 다음부터 연속된 A의 끝까지 탐색
            int aLen = idx + 1;
            while (aLen < strLen && name.charAt(aLen) == 'A') aLen++;

            int next = strLen - aLen;             // A 덩어리 이후 남은 거리

            // 커서 꺾기 전략 2가지: 왼→오, 오→왼 방향 각각 비교
            // 예) idx + idx + next: 왼쪽 다 처리하고 오른쪽 끝으로 꺾기
            //     next + next + idx: 오른쪽 다 처리하고 왼쪽 꺾기
            int move = Math.min(idx + idx + next, next + next + idx);

            // 지금까지의 최소 커서 이동 거리 업데이트
            minMove = Math.min(move, minMove);
        }

        // 최종 조작 수 = 알파벳 조작 수 + 커서 이동 수
        return abcMove + minMove;
    }
}
