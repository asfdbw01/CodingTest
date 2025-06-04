/*
문제 요약:
- 각 칸에 'S', 'L', 'R'이 쓰인 격자(grid)에서 빛을 쏴서 사이클을 찾는 문제.
- 모든 칸에서 4방향(오른쪽, 위, 왼쪽, 아래)으로 빛을 쏴보고,
  아직 방문하지 않은 방향이면 빛을 따라가면서 사이클 길이를 구한다.

이동 규칙:
- 'S': 현재 방향으로 직진
- 'L': 현재 방향 기준 왼쪽으로 회전 (dir = (dir + 3) % 4)
- 'R': 현재 방향 기준 오른쪽으로 회전 (dir = (dir + 1) % 4)

격자 경계:
- 격자 밖으로 나가면 반대편으로 이어진다 (토러스 형태: wrap-around)

사이클 판별:
- 방문 여부는 [방향][y][x]로 3차원 배열에 기록
- 이미 방문한 [방향][y][x] 조합에 다시 도달하면 사이클이므로 종료

출력:
- 만들어지는 모든 사이클의 길이를 오름차순 정렬하여 반환
*/


import java.util.*;

class Solution {
    public int[] solution(String[] grid) {
        int[] answer = {};
        int height = grid.length, width = grid[0].length();
        boolean[][][] visited = new boolean[4][height][width]; 
        List<Integer> lengthList = new ArrayList<>();
        
        for(int d=0;d<4;d++){
            for(int y=0;y<height;y++){
                for(int x=0;x<width;x++){
                    if(visited[d][y][x]==false){
                        DFS(y,x, d,lengthList,visited,grid);
                    }
                }
            }
        }
        Collections.sort(lengthList);
        answer = new int [lengthList.size()];
        answer = lengthList.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
    //3차원 DFS
    
    private void DFS(int y,int x,int dir,List<Integer> lengthList,boolean[][][] visited,String[] grid){
        int[] dx = {1,0,-1,0};
        int[] dy = {0,-1,0,1};
        int distence=0;
        int height = grid.length, width = grid[0].length();
        
        while(!visited[dir][y][x]){
            visited[dir][y][x] = true;
            
            //방향 체크
            char c = grid[y].charAt(x);
            if(c=='R'|| c=='L')dir = changeDir(c,dir);
            int ny = y+dy[dir];
            int nx = x+dx[dir];
            //범위 체크
            if(ny >= height || ny <0 || nx >= width || nx <0){
                int[] nextXY = overRangeToNext(ny,nx,height, width);
                ny = nextXY[0];
                nx = nextXY[1];
            }
                
            distence++;
            y=ny;
            x=nx;
            
        }
        if(distence>0)lengthList.add(distence);
    }
    
    //칸 넘어가는거 처리
    private int[] overRangeToNext(int ny,int nx,int height,int width){
        //오른쪽 범위 아웃
        if(nx>=width)return new int[]{ny,0};
        //왼쪽 범위 아웃
        if(nx <0)return new int[]{ny,width-1};
        //위쪽 범위 아웃
        if(ny<0)return new int[]{height-1,nx};
        //아래쪽 범위 아웃
        if(ny>=height)return new int[]{0,nx};
        return new int[]{ny,nx};
    }
    //방향 처리
    private int changeDir(char c,int dir){
        if(c=='R')return (dir +1)%4;
        if(c=='L')return (dir+3)%4;
        return dir;
    }
}
