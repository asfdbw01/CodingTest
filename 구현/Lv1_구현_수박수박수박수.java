/*  
 * 문제 요약
 *     - 길이가 n인 문자열을 "수박수박..." 패턴으로 구성하여 반환  
 *     - n이 짝수면 "수박" 반복, n이 홀수면 마지막에 "수"로 끝남  
 *
 * 입력  
 *     - 정수 n (1 이상 10,000 이하)  
 *
 * 출력  
 *     - "수박수박..." 패턴으로 구성된 길이 n짜리 문자열  
 *
 * 핵심 포인트  
 *     - "수박" 패턴을 반복하거나, 인덱스를 기준으로 '수' 또는 '박'을 선택  
 *     - repeat + substring 방식은 간결, Stream 방식은 유연하고 확장성 높음  
 */

class Solution {

	public String solution(int n) {
		// 방법 1: repeat + substring 사용 (간결하고 직관적)
		// return "수박".repeat((int) Math.ceil((double) n / 2)).substring(0, n);

		// 방법 2: IntStream 사용 (패턴 확장이나 다른 조건에도 유연)
		return IntStream.range(0, n)
						.mapToObj(i -> (i % 2 == 0) ? "수" : "박") // 짝수: 수, 홀수: 박
						.collect(Collectors.joining());          // 문자열 합치기
	}
	
}
