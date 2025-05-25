/*
문제 요약 - 행렬 테두리 회전 (시계방향으로 한 칸씩)

[문제 설명]
- 크기가 rows × columns인 행렬이 있으며, 1부터 순서대로 숫자가 채워져 있음
- queries[i] = [x1, y1, x2, y2]는 (x1, y1)부터 (x2, y2)까지의 테두리를 시계방향으로 한 칸씩 회전시키라는 의미
- 회전은 순서대로 진행되며, 각 회전 시 이동한 숫자들 중 가장 작은 값을 기록하여 반환

[입력]
- int rows: 행 개수 (2 ≤ rows ≤ 100)
- int columns: 열 개수 (2 ≤ columns ≤ 100)
- int[][] queries: 회전 명령 목록 (1 ≤ queries.length ≤ 10,000)
  - 각 query는 [x1, y1, x2, y2] 형태이며, 1 ≤ x1 < x2 ≤ rows, 1 ≤ y1 < y2 ≤ columns

[출력]
- int[]: 각 회전에 의해 움직인 숫자 중 최솟값을 순서대로 저장한 배열

[핵심 포인트]
- 회전 대상은 직사각형 테두리만 (내부는 그대로)
- 회전은 in-place로 수행하되, 값 손실 방지를 위해 이전 값을 저장해가며 진행
- 시계방향 이동 순서: → ↓ ← ↑ 순서로 테두리 좌표를 따라 4방향으로 회전
- 시간 복잡도는 O(queries.length × 테두리 길이) → 최악의 경우도 충분히 통과 가능

[예시]
- 입력: rows = 6, columns = 6, queries = [[2,2,5,4],[3,3,6,6],[5,1,6,3]]
- 출력: [8, 10, 25]
*/


class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int [][] box = new int[rows][columns];
        makeBox(box);
        for(int i=0;i<queries.length;i++){
            answer[i] = spin(box, queries[i]);
        }
        return answer;
    }
    
    private void makeBox(int[][]box){
        int num=1;
        for(int y=0;y<box.length;y++){
            for(int x=0;x<box[0].length;x++){
                box[y][x]=num;
                num++;
            }
        }
    }
    
    private int spin(int[][] box, int[] queries) {
        int[] start = new int[]{queries[0] - 1, queries[1] - 1};
        int[] end = new int[]{queries[2] - 1, queries[3] - 1};
        int prev = box[start[0]][start[1]];
        int min = prev;

        // 오른쪽 이동
        for (int x = start[1] + 1; x <= end[1]; x++) {
            int temp = box[start[0]][x];
            box[start[0]][x] = prev;
            prev = temp;
            min = Math.min(min, prev);
        }

        // 아래쪽 이동
        for (int y = start[0] + 1; y <= end[0]; y++) {
            int temp = box[y][end[1]];
            box[y][end[1]] = prev;
            prev = temp;
            min = Math.min(min, prev);
        }

        // 왼쪽 이동
        for (int x = end[1] - 1; x >= start[1]; x--) {
            int temp = box[end[0]][x];
            box[end[0]][x] = prev;
            prev = temp;
            min = Math.min(min, prev);
        }

        // 위쪽 이동
        for (int y = end[0] - 1; y >= start[0]; y--) {
            int temp = box[y][start[1]];
            box[y][start[1]] = prev;
            prev = temp;
            min = Math.min(min, prev);
        }

        return min;
    }

}
