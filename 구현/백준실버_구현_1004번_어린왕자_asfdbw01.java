/*
어린 왕자는 장미를 구하기 위해 은하수를 따라 긴 여행을 하기 시작했다. 
하지만 어린 왕자의 우주선은 그렇게 좋지 않아서 행성계 간의 이동을 최대한 피해서 여행해야 한다. 

은하수 지도, 출발점, 도착점이 주어졌을 때 
어린 왕자에게 필요한 최소의 행성계 진입/이탈 횟수를 구하는 프로그램을 작성해 보자.
행성계의 경계가 맞닿거나 서로 교차하는 경우는 없다. 
또한, 출발점이나 도착점이 행성계 경계에 걸쳐진 경우 역시 입력으로 주어지지 않는다.

입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다. 
그 다음 줄부터 각각의 테스트케이스에 대해 첫째 줄에 출발점 (x1, y1)과 도착점 (x2, y2)이 주어진다.
두 번째 줄에는 행성계의 개수 n이 주어지며, 세 번째 줄부터 n줄에 걸쳐
행성계의 중점과 반지름 (cx, cy, r)이 주어진다.


제한
-1000 ≤ x1, y1, x2, y2, cx, cy ≤ 1000
1 ≤ r ≤ 1000
1 ≤ n ≤ 50
좌표와 반지름은 모두 정수
*/

import java.io.IOException;
import java.io.*;
import java.util.*;


class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int read =0;

         
        int[] planets;
        while(read < N){
            while (!st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) break;        // EOF 방어
                st = new StringTokenizer(line);
            }
            int[][] startToEnd = initStartToEnd(st);
            int planetNum = Integer.parseInt(br.readLine());
            int count = 0; //지나치는 개수

            for(int i=0;i<planetNum;i++){
                int[] circ = new int[3];
                st = new StringTokenizer(br.readLine());
                circ[0] = Integer.parseInt(st.nextToken());
                circ[1] = Integer.parseInt(st.nextToken());
                circ[2] = Integer.parseInt(st.nextToken()); 

                if( isInside(circ,startToEnd))count++;
            }
            sb.append(count+"\n");
            read++;

        }
        System.out.println(sb.toString());
    }

    private static boolean isInside(int[] circ,int[][] startToEnd){
        int sDx = circ[0]-startToEnd[0][0];
        int sDy = circ[1] -  startToEnd[0][1];
        int eDx = circ[0] - startToEnd[1][0];
        int eDy = circ[1] - startToEnd[1][1];
        int sDist = sDx*sDx+sDy*sDy;
        int eDist = eDx*eDx+eDy*eDy;
        return ( sDist < circ[2]*circ[2]) ^ (eDist < circ[2]*circ[2]);
    }

    private static int[][] initStartToEnd(StringTokenizer st){
        int [][] startToEnd = new int[2][2];
        startToEnd[0][0] = Integer.parseInt(st.nextToken());
        startToEnd[0][1] = Integer.parseInt(st.nextToken());
        startToEnd[1][0] = Integer.parseInt(st.nextToken());
        startToEnd[1][1] = Integer.parseInt(st.nextToken());
        
        return startToEnd;
    }
}