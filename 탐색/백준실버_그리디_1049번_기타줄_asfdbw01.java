/*
Day Of Mourning의 기타리스트 강토가 사용하는 기타에서 N개의 줄이 끊어졌다. 
따라서 새로운 줄을 사거나 교체해야 한다. 
강토는 되도록이면 돈을 적게 쓰려고 한다. 
6줄 패키지를 살 수도 있고, 1개 또는 그 이상의 줄을 낱개로 살 수도 있다.

끊어진 기타줄의 개수 N과 기타줄 브랜드 M개가 주어지고, 
각각의 브랜드에서 파는 기타줄 6개가 들어있는 패키지의 가격, 
낱개로 살 때의 가격이 주어질 때, 
적어도 N개를 사기 위해 필요한 돈의 수를 최소로 하는 프로그램을 작성하시오.

첫째 줄에 N과 M이 주어진다. 
N은 100보다 작거나 같은 자연수이고, M은 50보다 작거나 같은 자연수이다. 
둘째 줄부터 M개의 줄에는 각 브랜드의 패키지 가격과 낱개의 가격이 공백으로 구분하여 주어진다. 
가격은 0보다 크거나 같고, 1,000보다 작거나 같은 정수이다.


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
        
        int[] minOfSetAndSingle = returnMinValue(M, br);
        int setMinCost  =setCost ( N, minOfSetAndSingle);
        int singleMinCost = singleCost(N, minOfSetAndSingle);

        System.out.println(setMinCost<singleMinCost ?setMinCost:singleMinCost );
    }



    private static int setCost (int N,int[] minOfSetAndSingle){
        int setMinCost  =0;
        
        int set=N/6;
        int remain = N-(set*6);
        for(int i=0;i<set;i++)setMinCost  += minOfSetAndSingle[0];
        int setOnly =setMinCost ;
        if(remain>0)setOnly+= minOfSetAndSingle[0];
        int setAndSingel =setMinCost  ;
        for(int i=0;i<remain;i++)setAndSingel+=minOfSetAndSingle[1];
        //System.out.println(setOnly+" "+setAndSingel);
        return Math.min(setOnly,setAndSingel);
    }

    private static int singleCost(int N,int[] minOfSetAndSingle){
        int singleMinCost=0;
        for(int i=0;i<N;i++)singleMinCost += minOfSetAndSingle[1];
        //System.out.println(singleMinCost);
        return singleMinCost;
    }

    private static int[] returnMinValue(int M,BufferedReader br) throws IOException{
        int [] minOfSetAndSingle = new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE};
        
        int read = 0;

        while(read < M){
            StringTokenizer st = new StringTokenizer(br.readLine());
            minOfSetAndSingle[0] = Math.min(minOfSetAndSingle [0],Integer.parseInt(st.nextToken()));
            minOfSetAndSingle[1] = Math.min(minOfSetAndSingle [1],Integer.parseInt(st.nextToken()));
            read++;
        }

        return minOfSetAndSingle;
    }
}