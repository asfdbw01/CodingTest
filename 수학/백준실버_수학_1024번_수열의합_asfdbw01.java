/*
N과 L이 주어질 때, 합이 N이면서, 
길이가 적어도 L인 가장 짧은 연속된 음이 아닌 정수 리스트를 구하는 프로그램을 작성하시오.

입력

첫째 줄에 N과 L이 주어진다.
 N은 1,000,000,000보다 작거나 같은 자연수이고, 
L은 2보다 크거나 같고, 100보다 작거나 같은 자연수이다.

출력
만약 리스트의 길이가 100보다 작거나 같으면, 
연속된 수를 첫째 줄에 공백으로 구분하여 출력한다. 
만약 길이가 100보다 크거나 그러한 수열이 없을 때는 -1을 출력한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken()); 
        int L = Integer.parseInt(st.nextToken());


        long [] leftRight = new long[2];
        boolean found = false;   
        StringBuilder sb = new StringBuilder();
        for(int i = L; i <= 100; i++){      
            
            if(isVaild( N,i,leftRight)){
                for(long j = leftRight[0]; j < leftRight[1]; j++) sb.append(j).append(' ');
                System.out.println(sb.toString()); 
                found = true;
                return;
            }
        }
        
        if(!found) System.out.println("-1");     
        
    }

    private static boolean isVaild(long N, int len, long[] leftRight){
        long left = Math.max(0, (long)Math.floor((double)N/len - (len - 1)/2.0) - 1);
        long right = left + len;
        long sum = len * left + (long)len * (len - 1) / 2;
        while (sum < N) {
            left++; right++;
            sum += len;
        }
        if (sum == N) {
            leftRight[0] = left;
            leftRight[1] = right;         
            return true;
        }
        return false;
    }
}