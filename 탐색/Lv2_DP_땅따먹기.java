/*
// 땅따먹기 게임의 땅(land)은 총 N행 4열로 이루어져 있고, 모든 칸에는 점수가 쓰여 있습니다
//1행부터 땅을 밟으며 한 행씩 내려올 때, 각 행의 4칸 중 한 칸만 밟으면서 내려와야 합니다
// 땅따먹기 게임에는 한 행씩 내려올 때, 같은 열을 연속해서 밟을 수 없는 특수 규칙이 있습니다.
//마지막 행까지 모두 내려왔을 때, 얻을 수 있는 점수의 최대값을 return하는 solution 함수를 완성해 주세요.
*/


class Solution {
    int solution(int[][] land) {
        int answer = 0;
        
        int[][] dp = new int[land.length][4];
        for (int i = 0; i < land.length; i++) {
            dp[i] = land[i].clone();
        }
        
        drawDp (dp,land);
        
        for(int i :dp[dp.length-1]){
            answer = Math.max(answer,i);
        }
        return answer;
    }
    
    
    private void drawDp (int[][]dp,int[][]land){
        //열
        for(int y=1;y<dp.length;y++){
            //행
            for(int x=0;x<dp[y].length;x++){
                for(int k=0;k<dp[y].length;k++){
                    if(k==x)continue;
                    dp[y][x]=Math.max(dp[y][x],dp[y-1][k]+land[y][x]);
                }
            }
        }
    }
}
