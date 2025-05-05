/**
 * 문제 요약:
 * 		- 세 가지 곡괭이(diamond, iron, stone)가 있고, 각각 특정 수만큼 사용할 수 있다.
 * 		- 광물들을 5개씩 한 묶음으로 작업하며, 곡괭이 종류에 따라 피로도가 다르게 누적된다.
 * 		- 피로도를 최소화하기 위해 피로도가 큰 묶음부터 좋은 곡괭이를 써야 한다.
 * 
 * 입력:
 * 		- picks: 곡괭이의 남은 개수 [다이아, 철, 돌]
 * 		- minerals: 채굴할 광물들의 배열 (각 값은 "diamond", "iron", "stone")
 * 
 * 출력:
 * 		- 최소 누적 피로도 (int)
 * 
 * 핵심 포인트 (전략):
 * 		✅ 1. **광물들을 5개 단위로 나누고**, 각 묶음의 상대적 피로도를 계산한다.
 * 		✅ 2. **피로도가 높은 묶음부터** 작업 순서를 정하고,
 * 		✅ 3. **좋은 곡괭이부터 순차적으로** 배정하여, 전체 누적 피로도를 최소화한다.
 */

class Solution {

    public int solution(int[] picks, String[] minerals) {
        int totalStress = 0;
        List<Integer> stressArr = new ArrayList<>();
        
        // Step 1. 사용할 수 있는 최대 곡괭이 수만큼만 광물 작업
        // (각 곡괭이로 5개씩만 작업 가능하므로 총 사용 가능 광물 수는 (picks 총합 * 5))
        for (int i = 0, stressSum = 0; i < minerals.length; i++) {
            if (i >= (picks[0] + picks[1] + picks[2]) * 5) break;

            // 각 광물에 대해 상대적인 피로도 점수 부여 (다이아:100, 철:10, 돌:1)
            stressSum += switch (minerals[i]) {
                case "diamond" -> 100;
                case "iron" -> 10;
                case "stone" -> 1;
                default -> 0;
            };

            // 5개마다 하나의 묶음 처리 (또는 마지막까지 남은 경우)
            if ((i + 1) % 5 == 0 || i + 1 == minerals.length) {
                stressArr.add(stressSum);  // 각 묶음의 총 피로도 점수 저장
                stressSum = 0;
            }
        }

        // Step 2. 피로도가 높은 묶음부터 좋은 곡괭이를 써야 하므로 내림차순 정렬
        Collections.sort(stressArr, Collections.reverseOrder());

        // Step 3. 각 묶음마다 적절한 곡괭이 선택하여 실제 피로도 계산
        Outer: for (int i = 0, kind = 0; i < stressArr.size(); i++) {
            // 더 이상 해당 곡괭이가 없으면 다음 종류로 넘어감
            while (picks[kind] <= 0) {
                if (kind + 1 < picks.length) kind++;
                else break Outer; // 모든 곡괭이를 다 썼으면 종료
            }

            // 저장된 점수(100/10/1 기준)를 다시 개수로 환산
            int dia = stressArr.get(i) / 100;
            int iron = (stressArr.get(i) % 100) / 10;
            int stone = stressArr.get(i) % 10;

            // 피로도 계산 (곡괭이 종류별 피로도 공식 적용)
            totalStress += switch (kind) {
                case 0 -> dia * 1 + iron * 1 + stone * 1;       // 다이아 곡괭이
                case 1 -> dia * 5 + iron * 1 + stone * 1;       // 철 곡괭이
                case 2 -> dia * 25 + iron * 5 + stone * 1;      // 돌 곡괭이
                default -> 0;
            };

            // 해당 곡괭이 1개 사용
            picks[kind]--;
        }

        return totalStress;
    }
}
