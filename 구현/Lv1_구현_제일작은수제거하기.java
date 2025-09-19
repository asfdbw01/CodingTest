/*  
 * 문제 요약
 *     - 정수 배열에서 가장 작은 수를 제거한 새 배열을 반환  
 *
 * 입력  
 *     - 정수 배열 arr (1 이상 길이, 모든 값은 서로 다름)  
 *
 * 출력  
 *     - 가장 작은 수를 제거한 배열  
 *     - 단, 제거 후 배열이 비면 [-1] 반환  
 *
 * 핵심 포인트  
 *     - 최소값을 찾은 후 filter로 제거  
 *     - Stream API 활용  
 */

class Solution {
	
	public int[] solution(int[] arr) {
		if (arr.length == 1) return new int[] {-1};  // 요소가 하나뿐이면 [-1] 반환
		
		int min = Arrays.stream(arr).min().getAsInt();  // 최소값 구하기
		
		// 최소값이 아닌 원소만 필터링하여 새 배열 생성
		return Arrays.stream(arr)
					 .filter(i -> i != min)
					 .toArray();
	}
	
}
