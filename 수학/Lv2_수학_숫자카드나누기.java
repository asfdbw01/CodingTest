/*
문제 요약:
- 두 사람이 숫자가 적힌 카드를 반씩 나눠 가짐 (arrayA: 철수, arrayB: 영희).
- 다음 두 조건 중 하나를 만족하는 **가장 큰 양의 정수 a**를 구해야 함:

  1. a는 철수의 모든 카드 숫자를 나눌 수 있어야 하고,
     영희의 카드 숫자들 중 **어느 하나도 나눌 수 없어야 함**.

  2. a는 영희의 모든 카드 숫자를 나눌 수 있어야 하고,
     철수의 카드 숫자들 중 **어느 하나도 나눌 수 없어야 함**.

- 조건을 만족하는 값 중 **가장 큰 정수 a**를 반환.
- 조건을 만족하는 값이 없다면 0을 반환.

제약 사항:
- 배열 길이: 최대 500,000
- 숫자 범위: 최대 100,000,000
- 배열에 중복 가능
*/

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        //최대 공약수 구하기
        // 최대 공약수가 다른 array를 나눌수 없으면 그거임 ㅇㅇ
        int aGcd = arrayGcd(arrayA);
        int bGcd = arrayGcd(arrayB);
        
        if(!canDiv(aGcd,arrayB))answer=aGcd;
        if(!canDiv(bGcd,arrayA))answer = Math.max(aGcd,bGcd);
        
        System.out.println(aGcd +" "+bGcd);
        
        
        return answer;
    }
    
    private int gcd(int big,int small){
        while(small!=0){
            int tmp = big % small;
            big = small;
            small = tmp;
        }
        return big;
    }
    
    private int arrayGcd(int[] arr){
        int maxDivisor = arr[0];
        for(int i=1;i<arr.length;i++){
            int big = (maxDivisor>arr[i])?maxDivisor:arr[i];
            int small = (maxDivisor<arr[i])?maxDivisor:arr[i];
            maxDivisor = gcd(big,small);
        }
        return maxDivisor;
    }
    
    private boolean canDiv(int divisor,int[] arr){
        for(int i=0;i<arr.length;i++){
            if(arr[i]%divisor==0)return true;
        }
        return false;
    }
    
}
