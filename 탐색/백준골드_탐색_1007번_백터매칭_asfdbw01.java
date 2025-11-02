/*
평면 상에 N개의 점이 찍혀있고, 그 점을 집합 P라고 하자.
집합 P의 벡터 매칭은 벡터의 집합인데, 모든 벡터는 집합 P의 한 점에서 시작해서,
 또 다른 점에서 끝나는 벡터의 집합이다. 
또, P에 속하는 모든 점은 한 번씩 쓰여야 한다.

벡터 매칭에 있는 벡터의 개수는 P에 있는 점의 절반이다.

평면 상의 점이 주어졌을 때, 
집합 P의 벡터 매칭에 있는 벡터의 합의 길이의 최솟값을 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 다음과 같이 구성되어있다.

테스트 케이스의 첫째 줄에 점의 개수 N이 주어진다.
N은 짝수이다. 둘째 줄부터 N개의 줄에 점의 좌표가 주어진다. 
N은 20보다 작거나 같은 자연수이고, 좌표는 절댓값이 100,000보다 작거나 같은 정수다.
모든 점은 서로 다르다.

각 테스트 케이스마다 정답을 출력한다. 절대/상대 오차는 10-6까지 허용한다.
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
        int read = 0;

        StringBuilder sb = new StringBuilder();
        
        while(read < N){
            double ans = Double.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            int pNum = Integer.parseInt(st.nextToken());
            int[][] pLocation  =  makePLocation(pNum, br);
            long Sx = 0, Sy = 0;
            for (int i = 0; i < pNum; i++) { Sx += pLocation[i][0]; Sy += pLocation[i][1]; }
            int half = pNum / 2;
            
            ans = dfsComb(0, 0, 0L, 0L, pLocation, half, Sx, Sy);
            sb.append(String.format(java.util.Locale.US, "%.12f%n", ans));
            read++;
        }
        System.out.println(sb.toString());
    }

    

    private static double dfsComb(int idx, int picked, long Tx, long Ty,int[][] P, int half, long Sx, long Sy) {
        if (picked == half) {
            long vx = 2*Tx - Sx, vy = 2*Ty - Sy;
            return Math.hypot((double)vx, (double)vy);
        }
        if (idx == P.length) return Double.MAX_VALUE;
        if (P.length - idx < half - picked) return Double.MAX_VALUE; 
        
        double a = dfsComb(idx + 1, picked + 1, Tx + P[idx][0], Ty + P[idx][1], P, half, Sx, Sy);
        
        double b = dfsComb(idx + 1, picked, Tx, Ty, P, half, Sx, Sy);
        return Math.min(a, b);
    }

    private static int[][] makePLocation(int pNum,BufferedReader br) throws IOException{
        int[][] pLocation = new int[pNum][2];
        for(int i=0;i<pNum;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            pLocation[i][0] = y;
            pLocation[i][1] = x;
        }
        return pLocation;
    }
}