/*
문제 요약:

- 하루 24시간, 각 시간대의 이용자 수가 주어진다 (players[24])
- 서버 1대당 m명의 이용자를 감당할 수 있다
- 서버는 한번 증설되면 k시간 동안 유지된다
- 특정 시각에 필요한 서버 수 = ceil(players[i] / m)
- 기존에 운영 중인 서버로 커버되지 않으면, 부족한 만큼 서버를 증설해야 하며
  이때의 증설 횟수 = 부족한 서버 수
- 이때의 증설 횟수를 모두 더한 값이 최소가 되도록 구하라

핵심 포인트:
- 현재 시간에 필요한 서버 수와 운영 중인 서버 수를 비교하여 부족한 만큼 증설
- 증설된 서버는 k시간 동안 유지되므로, 만료 시점을 따로 관리해야 함
- 총 증설 횟수의 합을 return
*/

import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        //서버 반납시각,줄어드는 시간
        HashMap <Integer,Integer> map = new HashMap<>();
        
        int limite =m;
        for(int i=0;i<players.length;i++){
            if(map.containsKey(i))limite-=map.get(i);
            if(players[i]>= limite){
                int addServer = ((players[i]-limite)/m)+1;
                limite += addServer*m;
                map.put(i+k,addServer*m);//수정 필요할수도
                answer += addServer;
            }
        }
        
        return answer;
    }
}
