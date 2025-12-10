/*  
 * 문제 요약
 *     - 공원 격자에서 로봇 강아지가 주어진 이동 명령을 순서대로 수행한다.
 *     - 이동 전, 공원을 벗어나는지 또는 장애물을 만나는지 검사하여 하나라도 위반하면 해당 명령 전체를 무시한다.
 *     - 모든 명령을 수행한 후의 최종 위치(세로, 가로)를 반환한다.
 *
 * 입력  
 *     - String[] park: 공원 지도. S(시작), O(빈칸), X(장애물)
 *     - String[] routes: 이동 명령. "방향 거리" 형태
 *
 * 출력  
 *     - int[]: 로봇 강아지의 최종 위치 [세로, 가로]
 *
 * 핵심 포인트  
 *     - 이동 과정 중 단 한 번이라도 범위 밖 또는 장애물을 만나면 '전체 명령' 무효 처리
 *     - dir 배열(dx, dy)을 이용한 방향 이동 처리
 *     - 시험 이동(nw, nh)을 통해 전체 경로가 유효할 때만 실제 위치(w, h) 갱신
 */

class Solution {
	
	String[] park;
	int maxW;
	int maxH;
	
	int[] dx = {0, 0, 1, -1};   // E, W, S, N 이동량 (세로)
	int[] dy = {1, -1, 0, 0};   // E, W, S, N 이동량 (가로)
	
	public int[] solution(String[] park, String[] routes) {
		
		this.park = park;
		this.maxW = park.length;        // 공원의 세로 길이
		this.maxH = park[0].length();   // 공원의 가로 길이
		
		int w = 0, h = 0;
		
		// 시작 위치(S) 탐색
		Outer: for (int i = 0; i < maxW; i++) {
			for (int j = 0; j < maxH; j++) {
				if (park[i].charAt(j) == 'S') {
					w = i;
					h = j;
					break Outer; // S를 찾으면 즉시 종료
				}
			}
		}
		
		// 명령 처리
		Outer: for (String route : routes) {
			String[] parts = route.split(" "); // 방향, 거리 분리
			
			int dir = setDir(parts[0]);        // 방향 인덱스 매핑
			int move = Integer.parseInt(parts[1]);
			
			int nw = w;
			int nh = h;
			
			// 이동 가능 여부를 한 칸씩 검사
			for (int i = 0; i < move; i++) {
				nw += dx[dir];
				nh += dy[dir];
				
				// 하나라도 이동 불가면 전체 명령 무시
				if (!canMove(nw, nh)) continue Outer;
			}
			
			// 모든 칸이 유효하면 실제 위치 갱신
			w = nw;
			h = nh;
		}
		
		return new int[] {w, h}; // 최종 위치 반환
	}
	
	// 방향 문자열을 인덱스로 변환
	public int setDir(String dir) {
		switch (dir) {
			case "E": return 0;
			case "W": return 1;
			case "S": return 2;
			case "N": return 3;
		}
		
		return -1;
	}
	
	// 해당 위치로 이동 가능한지 체크
	public boolean canMove(int nw, int nh) {
		return nw >= 0 && nw < maxW && nh >= 0 && nh < maxH && park[nw].charAt(nh) != 'X';
	}
	
}
