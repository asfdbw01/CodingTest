/*두 사람이 서로 선물을 주고받았다면,
→ 더 많이 준 사람이 다음 달에 선물 하나 받음.

서로 주고받은 횟수가 같거나 아예 기록이 없다면,
→ 선물 지수가 더 높은 사람이 낮은 사람에게 선물 하나 받음.
→ 선물 지수 = 준 횟수 - 받은 횟수

선물 지수도 같다면 아무 일도 안 일어남.

위 규칙을 바탕으로 다음 달에 친구별로 받을 선물 수를 계산하고,
그 중 가장 많이 받는 친구의 선물 개수를 리턴.


2 ≤ friends의 길이 = 친구들의 수 ≤ 50
		- friends의 원소는 친구의 이름을 의미하는 알파벳 소문자로 이루어진 길이가 10 이하인 문자열
		- 이름이 같은 친구는 없음

1 ≤ gifts의 길이 ≤ 10,000
		- gifts의 원소는 "A B"형태의 문자열
		- A와 B는 friends의 원소이며 A와 B가 같은 이름인 경우는 존재하지 않음
*/

class Solution {
	String[] friends;		// 친구
	String[] gifts;			// 선물 교환 기록
	int[][] giveCnt;		// friends 배열의 순서를 기준으로, 행: 선물을 준 사람, 열: 선물을 받은 사람
	int[] totalGiveCnt;		// 선물 지수
	int[] expGiftAmount;	// 기대 선물량
	int fLen;				// 친구의 수 (=모든 배열의 크기/길이)
	
	public int solution(String[] friends, String[] gifts) {
		int answer = 0;
		this.friends = friends;
		this.gifts = gifts;
		this.fLen = friends.length;
		this.expGiftAmount = new int[fLen];
		
		this.giftCnt();
		this.expectedGifts();
		
		// 가장 많은 선물을 받을 것으로 예상되는 양을 answer에 대입
		for (int exp : expGiftAmount) if (exp > answer) answer = exp;

		return answer;
	}
	
	// 선물을 주는 대상이 i, 선물을 받는 대상이 j 인덱스를 가짐
	// gifts 배열을 순회하며, i가 j에게 선물을 준 경우를 확인
	// 주었다면 giveCnt[i][j]++
	// 선물지수는 주는 사람은 +, 받는 사람은 -가 됨
	public void giftCnt() {
		this.giveCnt = new int[fLen][fLen];
		this.totalGiveCnt = new int[fLen];
		
		for (int i = 0; i < friends.length; i++) {
			for (int j = 0; j < friends.length; j++) {				
				for (String gift : gifts) {
					if ((friends[i] + " " + friends[j]).equals(gift)) {
						giveCnt[i][j]++;
						totalGiveCnt[i]++;
						totalGiveCnt[j]--;
					}
				}
			}
		}
	}
	
	// 선물을 둘이 주고 받은 양을 비교 -> 더 많이 선물한 사람에게 선물 1개 누적
	// 스스로에게 선물은 불가능 -> continue
	// 주고 받은 양이 같거나 없다면 선물 지수를 비교, 더 많이 선물한 사람에게 선물 1개 누적
	public void expectedGifts() {
		for (int i = 0; i < fLen; i++) {
			for (int j = 0; j < fLen; j++) {
				if (i == j) continue;
				else if (giveCnt[i][j] > giveCnt[j][i]) expGiftAmount[i]++;
				else if (giveCnt[i][j] == giveCnt[j][i] && totalGiveCnt[i] > totalGiveCnt[j]) expGiftAmount[i]++;
			}
		}
	}
	
}