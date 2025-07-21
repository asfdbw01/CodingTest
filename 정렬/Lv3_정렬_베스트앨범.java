/*  
 * 문제 요약  
 *     - 장르별로 총 재생 횟수를 기준으로 내림차순 정렬 후, 각 장르에서 가장 많이 재생된 곡 2개씩을 선택해 베스트 앨범을 구성한다.
 *
 * 입력  
 *     - genres: 고유번호 i에 해당하는 곡의 장르
 *     - plays: 고유번호 i에 해당하는 곡의 재생 횟수
 *
 * 출력  
 *     - 베스트 앨범에 수록될 곡의 고유번호를 순서대로 담은 배열
 *
 * 핵심 포인트  
 *     - 장르별 총 재생 횟수가 모두 다르다는 조건을 활용해 장르 그룹핑을 PQ 내에서 정렬 기준으로만 처리 가능
 *     - 정렬 기준: 장르 총합 내림차순 → 재생 횟수 내림차순 → 고유번호 오름차순
 *     - 장르당 최대 2곡만 선택
 */

class Solution {
	
	public int[] solution(String[] genres, int[] plays) {
		Map<String, Integer> genreMap = new HashMap<>();
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt((int[] i) -> -i[0])
																.thenComparingInt(i -> -i[1])
																.thenComparingInt(i -> i[2]));
		
		// 장르별 총 재생 횟수 계산
		for (int i = 0; i < plays.length; i++) {
			genreMap.merge(genres[i], plays[i], Integer::sum);
		}
		
		// [장르 총합, 곡 재생수, 고유번호]를 PQ에 삽입
		for (int i = 0; i < plays.length; i++) {
			pq.add(new int[] {genreMap.get(genres[i]), plays[i], i});
		}
		
		List<Integer> answer = new ArrayList<>();
		while (!pq.isEmpty()) {
			int genreTotal = pq.peek()[0];
			
			// 한 장르에서 최대 2곡까지 수록
			for (int i = 0; i < 2; i++) {
				if (pq.isEmpty() || pq.peek()[0] != genreTotal) break;
				answer.add(pq.poll()[2]);
			}
			
			// 해당 장르의 나머지 곡은 모두 제거
			while (!pq.isEmpty() && pq.peek()[0] == genreTotal) pq.poll();
		}
		
		return answer.stream().mapToInt(Integer::intValue).toArray();
	}
	
}
