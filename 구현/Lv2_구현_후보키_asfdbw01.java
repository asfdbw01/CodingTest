/*
주어진 relation 테이블에서 후보키 갯수 반환

다음 두 성질을 만족하는 것을 후보 키(Candidate Key)라고 한다.
유일성(uniqueness) : 릴레이션에 있는 모든 튜플에 대해 유일하게 식별되어야 한다.
최소성(minimality) : 유일성을 가진 키를 구성하는 속성(Attribute) 중 하나라도 제외하는 경우 유일성이 깨지는 것을 의미한다. 즉, 릴레이션의 모든 튜플을 유일하게 식별하는 데 꼭 필요한 속성들로만 구성되어야 한다.
*/

import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        int n = relation.length, m = relation[0].length;

        // 마스크를 비트수(컬럼 수) 오름차순으로 순회하면 최소성 충족이 쉬움
        List<Integer> masks = new ArrayList<>();
        for (int mask = 1; mask < (1 << m); mask++) masks.add(mask);
        masks.sort(Comparator.comparingInt(Integer::bitCount));

        List<Integer> keys = new ArrayList<>();

        for (int mask : masks) {
            // 최소성: 기존 키가 부분집합이면 패스
            boolean notMinimal = false;
            for (int k : keys) {
                if ((k & mask) == k) { notMinimal = true; break; }
            }
            if (notMinimal) continue;

            // 유일성: 선택된 컬럼들로 각 튜플의 서명 생성
            if (isUnique(relation, mask)) keys.add(mask);
        }
        return keys.size();
    }

    private boolean isUnique(String[][] rel, int mask) {
        int n = rel.length, m = rel[0].length;
        HashSet<String> seen = new HashSet<>(n * 2);
        StringBuilder sb = new StringBuilder(64);

        for (int i = 0; i < n; i++) {
            sb.setLength(0);
            for (int c = 0; c < m; c++) {
                if ((mask & (1 << c)) != 0) {
                    sb.append(rel[i][c]).append('\u0001'); // 희귀 구분자
                }
            }
            if (!seen.add(sb.toString())) return false; // 중복 → 유일성 실패
        }
        return true;
    }
}
