/*
 * 주어진 명령어(10초 전/후 이동)를 순서대로 처리한 후,
 * 최종 재생 위치를 "mm:ss" 형식으로 반환하는 문제
 * 
 * video_len: 동영상 전체 길이 ("mm:ss")
 * pos: 현재 재생 위치 ("mm:ss")
 * op_start: 오프닝 시작 시간 ("mm:ss")
 * op_end: 오프닝 끝 시간 ("mm:ss")
 * commands: 명령어 목록 ("prev" 또는 "next"로 구성된 배열)
 * 
 * "prev" → 현재 위치에서 10초 전으로 이동
 * 		단, 0초보다 이전은 0초로 고정
 * "next" → 현재 위치에서 10초 후로 이동
 * 		단, 비디오 길이를 넘으면 끝으로 고정
 * 오프닝 스킵 기능 (자동 적용)
 * 		이동 후 재생 위치가 오프닝 범위(op_start ≤ pos ≤ op_end)에 속하면,
 * 			→ 자동으로 op_end 위치로 점프
 */

class Solution {
	int video_len;
	int pos;
	int op_start;
	int op_end;
	
	public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
		String answer = "";
		this.video_len = toSec(video_len);
		this.pos = toSec(pos);
		this.op_start = toSec(op_start);
		this.op_end = toSec(op_end);

		if (this.pos >= this.op_start && this.pos < this.op_end) this.pos = this.op_end;
		for (String cmd : commands) {			
			if (cmd.equals("next")) 	this.pos = (this.pos + 10 < this.video_len)? this.pos + 10 : this.video_len;
			else if (cmd.equals("prev")) 	this.pos = (this.pos - 10 > 0)? this.pos - 10 : 0;
			
			if (this.pos >= this.op_start && this.pos < this.op_end) this.pos = this.op_end;
		}
		
		// String.format() 함수로 출력값 포맷 맞추기
		answer = String.format("%02d:%02d", this.pos / 60, this.pos % 60);

		return answer;
	}
	
	// String 입력값을 초(Sec)로 변환
	public int toSec(String time) {
		int min = Integer.parseInt(time.split(":")[0]);
		int sec = Integer.parseInt(time.split(":")[1]);
		return min * 60 + sec;
	}
}
