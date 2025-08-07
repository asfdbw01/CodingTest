/*
 * 문제 요약
 *     - 주어진 문자열을 LZW 방식으로 압축하여 색인 번호 리스트로 변환
 *
 * 입력  
 *     - msg: 영문 대문자로만 이루어진 문자열 (1 ≤ 길이 ≤ 1000)
 *
 * 출력  
 *     - 압축 후의 색인 번호 배열 (int[])
 *
 * 핵심 포인트  
 *     - 사전을 Map<String, Integer>로 구성
 *     - 현재 위치부터 가장 긴 문자열을 찾고, 다음 문자와 결합해 사전에 등록
 *     - putIfAbsent 활용으로 사전 등록과 존재 확인을 동시에 처리
 */

class Solution {
    
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> dict = new HashMap<>();
        
        // 1. A~Z까지 사전 초기화 (1~26)
        for (char c = 'A'; c <= 'Z'; c++) {
            dict.put(Character.toString(c), dict.size() + 1);
        }
        
        StringBuilder sb = new StringBuilder();
        
        // 2. 입력 문자열을 순회하며 압축
        for (int i = 0; i < msg.length(); i++) {
            sb.setLength(0);
            sb.append(msg.charAt(i));
            
            // 3. 현재 위치에서 가장 긴 문자열 찾기
            while (i + 1 < msg.length() &&
                   dict.putIfAbsent(sb.toString() + msg.charAt(i + 1), dict.size() + 1) != null) {
                sb.append(msg.charAt(++i));
            }
            
            // 4. 찾은 문자열의 사전 번호 저장
            answer.add(dict.get(sb.toString()));
        }
        
        // 5. 결과 반환
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
}
