/*
//지도에는 바다와 무인도들에 대한 정보가 표시돼 있습니다. 
//지도는 1 x 1크기의 사각형들로 이루어진 직사각형 격자 형태이며 
//격자의 각 칸에는 'X' 또는 1에서 9 사이의 자연수가 적혀있습니다.
//지도의 'X'는 바다를 나타내며, 숫자는 무인도를 나타냅니다. 이때, 상, 하, 좌, 우로 연결되는 땅들은 하나의 무인도를 이룹니다.
//지도의 각 칸에 적힌 숫자는 식량을 나타내는데, 상, 하, 좌, 우로 연결되는 칸에 적힌 숫자를 모두 합한 값은 해당 무인도에서 최대 며칠동안 머물 수 있는지를 나타냅니다. 
/각 섬에서 최대 며칠씩 머물 수 있는지 오름차순 리턴
*/


import java.util.*;

class Solution {
    public int[] solution(String[] maps) {
        int[] answer = {};
        int[][] intMaps = new int[maps.length][maps[0].length()];
        makeIntMaps(maps,intMaps);
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        //answer 들어갈 섬 크기저장용 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        islandSizeToPq (intMaps,visited,pq );
        
        
        if(pq.size() ==0)return new int[]{-1};
        answer = new int[pq.size()];
        for(int i=0;i<answer.length;i++){
            answer[i] = pq.poll();
        }
        return answer;
    }
    
    //intMap 만들기 0 = 바다 
    private void makeIntMaps(String[] maps,int[][] intMaps){
        for(int i=0;i<intMaps.length;i++){
            char[] mapsIarray = maps[i].toCharArray();
            for(int j=0;j<intMaps[i].length;j++){
                if(mapsIarray[j] == 'X')intMaps[i][j] = 0;
                else intMaps[i][j] = mapsIarray[j] - '0';
            }
        }
    }
    //bfs 구현 
    private int bfs(int[][] intMaps,boolean[][] visited,int y,int x){
        int[] dy = new int[]{-1,1,0,0};
        int[] dx = new int[]{0,0,-1,1};
        int result = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y,x});
        visited[y][x] = true;
        result += intMaps[y][x];
        
        while(!queue.isEmpty()){
            int cur[] = queue.poll();
            int cy = cur[0],cx = cur[1];
            
            for(int d = 0; d < 4 ; d++){
                int ny = cy+dy[d];
                int nx = cx + dx[d];
                
                if(ny <0 || ny>= intMaps.length || nx< 0 || nx>=intMaps[0].length)continue;
                if(visited[ny][nx]==true || intMaps[ny][nx]==0)continue;
                visited[ny][nx] = true;
                queue.add(new int[]{ny,nx});
                if(intMaps[ny][nx] != 0)result += intMaps[ny][nx];
            }
        }
        
        return result;
    }
    
    //섬 별 크기 구해서 pq로 넘김
    private void islandSizeToPq (int[][] intMaps,boolean[][] visited,PriorityQueue<Integer> pq ){
        for(int y=0;y<intMaps.length;y++){
            for(int x=0;x<intMaps[y].length;x++){
                if(intMaps[y][x]!=0 && !visited[y][x])pq.add(bfs(intMaps,visited,y,x));
            }
        }
    }
}
