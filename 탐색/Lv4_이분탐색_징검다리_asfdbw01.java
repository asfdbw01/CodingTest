/*
출발지점부터 distance만큼 떨어진 곳에 도착지점이 있습니다. 
그리고 그사이에는 바위들이 놓여있습니다. 바위 중 몇 개를 제거하려고 합니다.

출발지점부터 도착지점까지의 거리 distance, 
바위들이 있는 위치를 담은 배열 rocks, 
제거할 바위의 수 n이 매개변수로 주어질 때, 
바위를 n개 제거한 뒤 각 지점 사이의 거리의 최솟값 중에 가장 큰 값을 
return 하도록 solution 함수를 작성해주세요.

제한사항
도착지점까지의 거리 distance는 1 이상 1,000,000,000 이하입니다.
바위는 1개 이상 50,000개 이하가 있습니다.
n 은 1 이상 바위의 개수 이하입니다.
*/

import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        answer = binarySearch(rocks,distance,n);
        return answer;
    }
    
    private int binarySearch(int[] rocks,int distance,int n) {
        int left = 0, right = distance;
        int maxOfMinDist =-1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int remove = cntRemove(rocks,mid,distance);
            if(remove <= n){
                left = mid+1;
                maxOfMinDist = Math.max(maxOfMinDist,mid);
            }
            else right = mid-1;
        }

        return maxOfMinDist;
    }
    
    private int cntRemove(int[] rocks,int mid,int distance) {
        int remove=0;
        int cur = 0;
        for(int i=0;i<rocks.length;i++){
            if(rocks[i]-cur < mid){
                remove++;
            }
            else cur = rocks[i];
        }
        
        
        if(distance-cur<mid)remove++;
        return remove;
    }

}