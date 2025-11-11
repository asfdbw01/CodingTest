/*
0으로 시작하지 않는 정수 N이 주어진다. 
이때, M을 정수 N의 자릿수라고 했을 때, 다음과 같은 연산을 K번 수행한다.

1 ≤ i < j ≤ M인 i와 j를 고른다. 
그 다음, i번 위치의 숫자와 j번 위치의 숫자를 바꾼다. 
이때, 바꾼 수가 0으로 시작하면 안 된다.

위의 연산을 K번 했을 때, 나올 수 있는 수의 최댓값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정수 N과 K가 주어진다. 
N은 1,000,000보다 작거나 같은 자연수이고, K는 10보다 작거나 같은 자연수이다.

출력
첫째 줄에 문제에 주어진 연산을 K번 했을 때, 
만들 수 있는 가장 큰 수를 출력한다. 만약 연산을 K번 할 수 없으면 -1을 출력한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static int val = -1;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String N = st.nextToken();
        int K = Integer.parseInt(st.nextToken());

        int num = Integer.parseInt(N);
        int len = N.length();
        if (len == 1) {                     //한 자리면 교환 불가
            System.out.println(-1);
            return;
        }
        boolean[][] visit = new boolean[1000001][K+1];
        visit[num][0] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(num);

        bfs(visit,q,K,len );

        System.out.println(val);

    }

    private static void bfs(boolean[][] visit, Queue<Integer> q, int K, int len) {
        for (int depth = 0; depth < K; depth++) {     
            int sz = q.size();
            if (sz == 0) { val = -1; return; }

            HashSet<Integer> nextSeen = new HashSet<>(); 

            for (int s = 0; s < sz; s++) {
                int curr = q.poll();

                for (int i = 0; i < len - 1; i++) {
                    for (int j = i + 1; j < len; j++) {
                        int next = shift(curr, i, j);   
                        if (next == -1) continue;       
                        if (!visit[next][depth + 1] && nextSeen.add(next)) {
                            visit[next][depth + 1] = true;
                            q.offer(next);
                        }
                    }
                }
            }
        }

        // K 번 교환 후 큐에 남은 값 중 최대
        int ans = -1;
        while (!q.isEmpty()) ans = Math.max(ans, q.poll());
        val = ans;
    }

    private static int shift(int num,int i,int j){
        char[] c = Integer.toString(num).toCharArray();
        

        int ci =  i;
        int cj = j;

        char tmp = c[ci];
        c[ci] = c[cj];
        c[cj] = tmp;
        if (c[0] == '0') return -1;
        return Integer.parseInt(new String(c));
    }
}