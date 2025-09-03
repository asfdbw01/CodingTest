/*
문제: 양과 늑대 (이진 트리)
----------------------------------------
- 각 노드에 0=양, 1=늑대가 하나씩 있음. 루트(0)는 항상 양.
- 루트에서 시작해 노드들을 방문하며 동물을 데리고 다님.
- 언제든 늑대 수 >= 양 수가 되는 순간, 지금까지 모은 양이 모두 잡아먹혀 그 분기는 실패.
- 부모를 이미 방문했다면 그 자식 노드는 “언제든” 방문 가능(형제/삼촌으로 점프 가능).
- 목표: 잡아먹히지 않도록 하면서 모을 수 있는 양의 최대 개수.

입력
- info[i] ∈ {0(양), 1(늑대)}, 길이 N (2 ≤ N ≤ 17), info[0] = 0.
- edges: [parent, child] 쌍들 (트리는 이진 구조, 중복 없음).

출력
- 조건을 만족하며 방문 가능한 순서를 잘 골랐을 때의 최대 양 수.

핵심 규칙/함정
- 방문 순서는 경로 DFS가 아니라 “현재 갈 수 있는 후보 집합” 중 하나를 고르는 상태탐색.
- 어떤 노드를 방문하면 즉시 양/늑대 수를 갱신하고,
  그 노드의 자식들을 “후보 집합”에 추가하여 다음 선택을 이어감.
- 늑대 수가 양 수에 도달/초과하면 해당 분기를 즉시 중단(가지치기).

*/

import java.util.*; 
class Solution { 
    public int solution(int[] info, int[][] edges) { 
        int answer = 0; 
        final int n = info.length; 
        //자식 리스트
        List<Integer>[] child = new ArrayList[n]; 
        for(int i=0;i<n;i++){child[i] = new ArrayList<>();} 
        for(int[] e : edges)child[e[0]].add(e[1]); 
        
        // 시작 후보 
        List<Integer> avail = new ArrayList<>();
        avail.add(0);
        
        answer = dfs(0, 0, avail,child, info);
        return answer; 
    } 
    
     private int dfs(int sheep, int wolf, List<Integer> avail, List<Integer>[] child, int[] info) {
         int best = sheep;
         
         for (int i = 0; i < avail.size(); i++){
             int v = avail.get(i);
             int ns = sheep + (info[v] == 0 ? 1 : 0);
             int nw = wolf  + (info[v] != 0 ? 1 : 0);
             if (nw >= ns) continue; 
             
             ArrayList<Integer> next = new ArrayList<>(avail.size() - 1 + child[v].size());
             for (int j = 0; j < avail.size(); j++) if (j != i) next.add(avail.get(j));
             next.addAll(child[v]);
             
             best = Math.max(best, dfs(ns, nw, next, child, info));
         }
        return best;
    } 
}
