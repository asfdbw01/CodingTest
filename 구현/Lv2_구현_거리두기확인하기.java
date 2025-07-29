/*
개발자를 희망하는 죠르디가 카카오에 면접을 보러 왔습니다.

코로나 바이러스 감염 예방을 위해 응시자들은 거리를 둬서 대기를 해야하는데 개발 직군 면접인 만큼
아래와 같은 규칙으로 대기실에 거리를 두고 앉도록 안내하고 있습니다.

대기실은 5개이며, 각 대기실은 5x5 크기입니다.
거리두기를 위하여 응시자들 끼리는 맨해튼 거리1가 2 이하로 앉지 말아 주세요.
단 응시자가 앉아있는 자리 사이가 파티션으로 막혀 있을 경우에는 허용합니다.

멘헤튼 거리 : 두 테이블 T1, T2가 행렬 (r1, c1), (r2, c2)에 각각 위치하고 있다면, T1, T2 사이의 맨해튼 거리는 |r1 - r2| + |c1 - c2| 입니다. ↩

제한사항
places의 행 길이(대기실 개수) = 5
places의 각 행은 하나의 대기실 구조를 나타냅니다.
places의 열 길이(대기실 세로 길이) = 5
places의 원소는 P,O,X로 이루어진 문자열입니다.
places 원소의 길이(대기실 가로 길이) = 5
P는 응시자가 앉아있는 자리를 의미합니다.
O는 빈 테이블을 의미합니다.
X는 파티션을 의미합니다.
입력으로 주어지는 5개 대기실의 크기는 모두 5x5 입니다.
return 값 형식
1차원 정수 배열에 5개의 원소를 담아서 return 합니다.
places에 담겨 있는 5개 대기실의 순서대로, 거리두기 준수 여부를 차례대로 배열에 담습니다.
각 대기실 별로 모든 응시자가 거리두기를 지키고 있으면 1을, 한 명이라도 지키지 않고 있으면 0을 담습니다.
*/

class Solution {
    public int[] solution(String[][] places) {
        // 맨해튼 거리는 |r1 - r2| + |c1 - c2| 
        int[] answer = new int[places.length];
        for(int i=0;i<places.length;i++){
            answer[i] = isEveryonePossible(places[i]);
        }
        return answer;
    }
    private int isEveryonePossible(String[] place){
        for(int y=0;y<place.length;y++){
            for(int x=0;x<place[0].length();x++){
                if(place[y].charAt(x)=='P' && isPossible(place,y,x)==false)return 0;
            }
        }
        return 1;
    }
    
    private boolean isPossible(String[] place, int y, int x) {
        return leftPossible(place, y, x)
            && downPossible(place, y, x)
            && diagonalPossible(place, y, x);
    }

    private boolean leftPossible(String[] place, int y, int x) {
        int m = place[0].length();
        if (x + 1 < m && place[y].charAt(x + 1) == 'P') return false;
        if (x + 2 < m && place[y].charAt(x + 2) == 'P' && place[y].charAt(x + 1) == 'O') return false;
        return true;
    }

    private boolean downPossible(String[] place, int y, int x) {
        int n = place.length;
        if (y + 1 < n && place[y + 1].charAt(x) == 'P') return false;
        if (y + 2 < n && place[y + 2].charAt(x) == 'P' && place[y + 1].charAt(x) == 'O') return false;
        return true;
    }

    private boolean diagonalPossible(String[] place, int y, int x) {
        int n = place.length;
        int m = place[0].length();
        if (y + 1 >= n || x + 1 >= m) return true;

        if (place[y + 1].charAt(x + 1) == 'P') {
            if (!(place[y + 1].charAt(x) == 'X' && place[y].charAt(x + 1) == 'X'))
                return false;
        }
        
        if (y - 1 >= 0 && x + 1 < m && place[y - 1].charAt(x + 1) == 'P') {
            if (!(place[y - 1].charAt(x) == 'X' && place[y].charAt(x + 1) == 'X')) return false;
        }

        
        if (y + 1 < n && x - 1 >= 0 && place[y + 1].charAt(x - 1) == 'P') {
            if (!(place[y + 1].charAt(x) == 'X' && place[y].charAt(x - 1) == 'X')) return false;
        }

        return true;
    }


}
