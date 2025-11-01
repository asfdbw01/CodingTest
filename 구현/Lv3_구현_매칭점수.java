/*  
 * 문제 요약  
 *     - 웹페이지에서 검색어 등장 횟수(기본 점수) + 외부 링크를 통해 유입된 점수(링크 점수)를 더해 매칭 점수를 구한다.
 *     - 검색어는 대소문자 구분 없이, 알파벳만으로 구분되는 완전한 단어와 일치할 때만 점수로 반영된다.
 *     - HTML 내 meta 태그로부터 주소를 추출하고, a 태그로부터 외부 링크를 수집하여 점수를 계산한다.
 *
 * 입력  
 *     - word: 검색어 문자열 (대소문자 섞일 수 있음)
 *     - pages: HTML 형식의 웹 페이지 문자열 배열 (최대 20개, 각 문자열 최대 1500자)
 *
 * 출력  
 *     - 매칭 점수가 가장 높은 페이지의 인덱스 (동점이면 더 작은 인덱스)
 *
 * 핵심 포인트  
 *     - 링크 파싱 시 `<meta>`와 `<a>` 태그에서 정확한 규칙으로 URL 추출
 *     - 단어 구분을 위해 알파벳 외 모든 문자를 공백으로 치환
 *     - 링크 점수는 (기본 점수 / 외부 링크 수) 형태로 분배
 */

class Solution {
	
	public int solution(String word, String[] pages) {
		// 검색어 소문자로 통일
		word = word.toLowerCase();
		
		// 페이지 목록과 URL -> index 매핑
		List<Page> pageList = new ArrayList<>();
		Map<String, Integer> pageMap = new HashMap<>();
		
		// 1단계: 각 페이지 객체 생성 및 주소 등록
		for (int i = 0; i < pages.length; i++) {
			Page page = new Page(i, pages[i].toLowerCase(), word);
			pageList.add(page);
			pageMap.put(page.address, i);
		}
		
		// 2단계: 외부 링크로부터 링크 점수 분배
		for (Page page : pageList) {
			if (page.links.isEmpty()) continue;
			double linkScore = page.basicScore / page.links.size();
			for (String link : page.links) {
				if (pageMap.containsKey(link)) {
					pageList.get(pageMap.get(link)).linkScore += linkScore;
				}
			}
		}
		
		// 3단계: 매칭 점수가 가장 높은 페이지 탐색
		double[] answer = {0, 0};
		for (Page page : pageList) {
			double totalScore = page.basicScore + page.linkScore;
			if (totalScore > answer[1]) {
				answer[0] = page.index;
				answer[1] = totalScore;
			}
		}
		
		return (int) answer[0];
	}
	
	class Page {
		String address;      // 페이지 URL
		String body;         // <body> 이후 텍스트
		String contents;     // 텍스트에서 링크 제외 후 정제된 본문
		List<String> links;  // 외부 링크 목록
		double basicScore;   // 기본 점수
		double linkScore;    // 외부로부터 받은 점수
		int index;           // 페이지 인덱스
		
		Page(int index, String html, String word) {
			// 불필요한 개행 제거 및 태그 정리
			String str = html.replace("<a", "<a>").replace("</", "<");
			this.address = getLink(html, "<meta property=\"og:url\" content");
			this.body = str.replace("\n", " ").split("<body>")[1];
			splitBody();                  // 링크 수집 + 텍스트 정제
			getBasicScore(word);         // 기본 점수 계산
			this.linkScore = 0D;
			this.index = index;
		}
		
		// 태그에서 URL 추출
		private String getLink(String str, String header) {
			header = header + "=\"";
			int startIndex = str.indexOf(header) + header.length();
			int endIndex = str.indexOf("\"", startIndex);
			return str.substring(startIndex, endIndex);
		}
		
		// <a> 태그로부터 링크 수집 및 나머지 본문 정제
		private void splitBody() {
			this.links = new ArrayList<>();
			String[] parts = body.split("<a>");
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].trim().isEmpty()) continue;
				
				if (i % 2 == 0) sb.append(parts[i]).append(" ");
				else if (parts[i].startsWith(" href")) {
					this.links.add(getLink(parts[i], "href"));
				}
			}
			
			// 알파벳 외 문자는 공백 처리
			this.contents = sb.toString().replaceAll("[^a-z]", " ");
		}
		
		// 검색어 기준으로 기본 점수 계산
		private void getBasicScore(String word) {
			int count = 0;
			for (String s : this.contents.split(" ")) {
				if (s.equals(word)) count++;
			}
			this.basicScore = (double) count;
		}
	}
}
