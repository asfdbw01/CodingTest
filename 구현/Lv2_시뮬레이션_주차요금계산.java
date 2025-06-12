/*
 * 문제 요약
 *     - 차량의 입차(IN)/출차(OUT) 기록을 바탕으로 차량별 누적 주차 시간을 계산하고, 요금표에 따라 요금을 청구
 * 입력
 *     - fees: [기본 시간, 기본 요금, 단위 시간, 단위 요금]
 *     - records: ["시각 차량번호 내역"] 형식의 문자열 배열
 * 출력
 *     - 차량 번호 오름차순으로 정렬된 주차 요금 배열
 * 핵심 포인트
 *     - 출차 기록이 없는 차량은 23:59에 출차된 것으로 간주
 *     - 누적 주차 시간에서 기본 시간을 초과한 경우 단위 시간마다 단위 요금 부과 (올림 처리)
 *     - TreeMap 사용으로 차량 번호 정렬 자동 처리
 */

class Solution {
	
	int fullTime = 23 * 60 + 59;  // 하루의 마지막 시간 23:59을 분으로 환산
	
	public int[] solution(int[] fees, String[] records) {
		Map<String, Integer> chargeMap = new TreeMap<>();  // 차량 번호 기준 정렬

		// 입/출차 누적 시간 계산
		for (String record : records) {
			String[] parts = record.split(" ");
			String car = parts[1], type = parts[2];
			int min = fullTime - timeToMin(parts[0]);  // fullTime - 현재 시간으로 변환

			// IN은 양수 누적, OUT은 음수 누적 → 최종적으로 (OUT - IN) 시간 계산됨
			chargeMap.merge(car, type.equals("IN") ? min : -min, Integer::sum);
		}

		// 누적 시간 기준 요금 계산
		return chargeMap.values()
				.stream()
				.mapToInt(min -> calculateFee(min, fees))
				.toArray();
	}

	// 요금 계산 로직
	public int calculateFee(int min, int[] fees) {
	    if (min <= fees[0]) return fees[1];
	    return fees[1] + (int) Math.ceil((min - fees[0]) / (double) fees[2]) * fees[3];
	}

	// "HH:MM" 형식 → 분 단위로 변환
	public int timeToMin(String time) {
		String[] parts = time.split(":");
		int hour = Integer.parseInt(parts[0]);
		int min = Integer.parseInt(parts[1]);
		return hour * 60 + min;
	}
}
