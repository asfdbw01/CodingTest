/*
크기 N의 정수 배열 A가 있다. 
다음 조건을 만족하도록 배열을 연속 구간으로 분할하는 것이 가능한지 판단하시오.

 배열의 모든 원소가 정확히 하나의 구간에 포함된다.
 각 구간의 크기는 1이상 N 미만이며, 모든 구간의 크기는 같다.
 각 구간에서 최솟값과 최댓값을 더한 값이 모든 구간에서 같다.

첫째 줄에 배열의 크기 N이 주어진다. 

둘째 줄에 A 의 원소 A1,A2,...AN이 공백으로 구분되어 주어진다. 
조건을 만족하도록 배열을 분할하는 것이 가능하다면 1을, 그렇지 않다면 0을 첫째 줄에 출력한다.


*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N  =Integer.parseInt(st.nextToken());
        makeArr(br, N);

        for(int i=1;i<=N/2;i++){
            if(canMakeArray(N, i) && isVaildToAnswer(i, N)){
                System.out.println("1");
                return;
            }
        }
        System.out.println("0");
    }

    private static boolean isVaildToAnswer(int divSize,int N ){
        int lastSum=0;
        int curSum=0;
        for(int i=0;i<=N-divSize;){
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for(int j=i;j<i+divSize;j++){
                min = Math.min(min, arr[j]);
                max = Math.max(max,arr[j]);
            }
            curSum = min+max;
            if(i==0){lastSum = curSum;}
            //System.out.println(lastSum+" "+curSum);
            if(lastSum != curSum)return false;
            i += divSize;
        }

        return true;
    }

    private static boolean canMakeArray(int N,int size){
        return N%size==0;
    }

    private static void makeArr(BufferedReader br,int N ) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i=0;i<N;i++)arr[i] = Integer.parseInt(st.nextToken());
    }
}