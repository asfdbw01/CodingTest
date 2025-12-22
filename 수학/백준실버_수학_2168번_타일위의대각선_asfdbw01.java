/*
한 변의 길이가 1cm인 정사각형 모양의 타일이 있다. 
이 타일들을 가로가 xcm, 세로가 ycm인 직사각형 모양의 벽에 빈틈없이 붙였다. x와 y는 정수이다.

이 직사각형에 하나의 대각선을 그렸다. 
직사각형에 붙어 있는 x*y개의 타일 중에는 대각선이 그려진 타일도 있고, 
그렇지 않은 타일도 있다. 
x*y개의 타일 중에서 대각선이 그려져 있는 타일의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 가로의 길이 xcm와 세로의 길이 ycm가 주어진다. x와 y는 1,000,000,000 이하의 자연수이다. 
x와 y사이에는 빈칸이 하나 이상 있다.

출력
첫째 줄에 대각선이 그려져 있는 타일의 개수를 출력한다.
*/
import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        System.out.println(x+y-gcd(x, y));
    }

    private static int gcd(int a,int b){
        
        while (b!=0) {
            int t=a%b;
            a=b;
            b=t;
        }
        return a;
    }
}