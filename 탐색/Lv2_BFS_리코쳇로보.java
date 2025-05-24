/*
문제 요약 - 리코쳇 로봇 (미끄러지는 로봇의 최소 이동 횟수 구하기)

[문제 설명]
- 격자 게임판 위에서 로봇(R)이 장애물(D)이나 벽에 부딪힐 때까지 미끄러져 이동
- 목표 지점(G)에 도달하기 위한 **최소 이동 횟수**를 구하는 문제
- 이동은 상/하/좌/우 방향으로 가능하며, 한 번 미끄러지는 것이 1회 이동
- 목표 지점에 도달할 수 없으면 -1 반환

[입력]
- board: 문자열 배열 (게임판 상태)
  - ".", "D", "R", "G" 로만 구성됨
  - R: 로봇 시작 위치 (1회 등장)
  - G: 목표 위치 (1회 등장)
  - D: 장애물
  - .: 빈 공간
- 3 ≤ board.length ≤ 100
- board의 각 원소 길이는 동일 (최대 100)

[출력]
- 최소 이동 횟수 (도달 불가 시 -1)

[핵심 로직]
- BFS를 사용한 최단 거리 탐색
- 각 방향으로 **미끄러지며 이동**
  - 방향으로 계속 이동하다가 벽 또는 장애물 만나기 직전에서 멈춤
- 도착 좌표에 처음 도달했을 때만 방문 처리

[예시]
- 입력: ["...D..R", ".D.G...", "....D.D", "D....D.", "..D...."]
- 출력: 7 (예: 아래 → 왼쪽 → 위 → 왼쪽 → 아래 → 오른쪽 → 위)

*/


import java.util.*;

class Solution {
    public int solution(String[] board) {
        int answer = 0;
        int[] start=new int[2];
        int[] end=new int[2];
        char[][] charBoard = new char[board.length][board[0].length()];;
        //charBoard 만들기
        createCharBoard(board,charBoard,start,end);
        answer = bfs(charBoard,start,end);
        return answer;
    }
    
    private void createCharBoard(String[] board,char[][] charBoard,int[]start,int[] end){
        for(int y=0;y<board.length;y++){
            char[] c = board[y].toCharArray();
            for(int x=0;x<board[0].length();x++){
                if(c[x]=='R') {start[0]=y;start[1]=x;}
                if(c[x]=='G'){end[0]=y;end[1]=x;}
                charBoard[y][x] = c[x];
            }
        }
    }
    
    //bfs
    private int bfs(char[][] charBoard,int[] start,int[] end){
        int[] dy = new int[]{-1,1,0,0};
        int[] dx = new int[]{0,0,-1,1};
        boolean[][] visited = new boolean[charBoard.length][charBoard[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0],start[1],0});
        visited[start[0]][start[1]] = true;
        
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int y = now[0];
            int x = now[1];
            int dist = now[2];
            
            if(y==end[0] && x==end[1])return dist;
            
            for(int d = 0; d< 4;d++){
                int ny = y;
                int nx = x;
                
                //미끄러짐 구현
                while(true){
                    int nny = ny+dy[d];
                    int nnx = nx+dx[d];
                    
                    if(nny <0 || nny >= charBoard.length || nnx <0 || nnx>= charBoard[0].length)break;
                    if(charBoard[nny][nnx]=='D')break;
                    
                    ny=nny;
                    nx=nnx;
                }
                
                
                if(visited[ny][nx]==false){
                    visited[ny][nx]=true;
                    queue.add(new int[]{ny,nx,dist+1});
                }
            }
        }
        
        return -1;
    }
}
