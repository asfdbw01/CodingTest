/*  
 * 문제 요약
 *     - 문자열에 존재하는 모든 "110"을 제거한 후, 다시 임의의 위치에 삽입하여 사전순으로 가장 앞서는 문자열로 만드는 문제
 *
 * 입력  
 *     - 문자열 배열 s (각 문자열은 0과 1로만 이루어짐, 길이 합 ≤ 1,000,000)
 *
 * 출력  
 *     - 각 문자열에 대해 "110"을 적절히 재배치해 사전순으로 가장 앞서는 결과를 담은 문자열 배열
 *
 * 핵심 포인트  
 *     - "110"을 모두 제거한 뒤 삽입 위치를 찾는 방식이 핵심
 *     - 사전순으로 가장 앞서는 위치는 마지막 '0' 뒤이므로 lastIndexOf("0") + 1을 삽입 위치로 사용
 *     - StringBuilder를 사용하여 효율적인 삭제 및 삽입 처리
 */

class Solution {
	
	public String[] solution(String[] s) {
		String[] answer = new String[s.length];
		
		for (int i = 0; i < s.length; i++) {
			StringBuilder sb = new StringBuilder();
			int count = 0;
			
			// 문자열을 순회하며 "110" 패턴을 제거하고 카운트
			for (char c : s[i].toCharArray()) {
				sb.append(c);
				int len = sb.length();
				if (len >= 3 && 
					sb.charAt(len - 1) == '0' && 
					sb.charAt(len - 2) == '1' &&
					sb.charAt(len - 3) == '1') {
					sb.setLength(len - 3);  // "110" 제거
					count++;
				}
			}
			
			// 삽입 위치: 마지막 '0' 뒤 (없으면 맨 앞)
			int index = sb.lastIndexOf("0");
			sb.insert(index + 1, "110".repeat(count));
			
			answer[i] = sb.toString();
		}
		
		return answer;
	}
	
}
