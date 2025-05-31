/**
 * 3x3 틱택토 판이 주어졌을 때,
 * 해당 게임판이 틱택토의 규칙을 지켜서 만들어질 수 있는 상태인지 판별한다.
 *
 * 규칙 요약:
 * 1. 선공은 'O', 후공은 'X'이며, 번갈아가며 한 칸씩 둔다.
 * 2. 'O'는 항상 'X'보다 같거나 1개 많아야 한다.
 * 3. 승리 조건:
 *    - 가로, 세로, 대각선에 같은 문자가 3개 연속이면 해당 문자가 승리
 * 4. 승리한 이후에는 더 이상 두면 안 된다.
 * 5. O와 X가 동시에 승리할 수 없다.
 *
 * 판이 위 조건을 모두 만족하면 1, 아니면 0을 반환한다.
 */


class Solution {
    public int solution(String[] board) {
        int answer = 1;
        int[] countOX = new int[2];
        countOXCount(board,countOX);
        if(!(countOX[0]==countOX[1] || countOX[0] == countOX[1]+1))return 0; 
        
        boolean oWin = isWin(board,'O');
        boolean xWin = isWin(board,'X');
        if(oWin && xWin)return 0;
        if(oWin && countOX[0] !=countOX[1]+1)return 0;
        if(xWin && countOX[0]!=countOX[1])return 0;
        return answer;
    }
    private void countOXCount(String[] board,int[] countOX){
        for(int i=0;i<board.length;i++){
            char[] boardChar = board[i].toCharArray();
            for(char c:boardChar){
                if(c=='O')countOX[0]++;
                if(c=='X')countOX[1]++;
            }
        }
    }
    
    private boolean isWin(String[] board,char player){
        for(int i=0;i<3;i++){
            //가로
            if(board[i].charAt(0)==player &&
              board[i].charAt(1)==player &&
              board[i].charAt(2)==player)return true;
            
            //세로
            if(board[0].charAt(i)==player &&
              board[1].charAt(i)==player &&
              board[2].charAt(i)==player )return true;
        }
        //대각선
        if(board[0].charAt(0)==player &&
              board[1].charAt(1)==player &&
              board[2].charAt(2)==player )return true;
        if(board[0].charAt(2)==player &&
              board[1].charAt(1)==player &&
              board[2].charAt(0)==player )return true;
        return false;
    }
}
