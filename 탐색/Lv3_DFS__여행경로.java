/*
문제 요약
-----------
📌 주어진 항공권을 모두 사용해 여행 경로를 찾는다.
📌 항상 "ICN" 공항에서 출발한다.
📌 가능한 경로가 여러 개라면, 알파벳 순서가 가장 앞서는 경로를 선택한다.

입력
-----
- tickets: String[][] 형태의 항공권 정보
  (각 항공권은 [출발지, 도착지])

출력
-----
- String[] 형태의 여행 경로

조건
-----
- 모든 항공권은 정확히 한 번씩 사용해야 한다.
- 가능한 경로가 2개 이상이면 사전순으로 가장 빠른 것을 선택.
- 공항 이름은 모두 3글자 대문자.

예시
-----
tickets = [ ["ICN", "JFK"], ["HND", "IAD"], ["JFK", "HND"] ]
=> return [ "ICN", "JFK", "HND", "IAD" ]
*/
import java.util.*;

class Solution {
    
    LinkedList<String> route = new LinkedList<>();
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        graph= makeGraph( tickets);
        dfs("ICN");
        answer =route.toArray(new String[0]);
        return answer;
    }
    
    private Map<String, PriorityQueue<String>> makeGraph(String[][] tickets){
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (String[] ticket : tickets) {
            graph.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
        }
        return graph;
    }
    
    public void dfs(String airport) {
        PriorityQueue<String> nextAirports = graph.get(airport);

        while (nextAirports != null && !nextAirports.isEmpty()) {
            dfs(nextAirports.poll());
        }

        route.addFirst(airport); // 역순으로 쌓임
    }
}
