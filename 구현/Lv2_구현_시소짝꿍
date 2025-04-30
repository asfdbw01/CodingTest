// 문제 요약:
// 시소 좌석은 거리 2, 3, 4m에 존재
// 두 사람이 마주 앉아 (몸무게 × 거리)가 같으면 짝꿍 성립
// 허용 비율: 1:1, 2:3, 1:2, 3:4, 3:2, 2:1, 4:3, 4:2
// weights 배열이 주어졌을 때, 가능한 짝꿍 쌍의 총 개수를 구함


import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;

        // 1. 몸무게 등장 횟수 저장
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int w : weights) {
            countMap.put(w, countMap.getOrDefault(w, 0) + 1);
        }

        // 2. 모든 쌍 조합 확인 (중복 제거: w1 <= w2)
        List<Integer> weightList = new ArrayList<>(countMap.keySet());
        Collections.sort(weightList);  // 정렬하면 w1 ≤ w2 보장 쉬움

        for (int i = 0; i < weightList.size(); i++) {
            int w1 = weightList.get(i);
            long c1 = countMap.get(w1);

            for (int j = i; j < weightList.size(); j++) {
                int w2 = weightList.get(j);
                long c2 = countMap.get(w2);

                if (isValidPair(w1, w2)) {
                    if (w1 == w2) {
                        // 동일 몸무게일 경우 조합
                        answer += c1 * (c1 - 1) / 2;
                    } else {
                        answer += c1 * c2;
                    }
                }
            }
        }

        return answer;
    }

    // 3. 시소 짝꿍 조건: (몸무게 * 거리) 같으면 OK
    private boolean isValidPair(int w1, int w2) {
        int[][] ratios = {
            {1, 1}, {2, 3}, {1, 2}, {3, 4},
            {3, 2}, {2, 1}, {4, 3}, {4, 2}
        };

        for (int[] ratio : ratios) {
            if (w1 * ratio[0] == w2 * ratio[1]) {
                return true;
            }
        }
        return false;
    }
}
