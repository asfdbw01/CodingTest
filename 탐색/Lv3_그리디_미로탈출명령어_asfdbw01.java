/*
n x m 격자 미로가 주어집니다. 당신은 미로의 (x, y)에서 출발해 (r, c)로 이동해서 탈출해야 합니다.

단, 미로를 탈출하는 조건이 세 가지 있습니다.

격자의 바깥으로는 나갈 수 없습니다.
(x, y)에서 (r, c)까지 이동하는 거리가 총 k여야 합니다.
 이때, (x, y)와 (r, c)격자를 포함해, 같은 격자를 두 번 이상 방문해도 됩니다.
미로에서 탈출한 경로를 문자열로 나타냈을 때, 문자열이 사전 순으로 가장 빠른 경로로 탈출해야 합니다.

이동 경로는 다음과 같이 문자열로 바꿀 수 있습니다.

l: 왼쪽으로 한 칸 이동
r: 오른쪽으로 한 칸 이동
u: 위쪽으로 한 칸 이동
d: 아래쪽으로 한 칸 이동

격자의 크기를 뜻하는 정수 n, m, 출발 위치를 뜻하는 정수 x, y, 
탈출 지점을 뜻하는 정수 r, c, 탈출까지 이동해야 하는 거리를 뜻하는 정수 k가 매개변수로 주어집니다. 
이때, 미로를 탈출하기 위한 경로를 return 하도록 solution 함수를 완성해주세요.
 단, 위 조건대로 미로를 탈출할 수 없는 경우 "impossible"을 return 해야 합니다.


제한사항
2 ≤ n (= 미로의 세로 길이) ≤ 50
2 ≤ m (= 미로의 가로 길이) ≤ 50
1 ≤ x ≤ n
1 ≤ y ≤ m
1 ≤ r ≤ n
1 ≤ c ≤ m
(x, y) ≠ (r, c)
1 ≤ k ≤ 2,500

*/

class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        //가능 = 최소거리 +2n
        if(!isPossible( x,  y, r, c,k))return "impossible";
        answer = makeBuild(n, m, x,  y,  r, c, k);
        return answer;
    }
    
    private String makeBuild(int n, int m, int x, int y, int r, int c, int k){
        int[] dx = { 1, 0, 0,-1};   
        int[] dy = { 0,-1, 1, 0}; 
        String[] d = {"d","l","r","u"};
        
        StringBuilder sb = new StringBuilder();
        
        int cy = y,cx = x;
        int remain = k;
        
        while(remain>0){
            for(int dir = 0;dir<4;dir++){
                int ny = cy+dy[dir];
                int nx = cx+dx[dir];
                
                if(!isVaild(n,  m,ny,nx))continue;
                
                int left = remain-1;
                int dist = Math.abs(nx-r)+Math.abs(ny-c);
                
                if(dist <= left && ((left-dist)%2==0)){
                    sb.append(d[dir]);
                    cx = nx;
                    cy = ny;
                    remain = left;
                    break;
                }
            }
        }
        
        return sb.toString();
    }
    
    private boolean isVaild(int n, int m,int ny,int nx){
        return 1 <= nx && nx <= n && 1 <= ny && ny <= m;
    }
    
    private boolean isPossible(int x, int y, int r, int c,int k){
        int dist = Math.abs(x-r)+Math.abs(y-c);
        if(dist > k)return false;
        else if(dist == k)return true;
        else if((k - dist)%2==0)return true;
        return false;
    }
}