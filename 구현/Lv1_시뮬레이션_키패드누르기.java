/*  
 * 문제 요약
 *     - 스마트폰 키패드에서 숫자를 입력할 때, 왼손/오른손의 사용 규칙에 따라 어떤 손을 사용하는지 판단하여 문자열로 반환
 *
 * 입력  
 *     - numbers: 누를 숫자들이 담긴 배열 (길이 1 이상 1,000 이하, 값은 0~9)
 *     - hand: 주 사용 손 ("left" 또는 "right")
 *
 * 출력  
 *     - 각 숫자를 누를 때 사용한 손을 L 또는 R로 이어붙인 문자열
 *
 * 핵심 포인트  
 *     - 1, 4, 7은 무조건 왼손 / 3, 6, 9는 무조건 오른손  
 *     - 2, 5, 8, 0은 두 엄지손가락의 거리 비교 후 가까운 손 선택  
 *     - 거리가 같다면 주 사용 손(hand)에 따라 결정  
 *     - 키패드를 1차원 인덱스로 변환하여 거리 계산 간소화
 */

class Solution {
	
	public String solution(int[] numbers, String hand) {
		StringBuilder sb = new StringBuilder();

		// 시작 위치: 왼손은 '*' → 9, 오른손은 '#' → 11로 가정 (0-based index)
		int left = 9, right = 11;
		
		for (int num : numbers) {
			// 숫자 0은 키패드 상 위치 11번으로 변환
			if (num == 0) num = 11;
			num--; // 0-based 인덱스로 변환
			
			switch (num % 3) {
			case 0:
				// 1, 4, 7 → 왼손 사용
				sb.append("L");
				left = num;
				break;
			case 2:
				// 3, 6, 9 → 오른손 사용
				sb.append("R");
				right = num;
				break;
			default: 
				// 2, 5, 8, 0 → 거리 계산 후 더 가까운 손 선택
				int leftDist = Math.abs(left / 3 - num / 3) + Math.abs(left % 3 - num % 3);
				int rightDist = Math.abs(right / 3 - num / 3) + Math.abs(right % 3 - num % 3);
				
				if (leftDist < rightDist || (leftDist == rightDist && hand.equals("left"))) {
					// 왼손이 더 가깝거나 거리 같고 왼손잡이일 경우
					sb.append("L");
					left = num;
				} else {
					// 오른손이 더 가깝거나 거리 같고 오른손잡이일 경우
					sb.append("R");
					right = num;
				}
			}
		}
		
		return sb.toString();
	}
}
