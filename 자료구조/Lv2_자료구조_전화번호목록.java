/* 
 * 문제 요약
 *     - 전화번호 목록에서 한 번호가 다른 번호의 접두어인 경우가 있는지 확인
 * 
 * 입력
 *     - 전화번호 목록 배열 phone_book (1 ≤ 길이 ≤ 1,000,000, 각 번호 길이 ≤ 20)
 * 
 * 출력
 *     - 접두어 관계가 있으면 false, 없으면 true
 * 
 * 핵심 포인트
 *     - 모든 전화번호를 HashSet에 넣고, 각 번호의 접두어가 set에 존재하는지 검사
 *     - 접두어가 발견되면 즉시 false 반환
 */

class Solution {
	
	public boolean solution(String[] phone_book) {
		Set<String> set = new HashSet<>();
		
		// 모든 전화번호를 HashSet에 저장
		Arrays.stream(phone_book).forEach(set::add);
		
		// 각 번호의 접두어(1 ~ length - 1)를 검사하여
		// 다른 번호가 접두어로 존재하는 경우 false 반환
		for (String phone : phone_book) {
			for (int i = 1; i < phone.length(); i++) {
				if (set.contains(phone.substring(0, i))) return false;
			}
		}
		
		// 접두어가 존재하지 않으면 true 반환
		return true;
	}
}
