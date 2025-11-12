/*
지민이는 세계에서 가장 유명한 사람이 누구인지 궁금해졌다. 
가장 유명한 사람을 구하는 방법은 각 사람의 2-친구를 구하면 된다. 
어떤 사람 A가 또다른 사람 B의 2-친구가 되기 위해선, 두 사람이 친구이거나, 
A와 친구이고, B와 친구인 C가 존재해야 된다. 
여기서 가장 유명한 사람은 2-친구의 수가 가장 많은 사람이다. 
가장 유명한 사람의 2-친구의 수를 출력하는 프로그램을 작성하시오.

A와 B가 친구면, B와 A도 친구이고, A와 A는 친구가 아니다.

입력
첫째 줄에 사람의 수 N이 주어진다. 
N은 50보다 작거나 같은 자연수이다. 
둘째 줄부터 N개의 줄에 각 사람이 친구이면 Y, 아니면 N이 주어진다.

출력
첫째 줄에 가장 유명한 사람의 2-친구의 수를 출력한다.
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
        int[][] friends =  makeFriendsArr( N,br);
        int ans =returnMaxFriendsCnt(N ,friends);

        System.out.println(ans);
    }

    private static int returnMaxFriendsCnt(int N ,int[][] friends){
        int max =0;
        for(int i=0;i<N;i++){
            boolean[] seen = new boolean[N];

            for(int j=0;j<N;j++){
                if(friends[i][j]==1){
                    seen[j] = true;
                    findFriendsFriends(N,seen,friends[j]);
                }
            }
            seen[i] = false;//자기자신 제외
            max = Math.max(max,count2friends( N,seen));
        }
        return max;
    }

    private static void findFriendsFriends(int N,boolean[] seen,int[] friend){
        for(int i=0;i<N;i++){
            if(friend[i]==1)seen[i]=true;
        }
    }

    private static int count2friends(int N,boolean[] seen){
        int cnt=0;
        for(int i=0;i<N;i++)if(seen[i]==true)cnt++;
        return  cnt;
    }

    private static int[][] makeFriendsArr(int N,BufferedReader br) throws IOException{
        int[][] friends = new int[N][N];
        int read = 0;
        while(read<N){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for(int i=0;i<N;i++)friends[read][i]=str.charAt(i)=='N'?0:1;
            read++;
        }
        return friends;
    }
}