// 문제 요약 주석
/*
[문제 개요]
- 주어진 begin 단어를 target 단어로 변환한다.
- 한 번에 한 글자만 바꿀 수 있고,
- 바꾼 단어는 반드시 words 리스트 안에 있어야 한다.

[목표]
- begin → target 으로 변환하는 **최소 단계 수**를 구하라.

[조건]
- 각 단어는 길이가 같고, 알파벳 소문자만 포함한다.
- 변환 경로 중간의 모든 단어는 words 내에 있어야 한다.
- 변환 불가능할 경우 0을 반환.

[예시]
1. begin = "hit", target = "cog"
   words = ["hot", "dot", "dog", "lot", "log", "cog"]
   → "hit" → "hot" → "dot" → "dog" → "cog" → 정답: 4

2. begin = "hit", target = "cog"
   words = ["hot", "dot", "dog", "lot", "log"]
   → "cog"가 없으므로 변환 불가 → 정답: 0
*/
import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        List<String> wordsList = Arrays.asList(words);
        answer = bfs(begin,target, wordsList);
        
        return answer;
    }
    
    
    
    private boolean canConvert(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return diff == 1;
    }

    
    private int bfs(String begin,String target,List<String> wordsList){
        boolean[] visited = new boolean[wordsList.size()];
        Queue<String> queue = new LinkedList<>();
        Queue<Integer> depth = new LinkedList<>();
        queue.add(begin);
        depth.add(0);
        
        while(!queue.isEmpty()){
            String cur = queue.poll();
            int curDepth = depth.poll();
            
            if (cur.equals(target)) return curDepth;
            
            for (int i = 0; i < wordsList.size(); i++) {
                if (!visited[i] && canConvert(cur, wordsList.get(i))) {
                    visited[i] = true;
                    queue.add(wordsList.get(i));
                    depth.add(curDepth + 1);
                }
            }

        }
    
        return 0;
    }
    
    
}
