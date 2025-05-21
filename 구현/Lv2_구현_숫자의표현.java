/*
//n을 연속한 자연수들의 합으로 표현 하는 방법의 경우수
*/

class Solution {
    public int solution(int n) {
        int answer = 1;
        
        for(int i=1;i<=n/2;i++){
            if(sequence(i,n))answer++;
        }
        
        
        return answer;
    }
    
    private boolean sequence(int start,int target){
        int sum=0;
        int num = start;
        while(sum<target){
            sum+=num;
            num++;
            if(sum==target)return true;
        }
        return false;
    }
}

/*
//좀더 간단한 버전
class Solution {
    public int solution(int n) {
        int count = 0;

        for (int k = 1; k * (k - 1) / 2 < n; k++) {
            if ((n - k * (k - 1) / 2) % k == 0) {
                count++;
            }
        }

        return count;
    }
}

*/
