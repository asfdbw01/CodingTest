/*
문제
왕비를 피해 일곱 난쟁이들과 함께 평화롭게 생활하고 있던 백설공주에게 위기가 찾아왔다. 
일과를 마치고 돌아온 난쟁이가 일곱 명이 아닌 아홉 명이었던 것이다.

아홉 명의 난쟁이는 모두 자신이 "백설 공주와 일곱 난쟁이"의 주인공이라고 주장했다. 
뛰어난 수학적 직관력을 가지고 있던 백설공주는, 
다행스럽게도 일곱 난쟁이의 키의 합이 100이 됨을 기억해 냈다.

아홉 난쟁이의 키가 주어졌을 때, 백설공주를 도와 일곱 난쟁이를 찾는 프로그램을 작성하시오.

입력
아홉 개의 줄에 걸쳐 난쟁이들의 키가 주어진다. 
주어지는 키는 100을 넘지 않는 자연수이며, 아홉 난쟁이의 키는 모두 다르며, 
가능한 정답이 여러 가지인 경우에는 아무거나 출력한다.

출력
일곱 난쟁이의 키를 오름차순으로 출력한다. 일곱 난쟁이를 찾을 수 없는 경우는 없다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static int[] tall;
    static boolean found = false;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int person = 9;
        int need = 7;
        tall = new int[person];
        int[] stay = new int[need];
        for(int i=0;i<person;i++){
            tall[i] = Integer.parseInt(br.readLine());
        }    
        Arrays.sort(tall);
        dfs(need, person, 0, 0, 0, stay);
        
    }

    static void dfs(int need,int person,int idx, int depth, int sum, int[] pick) {
        if(found)return;

        if(depth ==  need){
            if(sum==100){
                StringBuilder sb = new StringBuilder();
                for(int i=0;i<need;i++)sb.append(pick[i]).append("\n");
                System.out.println(sb.toString());
                found = true;
            }
            return;
        }

        if(idx==person)return;

        if(depth +(person-idx)< need)return;

        pick[depth] = tall[idx];
        dfs(need, person, idx+1, depth+1, sum+tall[idx], pick);
        dfs(need, person, idx+1, depth, sum, pick);
    }
}