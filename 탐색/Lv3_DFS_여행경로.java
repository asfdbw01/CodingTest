/*  
 * 문제 요약
 *     - 주어진 항공권을 모두 사용하여 여행 경로를 찾아야 하며, 항상 "ICN" 공항에서 출발합니다.
 *     - 가능한 경로가 여러 개일 경우 알파벳 순서가 가장 빠른 경로를 반환해야 합니다.
 *
 * 입력
 *     - String[][] tickets : 항공권 정보 (출발지, 도착지)
 *
 * 출력
 *     - String[] : 여행 경로
 *
 * 핵심 포인트
 *     - tickets를 도착지 기준으로 사전순 정렬하여 DFS를 수행하면 첫 번째 완성 경로가 가장 빠른 경로가 됨
 *     - 방문 여부를 배열로 관리해 동일 경로 중복 사용 방지
 *     - 경로가 완성되면 즉시 종료 (가지치기)
 */

class Solution {
    
    String[][] tickets;          // 항공권 정보
    boolean[] visited;           // 티켓 사용 여부
    String[] answer;             // 최종 경로
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        this.visited = new boolean[tickets.length];
        
        // 도착지를 기준으로 사전순 정렬
        Arrays.sort(this.tickets, Comparator.comparing(i -> i[1]));
        
        List<String> path = new ArrayList<>();
        path.add("ICN"); // 출발지는 항상 ICN
        dfs("ICN", path);
        
        return answer;
    }
    
    public void dfs(String from, List<String> path) {
        // 이미 경로를 찾았으면 중단
        if (answer != null) return;
        
        // 모든 티켓을 사용하여 경로가 완성되면 결과 저장
        if (path.size() == tickets.length + 1) {
            answer = path.toArray(String[]::new);
            return;
        }
        
        // 현재 공항에서 출발할 수 있는 모든 티켓 탐색
        for (int i = 0; i < tickets.length; i++) {
            if (visited[i] || !tickets[i][0].equals(from)) continue;
            
            // 티켓 사용
            path.add(tickets[i][1]);
            visited[i] = true;
            
            // 다음 공항으로 이동
            dfs(tickets[i][1], path);
            
            // 백트래킹: 티켓 사용 취소
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}
