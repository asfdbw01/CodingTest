/*  
 * 문제 요약
 *     - 문자열 s가 길이 4 또는 6이고, 숫자로만 이루어졌는지 확인
 *
 * 입력  
 *     - String s (길이 1 이상 8 이하, 영문자 및 숫자 혼합 가능)
 *
 * 출력  
 *     - 조건을 만족하면 true, 아니면 false
 *
 * 핵심 포인트  
 *     - 길이 조건을 먼저 확인 (4 또는 6)
 *     - 모든 문자가 숫자인지 확인: s.chars().allMatch(Character::isDigit)
 */

class Solution {
	
	public boolean solution(String s) {
		// 길이가 4 또는 6이고, 모든 문자가 숫자인 경우 true
		return (s.length() == 4 || s.length() == 6) && s.chars().allMatch(Character::isDigit);
	}
	
}
