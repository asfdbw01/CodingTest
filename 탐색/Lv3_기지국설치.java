/*  
 * 문제 요약
 *     - 아파트가 일렬로 존재하고, 일부에만 기지국이 설치되어 있음
 *     - 기존 기지국은 범위가 넓지만, 새로 설치할 5G 기지국은 범위가 좁음
 *     - 모든 아파트에 전파가 도달하도록 최소한의 기지국을 더 설치해야 함
 *
 * 입력  
 *     - n: 아파트 수 (1 이상 2억 이하)
 *     - stations: 기존 기지국 설치 위치 (오름차순 정렬, 최대 1만 개)
 *     - w: 기지국 전파 도달 거리 (1 이상 10,000 이하)
 *
 * 출력  
 *     - 추가로 설치해야 할 최소 기지국 개수
 *
 * 핵심 포인트  
 *     - 기존 기지국이 도달하지 못하는 구간을 찾아 필요한 개수만큼 설치
 *     - 커버되지 않는 구간의 길이를 구한 뒤, 기지국 하나가 커버할 수 있는 길이로 나눠 올림 처리
 */

class Solution {
	
	double range;
	
	public int solution(int n, int[] stations, int w) {
		this.range = w * 2 + 1;  // 하나의 기지국이 커버할 수 있는 아파트 수
		
		// 첫 번째 기지국 전에 커버되지 않은 구간이 있다면, 필요한 기지국 수를 추가
		int count = (stations[0] - w - 1 > 0) ? (int) Math.ceil((stations[0] - w - 1) / range) : 0;
		
		// 각 기지국 사이의 커버되지 않는 구간에 대해 필요한 기지국 수 계산
		for (int i = 1; i < stations.length; i++) {
			int noSignal = stations[i] - stations[i - 1] - 1 - w * 2;
			if (noSignal > 0) {
				count += (int) Math.ceil(noSignal / range);
			}
		}
		
		// 마지막 기지국 이후 커버되지 않은 구간 처리
		if (stations[stations.length - 1] + w < n) {
			count += (int) Math.ceil((n - (stations[stations.length - 1] + w)) / range);
		}
		
		return count;
	}
	
}
