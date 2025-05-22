/*
 * 문제 요약
 *   - 지갑에 지폐를 넣기 위해 지폐를 접어야 할 때, 최소 몇 번 접어야 하는지를 구하는 문제.
 *   - 지폐는 긴 변을 반으로 접되, 접을 때마다 소수점 이하는 버림.
 *   - 지폐는 회전 가능하며, 지갑에 들어갈 수 있게 되는 순간 접기를 멈춤.
 *
 * 입력
 *   - wallet: 지갑의 가로, 세로 크기를 담은 길이 2의 정수 배열 (10 ≤ 각 요소 ≤ 100)
 *   - bill: 지폐의 가로, 세로 크기를 담은 길이 2의 정수 배열 (10 ≤ 각 요소 ≤ 2000)
 *
 * 출력
 *   - 지폐를 지갑에 넣기 위해 접어야 하는 최소 횟수 (더 이상 접을 수 없으면 -1)
 *
 * 핵심 포인트
 *   - 지폐를 접을 때마다 긴 변을 반으로 나누고 내림 처리
 *   - 접은 후에는 회전 여부(90도)도 고려해서 지갑에 들어가는지 확인
 *   - 지폐의 변 중 하나가 0이 되면 지갑에 절대 들어갈 수 없으므로 -1 반환
 */

class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;

        // 내림차순 정렬: 긴 변이 앞으로 오도록
        wallet = sortDesc(wallet);
        bill = sortDesc(bill);

        while (true) {
            // 지폐의 변이 0이 되면 접을 수 없음
            if (bill[0] == 0 || bill[1] == 0) return -1;

            // 현재 방향 또는 90도 회전했을 때 지갑에 들어가는지 확인
            if (canFit(bill, wallet)) return answer;

            // 긴 변을 반으로 접기 (내림 처리)
            bill[0] /= 2;

            // 다시 정렬 (긴 변이 앞으로 오도록)
            bill = sortDesc(bill);

            answer++;
        }
    }

    // 배열을 내림차순 정렬하여 긴 변이 앞으로 오도록 함
    private int[] sortDesc(int[] arr) {
        return (arr[1] > arr[0]) ? new int[] {arr[1], arr[0]} : arr;
    }

    // bill이 wallet에 들어갈 수 있는지 확인
    private boolean canFit(int[] bill, int[] wallet) {
        return bill[0] <= wallet[0] && bill[1] <= wallet[1];
    }
}
