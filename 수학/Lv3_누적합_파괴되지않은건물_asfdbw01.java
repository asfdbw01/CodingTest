/*
N x M 크기의 행렬 모양의 게임 맵이 있습니다
. 건물은 적의 공격을 받으면 내구도가 감소하고 내구도가 0이하가 되면 파괴됩니다. 반대로, 아군은 회복 스킬을 사용하여 건물들의 내구도를 높이려고 합니다.
적의 공격과 아군의 회복 스킬은 항상 직사각형 모양입니다.

건물의 내구도를 나타내는 2차원 정수 배열 board와 
적의 공격 혹은 아군의 회복 스킬을 나타내는 2차원 정수 배열 skill이 매개변수로 주어집니다. 
적의 공격 혹은 아군의 회복 스킬이 모두 끝난 뒤 파괴되지 않은 건물의 개수를 return하는 solution함수를 완성해 주세요.

제한사항
1 ≤ board의 행의 길이 (= N) ≤ 1,000
1 ≤ board의 열의 길이 (= M) ≤ 1,000
1 ≤ board의 원소 (각 건물의 내구도) ≤ 1,000
1 ≤ skill의 행의 길이 ≤ 250,000
skill의 열의 길이 = 6
skill의 각 행은 [type, r1, c1, r2, c2, degree]형태를 가지고 있습니다.
type은 1 혹은 2입니다.
type이 1일 경우는 적의 공격을 의미합니다. 건물의 내구도를 낮춥니다.
type이 2일 경우는 아군의 회복 스킬을 의미합니다. 건물의 내구도를 높입니다.
(r1, c1)부터 (r2, c2)까지 직사각형 모양의 범위 안에 있는 건물의 내구도를 degree 만큼 낮추거나 높인다는 뜻입니다.
0 ≤ r1 ≤ r2 < board의 행의 길이
0 ≤ c1 ≤ c2 < board의 열의 길이
1 ≤ degree ≤ 500
type이 1이면 degree만큼 건물의 내구도를 낮춥니다.
type이 2이면 degree만큼 건물의 내구도를 높입니다.
건물은 파괴되었다가 회복 스킬을 받아 내구도가 1이상이 되면 파괴되지 않은 상태가 됩니다. 즉, 최종적으로 건물의 내구도가 1이상이면 파괴되지 않은 건물입니다.
정확성 테스트 케이스 제한 사항
1 ≤ board의 행의 길이 (= N) ≤ 100
1 ≤ board의 열의 길이 (= M) ≤ 100
1 ≤ board의 원소 (각 건물의 내구도) ≤ 100
1 ≤ skill의 행의 길이 ≤ 100
1 ≤ degree ≤ 100
*/

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        //skill의 각 행은 [type, r1, c1, r2, c2, degree]
        //type : 1 공격 : 2회복
        //누적합 문제
        int[][] dp = new int [board.length+1][board[0].length+1];
        buildDP( dp, skill);
        answer = countBuilding(dp,board);
        return answer;
    }
    
    private int countBuilding(int[][] dp,int[][] board){
        int cnt = 0;
        for(int y=0;y<board.length;y++){
            for(int x=0; x<board[0].length;x++){
                if(board[y][x]+dp[y][x]>0)cnt++;
            }
        }
        return cnt;
    }
    
    private void buildDP(int[][] dp, int[][] skills){
        for(int[] skill : skills){
            skillEffect (dp,skill);
        }
        
        //왼 -> 오 누적합
        for(int i=0;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                dp[i][j] += dp[i][j-1];
            }
        }
        //위 -> 아래 누적합
        for(int x=0;x<dp[0].length;x++){
            for(int y=1;y<dp.length;y++){
                dp[y][x] += dp[y-1][x];
            }
        }
    }
    
    private void skillEffect (int[][] dp,int[] skill) {
        int effect=skill[5];
        if(skill[0]==1) effect *= -1;
        
        dp[skill[1]][skill[2]] += effect;
        dp[skill[1]][skill[4]+1] -= effect;
        dp[skill[3]+1][skill[4]+1] += effect;
        dp[skill[3]+1][skill[2]] -=effect;
    }
}
