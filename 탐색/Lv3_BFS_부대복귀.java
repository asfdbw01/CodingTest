/*
sources에 강철부대가 있습니다.

각 강철부대의 최종 복귀점은 destination 입니다.

근데 당신은 지금, sources별로 destination까지의 최소거리를 구하고 계시지는 않습니까?

잘 생각해봐야합니다.

사실 도착지점은 하나입니다.

반대로 생각하면 한 번의 연산으로 클리어가 가능합니다.

destination에서 각 source 까지의 최단 경로를 구해보는건 어떠신가요?

추가적으로, visit과 해시를 사용하여 중복연산을 줄이는 좋을 것 같군요
*/

import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        List<List<Integer>> graph = makeGraph( roads);
        int[] dist = bfs( graph,destination, n);
        
        for(int i=0;i<answer.length;i++){
            answer[i] = dist[sources[i]]==Integer.MAX_VALUE?-1:dist[sources[i]];
        }
        
        return answer;
    }
    
    private List<List<Integer>> makeGraph( int[][] vertex) {
        int n = 0;
        for (int[] edge : vertex) {
            n = Math.max(n, Math.max(edge[0], edge[1]));
        }
        
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : vertex) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;
    }
    
    private int[] bfs(List<List<Integer>> graph,int destination,int n){
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[destination] = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(destination);
        
        while(!queue.isEmpty()){
            int curr = queue.poll();
            for (int next : graph.get(curr)) {
                if (dist[next] > dist[curr] + 1) {
                    dist[next] = dist[curr] + 1;
                    queue.add(next);
                }
            }
        }
        return dist;
    }
    
}
