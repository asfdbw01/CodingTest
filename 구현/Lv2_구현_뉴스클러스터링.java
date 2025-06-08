
/*
문제 요약: 자카드 유사도를 이용해 두 문자열 간의 유사도를 계산한다.

1. 두 문자열 str1, str2을 2글자씩 나누어 다중집합으로 만든다.
   - 영문자가 아닌 문자가 포함된 쌍은 제외한다.
   - 대소문자 구분 없이 처리한다.

2. 다중집합 A, B의 교집합과 합집합을 계산한다.
   - 교집합: 각 원소의 개수 중 최소값
   - 합집합: 각 원소의 개수 중 최대값
   - 중복 허용 (multiset)

3. 자카드 유사도 J(A, B) = 교집합 크기 / 합집합 크기
   - 둘 다 공집합이면 유사도는 1로 간주

4. 유사도 결과에 65536을 곱한 뒤 소수점 아래 버림
   - (int)Math.floor(J * 65536)

예시:
str1 = "FRANCE", str2 = "french"
→ 다중집합 A = [fr, ra, an, nc, ce]
→ 다중집합 B = [fr, re, en, nc, ch]
→ 교집합 = [fr, nc], 합집합 = [fr, ra, an, nc, ce, re, en, ch]
→ 유사도 = 2 / 8 = 0.25
→ 출력: 16384
*/


import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        
        //A,B 집합 생성
        HashMap<String,Integer> str1Map = new HashMap<>();
        createSet(str1, str1Map);
        HashMap<String,Integer> str2Map = new HashMap<>();
        createSet(str2, str2Map);
        //합집합 생성
        HashMap<String,Integer> unionMap = new HashMap<>(str1Map);
        createUnionMap( unionMap,str2Map);
        //교집합 생성
        HashMap<String,Integer>  IntersectionMap = new HashMap<>();
        createIntersectionMap(IntersectionMap,str1Map, str2Map);
        
        //원소 수 세기
        int unionCount = countElements(unionMap);
        int intersectCount = countElements(IntersectionMap);
        
        answer =jaccard(unionCount,intersectCount);
        return answer;
    }
    private void createSet(String str,HashMap<String,Integer> strMap){
        for(int i=0;i<str.length()-1;i++){
            String key = str.substring(i,i+2).toLowerCase();
            if (key.matches("[a-z]{2}")){
                strMap.put(key,strMap.getOrDefault(key,0)+1);
            }
        }
    }
    
    private void createUnionMap(HashMap<String,Integer> unionMap,HashMap<String,Integer> str2Map){
        for(String key : str2Map.keySet()){
            int count1 = unionMap.getOrDefault(key, 0);
            int count2 = str2Map.get(key);
            unionMap.put(key, Math.max(count1, count2));
        }
    }
    
    private void createIntersectionMap(HashMap<String,Integer>IntersectionMap
                                       ,HashMap<String,Integer>str1Map,HashMap<String,Integer> str2Map){
        for(String key : str2Map.keySet()){
            int count1 = str1Map.getOrDefault(key, 0);
            int count2 = str2Map.get(key);
            if(count1 !=0 && count2 !=0)IntersectionMap.put(key, Math.min(count1, count2));
        }
    }
    
    private int countElements(HashMap<String,Integer> map){
        int cnt =0;
        for (int value : map.values()) {
            cnt += value;
        }
        return cnt;
    }
    
    private int jaccard(int unionCount,int intersectCount){
        if(unionCount==0)return 65536;
        double div = ((double)intersectCount/unionCount)*65536;
        
        return (int)Math.floor(div);
    }
}
