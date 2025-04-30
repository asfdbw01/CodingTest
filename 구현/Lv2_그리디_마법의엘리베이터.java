// 문제 요약:
// 마법 엘리베이터는 ±1, ±10, ±100, ... 형식의 버튼만 있음
// 목표: storey 층에서 0층까지 최소 횟수(버튼 수)로 도달
// 방법: 각 자릿수에서 내릴지(작게 만들지), 올릴지(다음 자리 반영할지) 그리디하게 결정
// 핵심: 일의 자리부터 왼쪽으로 탐색하며 올림(carry) 여부 판단



class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while (storey > 0) {
            int num = storey % 10;

            if (num <= 4) {
                answer += num;
            } else if (num >= 6) {
                answer += 10 - num;
                storey += 10;
            } else {  // num == 5
                int next = (storey / 10) % 10;
                if (next >= 5) {
                    answer += 10 - num;
                    storey += 10;
                } else {
                    answer += num;
                }
            }

            storey /= 10;
        }

        
        return answer;
    }
}
