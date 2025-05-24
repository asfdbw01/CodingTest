/*
문제 요약 - 줄 서는 방법 중 k번째 순열 구하기

[문제 설명]
- 1부터 n까지 번호가 매겨진 n명이 일렬로 줄을 서는 모든 경우(=순열)는 총 n!가지
- 이 순열들을 **사전 순으로 정렬했을 때**, k번째에 해당하는 줄 서는 방법을 반환하는 문제

[입력]
- int n : 사람의 수 (1 ≤ n ≤ 20)
- long k : 사전 순으로 정렬했을 때 찾고자 하는 순열의 위치 (1-based, 1 ≤ k ≤ n!)

[출력]
- int[] : 사전 순으로 정렬한 순열 중 k번째 순열을 배열 형태로 반환

[핵심 아이디어]
- 전체 순열을 생성하지 않고도 자리마다 그룹을 수학적으로 나눠서 빠르게 k번째 순열을 계산 가능
- 자리마다 (n-1)! 만큼의 경우의 수가 반복되므로, 이를 기준으로 선택할 숫자를 결정
- 선택된 숫자는 사용 리스트에서 제거하고, 남은 숫자에 대해 반복

[예시]
- n = 3, k = 5
- 사전순 순열: [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]
- 결과: [3,1,2]
*/


import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> numbers = new ArrayList<>();
        for(int i=1;i<=n;i++){
            numbers.add(i);
        }
        
        k--;
        
        for(int i=0; i< n ; i++){
            long fact = factorial(n-1-i);
            int idx = (int)(k/fact);
            answer[i] = numbers.get(idx);
            numbers.remove(idx);
            k%=fact;
        }
        return answer;
    }
    
    private long factorial(long n){
        if(n<=1)return 1;
        return n * factorial(n-1);
    }
}
