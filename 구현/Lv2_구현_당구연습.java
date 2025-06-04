// 당구대의 크기: 가로 m, 세로 n
// 머쓱이의 시작 위치: (startX, startY)
// 맞춰야 할 공들의 위치: balls (2차원 배열)

// 머쓱이는 항상 벽에 최소 한 번 맞힌 후 공에 도달해야 함 (원쿠션)
// 거리는 유클리드 거리의 제곱을 사용 (제곱근 없이)
// 공이 벽에 맞기 전에 목표 공을 먼저 맞게 되는 경우는 무효 (제외)

// 각 벽을 기준으로 목표 공을 대칭 이동시킴
// → 왼쪽, 오른쪽, 위, 아래 벽에 대해 각각 4가지 반사 위치 계산
// → 반사된 목표 공을 기준으로 시작점에서 거리 계산

// 단, 시작점과 목표 공이 일직선에 있고
// 벽에 닿기 전에 공에 먼저 도달하는 경우는 제외해야 함
// → 벡터 방향이 같으면 제외 (isVectorSame 함수 활용)

// 각 공에 대해 최소 거리 제곱값을 구해 answer 배열에 저장


class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int [balls.length];
        //각 회마다 머쓱이가 친 공이 굴러간 거리의 최솟값의 제곱을 배열에 담아 return 
        
        for(int i=0;i<balls.length;i++){
            int minDist = Integer.MAX_VALUE;
            int targetX = balls[i][0];
            int targetY = balls[i][1];
            
             int[][] mirrors = {
                {-targetX, targetY},        // 왼쪽 벽 반사
                {2 * m - targetX, targetY}, // 오른쪽 벽 반사
                {targetX, -targetY},        // 아래 벽 반사
                {targetX, 2 * n - targetY}  // 위 벽 반사
            };
            
            for(int[] p : mirrors){
                int dx = startX - p[0];
                int dy = startY - p[1];
                int dist = dx*dx+dy*dy;
                
                //친 공이 벽대신 공맞으면 continue
                // -> 친 공 벡터방향 같으면 continue
                if(isVactorSame( startX,startY,p[0],p[1],targetX,targetY))continue;
                minDist = Math.min(minDist,dist);
            }
            answer[i] = minDist;
        }
        
        return answer;
    }
    private boolean isVactorSame(int x,int y,int nx,int ny,int ex,int ey){
        int dx1 = nx - x;
        int dy1 = ny - y;
        int dx2 = ex - x;
        int dy2 = ey - y;

        // 외적이 0이면 방향 같거나 반대 (같은 직선 위에 있음)
        // 내적이 양수면 같은 방향
        return (dx1 * dy2 == dy1 * dx2) && (dx1 * dx2 >= 0) && (dy1 * dy2 >= 0);
    }
}
