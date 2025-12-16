/*
신종 바이러스인 웜 바이러스는 네트워크를 통해 전파된다.
한 컴퓨터가 웜 바이러스에 걸리면 그 컴퓨터와 네트워크 상에서 연결되어 있는 
모든 컴퓨터는 웜 바이러스에 걸리게 된다.

어느 날 1번 컴퓨터가 웜 바이러스에 걸렸다. 
컴퓨터의 수와 네트워크 상에서 서로 연결되어 있는 정보가 주어질 때, 
1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에는 컴퓨터의 수가 주어진다. 
컴퓨터의 수는 100 이하인 양의 정수이고 각 컴퓨터에는 1번 부터 차례대로 번호가 매겨진다. 
둘째 줄에는 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수가 주어진다. 
이어서 그 수만큼 한 줄에 한 쌍씩 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍이 주어진다.

출력
1번 컴퓨터가 웜 바이러스에 걸렸을 때, 
1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 첫째 줄에 출력한다.
*/
import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] online;

    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        online = new boolean[N];
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        makeGraph(N, K, br);
        bfs(0);
        System.out.println(totalInfectedComputerCnt(N)-1);
    }

    private static int totalInfectedComputerCnt(int N){
        int cnt=0;
        for(int i=0;i<N;i++)if(online[i]==true)cnt++;
        return cnt;
    }

    private static void bfs(int start){
        online[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for(Integer i : graph.get(curr)){
                if(online[i]==false){
                    q.offer(i);
                    online[i] = true;
                }
            }
        }
    }

    private static void makeGraph(int N,int K, BufferedReader br) throws IOException{
        for(int i=0;i<N;i++)graph.add(new ArrayList<>());
        int read =0;
        while (read < K) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int com1 = Integer.parseInt(st.nextToken())-1;
            int com2 = Integer.parseInt(st.nextToken())-1;
            graph.get(com1).add(com2);
            graph.get(com2).add(com1);
            read++;
        }
    }
}