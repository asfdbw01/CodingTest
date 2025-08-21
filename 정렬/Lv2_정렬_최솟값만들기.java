/*  
 * 문제 요약
 *     - 두 배열 A, B에서 각각 하나의 수를 선택해 곱하고 누적합을 구할 때,
 *       이 누적합이 최소가 되도록 수를 짝지어 계산
 *
 * 입력  
 *     - int[] A, B : 길이가 같은 자연수 배열 (1 이상 1,000 이하)
 *
 * 출력  
 *     - int : 곱의 누적합의 최소값
 *
 * 핵심 포인트  
 *     - A는 오름차순, B는 내림차순으로 정렬하여 작은 값과 큰 값을 곱하면 누적합 최소화 가능
 *     - 정렬 후 index를 반대로 매칭하면 효율적인 구현 가능
 */

class Solution {
	
	public int solution(int[] A, int[] B) {
		Arrays.sort(A); // A: 오름차순 정렬
		Arrays.sort(B); // B: 오름차순 정렬

		// A의 작은 수 × B의 큰 수 순으로 곱하여 최소 누적합 계산
		return IntStream.range(0, A.length)
						.map(i -> A[i] * B[B.length - 1 - i])
						.sum();
	}
	
}
