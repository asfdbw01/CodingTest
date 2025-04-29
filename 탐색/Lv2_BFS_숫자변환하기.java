// 문제: x를 y로 변환
// 주어진 연산 (+n, *2, *3) 중 최소 횟수로 x를 y로 만들기
// 불가능하면 -1 반환
// 알고리즘: BFS (최단거리 탐색)


import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[y+1];
        queue.add(new int[]{x,0}); 
        visited[x] = true;
        
        while (!queue.isEmpty()) {
            int[] num = queue.poll();
            
            if(num[0]==y)return num[1];
            int[] nextNums = {
                num[0] + n,
                num[0] * 2,
                num[0] * 3
            };
            
            for (int next : nextNums) {
                if (next <= y && !visited[next]) {
                    visited[next] = true;
                    queue.add(new int[]{next, num[1] + 1});
                }
            }  
        }
        
        return -1;
    } 
}
