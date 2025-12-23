/*
음 아닌 정수 N이 주어졌을 때, 
이 수를 서로 다른 정수 M(M ≥ 1)개의 팩토리얼의 합으로 나타낼 수 있는지 
알아내는 프로그램을 작성하시오. 예를 들어 2=0!+1!로 나타낼 수 있지만, 
5는 이와 같은 방식으로 나타낼 수 없다.

입력
첫째 줄에 정수 N이 주어진다.

출력
입력으로 주어진 수를 서로 다른 정수 M개의 팩토리얼의 합으로 나타낼 수 있으면 
YES, 없으면 NO를 출력한다. 
*/
import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static Stack<Long> st = new Stack<>();
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        if(N==0){
            System.out.print("NO");
            return;
        }
        makeFactorialQ(N);
        if(isVaild(N))System.out.print("YES");
        else System.out.print("NO");

    }

    private static boolean isVaild(long N){
        while(!st.isEmpty()){
            long now = st.pop();
            if(N==0)break;
            if(now>N)continue;
            else if(now <= N){
                N-=now;
            }
        }
        if(N>0)return false;
        else return true;
    }

    private static void makeFactorialQ(long N){
        long next = 1;
        st.add(next);
        long idx=1;

        while(next <= N){
            st.push(next);
            idx++;
            if (next > Long.MAX_VALUE / idx) break;
            next *= idx;
        }
    }
}