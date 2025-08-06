/*
문제 요약: 문자열 압축

- 동일한 문자열이 연속으로 반복되는 경우 "개수 + 문자열"로 압축할 수 있다. (예: "aaab" → "3ab")
- 문자열을 1개 이상 단위로 앞에서부터 잘라서 압축했을 때, 가장 짧은 길이를 구하라.
- 마지막 남은 문자열 조각은 압축하지 않고 그대로 붙인다.

예시:
- "aabbaccc" → 단위 1일 때: "2a2ba3c" → 길이 7
- "ababcdcdababcdcd" → 단위 8일 때: "2ababcdcd" → 길이 9

제한사항:
- 문자열 길이: 1 이상 1,000 이하
- 소문자 알파벳만 포함
- 압축 방식: 앞에서부터 정해진 길이만큼 나누어야 하며, 남는 조각은 그대로 붙인다.
*/
class Solution {
    public int solution(String s) {
        int answer = s.length();
        for (int i = 1; i <= s.length(); i++) {
            answer = Math.min(answer, compression(s, i));
        }
        return answer;
    }
    
    private int compression(String s, int size) {
        StringBuilder sb = new StringBuilder();
        String prev = s.substring(0, size);
        int count = 1;

        for (int i = size; i <= s.length(); i += size) {
            String curr;
            if (i + size <= s.length()) {
                curr = s.substring(i, i + size);
            } else {
                curr = s.substring(i);
            }

            if (prev.equals(curr)) {
                count++;
            } else {
                if (count > 1) sb.append(count);
                sb.append(prev);
                prev = curr;
                count = 1;
            }
        }

        // 마지막 덩어리 붙이기
        if (count > 1) sb.append(count);
        sb.append(prev);

        return sb.length();
    }

}
