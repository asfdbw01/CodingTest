/*  
 * 문제 요약
 *     - 1번 노드에서 가장 멀리 떨어진 노드의 개수를 구하는 문제
 *     - 간선의 개수가 가장 많은 최단 경로에 있는 노드들을 찾는다
 *
 * 입력
 *     - n: 노드의 개수 (2 <= n <= 20,000)
 *     - edge: 간선 정보 (1 <= 간선 개수 <= 50,000), 양방향
 *
 * 출력
 *     - 1번 노드로부터 가장 멀리 떨어진 노드의 개수
 *
 * 핵심 포인트
 *     - 모든 간선의 가중치가 동일하므로 BFS로 최단 경로 계산 가능
 *     - 다익스트라 알고리즘으로도 문제 해결 가능 (우선순위 큐 사용)
 *     - 최단 거리를 계산한 후, 최대 거리인 노드의 개수를 카운트
 */

class Solution {
	
	public int solution(int n, int[][] edge) {
		int[] distance = new int[n + 1];
		Arrays.fill(distance, n);
		distance[1] = 0;
		
		Map<Integer, Set<Integer>> dirMap = new HashMap<>();
		for (int[] e : edge) {
			dirMap.computeIfAbsent(e[0], k -> new HashSet<>()).add(e[1]);
			dirMap.computeIfAbsent(e[1], k -> new HashSet<>()).add(e[0]);
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((i, j) -> i[1] - j[1]);
		pq.add(new int[] {1, 0});
		
		// 우선순위 큐를 이용한 다익스트라 탐색
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0], dist = cur[1];
			
			// 이미 더 짧은 경로로 방문했으면 스킵
			if (dist > distance[node]) continue;
			
			for (int nextNode : dirMap.get(node)) {
				int nextDist = dist + 1;
				// 더 짧은 경로 발견 시 갱신
				if (nextDist < distance[nextNode]) {
					distance[nextNode] = nextDist;
					pq.add(new int[] {nextNode, nextDist});
				}
			}
		}
		
		// 최대 거리 구하기
		int maxDist = 0;
		for (int i = 2; i <= n; i++) {
			maxDist = Math.max(maxDist, distance[i]);
		}
		
		// 최대 거리인 노드 개수 카운트
		int count = 0;
		for (int i = 2; i <= n; i++) {
			if (distance[i] == maxDist) count++;
		}
		
		return count;
	}
	
}
