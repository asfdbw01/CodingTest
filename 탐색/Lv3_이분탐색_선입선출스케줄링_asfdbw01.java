/*
처리해야 할 동일한 작업이 n 개가 있고, 이를 처리하기 위한 CPU가 있습니다.

이 CPU는 다음과 같은 특징이 있습니다.

CPU에는 여러 개의 코어가 있고, 코어별로 한 작업을 처리하는 시간이 다릅니다.
한 코어에서 작업이 끝나면 작업이 없는 코어가 바로 다음 작업을 수행합니다.
2개 이상의 코어가 남을 경우 앞의 코어부터 작업을 처리 합니다.
처리해야 될 작업의 개수 n과, 각 코어의 처리시간이 담긴 배열 cores 가 매개변수로 주어질 때, 마지막 작업을 처리하는 코어의 번호를 return 하는 solution 함수를 완성해주세요.

제한 사항
코어의 수는 10,000 이하 2이상 입니다.
코어당 작업을 처리하는 시간은 10,000이하 입니다.
처리해야 하는 일의 개수는 50,000개를 넘기지 않습니다.
*/


class Solution {
    public int solution(int n, int[] cores) {
        int answer = -1;
        //이분탐색인가?
        
        long left = 0;
        long right = 10000*n;
        long time = 0;
        
        while(left <= right){
            long mid = (left+right)/2;
            //mid 초 까지의 작업 배정 수 함수
            long cnt = countWork(cores, mid);
            if(cnt >= n){
                time = mid;
                right = mid-1;
            }
            else left = mid +1;
        }
        
        //time-1 초까지의 작업량
        long cnt = countWork(cores,time-1);
        long remain = n-cnt;
        
        //비는 코어
        for(int i=0;i<cores.length;i++){
            if(time % cores[i]==0){
                remain--;
                if(remain==0)return i+1;
            }
        }
        return answer;
    }
    
    private long countWork(int[] cores,long mid){
        if(mid <0)return 0;
        long cnt = cores.length;
        for(int core : cores){
            cnt += mid/core;
        }
        return cnt;
    }
    
}