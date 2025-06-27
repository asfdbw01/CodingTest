/*
문제 요약 - 네트워크 (DFS/BFS)

- '네트워크'란 컴퓨터들이 서로 직접 또는 간접적으로 연결되어 있는 집합을 의미한다.
- 2차원 배열 `computers[i][j] == 1`이면 i번 컴퓨터와 j번 컴퓨터가 연결되어 있다.
- i == j인 경우는 항상 1이다. (자기 자신과는 항상 연결됨)
- 직접 연결이 아니더라도, 중간에 연결된 컴퓨터가 있으면 같은 네트워크로 간주한다.
- 주어진 연결 정보로부터 **총 몇 개의 네트워크(연결된 컴퓨터 집합)**가 존재하는지 구하라.

입력:
- n: 컴퓨터 수 (1 ≤ n ≤ 200)
- computers: n x n 2차원 인접 행렬 (각 원소는 0 또는 1)

출력:
- 네트워크의 개수 (int)

예시:
입력: n = 3, computers = [[1,1,0],[1,1,0],[0,0,1]]
출력: 2  → (0-1이 연결, 2는 단독)

입력: n = 3, computers = [[1,1,0],[1,1,1],[0,1,1]]
출력: 1  → (0-1-2가 모두 연결됨)

풀이 방법:
- 모든 컴퓨터를 순회하며 방문하지 않은 컴퓨터를 발견하면 DFS 시작
- DFS로 이어진 모든 컴퓨터를 방문 처리
- DFS 호출 횟수가 곧 네트워크의 개수
*/
import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        answer = countNetworks(visited, n, computers);
        return answer;
    }
    private void dfs(int node, int[][] computers, boolean[] visited){
        visited[node] = true;
        
        for(int j=0;j<computers[node].length;j++){
            if(computers[node][j]==1 && visited[j]==false){
                dfs(j, computers,  visited);
            }
        }
    }
    
    private int countNetworks(boolean[] visited,int n, int[][] computers){
        int cnt = 0;
        for(int node=0;node<n;node++){
            if(visited[node]==false){
                dfs(node, computers, visited);
                cnt++;
            }
        }
        return cnt;
    }
}
