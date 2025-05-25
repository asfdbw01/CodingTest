/*
문제 요약 - 병사 소모 & 무적권 사용 디펜스 게임

[문제 설명]
- 병사 n명을 가지고 적의 연속 공격을 막는 게임
- 각 라운드마다 enemy[i]명의 적이 등장
  - 병사가 enemy[i] 이상이면 소모해서 막음
  - 부족하면 게임 종료
- 병사 소모 없이 막을 수 있는 "무적권"이 최대 k번 사용 가능
- 무적권을 적절히 사용해 **최대한 많은 라운드를 막는 것이 목표**

[입력]
- int n: 초기 병사 수 (1 ≤ n ≤ 1,000,000,000)
- int k: 무적권 사용 가능 횟수 (1 ≤ k ≤ 500,000)
- int[] enemy: 각 라운드 적의 수 (길이 최대 1,000,000, 각 원소 ≤ 1,000,000)

[출력]
- int: 막을 수 있는 최대 라운드 수

[핵심 포인트]
- **무적권은 가장 많은 적에게 사용하는 것이 유리** (그리디 전략)
- 각 라운드마다 적의 수를 최대 힙에 저장
  - 병사 부족하면, 지금까지 가장 큰 적군에 무적권을 소급 적용 (최대 힙 poll)
- 무적권 다 쓰고도 병사가 부족하면 그때 종료
- 전형적인 **그리디 + 우선순위 큐 + 시뮬레이션 구현 문제**

[예시]
- n = 7, k = 3, enemy = [4, 2, 4, 5, 3, 3, 1]
- 무적권을 1, 3, 4 라운드에 쓰고, 2, 5 라운드 병사로 막으면 총 5라운드 가능 → 결과 5
*/


import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        //내림차순힙에 적군수 저장해놓고 더이상 진행 못할때 무적권 사용 + 힙 값 더해서 진행
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<enemy.length;i++){
            pq.add(enemy[i]);
            n-=enemy[i];
            
            if(n<0){
                if(k>0){
                    n+=pq.poll();
                    k--;
                }
                else{
                    return i;
                }
            }
        }
        return enemy.length;
    }
}
