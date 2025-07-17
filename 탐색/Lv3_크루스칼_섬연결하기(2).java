/*  
 * 문제 요약
 *     - n개의 섬을 최소 비용으로 모두 연결하는 문제로, 일부 섬들 간의 다리 건설 비용이 주어진다.
 *     - 여러 다리를 거쳐서 도달할 수 있으면 연결된 것으로 간주된다.
 *
 * 입력  
 *     - n: 섬의 개수 (1 ≤ n ≤ 100)
 *     - costs: 각 다리의 정보 [섬1, 섬2, 비용]을 담은 2차원 배열 (길이 ≤ n(n-1)/2)
 *
 * 출력  
 *     - 모든 섬을 연결하는 데 필요한 최소 비용 (int)
 *
 * 핵심 포인트  
 *     - 최소 신장 트리(MST)를 구성하는 문제
 *     - 간선의 비용이 주어졌고, 일부만 연결 가능하므로 크루스칼 알고리즘 사용
 *     - 싸이클 방지를 위해 유니온 파인드 적용 (경로 압축 포함)
 */

class Solution {
	
	int[] parents;

	public int solution(int n, int[][] costs) {
		// 각 노드를 자기 자신을 부모로 초기화
		this.parents = IntStream.range(0, n).toArray();

		// 비용 기준으로 정렬된 우선순위 큐 구성
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing((int[] i) -> i[2]));
		for (int[] cost : costs) pq.add(cost);

		int totalCost = 0, edgeCount = 0;

		// 최소 신장 트리 구성: n - 1개의 간선이 연결되면 종료
		while (!pq.isEmpty() && edgeCount < n - 1) {
			int[] cur = pq.poll();
			int a = cur[0], b = cur[1], cost = cur[2];

			// 서로 다른 집합이면 연결 (싸이클 방지)
			if (find(a) != find(b)) {
				union(a, b);
				totalCost += cost;
				edgeCount++;
			}
		}

		return totalCost;
	}

	// 경로 압축 포함한 find
	public int find(int node) {
		if (parents[node] == node) return node;
		return parents[node] = find(parents[node]);
	}

	// 두 루트 노드 병합
	public void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA != rootB) {
			parents[rootA] = rootB;
		}
	}
}
