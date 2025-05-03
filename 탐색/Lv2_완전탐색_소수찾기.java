/*
문제 요약:
- 주어진 문자열 numbers는 0~9 숫자로 구성된 종이 조각들이다.
- 이 종이 조각들을 순열로 조합해서 만들 수 있는 '모든 숫자'를 구한다.
- 그 중에서 소수인 숫자가 몇 개인지 반환한다.

조건:
- numbers의 길이는 1 이상 7 이하.
- 숫자들은 중복 가능(예: "011" 등).
- 만들어진 숫자는 중복 제거 (예: 011 == 11).

예시:
"17" -> 만들 수 있는 소수: 7, 17, 71 (총 3개)
"011" -> 만들 수 있는 소수: 11, 101 (총 2개)
*/


import java.util.*;

class Solution {
    
    Set<Integer> set = new HashSet<>();
    
    public int solution(String numbers) {
        int answer = 0;
        
        mix("",numbers);
        for(int i:set){
            if(isPirme(i))answer++;
        }
        return answer;
    }
    
    public boolean isPirme(int num){
        if (num <= 1) return false;
        for(int i=2;i<=(int)Math.sqrt(num);i++){
            if(num%i==0)return false;
        }
        return true;
    }
    
    public void mix(String prefix, String remaining) {
        if (!prefix.isEmpty()) {
            set.add(Integer.parseInt(prefix));
        }
        for (int i = 0; i < remaining.length(); i++) {
            mix(prefix + remaining.charAt(i), 
                remaining.substring(0, i) + remaining.substring(i + 1));
        }
    }

    
}
