/*  
 * 문제 요약
 *     - 피보나치 수 F(n)은 F(n) = F(n-1) + F(n-2) (단, F(0)=0, F(1)=1)
 *     - n번째 피보나치 수를 1234567로 나눈 나머지를 반환
 *
 * 입력  
 *     - 정수 n (2 ≤ n ≤ 100,000)
 *
 * 출력  
 *     - 정수: F(n) % 1234567
 *
 * 핵심 포인트  
 *     - 반복문을 사용한 동적 프로그래밍 방식으로 피보나치 수열 계산
 *     - 매 연산마다 1234567로 모듈러 연산을 수행하여 오버플로우 방지
 *     - 이전 값만 이용하므로 공간 최적화가 가능하나, 리스트 사용 방식도 정답 처리 가능
 */

class Solution {
    
    public int solution(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0); // F(0)
        list.add(1); // F(1)
        
        for (int i = 2; i <= n; i++) {
            // F(i) = (F(i-1) + F(i-2)) % 1234567
            list.add((list.get(list.size() - 1) + list.get(list.size() - 2)) % 1234567);
        }
        
        return list.get(list.size() - 1) % 1234567;
    }
}
