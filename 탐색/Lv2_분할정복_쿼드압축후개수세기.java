/*
 * 문제 요약
 * 		- 2차원 정사각형 배열 arr이 주어짐
 * 		- 각 칸에는 0 또는 1이 들어있고, 이 배열을 가능한 한 압축(쿼드압축)해서
 *   	  0과 1 각각이 몇 개의 블록으로 압축되었는지를 구하는 문제

 * 입력
 * 		- int[][] arr : N x N 크기 (N은 2의 거듭제곱, 최대 1024)
 * 		- arr[i][j] ∈ {0, 1}

 * 출력
 * 		- int[] answer : [압축된 0의 개수, 압축된 1의 개수]

 * 핵심 포인트
 * 		- 재귀적으로 4등분하며 압축을 진행
 * 		- 현재 블록이 모두 0이거나 모두 1일 경우 더 이상 쪼개지 않고 개수 증가
 * 		- sum을 통해 블록 내 값이 모두 같은지 판단 (0 또는 length^2이면 압축 가능)
 */

class Solution {
    public int[] solution(int[][] arr) {
        int n = arr.length;
        return compress(arr, 0, 0, n);
    }

    // 주어진 블록이 압축 가능한지 판단하여 결과 반환
    public int[] compress(int[][] arrs, int startX, int startY, int length) {
        int sum = 0;

        for (int i = startX; i < startX + length; i++) {
            for (int j = startY; j < startY + length; j++) {
                sum += arrs[i][j];
            }
        }

        // 모두 같은 값이 아니면 4등분하여 재귀 호출
        if (sum != 0 && sum != Math.pow(length, 2)) {
            return quad(arrs, startX, startY, length / 2);
        }

        // 모두 0 또는 모두 1인 경우 압축 가능
        return (sum == 0) ? new int[] {1, 0} : new int[] {0, 1};
    }

    // 블록을 4등분하여 각각 압축 수행
    public int[] quad(int[][] arr, int startX, int startY, int halflen) {
        int[] answer = {0, 0};

        for (int i = 0; i < 2; i++) {           // 행 방향 쪼개기
            for (int j = 0; j < 2; j++) {       // 열 방향 쪼개기
                int[] result = compress(arr, startX + i * halflen, startY + j * halflen, halflen);

                // 각 쿼드 결과 합산
                answer[0] += result[0];
                answer[1] += result[1];
            }
        }

        return answer;
    }
}
