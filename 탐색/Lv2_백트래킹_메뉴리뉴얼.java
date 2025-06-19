/*
[문제 요약]
- 각 손님의 주문 내역(문자열 배열 orders)을 분석해, 가장 자주 함께 주문된 메뉴 조합을 찾아 코스요리로 구성한다.
- 각 코스는 최소 2가지 이상의 단품메뉴로 구성되어야 하며,
  최소 2명 이상의 손님에게 주문된 조합만 코스요리 후보로 인정된다.
- 스카피가 고려 중인 코스 요리의 단품 개수 리스트는 course 배열로 주어진다.

[요구 사항]
- 각 order 문자열은 알파벳 대문자로 이루어짐 (중복 없음)
- 각 course 길이에 대해 조합을 만들어, 가장 많이 주문된 조합(2번 이상 등장)만 반환
- 최종 결과는 사전 순 정렬되어야 함

[입력]
- orders: 손님들의 주문 목록 (String[]), 각 원소는 알파벳 문자열
- course: 고려할 코스 메뉴 길이 리스트 (int[])

[출력]
- 코스요리로 구성될 문자열 목록 (String[]), 사전 순 오름차순 정렬

[예시]
orders = ["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"]
course = [2,3,4]

→ 결과: ["AC", "ACDE", "BCFG", "CDE"]
*/
import java.util.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        List<String> courseList = new LinkedList<>();
        Map<String, Integer> setMap = new HashMap<>();
        
        sortOrders(orders); // 사전순 정렬
        createSetMap(setMap, orders, course); // 모든 조합 카운팅
        createCourseList(courseList, setMap, course); // 가장 많이 나온 조합 선택
        
        courseList.sort(String::compareTo); // 사전순 정렬
        return courseList.toArray(new String[0]);
    }

    // 각 주문을 정렬 (조합 생성 시 순서 보장용)
    private void sortOrders(String[] orders) {
        for (int i = 0; i < orders.length; i++) {
            char[] chars = orders[i].toCharArray();
            Arrays.sort(chars);
            orders[i] = new String(chars);
        }
    }

    // 모든 주문에서 course 길이별 조합을 만들고 map에 카운팅
    private void createSetMap(Map<String, Integer> setMap, String[] orders, int[] course) {
        for (String order : orders) {
            char[] chars = order.toCharArray();
            for (int len : course) {
                if (len > chars.length) continue;
                makeCombinations(chars, new StringBuilder(), 0, 0, len, setMap);
            }
        }
    }

    // 조합 생성 재귀 함수
    private void makeCombinations(char[] chars, StringBuilder sb, int idx, int depth, int targetLen, 
                                  Map<String, Integer> map) {
        if (depth == targetLen) {
            String combo = sb.toString();
            map.put(combo, map.getOrDefault(combo, 0) + 1);
            return;
        }

        for (int i = idx; i < chars.length; i++) {
            sb.append(chars[i]);
            makeCombinations(chars, sb, i + 1, depth + 1, targetLen, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // 코스 후보 중에서 가장 많이 등장한 조합만 리스트에 추가
    private void createCourseList(List<String> courseList, Map<String, Integer> map, int[] course) {
        for (int len : course) {
            int maxCount = 2; // 최소 2번 이상 등장
            List<String> candidates = new ArrayList<>();

            for (String key : map.keySet()) {
                if (key.length() != len) continue;

                int count = map.get(key);
                if (count > maxCount) {
                    candidates.clear();
                    candidates.add(key);
                    maxCount = count;
                } else if (count == maxCount) {
                    candidates.add(key);
                }
            }

            courseList.addAll(candidates);
        }
    }
}
