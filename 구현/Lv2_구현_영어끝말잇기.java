/*  
 * 문제 요약  
 *     - 끝말잇기 게임에서 규칙 위반이 발생하는 최초의 사람과 차례를 구하는 문제  
 *
 * 입력  
 *     - n: 사람 수 (2 ≤ n ≤ 10)  
 *     - words: 끝말잇기 단어 배열 (길이 n 이상 100 이하)  
 *
 * 출력  
 *     - [번호, 차례]: 위반자가 누구인지 [사람 번호, 몇 번째 차례] 형태  
 *     - 위반자가 없으면 [0, 0] 반환  
 *
 * 핵심 포인트  
 *     - 한 글자 단어는 없음 (입력 조건상 항상 길이 ≥ 2)  
 *     - 앞 단어의 마지막 글자와 현재 단어의 첫 글자 일치 확인  
 *     - 중복된 단어 사용 금지 (HashSet으로 빠르게 확인)  
 *     - 사람 번호 = (턴 번호 % n) + 1, 차례 = (턴 번호 / n) + 1  
 */

class Solution {
	
	public int[] solution(int n, String[] words) {
		Set<String> used = new HashSet<>();
		used.add(words[0]);
		
		char c = words[0].charAt(words[0].length() - 1);
		
		for (int i = 1; i < words.length; i++) {
			// 앞 단어의 마지막 문자와 현재 단어의 첫 문자가 다르거나, 중복 단어일 경우
			if (words[i].charAt(0) != c || !used.add(words[i])) {
				return new int[] {i % n + 1, i / n + 1};
			}
			c = words[i].charAt(words[i].length() - 1);
		}
		
		// 탈락자 없음
		return new int[] {0, 0};
	}
	
}
