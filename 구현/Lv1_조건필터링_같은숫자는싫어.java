/*  
 * 문제 요약
 *     - 배열에서 연속으로 나타나는 같은 숫자는 하나만 남기고 제거한 후, 원래 순서를 유지하여 반환
 *
 * 입력  
 *     - int[] arr : 0부터 9까지의 숫자로 이루어진 배열 (길이 최대 1,000,000)
 *
 * 출력  
 *     - int[] : 연속 중복을 제거한 배열
 *
 * 핵심 포인트  
 *     - 첫 원소는 항상 포함
 *     - 현재 값이 이전 값과 다를 때만 결과에 포함
 *     - 시간복잡도 O(N)으로 처리
 */

class Solution {
	
	public int[] solution(int[] arr) {
		return IntStream.range(0, arr.length)
						.filter(i -> i == 0 || arr[i] != arr[i - 1]) // 첫 원소 또는 이전 값과 다를 때만 통과
						.map(i -> arr[i])                            // 해당 인덱스의 값 추출
						.toArray();                                  // 배열로 변환
	}
	
}
