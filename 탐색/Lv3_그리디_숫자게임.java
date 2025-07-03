/*
[문제 요약]
- A팀과 B팀이 1:1로 N번의 게임을 함 (N ≤ 100,000)
- A팀의 출전 순서는 고정, B팀은 순서를 최적화 가능
- 각 경기에서 A[i] < B[j] 이면 B팀이 승점 1
- B팀이 얻을 수 있는 **최대 승점**을 구하라

[규칙]
- A[i], B[j]는 각각 자연수 (1~1,000,000,000)
- A와 B는 각각 길이가 N인 배열
- A와 B는 서로 다른 팀의 선수들이 받는 숫자

[아이디어]
- A를 정렬, B를 정렬한 뒤
- A를 순회하며 이길 수 있는 가장 작은 B를 매칭
- 투포인터 또는 그리디 전략으로 진행

[예시]
A = [5,1,3,7], B = [2,2,6,8]
→ A: 1 3 5 7
→ B: 2 2 6 8
→ 결과: 3

[출력]
- B팀이 얻을 수 있는 최대 승점
*/
import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        
        int aIdx =0,bIdx = 0;
        
        while(aIdx < A.length && bIdx<B.length){
            if(B[bIdx]>A[aIdx]){
                answer++;
                aIdx++;
                bIdx++;
            }else{
                bIdx++;
            }
        }
        
        return answer;
    }
}
