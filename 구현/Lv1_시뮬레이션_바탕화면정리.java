/*
 * 문제 요약
 *     - 바탕화면 격자에서 '#' 파일들이 존재하는 영역을 한 번의 드래그로 모두 선택해야 한다.
 *     - 가장 적은 드래그 이동거리 = 가장 작은 직사각형으로 파일들을 모두 포함하는 경우.
 *
 * 입력
 *     - String[] wallpaper: 각 칸이 '.' 또는 '#'으로 구성된 바탕화면 상태
 *
 * 출력
 *     - int[]: 드래그 시작점 (lux, luy) 과 끝점 (rdx, rdy) 순으로 담긴 배열
 *
 * 핵심 포인트
 *     - 파일이 존재하는 가장 상단/좌측/하단/우측 좌표를 찾는다.
 *     - 끝점은 격자점 기준이므로 maxX + 1, maxY + 1 을 적용해야 한다.
 */

class Solution {
	
	public int[] solution(String[] wallpaper) {
		
		int xLen = wallpaper.length;
		int yLen = wallpaper[0].length();
		
		int minX = xLen;
		int minY = yLen;
		int maxX = 0;
		int maxY = 0;
		
		// 모든 칸을 탐색하며 '#' 위치의 최소/최대 행, 열을 기록
		for (int x = 0; x < xLen; x++) {
			for (int y = 0; y < yLen; y++) {
				if (wallpaper[x].charAt(y) == '#') {
					minX = Math.min(minX, x);
					minY = Math.min(minY, y);
					maxX = Math.max(maxX, x);
					maxY = Math.max(maxY, y);
				}
			}
		}
		
		// 드래그는 격자점 기준이므로 최대 좌표 + 1 반환
		return new int[] {minX, minY, maxX + 1, maxY + 1};
	}
	
}
