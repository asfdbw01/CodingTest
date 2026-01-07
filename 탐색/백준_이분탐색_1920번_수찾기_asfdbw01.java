/*
문제
N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 
이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)이 주어진다. 
다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다. 
다음 줄에는 M(1 ≤ M ≤ 100,000)이 주어진다. 다음 줄에는 M개의 수들이 주어지는데,
 이 수들이 A안에 존재하는지 알아내면 된다. 
모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.

출력
M개의 줄에 답을 출력한다. 존재하면 1을, 존재하지 않으면 0을 출력한다.
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

        int N = Integer.parseInt(st.nextToken());
        makeArr(N, br);
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            int find = Integer.parseInt(st.nextToken());
            if(isInclude(find))sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static boolean isInclude(int find){
        int l = 0 , r = arr.length-1;
        while (l<=r) {
            int mid = (l+r)/2;
            if(arr[mid]==find)return true;
            if(arr[mid]>find)r=mid-1;
            else if(arr[mid]<find)l = mid+1;
        }
        return false;
    }

    private static void makeArr(int N,BufferedReader br) throws IOException{
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}