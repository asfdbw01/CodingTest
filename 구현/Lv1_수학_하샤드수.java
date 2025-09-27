/*  
 * 문제 요약
 *     - 양의 정수 x가 자릿수의 합으로 나누어지면 하샤드 수로 판단
 *
 * 입력  
 *     - 정수 x (1 ≤ x ≤ 10,000)
 *
 * 출력  
 *     - boolean: x가 하샤드 수이면 true, 아니면 false
 *
 * 핵심 포인트  
 *     - 자릿수를 추출하여 합을 구하고, 원래 수 x가 그 합으로 나누어지는지 확인
 *     - 문자열 변환 + chars() + map(i -> i - '0')으로 자릿수 쉽게 추출 가능
 *     - Java 8+ Stream API를 이용한 간결한 구현
 */

class Solution {
    
    public boolean solution(int x) {
    	// 1. x를 문자열로 변환 → 각 자릿수를 문자로 나열
        // 2. 각 문자를 정수로 변환 (문자 - '0')
        // 3. 자릿수 합을 모두 더함
        // 4. 원래 수 x가 자릿수 합으로 나누어지는지 확인
        return x % String.valueOf(x).chars().map(i -> i - '0').sum() == 0;
    }
}
