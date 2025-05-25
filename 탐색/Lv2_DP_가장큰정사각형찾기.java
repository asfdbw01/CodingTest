/*
문제 요약 - 2차원 배열에서 1로 이루어진 가장 큰 정사각형 넓이 찾기

[문제 설명]
- 1과 0으로 채워진 2차원 배열 `board`가 주어짐
- 이 중 값이 모두 1로 이루어진 **가장 큰 정사각형**을 찾아
  그 **넓이(한 변의 길이의 제곱)** 를 반환

[입력]
- board: int[][] 형태의 2차원 배열 (1,000 × 1,000 이하 크기)
- 값은 1 또는 0

[출력]
- int: 가장 큰 1로 된 정사각형의 넓이 (변의 길이 × 변의 길이)

[핵심 아이디어]
- 동적 프로그래밍(DP) 사용
- `dp[y][x]`를 (y,x) 좌표를 **오른쪽 아래 꼭짓점**으로 하는 정사각형의 최대 변의 길이라고 정의
- 점화식:
  if board[y][x] == 1:
    dp[y][x] = min(dp[y-1][x], dp[y][x-1], dp[y-1][x-1]) + 1
- 최댓값을 계속 갱신하면서 마지막에 `최댓값^2` 반환

[예시]
- 예: [[0,1,1,1],[1,1,1,1],[1,1,1,1],[0,0,1,0]]
- 가장 큰 정사각형은 3×3 → 넓이 = 9
*/


class Solution
{
    public int solution(int [][]board)
    {
        int answer = board[0][0];
        int[][] dp = new int[board.length][];

        for (int i = 0; i < board.length; i++) {
            dp[i] = board[i].clone(); // 각 행을 따로 clone()
        }
        
        for(int y=1;y<dp.length;y++){
            for(int x=1;x<dp[0].length;x++){
                if(board[y][x]==0)continue;
                boolean square = board[y-1][x]==1 && board[y][x-1]==1 && board[y-1][x-1]==1;
                if(square){
                    dp[y][x]= Math.min(Math.min(dp[y-1][x],dp[y][x-1]),dp[y-1][x-1])+1;
                    answer = Math.max(answer,dp[y][x]);
                }
            }
        }
        
        return answer*answer;
    }
}
