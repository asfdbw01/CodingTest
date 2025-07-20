/*  
 * 문제 요약  
 *     - 초 단위로 기록된 주식 가격에서 각 시점 이후 가격이 떨어지지 않은 기간(초 단위)을 계산  
 *
 * 입력  
 *     - prices: int[] (주식 가격 배열, 길이 2 이상 100,000 이하)  
 *
 * 출력  
 *     - int[] (각 시점마다 가격이 떨어지지 않은 기간)  
 *
 * 핵심 포인트  
 *     - 스택을 이용해 가격이 떨어지는 시점을 만날 때까지 대기  
 *     - 가격이 떨어지는 시점에서 해당 인덱스와 현재 인덱스 차이를 정답으로 저장  
 *     - 끝까지 떨어지지 않은 인덱스는 마지막 인덱스까지 지속된 것으로 처리
 */

class Solution {
	
	public int[] solution(int[] prices) {
		
		int[] answer = new int[prices.length];
		Deque<Integer> stack = new ArrayDeque<>();
		
		for (int i = 0; i < prices.length; i++) {
			// 스택에 있는 인덱스 중 가격이 더 높았던 시점들은 이 시점(i)에서 가격 하락
			while (!stack.isEmpty() && prices[stack.peekLast()] > prices[i]) {
				int index = stack.pollLast();
				answer[index] = i - index;
			}
			
			// 현재 인덱스를 스택에 저장
			stack.addLast(i);
		}
		
		// 끝까지 가격이 떨어지지 않은 경우 처리
		while (!stack.isEmpty()) {
			int index = stack.poll();
			answer[index] = prices.length - index - 1;
		}
		
		return answer;
	}
	
}
