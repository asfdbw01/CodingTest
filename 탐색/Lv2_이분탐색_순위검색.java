/*  
 * 문제 요약  
 *     - 4개의 지원 조건(개발언어, 직군, 경력, 소울푸드)과 점수로 구성된 info 리스트가 주어질 때  
 *       특정 조건(query)을 만족하고 점수가 주어진 기준 이상인 지원자 수를 계산하는 문제  
 *
 * 입력  
 *     - info: String[] (지원자 정보, "언어 직군 경력 음식 점수" 형식)  
 *     - query: String[] ("언어 and 직군 and 경력 and 음식 점수" 형식의 검색 조건)  
 *
 * 출력  
 *     - 각 query 조건에 맞는 지원자 수를 int[] 형태로 반환  
 *
 * 핵심 포인트  
 *     - info 하나당 가능한 2^4 = 16개의 조건 조합을 모두 만들어 Map에 저장  
 *     - 각 조합별로 지원자의 점수를 리스트로 저장하고, 정렬 후 이분 탐색으로 빠르게 조회  
 *     - Collections.binarySearch는 중복에서 오류 가능성이 있어, 직접 lower bound 구현
 */

class Solution {
	
	public int[] solution(String[] info, String[] query) {
		Map<String, List<Integer>> typeMap = new HashMap<>();
		
		// info를 기반으로 가능한 모든 조건 조합을 Map에 저장
		for (String s : info) {
			String[] type = s.split(" ");
			for (int i = 0; i < (1 << 4); i++) {  // 0 ~ 15 (16가지 조합)
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < 4; j++) {
					sb.append((i & (1 << j)) != 0 ? type[j] : "-").append(" ");
				}
				String key = sb.toString().trim();
				typeMap.computeIfAbsent(key, k -> new ArrayList<>())
				       .add(Integer.parseInt(type[4]));
			}
		}
		
		// 각 조건 조합의 점수 리스트를 오름차순 정렬
		for (List<Integer> list : typeMap.values()) {
			list.sort(Comparator.naturalOrder());
		}
		
		int[] answer = new int[query.length];
		
		// 각 query를 처리
		for (int i = 0; i < query.length; i++) {
			// "and"를 제거하고 공백 기준 split
			String[] parts = query[i].replace(" and ", " ").split(" ");
			String key = String.join(" ", Arrays.copyOfRange(parts, 0, 4));  // 조건 key
			int score = Integer.parseInt(parts[4]);  // 점수 기준
			
			// 해당 조건 조합의 점수 리스트를 가져옴 (없으면 빈 리스트)
			List<Integer> list = typeMap.getOrDefault(key, new ArrayList<>());
			
			// score 이상인 첫 인덱스를 찾는 lower bound 이분 탐색
			answer[i] = list.size() - binarySearch(score, list);
		}
		
		return answer;
	}
	
	// score 이상인 첫 번째 인덱스를 반환하는 lower bound 이진 탐색
	public int binarySearch(int score, List<Integer> scoreList) {
		if (scoreList.isEmpty() || score > scoreList.get(scoreList.size() - 1)) return scoreList.size();
		
		int left = 0, right = scoreList.size() - 1;
		int answer = right;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			int midScore = scoreList.get(mid);
			
			if (midScore < score) {
				left = mid + 1;
			} else {
				right = mid - 1;
				answer = mid;
			}
		}
		
		return answer;
	}
}
