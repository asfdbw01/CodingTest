/*  
 * 문제 요약  
 *     - 컴퓨터들 간의 연결 정보가 주어졌을 때, 서로 연결된 네트워크의 개수를 구하는 문제  
 *
 * 입력  
 *     - n: 컴퓨터의 개수 (1 ≤ n ≤ 200)  
 *     - computers: 연결 정보 (computers[i][j] == 1이면 i와 j는 연결됨)  
 *
 * 출력  
 *     - 네트워크(연결 요소)의 개수  
 *
 * 핵심 포인트  
 *     - 연결 요소의 개수를 찾는 전형적인 그래프 탐색 문제  
 *     - 자기 자신은 항상 연결되어 있음 (computers[i][i] == 1)  
 *     - 간접 연결도 같은 네트워크로 간주됨   
 */

class Solution {
	
	int n;
	int[][] computers;
	boolean[] visited;
	
	public int solution(int n, int[][] computers) {
		this.n = n;
		this.computers = computers;
		this.visited = new boolean[n];
		
		int count = 0;
		for (int i = 0; i < n; i++) {
			// 방문하지 않은 노드를 시작점으로 DFS 수행
			if (!visited[i]) {
				dfs(i);
				count++; // 하나의 네트워크 탐색 완료
			}
		}
		
		return count;
	}
	
	// DFS를 통해 연결된 모든 컴퓨터 방문 처리
	public void dfs(int node) {
		visited[node] = true;
		
		for (int i = 0; i < n; i++) {
			// 연결되어 있고 아직 방문하지 않은 경우 재귀 탐색
			if (computers[node][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
	}
}
