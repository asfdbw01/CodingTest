/*  
 * 문제 요약
 *     - 0부터 9까지의 숫자 중 일부만 들어 있는 배열이 주어질 때, 배열에 없는 숫자들의 합을 구하는 문제
 *
 * 입력  
 *     - int[] numbers: 0~9까지 중 일부가 포함된 정수 배열 (길이 1~9, 중복 없음)
 *
 * 출력  
 *     - numbers에 없는 숫자들의 총합 (int)
 *
 * 핵심 포인트  
 *     - 0부터 9까지의 총합은 항상 45이므로, 전체 합에서 주어진 배열의 합을 빼는 방식이 가장 간단하고 빠름
 *     - 또는, 0~9를 순회하며 numbers에 없는 숫자만 필터링하여 직접 합산할 수도 있음
 *
 * 알고리즘  
 *     - [구현] 없는 숫자 더하기 - 전체합 차감 방식 / 조건 필터링 방식  
 *     - Lv1_구현_없는숫자더하기.java   
 */

class Solution {
	
	public int solution(int[] numbers) {
		// 방법 1: 0~9까지의 총합 45에서 주어진 숫자들의 합을 빼서 없는 숫자들의 합 계산
		return IntStream.rangeClosed(0, 9).sum() - Arrays.stream(numbers).sum();
//		return IntStream.rangeClosed(0, 9).map(i -> i - (i < numbers.length ? numbers[i] : 0)).sum();
		
		// 방법 2: 0~9 중 numbers에 포함되지 않은 숫자만 필터링하여 합산
		// return IntStream.rangeClosed(0, 9)
		//     .filter(n -> Arrays.stream(numbers).noneMatch(number -> number == n))
		//     .sum();
	}
}
