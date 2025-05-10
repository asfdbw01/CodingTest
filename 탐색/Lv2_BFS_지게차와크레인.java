/*
 * 문제 요약
 *   - 물류창고에는 알파벳 대문자로 구성된 n x m 크기의 컨테이너가 배치되어 있다.
 *   - 출고 요청이 들어오면 해당 알파벳 종류의 컨테이너를 조건에 따라 제거한다.
 *   - 지게차 요청(1글자)은 외부 공기(' ')에 인접한 컨테이너만 제거할 수 있고,
 *     크레인 요청(2글자)은 해당 알파벳 컨테이너 전체를 제거한다.

 * 입력
 *   - String[] storage: n행 m열의 컨테이너 정보 (n x m, 2 ≤ n, m ≤ 50)
 *   - String[] requests: 출고 요청 배열 (길이 1 또는 2, 1 ≤ 요청 수 ≤ 100)

 * 출력
 *   - int: 모든 출고 요청을 처리한 뒤 남은 컨테이너의 총 개수

 * 핵심 포인트
 *   - storage는 외부 공기 접근 판단을 위해 padding 배열로 확장
 *   - 지게차 요청은 isPickable 판별을 통해 공기 접촉 여부 판단
 *   - 크레인 요청은 조건 없이 전부 제거
 *   - '-'로 제거 예약 후 BFS로 연쇄 제거 처리
 */

class Solution {
    char[][] paddingArr;
    int rows, cols;

    public int solution(String[] storage, String[] requests) {
        // 원래 storage 배열의 크기
        int rows = storage.length;
        int cols = storage[0].length();

        // 전체 컨테이너 수로 시작 (이후 제거 수를 뺄 예정)
        int answer = rows * cols;

        // 패딩을 위해 2씩 확장된 행렬 크기 설정
        this.rows = rows + 2;
        this.cols = cols + 2;

        // 외곽 패딩을 포함한 배열로 변환
        this.paddingArr = toPaddingArr(storage);

        // 각 출고 요청 처리
        for (String request : requests) {
            // 해당 요청에서 제거된 컨테이너 수만큼 빼기
            answer -= pickUp(request);
        }

        return answer;
    }

    public int pickUp(String request) {
        int answer = 0;
        char ch = request.charAt(0); // 출고 요청 알파벳 종류
        List<int[]> idxList = getTargetIdxList(ch); // 해당 알파벳이 있는 위치들

        boolean isCrane = (request.length() == 2); // 길이가 2면 크레인 요청

        // 각 위치에 대해
        for (int[] idx : idxList) {
            // 크레인이거나, 현재 위치가 외부 공기와 접촉 중이면
            if (isCrane || isPickable(idx)) {
                // '-'로 마킹하여 제거 예약
                this.paddingArr[idx[0]][idx[1]] = '-';
                answer++;
            }
        }

        // '-'로 마킹된 위치를 기준으로 주변 연쇄 제거 수행
        for (int[] idx : idxList) {
            bfs(idx);
        }

        return answer;
    }

    // 빈 공간('-') 중 외부 공기와 맞닿은 곳을 기준으로 실제 공기(' ')로 확산 처리
    public void bfs(int[] idx) {
        // 시작 위치가 외부 공기와 닿아 있으면 확산 시작
        if (isPickable(idx)) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(idx);

            while (!queue.isEmpty()) {
                int[] nowIdx = queue.poll();

                // 아직 공기로 확정되지 않은 빈 공간('-')일 때만 처리
                if (this.paddingArr[nowIdx[0]][nowIdx[1]] != '-') continue;

                // 외부 공기와 연결된 빈 공간으로 처리 → 공기(' ')
                this.paddingArr[nowIdx[0]][nowIdx[1]] = ' ';

                // 인접한 빈 공간('-')들도 확산 대상이므로 큐에 추가
                if (this.paddingArr[nowIdx[0]][nowIdx[1] - 1] == '-') queue.add(new int[]{nowIdx[0], nowIdx[1] - 1});
                if (this.paddingArr[nowIdx[0]][nowIdx[1] + 1] == '-') queue.add(new int[]{nowIdx[0], nowIdx[1] + 1});
                if (this.paddingArr[nowIdx[0] - 1][nowIdx[1]] == '-') queue.add(new int[]{nowIdx[0] - 1, nowIdx[1]});
                if (this.paddingArr[nowIdx[0] + 1][nowIdx[1]] == '-') queue.add(new int[]{nowIdx[0] + 1, nowIdx[1]});
            }
        }
    }

    // 특정 알파벳이 위치한 모든 좌표 반환
    public List<int[]> getTargetIdxList(char target) {
        List<int[]> idxList = new ArrayList<>();

        // 내부 영역만 탐색 (패딩 제외)
        for (int i = 1; i < this.rows - 1; i++) {
            for (int j = 1; j < this.cols - 1; j++) {
                if (this.paddingArr[i][j] == target)
                    idxList.add(new int[]{i, j});
            }
        }

        return idxList;
    }

    // 해당 좌표가 외부 공기(' ')와 맞닿아 있으면 true
    public boolean isPickable(int[] idx) {
        if (this.paddingArr[idx[0]][idx[1] - 1] == ' ' ||
            this.paddingArr[idx[0]][idx[1] + 1] == ' ' ||
            this.paddingArr[idx[0] - 1][idx[1]] == ' ' ||
            this.paddingArr[idx[0] + 1][idx[1]] == ' ') return true;

        return false;
    }

    // storage 배열을 외곽에 공기(' ') 패딩을 덧붙인 2차원 배열로 변환
    public char[][] toPaddingArr(String[] arr) {
        char[][] paddingArr = new char[this.rows][this.cols];

        // 첫 행 공기 채움
        Arrays.fill(paddingArr[0], ' ');

        // 내부 행 처리
        for (int i = 1; i < this.rows - 1; i++) {
            paddingArr[i][0] = ' '; // 첫 열
            for (int j = 1; j < this.cols - 1; j++) {
                paddingArr[i][j] = arr[i - 1].charAt(j - 1); // 중심 채우기
            }
            paddingArr[i][this.cols - 1] = ' '; // 마지막 열
        }

        // 마지막 행 공기 채움
        Arrays.fill(paddingArr[this.rows - 1], ' ');

        return paddingArr;
    }
}
