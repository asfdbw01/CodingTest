/*
평행사변형은 평행한 두 변을 가진 사각형이다. 
세 개의 서로 다른 점이 주어진다. A(xA,yA), B(xB,yB), C(xC,yC)

이때, 적절히 점 D를 찾아서 네 점으로 평행사변형을 만들면 된다. 
이때, D가 여러 개 나올 수도 있다.

만들어진 모든 사각형 중 가장 큰 둘레 길이와 가장 작은 둘레 길이의 
차이를 출력하는 프로그램을 작성하시오. 
만약 만들 수 있는 평행사변형이 없다면 -1을 출력한다.

입력
첫째 줄에 xA yA xB yB xC yC가 주어진다. 모두 절댓값이 5000보다 작거나 같은 정수이다.

출력
첫째 줄에 문제의 정답을 출력한다. 절대/상대 오차는 10-9까지 허용한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static double max = Double.MIN_VALUE;
    static double min = Double.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        double[][] threeDotsLoc = returnThreeDotsLoc(br);
        if(IsDotsOnOneLine(threeDotsLoc)){
            System.out.println("-1.0");
            return;
        }
        findDist(threeDotsLoc);

        System.out.println(max-min);
    }

    private static boolean IsDotsOnOneLine(double[][] threeDotsLoc){
        double slope1 = Math.abs((threeDotsLoc[0][1]-threeDotsLoc[1][1])/(threeDotsLoc[0][0]-threeDotsLoc[1][0]));
        double slope2 = Math.abs((threeDotsLoc[0][1]-threeDotsLoc[2][1])/(threeDotsLoc[0][0]-threeDotsLoc[2][0]));
        return slope1 == slope2;
    }

    private static void findDist(double[][] threeDotsLoc){
        double dist = returnDIst(0, 1, 2, threeDotsLoc);
        findMaxAndMin( dist);
        dist = returnDIst(1, 0, 2, threeDotsLoc);
        findMaxAndMin( dist);
        dist = returnDIst(2, 1, 0, threeDotsLoc);
        findMaxAndMin( dist);
    }

    private static void findMaxAndMin(double dist){
        max = Math.max(dist, max);
        min = Math.min(dist, min);
    }

    private static double returnDIst(int i1,int i2,int i3,double[][] threeDotsLoc){
        double dis1 = Math.sqrt(Math.pow(threeDotsLoc[i1][0]-threeDotsLoc[i2][0],2)+Math.pow(threeDotsLoc[i1][1]-threeDotsLoc[i2][1],2));
        double dis2 = Math.sqrt(Math.pow(threeDotsLoc[i1][0]-threeDotsLoc[i3][0],2)+Math.pow(threeDotsLoc[i1][1]-threeDotsLoc[i3][1],2));
        return (dis1+dis2)*2;
    }

    private static double[][]  returnThreeDotsLoc(BufferedReader br) throws IOException{
        double[][] threeDotsLoc = new double[3][2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<3;i++){
            for(int j=0;j<2;j++){
                int dot = Integer.parseInt(st.nextToken());
                threeDotsLoc[i][j] = dot;
            }
        }
        return threeDotsLoc;
    }
}