/*  
 * 문제 요약
 *     - 양의 정수 x에 대해, x보다 크고 비트가 1~2개 다른 수 중 가장 작은 수를 구함
 *
 * 입력  
 *     - long[] numbers: 양의 정수 배열 (0 ≤ numbers[i] ≤ 10^15, 길이 ≤ 100,000)
 *
 * 출력  
 *     - 각 수에 대해 f(x)를 구한 결과 long[] 배열 반환
 *
 * 핵심 포인트  
 *     - 짝수는 무조건 x + 1이 정답 (비트 1개 차이)
 *     - 홀수는 가장 낮은 0비트를 1로 바꾸고, 그 앞의 1비트를 0으로 바꿔야 2비트 차이
 *     - Long.lowestOneBit(~x) >> 1을 더하면 해당 조건 만족
 */

class Solution {

    public long[] solution(long[] numbers) {
        return Arrays.stream(numbers)
            .map(n -> (n & 1) == 0 ? n + 1 : n + (Long.lowestOneBit(~n) >> 1))  // 짝수는 +1, 홀수는 가장 낮은 0비트 위치 계산
            .toArray();  // 결과 배열로 반환
    }

}
