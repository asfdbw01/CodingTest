/*
 * 문제 요약
 *     - n개의 지역이 양방향 도로로 연결되어 있으며, 각 도로를 지나는 데 걸리는 시간은 1
 *     - sources[]: 부대원이 위치한 여러 지역
 *     - destination: 부대가 위치한 지역
 *     - 각 source에서 destination까지의 최단 복귀 시간(= 최소 거리)을 구하라
 *     - 복귀가 불가능한 경우 -1을 반환
 *
 * 입력
 *     - int n: 지역 수 (1 ≤ n ≤ 100,000)
 *     - int[][] roads: 길 정보 (2 ≤ roads.length ≤ 500,000), 각 도로는 [a, b]
 *     - int[] sources: 부대원이 위치한 지역들 (1 ≤ sources.length ≤ 500)
 *     - int destination: 부대 위치
 *
 * 출력
 *     - 각 source별 최단 복귀 시간을 담은 int 배열 (복귀 불가 시 -1)
 *
 * 핵심 포인트
 *     - roads가 [a, b] 형식의 무방향 간선 → 연결 그래프
 *     - 각 간선의 가중치가 1이므로 BFS로 최단 거리 계산 가능
 *     - destination에서 모든 노드까지의 최단 거리를 한 번에 계산하면 효율적
 *     - sources[]는 최대 500개로, BFS 1회 + O(1) 응답 방식이 효율 최적
 */

class Solution {
	
	public int[] solution(int n, int[][] roads, int[] sources, int destination) {
		// 인접 리스트 구성
		Map<Integer, List<Integer>> roadMap = new HashMap<>();
		for (int[] road : roads) {
			roadMap.computeIfAbsent(road[0], k -> new ArrayList<>()).add(road[1]);
			roadMap.computeIfAbsent(road[1], k -> new ArrayList<>()).add(road[0]);
		}
		
		// 거리 정보 저장용 map과 방문 여부 set
		Map<Integer, Integer> distanceMap = new HashMap<>();
		Set<Integer> visited = new HashSet<>();
		
		// BFS 초기화
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {destination, 0});
		distanceMap.put(destination, 0);
		visited.add(destination);
		
		// BFS 수행
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			int nextDist = cur[1] + 1;
			
			for (int nextDest : roadMap.getOrDefault(cur[0], Collections.emptyList())) {
				if (visited.add(nextDest)) {
					distanceMap.put(nextDest, nextDist);
					que.add(new int[] {nextDest, nextDist});
				}
			}
		}
		
		// sources에 대해 거리 결과 추출
		int[] answer = new int[sources.length];
		for (int i = 0; i < sources.length; i++) {
			answer[i] = distanceMap.getOrDefault(sources[i], -1);
		}
		
		return answer;
	}
}
