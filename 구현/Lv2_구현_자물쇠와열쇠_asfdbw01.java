// 문제 요약 — 자물쇠와 열쇠 (프로그래머스)
// - key(M×M)와 lock(N×N)이 0/1 격자로 주어진다. (0=홈, 1=돌기)
// - key는 회전(0/90/180/270) 및 평행이동 가능.
// - 자물쇠 영역 안에서는
//    * key의 1과 lock의 0이 정확히 맞아야 하고(채움),
//    * key의 1과 lock의 1이 겹치면 안 되며(충돌 금지),
//    * lock의 모든 0이 채워져야 한다(빈칸 없음).
// - 자물쇠 영역 밖의 겹침은 무시.
// - 열 수 있으면 true, 없으면 false.
//
// 제약
// - 3 ≤ M,N ≤ 20, M ≤ N
// - 요소는 0/1
//
// 정석 풀이(패딩 + 브루트포스)
// 1) 패딩 보드 생성: size = N + 2*(M-1), off = M-1
//    → lock을 board[off..off+N-1][off..off+N-1]에 배치.
// 2) key를 4번 회전하며, 보드 전 위치(y=0..size-M, x=0..size-M)로 슬라이드:
//    - board에 key를 더해본다: board[y+i][x+j] += key[i][j]
//    - lock 영역이 전부 1이면 성공(0+1=1, 1+0=1 OK / 1+1=2, 0+0=0 실패).
//    - 실패면 곧바로 key를 빼서 원복.
// 3) 모든 회전/위치에서 실패하면 false.
//
// 복잡도
// - 대략 4 × (N+M-1)^2 × M^2 ≤ 수백만 연산 (N,M ≤ 20 → 충분히 통과)
//
// 구현 포인트
// - 회전: r[j][M-1-i] = a[i][j] (시계 90°)
// - 패딩 폭 정확히 M-1, 원복 필수
// - 판정은 “락 중앙 영역이 모두 1인가?” 한 가지 조건으로 충분
//
// 엣지
// - lock에 0이 아예 없으면 이미 열림(true)
// - 경계(보드 가장자리에 걸치는) 배치들도 패딩으로 자연스럽게 커버


class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int m = key.length, n = lock.length;
        boolean answer = false;
        int[][] copyKey = makeCopyKey(key);
        
        for(int i=0;i<4;i++){
            if(makeFit(m,n,copyKey,lock))return true;
            copyKey= turnCopyKey(copyKey);
        }
        return answer;
    }
    
    private boolean makeFit(int m,int n,int[][] copyKey,int[][] lock){
        int bSize = n+2*m-2;
        for(int i=0;i<bSize-m+1;i++){
            for(int j=0;j<bSize-m+1;j++){
                //작성
                int[][] board = makeBoard(copyKey,lock);
                combine(i,j,board,copyKey);
                if(isValid(board, m, n))return true;
            }
        }
        return false;
    }
    
    private void combine(int y,int x,int[][] board,int[][] copyKey){
        int cx=0,cy = 0;
        for(int i = y;i<y+copyKey.length;i++){
            cx=0;
            for(int j =x;j < x+copyKey.length;j++){
                board[i][j] += copyKey[cy][cx]; 
                cx++;
            }
            cy++;
        }
    }
    
    private boolean isValid(int[][] board,int m,int n){
        for(int i=m-1;i<m-1+n;i++){
            for(int j=m-1;j<m-1+n;j++){
                if(board[i][j]!=1)return false;
            }
        }
        return true;
    }
    
    private int[][] makeBoard(int[][] key, int[][] lock) {
        int m = key.length, n = lock.length;
        int size = n + 2 * (m - 1);
        int off  = m - 1;

        int[][] board = new int[size][size];
        for (int i = 0; i < n; i++) {
            System.arraycopy(lock[i], 0, board[off + i], off, n);
        }
        return board;
    }

    
    private int[][] makeCopyKey(int[][]key){
        int n = key.length;
        int copyKey[][] = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)copyKey[i][j] = key[i][j];
        }
        return copyKey;
    }
    
    private int[][] turnCopyKey(int[][] copyKey){
        int n = copyKey.length;
        int[][]turnKey = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                turnKey[j][n-1-i] = copyKey[i][j];
            }
        }
        return turnKey;
    }
}