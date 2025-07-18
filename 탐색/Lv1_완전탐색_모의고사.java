/*  
 * 문제 요약
 *     - 수포자 3명이 각각 일정한 규칙에 따라 문제를 찍었고,
 *       실제 정답 배열이 주어졌을 때 가장 많은 문제를 맞힌 사람(들)의 번호를 반환한다.
 *
 * 입력  
 *     - answers: 정답 배열 (길이 1 이상 10,000 이하, 값은 1~5)
 *
 * 출력  
 *     - 가장 많은 문제를 맞힌 수포자의 번호를 오름차순으로 정렬한 배열
 *
 * 핵심 포인트  
 *     - 각 수포자의 찍기 패턴은 주기적으로 반복되므로 % 연산으로 접근
 *     - 각 수포자의 점수를 계산한 뒤, 최고 점수를 받은 수포자만 골라낸다
 *     - 여러 명이 동점일 경우 모두 반환
 */

class Solution {
	
	int[][] supoja = {
			{1, 2, 3, 4, 5},
			{2, 1, 2, 3, 2, 4, 2, 5},
			{3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
	};
	
	public int[] solution(int[] answers) {
		int[] score = new int[supoja.length];
		
		// 각 수포자의 패턴과 비교하여 정답 개수 누적
		for (int i = 0; i < answers.length; i++) {
			for (int j = 0; j < supoja.length; j++) {
				if (answers[i] == supoja[j][i % supoja[j].length]) {
					score[j]++;
				}
			}
		}
		
		// 최고 점수 계산
		int maxScore = Arrays.stream(score).max().getAsInt();
		
		// 최고 점수를 받은 수포자 번호만 추출
		return IntStream.range(0, score.length)
						.filter(i -> score[i] == maxScore)
						.map(i -> i + 1)
						.toArray();
	}
	
}
