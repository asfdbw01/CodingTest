/*
앞뒤를 뒤집어도 똑같은 문자열을 팰린드롬(palindrome)이라고 합니다.
문자열 s가 주어질 때, s의 부분문자열(Substring)중 가장 긴 팰린드롬의 길이를 return 하는 solution 함수를 완성해 주세요.
*/

class Solution
{
    public int solution(String s)
    {
        int answer = lengthOfPalindrome( s);
        
        return answer;
    }
    
    private boolean isPalindrome(String s, int mid, int range) {
        int leftStart = mid - range + 1;
        int rightStart = mid + 1;

        if (leftStart < 0 || rightStart + range > s.length()) return false;

        String left = s.substring(leftStart, mid + 1);
        String right = s.substring(rightStart, rightStart + range);

        StringBuilder sb = new StringBuilder(right);
        return left.equals(sb.reverse().toString());
    }
    
    private int lengthOfPalindrome(String s) {
        int max = 1;

        for (int mid = 0; mid < s.length(); mid++) {
            // 홀수 길이
            max = Math.max(max, expand(s, mid, mid));
            // 짝수 길이
            max = Math.max(max, expand(s, mid, mid + 1));
        }

        return max;
    }

    private int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 현재 left와 right는 팰린드롬 범위를 벗어난 상태
        return right - left - 1;
    }


}
