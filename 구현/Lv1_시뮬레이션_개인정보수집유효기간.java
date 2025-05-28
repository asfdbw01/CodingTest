/*
 * 문제 요약
 *     - 개인정보 수집 일자와 약관 종류가 주어짐
 *     - 각 약관은 유효기간(개월 단위)이 정해져 있고, 수집 일자에 유효기간을 더한 날의 다음 날부터 파기 대상
 *     - 오늘 날짜(today) 기준으로 파기 대상이 된 개인정보의 번호(i+1)를 구해 오름차순으로 반환
 * 
 * 입력
 *     - today: 오늘 날짜 (형식: "YYYY.MM.DD")
 *     - terms: 약관 종류와 유효기간 목록 (형식: "A 6", 최대 20개)
 *     - privacies: 개인정보 수집 일자와 약관 종류 목록 (형식: "2021.05.02 A", 최대 100개)
 * 
 * 출력
 *     - 파기 대상이 된 개인정보의 번호 목록 (1-based index, 오름차순 정렬)
 * 
 * 핵심 포인트
 *     - 날짜를 모두 "일 수"로 환산해서 비교하면 단순한 정수 비교만으로 파기 여부 판단 가능
 *     - 월을 28일로 고정하여 계산
 *     - 약관별 유효기간을 Map에 저장해 빠르게 조회
 *     - 수집일 + 유효기간 * 28 - 1 까지가 보관 가능일, 그 이후는 파기 대상
 */

class Solution {

    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Map<Character, Integer> termMap = new HashMap<>();

        // 약관 종류별 유효기간을 맵에 저장
        for (String term : terms) {
            String[] split = term.split(" ");
            termMap.put(split[0].charAt(0), Integer.parseInt(split[1]));
        }

        int todayVal = toDate(today); // 오늘 날짜를 일 수로 변환

        for (int i = 0; i < privacies.length; i++) {
            String[] split = privacies[i].split(" ");
            int expire = toDate(split[0]) + termMap.get(split[1].charAt(0)) * 28 - 1;

            // 오늘 날짜가 만료일을 초과하면 파기 대상
            if (todayVal > expire) answer.add(i + 1);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    // 날짜를 총 일 수로 환산 (기준: 2000년 1월 1일 → 0일)
    private int toDate(String date) {
        String[] split = date.split("\\.");
        return (Integer.parseInt(split[0]) - 2000) * 12 * 28
             + (Integer.parseInt(split[1]) - 1) * 28
             + Integer.parseInt(split[2]);
    }
}
