/*  
 * 문제 요약
 *     - 과학자가 발표한 논문의 인용 횟수가 주어질 때, H-Index를 계산하는 문제  
 *     - H-Index란 h번 이상 인용된 논문이 h편 이상인 h의 최댓값을 의미함
 *
 * 입력  
 *     - citations: 각 논문의 인용 횟수를 담은 정수 배열 (1 이상 1,000 이하, 인용 수는 0 이상 10,000 이하)
 *
 * 출력  
 *     - H-Index 정수값 (int)
 *
 * 핵심 포인트  
 *     - 인용 수를 내림차순 정렬 후 i번째 논문이 i+1번째 이상 인용되었는지 확인  
 *     - h ≤ 인용수[i] 이고, h는 i+1번째 논문까지의 논문 수
 *     - 모든 논문에 대해 min(i+1, 인용수[i])를 구해 그 중 최대값이 H-Index
 */

class Solution {
	
	public int solution(int[] citations) {
		int[] sortedArr = Arrays.stream(citations)
						  .boxed()
						  .sorted(Comparator.reverseOrder())
						  .mapToInt(Integer::intValue)
						  .toArray();
		
		// 각 위치에서 (i+1번째 논문 수, 인용 수) 중 더 작은 값이 가능한 H-Index
		// 그 중 최대값이 정답
		return IntStream.range(0, citations.length)
						.map(i -> Math.min(i + 1, sortedArr[i]))
						.max()
						.getAsInt();
	}
	
}
