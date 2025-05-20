/*
//괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 반드시 짝지어서 ')' 문자로 닫혀야 한다는 뜻
// 전체다 짝지어지면 true 아님 false
*/


import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Stack<Character>left = new Stack<>();
        Queue<Character>right = new LinkedList<>();
        
        for(int i=0;i<s.length();i++){
            right.add(s.charAt(i));
        }
        
        while(!right.isEmpty()){
            char target = right.poll();
            if(target =='('){left.push(target);}
            else if(!left.isEmpty() && left.peek()=='('){
                left.pop();
            }
            else return false;
            
        }
        
        //left 에 뭔가 남았으면 false
        if(left.size() >0)return false;
        
        return answer;
    }
}
