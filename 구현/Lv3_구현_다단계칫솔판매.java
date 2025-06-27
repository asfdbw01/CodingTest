/*  
 * 문제 요약
 *     - 판매원은 칫솔을 팔아 발생한 이익의 10%를 추천인에게 넘기고, 나머지를 자신이 가짐
 *     - 추천인도 받은 금액의 10%를 상위 추천인에게 넘기고 이를 반복함
 *     - 단, 10%가 1원 미만이면 분배하지 않고 모두 가짐
 *
 * 입력  
 *     - String[] enroll: 등록된 판매원 이름 배열  
 *     - String[] referral: 추천인 이름 배열 (같은 인덱스는 enroll의 각 판매원에 대응)
 *     - String[] seller: 판매한 판매원 이름 배열
 *     - int[] amount: 각 판매원이 판매한 칫솔 개수 (seller와 인덱스 대응)
 *
 * 출력  
 *     - int[] 배열: enroll 순서대로 각 판매원이 얻은 총 이익
 *
 * 핵심 포인트  
 *     - 재귀적으로 이익을 분배하되, 중간에 분배 금액이 0원이면 중단
 *     - 추천인이 "-"인 경우 center이므로 상위로 분배 중단
 *     - 분배는 10%를 부모에게 넘기고 나머지를 자신이 가짐
 */

class Solution {
	
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		// 각 판매원(enroll[i])의 추천인을 저장하는 맵 생성
		Map<String, String> parentMap = new HashMap<>();
		for (int i = 0; i < referral.length; i++) {
			parentMap.put(enroll[i], referral[i]);  // 판매원 → 추천인 관계 설정
		}
		
		// 각 판매원의 누적 이익을 저장할 맵 생성
		Map<String, Integer> profitMap = new HashMap<>();
		for (int i = 0; i < seller.length; i++) {
			int totalProfit = amount[i] * 100;  // 칫솔 한 개당 100원
			distribute(seller[i], totalProfit, parentMap, profitMap);  // 재귀 분배
		}
		
		// 결과를 enroll 순서에 맞게 배열로 정리
		return Arrays.stream(enroll)
				.mapToInt(s -> profitMap.getOrDefault(s, 0))
				.toArray();
	}
	
	// 판매 이익을 상위 추천인까지 재귀적으로 분배
	public void distribute(String child, int profit, Map<String, String> parentMap, Map<String, Integer> profitMap) {
		if (profit == 0 || child.equals("-")) return;

		int fee = profit / 10;  // 추천인에게 줄 금액

		profitMap.merge(child, profit - fee, Integer::sum);
		distribute(parentMap.get(child), fee, parentMap, profitMap);
	}
}
