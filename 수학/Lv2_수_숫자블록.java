/*
 * 📌 문제 요약
 * - 1부터 10,000,000까지의 숫자가 적힌 블록을 다음 규칙에 따라 도로에 설치
 * - 각 숫자 n은 n의 배수 위치(2n, 3n, ...)에 블록을 설치함
 * - 이미 블록이 설치된 위치엔 더 큰 숫자가 덮어씀
 *
 * ✅ 목표
 * - 주어진 구간 [begin, end]에 최종적으로 깔린 블록 번호를 배열로 반환
 *
 * 🧠 핵심 규칙
 * - 위치 i에 설치될 블록은 i의 약수 중 **자기 자신 제외**하고 **가장 큰 약수**
 * - 단, 블록 번호는 10,000,000 이하만 가능
 *
 * ⚠️ 예외 처리
 * - i가 1일 경우: 설치할 수 있는 약수가 없으므로 0
 *
 * 🎯 최적화 팁
 * - i의 약수를 √i까지만 탐색하여 쌍으로 계산
 * - 큰 약수를 먼저 고려하여 빠르게 리턴
 */


class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end-begin+1)];
        //약수중 가장 큰 수 들어옴(자신제외)
        int idx=0;
        for(int i=0;i<answer.length;i++){
            long num = begin +i;
            answer[i] = getMaxDivisor(num);
        }
        
        return answer;
    }
    
     private int getMaxDivisor(long num) {
        if (num == 1) return 0;
        int candidate = 0;
        long sqrt = (long) Math.sqrt(num);

        for (long i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                long pair = num / i;
                if (pair <= 10_000_000) return (int) pair;
                if (i <= 10_000_000) candidate = (int) i;
            }
        }

        return candidate == 0 ? 1 : candidate;
    }  
}
