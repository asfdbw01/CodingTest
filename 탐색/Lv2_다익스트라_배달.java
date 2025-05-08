/**
 * 🚚 음식 배달 (다익스트라 알고리즘)
 * 
 * - 마을 수 N개 (1 ~ N)
 * - 각 마을은 양방향 도로로 연결되어 있음
 * - 도로 정보: [마을1, 마을2, 걸리는 시간]
 * - 1번 마을에서 출발해 K시간 이하로 도달 가능한 마을 개수 반환
 * 
 * ✅ 조건 요약
 * - N ≤ 50, 도로 ≤ 2000개, 시간 ≤ 10,000
 * - 중복 도로 존재 가능 (→ 최소 시간만 고려)
 * 
 * ✅ 핵심 로직
 * - 1번 마을을 시작점으로 다익스트라 알고리즘 사용
 * - 거리 배열에서 K 이하인 마을의 개수 세기
 */


import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int n = N; // 노드 수
        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0;i<road.length;i++){
            int from = road[i][0] - 1;
            int to = road[i][1] - 1;
            int time = road[i][2];

            graph.get(from).add(new Node(to, time)); //단방향
            graph.get(to).add(new Node(from, time)); //양방향
        }
        int[] dist = dijkstra(graph, 0);
        Set<Integer>set = new HashSet<>();
        for (int i = 0; i < dist.length; i++) {
            if(dist[i]<=K)set.add(i);
        }
        answer = set.size();
        return answer;
    }
    
    static class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return this.distance - other.distance; // 거리 짧은 순으로 정렬
        }
    }
    
    public static int[] dijkstra(List<List<Node>> graph, int start) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int curNode = current.index;
            int curDist = current.distance;

            if (curDist > dist[curNode]) continue; // 이미 최적 경로로 방문한 경우 무시

            for (Node next : graph.get(curNode)) {
                int cost = dist[curNode] + next.distance;
                if (cost < dist[next.index]) {
                    dist[next.index] = cost;
                    pq.add(new Node(next.index, cost));
                }
            }
        }

        return dist;
    }
    
    
}
