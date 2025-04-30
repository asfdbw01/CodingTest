// 문제 요약:
// 높이 n인 삼각형에 1부터 n(n+1)/2까지 숫자를 반시계 방향으로 달팽이처럼 채움
// 방향 순서: 아래 → 오른쪽 → 대각선 위
// 범위를 벗어나거나 이미 채워진 칸에 닿으면 방향 전환
// 삼각형 내부 값들을 위에서 아래로, 왼쪽에서 오른쪽으로 읽어 1차원 배열로 반환
// 카테고리: 구현 / 시뮬레이션 / 2차원 배열 탐색



import java.util.*;

class Solution {
    public int[] solution(int n) {
        int total = n * (n + 1) / 2;
        int[] answer = new int[total];
        int[][] snail = new int[n][n];
        
        int[][] dirs = { {1,0}, {0,1}, {-1,-1} };
        int num=0,dir=0,y=0,x=0; //num은 값 dir은 방향순서
        snail[y][x] = ++num; // 첫 값 채우고 시작
        while(num < total){
            
            int ny = y+dirs[dir][0];
            int nx = x+dirs[dir][1];
            
            if (ny >= 0 && ny < n && nx >= 0 && nx < n && snail[ny][nx] == 0) {
                y= ny;
                x = nx;
                snail[y][x] = ++num;
            }
            else dir = (dir+1)%3;
        }
        
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {  // 삼각형 형태니까 j <= i 까지만
                answer[idx++] = snail[i][j];
            }
        }
        
        return answer;
    }
}
