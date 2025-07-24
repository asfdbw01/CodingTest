/*  
 * 문제 요약
 *     - 숫자 문자열에서 k개의 수를 제거하여 만들 수 있는 가장 큰 수를 구하라
 *
 * 입력  
 *     - String number: 숫자로 이루어진 문자열 (길이 최대 1,000,000)
 *     - int k: 제거할 숫자의 개수
 *
 * 출력  
 *     - String: k개의 수를 제거한 후 얻을 수 있는 가장 큰 수
 *
 * 핵심 포인트  
 *     - 앞자리부터 순차적으로 탐색하며, 현재 숫자가 스택의 top보다 크면 이전 수를 제거
 *     - 제거 횟수(k)가 남아 있는 동안은 계속 제거
 *     - 탐색이 끝난 뒤에도 k가 남아 있다면 뒤에서부터 추가로 제거
 *     - StringBuilder를 스택처럼 활용하여 O(n) 시간 복잡도로 처리
 */

class Solution {
	
	public String solution(String number, int k) {
		StringBuilder sb = new StringBuilder();
		
		for (char n : number.toCharArray()) {
			// 현재 숫자가 이전 숫자보다 크면, 이전 숫자를 제거하면서 k 감소
			while (sb.length() > 0 && k > 0 && sb.charAt(sb.length() - 1) < n) {
				sb.setLength(sb.length() - 1);
				k--;
			}
			
			sb.append(n);  // 현재 숫자를 결과에 추가
		}
		
		// 제거 횟수가 남아 있다면 뒤에서 추가로 제거
		if (k > 0) sb.setLength(sb.length() - k);
		
		return sb.toString();  // 최종 문자열 반환
	}
	
}
