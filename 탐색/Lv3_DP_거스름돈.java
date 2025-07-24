// 문제 요약:
// 주어진 금액 n원을 거슬러 줄 수 있는 경우의 수를 구하는 문제
// 동전의 종류는 배열 money에 주어지며, 각 동전은 무한히 사용 가능
// 순서를 고려하지 않고 조합만 구함 (1+2와 2+1은 같은 경우로 봄)
// 결과는 1,000,000,007로 나눈 나머지를 반환해야 함
//
// 예시:
// n = 5, money = [1,2,5]
// -> 가능한 조합: [1*5], [1*3+2*1], [1*1+2*2], [5*1] => 총 4가지
import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        //점화식 dp[i] = dp[i] + dp[i - coin];
        int[] dp = new int[n+1];
        dp[0] = 1;
        int mod = 1_000_000_007;
        
        for (int coin : money) {
            for (int i = coin; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % mod;
            }
        }
        answer = dp[n];
        return answer;
    }
    
}
