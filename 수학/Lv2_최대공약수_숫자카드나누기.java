/*
 * 문제 요약
 *     - 철수와 영희가 각자 N장의 숫자 카드를 갖고 있을 때, 다음 조건을 만족하는 가장 큰 양의 정수 a를 구해야 한다:
 *         1) 한 쪽의 카드 숫자 전체를 a로 나눌 수 있음
 *         2) 다른 쪽의 카드 숫자 중 하나도 a로 나눌 수 없음
 * 
 * 입력
 *     - int[] arrayA: 철수의 카드 숫자 배열
 *     - int[] arrayB: 영희의 카드 숫자 배열
 * 
 * 출력
 *     - 조건을 만족하는 가장 큰 양의 정수 a (없다면 0)
 * 
 * 핵심 포인트
 *     - 한 배열의 모든 수를 나눌 수 있는 수는 그 배열의 GCD(최대공약수)의 약수임
 *     - 다른 배열에 그 GCD로 나누어지는 수가 존재하지 않아야 조건을 만족
 *     - 따라서 arrayA와 arrayB의 GCD를 구한 후, 상대 배열에서 나눌 수 없는지를 확인
 */

class Solution {

    // 문제 조건에 맞는 가장 큰 정수 a를 반환
    public int solution(int[] arrayA, int[] arrayB) {
        // 각 배열의 GCD 초기화
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];

        // 배열 전체의 GCD를 계산
        for (int i = 1; i < arrayA.length; i++) {
            gcdA = euclidean(gcdA, arrayA[i]);
            gcdB = euclidean(gcdB, arrayB[i]);
        }

        // 상대 배열에 나누어지는 수가 하나라도 있다면 무효화
        gcdA = isNotDivisor(gcdA, arrayB) ? gcdA : 0;
        gcdB = isNotDivisor(gcdB, arrayA) ? gcdB : 0;

        // 조건을 만족하는 GCD 중 큰 값 반환
        return Math.max(gcdA, gcdB);
    }

    // 유클리드 호제법으로 두 수의 최대공약수 반환
    public int euclidean(int a, int b) {
        return b == 0 ? a : euclidean(b, a % b);
    }

    // 주어진 수 d가 배열의 어떤 원소도 나눌 수 없는지 확인
    public boolean isNotDivisor(int d, int[] array) {
        for (int n : array) {
            if (n % d == 0) return false;  // 나눌 수 있는 수가 있으면 false
        }
        return true;  // 전부 나눌 수 없으면 true
    }

}
