/*  
 * 문제 요약
 *     - 주어진 전화번호 문자열에서 뒷 4자리를 제외한 나머지를 '*'로 가려 반환
 *
 * 입력  
 *     - 문자열 phone_number (길이 4 이상 20 이하, 숫자로만 구성)
 *
 * 출력  
 *     - 문자열: 뒷 4자리는 그대로, 앞은 '*'로 마스킹된 문자열
 *
 * 핵심 포인트  
 *     - 전체 길이에서 마지막 4자리를 제외한 길이만큼 '*' 생성
 *     - substring을 이용해 뒷 4자리를 그대로 붙임
 *     - String.repeat() 기능을 활용하여 코드 간결화
 */

class Solution {
    
    public String solution(String phone_number) {
        // 앞의 길이만큼 '*' 반복 + 마지막 4자리 붙이기
        return "*".repeat(phone_number.length() - 4) 
             + phone_number.substring(phone_number.length() - 4);
    }
}
