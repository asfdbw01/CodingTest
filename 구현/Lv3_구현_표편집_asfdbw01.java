/*
 명령어 기반으로 표의 행을 선택, 삭제, 복구하는 프로그램을 작성하는 과제를 맡았습니다.
"U X": 현재 선택된 행에서 X칸 위에 있는 행을 선택합니다.
"D X": 현재 선택된 행에서 X칸 아래에 있는 행을 선택합니다.
"C" : 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 
    단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다.
"Z" : 가장 최근에 삭제된 행을 원래대로 복구합니다. 단, 현재 선택된 행은 바뀌지 않습니다.

제한사항
5 ≤ n ≤ 1,000,000
0 ≤ k < n
1 ≤ cmd의 원소 개수 ≤ 200,000
cmd의 각 원소는 "U X", "D X", "C", "Z" 중 하나입니다.
X는 1 이상 300,000 이하인 자연수이며 0으로 시작하지 않습니다.
X가 나타내는 자연수에 ',' 는 주어지지 않습니다. 예를 들어 123,456의 경우 123456으로 주어집니다.
cmd에 등장하는 모든 X들의 값을 합친 결과가 1,000,000 이하인 경우만 입력으로 주어집니다.
표의 모든 행을 제거하여, 행이 하나도 남지 않는 경우는 입력으로 주어지지 않습니다.
본문에서 각 행이 제거되고 복구되는 과정을 보다 자연스럽게 보이기 위해 "이름" 열을 사용하였으나, "이름"열의 내용이 실제 문제를 푸는 과정에 필요하지는 않습니다. "이름"열에는 서로 다른 이름들이 중복없이 채워져 있다고 가정하고 문제를 해결해 주세요.
표의 범위를 벗어나는 이동은 입력으로 주어지지 않습니다.
원래대로 복구할 행이 없을 때(즉, 삭제된 행이 없을 때) "Z"가 명령어로 주어지는 경우는 없습니다.


정답은 표의 0행부터 n - 1행까지에 해당되는 O, X를 순서대로 이어붙인 문자열 형태로 return 해주세요.

*/

import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] removed = new boolean[n];
        Deque<Integer> trash = new ArrayDeque<>();

        initLinks(n, prev, next);

        int cur = k;
        for (String c : cmd) {
            char op = c.charAt(0);
            if (op == 'U') {
                cur = moveUp(cur, parseStep(c), prev);
            } else if (op == 'D') {
                cur = moveDown(cur, parseStep(c), next);
            } else if (op == 'C') {
                cur = deleteRow(cur, prev, next, removed, trash);
            } else if (op == 'Z'){ 
                undoDelete(prev, next, removed, trash);
            }
        }
        
        answer = buildAnswer(removed);
        return answer;
    }
    
    private String buildAnswer(boolean[] removed) {
        char[] out = new char[removed.length];
        Arrays.fill(out, 'O');
        for (int i = 0; i < removed.length; i++) {
            if (removed[i]) out[i] = 'X';
        }
        return new String(out);
    }
    
    private void undoDelete(int[] prev,int[] next,boolean[] removed,Deque<Integer> trash){
        int rec = trash.pop();
        removed[rec] = false;

        int p = prev[rec];
        int q = next[rec];

        if (p != -1) next[p] = rec;
        if (q != -1) prev[q] = rec;
    }
    
    private int deleteRow(int cur,int[] prev,int[] next,boolean[] removed,Deque<Integer> trash){
        trash.push(cur);
        removed[cur] = true;
        
        int p = prev[cur];
        int q = next[cur];
        
        if (p != -1) next[p] = q;
        if (q != -1) prev[q] = p;

        return (q != -1) ? q : p;
    }
    
    private int moveUp(int cur, int steps, int[] prev) {
        while (steps-- > 0) cur = prev[cur]; 
        return cur;
    }
    
    private int moveDown(int cur, int steps, int[] next) {
        while (steps-- > 0) cur = next[cur]; 
        return cur;
    }
    
    private void initLinks(int n,int[] prev, int[] next){
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = (i == n - 1) ? -1 : i + 1;
        }
    }
    
    private int parseStep(String cmd) {
        // 숫자만 파싱
        return Integer.parseInt(cmd.substring(2));
    }
    
}