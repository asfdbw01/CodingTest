/*
문제
어떤 양의 정수 X의 각 자리가 등차수열을 이룬다면, 
그 수를 한수라고 한다. 등차수열은 연속된 두 개의 수의 차이가 일정한 수열을 말한다. 
N이 주어졌을 때, 1보다 크거나 같고, N보다 작거나 같은 한수의 개수를 출력하는 프로그램을 작성하시오. 

입력
첫째 줄에 1,000보다 작거나 같은 자연수 N이 주어진다.

출력
첫째 줄에 1보다 크거나 같고, N보다 작거나 같은 한수의 개수를 출력한다.
*/
import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        makeHanSuList();
        int cnt = 0;
        for(int i:list){
            if(i<=N)cnt++;
        }
        System.out.println(cnt);
    }

    private static void makeHanSuList(){
        for(int i=1;i<=99;i++)list.add(i);
        for(int i=100;i<=1000;i++){
            if(isAP(i))list.add(i);
        }
    }

    private static boolean isAP(int num){
        int prev = num%10;
        num /=10;
        int cur = num%10;
        int difference = prev - cur;
        num /=10;
        while(num>0){
            int next = num%10;
            if(difference != cur - next)return false;
            prev = cur;
            cur = next;
            num/=10;
        }
        return true;
    }

}