/*  
 * 문제 요약
 *     - 인접한 풍선 두 개 중 더 작은 번호를 터트리는 행동은 최대 1번만 가능할 때,
 *       최후까지 남을 수 있는 풍선의 개수를 구함
 *
 * 입력  
 *     - int[] a: 각 풍선에 적힌 정수 번호 (서로 다름, 길이 ≤ 1,000,000)
 *
 * 출력  
 *     - 조건을 만족하며 최후까지 남을 수 있는 풍선 개수 (int)
 *
 * 핵심 포인트  
 *     - 어떤 풍선이 마지막까지 남으려면 좌/우 중 한쪽 구간에서는 해당 풍선이 최소값이어야 함
 *     - 따라서 prefixMin[i], suffixMin[i]를 각각 구한 뒤,
 *       a[i]가 둘 중 하나의 최소값과 같다면 생존 가능
 */

class Solution {
	
    public int solution(int[] a) {
        int len = a.length;
        
        int[] prefixMin = new int[len];  // 0~i까지 최소값
        int[] suffixMin = new int[len];  // i~n-1까지 최소값
        
        prefixMin[0] = a[0];
        for (int i = 1; i < len; i++) {
            prefixMin[i] = Math.min(a[i], prefixMin[i - 1]);  // 왼쪽부터 누적 최소
        }
        
        suffixMin[len - 1] = a[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(a[i], suffixMin[i + 1]);  // 오른쪽부터 누적 최소
        }
        
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (a[i] == prefixMin[i] || a[i] == suffixMin[i]) count++;  // 한 쪽 이상에서 최소값이면 생존 가능
        }
        
        return count;
    }
	
}
