/*  
 * 문제 요약  
 *     - 노란색 격자(yellow)는 가운데에 배치되고, 갈색 격자(brown)는 테두리를 형성하는 카펫의 전체 크기를 구하는 문제  
 *
 * 입력  
 *     - brown (갈색 격자 수): 8 이상 5000 이하  
 *     - yellow (노란색 격자 수): 1 이상 2,000,000 이하  
 *
 * 출력  
 *     - 전체 카펫의 가로, 세로 크기를 담은 배열 (가로 ≥ 세로)  
 *
 * 핵심 포인트  
 *     - 전체 카펫의 면적 = brown + yellow  
 *     - (가로 - 2) * (세로 - 2) = yellow  
 *     - brown = 전체 테두리 격자 수 = (가로 + 세로) * 2 - 4  
 *     - 위 관계를 기반으로 가로, 세로를 추론함
 */

class Solution {
	
	public int[] solution(int brown, int yellow) {
		
		// (가로 + 세로) = brown / 2 + 2 → 내부 너비 + 높이 = brown / 2 - 2
		int width = brown / 2 - 2;
		int height = 0;
		
		// 내부 영역이 yellow가 될 때까지 반복
		while (--width * ++height != yellow);
		
		// 테두리(2줄)를 더해 실제 가로/세로 반환
		return new int[] {width + 2, height + 2};
	}
	
}
