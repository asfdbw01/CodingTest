/*
카카오톡에서는 이모티콘 할인 행사를 하는데, 목표는 다음과 같습니다.

1. 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것.
2. 이모티콘 판매액을 최대한 늘리는 것.

1번 목표가 우선이며, 2번 목표가 그 다음입니다.

이모티콘 할인 행사는 다음과 같은 방식으로 진행됩니다.

n명의 카카오톡 사용자들에게 이모티콘 m개를 할인하여 판매합니다.
이모티콘마다 할인율은 다를 수 있으며, 할인율은 10%, 20%, 30%, 40% 중 하나로 설정됩니다.
카카오톡 사용자들은 다음과 같은 기준을 따라 이모티콘을 사거나, 이모티콘 플러스 서비스에 가입합니다.

각 사용자들은 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매합니다.
각 사용자들은 자신의 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이 된다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.

제한사항
1 ≤ users의 길이 = n ≤ 100
users의 원소는 [비율, 가격]의 형태입니다.
users[i]는 i+1번 고객의 구매 기준을 의미합니다.
비율% 이상의 할인이 있는 이모티콘을 모두 구매한다는 의미입니다.
1 ≤ 비율 ≤ 40
가격이상의 돈을 이모티콘 구매에 사용한다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입한다는 의미입니다.
100 ≤ 가격 ≤ 1,000,000
가격은 100의 배수입니다.
1 ≤ emoticons의 길이 = m ≤ 7
emoticons[i]는 i+1번 이모티콘의 정가를 의미합니다.
100 ≤ emoticons의 원소 ≤ 1,000,000
emoticons의 원소는 100의 배수입니다.
*/

import java.util.*;

class Solution {
    int[] rates = {10, 20, 30, 40};
    int bestSubs, bestRevenue;
    int[][] users;
    int[] emoticons;

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        int[] choice = new int[emoticons.length]; 
        dfs(0, choice);
        return new int[]{bestSubs, bestRevenue};
    }

    private void dfs(int idx, int[] choice) {
        if (idx == emoticons.length) {
            evaluate(choice);
            return;
        }
        for (int r : rates) {
            choice[idx] = r;
            dfs(idx + 1, choice);
        }
    }

    
    private void evaluate(int[] choice) {
        int subs = 0, revenue = 0;
        for (int[] u : users) {
            int minRate = u[0];
            int limit   = u[1];
            int sum = 0;
            for (int i = 0; i < emoticons.length; i++) {
                int r = choice[i];
                if (r >= minRate) {
                    sum += emoticons[i] * (100 - r) / 100; 
                }
            }
            if (sum >= limit) subs++;
            else revenue += sum;
        }
        if (subs > bestSubs || (subs == bestSubs && revenue > bestRevenue)) {
            bestSubs = subs;
            bestRevenue = revenue;
        }
    }
}