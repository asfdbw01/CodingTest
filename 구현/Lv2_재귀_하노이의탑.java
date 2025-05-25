/*
// 하노이의 탑
// 이동경로를 return 해주면 됨
*/

import java.util.*;

class Solution {
    public int[][] solution(int n) {
        int[][] answer = {};
        List<int[]> answerList = new ArrayList<>();
        hanoi(answerList, n,1,3,2);
        answer = answerList.toArray(new int[answerList.size()][]);
        return answer;
    }
    
    private void hanoi(List<int[]> answerList,int n,int from,int to,int via){
        if(n==1){
            answerList.add(new int[]{from,to});
            return;
        }
        
        hanoi(answerList,n-1,from,via,to);
        answerList.add(new int[]{from,to});
        hanoi(answerList,n-1,via,to,from);
    }
}
