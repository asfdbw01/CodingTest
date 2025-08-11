// 문제 요약
// - 선수는 1..n 번호. results의 [A,B]는 A가 B를 이겼음을 의미(단방향, 모순 없음).
// - 정확히 순위를 매길 수 있는 선수의 수를 구하라.
//   기준: i에 대해 (i가 이길 수 있는 사람 수 + i가 질 수 있는 사람 수) == n-1 이면 i의 순위 확정.

// 제약
// - 1 ≤ n ≤ 100
// - 1 ≤ results.length ≤ 4500
// - 전이 성립: A>B, B>C ⇒ A>C

// 접근 1) 그래프 + 역그래프 + BFS (권장)
// - g[u] : u가 이기는 사람들로 가는 간선(u→v)
// - rg[v]: v가 지는(=v를 이긴) 사람들로 가는 역간선(v→u)
// - 각 i에 대해:
//   wins  = BFS(i, g)   // i에서 도달 가능한 정점 수
//   losses= BFS(i, rg)  // i로 도달 가능한 정점 수(= i를 이길 수 있는 사람 수)
//   if (wins + losses == n-1) answer++
// - 시간복잡도: O(n*(n+m))  (n≤100이므로 충분)

// 접근 2) 플로이드-워셜(전이 폐쇄)
// - win[i][j]=true 초기화 후, k,i,j 삼중루프로 전이 채움
// - i에 대해 ∑(win[i][j] || win[j][i]) == n-1 이면 확정
// - 시간복잡도: O(n^3)

// 구현 주의
// - 입력은 1-based이므로 0-based로 변환하거나 리스트를 n+1개 생성
// - BFS에서 시작 정점 자신은 카운트에 포함하지 않기
// - 결과 합 비교는 n이 아니라 (n-1)

// 예시
// n=5, results=[[4,3],[4,2],[3,2],[1,2],[2,5]] ⇒ 정답 2


import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> rgraph = new ArrayList<>();
        makeGraph( n, results,graph,rgraph);
        for(int i=0;i<n;i++){
            int win = bfsCount(i,n,graph);
            int lose = bfsCount(i,n,rgraph);
            System.out.println(win+lose);
            if(win+lose+1==n)answer++;
        }
        return answer;
    }
    private int bfsCount(int start,int n,List<List<Integer>> graph){
        boolean[] visited = new boolean[n];
        int cnt=0;
        Queue<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.add(start);
        
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int nx : graph.get(cur)){
                if(visited[nx] == false){
                    cnt++;
                    visited[nx]=true;
                    q.add(nx);
                }
            }
        }
        return cnt;
    }
    
    private void makeGraph(int n, int[][] results,List<List<Integer>> graph,List<List<Integer>> rgraph) {
        for(int i=0; i<n;i++){
            graph.add(new ArrayList<>());
            rgraph.add(new ArrayList<>());
        }
        
        for(int[] edge : results){
            int u = edge[0]-1;
            int v = edge[1]-1;
            
            graph.get(u).add(v);
            rgraph.get(v).add(u);
        }
    }
}
