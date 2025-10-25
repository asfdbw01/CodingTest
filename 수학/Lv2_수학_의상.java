/*  
 * 문제 요약  
 *     - 의상 종류별로 하루에 하나만 입을 수 있으며, 최소 한 개는 착용해야 함
 *     - 모든 의상 조합의 수를 구하되, 아무것도 입지 않는 경우는 제외
 *
 * 입력  
 *     - clothes: 각 의상의 [이름, 종류]를 담은 2차원 배열 (최대 30개)
 *
 * 출력  
 *     - 서로 다른 옷 조합의 수 (아무것도 안 입는 경우 제외)
 *
 * 핵심 포인트  
 *     - 각 의상 종류마다 (입는 경우 + 입지 않는 경우) → (n + 1)
 *     - 모든 종류에 대해 곱한 뒤, 아무것도 입지 않는 경우 1을 빼야 함
 *     - 예: (2가지 얼굴) * (1가지 상의) * (1가지 하의) * (1가지 겉옷) = (2+1)*(1+1)*(1+1)*(1+1) = 전체 조합 수
 */

class Solution {
	
	public int solution(String[][] clothes) {
		Map<String, Integer> clothMap = new HashMap<>();
		
		// 각 의상 종류별 개수 누적
		for (String[] cloth : clothes) {
			clothMap.merge(cloth[1], 1, Integer::sum);
		}
		
		int answer = 1;
		
		// 각 종류마다 (선택 + 미선택) 경우의 수 곱셈
		for (int kinds : clothMap.values()) {
			answer *= kinds + 1;
		}
		
		// 아무 것도 입지 않는 경우 제외
		return answer - 1;
	}
	
}
