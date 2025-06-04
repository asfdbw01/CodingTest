/*
// 0 번째 유사 칸토어 비트열은 "1" 입니다.
// n(1 ≤ n) 번째 유사 칸토어 비트열은 n - 1 번째 유사 칸토어 비트열에서의 1을 11011로 치환하고 0을 00000로 치환하여 만듭니다.
// l, r이 주어졌을 때 그 구간 내의 1의 개수를 return
*/

class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        //5진수로 표혔했을 때 하나라도 2가 포함되어 있으면 해당 인덱스의 값은 0
        for(long i=l-1;i<r;i++){
            if(isOne(i))answer++;
        }
        return answer;
    }
    private boolean isOne(long n){
        while(n>=5){
            if(n%5==2)return false;
            n /=5;
        }
        return n!=2;
    }
}
