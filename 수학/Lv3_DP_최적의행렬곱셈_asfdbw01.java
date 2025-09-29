/*
행렬이 2개일 때는 연산 횟수가 일정 하지만, 
행렬의 개수가 3개 이상일 때는 연산의 순서에 따라서 곱하기 연산의 횟수가 바뀔 수 있습니다.

각 행렬의 크기 matrix_sizes 가 매개변수로 주어 질 때, 
모든 행렬을 곱하기 위한 최소 곱셈 연산의 수를 return하는 solution 함수를 완성해 주세요.


제한 사항
행렬의 개수는 3이상 200이하의 자연수입니다.
각 행렬의 행과 열의 크기는 200이하의 자연수 입니다.
계산을 할 수 없는 행렬은 입력으로 주어지지 않습니다.
*/

import java.util.*;

class Solution {
    public int solution(int[][] matrix_sizes) {
        int answer = 0;
        int n = matrix_sizes.length;
        
        int[] p = new int[n + 1];
        p[0] = matrix_sizes[0][0];
        for (int i = 1; i <= n; i++) p[i] = matrix_sizes[i - 1][1];
        
        
        int[][] m = new int[n + 1][n + 1]; 
        
        //m[i][j] = i~j 를 곱하는 최소 비용
        //점화식 : m[i][j] = min(i<=k<j){m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j]}
        
        for (int L = 2; L <= n; L++) {
            for (int i = 1; i <= n - L + 1; i++) {
                int j = i + L - 1;
                int best = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    // 마지막 곱 비용: p[i-1] * p[k] * p[j]
                    long cost = (long)m[i][k] + m[k+1][j] + 1L * p[i-1] * p[k] * p[j];
                    if (cost < best) best = (int)cost;
                }
                m[i][j] = best;
            }
        }
        
        answer = m[1][n];
        return answer;
    }
}