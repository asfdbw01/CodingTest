/*
문제 요약: 다단계 칫솔 판매 조직에서 이익을 분배하는 구조 구현

- 각 판매원(enroll)은 자신을 추천한 사람(referral)과 연결되어 트리 구조를 이룸.
- seller가 칫솔을 판매하면 (1개당 100원 이익), 이익이 10% 단위로 상위 추천인에게 분배됨.
    - 10%는 정수로 내림(1원 미만이면 분배 없이 본인이 전부 가짐)
    - 분배는 root("center")까지 재귀적으로 이어짐
- 최종적으로 각 판매원(enroll 순서)의 총 수익을 구해서 배열로 반환

입력:
- enroll: 판매원 이름 배열
- referral: 각 판매원의 추천인 (없으면 "-")
- seller: 실제 판매를 한 판매원 배열
- amount: 각 판매원이 판 칫솔 개수

출력:
- enroll 순서에 따라 각 판매원이 얻은 수익 배열을 return

조건:
- 판매원 수 최대 10,000명, 판매 기록 최대 100,000개
- 판매 수익 분배는 재귀적으로 위로 전파
- 성능 고려 필요 (Map 사용, 재귀 혹은 반복으로 분배)

예시:
판매원 young이 1200원을 벌면 → edward(120), mary(12), center(1)에게 분배되고
나머지는 각자 보유.
*/
import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        Map<String, Member> map = makeMemberMap(enroll);
        connectRecommender( map, referral,enroll);
        adjustment(map, seller,  amount );
        answer = makeAnswerArray(enroll, map);
        return answer;
    }
    
    private int[] makeAnswerArray(String[] enroll,Map<String, Member> map){
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = map.get(enroll[i]).profit;
        }
        return answer;
    }
    
    private void adjustment(Map<String, Member> map,String[] seller, int[] amount ){
        for (int i = 0; i < seller.length; i++) {
            int money = amount[i] * 100;
            map.get(seller[i]).addProfit(money);
        }
    }
    
    private Map<String, Member> makeMemberMap(String[] enroll){
        Map<String, Member> map = new HashMap<>();
        for(String name : enroll){
            map.put(name,new Member(name));
        }
        return map;
    }
    
    private void connectRecommender(Map<String, Member> map,String[] referral,String[] enroll){
        for (int i = 0; i < enroll.length; i++) {
            if (!referral[i].equals("-")) {
                map.get(enroll[i]).parent = map.get(referral[i]);
            }
        }
    }
    
    static class Member {
        private final String name;
        private Member parent;
        private int profit = 0;

        public Member(String name) {
            this.name = name;
        }

        public void setParent(Member parent) {
            this.parent = parent;
        }

        public void addProfit(int money) {
            int commission = money / 10;
            this.profit += money - commission;
            if (commission >= 1 && parent != null) {
                parent.addProfit(commission);
            }
        }

        public int getProfit() {
            return profit;
        }
    }


}
