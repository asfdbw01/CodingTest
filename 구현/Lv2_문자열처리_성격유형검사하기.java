/*
 * 문제 요약
 *     - 4개의 지표(RT, CF, JM, AN)에 대해 각 유형 중 높은 점수를 기준으로 성격 유형 결정
 *     - 설문 결과(survey)와 선택 점수(choices)를 기반으로 점수 계산
 * 입력
 *     - String[] survey : 각 질문의 지표 ("RT", "TR" 등)
 *     - int[] choices : 1 ~ 7 사이의 선택 점수 (1: 매우 비동의, 7: 매우 동의)
 * 출력
 *     - String : 4개의 지표별로 선택된 성격 유형을 이어붙인 문자열
 * 핵심 포인트
 *     - 선택 점수가 4보다 작으면 비동의 유형에, 4보다 크면 동의 유형에 점수 부여
 *     - 점수가 같을 경우 사전 순으로 빠른 유형 선택
 */

class Solution {
	
	char[][] categories = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
	
	public String solution(String[] survey, int[] choices) {
		int[] score = new int['Z' - 'A' + 1];
		
		for (int i = 0; i < choices.length; i++) {
			// 선택 점수가 4보다 크면 동의 쪽에 점수
			if (choices[i] > 4) score[survey[i].charAt(1) - 'A'] += choices[i] - 4;
			// 선택 점수가 4보다 작으면 비동의 쪽에 점수
			else if (choices[i] < 4) score[survey[i].charAt(0) - 'A'] += 4 - choices[i];
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (char[] category : categories) {
			// 점수가 같을 경우 사전 순으로 빠른 쪽 선택
			sb.append((score[category[0] - 'A'] >= score[category[1] - 'A']) ? category[0] : category[1]);
		}
		
		return sb.toString();
	}
}
