/*
문제 요약
-----------
📌 1번 노드에서 가장 멀리 있는 노드의 개수를 구하라.
   (가장 멀리 = 최단 경로의 간선 개수가 가장 많은 노드들)

입력
-----
n: 노드의 개수 (2 ≤ n ≤ 20,000)
vertex: 간선 정보가 담긴 2차원 배열 (양방향, 길이 ≤ 50,000)

출력
-----
1번 노드로부터 가장 멀리 떨어진 노드의 개수

조건
-----
- 노드는 1번부터 n번까지 번호가 매겨져 있음
- 간선은 무방향
- 최단 거리(간선 개수)가 가장 큰 노드들을 찾고, 그 개수를 반환
*/


import java.util.*;

class Solution {
     public int solution(int n, int[][] edge) {
        List<List<Integer>> graph = makeGraph(edge);
        int[] distance = bfs(graph, 1);
        return countMaxDistance(distance);
    }
    
    public static List<List<Integer>> makeGraph( int[][] vertex) {
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
    
    public static int[] bfs(List<List<Integer>> graph, int start) {
        int n = graph.size() - 1;  // 노드 번호가 1부터 시작하므로
        int[] distance = new int[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        distance[start] = 1;  // 방문 표시 겸 거리

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int next : graph.get(curr)) {
                if (distance[next] == 0) { // 아직 방문하지 않은 노드
                    distance[next] = distance[curr] + 1;
                    queue.add(next);
                }
            }
        }

        return distance;
    }
    
    public static int countMaxDistance(int[] distance) {
        int maxDist = 0;

        // 최대 거리 찾기
        for (int d : distance) {
            maxDist = Math.max(maxDist, d);
        }

        int count = 0;

        // 최대 거리에 해당하는 노드 수 세기
        for (int d : distance) {
            if (d == maxDist) count++;
        }

        return count;
    }


}
