/*  
 * 문제 요약
 *     - 정수 left부터 right까지의 숫자 중에서,
 *       약수의 개수가 짝수인 수는 더하고, 홀수인 수는 뺀 합을 구함
 *
 * 입력  
 *     - int left: 시작 정수 (1 ≤ left ≤ right ≤ 1,000)
 *     - int right: 끝 정수
 *
 * 출력  
 *     - 약수 개수 조건에 따라 더하거나 뺀 정수들의 합 (int)
 *
 * 핵심 포인트  
 *     - 약수는 자기 자신을 포함하므로 1부터 n/2까지의 약수를 세고 +1
 *     - 약수 개수가 짝수면 양수로 더하고, 홀수면 음수로 더함
 *     - IntStream을 통해 범위 순회 및 조건 적용 가능
 */

class Solution {
	
    public int solution(int left, int right) {
        return IntStream.rangeClosed(left, right)
            .map(n -> (IntStream.rangeClosed(1, n / 2)
                .filter(i -> n % i == 0)
                .count() + 1) % 2 == 0 ? n : -n)  // 약수 개수가 짝수면 +n, 홀수면 -n
            .sum();  // 전체 합계 반환
    }

}
