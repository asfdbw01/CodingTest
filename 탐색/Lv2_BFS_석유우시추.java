/*
//세로길이가 n 가로길이가 m인 격자 모양의 땅 속에서 석유가 발견되었습니다.
//당신이 시추관을 수직으로 단 하나만 뚫을 수 있을 때, 가장 많은 석유를 뽑을 수 있는 시추관의 위치를 찾으려고 합니다.
//시추관은 열 하나를 관통하는 형태여야 하며, 열과 열 사이에 시추관을 뚫을 수 없습니다.
//시추관이 석유 덩어리의 일부를 지나면 해당 덩어리에 속한 모든 석유를 뽑을 수 있습니다.
//시추관이 뽑을 수 있는 석유량은 시추관이 지나는 석유 덩어리들의 크기를 모두 합한 값입니다.
*/

import java.util.*;

class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        Set<Integer> set = new LinkedHashSet<>();
        int[] sumCol = new int[land[0].length];
        //bfs사용해서 각 열별 시추량 뽑고 answer 과 크기 비교
        boolean[][] visited =new boolean[land.length][land[0].length];
        
        for(int i=0;i<land[0].length;i++){
            for(int j=0;j<land.length;j++){
                if(visited[j][i]==false && land[j][i]==1){
                    int value =  bfs(land,visited,j,i,set);  
                    addSumCol(value,set,sumCol );
                }
            }
        }
        
        for(int i=0;i<sumCol.length;i++){
            answer = Math.max(answer,sumCol[i]);
        }
        
        return answer;
    }
    
    //프로그래머스 메모리가 생각보다 작네 ㅋㅋ bfs로 바꿔라 애송이
    private int bfs(int land[][],boolean visited[][],int y,int x,Set<Integer> set){
        int[] dy = {-1,1,0,0};
        int[] dx = {0,0,-1,1};
        int sum =0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y,x});
        visited[y][x] = true;
        if (land[y][x] == 1) {sum++;set.add(x);}
        while(!queue.isEmpty()){
            int cur[] = queue.poll();
            int cy = cur[0],cx = cur[1];
            
             for(int d=0;d<4;d++){
                int ny = cy+dy[d];
                int nx = cx+dx[d];
                if(ny<0 || ny >= land.length || nx <0 || nx >= land[0].length )continue;
                if(land[ny][nx] == 0|| visited[ny][nx])continue;
                visited[ny][nx] = true;
                queue.add(new int[]{ny,nx});
                if (land[ny][nx] == 1) {sum++;set.add(nx);}
            }
        }
       
        return sum;
    }
    
    //dfs 값 set 사용해서 sumCol 에 += 로 넣어주는 메소드
    private void addSumCol(int value,Set<Integer> set,int[] sumCol ){
        for(int col : set){
            sumCol[col] += value;
        }
        set.clear();
    }
}
