/*  
 * 문제 요약  
 *     - 크레인 인형뽑기 게임에서 주어진 moves 배열에 따라 인형을 뽑고, 바구니에 같은 인형이 연속으로 쌓이면 터뜨릴 때, 사라진 인형의 총 개수를 구하는 문제  
 *
 * 입력  
 *     - int[][] board: N x N 격자 형태의 게임판 (0은 빈칸, 1~100은 인형 번호)  
 *     - int[] moves: 크레인을 작동시킨 열 정보 (1-based index)  
 *
 * 출력  
 *     - 터져서 사라진 인형의 총 개수 (int)  
 *
 * 핵심 포인트  
 *     - 같은 인형이 연속해서 바구니에 들어오면 사라짐 (2개 단위)  
 *     - 각 열의 최상단 인형을 빠르게 찾기 위한 사전 처리 필요  
 *     - 바구니는 스택 구조로 구현
 */

class Solution {
	
	public int solution(int[][] board, int[] moves) {
		int n = board.length, m = board[0].length;

		// 각 열에서 가장 위에 있는 인형의 행 번호를 미리 구해놓음
		int[] topRow = IntStream.range(0, m)
					.map(i -> IntStream.range(0, n)
							   .filter(j -> board[j][i] != 0)
							   .findFirst()
							   .orElse(n))
					.toArray();
		
		Deque<Integer> stack = new ArrayDeque<>();  // 바구니 역할
		int pop = 0;  // 터진 인형 수

		for (int move : moves) {
			int col = move - 1;  // 1-based index → 0-based

			// 해당 열에 더 이상 인형이 없으면 건너뜀
			if (topRow[col] >= n) continue;

			// 인형이 바구니 맨 위와 같으면 제거
			if (!stack.isEmpty() && stack.peekLast() == board[topRow[col]][col]) {
				stack.pollLast();
				pop += 2;
			}
			// 그렇지 않으면 바구니에 추가
			else {
				stack.addLast(board[topRow[col]][col]);
			}

			// 해당 열의 다음 인형을 가리키도록 갱신
			topRow[col]++;
		}
		
		return pop;
	}
	
}
