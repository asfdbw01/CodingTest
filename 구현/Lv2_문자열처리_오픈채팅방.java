/*  
 * 문제 요약
 *     - 오픈채팅방에서 사용자의 입장, 퇴장, 닉네임 변경 기록을 받아 
 *       최종적으로 출력될 메시지 목록을 생성하는 문제
 *
 * 입력  
 *     - record: 명령어("Enter", "Leave", "Change")와 유저ID, 닉네임(옵션)이 포함된 문자열 배열 (1 ≤ 길이 ≤ 100,000)
 *
 * 출력  
 *     - 최종적으로 방장 화면에 보여질 메시지를 담은 문자열 배열
 *
 * 핵심 포인트  
 *     - 닉네임 변경은 과거 기록까지 모두 반영되어야 함
 *     - ID 기준으로 닉네임을 저장하고, 출력 메시지는 마지막 닉네임으로 생성
 */

class Solution {

	public String[] solution(String[] record) {
		Map<String, String> nameMap = new HashMap<>(); // uid -> nickname 맵

		// 먼저 uid에 대한 최신 nickname 정보를 기록
		for (String s : record) {
			if (s.startsWith("Leave")) continue;
			String[] parts = s.split(" ");
			
			nameMap.put(parts[1], parts[2]);
		}

		// 메시지를 기록할 리스트
		List<String> answer = new ArrayList<>();
		for (String s : record) {
			String[] parts = s.split(" ");
			String log = parts[0], uid = parts[1];

			if (log.equals("Enter")) {
				answer.add(nameMap.get(uid) + "님이 들어왔습니다.");
			} else if (log.equals("Leave")) {
				answer.add(nameMap.get(uid) + "님이 나갔습니다.");
			}
		}

		return answer.toArray(String[]::new);
	}
}
