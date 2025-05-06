// 문제 요약:
// 주어진 0 이상 정수 배열 numbers에서 숫자를 이어 붙여 만들 수 있는 가장 큰 수를 구하는 문제.
// 예: [6, 10, 2] → 가능한 조합 중 가장 큰 수는 6210.
// 숫자가 매우 클 수 있으므로 결과는 문자열로 반환해야 함.

import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] numStr = new String[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            numStr[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(numStr, (a, b) -> (b + a).compareTo(a + b));
        
        StringBuilder sb = new StringBuilder();
        for(String s: numStr){
            sb.append(s);
        }
        if (sb.charAt(0) == '0') return "0"; "0~00일경우 0으로 변환"
        answer = sb.toString();
        return answer;
    }
}
