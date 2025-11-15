/*  
 * 문제 요약  
 *     - 각 작업의 진도(progresses)와 개발 속도(speeds)가 주어질 때  
 *       앞에 있는 작업이 완료되지 않으면 뒤의 작업도 배포되지 않음  
 *       한 번의 배포마다 몇 개의 작업이 함께 배포되는지 구하는 문제  
 *
 * 입력  
 *     - progresses: int[] (작업들의 초기 진도, 100 미만)  
 *     - speeds: int[] (작업들의 하루 개발 속도, 100 이하)  
 *
 * 출력  
 *     - int[] (각 배포마다 배포되는 작업 수)  
 *
 * 핵심 포인트  
 *     - day를 누적적으로 증가시키며 작업 완료 여부 판단  
 *     - 앞 작업이 끝나야 뒷 작업이 배포될 수 있으므로 이전 배포 시점 기준으로 묶음  
 *     - 리스트를 활용해 동적 결과 저장 후 배열로 변환  
 */

class Solution {
	
	public int[] solution(int[] progresses, int[] speeds) {
		
		List<Integer> answer = new ArrayList<>();
		
		for (int i = 0, day = 0; i < progresses.length; i++) {
			// 현재 작업이 아직 완료되지 않았다면
			if (progresses[i] + speeds[i] * day < 100) {
				// 완료될 때까지 day 증가
				while (progresses[i] + speeds[i] * day < 100) day++;
				answer.add(1); // 새로운 배포 시작
			} else {
				// 이전 배포에 함께 포함되는 작업이므로 증가
				int lastIndex = answer.size() - 1;
				answer.set(lastIndex, answer.get(lastIndex) + 1);
			}
		}
		
		return answer.stream().mapToInt(Integer::intValue).toArray();
	}
	
}
