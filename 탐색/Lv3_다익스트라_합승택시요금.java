/*  
 * 문제 요약  
 *     - 출발지점 s에서 두 사람이 합승하거나 따로 이동해 각자의 목적지 a, b까지 이동할 때의 최소 택시요금을 구하는 문제  
 *
 * 입력  
 *     - n: 지점 개수 (1~200)  
 *     - s: 출발지점 번호  
 *     - a: A의 도착지점 번호  
 *     - b: B의 도착지점 번호  
 *     - fares: 지점 간 예상 요금을 나타내는 2차원 배열 [c, d, f]  
 *
 * 출력  
 *     - 두 사람이 s에서 출발하여 각자의 목적지까지 가는 최소 택시요금  
 *
 * 핵심 포인트  
 *     - 합승은 중간 지점 k에서 분기된다고 가정하고, 모든 k에 대해 s→k, k→a, k→b 비용 합을 비교  
 *     - s, a, b를 각각 시작점으로 다익스트라를 수행하거나, 필요한 도착지에 도달하면 탐색 종료시켜 최적화  
 *     - 우선순위 큐를 사용하여 최단 경로를 찾고, 불필요한 연산 줄이기 위해 거리 조건 추가  
 */

class Solution {
	
	int n;
	
	public int solution(int n, int s, int a, int b, int[][] fares) {
		this.n = n;
		
		// 0~n번 노드를 모두 포함한 Set 구성 (거리 계산용)
		Set<Integer> nodeSet = IntStream.rangeClosed(0, n).boxed().collect(Collectors.toSet());
		
		// 인접 리스트 생성
		Map<Integer, List<int[]>> fareMap = new HashMap<>();
		for (int[] fare : fares) {
			int node1 = fare[0], node2 = fare[1], f = fare[2];
			fareMap.computeIfAbsent(node1, k -> new ArrayList<>()).add(new int[] {node2, f});
			fareMap.computeIfAbsent(node2, k -> new ArrayList<>()).add(new int[] {node1, f});
		}
		
		// 출발점 s에서 모든 노드까지의 최단 거리
		int[] distFromS = dijkstra(s, nodeSet, fareMap);
		
		// 합승 없이 따로 가는 경우의 초기 비용
		int min = distFromS[a] + distFromS[b];
		
		// 중간 분기점 k를 1~n까지 모두 탐색
		for (int node = 1; node <= n; node++) {
			if (node == s || !fareMap.containsKey(node)) continue;
			
			// k → a, k → b 최단 거리
			int[] distFromNode = dijkstra(node, new HashSet<>(Set.of(a, b)), fareMap);
			
			// s → k + k → a + k → b 총합과 최소 비용 비교
			int distance = distFromS[node] + distFromNode[a] + distFromNode[b];
			min = Math.min(min, distance);
		}
		
		return min;
	}
	
	public int[] dijkstra(int s, Set<Integer> nodeSet, Map<Integer, List<int[]>> fareMap) {
		int[] distFromNode = new int[n + 1];
		Arrays.fill(distFromNode, Integer.MAX_VALUE);
		distFromNode[s] = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);  // 거리 기준 최소 힙
		pq.add(new int[] {s, 0});
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0], distance = cur[1];
			
			// 이미 더 짧은 거리로 방문했다면 스킵
			if (distance > distFromNode[node]) continue;
			
			// 목적지에 도달하면 set에서 제거, 모두 방문했으면 종료
			if (nodeSet.contains(node)) {
				nodeSet.remove(node);
				if (nodeSet.isEmpty()) break;
			}
			
			// 인접 노드들 탐색
			for (int[] next : fareMap.get(node)) {
				int nextNode = next[0], nextDist = distance + next[1];
				
				if (nextDist < distFromNode[nextNode]) {
					distFromNode[nextNode] = nextDist;
					pq.add(new int[] {nextNode, nextDist});
				}
			}
		}
		
		return distFromNode;
	}
}
