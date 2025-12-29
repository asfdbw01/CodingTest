/*
문제
0과 1로만 이루어진 행렬 A와 행렬 B가 있다. 
이때, 행렬 A를 행렬 B로 바꾸는데 필요한 연산의 횟수의 최솟값을 구하는 프로그램을 작성하시오.

행렬을 변환하는 연산은 어떤 3×3크기의 부분 행렬에 있는 모든 원소를 뒤집는 것이다. 
(0 → 1, 1 → 0)

입력
첫째 줄에 행렬의 크기 N M이 주어진다. N과 M은 50보다 작거나 같은 자연수이다.
둘째 줄부터 N개의 줄에는 행렬 A가 주어지고, 그 다음줄부터 N개의 줄에는 행렬 B가 주어진다.

출력
첫째 줄에 문제의 정답을 출력한다. 만약 A를 B로 바꿀 수 없다면 -1을 출력한다.
*/
import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static int[][] A;
    static int[][] B;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        A = makeMatrix(N, M, br);
        B = makeMatrix(N, M, br);
        if(N <3 || M <3){
            if(isSameMatrix(N, M))System.out.println(0);
            else System.out.println(-1);
            return;
        }
        System.out.println(countUntilSameMatrix(N, M));
    }

    private static int countUntilSameMatrix(int N,int M){
        int cnt=0;
        for(int x=0;x<N-2;x++){
            for(int y=0;y<M-2;y++){
                if(A[x][y]!=B[x][y]){
                    flip(x, y);
                    cnt++;
                }
            }
        }
        if(isSameMatrix(N,M))return cnt;
        else return -1;
    }

    private static boolean isSameMatrix(int N, int M){
        for(int x=0;x<N;x++){
            for(int y=0;y<M;y++){
                if(A[x][y]!=B[x][y])return false;
            }
        }
        return true;
    }

    private static void flip(int x,int y){
        for(int i=x;i<x+3;i++){
            for(int j=y;j<y+3;j++){
                if(A[i][j]==1)A[i][j]=0;
                else A[i][j]=1;
            }
        }
    }

    private static int[][] makeMatrix(int N,int M,BufferedReader br )throws IOException{
        int[][] matrix = new int[N][M];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String col = st.nextToken();
            for(int j=0;j<M;j++){
                matrix[i][j] = col.charAt(j)-'0';
            }
        }
        return matrix;
    }
}