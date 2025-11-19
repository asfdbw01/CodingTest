/*
문제
두 정수 A와 B를 입력받은 다음, A/B를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 A와 B가 주어진다. (0 < A, B < 10)

출력
첫째 줄에 A/B를 출력한다. 실제 정답과 출력값의 절대오차 또는 상대오차가 10-9 이하이면 정답이다.
*/


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // 버퍼를 사용해 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double N = Double.parseDouble(st.nextToken());
        double M = Double.parseDouble(st.nextToken());
        
        
        System.out.println(N/M);
    }
}