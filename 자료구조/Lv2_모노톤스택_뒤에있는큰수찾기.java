/*
 * 문제 요약
 *     - 정수 배열 numbers에서 각 원소에 대해 "자신보다 뒤에 있는 수 중 가장 가까이 있는 큰 수"를 찾음
 *     - 해당 수가 없으면 -1을 결과 배열에 담음
 * 
 * 입력
 *     - numbers: 길이 4 이상 1,000,000 이하의 정수 배열
 *     - 각 원소는 1 이상 1,000,000 이하의 정수
 * 
 * 출력
 *     - 각 인덱스에 대해 '뒷 큰수'를 담은 정수 배열
 *     - 존재하지 않으면 해당 위치에 -1
 * 
 * 핵심 포인트
 *     - 완전 탐색은 O(N²)로 시간 초과 발생
 *     - 뒤에서부터 탐색하며 Stack 또는 Deque로 "뒤에서 가까운 큰 수" 추적
 *     - Stack에는 "현재까지 만나온 오른쪽 수 중 큰 값들"만 유지
 *     - 최종 시간복잡도 O(N) 보장
 */

class Solution {

	public int[] solution(int[] numbers) {
		int[] answer = new int[numbers.length];
		Deque<Integer> stack = new ArrayDeque<>();
		
		// 뒤에서부터 탐색
		for (int i = numbers.length - 1; i >= 0; i--) {
			// 현재 수보다 작거나 같은 수는 뒷 큰수가 될 수 없음 → 제거
			while (!stack.isEmpty()) {
				if (stack.peekLast() <= numbers[i]) stack.pollLast();
				else {
					answer[i] = stack.peekLast(); // 뒷 큰수 발견
					break;
				}
			}
			
			// 스택이 비었다면 뒷 큰수가 없음
			if (stack.isEmpty()) answer[i] = -1;
			
			// 현재 수를 스택에 추가 (다음 수의 뒷 큰수 후보가 됨)
			stack.addLast(numbers[i]);
		}
		
		return answer;
	}
}
