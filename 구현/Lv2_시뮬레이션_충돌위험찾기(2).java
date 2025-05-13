/*
 * 문제 요약
 *   - 2차원 평면상의 물류 포인트들(points)과 각 로봇의 운송 경로(routes)가 주어진다.
 *   - 모든 로봇은 0초에 동시에 출발하고, 각 로봇은 주어진 포인트를 순서대로 최단 경로로 이동한다.
 *   - 이동은 한 번에 r 또는 c 중 하나의 좌표만 1만큼 증가/감소할 수 있다.
 *   - 최단 경로가 여러 개인 경우, r좌표 이동을 먼저 수행한다.
 *   - 같은 시점에 같은 좌표에 2대 이상의 로봇이 모이면 위험 상황으로 간주하며, 그 횟수를 모두 합산하여 반환한다.

 * 입력
 *   - int[][] points: n개의 포인트의 좌표 (1-based 번호와 대응됨), 크기 n x 2
 *   - int[][] routes: 각 로봇의 경로, 크기 x x m (각 행은 포인트 번호의 순서)

 * 출력
 *   - int: 로봇이 이동 중 충돌 위험이 발생한 횟수의 총합

 * 핵심 포인트
 *   - 각 로봇의 전체 이동 경로를 deque에 시뮬레이션 형태로 구성
 *   - 매 초마다 모든 로봇의 현재 위치를 꺼내고, 같은 좌표가 2번 이상 등장하면 위험 상황으로 간주
 *   - 좌표를 String("r,c") 형태로 key화하여 위치 중복을 판별
 *   - 이동 경로는 r 우선 방향으로 한 칸씩 진행하여 정확히 최단 경로를 따름
 *   - 각 시점에서 로봇 이동 상태를 하나씩 줄이며 시뮬레이션 수행
 */

class Solution {

    // 전체 위험 상황 횟수를 계산하는 메인 함수
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        // 각 로봇의 이동 경로(좌표 리스트)를 담은 큐 리스트 생성
        List<Deque<int[]>> stepQueList = new ArrayList<>();

        // 각 로봇의 경로를 실제 좌표 이동 리스트로 변환
        for (int[] route : routes) {
            stepQueList.add(toSteps(points, route));
        }

        // 시뮬레이션을 통해 위험 상황 횟수 계산
        answer = dangerCount(stepQueList);

        return answer;
    }

    // 로봇들이 이동하며 동시에 같은 좌표에 도달한 횟수를 세는 시뮬레이션 함수
    public int dangerCount(List<Deque<int[]>> stepQueList) {
        int answer = 0;
        Map<String, Integer> robotCountMap = new HashMap<>();

        // 모든 로봇이 이동을 마칠 때까지 반복
        while (!stepQueList.isEmpty()) {
            robotCountMap.clear();

            // 반복자 사용으로 리스트 순회 중 안전하게 삭제 가능
            Iterator<Deque<int[]>> iter = stepQueList.iterator();

            while (iter.hasNext()) {
                Deque<int[]> stepQue = iter.next();
                int[] loc = stepQue.pollFirst();  // 현재 시점 위치 꺼냄

                // 좌표를 "r,c" 문자열로 변환해 map key로 사용
                String key = loc[0] + "," + loc[1];
                robotCountMap.merge(key, 1, Integer::sum);  // 해당 좌표에 로봇 수 누적

                if (stepQue.isEmpty()) iter.remove(); // 로봇이 이동을 마쳤으면 리스트에서 제거
            }

            // 위험 상황 판단: 같은 좌표에 2대 이상 존재한 경우
            for (int count : robotCountMap.values()) {
                if (count >= 2) answer++;
            }
        }

        return answer;
    }

    // 특정 로봇의 포인트 경로를 한 칸씩 이동하는 스텝 좌표 큐로 변환
    public Deque<int[]> toSteps(int[][] points, int[] route) {
        Deque<int[]> stepQue = new LinkedList<>();
        Queue<int[]> routeQue = new LinkedList<>();

        // 포인트 번호를 실제 좌표로 변환하여 큐에 넣음
        for (int node : route) routeQue.offer(points[node - 1]);

        // 첫 시작 좌표 삽입
        stepQue.offerLast(routeQue.poll());

        // 목표 지점 큐가 빌 때까지 최단 경로로 이동
        while (!routeQue.isEmpty()) {
            int[] nowLoc = stepQue.peekLast();     // 현재 좌표
            int[] destination = routeQue.peek();   // 목표 좌표

            // r좌표가 다르면 먼저 r방향 이동, r좌표가 같으면 c방향 이동
            if (nowLoc[0] > destination[0]) stepQue.offerLast(new int[]{nowLoc[0] - 1, nowLoc[1]});
            else if (nowLoc[0] < destination[0]) stepQue.offerLast(new int[]{nowLoc[0] + 1, nowLoc[1]});
            else if (nowLoc[1] > destination[1]) stepQue.offerLast(new int[]{nowLoc[0], nowLoc[1] - 1});
            else if (nowLoc[1] < destination[1]) stepQue.offerLast(new int[]{nowLoc[0], nowLoc[1] + 1});
            // 목적지에 도달한 경우 다음 포인트로
            else routeQue.poll();
        }

        return stepQue;
    }
}
