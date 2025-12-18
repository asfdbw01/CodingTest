/*
두 양의 정수 k,x 가 주어질 때, 
피보나치 수열의 항들 중 정확히 
k 개를 더하여 x 를 만들 수 있는지 판별하여라. 
이때 피보나치 수열의 항을 중복하여 선택할 수 있다.
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
        List<Long> fibo = new ArrayList<>();
        makeFiboList(fibo);
        int read =0;
        while(read < N){
            st = new StringTokenizer(br.readLine());
            long K = Long.parseLong(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            long m = findMinSumCount(x, fibo);
            boolean vaild = isVaild(m, K, x);
            if (vaild) {
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }
            read++;
        }
    }

    private static boolean isVaild(long m,long k,long x){
        return m<=k && k<=x;
    }

    private static void makeFiboList(List<Long> fibo){
        long a = 1,b=1;
        fibo.add(1L);
        while(true){
            long sum = a+b;
            if(a+b>10_000_000_000_000_000L)break;
            fibo.add(sum);
            a = b;
            b = sum;
        }
    }

    private static long findMinSumCount(long num,List<Long> fibo){
        long cnt = 0;
        int idx = fibo.size()-1;
        while(num>0){
            if(fibo.get(idx)>num)idx--;
            else{
                num-=fibo.get(idx);
                cnt++;
            }
        }
        return cnt;
    }

}