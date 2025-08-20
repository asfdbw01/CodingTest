/*
도둑이 어느 마을을 털 계획을 하고 있습니다. 이 마을의 모든 집들은 동그랗게 배치되어 있습니다.
인접한 두 집을 털면 경보가 울립니다.
각 집에 있는 돈이 담긴 배열 money가 주어질 때, 도둑이 훔칠 수 있는 돈의 최댓값을 return 

제한사항
이 마을에 있는 집은 3개 이상 1,000,000개 이하입니다.
money 배열의 각 원소는 0 이상 1,000 이하인 정수입니다.
*/

class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int len = money.length;
        int[] dpFirstHouse = new int[len];
        int[] dpLastHouse = new int[len];
        for(int i=0;i<len;i++){
            dpFirstHouse[i] = money[i]; 
            dpLastHouse[i] = money[i];
        }
        dpFirstHouse[1]=Integer.MIN_VALUE;
        dpFirstHouse[2] += dpFirstHouse[0];
        dpLastHouse[0] = Integer.MIN_VALUE;
        for(int i=3;i<len;i++){
            dpFirstHouse[i] += Math.max(dpFirstHouse[i-2],dpFirstHouse[i-3]);
            dpLastHouse[i] += Math.max( dpLastHouse[i-2], dpLastHouse[i-3]);
        }
        int FirstMax = Math.max(dpFirstHouse[len-2],dpFirstHouse[len-3]);
        int LastMax = Math.max(dpLastHouse[len-1],dpLastHouse[len-2]);
        answer = Math.max(FirstMax,LastMax);
        return answer;
    }
}