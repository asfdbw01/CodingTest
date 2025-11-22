/*
1부터 N2까지의 수가 하나씩 채워져 있는 크기가 N×N인 배열이 있고, 
이 배열의 모든 행, 열, 길이가 N인 대각선의 합이 모두 같을 때, 매직 스퀘어라고 한다.

크기가 3×3인 배열 A가 주어졌을 때, 이 배열을 매직 스퀘어로 변경하려고 한다. 
한 칸에 있는 수 a를 b로 변경하는 비용은 |a - b| 이다. 

입력
총 세 개의 줄에 걸쳐서 배열 A의 원소가 주어진다.

출력
첫째 줄에 배열 A를 매직 스퀘어로 변경하는 비용의 최솟값을 출력한다.

가장 오른쪽 아랫칸의 수를 6으로 변경하면 매직 스퀘어가 되고, 비용은 |5 - 6| = 1이다.

*/
import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static final int size = 3;
    static int ans = Integer.MAX_VALUE;
    static boolean [] num = new boolean[size*size+1];
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        arr = makeArr();
        backTraking(0, 0);
        System.out.println(ans);
    }

    private static int[][] makeArr() throws IOException{
        int[][] arr = new int[size][size];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int read = 0;
        for(int i=0;i<size;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<size;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return arr;
    }

    private static void backTraking(int depth,int cost){
        //종료조건 삽입
        if(depth == size*size && isMagicSquare()){
            ans = Math.min(ans,cost);
            return;
        }
        int x=depth/size;
        int y = depth%size;

        for(int i=1;i<=size*size;i++){
            if(!num[i]){
                int temp = arr[x][y];
                num[i] = true;
                arr[x][y] = i;
                backTraking(depth+1, cost+Math.abs(temp-i));
                num[i] = false;
                arr[x][y] = temp;
            }
        }
    }

    private static boolean isMagicSquare(){
        int rowSum0 = arr[0][0] + arr[0][1] + arr[0][2];
        int rowSum1 = arr[1][0] + arr[1][1] + arr[1][2];
        int rowSum2 = arr[2][0] + arr[2][1] + arr[2][2];
 
        int colSum0 = arr[0][0] + arr[1][0] + arr[2][0];
        int colSum1 = arr[0][1] + arr[1][1] + arr[2][1];
        int colSum2 = arr[0][2] + arr[1][2] + arr[2][2];
 
        int diagonalSum0 = arr[0][0] + arr[1][1] + arr[2][2];
        int diagonalSum1 = arr[2][0] + arr[1][1] + arr[0][2];
 
        if(rowSum0 != 15 || rowSum1 != 15 || rowSum2 != 15) return false;
        if(colSum0 != 15 || colSum1 != 15 || colSum2 != 15) return false;
        if(diagonalSum0 != 15 || diagonalSum1 != 15) return false;

        return true;
    }

    
}