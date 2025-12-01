/*  
 * 문제 요약
 *     - 파일명을 HEAD, NUMBER, TAIL로 분리한 뒤, 정해진 정렬 기준에 따라 정렬해야 한다.
 *     - HEAD: 문자로 시작하며 최소 1글자 이상
 *     - NUMBER: 연속된 숫자 1~5자리 (앞의 0 허용), 정수로 비교
 *     - TAIL: 남은 부분 (있을 수도, 없을 수도 있음)
 *
 * 입력  
 *     - files: 문자열 배열 (파일명), 길이 1~1000, 각 문자열 길이 1~100
 *     - 각 파일명은 영문자로 시작하고 숫자를 반드시 포함
 *
 * 출력  
 *     - 정렬 기준에 따라 재배치된 문자열 배열
 *
 * 핵심 포인트  
 *     - HEAD는 대소문자 구분 없이 사전순 정렬
 *     - NUMBER는 앞의 0을 무시하고 정수로 비교
 *     - HEAD, NUMBER가 동일할 경우 원래 입력 순서 유지 (안정 정렬)
 *     - 필요시 index를 저장해 정렬 안정성 보강 가능
 */

class Solution {
	
	public String[] solution(String[] files) {
		return IntStream.range(0, files.length)
						.mapToObj(i -> new File(files[i], i))   // 파일명 + 원래 index 포함 객체 생성
						.sorted()                               // 커스텀 정렬
						.map(i -> i.name)                       // 정렬된 원본 문자열 추출
						.toArray(String[]::new);
	}
	
	class File implements Comparable<File> {
		String name;    // 원본 파일명
		String head;    // HEAD
		int number;     // NUMBER (정수)
		int index;      // 입력 순서 보존용 index
		
		File(String file, int index) {
			this.name = file;
			
			int i = 0;
			while (i < file.length() && !Character.isDigit(file.charAt(i))) i++;
			this.head = file.substring(0, i);  // HEAD 추출
			
			int j = i;
			while (j < file.length() && j - i < 5 && Character.isDigit(file.charAt(j))) j++;
			this.number = Integer.parseInt(file.substring(i, j));  // NUMBER 추출
			
			this.index = index;  // 입력 순서 저장
		}
		
		@Override
		public int compareTo(File o) {
			int headComp = this.head.toLowerCase().compareTo(o.head.toLowerCase());
			if (headComp != 0) return headComp;  // HEAD 비교
			
			int numberComp = Integer.compare(this.number, o.number);
			if (numberComp != 0) return numberComp;  // NUMBER 비교
			
			return Integer.compare(this.index, o.index);  // 입력 순서 유지
		}
	}
}
