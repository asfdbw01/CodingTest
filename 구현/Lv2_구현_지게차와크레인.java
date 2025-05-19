/*
//컨테이너가 세로로 n 줄, 가로로 m줄 총 n x m개 놓여 있습니다.
//특정 종류 컨테이너의 출고 요청이 들어올 때마다 지게차로 창고에서 접근이 가능한 해당 종류의 컨테이너를 모두 꺼냅니다.
//접근이 가능한 컨테이너란 4면 중 적어도 1면이 창고 외부와 연결된 컨테이너를 말합니다.
//“A”처럼 알파벳 하나로만 출고 요청이 들어올 경우 지게차를 사용해 출고 요청이 들어온 순간 접근 가능한 컨테이너를 꺼냅니다. 
//"BB"처럼 같은 알파벳이 두 번 반복된 경우는 크레인을 사용해 요청된 종류의 모든 컨테이너를 꺼냅니다.
*/

import java.util.*;

class Solution {
    public int solution(String[] storage, String[] requests) {
        int storageWidth = storage[0].length();
        int storageHight = storage.length;
        int answer = storageWidth*storageHight;
        // storage 를 char array로 바꿈
        char[][] storageChar = new char[storageHight+2][storageWidth+2];
        //'n' 이면 비어있고 경계면과 연결되있음
        for (int i = 0; i < storageChar.length; i++) {
            storageChar[i][0] = 'n';                                 // 왼쪽
            storageChar[i][storageChar[0].length - 1] = 'n';         // 오른쪽
        }
        for (int j = 0; j < storageChar[0].length; j++) {
            storageChar[0][j] = 'n';                                 // 위쪽
            storageChar[storageChar.length - 1][j] = 'n';            // 아래쪽
        }

        for(int i=0;i<storage.length;i++){
            char[] c = storage[i].toCharArray();
            for(int j=0;j<c.length;j++){
                storageChar[i+1][j+1] = c[j];
            }
        }
        
        //실행문
        Queue<int[]> delete = new LinkedList<>();
        for(int i=0;i<requests.length;i++){
            char target = requests[i].charAt(0);
            if(requests[i].length()==1)requestIsOneAlphabet (storageChar,delete,target);
            else requestIsTwoAlphabet (storageChar,delete,target);
            answer -= delete.size();
            System.out.println(answer);
            deleteCharArr(storageChar,delete);
        }
        
        return answer;
    }
    
    //한글자 메서드 
    private void requestIsOneAlphabet (char[][] storageChar,Queue<int[]> delete,char target){
        for(int i=1;i<=storageChar.length-1;i++){
            for(int j=1;j<=storageChar[i].length-1;j++){
                if(storageChar[i][j]=='\u0000')continue; // 공백시 스킵
                if(storageChar[i][j]!=target)continue;// 찾는게 아님 스킵
                if(isConnectedToEdge(storageChar,i,j)){delete.add(new int[]{i,j});}
            }
        }
    }
    
    // 외부에 한쪽이라도 있는지(한면이라도 'n'과 접하면)
    private boolean isConnectedToEdge(char[][] storageChar, int y, int x) {
        
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            // 외부 공백과 접촉했으면 외부 연결로 간주
            if (storageChar[ny][nx] == 'n') return true;
        }

        return false;
    }



    //두글자 메서드
    private void requestIsTwoAlphabet (char[][] storageChar,Queue<int[]> delete,char target){
        for(int i=1;i<=storageChar.length-1;i++){
            for(int j=1;j<=storageChar[i].length-1;j++){
                if(storageChar[i][j]==target)delete.add(new int[]{i,j});
            }
        }
    }
    
    
    //삭제 메서드
    private void deleteCharArr(char[][] storageChar,Queue<int[]> delete){
        while(!delete.isEmpty()){
            int[] target = delete.poll();
            System.out.println(target[0]+" "+target[1]);//삭제 좌표 까보기
            if(isConnectedToEdge(storageChar, target[0], target[1])){
                storageChar[target[0]][target[1]] = 'n';
                spreadn(storageChar,target[0],target[1]);
            }
            else storageChar[target[0]][target[1]] = '\u0000';
        }
    }
    
    private void spreadn(char[][] storageChar,int y,int x){
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            // 외부 공백과 접촉했으면 외부 연결로 간주
            if (storageChar[ny][nx] == '\u0000') {
                storageChar[ny][nx]='n';
                spreadn(storageChar,ny,nx);
            }
        }
    }
    
}
