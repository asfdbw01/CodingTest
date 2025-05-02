/*
 * 문제 요약
 * 		- 한 유저가 다른 유저를 신고할 수 있으며, 중복 신고는 1회로 처리
 * 		- k번 이상 신고당한 유저는 정지, 이를 신고한 유저들에게 메일 발송
 * 		- id_list 순서에 따라 메일 수를 반환
 * 
 * 입력값
 * 		- id_list: 전체 유저 ID 목록 (중복 없음)
 * 		- report: "신고자 피신고자" 형식의 문자열 목록 (중복 신고 가능)
 * 		- k: 정지 처리 기준이 되는 신고 횟수 (자연수)
 * 
 * 출력값
 * 		- 각 유저가 받은 정지 처리 결과 메일 수를 int 배열로 반환 (id_list 순서대로)
 *
 * 핵심 로직
 * 		- 신고 관계는 Map<String, Set<String>>으로 집계
 * 		- 정지된 유저는 Set 크기가 k 이상인 경우로 판단
 * 		- 메일 발송 수는 Map<String, Integer>로 기록
 */

// 스스로에게 주는 제약조건
// 		1. if문 최소화
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Set<String>> reportedByMap = new HashMap<>();	// 피신고자를 신고한 유저 Map
        Map<String, Integer> mailCounts = new HashMap<>();			// 신고자에게 오는 정지 결과 메일의 수
        
        for (String rep : report) {
        	String[] parts = rep.split(" ");
        	String reporter = parts[0],		// 신고자
        		   reportedUser = parts[1];	// 피신고자

        	reportedByMap.computeIfAbsent(reportedUser, key -> new HashSet<>()).add(reporter); // 피신고자를 신고한 유저 이름을 추가
        }
        
        for (Set<String> reporters : reportedByMap.values()) {
        	if (reporters.size() < k) continue;		// 누적 신고건수가 기준점에 미달 시 continue
        	for (String reporter : reporters) {
        		mailCounts.put(reporter, mailCounts.getOrDefault(reporter, 0) + 1);	// 신고자가 받는 메일 수 + 1
        	}
        }
        
        for (int i = 0; i < answer.length; i++) {
        	answer[i] = mailCounts.getOrDefault(id_list[i], 0);		// id_list의 순서대로 신고자가 받게되는 메일 수를 대입
        }
        
        return answer;
    }
}
