/*
 * 문제 요약
 *     - 각 행마다 [코드 번호, 제조일, 최대 수량, 현재 수량]이 들어 있는 2차원 배열 data가 주어진다.
 *     - 주어진 필드(ext)가 기준값(val_ext)보다 작은 데이터만 필터링한 뒤,
 *     - 정렬 기준 필드(sort_by)를 기준으로 오름차순 정렬하여 결과를 반환한다.
 *
 * 입력
 *     - int[][] data : 제품 데이터 (1 ≤ data.length ≤ 500)
 *         [code(int), date(int), maximum(int), remain(int)]
 *     - String ext : 필터링 기준 필드명 ("code", "date", "maximum", "remain")
 *     - int val_ext : ext 필드 기준 필터링 임계값
 *     - String sort_by : 정렬 기준 필드명 ("code", "date", "maximum", "remain")
 *
 * 출력
 *     - 조건을 만족한 데이터들을 sort_by 기준 오름차순으로 정렬한 int[][] 2차원 배열
 *
 * 핵심 포인트
 *     - 필드명을 숫자 인덱스로 바꾸기 위한 해싱(Map) 기반 접근
 *     - stream을 통한 필터링 및 정렬 처리
 *     - Comparator.comparingInt를 이용한 안전한 정렬
 */

class Solution {
	
  // 각 필드명을 배열 인덱스로 매핑하는 상수 Map (불변)
	private static final Map<String, Integer> FIELDINDEXMAP = Map.ofEntries(
		Map.entry("code", 0),
		Map.entry("date", 1),
		Map.entry("maximum", 2),
		Map.entry("remain", 3)
	);
	
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
    	// 필드명 → 배열 인덱스로 변환
    	int extIdx = FIELDINDEXMAP.get(ext);
    	int sortIdx = FIELDINDEXMAP.get(sort_by);
    	
        // 조건 필터링 후 정렬하여 배열로 반환
        int[][] answer = Arrays.stream(data)
        	.filter(item -> item[extIdx] < val_ext) // 필터 조건 적용
        	.sorted(Comparator.comparingInt(item -> item[sortIdx])) // 오름차순 정렬
        	.toArray(int[][]::new); // 배열로 반환
        
        return answer;
    }
}
