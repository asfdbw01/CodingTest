/*
 * 문제 요약
 *     - 소문자 알파벳으로 만들 수 있는 모든 문자열을 길이순 + 사전순으로 정렬한 주문서가 존재함
 *     - 일부 주문들은 삭제되었으며, 삭제를 반영한 주문서에서 n번째 문자열을 찾아야 함
 *
 * 입력
 *     - n: 삭제된 주문을 제외하고 n번째로 등장하는 주문 (1 ≤ n ≤ 10^15)
 *     - bans: 삭제된 주문 목록 (1 ≤ 길이 ≤ 300,000, 각 문자열 길이 1~11, 중복 없음)
 *
 * 출력
 *     - 삭제된 주문을 제외한 n번째 주문 문자열
 *
 * 핵심 포인트
 *     - 알파벳 문자열은 26진법 + 1-based로 해석 가능
 *     - 모든 가능한 문자열 수는 26^1 + 26^2 + ... + 26^11 (약 4 * 10^15) → 직접 생성 불가
 *     - bans를 숫자로 바꿔 정렬 후, 삭제된 주문 중 n 이하의 개수만큼 n을 보정
 *     - 보정된 순서를 이용해 문자열 길이와 위치를 찾아 26진법 역변환으로 문자열 구성
 */

class Solution {
    public String solution(long n, String[] bans) {

        // 삭제된 문자열을 사전 순서 기준 숫자로 변환
        long[] banNums = new long[bans.length];

        for (int i = 0; i < bans.length; i++) {
            long charNum = 0;
            for (char c : bans[i].toCharArray()) {
                charNum = charNum * 26 + (c - 'a' + 1);
            }
            banNums[i] = charNum;
        }

        Arrays.sort(banNums); // 사전순 정렬 (숫자 기준)

        // 삭제된 번호 중 n 이하인 개수만큼 n을 보정
        for (long num : banNums) {
            if (num <= n) n++;
            else break;
        }

        // 사전 순서 n번째에 해당하는 문자열 길이 찾기
        int len = 1;
        long sum = 0, pow = 26;
        while (n > sum + pow) {
            sum += pow;
            pow *= 26;
            len++;
        }

        n = n - sum - 1; // 해당 길이 내에서 0-based index

        // 1-based 26진법 → 문자열 변환
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append((char) ('a' + n % 26));
            n /= 26;
        }

        return sb.reverse().toString();
    }
}
