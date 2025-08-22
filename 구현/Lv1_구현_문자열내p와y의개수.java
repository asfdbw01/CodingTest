/*  
 * 문제 요약
 *     - 주어진 문자열에서 'p'와 'y'의 개수를 비교하여 같으면 true, 다르면 false 반환
 *     - 대소문자는 구분하지 않음 ('P'와 'p'는 동일하게 처리)
 *     - 둘 다 없으면 true 반환
 *
 * 입력  
 *     - String s (길이 1~50, 알파벳 대소문자)
 *
 * 출력  
 *     - boolean (p와 y의 개수가 같으면 true, 다르면 false)
 *
 * 핵심 포인트  
 *     - 대소문자 구분 없이 비교해야 하므로 toLowerCase()로 통일
 *     - 각 문자의 개수를 개별적으로 filter + count
 */

class Solution {
	
	boolean solution(String s) {
		// 모든 문자를 소문자로 변환 후
		// 각각 p, y에 해당하는 문자의 개수를 세고 비교
		return s.toLowerCase()
				.chars()
				.filter(c -> c == 'p')
				.count()
				==
			   s.toLowerCase()
			    .chars()
			    .filter(c -> c == 'y')
			    .count();
	}
	
}
