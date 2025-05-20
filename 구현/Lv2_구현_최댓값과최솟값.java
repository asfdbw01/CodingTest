/*
//문자열 s에는 공백으로 구분된 숫자들이 저장됨
//최소값과 최대값을 찾아 이를 "(최소값) (최대값)"형태의 문자열을 반환
*/

import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] sArray = s.split(" ");
        Arrays.sort(sArray,(a,b) ->Integer.parseInt(a)-Integer.parseInt(b) );
        answer = sArray[0] + " " + sArray[sArray.length-1];
        return answer;
    }
}
