// 문제 요약:
// 야근 피로도를 최소화하기 위해 N시간 동안 가장 작업량이 많은 일부터 1씩 줄인다.
// 피로도는 각 작업량의 제곱을 모두 더한 값이다.
// 작업을 줄일 때는 1시간에 작업량 1만큼 줄일 수 있다.

// 입력:
// - works: 각 일의 작업량 배열 (길이 1 이상 20,000 이하, 원소는 자연수 ≤ 50000)
// - n: 남은 야근 시간 (1 ≤ n ≤ 1,000,000)

// 출력:
// - 모든 작업을 N시간 안에 처리한 뒤 남은 작업량의 제곱 합(야근 피로도)을 리턴

// 예시:
// works = [4, 3, 3], n = 4 → [2, 2, 2]로 줄인 뒤, 피로도 = 2² + 2² + 2² = 12
// works = [2, 1, 2], n = 1 → [1, 1, 2]로 줄이면 피로도 = 1² + 1² + 2² = 6
// works = [1, 1], n = 3 → 모두 제거되어 피로도 = 0
import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        //피로도 = 시작시점 + 남은 작업량 * 남은 작업량
        //그리디
        PriorityQueue<Integer> pq = makePq(works);
        while (n > 0) {
            int max = pq.poll();
            if (max == 0) break; // 더 이상 줄일 수 없으면 중단
            pq.add(max - 1); // 1만 줄이고 다시 넣음
            n--;
        }

        
        while (!pq.isEmpty()) {
            int w = pq.poll();
            answer += (long) w * w; // 피로도 = 제곱 합
        }
        return answer;
    }
    
    private PriorityQueue<Integer> makePq(int[] works){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i: works){
            pq.add(i);
        }
        return pq;
    }
}
