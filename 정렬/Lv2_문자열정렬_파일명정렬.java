/*
문제 요약: 파일명을 HEAD, NUMBER, TAIL로 나눈 후 정렬

1. 파일명은 아래 세 부분으로 구성됨
   - HEAD  : 숫자 전까지의 문자열 (영문자, 공백, 특수문자 포함, 최소 1글자 이상)
   - NUMBER: 연속된 숫자 (최대 5자리, 00000~99999 가능)
   - TAIL  : 그 외 나머지 (없을 수도 있음)

2. 정렬 기준
   1) HEAD를 기준으로 사전 순 정렬 (대소문자 구분 없이)
   2) HEAD가 같으면 NUMBER를 숫자 크기대로 정렬 (숫자 앞의 0 무시)
   3) HEAD와 NUMBER가 같으면 기존 입력 순서 유지 (stable sort)

3. 제약 조건
   - files 배열 길이: 최대 1000개
   - 각 파일명은 길이 100자 이하
   - 파일명은 영문자로 시작하며 숫자를 최소 1개 포함

예시:
["img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"]
 → 정렬 결과:
["img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"]
*/

import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        List<String[]> filesList = new ArrayList<>();
        makeFilesList(filesList,files);
        sortFilesList(filesList);
        
        //answer 넣어주기
        for(int i=0;i<filesList.size();i++){
            answer[i] = String.join("",filesList.get(i));
        }
        
        return answer;
    }
    
    private void makeFilesList(List<String[]> filesList,String[] files){
        for(int i=0;i<files.length;i++){
            //나누기
            String[] file = splitFile(files[i]);
            filesList.add(file);
            //디버깅
            //System.out.println(file[0]+" "+file[1]+" "+file[2]);
        }
    }
    
    private String[] splitFile(String file){
        String[] split = new String[3];
        int headIdx=0,tailIdx = file.length();
        boolean findHead = false;
        
        for(int i=0;i<file.length();i++){
            char c = file.charAt(i);
            if(Character.isDigit(c) && findHead == false){
                headIdx=i;
                findHead = true;
            }
            if (findHead && !Character.isDigit(c)) {
                tailIdx = i;
                break;
            }
            
        }
        split[0] = file.substring(0, headIdx);
        split[1] = file.substring(headIdx, tailIdx); 
        split[2] = file.substring(tailIdx);
        return split;
    }
    
    private void sortFilesList(List<String[]> filesList){
        filesList.sort((a,b) -> {
                      //사전순
                      int headCompare = a[0].toLowerCase().compareTo(b[0].toLowerCase());
                      if(headCompare ==0)
                        return Integer.parseInt(a[1])-Integer.parseInt(b[1]);
                      return headCompare;});
    }
    
}
