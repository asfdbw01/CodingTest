/*
//124 나라가 있습니다. 124 나라에서는 10진법이 아닌 다음과 같은 자신들만의 규칙으로 수를 표현합니다.
// 124 나라에는 자연수만 존재합니다.
// 124 나라에는 모든 수를 표현할 때 1, 2, 4만 사용합니다.
// 1 -> 1
// 2 -> 2
// 3 -> 4
// 4 -> 11
// 5 -> 12
*/ 

class Solution {
    public String solution(int n) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        while(n>0){
            int remainder = n %3;
            n /=3;
            
            if(remainder == 0){
                remainder = 4;
                n--;
            }
            sb.insert(0,remainder);
        }
        
        answer = sb.toString();
        return answer;
    }
}
