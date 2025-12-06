/*  
 * 문제 요약
 *     - 주어진 문자열에서 가장 긴 팰린드롬 부분 문자열의 길이를 구함
 *
 * 입력  
 *     - String s : 길이 최대 2,500인 소문자 문자열
 *
 * 출력  
 *     - int : 가장 긴 팰린드롬 부분 문자열의 길이
 *
 * 핵심 포인트  
 *     - 모든 중심점을 기준으로 좌우 확장하여 팰린드롬 여부 확인
 *     - 홀수/짝수 길이 모두 검사
 *     - 시간복잡도 O(N^2)으로도 2500자 이내면 통과 가능
 */

class Solution {
	
	public int solution(String s) {
		int max = 0;
		
		for (int i = 0; i < s.length(); i++) {
			int len1 = 1;     // 홀수 길이 팰린드롬
			int len2 = 0;     // 짝수 길이 팰린드롬
			int left1 = i - 1, right1 = i + 1;
			int left2 = i, right2 = i + 1;
			
			boolean flag1 = true;
			boolean flag2 = true;
			
			// 좌우 확장을 통해 팰린드롬 길이 계산
			while (flag1 || flag2) {
				if (flag1 && 
					left1 >= 0 && 
					right1 < s.length() && 
					s.charAt(left1--) == s.charAt(right1++)) {
					len1 += 2;
				} else flag1 = false;
				
				if (flag2 && 
					left2 >= 0 && 
					right2 < s.length() && 
					s.charAt(left2--) == s.charAt(right2++)) {
					len2 += 2;
				} else flag2 = false;
			}
			max = Math.max(max, Math.max(len1, len2)); // 최대 길이 갱신
		}
		
		return max;
	}
	
}
