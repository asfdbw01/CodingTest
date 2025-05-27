/*
2*n 문제의 3*n 버전임
*/

class Solution {
    public int solution(int n) {
        int answer = 0;
        //n이 홀수인 경우 존재 불가능
        //n 가지수 = n-2칸 경우수 * 3  + 대칭칸 
        // 대칭칸 = n-4만한 대칭칸 *2 + n-6 대칭칸 *2 ...
        final long MOD = 1_000_000_007;
        long[] numberOfCases = new long[n+1];
        numberOfCases[0]=1;
        numberOfCases[2]=3;
        
        if(n%2!=0)return 0;
        
        //누적합 배열
        long[] acc = new long[n + 1];
        acc[0] = numberOfCases[0];
        acc[2] = numberOfCases[0] + numberOfCases[2];
        
        for(int i=4;i<=n;i+=2){
            numberOfCases[i] = (numberOfCases[i - 2] * 3 % MOD + acc[i - 4] * 2 % MOD) % MOD;
            acc[i] = (acc[i - 2] + numberOfCases[i]) % MOD;
        }
        
        answer = (int)numberOfCases[n];
        return answer;
    }
}
