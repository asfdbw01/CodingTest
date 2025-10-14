/*  
 * 문제 요약  
 *     - 0부터 시작하여 n진법으로 표현된 숫자를 게임 참가자들이 한 자리씩 돌아가며 말함
 *     - 참가자 m명이 있고 튜브의 순서는 p번째일 때, 튜브가 말해야 하는 숫자 t개를 구한다
 *
 * 입력  
 *     - n: 진법 (2~16)
 *     - t: 튜브가 말해야 할 숫자의 개수
 *     - m: 게임에 참여하는 사람 수
 *     - p: 튜브의 순서 (1부터 시작)
 *
 * 출력  
 *     - 튜브가 말해야 할 숫자 t개를 문자열로 이어붙인 값 (10~15는 A~F)
 *
 * 핵심 포인트  
 *     - 숫자를 n진수로 계속 변환하여 한 자리씩 순회
 *     - 전체 발음 순서에서 튜브의 턴일 때만 문자를 추가
 *     - Integer.toString(num, n)으로 진법 변환, 대문자 처리만 따로 수행
 */

class Solution {
	
	public String solution(int n, int t, int m, int p) {
		StringBuilder sb = new StringBuilder();
		
		int num = 0, len = 0;
		
		// 말한 숫자의 길이가 t에 도달할 때까지 숫자 증가
		while (sb.length() < t) {
			// 현재 숫자를 n진수 문자열로 변환
			for (char c : Integer.toString(num, n).toCharArray()) {
				// 전체 순서 중 튜브의 차례인지 확인
				if (len++ % m == p - 1) {
					sb.append(Character.toUpperCase(c));
				}
				if (sb.length() == t) break;
			}
			
			num++;
		}
		
		return sb.toString();
	}
	
}
