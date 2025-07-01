/*  
 * 문제 요약  
 *     - 주어진 이진 문자열에서 모든 0을 제거한 뒤, 남은 길이를 이진수로 바꾸는 작업을 반복  
 *     - 문자열이 "1"이 될 때까지 반복하고, 변환 횟수와 제거된 0의 총 개수를 반환  
 *
 * 입력  
 *     - s: 길이 1 이상 150,000 이하의 0과 1로 이루어진 문자열 (1이 최소 1개 이상 포함됨)  
 *
 * 출력  
 *     - [이진 변환 횟수, 제거된 0의 총 개수]  
 *
 * 핵심 포인트  
 *     - 문자열 내 '0'의 개수를 Stream으로 효율적으로 계산  
 *     - replace 없이 원본을 유지하며 메모리 낭비 없이 처리  
 *     - 문자열 길이를 줄여가므로 반복 횟수는 log(s.length()) 이하   
 */

class Solution {
	
	public int[] solution(String s) {
		String binary = s;
		int count = 0, zeroCount = 0;
		
		// 문자열이 "1"이 될 때까지 반복
		while (binary.length() > 1) {
			int len = binary.length();
			
			// 현재 문자열에서 '0'의 개수 세기
			int zero = (int) binary.chars().filter(i -> i == '0').count();
			
			// 제거된 0의 개수 누적
			zeroCount += zero;
			
			// 변환 횟수 증가
			count++;
			
			// 0 제거 후 남은 1의 개수를 이진 문자열로 변환
			binary = Integer.toBinaryString(len - zero);
		}
		
		// 변환 횟수와 제거된 0의 개수를 배열로 반환
		return new int[] {count, zeroCount};
	}
	
}
