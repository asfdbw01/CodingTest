/*  
 * 문제 요약
 *     - 문자열에서 같은 문자가 연속 2번 등장하면 제거
 *     - 이 과정을 반복해서 문자열이 전부 사라지면 1, 아니면 0 반환
 *
 * 입력  
 *     - 문자열 s (길이 ≤ 1,000,000, 소문자만)
 *
 * 출력  
 *     - 성공적으로 제거 가능하면 1, 아니면 0
 *
 * 핵심 포인트  
 *     - 연속된 두 문자를 빠르게 비교하고 제거하는 자료구조 → Stack
 */

class Solution {
	
	public int solution(String s) {
		Deque<Character> stack = new ArrayDeque<>();
		
		for (char c : s.toCharArray()) {
			if (!stack.isEmpty() && stack.peek() == c) {
				stack.pop(); // 짝 제거
			} else {
				stack.push(c); // 스택에 추가
			}
		}
		
		return stack.isEmpty() ? 1 : 0;
	}
	
}
