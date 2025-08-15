/*  
 * 문제 요약  
 *     - 문자열로 표현된 중첩 집합을 분석하여 원래의 튜플을 찾아내는 문제  
 *     - 집합들은 원소가 하나씩 추가되는 형태로 구성되며, 순서가 있는 튜플을 의미함  
 *
 * 입력  
 *     - s: 중첩된 집합을 문자열로 표현한 값 (예: "{{2},{2,1},{2,1,3},{2,1,3,4}}")  
 *
 * 출력  
 *     - 튜플을 구성하는 정수 배열  
 *
 * 핵심 포인트  
 *     - 중첩된 집합들을 문자열 파싱으로 분리  
 *     - 원소 개수가 적은 집합부터 차례로 정렬  
 *     - flatMap을 통해 문자들을 모두 펼친 후, 순서대로 중복 제거
 */

class Solution {
	
	public int[] solution(String s) {
		return Arrays.stream(s.substring(2, s.length() - 2).split("},\\{"))	// 양 끝 괄호 제거 후 각 집합 분리
					 .sorted(Comparator.comparingInt(String::length))		// 집합의 길이순으로 정렬 (원소 개수 순)
					 .flatMap(str -> Arrays.stream(str.split(",")))        	// 각 집합 내부를 "," 기준으로 나눔
					 .mapToInt(Integer::parseInt)                           // 문자열을 정수로 변환
					 .distinct()                                            // 튜플의 순서를 유지하며 중복 제거
					 .toArray();                                            // 배열로 반환
	}
	
}
