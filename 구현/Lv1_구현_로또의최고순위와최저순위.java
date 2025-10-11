/*  
 * 문제 요약  
 *     - 로또 번호 6개 중 일부가 0으로 가려져 있을 때, 당첨 번호와 비교하여 가능한 최고 순위와 최저 순위를 반환  
 *
 * 입력  
 *     - lottos: 민우가 구매한 로또 번호 (길이 6, 0은 가려진 번호)  
 *     - win_nums: 당첨 번호 (길이 6, 중복 없음)  
 *
 * 출력  
 *     - [최고 순위, 최저 순위] 형태의 정수 배열  
 *
 * 핵심 포인트  
 *     - 0은 어떤 번호든 될 수 있으므로 최대로 많이 맞췄을 때를 고려해 최고 순위 계산  
 *     - 0을 제외하고 실제로 맞춘 개수만으로 최저 순위 계산  
 *     - 맞춘 개수가 2보다 작으면 순위는 6등으로 고정되므로 예외 처리 필요
 */

class Solution {
	
	public int[] solution(int[] lottos, int[] win_nums) {
		// win_nums와 lottos의 일치 개수 계산
		int match = (int) Arrays.stream(win_nums)
				.filter(num -> Arrays.stream(lottos).anyMatch(lotto -> lotto == num))
				.count();
		
		// 가려진 숫자(0)의 개수 계산
		int zeros = (int) Arrays.stream(lottos)
				.filter(lotto -> lotto == 0)
				.count();
		
		// 최고 순위: match + zeros / 최저 순위: match만 고려
		// 순위는 맞춘 개수가 2 미만일 경우 6등으로 고정되므로 Math.min으로 보정
		return new int[] {Math.min(6, 6 - (match + zeros) + 1), Math.min(6, 6 - match + 1)};
	}
	
}
