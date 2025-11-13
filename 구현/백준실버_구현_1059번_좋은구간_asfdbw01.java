/*
정수 집합 S가 주어졌을때, 다음 조건을 만족하는 구간 [A, B]를 좋은 구간이라고 한다.

A와 B는 양의 정수이고, A < B를 만족한다.
A ≤ x ≤ B를 만족하는 모든 정수 x가 집합 S에 속하지 않는다.
집합 S와 n이 주어졌을 때, n을 포함하는 좋은 구간의 개수를 구해보자.

입력
첫째 줄에 집합 S의 크기 L이 주어진다.
둘째 줄에는 집합에 포함된 정수가 주어진다. 
셋째 줄에는 n이 주어진다.

출력
첫째 줄에 n을 포함하는 좋은 구간의 개수를 출력한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static int max = 0;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int[]arr= makeArr(L, br);
        Arrays.sort(arr);

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] leftAndRight = findLeftAndRight(arr,L,N);
        int ans = (N - ( leftAndRight[0] - 1)) * ((leftAndRight[1] + 1) - N) - 1;
        ans = ans<0?0:ans;
        System.out.println(ans);
        
    }

    private static int[] findLeftAndRight(int[] arr, int L, int N) {
        int i = 0;
        while (i < L && arr[i] < N) i++;

        if (i == L) return null; 
        int r = arr[i];
        int l = (i == 0) ? 0 : arr[i - 1];

        int A = l + 1;                 
        int B = r - 1;         
        if (A > B) return new int[]{A, A - 1};

        return new int[]{A, B};
    }

    private static int[] makeArr(int L,BufferedReader br) throws IOException{
        int[]arr= new int[L];
        int read = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int idx = 0;
        while(read < L){
            int num = Integer.parseInt(st.nextToken());
            arr[idx] = num;
            idx++;
            max = max>num?max:num;
            read++;
        }
        return arr;
    }
}