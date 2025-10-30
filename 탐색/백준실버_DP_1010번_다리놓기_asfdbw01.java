/*
강 주변에서 다리를 짓기에 적합한 곳을 사이트라고 한다. 
재원이는 강 주변을 면밀히 조사해 본 결과 강의 서쪽에는 N개의 사이트가 있고 
동쪽에는 M개의 사이트가 있다는 것을 알았다. (N ≤ M)

재원이는 서쪽의 사이트와 동쪽의 사이트를 다리로 연결하려고 한다. 
(이때 한 사이트에는 최대 한 개의 다리만 연결될 수 있다.) 
재원이는 다리를 최대한 많이 지으려고 하기 때문에 서쪽의 사이트 개수만큼 (N개) 다리를 지으려고 한다.
다리끼리는 서로 겹쳐질 수 없다고 할 때 다리를 지을 수 있는 경우의 수를 구하는 프로그램을 작성하라.

입력
입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다. 
그 다음 줄부터 각각의 테스트케이스에 대해 
강의 서쪽과 동쪽에 있는 사이트의 개수 정수 N, M (0 < N ≤ M < 30)이 주어진다.


*/

import java.io.IOException;
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int read =0;

        int[][] dp = makeDp();

        while (read < N) {
            
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            sb.append(dp[m][n]+"\n");
            read++;
        }
        
        System.out.println(sb.toString());

    }

    private static int[][] makeDp(){
        int[][] dp = new int[30][30];
        for(int i=1;i<30;i++)dp[i][1] = i;
        for(int j=1;j<30;j++)dp[1][j] = j;
        for(int i=2;i<30;i++){
            for(int j=1;j<30;j++){
                for(int k=i-1;k>=j-1;k--){
                    dp[i][j] += dp[k][j-1];
                }
            }
        }
        return dp;
    }
}