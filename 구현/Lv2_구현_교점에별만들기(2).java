/*  
 * 문제 요약
 *     - 여러 개의 직선이 주어졌을 때, 서로 교차하는 정수 좌표의 교점을 찾아 별을 찍고,
 *       별이 찍힌 영역만 포함하는 최소 크기의 문자열 격자를 구성해 반환하는 문제.
 *
 * 입력  
 *     - 2차원 정수 배열 line (길이 2 이상 1,000 이하), 각 원소는 [A, B, C] (Ax + By + C = 0)
 *     - 각 A, B, C는 -100,000 이상 100,000 이하의 정수
 *
 * 출력  
 *     - 정수 교점에 *을 찍고, 나머지는 .으로 채운 최소 크기의 문자열 배열
 *
 * 핵심 포인트  
 *     - 두 직선의 교점을 수학적으로 구하고 정수인지 판별해야 함
 *     - 좌표 범위를 갱신하여 최소 사각형 영역만 출력
 *     - 메모리 초과를 방지하기 위해 좌표 저장 및 출력 최적화 필요
 */

class Solution {
	
    public String[] solution(int[][] line) {
        // 교점들을 저장할 맵: x좌표 → y좌표 집합
        Map<Integer, Set<Integer>> crossMap = new HashMap<>();

        // 전체 교점의 좌표 범위 추적
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;

        // 모든 직선 쌍을 순회하며 교점 계산
        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i + 1; j < line.length; j++) {
                int ia = line[i][0], ib = line[i][1], ic = line[i][2];
                int ja = line[j][0], jb = line[j][1], jc = line[j][2];

                // 분모 및 분자 계산 (정수 오버플로우 방지 위해 long 사용)
                long base = (long) ia * jb - (long) ib * ja;
                long xNumer = (long) ib * jc - (long) ic * jb;
                long yNumer = (long) ic * ja - (long) ia * jc;

                // 평행이거나 정수 좌표가 아니면 건너뜀
                if (base == 0 || xNumer % base != 0 || yNumer % base != 0) continue;

                // 정수 좌표로 변환
                int x = (int) (xNumer / base), y = (int) (yNumer / base);

                // 교점 좌표 반영
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);

                // 해당 x좌표에 y값 저장
                crossMap.computeIfAbsent(x, k -> new HashSet<>()).add(y);
            }
        }

        List<String> answer = new ArrayList<>();

        // 위에서 아래로 (큰 y부터 작은 y까지)
        for (int y = maxY; y >= minY; y--) {
            StringBuilder sb = new StringBuilder();
            for (int x = minX; x <= maxX; x++) {
                // 별이 찍힌 위치인지 확인
                if (crossMap.containsKey(x) && crossMap.get(x).contains(y)) sb.append('*');
                else sb.append('.');
            }
            answer.add(sb.toString());
        }

        // 리스트를 문자열 배열로 변환
        return answer.toArray(new String[0]);
    }
}
