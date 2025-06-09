/*
문제: N진수 게임

[게임 규칙]
- 0부터 시작해 숫자를 차례대로 n진법으로 표현하고, 각 자릿수를 돌아가며 한 글자씩 말한다.
- 사람 수는 m명이며, 튜브의 순서는 p번째이다 (1부터 시작).

[입력]
- n: 진법 (2~16)
- t: 튜브가 말해야 할 숫자 개수
- m: 게임 참가 인원 수
- p: 튜브의 순서 (1-based)

[출력]
- 튜브가 말해야 하는 숫자 t개를 문자열로 이어서 출력
- 10~15는 대문자 A~F로 표기 (예: 15 -> 'F')

[예시]
n=2, t=4, m=2, p=1 → "0111"
  → 전체 수열: 0 1 10 11 ... → 자릿수 나열: 0 1 1 0 1 1 ...
  → 튜브(1번)가 말해야 하는: index % m == p-1 → 0 2 4 6 → "0 1 1 1"

[전략]
1. 숫자를 0부터 n진법으로 변환해 문자열로 붙인다.
2. 해당 문자열에서 튜브 차례(p번째)를 찾아 t개 추출한다.
*/
class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder total = new StringBuilder();
        int num = 0;
        
        // t * m개의 문자 이상 생성될 때까지 숫자를 진법 변환하여 누적
        while (total.length() < t * m) {
            total.append(Integer.toString(num++, n).toUpperCase());
        }
        
        StringBuilder answer = new StringBuilder();
        // 튜브가 말해야 하는 문자를 t개 수집
        for (int i = 0; answer.length() < t; i++) {
            if (i % m == p - 1) {
                answer.append(total.charAt(i));
            }
        }
        
        return answer.toString();
    }
}
