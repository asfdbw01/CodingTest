/*
어떤 자연수 N은 그보다 작거나 같은 제곱수들의 합으로 나타낼 수 있다. 

주어진 자연수 N을 이렇게 제곱수들의 합으로 표현할 때에 
그 항의 최소개수를 구하는 프로그램을 작성하시오.
입력
첫째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 100,000)

출력
주어진 자연수를 제곱수의 합으로 나타낼 때에 그 제곱수 항의 최소 개수를 출력한다.
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
        
        //라그랑주의 네제곱수 정리
        if(isSquare(N)){
            System.out.println(1);
            return;
        }

        int limit = (int)Math.sqrt(N);
        for(int i=1;i<limit;i++){
            int rest = N-i*i;
            if(isSquare(rest)){
                System.out.println(2);
                return;
            }
        }

        int m = N;
        while (m%4==0) m/=4;
        if(m%8==7){
            System.out.println(4);
            return;
        }

        //나머지면 무조건 3개
        System.out.println(3);
    }
    private static boolean isSquare(int N){
        int r = (int)Math.sqrt(N);
        return r*r == N;
    }
    
}