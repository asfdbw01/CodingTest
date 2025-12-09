/*
영우는 나코더 신입생들을 환영하기 위해 정성스럽게 입부 환영 카드를 만들었다. 
입부 환영 카드에서 소환된 기장 동현이의 환영이 신입생들을 환영할 것이다. 
부기장인 이환이는 환영 카드의 색칠을 맡았다. 
이환이는 각 카드를 일렬로 줄지어 놓고 빨간색, 초록색, 파란색 중 하나의 색으로 칠하려 한다. 
그런데 그중 몇 개의 카드는 영우가 이미 색칠했다. 색칠은 다음 규칙에 따라야 한다.

단조로움을 피하기 위해, 인접한 카드는 서로 다른 색으로 칠해야 한다.
이미 색칠된 카드에 덧칠할 수 없다.
카드의 순서를 바꿀 수 없다.
하지만 이환이는 서울과학고등학교 동아리 '싸이컴'의 상훈이와 
테트리스 대결을 해야 하기 때문에 환영 카드를 색칠할 시간 따위는 없다. 
그러니까 신입생 환영 카드의 색칠은 여러분이 직접 하도록 하자.

입력
첫째 줄에 카드의 개수를 나타내는 정수 N이 주어진다. 
둘째 줄에 N 개의 정수가 공백으로 구분되어 주어진다. 
i 번째 정수 ai는 i 번째 카드의 색깔을 나타낸다. 
1, 2, 3은 각각 빨간색, 초록색, 파란색을 의미하며, 0은 i 번째 카드가 색칠되어 있지 않음을 의미한다.

출력
유일한 줄에 N 개의 정수를 공백으로 구분하여 출력한다. 
각 정수는 1, 2, 3 중 하나여야 하며, 주어진 조건에 맞아야 한다. 
가능한 방법이 여러 가지인 경우, 그중 아무거나 출력한다. 
만약 모든 조건에 맞는 색칠이 불가능하다면, 유일한 줄에 –1만 출력한다.

제한
1 ≤ N ≤ 1,000,000
0 ≤ ai ≤ 3
주어지는 모든 수는 정수이다.
*/
import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static int [] arr;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        makeArr(N, br);
        paint(N);
    }
    private static void paint(int N){
        StringBuilder sb = new StringBuilder();
        int prev = 0; 

        for(int i = 0; i < N; i++){
            int next = (i + 1 < N) ? arr[i + 1] : 0; 
            int curr = arr[i];

            if (!isVaild(prev, next, curr)) {
                System.out.println(-1);
                return;
            }

            if (curr == 0) {
                curr = returnVaildNum(prev, next, i);
            }

            sb.append(curr).append(' ');
            prev = curr;
        }

        System.out.println(sb.toString());
    }
    private static int returnVaildNum(int prev,int next,int idx){
        for(int i=1;i<=3;i++){
            if(prev!=i && next !=i){
                arr[idx] = i;
                return i;
            }
        }
        return 0;
    }

    private static boolean isVaild(int prev,int next,int curr){
        return curr ==0 || (curr != prev && curr!=next);
    }

    private static void makeArr(int N ,BufferedReader br) throws IOException{
        int read =0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N+1];
        while (read < N) {
            int num = Integer.parseInt(st.nextToken());
            arr[read] = num; 
            read++;
        }
    }
}