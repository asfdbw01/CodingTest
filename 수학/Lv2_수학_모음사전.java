/*  
 * 문제 요약
 *     - 'A', 'E', 'I', 'O', 'U'로 구성된 길이 1~5의 모든 단어가 사전 순으로 수록되어 있음
 *     - 주어진 단어가 사전에서 몇 번째에 위치하는지 구하는 문제
 *
 * 입력  
 *     - String word: 알파벳 대문자 'A', 'E', 'I', 'O', 'U'로 구성된 길이 1~5의 문자열
 *
 * 출력  
 *     - 해당 단어가 사전에서 몇 번째에 위치하는지를 나타내는 정수
 *
 * 핵심 포인트  
 *     - 각 자리마다 5진법 기반의 가중치를 부여하여 계산
 *     - 각 문자의 인덱스 × 해당 자리의 가중치의 총합 + 단어 길이로 위치 계산
 *     - 수학적으로 파악하면 시간복잡도 O(1)로 매우 빠르게 해결 가능
 */

class Solution {
	
	public int solution(String word) {
		String vowel = "AEIOU";
		int[] weight = new int[vowel.length() + 1];
		
		for (int i = vowel.length() - 1; i >= 0; i--) {
			weight[i] = weight[i + 1] * 5 + 1; // 자리별 가중치 계산
		}
		
		int answer = 0;
		
		for (int i = 0; i < word.length(); i++) {
			// 각 자리에서 해당 문자 인덱스 × 가중치 누적
			answer += weight[i] * vowel.indexOf(word.charAt(i));
		}
		
		return answer + word.length(); // 자기 자신까지 포함한 순번 보정
	}
}
