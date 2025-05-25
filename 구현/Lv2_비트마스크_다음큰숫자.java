/*
문제 요약 📘
- 주어진 자연수 n보다 큰 수 중에서
- 2진수로 변환했을 때 **1의 개수가 같은 수**를 찾는다
- 그중에서 **가장 작은 수**를 return

예시 ✍️
n = 78 → 2진수 = 1001110 (1의 개수 = 4)
→ 다음 큰 숫자: 83 → 1010011 (1의 개수 = 4)

입력 🎯
- n: 1 이상 1,000,000 이하 자연수

출력 🧾
- 조건을 만족하는 가장 작은 정수 (int)

제한사항
- 반드시 n보다 커야 함
- 2진수로 변환했을 때 1의 개수가 같아야 함
- 가장 작은 수여야 함

접근 방법 💡
- Integer.bitCount(n)으로 1의 개수를 얻고,
- n+1부터 차례로 검사하면서
- 1의 개수가 같아지면 return

*/

class Solution {
    public int solution(int n) {
        int answer = n+1;
        int count = Integer.bitCount(n);
        while(Integer.bitCount(answer)!=count){
            answer++;
        }
        return answer;
    }
}
