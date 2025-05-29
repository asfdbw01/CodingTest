/*
문제 요약:
- A도둑과 B도둑이 모든 물건을 훔치려 한다.
- 각 물건마다 A 또는 B가 훔칠 수 있고, 각각에 대해 흔적이 남는다.
  -> info[i][0] = A가 i번 물건을 훔칠 때 흔적 수
  -> info[i][1] = B가 i번 물건을 훔칠 때 흔적 수
- 누적 흔적이 A는 n 이상, B는 m 이상이 되면 경찰에 붙잡힌다.

목표:
- 모든 물건을 도둑 중 한 명이 반드시 훔쳐야 한다.
- 두 도둑 모두 경찰에 붙잡히지 않게 하면서,
- A도둑이 남긴 흔적의 누적 합을 **최소화**하라.

제약 조건:
- info 길이 최대 40 → 상태공간을 줄여야 한다.
- 흔적 값은 1~3 → 완전탐색 가능성이 있지만 비효율적임.
- n, m은 최대 120 → dp[물건 수][B의 누적 흔적] 형태로 DP 가능.

풀이 방향:
- DP[i][j] := i번째 물건까지 고려하고, B의 흔적이 j일 때 A의 누적 흔적 최소값
- 가능한 모든 흔적 조합을 시뮬레이션하여 A의 최소값을 계산
- A 또는 B 도둑이 경찰에 걸리면 해당 경로는 폐기
- 가능한 경로 중 A의 최소 흔적을 반환하고, 없다면 -1 반환
*/


class Solution {
    public int solution(int[][] info, int n, int m) {
        int answer = 0;
        //A도둑이 남긴 흔적의 누적 개수의 최솟값을 return
        int len = info.length;
        final int inf = 9999999;
        //dp[i][j] = i번째 물건을 처리하고 B 흔적합이 j일때 A의 최소값
        int[][] dp = new int[len+1][m];
        for(int i=0;i<=len;i++){
            for(int j=0;j<m;j++){
                dp[i][j] = inf;
            }
        }
        dp[0][0] = 0;
        
        for(int i=0;i<len;i++){
            int traceA = info[i][0];
            int traceB = info[i][1];
            
            for(int j=0;j<m;j++){
                if(dp[i][j]==inf)continue; //도달 불가능
                
                //A가 훔침
                if(dp[i][j]+traceA<n){
                    dp[i+1][j]= Math.min(dp[i+1][j],dp[i][j]+traceA);
                }
                //B가 훔침
                if(j+traceB < m){
                    dp[i+1][j+traceB] = Math.min(dp[i+1][j+traceB],dp[i][j]); 
                }
            }
        }
        
        
        // 최소값 찾기
        int min = inf ;
        for(int i=0;i<m;i++){
            min = Math.min(min,dp[len][i]);
        }
        
        answer = (min==inf)?-1:min;
        return answer;
    }
}
