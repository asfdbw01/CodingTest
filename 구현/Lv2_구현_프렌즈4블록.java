/*
문제 요약: 프렌즈4블록

- m행 n열의 문자 배열(board)에서, 2x2로 같은 문자가 모이면 블록이 지워짐
- 블록이 지워진 후, 위에 있는 블록들이 아래로 떨어짐 (중력 적용)
- 위 과정은 더 이상 지워질 블록이 없을 때까지 반복됨
- 총 몇 개의 블록이 지워졌는지 반환하라

입력:
- m: 행의 길이 (2 ≤ m ≤ 30)
- n: 열의 길이 (2 ≤ n ≤ 30)
- board: 문자열 배열 (각 요소는 n길이 문자열, A~Z 대문자)

출력:
- 사라진 블록의 총 개수 (int)

예시:
m = 6, n = 6,
board = ["TTTANT", 
         "RRFACC", 
         "RRRFCC", 
         "TRRRAA", 
         "TTMMMF", 
         "TMMTTJ"]

→ 총 15개의 블록이 지워짐

핵심 구현 흐름:
1. 2x2 같은 블록 탐색
2. 해당 블록 위치 마킹 후 제거
3. 중력 적용 (아래로 당기기)
4. 반복 (지워질 블록 없을 때까지)
*/

import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] map = toCharMap(board);
        Set<String> coordinate = new HashSet<>();
        int totalRemoved = 0;
        
        //answer 갯수 세기
        do{
            coordinate.clear();
            findSquare(map,coordinate);
            answer += removeBlocks(coordinate,map);
            gravityMap(map);
        }while(!coordinate.isEmpty());
        return answer;
    }
    
    private char[][] toCharMap(String[] board) {
        int m = board.length;
        int n = board[0].length();
        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }
        return map;
    }
    
    private void findSquare(char[][] map,Set<String> coordinate){
        int m = map.length;
        int n = map[0].length;
        for(int i=0;i<m-1;i++){
            for(int j=0;j<n-1;j++){
                if(map[i][j]==' ')continue;
                char c = map[i][j];
                boolean square = map[i][j+1] == c && map[i+1][j]==c && map[i+1][j+1]==c;
                if(square)addToCoordinate(coordinate,i,j);
            }
        }
    }
    
    private void addToCoordinate(Set<String> coordinate,int i,int j){
        coordinate.add(i+","+j);
        coordinate.add((i+1)+","+j);
        coordinate.add(i+","+(j+1));
        coordinate.add((i+1)+","+(j+1));
    }
    
    //여기서 set안비우니까 호출하면 비워라
    private int removeBlocks(Set<String> coordinate,char[][] map){
        int cnt = 0;
        for (String pos : coordinate) {
            String[] split = pos.split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            if (map[x][y] != ' ') {
                map[x][y] = ' ';
                cnt++;
            }
        }
        return cnt;
    }
    
    private void gravityMap(char[][] map){
        for(int x=0;x<map[0].length;x++){
            //x열에 대해 중력 적용 메서드 호출
            gravityColumn(map, x);
        }
    }
    
    private void gravityColumn(char[][] map, int col) {
        int m = map.length;
        int emptyRow = m - 1;

        for (int i = m - 1; i >= 0; i--) {
            if (map[i][col] != ' ') {
                map[emptyRow][col] = map[i][col];
                if (emptyRow != i) map[i][col] = ' ';
                emptyRow--;
            }
        }

        for (int i = emptyRow; i >= 0; i--) {
            map[i][col] = ' ';
        }
    }

}
