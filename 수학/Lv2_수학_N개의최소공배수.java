/*  
 * 문제 요약
 *     - n개의 자연수가 주어졌을 때, 이 수들의 최소공배수(LCM)를 구하는 문제
 *
 * 입력  
 *     - 정수 배열 arr (1 ≤ arr.length ≤ 15, 1 ≤ arr[i] ≤ 100)
 *
 * 출력  
 *     - 모든 수의 최소공배수 (int형)
 *
 * 핵심 포인트  
 *     - 최소공배수: LCM(a, b) = a * b / GCD(a, b)
 *     - 유클리드 호제법으로 GCD를 빠르게 구한 뒤, 두 수씩 누적 계산
 */

class Solution {
    
    public int solution(int[] arr) {
        int lcm = arr[0];
        for (int i = 1; i < arr.length; i++) {
            lcm = lcm(lcm, arr[i]); // 누적 LCM 계산
        }
        return lcm;
    }

    // 유클리드 호제법을 이용한 GCD 계산
    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 두 수의 LCM 계산
    public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
