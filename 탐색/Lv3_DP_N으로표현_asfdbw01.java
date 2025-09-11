/*
숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중
 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.

제한사항
N은 1 이상 9 이하입니다.
number는 1 이상 32,000 이하입니다.
수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
최솟값이 8보다 크면 -1을 return 합니다.
*/

import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if (N == number) return 1;
        int answer = -1;
        List<HashSet<Integer>> dp=  new ArrayList<HashSet<Integer>>();
        
    	
        for(int i=0; i<=8; i++)
        	dp.add(new HashSet<Integer>());
        dp.get(1).add(N);
        
        answer= returnAnsnwerAndMakeDp( dp,N,number);
        return answer;
    }
    
    
    private int returnAnsnwerAndMakeDp(List<HashSet<Integer>> dp,int N,int number){
        for(int i = 2; i<=8;i++){
            int concat = 0;
            for (int k = 0; k < i; k++) concat = concat * 10 + N;
            dp.get(i).add(concat);
            
            for(int j=1;j<i;j++){
                HashSet<Integer> A = dp.get(j);
                HashSet<Integer> B = dp.get(i-j);
                for(int a:A){
                    for(int b:B){
                        dp.get(i).add(a+b);
                        dp.get(i).add(a-b);
                        dp.get(i).add(b-a);
                        dp.get(i).add(a*b);
                        if(b!=0 && a/b==(double)a/b)dp.get(i).add(a/b);
                        if(a!=0 && b/a ==(double)b/a)dp.get(i).add(b/a);
                    }
                } 
            }
            
            if(dp.get(i).contains(number))return i;
        }
        return -1;
    }
}