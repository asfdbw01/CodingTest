/*  
 * 문제 요약
 *     - n x n 배열에서 (i, j) 위치의 값은 max(i, j) + 1로 정의된다.
 *       이 배열을 1차원으로 펼친 뒤, 인덱스 left부터 right까지의 부분 배열을 구하는 문제.
 *
 * 입력  
 *     - 정수 n (1 ≤ n ≤ 10^7)
 *     - 정수 left, right (0 ≤ left ≤ right < n^2, right - left < 10^5)
 *
 * 출력  
 *     - arr[left]부터 arr[right]까지의 값을 담은 1차원 정수 배열
 *
 * 핵심 포인트  
 *     - 배열 전체를 생성하면 메모리 초과 → 인덱스를 수학적으로 계산
 *     - 1차원 인덱스 i에 대해 (row, col) = (i / n, i % n)
 *     - 해당 위치의 값은 max(row, col) + 1로 계산 가능
 */

class Solution {

    public int[] solution(int n, long left, long right) {
        // left부터 right까지 각 인덱스를 순회
        return LongStream.rangeClosed(left, right)
                // 각 인덱스 i에 대해 (i / n, i % n)에 해당하는 max(row, col) + 1 계산
                .mapToInt(i -> (int) Math.max(i / n, i % n) + 1)
                // 최종 배열로 반환
                .toArray();
    }
}
