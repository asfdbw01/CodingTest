/*  
 * 문제 요약
 *     - 정수 배열에서 서로 다른 인덱스에 있는 두 수를 뽑아 더한 결과들을 모두 구하고,
 *       중복 없이 오름차순으로 정렬하여 배열로 반환하는 문제
 *
 * 입력  
 *     - int[] numbers: 길이 2 이상 100 이하, 각 원소는 0 이상 100 이하의 정수
 *
 * 출력  
 *     - int[]: 두 수의 합으로 만들 수 있는 모든 경우의 수를 오름차순 정렬하여 반환
 *
 * 핵심 포인트  
 *     - 중복된 합 제거 → distinct()
 *     - 정렬 → sorted()
 *     - 서로 다른 두 인덱스 쌍 → i < j 조건으로 처리
 *     - 스트림을 활용해 flatMap으로 2중 루프를 평탄화하여 표현
 */

class Solution {
	
	public int[] solution(int[] numbers) {
		return IntStream.range(0, numbers.length)      // [1] 인덱스 i 순회 (0 ~ n-1)
				.boxed()                               // [2] IntStream → Stream<Integer> (flatMap을 위해)
				.flatMap(i -> IntStream.range(i + 1, numbers.length) // [3] i 이후 인덱스 j 순회
				                       .map(j -> numbers[i] + numbers[j]) // [4] 두 수의 합 계산
				                       .boxed())                          // [5] int → Integer (Stream<Integer> 리턴)
				.distinct()                            // [6] 중복 제거
				.sorted()                              // [7] 오름차순 정렬
				.mapToInt(Integer::intValue)           // [8] Integer → int (기본형 배열로 만들기 위해)
				.toArray();                            // [9] 최종적으로 int[] 배열로 반환
	}
	
}
