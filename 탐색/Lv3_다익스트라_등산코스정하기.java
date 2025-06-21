/*
 * 문제 요약
 *     - n개의 지점으로 구성된 산에서 출입구 → 산봉우리 → 출입구 코스를 정할 때,
 *       intensity(휴식 없이 이동한 가장 긴 시간)를 최소로 하는 경로를 찾는다.
 *     - 경로는 출입구에서 시작해 산봉우리 하나만 방문하고 다시 같은 출입구로 돌아와야 한다.
 *     - 각 등산로는 양방향이며, 쉼터는 여러 번 지나도 되지만 출입구/산봉우리는 한 번만 방문 가능
 * 
 * 입력
 *     - n (정점 개수, 2 ≤ n ≤ 50,000)
 *     - paths (등산로 정보, 길이 m: n-1 ≤ m ≤ 200,000), 각 원소: [i, j, w]
 *     - gates (출입구 목록), 길이 ≤ n
 *     - summits (산봉우리 목록), 길이 ≤ n
 * 
 * 출력
 *     - [산봉우리 번호, 최소 intensity] 형태의 정수 배열
 *     - intensity가 같다면 산봉우리 번호가 더 작은 경로 우선
 * 
 * 핵심 포인트
 *     - 등산로의 가중치가 존재하므로 일반 BFS로는 불가능
 *     - 출입구를 모두 시작점으로 하여 다익스트라 수행
 *     - intensity = 경로 내 최대 간선 가중치
 *     - 각 지점까지의 최소 intensity를 기록하며 pruning
 *     - 산봉우리에 도착한 후에는 탐색을 더 진행하지 않음
 */

class Solution {

    private final int INF = 10_000_001;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        // 1. 그래프 구성 (양방향)
        Map<Integer, List<int[]>> nodeMap = new HashMap<>();
        for (int[] path : paths) {
            int n1 = path[0], n2 = path[1], w = path[2];
            nodeMap.computeIfAbsent(n1, k -> new ArrayList<>()).add(new int[] {n2, w});
            nodeMap.computeIfAbsent(n2, k -> new ArrayList<>()).add(new int[] {n1, w});
        }

        // 2. 산봉우리 Set 구성 (탐색 중단 용도)
        Set<Integer> summitSet = Arrays.stream(summits).boxed().collect(Collectors.toSet());

        // 3. 다익스트라 초기화 (PQ는 intensity 기준 정렬)
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int[] minIntensity = new int[n + 1];
        Arrays.fill(minIntensity, INF);

        // 출입구를 모두 시작점으로 추가
        for (int gate : gates) {
            pq.add(new int[] {gate, 0});
            minIntensity[gate] = 0;
        }

        // 4. 다익스트라 탐색
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int from = cur[0], intensity = cur[1];

            if (intensity > minIntensity[from]) continue;          	// 중복 제거
            if (summitSet.contains(from)) break;                	// 산봉우리에 도달하면 탐색 종료

            for (int[] path : nodeMap.getOrDefault(from, Collections.emptyList())) {
                int to = path[0], w = path[1];
                int nextIntensity = Math.max(intensity, w);

                if (nextIntensity < minIntensity[to]) {
                    minIntensity[to] = nextIntensity;
                    pq.add(new int[] {to, nextIntensity});
                }
            }
        }

        // 5. 결과 산봉우리 중 최소 intensity 선택
        int[] min = {INF, INF}; // [산봉우리 번호, intensity]
        Arrays.sort(summits);   // 번호가 낮은 것 우선

        for (int summit : summits) {
            if (minIntensity[summit] < min[1]) {
                min[0] = summit;
                min[1] = minIntensity[summit];
            }
        }

        return min;
    }
}
