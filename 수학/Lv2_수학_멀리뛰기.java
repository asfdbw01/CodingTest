/*
// 한번에 1칸, 또는 2칸을 뛸 수 있습니다
//칸이 총 4개 있을 때, 
//(1칸, 1칸, 1칸, 1칸)
//(1칸, 2칸, 1칸)
//(1칸, 1칸, 2칸)
//(2칸, 1칸, 1칸)
//(2칸, 2칸)
//의 5가지 방법 있습니다
//끝에 도달하는 방법이 몇 가지인지 알아내, 여기에 1234567를 나눈 나머지를 리턴하는 함수
*/


//이론상 n-1번째 칸에서 1칸 뛰는 경우수 + n-2 번쨰 칸에서 2칸 뛰는 경우수 = n[n-1]+n[n-2] = 피보나치
class Solution {
    public long solution(int n) {
        long answer = 1;
        long prev = 1;
        long now =1;
        //피보나치 인듯?
        for(int i=1;i<n;i++){
            answer = (prev+now)%1234567;
            prev = now;
            now = answer;
        }
        
        return answer;
    }
}
