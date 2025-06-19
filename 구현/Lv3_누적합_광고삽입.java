/*  
 * 문제 요약  
 *     - 동영상 재생 시간과 광고 시간, 시청자들의 재생 로그가 주어질 때  
 *       누적 시청 시간이 가장 길어지는 광고 시작 시각을 구하는 문제  
 *
 * 입력  
 *     - play_time: 영상 전체 길이 (HH:MM:SS)  
 *     - adv_time: 광고 길이 (HH:MM:SS)  
 *     - logs: 시청자들의 시청 구간 (시작~끝 HH:MM:SS-HH:MM:SS)  
 *
 * 출력  
 *     - 누적 시청 시간이 최대가 되는 광고 시작 시각 (HH:MM:SS)  
 *
 * 핵심 포인트  
 *     - 1초 단위로 시청자 수를 마킹한 후 누적합 2회  
 *     - 슬라이딩 윈도우 방식으로 광고 시간 내 최대 누적 시청시간 구간 탐색  
 */

class Solution {
	
	public String solution(String play_time, String adv_time, String[] logs) {
		if (play_time.equals(adv_time)) return secToTime(0);  // 전체 영상에 광고 삽입 가능하면 바로 리턴
		
		int playTime = timeToSec(play_time);  // 전체 영상 길이를 초로 변환
		int advTime = timeToSec(adv_time);    // 광고 시간을 초로 변환
		
		int len = playTime + 1;
		long[] timeLine = new long[len];  // 1초 단위 재생 누적 정보 배열
		
		// 각 로그에 대해 시작 시점 +1, 종료 시점 -1 (Imos 방식 마킹)
		for (String log : logs) {
			String[] parts = log.split("-");
			int start = timeToSec(parts[0]);
			int end = timeToSec(parts[1]);
			
			timeLine[start] += 1;
			timeLine[end] -= 1;
		}
		
		// 1차 누적합: 시간별 시청자 수 계산
		for (int i = 1; i < len; i++) {
			timeLine[i] += timeLine[i - 1];
		}
		
		// 2차 누적합: 0초부터 i초까지 누적 시청 시간 계산
		for (int i = 1; i < len; i++) {
			timeLine[i] += timeLine[i - 1];
		}
		
		// 초기 광고 구간(0~advTime)의 누적 시청 시간 계산
		long max = timeLine[advTime];
		int startTime = 0;
		
		// 슬라이딩 윈도우로 최대 누적 시청 시간 구간 탐색
		for (int i = 1; i < len - advTime; i++) {
			long cur = timeLine[advTime + i - 1] - timeLine[i - 1];
			if (cur > max) {
				max = cur;
				startTime = i;
			}
		}
		
		return secToTime(startTime);  // 최적 광고 시작 시각을 HH:MM:SS로 반환
	}
	
	// 초 단위를 HH:MM:SS 문자열로 변환
	public String secToTime(int sec) {
		int hour = sec / (60 * 60);
		sec %= 60 * 60;
		int min = sec / 60;
		sec %= 60;
		
		return String.format("%02d:%02d:%02d", hour, min, sec);
	}
	
	// HH:MM:SS 형식 문자열을 초 단위 정수로 변환
	public int timeToSec(String time) {
		String[] parts = time.split(":");
		int hour = Integer.parseInt(parts[0]);
		int min = Integer.parseInt(parts[1]);
		int sec = Integer.parseInt(parts[2]);
		
		return hour * 60 * 60 + min * 60 + sec;
	}
	
}
