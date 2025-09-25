/*  
 * 문제 요약
 *     - 문자열 배열 seoul에서 "Kim"이 있는 인덱스를 찾아 
 *       "김서방은 x에 있다" 형태로 반환
 *
 * 입력  
 *     - String[] seoul: 길이 1 이상 1000 이하, 각 원소는 길이 1 이상 20 이하  
 *
 * 출력  
 *     - "김서방은 x에 있다" 형식의 문자열  
 *
 * 핵심 포인트  
 *     - "Kim"은 반드시 1번만 등장하므로, 최초 인덱스를 찾는 방식으로 처리
 *     - IntStream.range().filter().findFirst()로 인덱스를 탐색
 */

class Solution {
	
	public String solution(String[] seoul) {
		// 0부터 seoul.length - 1까지 인덱스를 순회
		// seoul[i]가 "Kim"과 일치하는 첫 인덱스를 찾음
		// 찾은 인덱스를 "김서방은 x에 있다" 형식으로 변환
		return "김서방은 " + 
				IntStream.range(0, seoul.length)
						 .filter(i -> seoul[i].equals("Kim")) // "Kim"의 위치 필터
						 .findFirst()                         // 첫 번째 인덱스 반환
						 .getAsInt() +                        // int로 변환
				"에 있다";
	}
	
}
