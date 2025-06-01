/**
 * 가로 w, 세로 h인 직사각형에서
 * 대각선이 지나가는 격자 칸은 w + h - GCD(w, h)개이며,
 * 나머지 칸은 온전히 사용 가능하다.
 *
 * 전체 격자 수: w * h
 * 사용 불가능한 칸 수: w + h - GCD(w, h)
 * 최종 답: w * h - (w + h - GCD(w, h))
 */

class Solution {
    public long solution(int w, int h) {
        long answer = 1;
        int gcd = getGcd( w,  h);
        int cw = w/gcd,ch = h/gcd;
        answer = (long)w*h - (cw+ch-1)*gcd;
        return answer;
    }
     public int getGcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}
