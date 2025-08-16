/*  
 * 문제 요약
 *     - 손님들의 주문 내역을 분석해 가장 자주 함께 주문된 단품 메뉴 조합을 코스요리 후보로 선정
 *
 * 입력
 *     - orders: 손님들이 주문한 단품 메뉴 문자열 배열 (각 문자열은 중복 없는 알파벳 대문자, 길이 2~10)
 *     - course: 만들고 싶은 코스 요리의 구성 수 배열 (각 원소는 2~10, 오름차순, 중복 없음)
 *
 * 출력
 *     - 각 코스 구성 수 별로 가장 자주 등장한 조합을 문자열 배열로 반환 (사전순 정렬)
 *
 * 핵심 포인트
 *     - 주문 문자열을 사전순 정렬하여 조합 키 통일
 *     - 비트마스킹으로 조합 생성 (길이 필터링 포함)
 *     - 각 길이별로 등장 횟수 최대 조합만 추출 (단, 최소 2회 이상 등장 조건)
 *     - TreeSet을 사용해 사전순 정렬 자동 처리
 */

class Solution {
	
	public String[] solution(String[] orders, int[] course) {
		// 각 주문의 메뉴 순서를 사전순으로 정렬하여 중복 조합 정규화
		for (int i = 0; i < orders.length; i++) {
			char[] chars = orders[i].toCharArray();
			Arrays.sort(chars);
			orders[i] = new String(chars);
		}
		
		// 조합 카운팅용 맵, 길이별 조합 모음 맵 초기화
		Map<Integer, List<String>> setLengthMap = new HashMap<>();
		Map<String, Integer> setMap = new HashMap<>();

		// course 배열을 빠르게 판별하기 위한 boolean 배열
		boolean[] isTargetLen = new boolean[11];
		for (int i : course) isTargetLen[i] = true;
		
		// 각 주문 문자열에 대해 가능한 모든 조합을 비트마스킹으로 생성
		for (String order : orders) {
			for (int i = 0; i < (1 << order.length()); i++) {
				if (isTargetLen[Integer.bitCount(i)]) {
					StringBuilder sb = new StringBuilder();
					for (int j = 0; j < order.length(); j++) {
						if ((i & (1 << j)) != 0) sb.append(order.charAt(j));
					}
					String combo = sb.toString();

					// 길이별 조합 목록과 전체 등장 횟수 누적
					setLengthMap.computeIfAbsent(combo.length(), k -> new ArrayList<>()).add(combo);
					setMap.merge(combo, 1, Integer::sum);
				}
			}
		}
		
		// 정답 후보들을 담을 TreeSet (자동 사전순 정렬)
		Set<String> answer = new TreeSet<>();
		
		// 각 코스 길이에 대해
		for (int len : course) {
			if (!setLengthMap.containsKey(len)) continue;

			// 등장 횟수가 가장 많은 조합 찾기
			int max = 0;
			for (String set : setLengthMap.get(len)) {
				max = Math.max(max, setMap.get(set));
			}
			if (max < 2) continue;  // 최소 2명 이상이 주문한 조합만 유효

			// 최대 등장 횟수를 가진 조합만 정답 후보에 추가
			for (String set : setLengthMap.get(len)) {
				if (setMap.get(set) == max) answer.add(set);
			}
		}
		
		// TreeSet을 배열로 변환하여 반환
		return answer.toArray(String[]::new);
	}
}
