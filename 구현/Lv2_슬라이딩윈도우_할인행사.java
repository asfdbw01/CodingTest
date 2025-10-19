/*
 * 문제 요약
 *     - 할인 행사 품목 목록이 주어졌을 때, 정현이가 원하는 품목과 수량을 모두 할인 받을 수 있는 10일 연속 구간의 개수를 구한다.
 * 입력
 *     - want: 정현이가 원하는 제품명 배열 (길이 ≤ 10)
 *     - number: want에 대응하는 수량 배열 (합계 = 10)
 *     - discount: 마트에서 매일 할인하는 제품 배열 (길이 ≤ 100,000)
 * 출력
 *     - 할인 조건을 만족하는 10일 연속 구간의 개수 (정수)
 * 핵심 포인트
 *     - 슬라이딩 윈도우(크기 10)를 사용하여 각 날짜마다 조건 만족 여부를 O(1)로 판단
 *     - 할인 품목 수량은 Map으로 관리, 만족 여부는 Set 또는 카운터로 추적
 *     - 입장과 퇴장 시 품목 수량을 업데이트하여 빠르게 구간 상태를 유지
 */

class Solution {
	
	public int solution(String[] want, int[] number, String[] discount) {
		int count = 0;

		Map<String, Integer> wantMap = new HashMap<>();
		Map<String, Integer> discountMap = new HashMap<>();
		Set<String> enoughStuffSet = new HashSet<>();

		// 정현이가 원하는 품목과 수량을 맵에 기록
		for (int i = 0; i < want.length; i++) wantMap.put(want[i], number[i]);

		for (int i = 0; i < discount.length; i++) {
			// 윈도우에 새로 들어온 품목 처리
			if (wantMap.containsKey(discount[i])) {
				String stuff = discount[i];

				discountMap.merge(stuff, 1, Integer::sum);

				// 원하는 수량을 채웠을 경우 Set에 등록
				if (discountMap.get(stuff) >= wantMap.get(stuff)) {
					enoughStuffSet.add(stuff);
				}
			}

			// 윈도우에서 빠져나간 품목 처리 (10일 전 품목 제거)
			if (i - 10 >= 0 && wantMap.containsKey(discount[i - 10])) {
				String stuff = discount[i - 10];

				discountMap.merge(stuff, -1, Integer::sum);

				// 수량이 부족해졌을 경우 Set에서 제거
				if (discountMap.get(stuff) < wantMap.get(stuff)) {
					enoughStuffSet.remove(stuff);
				}
			}

			// 모든 품목이 조건을 만족하면 카운트 증가
			if (enoughStuffSet.size() == want.length) {
				count++;
			}
		}

		return count;
	}
}
