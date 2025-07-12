/*
 * 문제 요약
 *     - 원형 수열에서 연속 부분 수열의 합으로 만들 수 있는 서로 다른 수의 개수를 구하라.
 * 
 * 입력
 *     - int[] elements: 원형 수열 (길이 3 이상 1,000 이하, 원소는 1 이상 1,000 이하의 자연수)
 * 
 * 출력
 *     - 만들 수 있는 연속 부분 수열 합의 개수 (중복 없이)
 * 
 * 핵심 포인트
 *     - 원형 수열이므로 끝과 처음이 연결됨 (인덱스 wrap-around 처리)
 *     - 부분합 계산을 O(1)로 하기 위해 누적합(prefix sum) 사용
 *     - 길이 n 미만 부분합은 모두 탐색, 길이 n은 마지막에 +1로 보정
 */

class Solution {
	
	public int solution(int[] elements) {
	    int n = elements.length;
	    int[] prefix = new int[n * 2 + 1];
	    
	    // 누적합 배열 생성
	    for (int i = 0; i < n * 2; i++) {
	        prefix[i + 1] = prefix[i] + elements[i % n];
	    }

	    Set<Integer> set = new HashSet<>();

	    // 길이 1부터 n-1까지의 연속 부분합을 모두 집합에 추가
	    for (int len = 1; len < n; len++) {
	        for (int start = 0; start < n; start++) {
	            int sum = prefix[start + len] - prefix[start];
	            set.add(sum);
	        }
	    }

	    return set.size() + 1;  // 길이 n 전체 합은 마지막에 +1 보정
	}
}
