// 문제 요약 — 다트(카운트다운)
// - 목표 점수 target을 다트로 정확히 0이 되게 만든다.
// - 한 번 던질 때 가능한 점수: 싱글(1~20), 더블(2*1~2*20), 트리플(3*1~3*20), 불(50)
// - 우선순위: (1) 던진 횟수 최소, (2) 동률이면 ‘싱글 또는 불’ 횟수 최대
//
// 제약
// - 1 ≤ target ≤ 100000
//
// 핵심 아이디어(언바운디드 DP)
// - 점수 집합 S(1..60 중 실제로 만들 수 있는 점수 + 50)를 미리 구성
// - dpScore[t] = t점을 만드는 최소 다트 수
// - dpSB[t]    = 위 최소 다트 수일 때 ‘싱글/불’ 횟수
// - 전이: 모든 s ∈ S 에 대해
//     candD  = dpScore[t - s] + 1
//     candSB = dpSB[t - s] + (s가 싱글/불이면 1, 아니면 0)
//   더 좋은 해로 갱신: candD가 작거나, 같으면 candSB가 큰 경우 채택
//
// 초기값
// - dpScore[0] = 0, dpSB[0] = 0
//
// 복잡도
// - |S| ≤ ~42, 전체 O(target * |S|) ≈ O(target)
// - 메모리 O(target)
//
// 구현 포인트
// - ‘만들 수 있는 점수’ 판정: n==50 || 1≤n≤20 || (n%2==0 && 1≤n/2≤20) || (n%3==0 && 1≤n/3≤20)
// - 싱글/불 여부 표기(isSB[n])로 보조 지표 누적
// - BFS로도 가능하나, 동점 2차 기준 처리 때문에 DP가 더 간결/안전


import java.util.*;

class Solution {
    public int[] solution(int target) {
        int[] answer = {};
        //23,25,29,31,35,37,41,43,47,49,53,55,59 -> 2번 
        boolean[] isSB = new boolean[61]; // 그 점수를 싱글/불로 낼 수 있으면 true
        for(int i=1;i<21;i++){
            isSB[i] = true;
        }
        isSB[50]=true;
        
        List<Integer> scores = new ArrayList<>();
        for(int i=1;i<61;i++){
            if(isMakeable(i))scores.add(i);
        }
        
        // 2) DP
        final int INF = 1_000_000_000;
        int[] dpScore = new int[target + 1];
        int[] dpSB    = new int[target + 1];
        Arrays.fill(dpScore, INF);
        dpScore[0] = 0; dpSB[0] = 0;
        
        for(int t=1;t<target+1;t++){
            int bestD = INF, bestSB = 0;
            for (int s : scores) {
                if (s > t) continue;
                int candD  = dpScore[t - s] + 1;
                int candSB = dpSB[t - s] + (isSB[s] ? 1 : 0);
                if (candD < bestD || (candD == bestD && candSB > bestSB)){
                    bestD = candD; bestSB = candSB;
                }
            }
            dpScore[t] = bestD;
            dpSB[t] = bestSB;
        }
        answer =  new int[]{ dpScore[target], dpSB[target] };
        return answer;
    }
    
    //한번에 가능한지
    private boolean isMakeable(int n){
        if(n==50)return true;
        if(n>=1 && n<=20)return true;
        if(n%2==0 && n/2>=1 && n/2<=20)return true;
        if(n%3==0 && n/3>=1 && n/3<=20)return true;
        return false;
    }
    
    
}