/*
LZW 압축 알고리즘 문제 요약

1. 길이 1짜리 A~Z를 사전에 미리 등록한다. (색인 번호 1~26)
2. msg 문자열에서 가장 긴 문자열 w를 사전에서 찾는다.
3. w에 대한 색인 번호를 출력 리스트에 추가한다.
4. 다음 글자 c가 존재하면 w+c를 사전에 새로 등록한다.
5. 위 과정을 입력이 끝날 때까지 반복한다.

예시)
입력: "KAKAO"
과정:
 - 'K'는 사전에 있음 → 출력: 11, 등록: "KA" (27)
 - 'A'는 사전에 있음 → 출력: 1, 등록: "AK" (28)
 - "KA"는 사전에 있음 → 출력: 27, 등록: "KAO" (29)
 - 'O'는 사전에 있음 → 출력: 15

출력 결과: [11, 1, 27, 15]
*/


import java.util.*;

class Solution {
    static int index=1;
    public int[] solution(String msg) {
        int[] answer = {};
        
        HashMap<String,Integer> dict = new HashMap<>();
        createDict(dict);

        List<Integer> answerList = LZW(dict, msg);
        
        answer = answerList.stream().mapToInt(Integer::intValue).toArray();
        
        return answer;
    }
    
    private void createDict(HashMap<String,Integer> dict){
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            dict.put(String.valueOf(ch), index);
            index++;
        }
    }
    
    private List<Integer> LZW(HashMap<String, Integer> dict, String msg) {
        List<Integer> output = new ArrayList<>();
        int i = 0;
        while (i < msg.length()) {
            String w = "";
            while (i < msg.length()) {
                String next = w + msg.charAt(i);
                if (dict.containsKey(next)) {
                    w = next;
                    i++;
                } else {
                    break;
                }
            }

            output.add(dict.get(w));
            if (i < msg.length()) {
                dict.put(w + msg.charAt(i), index++);
            }
        }
        return output;
    }
    
}
