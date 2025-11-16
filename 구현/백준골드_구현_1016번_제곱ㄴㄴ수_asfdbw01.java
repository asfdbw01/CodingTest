/*
어떤 정수 X가 1보다 큰 제곱수로 나누어 떨어지지 않을 때, 
그 수를 제곱ㄴㄴ수라고 한다. 제곱수는 정수의 제곱이다. min과 max가 주어지면, 
min보다 크거나 같고, max보다 작거나 같은 제곱ㄴㄴ수가 몇 개 있는지 출력한다.

입력
첫째 줄에 두 정수 min과 max가 주어진다.

출력
첫째 줄에 min보다 크거나 같고, max보다 작거나 같은 제곱ㄴㄴ수의 개수를 출력한다.

제한
1 ≤ min ≤ 1,000,000,000,000
min ≤ max ≤ min + 1,000,000
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static long[] prime ;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long MIN = Long.parseLong(st.nextToken());
        long  MAX = Long.parseLong(st.nextToken());

        
        prime = makePrimeArr( MAX);

        //long ans = shouldReturnNoPowCnt(MIN, MAX);
        boolean[] NoNoBoolean = returnNoNoboolean(MIN, MAX);
        int ans = 0;
        for(boolean b : NoNoBoolean){
            if(b==false)ans++;
        }
        System.out.println(ans);
    }

    private static boolean[] returnNoNoboolean(long MIN,long MAX){
        int range = (int)(MAX - MIN + 1);
        boolean[] bad = new boolean[range];

        for (long p : prime) {
            long pp = p * p;
            if (pp > MAX) break;

            long start = ((MIN + pp - 1) / pp) * pp;
            for (long x = start; x <= MAX; x += pp) {
                bad[(int)(x - MIN)] = true;
            }
        }
        return bad;
    }

    private static long[] makePrimeArr(long Max){
        int N = (int)Math.sqrt(Max);
        boolean[] comp = new boolean[N + 1];
        ArrayList<Long> list = new ArrayList<>();

        if (N >= 2) list.add(2L);
        for (int i = 3; i <= N; i += 2) {
            if (!comp[i]) {
                list.add((long)i);
                if ((long)i * i <= N) {
                    for (int j = i * i; j <= N; j += 2 * i) comp[j] = true;
                }
            }
        }

        long[] primes = new long[list.size()];
        for (int i = 0; i < list.size(); i++) primes[i] = list.get(i);
        return primes;
    }

    private static List<Long> sieveOfEratosthenes(long Max){
        List<Long> list = new ArrayList<>();
        int N = (int)Math.sqrt(Max);
        boolean[] sieve = new boolean[N+2];
        sieve[0] = true;
        sieve[1] = true;

        for(long i=2;i<=N+1;i++){
            if(sieve[(int) i]==false){
                list.add(i);
                for(int j=1;j*i<=N+1;j++)sieve[(int) (j*i)]=true;
            }
        }

        return list;
    }

    private static long shouldReturnNoPowCnt(long MIN,long  MAX){
        long noPowCnt=0;
        for(long i=MIN;i<=MAX;i++){
            if(i<=1){noPowCnt++; continue;}
            //if(!isDivByPow(i))noPowCnt++;
            if(isNoNoNum(i))noPowCnt++;
        }
        return noPowCnt;
    }

    private static boolean isNoNoNum(long num){
        for(long p:prime){
            long pp = p*p;
            if(pp>num)break;
            if(num%pp==0)return false;
        }
        return true;
    }

}