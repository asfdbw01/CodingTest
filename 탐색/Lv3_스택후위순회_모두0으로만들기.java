/*  
 * 문제 요약  
 *     - 각 정점에 가중치가 주어진 트리에서, 인접한 두 정점을 선택해 한쪽은 +1, 다른 한쪽은 -1을 수행하는 연산을 반복하여 모든 정점의 가중치를 0으로 만들고자 함  
 *
 * 입력  
 *     - int[] a: 각 정점의 초기 가중치  
 *     - int[][] edges: 트리를 구성하는 간선 정보  
 *
 * 출력  
 *     - 모든 정점의 가중치를 0으로 만들 수 없다면 -1  
 *     - 가능하다면 최소 연산 횟수  
 *
 * 핵심 포인트  
 *     - 전체 가중치 합이 0이 아니면 절대 0으로 만들 수 없음  
 *     - 후위 순회 방식으로 리프에서 루트 방향으로 가중치를 전파해야 최소 이동  
 *     - 재귀 없이 스택을 이용해 후위 순회를 구현하고, 가중치 누적은 long[]으로 처리
 */

class Solution {

	public long solution(int[] a, int[][] edges) {

		// 전체 가중치 합이 0이 아니면 절대 0으로 만들 수 없음
		long sum = Arrays.stream(a).asLongStream().sum();
		if (sum != 0) return -1;

		// 모든 노드의 가중치가 처음부터 0이면 연산이 필요 없음
		if (Arrays.stream(a).allMatch(w -> w == 0)) return 0;

		// 트리 구조를 인접 리스트로 구성 (양방향 연결)
		Map<Integer, Set<Integer>> edgeMap = new HashMap<>();
		for (int[] edge : edges) {
			edgeMap.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
			edgeMap.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
		}

		Deque<Integer> stack = new ArrayDeque<>();
		boolean[] visited = new boolean[a.length];
		long[] weights = Arrays.stream(a).asLongStream().toArray();
		long totalMove = 0;

		stack.add(0);  // 루트 노드를 0번으로 시작

		while (!stack.isEmpty()) {
			int node = stack.peekLast();

			if (visited[node]) {
				// 후위 순회 처리 단계: 자식 노드의 가중치를 현재 노드에 누적
				weights[node] += edgeMap.get(node).stream().mapToLong(child -> weights[child]).sum();

				// 현재 노드의 이동 횟수를 누적
				totalMove += Math.abs(weights[node]);

				// 처리 완료 후 스택에서 제거
				stack.pollLast();
			} else {
				// 자식 노드를 모두 스택에 추가하고, 역방향(부모) 제거
				for (int child : edgeMap.get(node)) {
					edgeMap.get(child).remove(node);  // 부모 제거
					stack.addLast(child);             // 자식 push
				}

				// 방문 처리: 다음 번 방문 시 후위 처리로 진입
				visited[node] = true;
			}
		}

		return totalMove;
	}
}
