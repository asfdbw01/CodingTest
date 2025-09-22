/*
어떤 수열 x의 부분 수열(Subsequence)이란,
 x의 몇몇 원소들을 제거하거나 그러지 않고 남은 원소들이 원래 순서를 유지하여 
얻을 수 있는 새로운 수열을 말합니다.

다음과 같은 조건을 모두 만족하는 수열 x를 스타 수열이라고 정의합니다.
x의 길이가 2 이상의 짝수입니다. (빈 수열은 허용되지 않습니다.)
x의 길이를 2n이라 할 때, 다음과 같은 n개의 집합
 {x[0], x[1]}, {x[2], x[3]}, ..., {x[2n-2], x[2n-1]} 의 교집합의 원소의 개수가 1 이상입니다.
x[0] != x[1], x[2] != x[3], ..., x[2n-2] != x[2n-1] 입니다.
예를 들어, [1,2,1,3,4,1,1,3]은 스타 수열입니다. 
{1,2}, {1,3}, {4,1}, {1,3} 의 교집합은 {1} 이고, 각 집합 내의 숫자들이 서로 다르기 때문입니다.

1차원 정수 배열 a가 매개변수로 주어집니다.
 a의 모든 부분 수열 중에서 가장 길이가 긴 스타 수열의 길이를 
return 하도록 solution 함수를 완성해주세요.
 이때, a의 모든 부분 수열 중에서 스타 수열이 없다면, 0을 return 해주세요.
*/

import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int n = a.length;
        //길이 2보다 적으면 무조건 0
        if(n <2)return 0;
        
        HashMap<Integer,Integer> freq = new HashMap<>();
        for(int i:a)freq.put(i,freq.getOrDefault(i,0)+1);
        
        for(Map.Entry<Integer, Integer> e  : freq.entrySet()){
            int v = e.getKey();
            int f = e.getValue();
            
            //가지치기
            if(f*2 <= answer)continue;
            
            int pair = 0;
            int i=0;
            while(i<n-1){
                //값 같으면 안됨
                if(a[i]==a[i+1]){
                    i++;
                    continue;
                }
                
                //둘중하나 v면 쌍을 만듬
                if(a[i]==v || a[i+1]==v){
                    pair++;
                    i+=2;
                }else{
                    i++;
                }
            }
            answer = Math.max(pair,answer);
        }
       
        return answer*2;
    }
}