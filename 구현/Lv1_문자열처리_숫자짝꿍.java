/*
 * 문제 요약
 *     - 두 정수 X, Y에서 공통으로 나타나는 숫자를 짝지어 만들 수 있는 가장 큰 수를 구한다.
 *     - 짝꿍 숫자가 없으면 "-1", 0으로만 이루어졌다면 "0"을 반환한다.
 * 입력
 *     - X, Y: 최대 3,000,000자리의 숫자를 문자열로 입력 (0으로 시작하지 않음)
 * 출력
 *     - X와 Y의 짝꿍 숫자를 문자열로 반환
 * 핵심 포인트
 *     - 각 숫자(0~9)의 등장 빈도를 카운팅한 후, 최소값만큼 사용
 *     - 숫자를 큰 순서(9~0)로 탐색하여 StringBuilder에 추가
 *     - 전부 0으로만 이루어진 경우와 짝꿍이 존재하지 않는 경우 예외 처리
 */

class Solution {
	
	public String solution(String X, String Y) {
		StringBuilder sb = new StringBuilder();
		int[][] count = new int[2][10];  // X와 Y에서 각 숫자의 개수를 세기 위한 배열

		// X와 Y의 각 숫자 등장 횟수 카운팅
		for (char c : X.toCharArray()) count[0][c - '0']++;
		for (char c : Y.toCharArray()) count[1][c - '0']++;

		// 9부터 0까지 공통으로 등장한 숫자를 가능한 만큼 StringBuilder에 추가
		for (int i = 9; i >= 0; i--) {
			int pair = Math.min(count[0][i], count[1][i]);
			for (int j = 0; j < pair; j++) sb.append(i);
		}

		// 짝꿍이 존재하지 않는 경우
		if (sb.length() == 0) return "-1";

		// 모든 짝꿍 숫자가 0인 경우
		if (sb.charAt(0) == '0') return "0";

		return sb.toString();
	}
}
