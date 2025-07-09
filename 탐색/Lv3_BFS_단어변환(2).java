/*  
 * 문제 요약
 *     - begin 단어에서 시작해 target 단어로 변환하되, 한 번에 한 글자만 바꿀 수 있고, 바뀐 단어는 words에 존재해야 함
 *     - 이때 최소 몇 단계의 변환이 필요한지를 구하는 문제
 *
 * 입력  
 *     - begin: 시작 단어 (String)
 *     - target: 목표 단어 (String)
 *     - words: 변환 가능한 단어 리스트 (String[])
 *
 * 출력  
 *     - begin에서 target으로 변환하는 최소 단계 수 (int)
 *     - 변환할 수 없다면 0 반환
 *
 * 핵심 포인트  
 *     - 각 단어를 정점으로 보고, 한 글자만 다른 단어 사이에 간선이 있다고 간주
 *     - BFS를 사용하여 최소 경로를 탐색 (최단 거리)
 *     - begin은 words에 포함되지 않을 수 있으며, 시작점으로만 활용
 *     - 변환 조건은 '정확히 한 글자만 다른 경우'로 제한
 */

class Solution {
   
   public int solution(String begin, String target, String[] words) {
      boolean[] visited = new boolean[words.length];
      Queue<Word> que = new LinkedList<>();
      que.add(new Word(begin, 0));  // 시작 단어와 깊이 0으로 초기화
      
      while (!que.isEmpty()) {
         Word cur = que.poll();
         
         for (int i = 0; i < words.length; i++) {
            // 아직 방문하지 않았고, 한 글자만 다른 단어인 경우
            if (!visited[i] && canConvert(cur.word, words[i])) {
               if (words[i].equals(target)) return cur.depth + 1;
               visited[i] = true;
               que.add(new Word(words[i], cur.depth + 1));
            }
         }
      }
      
      return 0;  // 변환 불가능한 경우
   }
   
   // 두 단어가 정확히 한 글자만 다른 경우 true 반환
   public boolean canConvert(String a, String b) {
      int count = 0;
      for (int i = 0; i < a.length(); i++) {
         if (a.charAt(i) != b.charAt(i)) count++;
      }
      return count == 1;
   }
   
   // 단어와 변환 깊이를 저장할 클래스
   class Word {
      String word;
      int depth;
      
      Word(String word, int depth) {
         this.word = word;
         this.depth = depth;
      }
   }
}
