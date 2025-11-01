/*
이 게임은 지금까지 나온 게임들과는 다르게 
ACM크래프트는 다이나믹한 게임 진행을 위해 건물을 짓는 순서가 정해져 있지 않다. 
즉, 첫 번째 게임과 두 번째 게임이 건물을 짓는 순서가 다를 수도 있다. 
매 게임시작 시 건물을 짓는 순서가 주어진다.
또한 모든 건물은 각각 건설을 시작하여 완성이 될 때까지 Delay가 존재한다.

프로게이머 최백준은 애인과의 데이트 비용을 마련하기 위해 
서강대학교배 ACM크래프트 대회에 참가했다! 
최백준은 화려한 컨트롤 실력을 가지고 있기 때문에 모든 경기에서 특정 건물만 짓는다면 
무조건 게임에서 이길 수 있다. 
그러나 매 게임마다 특정건물을 짓기 위한 순서가 달라지므로 최백준은 좌절하고 있었다. 
백준이를 위해 특정건물을 가장 빨리 지을 때까지 걸리는 최소시간을 알아내는 프로그램을 작성해주자.


입력
첫째 줄에는 테스트케이스의 개수 T가 주어진다. 
각 테스트 케이스는 다음과 같이 주어진다. 
첫째 줄에 건물의 개수 N과 건물간의 건설순서 규칙의 총 개수 K이 주어진다. 
(건물의 번호는 1번부터 N번까지 존재한다) 

둘째 줄에는 각 건물당 건설에 걸리는 시간 D1, D2, ..., DN이 공백을 사이로 주어진다. 
셋째 줄부터 K+2줄까지 건설순서 X Y가 주어진다. 
(이는 건물 X를 지은 다음에 건물 Y를 짓는 것이 가능하다는 의미이다) 

마지막 줄에는 백준이가 승리하기 위해 건설해야 할 건물의 번호 W가 주어진다.

*/

import java.io.IOException;
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        while(cnt <N){
            cnt++;
            st = new StringTokenizer(br.readLine());
            int buileCnt = Integer.parseInt(st.nextToken());
            int ruleCnt = Integer.parseInt(st.nextToken());
            int[] buildCost = makeBuildCost(buileCnt, br);
            int[] indegree = new int[buileCnt + 1];
            List<List<Integer>> graph = makeGraph(ruleCnt, buileCnt , br,indegree) ;
            st = new StringTokenizer(br.readLine());
            int finalBuild = Integer.parseInt(st.nextToken());
            int[] dp = new int[buileCnt+1]; // np[n]은 n번쨰 건물 짓는데 필요한 시간
            topologicalSort(indegree,   graph, dp,buildCost,buileCnt);
            sb.append(dp[finalBuild]+"\n");
        }
        System.out.println(sb.toString());
    }
    
     static void topologicalSort(int[] indegree, List<List<Integer>> array,int[] dp,int[] buildCost,int buileCnt) {
        Queue<Integer> q = new LinkedList<Integer>();

        for(int i=1;i<=buileCnt;i++){
            if(indegree[i]==0){
                q.offer(i);
                dp[i] = buildCost[i];
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            
            for(Integer i : array.get(node)){
                indegree[i]--;
                dp[i] = Math.max(dp[i],(dp[node]+buildCost[i]));
                if(indegree[i]==0){
                    q.offer(i);
                }
            }
        }
     }

    
    private static List<List<Integer>> makeGraph(int ruleCnt,int buileCnt ,BufferedReader br,int[] indegree) throws IOException{
        
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<buileCnt+1;i++)graph.add(new ArrayList<>());
        for(int i=0;i<ruleCnt;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph.get(s).add(e);
            indegree[e]++;
        }
        return graph;
    }

    private static int[] makeBuildCost(int buileCnt,BufferedReader br) throws IOException{
        int[] buildCost = new int[buileCnt+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<buileCnt;i++)buildCost[i+1] = Integer.parseInt(st.nextToken());
        return buildCost;
    }

}