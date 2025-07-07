/*
// 🎵 베스트 앨범 문제 요약
// - 장르별로 가장 많이 재생된 노래를 최대 2곡씩 모아 앨범에 수록
// - 장르 총 재생수 ↓, 장르 내 재생수 ↓, 고유번호 ↑
// - 각 장르의 노래는 최대 2곡까지만 선택
*/

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        //곡장르, [곡 인덱스,곡 재생수]들어간 역순 우선순위 큐 해시맵
        Map<String, Integer> genreTotal = new HashMap<>();
        Map<String, PriorityQueue<int[]>> genreMap =  makeGenreMap(genres, plays,genreTotal);
        
        List<String> sortedGenres = new ArrayList<>(genreTotal.keySet());
        sortedGenres.sort((a,b) -> genreTotal.get(b)- genreTotal.get(a));
        
        List<Integer> playList =  makePlayList(sortedGenres,genreMap,2);
        answer = playList.stream()
                    .mapToInt(Integer::intValue) 
                    .toArray();
        return answer;
    }
    
    private  Map<String, PriorityQueue<int[]>> makeGenreMap(String[] genres, int[] plays,
                                                            Map<String, Integer> genreTotal){
        Map<String, PriorityQueue<int[]>> genreMap = new HashMap<>();
        int songAmount = genres.length;
        for (int i = 0; i < songAmount; i++) {
            String genre = genres[i];
            genreMap
                .computeIfAbsent(genre, k -> new PriorityQueue<>(
                    (a, b) -> a[1] != b[1] ? b[1] - a[1] : a[0] - b[0]))
                .add(new int[]{i, plays[i]});
            genreTotal.put(genre, genreTotal.getOrDefault(genre, 0) + plays[i]);
        }

        return genreMap;
    }
    
    private List<Integer> makePlayList(List<String> sortedGenres,
                                       Map<String, PriorityQueue<int[]>> genreMap,int genreTopSongs){
        List<Integer> playList = new ArrayList<>();
        for(String s : sortedGenres){
            PriorityQueue<int[]> pq = genreMap.get(s);
            for(int i=0;i<genreTopSongs && !pq.isEmpty();i++){
                int[] song = pq.poll();
                playList.add(song[0]);
            }
        }
        return playList;
    }
    
}
