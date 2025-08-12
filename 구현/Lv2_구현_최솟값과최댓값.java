/*  
 * 문제 요약
 *     - 문자열 s에 포함된 정수들 중 최솟값과 최댓값을 찾아 "(최소값) (최댓값)" 문자열로 반환
 *
 * 입력  
 *     - String s : 공백으로 구분된 정수 문자열
 *
 * 출력  
 *     - String : "(최솟값) (최댓값)" 형식의 문자열
 *
 * 핵심 포인트  
 *     - 문자열을 split하여 정수 배열로 변환 후 정렬
 *     - 정렬된 배열의 양 끝에서 min/max 추출
 */

class Solution {
	
	public String solution(String s) {
		int[] sortedArr = Arrays.stream(s.split(" "))
							    .mapToInt(Integer::parseInt)
							    .sorted()
							    .toArray();
		
		// 첫 요소는 최솟값, 마지막 요소는 최댓값
		return sortedArr[0] + " " + sortedArr[sortedArr.length - 1];
	}
	
}
