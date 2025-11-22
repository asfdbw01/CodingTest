/*  
 * 문제 요약  
 *     - 놀이기구를 1회, 2회, ..., count회까지 탈 때 드는 총 비용을 구하고  
 *       현재 가진 돈(money)보다 부족한 금액을 반환하는 문제  
 *
 * 입력  
 *     - price: 놀이기구 1회 요금 (1 ≤ price ≤ 2,500)  
 *     - money: 현재 가진 돈 (1 ≤ money ≤ 1,000,000,000)  
 *     - count: 놀이기구 탈 횟수 (1 ≤ count ≤ 2,500)  
 *
 * 출력  
 *     - 놀이기구를 count번 탈 때 부족한 금액  
 *     - 단, 부족하지 않으면 0 반환  
 *
 * 핵심 포인트  
 *     - 1 + 2 + ... + count = count * (count + 1) / 2  
 *     - 이 합에 price를 곱한 후 money와 비교  
 *     - 부족한 금액만큼 반환, 부족하지 않으면 0 반환  
 */

class Solution {
	
	public long solution(int price, int money, int count) {
		// Stream을 이용한 방식
		// return Math.max(0, LongStream.rangeClosed(1, count).sum() * price - money);

		// 등차수열 합 공식으로 비용 계산 → 부족한 금액 반환
		return Math.max(0, ((1 + count) * count / 2) * (long) price - money);
	}
	
}
