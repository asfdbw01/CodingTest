/*
문제 요약:
- 주어진 문자열은 '(', ')'로 구성된 균형잡힌 괄호 문자열임 (괄호 수는 맞음).
- 이 문자열을 올바른 괄호 문자열로 변환하는 것이 목적.

용어 정리:
- 균형잡힌 괄호 문자열: '('와 ')'의 수가 같음.
- 올바른 괄호 문자열: 괄호 짝이 올바르게 맞음. 예: "(())", "()()"

변환 알고리즘 요약:
1. 입력이 빈 문자열이면 빈 문자열 반환.
2. w를 두 개의 균형잡힌 문자열 u, v로 분리 (u는 최소 단위의 균형 문자열).
3. u가 올바른 괄호 문자열이라면:
   - v를 재귀적으로 변환한 뒤 u에 이어붙여 반환.
4. u가 올바르지 않다면:
   - "(" + solution(v) + ")" + reverse(u의 앞뒤 제거 후 나머지 반전)
   - reverse는 괄호 방향 뒤집는 것: '(' ↔ ')'

예시:
"()))((()"
→ u = "()", v = "))((()"
→ u는 올바르므로 유지, v 변환
→ v의 u = "))((", v = "()" → u 잘못됨
→ 변환: "(" + solution("()") + ")" + reverse("))((") → "(())()"
→ 최종 결과: "()" + "(())()" = "()(())()"
*/

import java.util.*;

class Solution {
    public String solution(String p) {
        if (p.equals("")) return "";
        
        String[] separate = separation(p);
        String u = separate[0];
        String v = separate[1];

        if (isPerfect(u)) {
            return u + solution(v);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(solution(v));
            sb.append(")");
            sb.append(reverse(u.substring(1, u.length() - 1)));
            return sb.toString();
        }
    }
    
    private String[] separation(String str){
        String[]  separate = new String[2];
        int cntLeft=0,cntRight =0;
        int i;
        for(i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(c=='(')cntLeft++;
            else if(c==')')cntRight++;
            if(cntLeft==cntRight)break;
        }
        
        separate[0] = str.substring(0, i + 1); // u
        separate[1] = str.substring(i + 1);    // v
        return separate;
    }
    
    private boolean isPerfect(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else { // c == ')'
                if (stack.isEmpty()) {
                    return false; // 짝이 안 맞음
                }
                stack.pop();
            }
        }
        return stack.isEmpty(); // 모두 짝 맞으면 true
    }
    
    private String reverse(String str){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            sb.append(c=='('? ')' : '(');
        }
        return sb.toString();
    }

}
