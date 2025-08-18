// 문제 요약 (프로그래머스: 110 옮기기)
// - 0/1 문자열 x에서 부분문자열 "110"을 골라 아무 위치로 옮길 수 있다.
// - 가능한 변형 중 사전순으로 가장 앞서는 문자열을 구해라(여러 x에 대해 각각).
//
// 제한
// - s.length ≤ 1e6, 각 문자열 길이 ≥ 1, 총 길이 합 ≤ 1e6  → O(총길이) 필요
//
// 핵심 아이디어
// 1) x에서 등장하는 "110"을 전부 '추출'한다. (스택식 스캔으로 O(n)에 개수만 카운트)
//    - 한 글자씩 StringBuilder(스택)에 push
//    - 마지막 3글자가 '1','1','0'이면 pop 3번하고 cnt110++
// 2) 추출 후 남은 문자열 R에서 '0'의 마지막 위치를 찾는다(lastIndexOf('0')).
//    - 그 위치 바로 뒤(= 사전순 최소가 되는 자리)에 "110"을 cnt110개 연속 삽입
//    - 만약 R에 '0'이 하나도 없으면 문자열 맨 앞에 삽입
//  → 위 배치가 사전순 최소가 됨(그리디 정리 결과)
//
// 예시
// - "1110" -> R="1", cnt110=1, '0' 없음 → 앞에 "110" 붙여 "1101"
// - "100111100" -> R에서 마지막 '0' 뒤에 "110"*k 삽입 → "100110110"
//
// 복잡도
// - 각 문자열당 O(len) 스캔 + O(len) 삽입 위치 계산 → 총 O(모든 문자열 길이 합)
// - 추가 메모리: O(len)
//
// 구현 팁
// - StringBuilder로 스택 구현, setLength로 3글자 제거
// - 삽입은 insert(pos, "110".repeat(cnt))
// - 다건 입력이므로 I/O와 객체 생성(특히 new String) 최소화


class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
       for(int i=0;i<s.length;i++){
           answer[i] = move110Fast(s[i]);
       }
        return answer;
    }
    
    private String move110Fast(String s) {
        StringBuilder st = new StringBuilder();
        int cnt = 0;
        for (char ch : s.toCharArray()) {
            st.append(ch);
            int L = st.length();
            if (L >= 3 &&
                st.charAt(L-3)=='1' && st.charAt(L-2)=='1' && st.charAt(L-1)=='0') {
                st.setLength(L-3); // "110" 제거
                cnt++;
            }
        }
        // 남은 문자열에서 '0'의 마지막 위치 뒤에 "110"붙이기
        int insertPos = st.lastIndexOf("0") + 1; // 없으면 0
        String rep = "110".repeat(cnt);
        st.insert(insertPos, rep);
        return st.toString();
    }


}