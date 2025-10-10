/*  
 * 문제 요약
 *     - 절댓값 배열과 부호 배열이 주어질 때, 실제 정수들의 합을 구하는 문제
 *
 * 입력  
 *     - int[] absolutes: 정수들의 절댓값 배열 (길이 1 이상 1,000 이하)
 *     - boolean[] signs: 각 정수의 부호를 나타내는 배열 (true면 양수, false면 음수)
 *
 * 출력  
 *     - 실제 정수들의 총합
 *
 * 핵심 포인트  
 *     - signs 배열을 통해 실제 부호를 적용
 *     - 스트림을 이용해 index 기반으로 처리하면 간결하게 구현 가능
 */

class Solution {
	
	public int solution(int[] absolutes, boolean[] signs) {
		// 인덱스를 순회하며 부호를 적용한 값을 합산
		return IntStream.range(0, absolutes.length)
				.map(i -> signs[i] ? absolutes[i] : -absolutes[i])
				.sum();
	}
	
}
