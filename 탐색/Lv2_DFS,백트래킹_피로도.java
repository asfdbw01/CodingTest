/**
 * 💡 문제 요약 - 피로도 기반 던전 탐험 최대 개수 구하기
 *
 * - 유저는 피로도(k)를 가지고 있고, 여러 개의 던전이 있음
 * - 각 던전은 [최소 필요 피로도, 소모 피로도] 로 구성됨
 *   - 최소 필요 피로도: 던전에 진입하기 위해 현재 피로도가 이 이상이어야 함
 *   - 소모 피로도: 던전을 클리어하면 감소하는 피로도
 * - 던전마다 하루에 한 번만 들어갈 수 있음
 * - 어떤 순서로 던전을 탐험해야 가장 많이 탐험할 수 있을까?
 *
 * ✔ 완전탐색(백트래킹/DFS)을 통해 모든 순열 탐색 필요 (최대 8! = 40320가지)
 *
 * 🧠 예시:
 * k = 80
 * 던전 = [ [80,20], [50,40], [30,10] ]
 * -> 최대 3개까지 탐험 가능
 */


import java.util.*;

class Solution {
    
    static boolean[] visited;
    static int max = 0;

    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        visited = new boolean[dungeons.length];  
        dfs(k,0, dungeons);
        answer = max;
        return answer;
    }
    
    public static void dfs(int fatigue, int depth, int[][]dungeons) {
        max = Math.max(max,depth);

        for (int i = 0; i < dungeons.length; i++) {
            int min = dungeons[i][0];
            int cost = dungeons[i][1];
            
            if(!visited[i]&& fatigue >= min){
                visited[i] = true;
                dfs(fatigue-cost, depth+1, dungeons);
                visited[i] = false;
                
            }
        }
    }
}
