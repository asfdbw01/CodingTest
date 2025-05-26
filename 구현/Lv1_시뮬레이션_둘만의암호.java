/*
 * 문제 요약
 *     - 문자열 s의 각 알파벳을 index만큼 뒤의 알파벳으로 바꾸되, skip 문자열에 포함된 알파벳은 건너뛴다.
 *     - 알파벳은 z를 넘어가면 a부터 다시 시작한다.
 *     - 변환된 문자들을 이어서 새로운 문자열을 반환한다.
 *
 * 입력
 *     - s: 변환 대상 문자열 (알파벳 소문자, 길이 5~50)
 *     - skip: 건너뛸 알파벳들로 이루어진 문자열 (길이 1~10, s에 포함되지 않음)
 *     - index: 이동할 칸 수 (1~20)
 *
 * 출력
 *     - 변환된 문자열 (String)
 *
 * 핵심 포인트
 *     - 알파벳 순환 구조 (z 다음은 a)
 *     - skip된 문자는 index 계산 시 제외
 *     - O(1)로 skip 체크할 수 있도록 boolean 배열 사용
 */

class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();
        int abcLen = 'z' - 'a' + 1;
        boolean[] isSkip = new boolean[abcLen]; // 알파벳 건너뛰기 여부 체크

        // skip 문자 체크 배열 구성
        for (char c : skip.toCharArray()) {
            isSkip[c - 'a'] = true;
        }

        // 각 문자 변환
        for (char c : s.toCharArray()) {
            int i = c - 'a', cnt = 0;

            // index만큼 유효한 문자만 건너뛰며 이동
            while (cnt != index) {
                i = (i + 1) % abcLen;
                if (!isSkip[i]) cnt++;
            }

            sb.append((char) ('a' + i));
        }

        return sb.toString();
    }
}
