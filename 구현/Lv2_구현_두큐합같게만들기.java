/*
문제 요약 - 두 큐 합 같게 만들기

- 길이가 같은 두 개의 큐(queue1, queue2)가 주어진다.
- 한 번의 작업은 한 큐에서 pop(맨 앞 원소 제거) 후 다른 큐에 insert(맨 뒤에 추가)하는 것.
- 이런 작업을 반복하여 두 큐의 원소 합을 같게 만들어야 한다.
- 가능한 최소 작업 횟수를 구하라. 만약 절대 같게 만들 수 없다면 -1을 반환하라.

제약 조건:
- 1 ≤ queue1.length == queue2.length ≤ 300,000
- 1 ≤ 원소 값 ≤ 1,000,000,000
- 합이 홀수이면 절대 같게 만들 수 없음
- 연산 횟수 제한은 총 원소 수 * 3 미만 (무한 루프 방지)

핵심 아이디어:
- queue1 + queue2를 하나의 배열로 결합해 원형 큐처럼 사용
- 두 큐의 현재 범위를 투 포인터로 관리 (startIndex, endIndex)
- 두 큐의 합을 비교해 더 큰 쪽에서 pop하고, 반대쪽에 insert
- 작업 횟수를 카운트하여 정답 반환
*/

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        int[] combine = combineArray(queue1,queue2);
        long sum = sumArray(combine);
        //합이 홀수면 같게 할수 없다
        if(sum%2==1)return -1;
        int[] q1Range = new int[]{0,queue1.length-1};
        int[] q2Range = new int[]{queue1.length,combine.length-1};
        long q1Sum = sumArray(queue1);
        long q2Sum = sumArray(queue2);
        
        return simulateEqualize(combine, q1Range, q2Range, q1Sum, q2Sum);
    }
    
    private int[]combineArray (int[] queue1,int[] queue2){
        int[] combine = new int[queue1.length+queue2.length];
        int idx = 0;
        for(int i:queue1){
            combine[idx]=i;
            idx++;
        }
        for(int i:queue2){
            combine[idx]=i;
            idx++;
        }
        return combine;
    }
    
    private long sumArray(int[] combine){
        long sum = 0;
        for(long i:combine){
            sum += i;
        }
        return sum;
    }
    
    private int popLeft(int[] range, int[] combine) {
        int val = combine[range[0] % combine.length];
        range[0]++;
        return val;
    }

    private void pushRight(int[] range) {
        range[1]++;
    }

    private int simulateEqualize(int[] combine, int[] q1Range, int[] q2Range, long q1Sum, long q2Sum) {
            int ops = 0;
            int maxOps = combine.length * 3;

            while (ops <= maxOps) {
                if (q1Sum == q2Sum) return ops;

                if (q1Sum > q2Sum) {
                    int val = popLeft(q1Range, combine);
                    pushRight(q2Range);
                    q1Sum -= val;
                    q2Sum += val;
                } else {
                    int val = popLeft(q2Range, combine);
                    pushRight(q1Range);
                    q2Sum -= val;
                    q1Sum += val;
                }
                ops++;
            }

            return -1;
        }
    
}
