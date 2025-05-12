package lvl2.p250136;

import java.util.*;

public class P250136 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] land;
		
		land = new int[][] {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
		System.out.println("결과: " + sol.solution(land) + ", 기댓값: 9");
		
		land = new int[][] {{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}};
		System.out.println("결과: " + sol.solution(land) + ", 기댓값: 16");
	}
}

/*
 * 문제 요약
 *   - 0과 1로 구성된 n×m 크기의 격자에서 1은 석유가 있는 땅을 의미한다.
 *   - 상하좌우로 연결된 1은 하나의 석유 덩어리로 본다.
 *   - 시추관을 수직(열 하나)으로 설치할 수 있고, 이 열이 통과하는 모든 석유 덩어리의 전체 크기를 석유량으로 얻는다.
 *   - 가장 많은 석유를 얻을 수 있는 시추관 열의 석유량을 구한다.
 *
 * 입력
 *   - land (int[][]): n x m 2차원 배열 (1 ≤ n, m ≤ 500), 각 값은 0 또는 1
 *
 * 출력
 *   - int: 시추관 하나를 설치해 얻을 수 있는 최대 석유량
 *
 * 핵심 포인트
 *   - 각 석유 덩어리를 BFS로 탐색하여 덩어리 전체 크기와 열 범위를 기록
 *   - 덩어리마다 어떤 열에 걸치는지 추적하여 해당 열의 석유량에 누적
 *   - 중복 방문 방지를 위한 visited 배열 사용
 */

class Solution {
    // 상하좌우 방향 벡터
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int solution(int[][] land) {
        int answer = 0;

        // 열마다 얻을 수 있는 석유량을 누적
        int[] oils = findOil(land);

        for (int oil : oils) {
            answer = Math.max(answer, oil);
        }

        return answer;
    }

    // 석유 덩어리를 BFS로 찾아 각 열마다 얻을 수 있는 석유량을 누적
    public int[] findOil(int[][] land) {
        int landRows = land.length;
        int landCols = land[0].length;
        int[] oils = new int[landCols];

        // 방문 여부 체크
        boolean[][] visited = new boolean[landRows][landCols];

        for (int i = 0; i < landRows; i++) {
            for (int j = 0; j < landCols; j++) {
                // 석유 덩어리 시작점인 경우
                if (land[i][j] == 1 && !visited[i][j]) {
                    Queue<int[]> taskQue = new LinkedList<>();
                    int minCol = j, maxCol = j, oilCount = 0;

                    taskQue.offer(new int[] {i, j});

                    while (!taskQue.isEmpty()) {
                        int[] loc = taskQue.poll();
                        int row = loc[0];
                        int col = loc[1];

                        // 이미 방문했으면 건너뜀
                        if (visited[row][col]) continue;
                        visited[row][col] = true;

                        // 열 범위 추적 및 석유 개수 누적
                        minCol = Math.min(minCol, col);
                        maxCol = Math.max(maxCol, col);
                        oilCount++;

                        // 상하좌우 인접 위치 탐색
                        for (int[] dir : DIRS) {
                            int nr = row + dir[0];
                            int nc = col + dir[1];

                            if (0 <= nr && nr < landRows &&
                                0 <= nc && nc < landCols &&
                                land[nr][nc] == 1 && !visited[nr][nc]) {
                                taskQue.offer(new int[] {nr, nc});
                            }
                        }
                    }

                    // 덩어리가 포함된 모든 열에 석유량 누적
                    for (int k = minCol; k <= maxCol; k++) {
                        oils[k] += oilCount;
                    }
                }
            }
        }

        return oils;
    }
}
