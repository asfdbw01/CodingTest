/*
 * 📄 보석 쇼핑 (투 포인터 + 해시맵)
 *
 * 문제 요약
 * ----------
 * - 진열된 보석(gems)에서 모든 종류의 보석을 1개 이상 포함하는
 *   가장 짧은 연속 구간을 찾는다.
 * - 가장 짧은 구간이 여러 개라면 시작 진열대 번호가 가장 작은 구간을 선택한다.
 *
 * 입력
 * -----
 * - gems: 진열된 보석 이름 배열 (1 ≤ gems.length ≤ 100,000)
 *   (각 보석 이름은 길이 1~10의 대문자 문자열)
 *
 * 출력
 * -----
 * - [시작 번호, 끝 번호] (1-based index)
 *
 * 조건
 * -----
 * - 모든 보석 종류를 하나 이상 포함해야 함
 * - 최소 길이의 구간을 찾아야 함
 * - 구간의 시작 번호가 가장 작은 것을 우선함
 *
 * 예시
 * -----
 * gems = ["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"]
 * → 출력: [3, 7]
 *
 * 알고리즘
 * -------
 * ✔ 모든 보석의 종류 수를 센다
 * ✔ 투 포인터 + 해시맵으로 현재 구간의 보석을 카운트
 * ✔ 모든 보석을 포함하는 구간을 찾으면 왼쪽 포인터를 당겨 최소 길이 갱신
 */

import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int type = countGemTypes(gems);
        return findMinRange(gems, type);
    }

    private int countGemTypes(String[] gems) {
        return (int) Arrays.stream(gems).distinct().count();
    }

    private int[] findMinRange(String[] gems, int type) {
        int left = 0, right = 0;
        int minLength = gems.length;
        int[] loc = {1, gems.length};
        
        Map<String, Integer> gemMap = new HashMap<>();
        
        while (right < gems.length) {
            addGem(gemMap, gems[right]);
            
            while (gemMap.size() == type) {
                if ((right - left) < minLength) {
                    minLength = right - left;
                    loc[0] = left + 1;
                    loc[1] = right + 1;
                }
                
                removeGem(gemMap, gems[left]);
                left++;
            }
            
            right++;
        }
        
        return loc;
    }

    private void addGem(Map<String, Integer> map, String gem) {
        map.put(gem, map.getOrDefault(gem, 0) + 1);
    }

    private void removeGem(Map<String, Integer> map, String gem) {
        map.put(gem, map.get(gem) - 1);
        if (map.get(gem) == 0) {
            map.remove(gem);
        }
    }
}

