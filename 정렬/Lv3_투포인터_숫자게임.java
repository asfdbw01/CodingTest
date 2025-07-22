/*  
 * 문제 요약
 *     - A팀과 B팀이 각각 자연수를 하나씩 받아 1:1 매치를 하고, B팀이 출전 순서를 조절하여 얻을 수 있는 최대 승점을 구하는 문제
 *
 * 입력  
 *     - int[] A: A팀의 출전 순서대로 받은 수
 *     - int[] B: B팀의 각 사원이 받은 수 (순서는 자유롭게 조정 가능)
 *
 * 출력  
 *     - int: B팀이 최적으로 순서를 조정했을 때 얻을 수 있는 최대 승점
 *
 * 핵심 포인트  
 *     - A팀은 순서를 고정했으므로, B팀은 이진 매칭처럼 가능한 최소 수로 이기는 전략을 사용해야 함
 *     - 정렬 후 투 포인터 방식으로 B팀의 각 수가 A팀 수를 이길 수 있으면 매칭하여 승점 획득
 */

class Solution {
	
	public int solution(int[] A, int[] B) {
		Arrays.sort(A);  // A팀 수 오름차순 정렬
		Arrays.sort(B);  // B팀 수 오름차순 정렬
		
		int aIndex = 0, bIndex = 0;
		
		// B팀 수가 A팀 수를 이길 때마다 A의 다음 사람으로 이동하며 승점 추가
		while (bIndex < B.length) {
			if (B[bIndex++] > A[aIndex]) aIndex++;
		}
		
		return aIndex;
	}
}
