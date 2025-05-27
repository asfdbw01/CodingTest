/*
 * 문제 요약:
 * - Ax + By + C = 0 형태의 직선 n개가 주어질 때,
 *   이들 직선의 정수 좌표 교점에만 '*' 표시.
 * - 모든 '*'을 포함하는 최소 사각형 영역만 출력.

 * 입력:
 * - line[i] = [A, B, C] (직선 방정식 Ax + By + C = 0)
 * - 직선은 2개 이상 1,000개 이하
 * - A, B, C는 -100_000 이상 100_000 이하 정수

 * 출력:
 * - '*'와 '.'으로 구성된 String 배열
 * - '*'은 정수 교점, '.'은 빈 공간
 * - 배열은 모든 별을 포함하는 최소 크기만 출력

 * 교점 공식:
 * - 두 직선 (A1x + B1y + C1 = 0), (A2x + B2y + C2 = 0)
 * - 분모 = A1*B2 - A2*B1 ≠ 0 (평행 또는 중복 제거됨)
 * - x = (B1*C2 - B2*C1) / 분모
 * - y = (C1*A2 - C2*A1) / 분모
 * - x, y가 정수인 경우만 '*'로 표시

 * 구현 포인트:
 * 1. 모든 직선쌍에 대해 정수 교점 찾기
 * 2. 모든 교점의 minX, minY, maxX, maxY 계산
 * 3. 해당 영역만큼 char[][] 생성 후, 교점 '*' 표시
 * 4. y 좌표는 위쪽부터 출력해야 하므로 배열은 상단부터 y가 큰 값
 */


import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        List<long[]> points = new ArrayList<>();
        long minX = Long.MAX_VALUE, minY = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE, maxY = Long.MIN_VALUE;

        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                long A = line[i][0], B = line[i][1], C = line[i][2];
                long D = line[j][0], E = line[j][1], F = line[j][2];

                long denominator = A * E - B * D;
                if (denominator == 0) continue;

                long xNumerator = B * F - E * C;
                long yNumerator = C * D - A * F;

                if (xNumerator % denominator != 0 || yNumerator % denominator != 0) continue;

                long x = xNumerator / denominator;
                long y = yNumerator / denominator;

                points.add(new long[]{x, y});
                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
            }
        }

        int width = (int)(maxX - minX + 1);
        int height = (int)(maxY - minY + 1);
        char[][] board = new char[height][width];
        for (char[] row : board) Arrays.fill(row, '.');

        for (long[] point : points) {
            int x = (int)(point[0] - minX);
            int y = (int)(maxY - point[1]); // y축 반전
            board[y][x] = '*';
        }

        String[] answer = new String[height];
        for (int i = 0; i < height; i++) {
            answer[i] = new String(board[i]);
        }
        return answer;
        }
    
}
