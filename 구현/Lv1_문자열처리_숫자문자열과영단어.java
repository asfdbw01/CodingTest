/*  
 * 문제 요약  
 *     - 숫자 일부가 영단어로 바뀐 문자열 s가 주어짐  
 *     - 영단어를 숫자로 모두 치환한 후 원래 숫자를 정수로 반환  
 *  
 * 입력  
 *     - s: 숫자와 숫자 영단어가 혼합된 문자열 (길이 1 이상 50 이하)  
 *  
 * 출력  
 *     - 영단어를 숫자로 변환한 정수 값 (int)  
 *  
 * 핵심 포인트  
 *     - replace를 통해 문자열을 숫자 문자열로 치환  
 *     - replace는 대상 문자열에 등장하는 모든 부분을 변환  
 *     - Stream + reduce를 통해 깔끔하게 처리 가능  
 */

class Solution {
	
	public int solution(String s) {
		// 0~9까지 숫자에 대응되는 영단어 배열
		String[] numbers = {
			"zero", "one", "two", "three", "four", 
			"five", "six", "seven", "eight", "nine"
		};

		// Stream + reduce를 사용하여 모든 영단어를 대응 숫자로 치환 후 정수로 반환
		return Integer.parseInt(IntStream.range(0, numbers.length)
				.boxed()
				.reduce(s, (str, i) -> str.replace(numbers[i], Integer.toString(i)), (a, b) -> b));
	}
}
