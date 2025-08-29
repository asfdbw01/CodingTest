/*
"무지"는 "어피치"와 귀가 방향이 비슷하여 택시 합승을 적절히 이용하면
 택시요금을 얼마나 아낄 수 있을 지 계산해 보고 
"어피치"에게 합승을 제안해 보려고 합니다.


두 사람이 모두 귀가하는 데 소요되는 예상 최저 택시요금이 얼마인 지 계산하려고 합니다.


지점의 개수 n, 출발지점을 나타내는 s, A의 도착지점을 나타내는 a, 
B의 도착지점을 나타내는 b, 
지점 사이의 예상 택시요금을 나타내는 fares가 매개변수로 주어집니다.
 이때, A, B 두 사람이 s에서 출발해서 각각의 도착 지점까지 택시를 타고 간다고 가정할 때, 
최저 예상 택시요금을 계산해서 return 하도록 solution 함수를 완성해 주세요.
만약, 아예 합승을 하지 않고 각자 이동하는 경우의 예상 택시요금이 더 낮다면,
 합승을 하지 않아도 됩니다.

[제한사항]
지점갯수 n은 3 이상 200 이하인 자연수입니다.
지점 s, a, b는 1 이상 n 이하인 자연수이며, 각기 서로 다른 값입니다.
즉, 출발지점, A의 도착지점, B의 도착지점은 서로 겹치지 않습니다.
fares는 2차원 정수 배열입니다.
fares 배열의 크기는 2 이상 n x (n-1) / 2 이하입니다.
예를들어, n = 6이라면 fares 배열의 크기는 2 이상 15 이하입니다. (6 x 5 / 2 = 15)
fares 배열의 각 행은 [c, d, f] 형태입니다.
c지점과 d지점 사이의 예상 택시요금이 f원이라는 뜻입니다.
지점 c, d는 1 이상 n 이하인 자연수이며, 각기 서로 다른 값입니다.
요금 f는 1 이상 100,000 이하인 자연수입니다.
fares 배열에 두 지점 간 예상 택시요금은 1개만 주어집니다. 즉, [c, d, f]가 있다면 [d, c, f]는 주어지지 않습니다.
출발지점 s에서 도착지점 a와 b로 가는 경로가 존재하는 경우만 입력으로 주어집니다.

*/

import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
        int index;
        int cost;
        Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost); // 비용 오름차순
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        
        for(int i=0;i<fares.length;i++){
            graph.get(fares[i][0]).add(new Node(fares[i][1], fares[i][2]));
            graph.get(fares[i][1]).add(new Node(fares[i][0], fares[i][2]));
        }
        
        int[] sDist = dijkstra(graph, s); // s번 노드에서 시작
        int[] aDist = dijkstra(graph, a);
        int[] bDist = dijkstra(graph, b);
        for (int i = 0; i < sDist.length; i++) {
            System.out.println(s+"번 노드 -> " + i + "번 노드 최단거리: " + sDist[i]);
        }
        //s~i번 + i~a + i~b 더한 값이 가장 낮은 경우수 찾기
        for(int i = 0; i < sDist.length; i++){
            int dist = sDist[i] + aDist[i] + bDist[i];
            answer = Math.min(answer,dist);
        }
        return answer;
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
            int curDist = current.cost;

            if (curDist > dist[curNode]) continue; // 이미 최적 경로로 방문한 경우 무시

            for (Node next : graph.get(curNode)) {
                int cost = dist[curNode] + next.cost;
                if (cost < dist[next.index]) {
                    dist[next.index] = cost;
                    pq.add(new Node(next.index, cost));
                }
            }
        }

        return dist;
    }
    
}