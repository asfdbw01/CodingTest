/*
[문제 요약]
- 격자 크기: m(가로), n(세로)
- 출발지: (1,1), 도착지: (m,n)
- 오른쪽 또는 아래쪽으로만 이동 가능
- 웅덩이(puddles)로 표시된 좌표는 통과 불가
- 최단 경로의 수를 1,000,000,007로 나눈 나머지를 구함

[제한 사항]
- 1 ≤ m, n ≤ 100
- puddles.length ≤ 10
- puddles[i] = [x, y] (1-based 좌표, 가로 x, 세로 y)
- 시작/도착 지점은 웅덩이가 아님

[예시]
m = 4, n = 3, puddles = [[2, 2]]
→ 가능한 경로 수 = 4

[해결 아이디어]
- DP[i][j]: (1,1)에서 (i,j)까지 가는 경로 수
- DP[i][j] = DP[i-1][j] + DP[i][j-1] (단, 웅덩이 위치는 0)
- 결과: DP[n][m] % 1_000_000_007
*/
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][][] dp = makeDp( m, n,  puddles);
        answer = countDp( dp,m, n);
        return answer;
    }
    private int[][][] makeDp(int m, int n, int[][] puddles){
        int[][][] dp = new int[n+1][m+1][3];//y좌표,x좌표,[홍수 여부,최소값,경로수]
        for(int i=0;i<puddles.length;i++){
            int[] flood = puddles[i];
            dp[flood[1]][flood[0]][0] = -1;
        }
        dp[1][1]=new int[]{0,0,1};
        return dp;
    }
    
    private int countDp(int[][][] dp, int m, int n){
        int MOD = 1_000_000_007;

        for(int y = 1; y <= n; y++){
            for(int x = 1; x <= m; x++){
                if (y == 1 && x == 1) continue; // 시작점은 이미 초기화됨
                if (dp[y][x][0] == -1) continue; // 웅덩이

                int fromTop = (dp[y - 1][x][0] == -1) ? 0 : dp[y - 1][x][2];
                int fromLeft = (dp[y][x - 1][0] == -1) ? 0 : dp[y][x - 1][2];

                dp[y][x][2] = (fromTop + fromLeft) % MOD;
            }
        }

        return dp[n][m][2];
    }

}
