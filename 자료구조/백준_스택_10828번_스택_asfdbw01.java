/*
문제
정수를 저장하는 스택을 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.

명령은 총 다섯 가지이다.

push X: 정수 X를 스택에 넣는 연산이다.
pop: 스택에서 가장 위에 있는 정수를 빼고, 그 수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
size: 스택에 들어있는 정수의 개수를 출력한다.
empty: 스택이 비어있으면 1, 아니면 0을 출력한다.
top: 스택의 가장 위에 있는 정수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
입력
첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다. 둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다. 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다. 문제에 나와있지 않은 명령이 주어지는 경우는 없다.

출력
출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static ArrayList<Integer> stack = new ArrayList<>();
    static int size=0;
    static StringBuilder out = new StringBuilder();
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        run(N, br);
        System.out.println(out.toString());
    }

    private static void run(int N,BufferedReader br ) throws IOException {
        int read =0;
        while(read < N){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            mapper(order, st);
            read++;
        }
    }

    private static void mapper(String order,StringTokenizer st){
        if(order.equals("push")){
            int num = Integer.parseInt(st.nextToken());
            push(num);
            return;
        }
        if(order.equals("pop")){
            int num = pop();
            out.append(num).append("\n");
            return;
        }
        if(order.equals("size")){
            int num = size();
            out.append(num).append("\n");
            return;
        }
        if(order.equals("empty")){
            int num = empty();
            out.append(num).append("\n");
            return;
        }
        if(order.equals("top")){
            int num = top();
            out.append(num).append("\n");
            return;
        }
        out.append("Unknown Order").append("\n");
    }

    private static int top(){
        if(size==0)return -1;
        return stack.get(size-1);
    }

    private static void push(int num){
        stack.add(num);
        size++;
    }

    private static int pop(){
        if(size==0)return-1;
        int lastIdx = size-1;
        int num = stack.remove(lastIdx);
        size--;
        return num;
    }

    private static int size(){
        return size;
    }

    private static int empty(){
        if (size==0) {
            return 1;
        }
        return 0;
    }
}