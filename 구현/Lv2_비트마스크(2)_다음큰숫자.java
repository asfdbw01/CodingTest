/*  
 * 문제 요약
 *     - 주어진 자연수 n보다 큰 수 중에서, 2진수로 변환했을 때 1의 개수가 같은 가장 작은 수를 구하는 문제
 *
 * 입력
 *     - 자연수 n (1 ≤ n ≤ 1,000,000)
 *
 * 출력
 *     - 조건을 만족하는 가장 작은 자연수
 *
 * 핵심 포인트
 *     - 비트마스크와 비트 조작으로 다음 숫자를 빠르게 생성
 *     - (n + targetBit)로 자리 올림
 *     - 하위 비트 개수를 계산해 다시 오름차순으로 채움
 */

class Solution {
	
	public int solution(int n) {
		// (~n >> 1) & n : 오른쪽에 '01' 패턴이 생기는 자리 검출
		int targetBit = Integer.lowestOneBit((~n >> 1) & n);
		
		// targetBit 아래에 있던 1의 개수 계산
		int ones = Integer.bitCount(n & (targetBit - 1));
		
		// targetBit을 올리고 하위 비트를 모두 0으로 클리어한 후 ones 만큼 1을 하위에 채움
		return (n + targetBit) & ~(targetBit - 1) | ((1 << ones) - 1);
	}

}
