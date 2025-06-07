/*
캐시 크기(cacheSize)와 도시이름 배열(cities)을 입력받는다.
cacheSize는 정수이며, 범위는 0 ≦ cacheSize ≦ 30 이다.
cities는 도시 이름으로 이뤄진 문자열 배열로, 최대 도시 수는 100,000개이다.
각 도시 이름은 공백, 숫자, 특수문자 등이 없는 영문자로 구성되며, 대소문자 구분을 하지 않는다. 도시 이름은 최대 20자로 이루어져 있다.

입력된 도시이름 배열을 순서대로 처리할 때, "총 실행시간"을 출력한다.

캐시 교체 알고리즘은 LRU(Least Recently Used)를 사용한다.
cache hit일 경우 실행시간은 1이다.
cache miss일 경우 실행시간은 5이다.

*/
//참고 LRU 알고리즘은 알고리즘라이브러리 레포에 올려둠
import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LRUCache<String, Boolean> cache = new LRUCache<>(cacheSize);
        answer = countCacheTime(cache, cities);
        return answer;
    }
    static class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true); // accessOrder = true
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }
    
    private int countCacheTime(LRUCache<String, Boolean> cache, String[] cities){
        int count = 0;
        for(int i=0;i<cities.length;i++){
            String city = cities[i].toLowerCase();
            if(cache.containsKey(city))count+=1;
            else count +=5;
            cache.put(city, true); // key를 city로
        }
        return count;
    }

}
