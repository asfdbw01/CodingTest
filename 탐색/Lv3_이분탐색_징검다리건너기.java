// 문제 설명
// 디딤돌(stones)은 밟을 때마다 숫자가 1씩 줄어들며, 0이 되면 더 이상 밟을 수 없다.
// 연속으로 k개의 디딤돌이 0 이하가 되면 더 이상 징검다리를 건널 수 없다.
// 최대 몇 명까지 징검다리를 건널 수 있는지 구하는 문제.

// 입력
// stones: 디딤돌 상태를 나타내는 정수 배열 (1 ≤ stones.length ≤ 200,000, 1 ≤ stones[i] ≤ 200,000,000)
// k: 한 번에 건너뛸 수 있는 최대 디딤돌 수 (1 ≤ k ≤ stones.length)

// 출력
// 최대 몇 명이 징검다리를 건널 수 있는지 정수로 반환

// 예시
// stones = [2, 4, 5, 3, 2, 1, 4, 2, 5, 1], k = 3
// → 출력: 3 (4번째 친구부터는 k개 이상 연속으로 건널 수 없기 때문)

// 핵심 아이디어
// - 이분 탐색으로 가능한 인원 수를 탐색
// - 매 중간값(mid)마다 해당 인원이 건널 수 있는지를 확인 (canCross)
// - 연속으로 mid명 이상 못 밟는 디딤돌이 k개 이상이면 불가능


class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int left = 1;
        int right = 200_000_000;
        while(left<=right){
            int mid = (left+right)/2;
            if(canCross(stones, k,mid)){
                left = mid+1;
                answer = mid;
            }
            else right = mid-1;
        }
        
        return answer;
    }
    
    private boolean canCross(int[] stones,int k,int people) {
        int skip = 0;
        for(int s:stones){
            if(s-people<0)skip++;
            else skip=0;
            if (skip >= k) return false;
        }
        return true;
    }
}
