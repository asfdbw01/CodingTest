/*  
 * 문제 요약
 *     - 무인도에 갇힌 사람들을 구명보트로 구출하는 문제
 *     - 한 보트에 최대 2명까지 탑승 가능하며, 두 사람의 몸무게 합이 limit 이하일 때만 같이 탈 수 있음
 *
 * 입력  
 *     - people: 각 사람의 몸무게가 담긴 배열 (1 이상 50,000 이하, 각 값은 40~240 사이)
 *     - limit: 보트의 무게 제한 (40 이상 240 이하)
 *
 * 출력  
 *     - 모든 사람을 구출하는 데 필요한 최소 보트 수
 *
 * 핵심 포인트  
 *     - 무거운 사람부터 처리하면서 가능한 경우 가장 가벼운 사람을 함께 태움
 *     - 몸무게 배열을 정렬한 후 양 끝에서 투포인터로 접근하면 최적의 짝 조합을 빠르게 탐색 가능
 */

class Solution {
	
	public int solution(int[] people, int limit) {
		// 몸무게 오름차순 정렬
		Arrays.sort(people);

		int count = 0;
		int left = 0, right = people.length - 1;

		// 양쪽에서 투포인터로 사람 짝짓기 시도
		while (left <= right) {
			// 가장 무거운 사람 + 가장 가벼운 사람을 태울 수 있는 경우
			if (people[right--] + people[left] <= limit) left++;

			// 보트 하나 사용
			count++;
		}

		return count;
	}
}
