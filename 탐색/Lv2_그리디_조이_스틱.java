/*
[문제 요약]
- 이름을 완성하기 위해 조이스틱을 조작해야 함.
- 초기 상태는 모두 'A'.
- 조작 방식:
  ▲ : 알파벳 다음 문자 (Z 다음은 A)
  ▼ : 알파벳 이전 문자 (A 이전은 Z)
  ◀ : 왼쪽으로 이동 (0번 인덱스에서 왼쪽은 마지막으로)
  ▶ : 오른쪽으로 이동 (마지막 인덱스에서 오른쪽은 처음으로)

[목표]
- 주어진 문자열 name을 완성하기 위한 조이스틱 조작의 **최솟값**을 구하라.

[주의 사항]
- 문자 변경 횟수 + 커서 이동 횟수를 모두 고려해야 함.
- 연속된 'A'는 스킵할 수 있기 때문에 커서 경로 최적화가 필요함.
- 커서 이동은 양방향 가능하므로, 되돌아오는 경로가 더 짧을 수 있음.

[입력]
- name: 알파벳 대문자로 구성된 문자열 (길이 1~20)

[출력]
- 최소 조작 횟수 (int)
*/


class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        
        //상하 조작
        for(int i=0;i<len;i++){
            char c =name.charAt(i);
            answer += Math.min(c-'A','Z'-c+1);
        }
        
        //좌우
        int minMove = len-1;
        for(int i=0;i<len ;i++){
            int next = i+1;
            while(next < len && name.charAt(next)=='A'){
                next++;
            }
            minMove = Math.min(minMove,i*2+len-next);
            minMove = Math.min(minMove,(len-next)*2+i);
        }
        answer += minMove;
        return answer;
    }
}
