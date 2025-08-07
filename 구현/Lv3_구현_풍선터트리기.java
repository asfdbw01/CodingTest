/*
문제 요약: 풍선 터트리기 게임
- 일렬로 서로 다른 숫자가 적힌 풍선들이 있음.
- 인접한 두 풍선 중 "번호가 더 작은 풍선"을 터트리는 행동은 최대 1번만 가능.
- 그 외의 경우는 "번호가 더 큰 풍선"만 터트릴 수 있음.
- 규칙에 따라 풍선을 터트렸을 때 최후까지 남을 수 있는 풍선의 개수를 구하라.

핵심 아이디어:
- 어떤 풍선이 마지막까지 남으려면, 해당 풍선이 왼쪽 방향 또는 오른쪽 방향에서
  최소값이어야 함(둘 중 하나만 만족해도 가능).
- 왼쪽에서 최소값 / 오른쪽에서 최소값을 선형 스캔으로 구해 표시 후 개수 세기.
- 시간 복잡도: O(n), 공간 복잡도: O(n)
*/

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int n = a.length;
        if(n<2)return n;
        
        boolean[] possible = new boolean[n];
        int minL = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(a[i] < minL){
                possible[i] = true;
                minL = a[i];
            }
        }
        
        int minR = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (a[i] < minR) {
                possible[i] = true; 
                minR = a[i];
            }
        }
        
        for(boolean b : possible)if(b)answer++;
        return answer;
    }
}
