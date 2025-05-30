// [문제 개요]
// n x n 크기의 체스판 위에 n개의 퀸을 서로 공격하지 않도록 배치하는 경우의 수를 구하는 문제

// [퀸의 이동 규칙]
// 퀸은 같은 행, 같은 열, 대각선 방향으로 이동 가능
// 즉, 퀸들 간의 충돌을 피하려면:
//   - 같은 행에 두지 않기
//   - 같은 열에 두지 않기
//   - 같은 대각선 상에 두지 않기

// [목표]
// 서로 공격하지 않는 방식으로 n개의 퀸을 배치할 수 있는 "모든 경우의 수"를 return

// [제한사항]
// n은 1 이상 12 이하의 자연수

// [입출력 예]
// n = 4 -> 가능한 배치 수 = 2


class Solution {
    public int solution(int n) {
        int answer = 0;
        //가로,세로 겹치지 않도록 -> 인접한 수의 차이가 2이상 나야 한다. 예) {2,4,6,1,3,5} 
        //대각선 겹치지 않도록 -> 임의의 두 수의 차이가 그 수들의 인덱스 차이와 동일하면 안 된다.
        
        NQueen nq= new NQueen();
        answer = nq.solveNQueen(n);
        
        return answer;
    }
    class NQueen{
        int count = 0;
        
        public int solveNQueen(int n){
            int[] cols = new int[n];
            backtrack(cols,0,n);
            return count;
        }
        
        private void backtrack(int[] cols,int col,int n){
            if(col==n){
                count++;
                return;
            }
            for(int row = 0; row < n ;row++){
                if(isVaild(cols,col,row)){
                    cols[col] = row;
                    backtrack(cols,col+1,n);
                }
            }
        }
        
        private boolean isVaild(int[] cols,int col,int row){
            for(int prev = 0; prev < col; prev++){
                if(cols[prev]==row)return false;//같은 행
                if(Math.abs(cols[prev]-row)==Math.abs(prev-col))return false;//대각선
                
            }
            return true;
        }
        
    }
}
