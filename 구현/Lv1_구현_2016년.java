/*  
 * 문제 요약
 *     - 2016년 a월 b일의 요일을 구함
 *     - 2016년 1월 1일은 금요일
 *     - 윤년이므로 2월은 29일까지 존재
 *
 * 입력  
 *     - a: 월 (1 ≤ a ≤ 12)
 *     - b: 일 (해당 월의 유효한 일자)
 *
 * 출력  
 *     - 요일 (SUN, MON, TUE, WED, THU, FRI, SAT)
 *
 * 핵심 포인트  
 *     - 2016년 1월 1일부터 a월 b일까지의 날짜 수를 계산
 *     - 요일 배열을 이용해 인덱스 계산
 *     - 라벨문(Outter)로 이중 반복문 탈출
 */

class Solution {
	
	String[] week = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
	int[] month = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public String solution(int a, int b) {
		int index = 5; // 2016-01-01은 금요일(week[5])
		
		Outter: for (int m = 1; m <= 12; m++) {
			for (int j = 1; j <= month[m - 1]; j++) {
				if (m == a && j == b) break Outter; // 목표 날짜 도달 시 탈출
				index++;
			}
		}
		
		return week[index % week.length];
	}
	
}
