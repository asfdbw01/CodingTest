/*
올바른 괄호란 (())나 ()와 같이 올바르게 모두 닫힌 괄호를 의미합니다.
괄호 쌍의 개수 n이 주어질 때, 
n개의 괄호 쌍으로 만들 수 있는 모든 가능한 괄호 문자열의 갯수를 반환하는 
함수 solution을 완성해 주세요.
*/

class Solution {
    public int solution(int n) {
        int answer = 0;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            for(int j=0;j<i;j++){
                dp[i] += (dp[j]*dp[i-j-1]);
            }
        }
        
        answer = dp[n];
        return answer;
    }
}