/*
동전들의 초기 상태와 목표 상태가 주어졌을 때, 
초기 상태에서 최소 몇 번의 동전을 뒤집어야 목표 상태가 되는지 알아봅시다.

동전을 뒤집기 위해서는 같은 줄에 있는 모든 동전을 뒤집어야 합니다

직사각형 모양의 공간에 놓인 동전들의 초기 상태를 나타내는 2차원 정수 배열 beginning, 
목표 상태를 나타내는 target이 주어졌을 때,
초기 상태에서 목표 상태로 만들기 위해 필요한 동전 뒤집기 횟수의 최솟값을
return 하는 solution 함수를 완성하세요. 
단, 목표 상태를 만들지 못하는 경우에는 -1을 return 합니다.

제한사항
1 ≤ beginning의 길이 = target의 길이 ≤ 10
1 ≤ beginning[i]의 길이 = target[i]의 길이 ≤ 10
beginning[i][j]와 target[i][j]는 i + 1행 j + 1열의 동전의 상태를 나타내며, 0 또는 1의 값으로 주어집니다.
0은 동전의 앞면을, 1은 동전의 뒷면을 의미합니다.
*/


class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int answer = 0;
        int min = 0;
        int[][] flip = makeFlip(beginning, target);
        //flip을 전부 0으로 만드는 뒤집기 조합 찾기
        
        answer = Math.min(tryScenario(flip, 0), tryScenario(flip, 1));
        return (answer==1_000_000_000)?-1:answer;
    }
    
    private int tryScenario(int[][] flip, int r0) {
        int len = flip.length;
        int wid = flip[0].length;

        int[] R = new int[len];
        int[] C = new int[wid];

        R[0] = r0;
        // 첫 행으로 열 결정: C[j] = D[0][j] ^ R[0]
        for (int j = 0; j < wid; j++) C[j] = flip[0][j] ^ R[0];
        // 첫 열로 각 행 결정: R[i] = D[i][0] ^ C[0]
        for (int i = 1; i < len; i++) R[i] = flip[i][0] ^ C[0];

        // 전 칸 검증: R[i] ^ C[j] == D[i][j] ?
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < wid; j++) {
                if ( (R[i] ^ C[j]) != flip[i][j] ) return 1_000_000_000; // 불가능
            }
        }

        // 비용 = 뒤집은 행 수 + 열 수
        int cost = 0;
        for (int x : R) cost += x;
        for (int y : C) cost += y;
        return cost;
    }
    
    
    private int[][] makeFlip(int[][] beginning, int[][] target){
        int len = beginning.length;
        int wid = beginning[0].length;
        int[][] flip = new int[len][wid];
        for(int i=0;i<len;i++){
            for(int j=0;j<wid;j++){
                flip[i][j] = beginning[i][j] ^ target[i][j];
            }
        }
        return flip;
    }
}