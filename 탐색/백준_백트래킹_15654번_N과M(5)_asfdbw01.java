/*
문제
N개의 자연수와 자연수 M이 주어졌을 때, 
아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

N개의 자연수 중에서 M개를 고른 수열
같은 수를 여러 번 골라도 된다.
입력
첫째 줄에 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 7)

둘째 줄에 N개의 수가 주어진다. 
입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.

출력
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 
중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static int[] arr;
    static StringBuilder out = new StringBuilder();
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        makeArr(N, br);
        Arrays.sort(arr);
        //for(int i : arr)System.out.print(i+"-");
        //System.out.println();
        int[] pick = new int[M];
        backTracking(M, N, pick, 0);
        System.out.println(out.toString());
    }

    private static void backTracking(int M,int N,int[] pick,int depth){
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                if (i > 0) out.append(' ');
                out.append(pick[i]);
            }
            out.append('\n');
            return;
        }

        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if(arr[i] == prev)continue;
            prev = arr[i];

            pick[depth]  = arr[i];
            backTracking(M, N, pick, depth+1);

        }
    }

    private static void makeArr(int N, BufferedReader br) throws IOException{
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}