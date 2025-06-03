/*
 * 문제 요약
 *     - 주어진 수를 이진트리로 표현하려고 한다.
 *     - 표현 방식은 해당 수를 이진수로 변환하고, 포화 이진트리 형태가 되도록 앞에 '0'을 추가한다.
 *     - 완성된 포화 이진트리에서, 루트가 0이면 해당 서브트리(왼쪽, 오른쪽)에 1이 있으면 표현 불가능하다.
 *     - 가능한지 여부를 판단하여, 각 수에 대해 1(가능), 0(불가능)을 반환한다.
 * 
 * 입력
 *     - numbers: long[], 검사할 수들의 배열 (1 ≤ numbers.length ≤ 10,000, 1 ≤ number ≤ 10^15)
 * 
 * 출력
 *     - int[], 각 수가 유효한 이진트리로 표현 가능한지 여부 (1 또는 0)
 * 
 * 핵심 포인트
 *     - 수를 이진수로 변환한 뒤, 포화 이진트리(노드 수 = 2^k - 1)가 되도록 앞에 '0'을 채운다.
 *     - 이진트리로 변환된 배열에 대해 중위순회를 기반으로 루트를 나누고 재귀적으로 유효성 검사한다.
 *     - 루트가 0인데 자식이 1이면 불가능하다.
 */

class Solution {

    // 메인 함수: 각 수가 표현 가능한 이진트리인지 판단
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            char[] cur = getPerfectBinaryTree(numbers[i]);
            answer[i] = isBinaryTree(cur, 0, cur.length - 1) ? 1 : 0;
        }

        return answer;
    }

    // 이진트리로 표현 가능한지 확인하는 재귀 함수
    public boolean isBinaryTree(char[] tree, int start, int end) {
        if (start >= end) return true;  // 기저 조건

        int root = (start + end) / 2;

        // 루트가 0인데 자식 노드 중 1이 있으면 불가능
        if (tree[root] == '0') {
            for (int i = start; i <= end; i++) {
//                if (i == root) continue;	// 명시적인 의미는 있으나 불필요함.
                if (tree[i] == '1') return false;
            }
            return true;
        }

        // 루트가 1이면 좌우 서브트리에 대해 재귀 검사
        return isBinaryTree(tree, start, root - 1) &&
               isBinaryTree(tree, root + 1, end);
    }

    // 주어진 수를 이진수로 변환하고, 포화 이진트리 형태가 되도록 앞에 0을 채움
    public char[] getPerfectBinaryTree(long num) {
        String binary = Long.toBinaryString(num);
        int bLen = binary.length();

        // 포화 이진트리 길이: 2^k - 1 중 bLen 이상인 최소 값
        int fullLen = ((bLen & (bLen + 1)) == 0) ? bLen : Integer.highestOneBit(bLen) * 2 - 1;

        // 앞쪽에 '0' 채우기
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fullLen - bLen; i++) sb.append('0');
        sb.append(binary);

        return sb.toString().toCharArray();
    }
}
