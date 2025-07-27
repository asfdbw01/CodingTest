/*  
 * 문제 요약
 *     - 1번 마을에서 각 마을까지 K시간 이하로 배달 가능한 마을의 수를 구하는 문제  
 *     - 각 마을은 양방향 도로로 연결되어 있으며, 도로마다 시간(가중치)이 다름  
 *
 * 입력  
 *     - N : 마을의 수 (1 ≤ N ≤ 50)  
 *     - road : 도로 정보 배열 [a, b, c] (1 ≤ road.length ≤ 2,000, 1 ≤ c ≤ 10,000)  
 *     - K : 배달 가능 시간 (1 ≤ K ≤ 500,000)  
 *
 * 출력  
 *     - 1번 마을에서 K 이하의 시간으로 도달 가능한 마을 수  
 *
 * 핵심 포인트  
 *     - 도로는 양방향이므로 양쪽 모두 인접 리스트에 추가  
 *     - 동일한 두 마을을 연결하는 여러 도로가 존재할 수 있음  
 *     - 1번 마을에서 시작하여 최단거리 탐색 → Dijkstra 알고리즘 사용  
 *     - Integer.MAX_VALUE는 도달 불가능한 마을이므로 K 이하만 필터링  
 */

class Solution {
	
	public int solution(int N, int[][] road, int K) {
		Map<Integer, List<int[]>> map = new HashMap<>();
		
		// 인접 리스트 구성 (양방향)
		for (int[] edge : road) {
			int node1 = edge[0], node2 = edge[1], dist = edge[2];
			map.computeIfAbsent(node1, k -> new ArrayList<>()).add(new int[] {node2, dist});
			map.computeIfAbsent(node2, k -> new ArrayList<>()).add(new int[] {node1, dist});
		}
		
		// 최단 거리 배열 초기화
		int[] distance = new int[N + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[1] = 0; // 1번 마을 거리 0
		
		// 우선순위 큐(Dijkstra)
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));
		pq.add(new int[] {1, 0});
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0], dist = cur[1];
			
			// 이미 더 짧은 거리로 도달한 적 있음
			if (dist > distance[node]) continue;
			
			// 인접 노드 순회
			for (int[] next : map.get(node)) {
				int nextNode = next[0], nextDist = distance[node] + next[1];
				if (nextDist < distance[nextNode]) {
					distance[nextNode] = nextDist;
					pq.add(new int[] {nextNode, nextDist});
				}
			}
		}
		
		// K 이하 거리인 마을 개수 반환
		return (int) Arrays.stream(distance)
						   .filter(i -> i <= K)
						   .count();
	}
	
}
