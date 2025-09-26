/*  
 * 문제 요약
 *     - 두 개의 행렬 arr1과 arr2를 받아 같은 위치의 원소끼리 더한 결과 행렬을 반환
 *
 * 입력  
 *     - 정수형 2차원 배열 arr1, arr2 (행과 열의 크기: 1 이상 500 이하)
 *
 * 출력  
 *     - 덧셈 결과가 적용된 새로운 2차원 배열 (int[][])
 *
 * 핵심 포인트  
 *     - 행렬의 같은 인덱스 위치의 값을 더하여 새로운 행렬 생성
 *     - Stream API를 활용하여 가독성 높게 구현
 *     - 내부 IntStream은 int[]로, 외부는 int[][]로 반환해야 함
 */

class Solution {
    
    public int[][] solution(int[][] arr1, int[][] arr2) {
        return IntStream.range(0, arr1.length)
                        .mapToObj(i -> IntStream.range(0, arr1[i].length)
                                                .map(j -> arr1[i][j] + arr2[i][j])
                                                .toArray()) // int[]: 한 행의 덧셈 결과
                        .toArray(int[][]::new); // int[][]: 전체 행렬로 반환
    }
}
