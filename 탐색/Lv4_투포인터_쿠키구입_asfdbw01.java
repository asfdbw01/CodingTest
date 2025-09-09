/*
 1번부터 N번까지 차례로 번호가 붙은 바구니 N개가 일렬로 나열해 놨습니다.
철수는 두 아들에게 줄 과자를 사려합니다
 첫째 아들에게는 l번 바구니부터 m번 바구니까지, 
둘째 아들에게는 m+1번 바구니부터 r번 바구니까지를 주려합니다
단, 두 아들이 받을 과자 수는 같아야 합니다

각 바구니 안에 들은 과자 수가 차례로 들은 배열 cookie가 주어질 때, 
조건에 맞게 과자를 살 경우 한 명의 아들에게 줄 수 있는 
가장 많은 과자 수를 return 하는 solution 함수를 완성해주세요.
단, 조건에 맞게 과자를 구매할 수 없다면 0을 return


제한사항
cookie의 길이는 1 이상 2,000 이하입니다.
cookie의 각각의 원소는 1 이상 500 이하인 자연수입니다.
*/

class Solution {
    public int solution(int[] cookie) {
        int n = cookie.length;
        int answer = 0;

        
        for (int mid = 0; mid < n - 1; mid++) {
            answer = Math.max(answer, maxEqualAround(cookie, mid, mid + 1));
        }
        return answer;
    }
    //중심값 기준으로 양옆으로 쿠키를 더해, 값이 같아지는 경우
    private int  maxEqualAround(int[] cookie,int L,int R){
        int n=cookie.length;
        int left = L, right = R;
        int sumL = cookie[left], sumR = cookie[right];
        int best = 0;
         while (left >= 0 && right <n) {
            if (sumL == sumR) {
                best = Math.max(best, sumL);
                
                left--; right++;
                if (left >= 0) sumL += cookie[left];
                if (right < n) sumR += cookie[right];
            } else if (sumL < sumR) {
                left--;
                if (left < 0) break;
                sumL += cookie[left];
            } else { 
                right++;
                if (right >= n) break;
                sumR += cookie[right];
            }
        }
        return best;
    }
}