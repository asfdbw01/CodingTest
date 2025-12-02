/*  
 * 문제 요약
 *     - N명이 토너먼트 형식으로 진행되는 게임에서, 번호 A와 B가 몇 번째 라운드에서 만나는지 구하는 문제
 *
 * 입력  
 *     - int n: 참가자 수 (2의 제곱수)
 *     - int a: 참가자 A의 번호
 *     - int b: 참가자 B의 번호
 *
 * 출력  
 *     - int: A와 B가 처음 만나는 라운드 번호
 *
 * 핵심 포인트  
 *     - 라운드마다 참가자 번호를 (번호 - 1) / 2로 갱신하여 같은 매치 조에 속하는지 확인
 *     - 두 번호가 동일한 매치 조가 될 때까지 반복
 *     - 0-index로 계산하기 위해 처음에 a, b에서 -1 수행
 */

class Solution {
	
	public int solution(int n, int a, int b) {
		int round = 1;
		a--;
		b--;
		
		// a와 b가 같은 조에 속하게 될 때까지 반복
		while ((a /= 2) != (b /= 2)) round++;
		
		return round;
	}
}
