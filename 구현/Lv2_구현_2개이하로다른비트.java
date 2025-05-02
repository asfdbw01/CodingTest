// 문제 요약:
// 주어진 수보다 큰 수 중에서 이진수로 1~2개만 다른 수 중 가장 작은 수를 찾음
// 예시: f(2) = 3 (2보다 크고 비트 차이 1개), f(7) = 11 (2개 다름)
// 풀이 요약:
// - 숫자를 이진 문자열로 변환 후 앞에 "0" 추가 (자리 올림 대비)
// - 마지막 비트가 0이면 바로 1로 변경 (짝수 케이스)
// - 아니면 뒤에서부터 처음 만나는 0을 1로 바꾸고 그 옆 1을 0으로 바꿈
// 카테고리: 그리디 / 비트 조작 / 문자열 처리


class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        // 처음 0 -> +1
        // 처음 1 다음 0 -> +1
        //처음 1 다음 1 -> 다음 0일때 +1
        
        for(int i=0;i<numbers.length;i++){
            answer[i] = function(numbers[i]);
        }
        
        return answer;
    }
    
    private long function(long number){
        String binary = Long.toBinaryString(number);
        binary = "0"+binary;
        char[] binaryArray = binary.toCharArray();
        int lastIdx = binaryArray.length-1;
        if(binaryArray[lastIdx]=='0')binaryArray[lastIdx]='1';
        else{
            for(int i=lastIdx-1;i>=0;i--){
                if(binaryArray[i]=='0'){
                    binaryArray[i+1]='0';
                    binaryArray[i]='1';
                    break;
                }
            }
        }
        return Long.parseLong(new String(binaryArray), 2);
    }
}
