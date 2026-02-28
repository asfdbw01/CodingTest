/*
문제
한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만들려고 한다.
각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 
각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자. 
단, 회의는 한번 시작하면 중간에 중단될 수 없으며 
한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다. 
회의의 시작시간과 끝나는 시간이 같을 수도 있다. 
이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.

입력
첫째 줄에 회의의 수 N(1 ≤ N ≤ 100,000)이 주어진다. 
둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어지는데 
이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다. 
시작 시간과 끝나는 시간은 231-1보다 작거나 같은 자연수 또는 0이다.

출력
첫째 줄에 최대 사용할 수 있는 회의의 최대 개수를 출력한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        makeArr(N, br);
        Arrays.sort(arr,(a,b)-> {
            if(a[1]==b[1])return  a[0]-b[0];
            return a[1] - b[1];
        });
        System.out.println(countMeetingNumber());
    }

    private static int countMeetingNumber(){
        int cnt=0;
        int len = arr.length;
        int curTime = 0;
        for(int i=0;i<len;i++){
            //현재시긴보다 회의 시직시간이 크거나 같으면 cnt++
            if(arr[i][0]>=curTime){
                cnt++;
                curTime = arr[i][1];
            }
        }
        return cnt;
    }

    private static void makeArr(int N, BufferedReader br ) throws IOException{
        arr=  new int[N][2];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i][0] = start;
            arr[i][1] = end;
        }
    }
}