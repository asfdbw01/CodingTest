/*
문제 요약 - 물류센터 로봇 충돌 위험 분석

1. 센터는 2차원 격자공간에 있으며, n개의 포인트가 존재한다.
   - 각 포인트는 (r, c) 좌표로 고유하게 주어짐
   - 포인트 번호는 1번부터 시작

2. 로봇은 여러 대 존재하며, 각 로봇마다 고정된 경로(routes[i])가 있음
   - 경로는 여러 포인트 번호로 구성됨
   - 로봇은 경로의 포인트를 순서대로 방문함

3. 이동 규칙:
   - 모든 로봇은 0초에 동시에 출발
   - 1초에 한 칸 이동 (r 또는 c가 1만큼 변함)
   - 최단 거리로 이동하며,
     같은 거리인 경우 r좌표 먼저 움직이고, 그 다음 c좌표 이동

4. 목적지 도착 후 로봇은 물류 센터를 벗어나며 이후는 고려하지 않음

5. 위험 상황:
   - 같은 시간에 같은 좌표에 2대 이상 로봇이 위치 → 위험 1회 발생
   - 여러 좌표에서 동시에 위험이 발생하면 각 위치별로 1회씩 카운트

6. 목표:
   - 전체 로봇이 경로를 마칠 때까지 위험 상황이 총 몇 번 발생했는지 반환

입출력 예 요약:
- 로봇들이 경로를 이동하면서 충돌 가능성이 있는 시간/위치를 모두 확인해
  위험 횟수를 정확히 계산해야 함
*/



import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        //구현 순서
        // 포인트 번호 > 좌표 매핑
        Map<Integer, int[]> pointMap = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            pointMap.put(i + 1, points[i]);
        }
        // 각 로봇별 경로 계산
        List<List<int[]>> robotPaths = new ArrayList<>();
        int maxTime = 0;

        for (int[] route : routes) {
            List<int[]> path = new ArrayList<>();
            for (int i = 0; i < route.length - 1; i++) {
                int[] start = pointMap.get(route[i]);
                int[] end = pointMap.get(route[i + 1]);

                List<int[]> segment = generatePath(start, end);
                if (i > 0) segment.remove(0); // 중복 방지
                path.addAll(segment);
            }
            robotPaths.add(path);
            maxTime = Math.max(maxTime, path.size());
        }
        // 시간별 시뮬
         

        for (int t = 0; t < maxTime; t++) {
            Map<String, Integer> posMap = new HashMap<>();
            //로봇의 t초 위치
            for (List<int[]> robotPath : robotPaths) {
                if (t < robotPath.size()) {
                    int[] pos = robotPath.get(t);
                    String key = pos[0] + "," + pos[1];
                    posMap.put(key, posMap.getOrDefault(key, 0) + 1);
                }
            }
            // 충돌 여부
            for (int count : posMap.values()) {
                            if (count >= 2) {
                                answer += 1;
                    }
                }
            }
        
        

        // 반환
        
        
        return answer;
    }
    // 최단 경로 생성 메서드
    private List<int[]> generatePath(int[] start, int[] end) {
    List<int[]> path = new ArrayList<>();
    int r = start[0], c = start[1];
    path.add(new int[]{r, c});

    while (r != end[0]) {
        r += (r < end[0]) ? 1 : -1;
        path.add(new int[]{r, c});
    }

    while (c != end[1]) {
        c += (c < end[1]) ? 1 : -1;
        path.add(new int[]{r, c});
    }

        return path;
    }
}
