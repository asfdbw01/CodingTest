/*
 * 📄 최소 비용으로 모든 섬을 연결하기 (MST - 크루스칼)
 *
 * 문제 요약
 * ----------
 * n개의 섬과 섬 사이의 다리 건설 비용이 주어질 때,
 * 모든 섬이 서로 통행 가능하도록 최소의 비용으로 다리를 설치한다.
 * (다리를 여러 번 거쳐 도달 가능하면 OK)
 *
 * 입력
 * -----
 * n: 섬의 개수 (1 ≤ n ≤ 100)
 * costs: int[][] 형태의 [섬1, 섬2, 비용] 배열
 *
 * 출력
 * -----
 * 최소 비용 (int)
 *
 * 조건
 * -----
 * - 모든 섬을 반드시 연결해야 함
 * - 연결 가능한 경우만 주어짐 (즉, MST가 항상 존재)
 * - costs는 간선의 비용 오름차순으로 처리
 *
 * 풀이
 * -----
 * ✔ 간선(다리)을 비용순으로 정렬
 * ✔ 작은 비용의 간선부터 선택하면서 연결
 * ✔ 연결 시 사이클이 발생하지 않으면 채택
 * ✔ 사이클 판별은 Union-Find 사용
 * ✔ 간선 수 = n - 1이 되면 종료
 */


import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        //간선숫자  = 노드 숫자 -1
        //최소 거리 정렬 후 작은순서대로 연결
        //연결시 사이클 발생 : 연결 x -> 다음 작은거 연결
        //사이클 발생 여부는 유니온 파인드 사용
        
        //거리순 정렬
        Arrays.sort(costs,(a,b) -> a[2]-b[2]);
        //유니온 테이블 생성
        int[] table =  initTable (n);
        answer = sumCost(costs,table,n);
        
        return answer;
    }
    
    private int[] initTable (int n){
        int [] table = new int[n];
        for(int i=0;i<n;i++){
            table[i]=i;
        }
        return table;
    }
    
    private int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }

    private void union(int[] parent, int x, int y) {
        int px = find(parent, x);
        int py = find(parent, y);
        if (px != py) parent[py] = px;
    }

    private boolean isCycle(int[] parent, int x, int y) {
        return find(parent, x) == find(parent, y);
    }


    private int sumCost(int[][] costs, int[] parent, int n) {
        int sum = 0;
        int count = 0;
        int maxEdges = n - 1;

        for (int[] cost : costs) {
            if (!isCycle(parent, cost[0], cost[1])) {
                union(parent, cost[0], cost[1]);
                sum += cost[2];
                count++;
                if (count == maxEdges) break;
            }
        }
        return sum;
    }

}
