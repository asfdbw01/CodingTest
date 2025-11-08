/*  
 * 문제 요약
 *     - 배열 array에서 주어진 구간 [i, j]를 자르고 정렬한 뒤,
 *       해당 구간의 k번째 수를 구하여 결과 배열로 반환하는 문제
 *
 * 입력  
 *     - array: 정수 배열 (길이 1 이상 100 이하)
 *     - commands: 2차원 배열, 각 원소는 [i, j, k] (길이 1 이상 50 이하)
 *
 * 출력  
 *     - 각 명령어에 대해 구한 k번째 수들을 담은 정수 배열
 *
 * 핵심 포인트  
 *     - 구간 자르기: 인덱스 보정 필요 (i-1 ~ j-1)
 *     - 정렬 후 k번째 요소 접근: Stream에서 skip(k-1) + findFirst() 사용
 *     - Stream API를 사용한 선언형 처리로 가독성과 간결함 확보
 */

class Solution {
	
	public int[] solution(int[] array, int[][] commands) {
		return IntStream.range(0, commands.length)
						.map(i -> IntStream.rangeClosed(commands[i][0] - 1, commands[i][1] - 1)
										   .map(j -> array[j])       // 구간 추출
										   .sorted()                 // 정렬
										   .skip(commands[i][2] - 1) // k번째까지 건너뜀
										   .findFirst()              // k번째 값 가져오기
										   .getAsInt())              // int 반환
						.toArray();
	}
	
}
