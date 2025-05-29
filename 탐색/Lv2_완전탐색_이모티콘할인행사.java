/*
 * 문제 요약
 *     - 이모티콘에 할인율(10, 20, 30, 40%)을 적용하여 판매 시, 각 사용자의 구매 기준에 따라
 *       이모티콘을 구매하거나 이모티콘 플러스 서비스에 가입함.
 *     - 할인율 조합을 적절히 선택하여 서비스 가입자 수를 최대화하고, 그 수가 같다면 판매 수익을 최대화하는 것이 목표.
 * 
 * 입력
 *     - users: int[][], 각 사용자에 대한 [최소 할인율, 최소 구매 금액] 정보 (1 ≤ 길이 ≤ 100)
 *     - emoticons: int[], 이모티콘의 정가 목록 (1 ≤ 길이 ≤ 7, 정가는 100의 배수)
 * 
 * 출력
 *     - int[], 크기 2, [이모티콘 플러스 서비스 가입자 수, 이모티콘 판매 금액 합계]
 * 
 * 핵심 포인트
 *     - 각 이모티콘의 할인율을 4가지 중 하나로 선택하는 모든 경우의 수(중복순열)를 완전탐색
 *     - 각 할인율 조합에 대해 사용자별 구매 금액을 계산하고 조건에 따라 가입 여부 판단
 *     - 가입자 수를 최우선으로, 그 수가 같을 경우 판매 수익을 기준으로 최적 선택
 */

class Solution {
	
	int[] rates = {10, 20, 30, 40};       // 가능한 할인율 목록
	int eLen;                             // 이모티콘 개수
	List<int[]> allDiscounts;            // 모든 할인율 조합 저장 리스트

	// 메인 로직: 최적의 할인율 조합을 찾아 가입자 수와 판매 수익을 계산
	public int[] solution(int[][] users, int[] emoticons) {
		int[] answer = {0, 0};            // [이모티콘 플러스 가입자 수, 판매 수익]

		this.eLen = emoticons.length;
		this.allDiscounts = new ArrayList<>();
		backtrack(new int[eLen], 0);     // 할인율 조합 완전탐색 시작

		// 모든 할인 조합에 대해 사용자 반응 시뮬레이션
		for (int[] discounts : allDiscounts) {
			int plusUsers = 0, totalSales = 0;

			for (int[] user : users) {
				int curSales = 0;

				// 사용자가 구매할 이모티콘 총액 계산
				for (int i = 0; i < eLen; i++) {
					if (discounts[i] < user[0]) continue;
					curSales += emoticons[i] * (100 - discounts[i]) / 100;
				}

				// 기준 이상 지출 시 가입, 아니면 구매 수익으로 포함
				if (curSales >= user[1]) plusUsers++;
				else totalSales += curSales;
			}

			// 우선순위: 가입자 수 → 가입자 수가 같으면 판매 수익
			if (plusUsers > answer[0]) {
				answer[0] = plusUsers;
				answer[1] = totalSales;
			} else if (plusUsers == answer[0] && totalSales > answer[1]) {
				answer[1] = totalSales;
			}
		}

		return answer;
	}

	// 백트래킹으로 할인율 조합 생성 (중복순열)
	public void backtrack(int[] cur, int depth) {
		if (depth == eLen) {
			allDiscounts.add(cur.clone());
			return;
		}

		for (int rate : rates) {
			cur[depth] = rate;
			backtrack(cur, depth + 1);
		}
	}
}
