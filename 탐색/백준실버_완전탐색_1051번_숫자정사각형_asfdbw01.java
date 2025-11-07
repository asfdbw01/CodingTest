/*
N×M크기의 직사각형이 있다. 
각 칸에는 한 자리 숫자가 적혀 있다.
이 직사각형에서 꼭짓점에 쓰여 있는 수가 모두 같은 가장 큰 정사각형을 찾는 프로그램을 작성하시오. 
이때, 정사각형은 행 또는 열에 평행해야 한다.

입력

첫째 줄에 N과 M이 주어진다. 
N과 M은 50보다 작거나 같은 자연수이다. 
둘째 줄부터 N개의 줄에 수가 주어진다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] field = makeField(N,M,br);
        int max = 1;
        for(int y=0;y<N;y++){
            for(int x=0;x<M;x++){
              max = Math.max(max,squareSize( y, x, field, N, M)) ;
            }
        }
        
        System.out.println(max);
        
    }

    private static int[][] makeField(int N, int M,BufferedReader br ) throws IOException{
        int[][] field = new int[N][M];
        int read = 0;
        while(read < N){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String str  = st.nextToken();
            for(int i=0;i<M;i++)field[read][i]=  Character.getNumericValue(str.charAt(i)) ;
            read++;
        }

        return field;
    }

    private static int  squareSize(int y,int x,int[][] field,int N,int M){
        int target= field[y][x];
        int size = 1;

        for(int i=x+1;i<M;i++){
            int len = i-x;
            if(field[y][i]==target &&  isSquare( target, y,x,len, field, N))size = len+1;
        }
        

        return  size*size;
    }

    private static boolean isSquare(int target,int y,int x,int len,int[][] field,int N){
        if(y+len >= N)return false;
        
        return field[y+len][x+len]==field[y][x] && field[y+len][x] == field[y][x];
    }
}