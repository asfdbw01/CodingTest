/*
 * 문제 요약
 *     - 각 음식의 수가 주어졌을 때, 두 선수가 공정하게 먹을 수 있도록 음식을 양쪽에서 대칭되게 배치
 *     - 양쪽 선수는 각각 왼쪽과 오른쪽에서 음식들을 같은 순서, 같은 양으로 먹고
 *       중앙의 물(0)을 먼저 먹는 쪽이 승리
 * 
 * 입력
 *     - int[] food: 0번 인덱스를 제외하고 i번 음식이 food[i]개 준비됨
 *                   food[0]은 물(항상 1개)
 * 
 * 출력
 *     - 공정한 배치를 나타내는 문자열 (앞쪽 + "0" + 반대쪽)
 * 
 * 핵심 포인트
 *     - 각 음식은 공정하게 나누기 위해 2로 나눈 개수만큼 좌우에 배치
 *     - 왼쪽은 순서대로 append, 오른쪽은 reverse를 통해 mirror
 *     - 가운데는 항상 물(0) 하나 배치
 */

class Solution {
    
    // 음식 배열을 기반으로 공정한 음식 배치 문자열을 생성
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        
        // i번 음식은 food[i] / 2만큼 왼쪽에 배치
        for (int i = 1; i < food.length; i++) {
//			sb.repeat(i + '0', food[i] / 2);	// jdk 21 이상
            for (int j = 0; j < food[i] / 2; j++) {
                sb.append(i);  // 문자열로 직접 append
            }
        }
        
        // 최종 배치는: 왼쪽 음식 + 물(0) + 오른쪽 음식(역순)
        return sb.toString() + "0" + sb.reverse().toString();
    }

}
