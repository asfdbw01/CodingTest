/*
 * 문제 요약
 *     - 하나의 트리 형태로 연결된 n개의 송전탑이 존재하며, 전선 중 하나를 끊어 두 개의 전력망으로 분할할 때
 *       두 전력망의 송전탑 개수 차이를 최소화하는 문제
 *
 * 입력
 *     - n: 송전탑 개수 (2 ≤ n ≤ 100)
 *     - wires: 전선 연결 정보 (n - 1개의 [v1, v2] 쌍, 1 ≤ v1 < v2 ≤ n)
 *
 * 출력
 *     - 하나의 전선을 끊어 두 전력망으로 나누었을 때 송전탑 개수 차이의 최소값 반환
 *
 * 핵심 포인트
 *     - 전선을 하나씩 제거하면서 두 부분 트리로 분할 → 한쪽 트리만 DFS로 개수 세면 전체 개수에서 빼서 비교 가능
 *     - 트리는 사이클이 없으므로, 직전 노드 하나만 제외하면 중복 방문 없이 DFS 가능
 */

class Solution {
	
	public int solution(int n, int[][] wires) {
		int min = Integer.MAX_VALUE;
		
		// 송전탑 간 연결 정보를 인접 리스트 형태로 저장
		@SuppressWarnings("unchecked")
		List<Integer>[] wireMap = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) wireMap[i] = new ArrayList<>();
		for (int[] wire : wires) {
			wireMap[wire[0]].add(wire[1]);
			wireMap[wire[1]].add(wire[0]);
		}
		
		// 모든 전선을 하나씩 끊어보며 두 트리로 나눔
		for (int[] wire : wires) {
			// wire[0] - wire[1] 간선을 끊고 한쪽 트리의 크기를 DFS로 계산
			int count = dfs(wire[0], wireMap, wire[1]);

			// 나머지 트리는 전체에서 뺀 값이므로 두 트리 차이 계산
			min = Math.min(min, Math.abs(count - (n - count)));
		}
		
		return min;
	}
	
	// DFS를 통해 하나의 연결 컴포넌트에 포함된 송전탑 개수 반환
	public int dfs(int node, List<Integer>[] wireMap, int visited) {
		int count = 1;
		
		// 연결된 노드 중에서 직전 노드(visited)를 제외하고 재귀 탐색
		for (int nextNode : wireMap[node]) {
			if (nextNode == visited) continue;
			count += dfs(nextNode, wireMap, node);
		}
		
		return count;
	}
	
}
