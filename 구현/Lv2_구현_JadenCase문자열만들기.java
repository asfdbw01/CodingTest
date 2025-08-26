/*  
 * 문제 요약
 *     - 주어진 문자열 s를 JadenCase로 변환
 *     - JadenCase: 각 단어의 첫 글자는 대문자, 나머지는 소문자
 *     - 단어는 공백으로 구분되며, 공백이 연속될 수 있음
 *
 * 입력  
 *     - 문자열 s (1 ≤ s.length ≤ 200)  
 *     - 알파벳, 숫자, 공백으로 구성됨
 *
 * 출력  
 *     - JadenCase로 변환된 문자열
 *
 * 핵심 포인트  
 *     - 연속된 공백을 포함한 원본 문자열의 구조를 그대로 유지
 *     - 첫 문자가 숫자인 경우 그대로 두고, 알파벳일 경우 대문자로 변환
 *     - StringBuilder로 효율적인 문자 수정
 */

class Solution {
    
    public String solution(String s) {
        // 전체 문자열을 소문자로 변환하여 기본 상태로 만듦
        StringBuilder sb = new StringBuilder(s.toLowerCase());
        
        boolean isFirst = true; // 단어의 첫 문자 여부 체크
        for (int i = 0; i < s.length(); i++) {
            char c = sb.charAt(i);

            if (c == ' ') {
                // 공백이 나오면 다음 문자는 단어의 첫 글자가 됨
                isFirst = true;
            } else if (isFirst) {
                // 단어의 첫 글자일 경우 대문자로 변환
                sb.setCharAt(i, Character.toUpperCase(c));
                isFirst = false;
            }
            // 단어의 첫 글자가 아니면 소문자 유지 (이미 처리됨)
        }
        
        // 변환된 문자열 반환
        return sb.toString();
    }
}
