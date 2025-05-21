/*
 * 문제 요약
 *     - 비내림차순으로 정렬된 수열에서, 연속된 부분 수열의 합이 k가 되는 구간을 찾아야 한다.
 *     - 조건: 부분 수열의 길이가 가장 짧고, 길이가 같다면 시작 인덱스가 앞선 수열을 선택
 * 
 * 입력
 *     - int[] sequence: 비내림차순 수열 (길이 5 이상 1,000,000 이하)
 *     - int k: 목표 부분 수열의 합 (5 이상 10억 이하)
 * 
 * 출력
 *     - int[] answer: 조건을 만족하는 부분 수열의 시작 인덱스와 끝 인덱스를 담은 배열
 * 
 * 핵심 포인트
 *     - 수열이 정렬되어 있기 때문에 투 포인터를 적용한 선형 시간 탐색이 가능함
 *     - 연속 구간의 합을 조절하며 합이 k인 구간을 찾고, 그 중 가장 짧고 앞선 구간을 저장
 *     - sequence[left] == k인 경우는 길이 1로 최단이므로 조기 종료 가능
 */

class Solution {

    // 연속된 부분 수열의 합이 k인 가장 짧고 앞선 구간의 인덱스를 반환
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2]; // 정답 구간의 시작/끝 인덱스 저장

        int left = 0, right = 0, sum = sequence[0]; // 투 포인터 및 현재 구간 합 초기화
        int minLen = Integer.MAX_VALUE; // 최소 구간 길이 기록 변수

        while (right < sequence.length) {
            if (sum < k) {
                // 합이 k보다 작으면 오른쪽 포인터를 한 칸 확장
                right++;
                if (right < sequence.length) sum += sequence[right];
            } else {
                if (sum == k) {
                    // 합이 k일 때 구간 길이가 더 짧으면 정답 갱신
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        answer[0] = left;
                        answer[1] = right;
                    }
                    // 최단 길이(1)이고 정렬상 가장 앞선 구간이면 즉시 반환
                    if (sequence[left] == k) return answer;
                }
                // 현재 구간의 왼쪽 끝을 제거하고 한 칸 오른쪽으로 이동
                sum -= sequence[left++];
            }
        }

        return answer;
    }
}
