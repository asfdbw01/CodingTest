/*  
 * 문제 요약
 *     - 정수 배열 arr의 평균값을 계산하여 반환하는 문제
 *
 * 입력  
 *     - int[] arr : 길이 1 이상 100 이하의 정수 배열 (원소는 -10,000 이상 10,000 이하)
 *
 * 출력  
 *     - double : 배열 원소의 평균값
 *
 * 핵심 포인트  
 *     - 배열의 평균을 간결하게 구하기 위해 Stream API 사용
 *     - OptionalDouble에서 값 꺼낼 때 getAsDouble 사용 가능 (arr.length ≥ 1 보장됨)
 */

class Solution {
	
	public double solution(int[] arr) {
		// 배열의 평균을 Stream API로 구하여 반환
		return Arrays.stream(arr).average().getAsDouble();
	}
	
}
