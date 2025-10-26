/*  
 * 문제 요약
 *     - 주어진 선행 스킬 순서에 따라 유저가 만든 스킬트리가 유효한지 판단하여,
 *       가능한 스킬트리 개수를 반환하는 문제.
 *
 * 입력
 *     - skill: 선행 스킬 순서를 나타내는 문자열 (길이 1~26)
 *     - skill_trees: 유저가 만든 스킬트리 배열 (길이 1~20, 각 원소 길이 2~26)
 *
 * 출력
 *     - 유효한 스킬트리 개수(int)
 *
 * 핵심 포인트
 *     - skill에 포함되지 않은 스킬은 무시
 *     - skill에 포함된 스킬만 순서대로 등장해야 함
 *     - 선행 스킬이 빠진 상태에서 이후 스킬이 먼저 나오면 실패 처리
 */

class Solution {
	
	public int solution(String skill, String[] skill_trees) {
		Set<Character> skills = skill.chars().mapToObj(i -> (char) i).collect(Collectors.toSet());
		
		int count = 0;
		Outter : for (String str : skill_trees) {
			int index = 0;
			for (char c : str.toCharArray()) {
				// 선행 스킬이면 순서 일치 확인
				if (skills.contains(c) && skill.charAt(index++) != c) continue Outter;
			}
			count++;
		}
		
		return count;
	}
	
}
