/*
 * 문제 요약
 *     - 숫자로 이루어진 문자열 t에서 p와 길이가 같은 부분문자열 중,
 *       그 수가 p가 나타내는 수보다 작거나 같은 것의 개수를 구하는 문제
 *
 * 입력
 *     - t: 전체 숫자 문자열 (1 ≤ t.length() ≤ 10,000)
 *     - p: 비교 기준 숫자 문자열 (1 ≤ p.length() ≤ 18)
 *
 * 출력
 *     - 조건을 만족하는 부분 문자열의 개수 (정수)
 *
 * 핵심 포인트
 *     - 부분 문자열과 p의 길이는 항상 같음
 *     - 0으로 시작하지 않으므로 사전식 비교 == 정수 비교
 *     - BigInteger 없이 직접 문자 비교로 해결 가능
 *     - substring 없이 charAt()만으로 비교 → 불필요한 메모리 사용 최소화
 */

class Solution {

	public int solution(String t, String p) {
		int cnt = 0;
		
		int tLen = t.length();
		int pLen = p.length();
		
		for (int i = 0; i <= tLen - pLen; i++) {
			int index = 0;
			
			// 앞에서부터 각 자리 숫자가 다를 때까지 반복 (사전식 비교 == 정수 비교)
			while (index < pLen && t.charAt(i + index) == p.charAt(index)) index++;
			
			// 모두 같았다면 마지막 자리 비교를 위해 index 줄임
			if (index == pLen) index--;
			
			// 다르다면 해당 자리에서 비교
			if (t.charAt(i + index) <= p.charAt(index)) cnt++;
		}
		
		return cnt;
	}
}
