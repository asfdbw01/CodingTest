/*
문제
준규가 가지고 있는 동전은 총 N종류이고, 각각의 동전을 매우 많이 가지고 있다.

동전을 적절히 사용해서 그 가치의 합을 K로 만들려고 한다. 
이때 필요한 동전 개수의 최솟값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N과 K가 주어진다. (1 ≤ N ≤ 10, 1 ≤ K ≤ 100,000,000)

둘째 줄부터 N개의 줄에 동전의 가치 Ai가 오름차순으로 주어진다. 
(1 ≤ Ai ≤ 1,000,000, A1 = 1, i ≥ 2인 경우에 Ai는 Ai-1의 배수)

출력
첫째 줄에 K원을 만드는데 필요한 동전 개수의 최솟값을 출력한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static int[] moneys;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        makeMoneysArr(N, br);
        int coinsCnt = cntCoin(N, K);
        System.out.println(coinsCnt);
    }

    private static void makeMoneysArr( int N,BufferedReader br )throws IOException{
        moneys = new int[N];
        for(int i=0;i<N;i++)moneys[i] = Integer.parseInt(br.readLine());
    }

    private static int cntCoin(int N,int K){
        int cnt=0;
        int idx = N-1;

        while(K>0){
            if(moneys[idx]>K){
                idx--;
                continue;
            }
            K -= moneys[idx];
            cnt++;
        }
        return cnt;
    }
}