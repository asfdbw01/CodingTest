/*
이석원은 조규현과 백승환에게 상대편 마린(류재명)의 위치를 계산하라는 명령을 내렸다. 
조규현과 백승환은 각각 자신의 터렛 위치에서 현재 적까지의 거리를 계산했다.

조규현의 좌표 (x_1, y_1)와 백승환의 좌표 (x_2, y_2)가 주어지고, 조
규현이 계산한 류재명과의 거리 
r_1과 백승환이 계산한 류재명과의 거리 r_2가 주어졌을 때, 
류재명이 있을 수 있는 좌표의 수를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 테스트 케이스의 개수 가 주어진다. 
각 테스트 케이스는 다음과 같이 이루어져 있다.

한 줄에 공백으로 구분 된 여섯 정수 
x_1, y_1, r_1, x_2, y_2, r_2가 주어진다.

출력
각 테스트 케이스마다 류재명이 있을 수 있는 위치의 수를 출력한다. 
만약 류재명이 있을 수 있는 위치의 개수가 무한대일 경우에는 -1 출력한다.
*/

import java.io.*;
import java.io.IOException;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int read =0;
        int [][] coordinate = new int[2][2];
        int[] dist = new int[2];

        StringBuilder sb = new StringBuilder();

        while (read < N ) {
            while (!st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) break;        // EOF 방어
                st = new StringTokenizer(line);
            }
            coordAndDist(coordinate, dist, st);
            sb.append(returnAnswer(coordinate,dist)+"\n");
            read++;
        }
        System.out.println(sb.toString());
    }

    private static double circDist (int [][] coordinate){
        double dx = (double)  coordinate[0][0] -  coordinate[1][0];
        double dy = (double)  coordinate[0][1] -  coordinate[1][1];
        return Math.hypot(dx, dy);
    } 

    private static int returnAnswer(int [][] coordinate,int[] dist){
        int ans = 0;
        boolean isSameCoord =  (coordinate[0][0] ==  coordinate[1][0]) && ( coordinate[0][1] ==  coordinate[1][1]);
        boolean isSameDist = dist[0] == dist[1];
        if(isSameCoord ){
            if(isSameDist)return -1;
            else return 0;
        }
        else{
            double spotDist = circDist (coordinate);
            double sumR = (double)(dist[0]+dist[1]);
            double minuR = Math.abs((double)(dist[0]-dist[1]));
            if(spotDist == sumR || spotDist ==  minuR)return 1;
            else if(spotDist > minuR && spotDist <sumR ) return 2;
            else return 0;
        }
    }

    private static void coordAndDist(int [][] coordinate,int[] dist,StringTokenizer st){
        coordinate[0][0] = Integer.parseInt(st.nextToken());
        coordinate[0][1] = Integer.parseInt(st.nextToken());
        dist[0] = Integer.parseInt(st.nextToken());
        coordinate[1][0] = Integer.parseInt(st.nextToken());
        coordinate[1][1] = Integer.parseInt(st.nextToken());
        dist[1] = Integer.parseInt(st.nextToken());
    }
}