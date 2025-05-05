/*
 * 문제 요약
 * 		- 붕대 감기 생존 시뮬레이션 게임 캐릭터는 **"붕대 감기"**라는 기술을 사용하여 체력을 회복할 수 있습니다. 이 기술은 일정
 * 		- 시간 동안 매초 체력을 회복하며, 정해진 시간(t초) 동안 중단 없이 회복에 성공하면 추가 회복량(y)도 획득합니다.
 * 
 * 		- 하지만 기술 도중 몬스터의 공격을 받으면 회복이 중단되고, 회복은 다음 초부터 다시 시작됩니다. 공격 순간에는 회복이 일어나지 않으며,
 * 		- 체력이 0 이하로 떨어지면 캐릭터는 사망합니다.
 * 
 * 입력:
 * 		- bandage: 붕대 감기 기술 정보 [시전 시간 t, 초당 회복량 x, 추가 회복량 y]
 * 		- health: 캐릭터의 최대 체력 (초기 체력)
 * 		- attacks: [공격 시간, 피해량] 배열 (공격 시간은 오름차순 정렬됨)
 * 
 * 출력:
 * 		- 모든 공격이 끝난 후 남은 체력 반환
 * 		- 도중에 체력이 0 이하가 되면 -1 반환
 */

// 핵심 로직
// 		- 매초 상태를 확인하지 않고, 공격 이벤트 시점 사이의 회복량만 수식으로 계산하여 처리

// 스스로에게 주는 제약조건
// 		1. 매 초 회복과 공격을 확인하지 않을 것 (이벤트가 발생 시에만 연산)
// 		2. 괄호는 연산 우선순위를 명시적으로 표현
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;
        int healingCycle = bandage[0];	// 체력 회복 주기
        int healPerSec = bandage[1];	// 초당 회복량
        int bonusHeal = bandage[2];		// 체력 회복 주기를 채웠을 때 추가 회복량
        
        for (int i = 0; i < attacks.length; i++) {
        	if (i != 0) {	// 첫 공격 전까지의 회복은 의미 없기에 생략
        		int healingSec = attacks[i][0] - attacks[i - 1][0] - 1;	// 방해가 없었던 순수 회복 시간
        		answer += healingSec * healPerSec						// 순수 회복 시간 동안의 초당 회복량
        				+ (healingSec / healingCycle) * bonusHeal;		// 순수 회복 시간 동안 달성한 회복 주기(몫)에 따른 추가 회복량
        		answer = Math.min(answer, health);						// 회복 연산 이후 최대 체력을 넘겼다면 최대 체력으로 되돌림
        	}
        	answer -= attacks[i][1];		// 체력 - 이번 피해
        	if (answer <= 0) return -1;		// 체력이 0 이하가 되었다면 -1을 반환하고 종료
        }
        
        return answer;
    }
}
