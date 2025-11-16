/*  
 * 문제 요약
 *     - 이중 우선순위 큐는 삽입(I)과 삭제(D 1: 최대값 삭제, D -1: 최소값 삭제)를 반복하는 자료구조이다.
 *     - operations 배열에 주어진 명령을 순서대로 처리한 뒤, 큐에 남아 있는 값 중 최대값과 최소값을 반환한다.
 *     - 큐가 비어있다면 [0, 0]을 반환한다.
 *
 * 입력  
 *     - String[] operations : "I 숫자" 또는 "D 1", "D -1" 형태의 연산 명령 (최대 1,000,000개)
 *
 * 출력  
 *     - int[] : 큐가 비어 있으면 [0, 0], 비어있지 않으면 [최댓값, 최솟값]
 *
 * 핵심 포인트  
 *     - 삽입된 숫자는 중복 가능하므로, 각 숫자의 개수를 따로 관리해야 한다.
 *     - 삭제 연산은 최댓값 또는 최솟값 중 하나만 제거한다.
 *     - TreeMap을 사용하면 정렬된 상태를 유지하며 최대/최솟값 접근이 O(log n)으로 가능하다.
 */

class Solution {

	public int[] solution(String[] operations) {
		TreeMap<Integer, Integer> numMap = new TreeMap<>();
		
		for (String oper : operations) {
			String[] parts = oper.split(" ");
			
			if (parts[0].equals("I")) {
				// 삽입 연산: 해당 숫자의 등장 횟수를 1 증가
				numMap.merge(Integer.parseInt(parts[1]), 1, Integer::sum);
			}
			else if (!numMap.isEmpty()) {
				// 삭제 연산: 1이면 최댓값 삭제, -1이면 최솟값 삭제
				int key = parts[1].equals("1") ? numMap.lastKey() : numMap.firstKey();
				
				// 해당 숫자의 등장 횟수를 1 감소시키고 0이 되면 제거
				numMap.merge(key, -1, Integer::sum);
				if (numMap.get(key) == 0) numMap.remove(key);
			}
		}
	
		// 결과 반환: 비어 있으면 [0, 0], 그렇지 않으면 [최댓값, 최솟값]
		return numMap.isEmpty() ? new int[] {0, 0} : new int[] {numMap.lastKey(), numMap.firstKey()};
	}
}
