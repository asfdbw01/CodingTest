/*
상근이는 하프 마라톤(21km 정도) 대회를 준비하러 동해안으로 떠났다. 
상근이의 첫 번째 훈련은 21마일을 뛰는 것이었다.

21마일을 뛰어보니 21킬로미터를 뛴 것보다 더 지치는 것 같았다. 
상근이의 친구 정인이는 마라톤은 21마일이 아니고 21킬로미터라고 알려주었다. 
또, 21킬로미터는 13마일 같다는 사실도 알려주었다. 21, 13? 상근이는 깊은 깨달음을 얻었다. 
두 숫자 모두 피보나치 숫자이다!

피보나치 숫자는 다음과 같이 정의한다.

F1 = 1
F2 = 2
Fn+1 = Fn + Fn-1 (n > 1)

마침 상근이는 훈련을 떠나기 전, 대학에서 피보나치 진법을 배웠다. 
모든 양의 정수 X는 서로 다른 피보나치 수의 합으로 나타낼 수 있다. 
즉, bk = 1과 bi (1 ≤ i < k) = 0 또는 1이면서 x = ∑i=1..k bi × Fi을 만족하는 정수 k와 
b1, b2, ..., bk가 항상 존재한다. 
이러한 표현을 b(x) = (bk, bk-1, ..., b1)와 같이 간편하게 쓰기로 한다. 
또, 여러 가지 표현이 생기는 것을 막기 위해 bi × bi-1 = 0이어야 한다. (i > 1)

예를 들어, 21은 (1, 0, 0, 0, 0, 0, 0)로, 13은 (1, 0, 0, 0, 0, 0)으로 나타낼 수 있다. 
상근이는 이런 피보나치 진법을 이용해서 x킬로미터를 y마일로 바꾸는 방법을 만들었다.

먼저, x를 피보나치 진법 b(x)로 나타낸다. 
그 다음, b(x)를 오른쪽으로 한 비트씩 시프트 시킨다. 
(맨 마지막 비트는 버린다) 이것을 b(y)라고 한다. 이제 b(y)를 이용해서 y를 계산하면 된다.

예를 들어, 42는 피보나치 진법으로 (1, 0, 0, 1, 0, 0, 0, 0)이다. 
이걸 오른쪽으로 한 비트씩 시프트 시키면 (1, 0, 0, 1, 0, 0, 0)이 되고,
 0×1 + 0×2 + 0×3 + 1×5 + 0×8 + 0×13 + 1×21 = 26이 된다.

킬로미터 값이 주어졌을 때, 상근이의 알고리즘을 이용해서 마일로 바꾸는 프로그램을 작성하시오.

입력
첫째 줄에 마일로 바꾸려고 하는 킬로미터 값의 수 t가 주어진다. 
(0 < t < 25000) 다음 t개 줄에는 마일로 바꿀 킬로미터 값 x가 주어진다. (2 < x < 25000)

출력
각각의 킬로미터 거리 x에 대해서, 상근이의 알고리즘을 이용해 마일로 바꾼 값 y를 출력한다.
*/
import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static int[] fib;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        buildFibUpTo(25000);
        StringBuilder sb = new StringBuilder();
        int read = 0;
        while (read < N) {
            st = new StringTokenizer(br.readLine());
            int km = Integer.parseInt(st.nextToken());
            long kmMask = toZeckendorfMask(km);
            long mileMask = kmMask >> 1;
            int mile = valueFromMask(mileMask);
            sb.append(mile + "\n");
            read++;
        }
        System.out.println(sb.toString());
    }

    static int valueFromMask(long mileMask){
        int sum = 0;
        for(int i=0;i<fib.length;i++){
            if(((mileMask >> i ) & 1L) == 1L)sum += fib[i];
        }
        return sum;
    }

    static long toZeckendorfMask(int km){
        long mask =0L;
        int i = fib.length-1;
        while(km > 0){
            while (i>=0 && fib[i] > km)i--;
            km -=fib[i];
            mask |= (1L <<i);
            i -= 2;
        }
        return mask;
    }

    static void buildFibUpTo(int max){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        while (true) {
            int next=  list.get(list.size()-1)+list.get(list.size()-2);
            if(next > max)break;
            list.add(next);
        }
        fib = new int[list.size()];
        for(int i=0;i<list.size();i++)fib[i] = list.get(i);
    }

}