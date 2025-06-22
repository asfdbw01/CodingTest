/*  
 * 문제 요약
 *     - 어떤 수 x를 기준으로 {x, a}, {x, b}, ... 쌍을 이루는 부분 수열 중 길이가 가장 긴 스타 수열의 길이를 구하는 문제
 *     - 스타 수열은 각 쌍이 서로 다른 두 원소로 구성되어야 하며, 쌍 전체에서 공통 원소 x가 있어야 함
 *
 * 입력  
 *     - int[] a: 1 이상 500,000 이하의 정수 배열 (원소는 0 이상 a.length 미만)
 *
 * 출력  
 *     - 가장 긴 스타 수열의 길이 (짝수), 없다면 0 반환
 *
 * 핵심 포인트  
 *     - O(N²) 방식으로 모든 쌍을 비교하면 시간 초과 → 특정 수를 기준으로만 유효 쌍을 카운트
 *     - 서로 다른 두 원소 쌍 중 하나가 특정 수일 때 유효한 쌍으로 판단하고 중복을 피해야 함
 *     - 수마다 마지막 사용 인덱스를 기억하여 동일 쌍 중복을 방지하고 최대 등장 쌍 수를 세기
 */

class Solution {
	
	public int solution(int[] a) {
		if (a.length <= 1) return 0;
		
		int len = a.length;
		int[] count = new int[len];
		int[] lastUsedIndex = new int[len];
		Arrays.fill(lastUsedIndex, -1);
		
		// 첫 두 원소가 서로 다르면 a[0] 기준으로 유효쌍 판단
		if (a[0] != a[1]) {
			count[a[0]]++;
			lastUsedIndex[a[0]] = 1;
		}
		
		// 중간 구간에서 a[i] 기준으로 인접 쌍 확인
		for (int i = 1; i < len - 1; i++) {
			if (a[i] != a[i - 1] && lastUsedIndex[a[i]] != i - 1) {
				count[a[i]]++;
				lastUsedIndex[a[i]] = i - 1;
			} else if (a[i] != a[i + 1] && lastUsedIndex[a[i]] != i + 1) {
				count[a[i]]++;
				lastUsedIndex[a[i]] = i + 1;
			}
		}
		
		// 마지막 두 원소가 다르고 중복되지 않으면 처리
		if (a[len - 1] != a[len - 2] && lastUsedIndex[a[len - 1]] != len - 2) {
			count[a[len - 1]]++;
			lastUsedIndex[a[len - 1]] = len - 2;
		}
		
		// 가장 많이 등장한 수 기준으로 최대 쌍 개수 × 2 = 최대 스타 수열 길이
		return Arrays.stream(count).max().orElse(0) * 2;
	}
	
}
