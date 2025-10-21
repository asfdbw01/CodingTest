/*  
 * 문제 요약
 *     - 두 2차원 행렬 arr1, arr2를 곱한 결과 행렬을 반환
 *     - 행렬 곱셈: arr1의 i행과 arr2의 j열을 내적하여 결과 행렬[i][j]에 저장
 *
 * 입력  
 *     - int[][] arr1: 크기 N × M (2 ≤ N, M ≤ 100)
 *     - int[][] arr2: 크기 M × P (2 ≤ M, P ≤ 100)
 *     - 각 원소는 -10 이상 20 이하인 자연수
 *     - 항상 곱할 수 있는 유효한 배열만 입력됨
 *
 * 출력  
 *     - int[][]: 크기 N × P인 행렬 곱셈 결과
 *
 * 핵심 포인트  
 *     - 행렬 곱셈의 정의에 따라 결과 행렬의 각 원소 [i][j]는
 *       arr1[i][k] * arr2[k][j]의 합으로 계산됨
 *     - Stream API를 통해 가독성과 연산 흐름을 동시에 확보
 */

class Solution {
    
    public int[][] solution(int[][] arr1, int[][] arr2) {
        return IntStream.range(0, arr1.length) // i: 결과 행 인덱스
                        .mapToObj(i -> 
                            IntStream.range(0, arr2[0].length) // j: 결과 열 인덱스
                                     .map(j -> 
                                         IntStream.range(0, arr2.length) // k: 내적 인덱스
                                                  .map(k -> arr1[i][k] * arr2[k][j])
                                                  .sum()
                                     )
                                     .toArray() // j 열 반복 → 1행 완성
                        )
                        .toArray(int[][]::new); // 모든 행 완성 → 2차원 배열 반환
    }
}
