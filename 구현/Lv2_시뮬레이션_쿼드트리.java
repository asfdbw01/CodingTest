/*
문제 요약:
- 0과 1로 구성된 2차원 배열(정사각형)에서 쿼드트리 방식으로 압축한다.
- 배열의 특정 영역이 전부 0 또는 전부 1이면 하나로 압축.
- 압축된 결과로 최종적으로 0과 1이 각각 몇 번 압축되었는지 반환.

구현 방식:
- 배열을 4등분해서 재귀적으로 탐색한다.
- 더 이상 쪼갤 필요가 없는(모두 같은 값) 부분은 해당 숫자를 카운트한다.
*/



class Solution {
    int[] answer = new int[2];
    
    public int[] solution(int[][] arr) {
        
        int length = arr.length;
        quad(arr, 0, 0, length);
        return answer;
    }
    
    private void quad(int[][]arr, int row, int col, int size){
        if (instanceIsSame(arr, row, col, size)) {
            answer[arr[row][col]]++;
            return;
        }
        
        int half = size / 2;
        
        quad(arr, row, col, half);
        quad(arr, row, col + half, half);
        quad(arr, row + half, col, half);
        quad(arr, row + half, col + half, half);

        
    }
    
    
    private boolean instanceIsSame(int[][] arr,int row,int col,int size){
        int value = arr[row][col];
        
        for(int i=row;i<row+size;i++){
            for(int j=col;j<col+size;j++){
                if(arr[i][j]!=value)return false;
            }
        }
        return true;
    }

}
