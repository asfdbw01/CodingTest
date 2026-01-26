/*
문제
2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)

출력
첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        if(N==1){
            System.out.println(1);
            return;
        }
        int[] dp = new int[N+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3;i<=N;i++){
            dp[i] = (dp[i-1]+dp[i-2])%10007;
        }
        System.out.println(dp[N]);
    }
}