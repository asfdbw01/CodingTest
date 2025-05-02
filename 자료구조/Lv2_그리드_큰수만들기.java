[1] 스택 준비
[2] 문자열 순회:
      - (조건 만족 시) pop + k 감소
      - push
[3] 아직 k > 0 이면 뒤에서 더 pop
[4] StringBuilder로 결과 조립


import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        Stack<Character> stack = new Stack<>();
        
        for(char digit : number.toCharArray()){
            while(!stack.isEmpty() && k>0 &&stack.peek() < digit){
                stack.pop();
                k--;
            }
            stack.push(digit);
        }
        
        while(k>0){
            stack.pop();
            k--;
        }
        
        for(char c: stack){
            answer += c;    
        }
        
        
        return answer;
    }
    
    
}
