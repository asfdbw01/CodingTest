/*  
 * 문제 요약
 *     - 문자열을 왼쪽으로 x칸 회전했을 때, 올바른 괄호 문자열이 되는 x의 개수를 구하는 문제
 *
 * 입력  
 *     - 문자열 s (길이 1 이상 1,000 이하): '(', ')', '{', '}', '[', ']'로만 구성됨  
 *
 * 출력  
 *     - 회전 결과 중 올바른 괄호 문자열이 되는 경우의 수 (0 이상 s.length() 이하)
 *
 * 핵심 포인트  
 *     - 회전은 substring으로 구현하는 것이 직관적이고 효율적
 *     - 스택을 사용해 괄호 유효성 검사를 수행
 *     - 시작이 닫는 괄호이거나, 끝이 여는 괄호인 경우는 프루닝 가능
 */

class Solution {
	
	public int solution(String s) {
		int count = 0;

		Outter : for (int i = 0; i < s.length(); i++) {
			// 문자열을 왼쪽으로 i칸 회전
			String rotated = s.substring(i) + s.substring(0, i);
			
			// 시작이 닫는 괄호이거나, 끝이 여는 괄호이면 절대 올바른 괄호가 될 수 없음
			char first = rotated.charAt(0);
			char last = rotated.charAt(rotated.length() - 1);
			if (first == ')' || first == '}' || first == ']' ||
			    last == '(' || last == '{' || last == '[') continue;

			Deque<Character> stack = new ArrayDeque<>();
			
			// 괄호 유효성 검사
			for (char c : rotated.toCharArray()) {
				switch (c) {
					case '(': stack.addLast(')'); break;
					case '[': stack.addLast(']'); break;
					case '{': stack.addLast('}'); break;
					default:
						if (stack.isEmpty() || c != stack.pollLast()) continue Outter;
				}
			}
			
			if (stack.isEmpty()) count++;  // 올바른 괄호 문자열
		}
		
		return count;
	}
}
