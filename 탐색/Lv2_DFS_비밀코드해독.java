/*
// 시스템은 1부터 n까지의 서로 다른 정수 5개가 오름차순으로 정렬된 비밀 코드를 가지고 있으며, 당신은 이 비밀 코드를 맞혀야 합니다.
// 당신은 비밀 코드를 알아내기 위해 암호 분석 도구를 사용하며, m번의 시도를 할 수 있습니다
// 각 시도마다 서로 다른 5개의 정수를 입력하면, 시스템은 그 중 몇 개가 비밀 코드에 포함되어 있는지 알려줍니다.
// 만약 비밀 코드가 [3, 5, 7, 9, 10]이고, 입력한 정수가 [1, 2, 3, 4, 5]라면 비밀 코드에 포함된 정수는 3, 5 두 개이므로 시스템은 2를 응답합니다.
// 당신은 m번의 시도 후, 비밀 코드로 가능한 정수 조합의 개수를 알고 싶습니다.
제한사항
10 ≤ n ≤ 30
1 ≤ (q의 길이 = m) ≤ 10
q[i]의 길이 = 5
q[i]는 i+1번째 시도에서 입력한 5개의 서로 다른 정수를 담고 있으며, 오름차순으로 정렬되어 있습니다.
1 ≤ q[i][j] ≤ n
ans의 길이 = m
ans[i]는 i+1번째 시도에서 입력한 5개의 정수 중 비밀 코드에 포함된 정수의 개수를 나타냅니다.
0 ≤ ans[i] ≤ 5
비밀 코드가 존재하지 않는(답이 0인) 경우는 주어지지 않습니다.

*/


import java.util.*;

class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        List<List<Integer>> combinations = new ArrayList<>();
        dfs(n,1,new ArrayList<>(),combinations);
        //이제 여기에 q ans 비교해서 참이면 answer++해주기
        for(List<Integer>list : combinations){
            if(isVaild(list,q,ans))answer++;
        }
        return answer;
    }
    
    //순열 생성
    private void dfs(int n,int start,List<Integer> picked,List<List<Integer>> result) {
        
        if(picked.size()==5){
            result.add(new ArrayList<>(picked));
            return;
        }
        
        for (int i=start;i<=n;i++) {
            picked.add(i);
            dfs(n,i+1,picked,result);
            picked.remove(picked.size()-1);
        }
    }
    
    //비교 메서드
    private boolean isVaild(List<Integer> picked,int[][] q, int[] ans){
        for(int i=0;i<q.length;i++){
            int matched =0;
            for(int num : q[i]){
                if(picked.contains(num))matched++;
            }
            if(matched != ans[i])return false;
        }
        return true;
    }
}
