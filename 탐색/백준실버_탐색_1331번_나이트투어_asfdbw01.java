/*
나이트 투어는 체스판에서 나이트가 모든 칸을 정확히 한 번씩 방문하며, 
마지막으로 방문하는 칸에서 시작점으로 돌아올 수 있는 경로이다. 
영식이는 6×6 체스판 위에서 또 다른 나이트 투어의 경로를 찾으려고 한다. 
체스판의 한 칸은 A, B, C, D, E, F 중에서 하나와 
1, 2, 3, 4, 5, 6 중에서 하나를 이어 붙인 것으로 나타낼 수 있다. 
영식이의 나이트 투어 경로가 주어질 때, 이것이 올바른 것이면 Valid, 
올바르지 않으면 Invalid를 출력하는 프로그램을 작성하시오.

입력
36개의 줄에 나이트가 방문한 순서대로 입력이 주어진다. 체스판에 존재하는 칸만 입력으로 주어진다.

출력
첫째 줄에 문제의 정답을 출력한다.
*/
import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static final int maxMove = 36;
    static final int[] dx = {-2,-1,-2,-1,1,2,1,2};
    static final int[] dy = {1,2,-1,-2,2,1,-2,-1};
    static final int direction = 8;
    static final int boardRange = 6;

    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println(returnAnswer(br));
    }

    private static String returnAnswer(BufferedReader br) throws IOException{
        int read = 1;
        boolean[][] visited = new boolean[boardRange][boardRange];
        StringTokenizer st = new StringTokenizer(br.readLine());
        String firstStr = st.nextToken(); 
        int[] firstCoord = coordParsher(firstStr);
        String currStr = firstStr;

        while(read < maxMove){
            st = new StringTokenizer(br.readLine());
            String nextStr = st.nextToken(); 
            if(!isVaild(currStr, nextStr, visited))return "Invalid";
            currStr = nextStr;
            read++;
        }

        visited[firstCoord[0]][firstCoord[1]]=false;
                if(!isVaild(currStr, firstStr, visited))return "Invalid";
        return "Valid";
    }

    private static boolean isVaild(String curr,String next,boolean[][] visited){
        int[] currCoord = coordParsher(curr);
        int[] nextCoord = coordParsher(next);
        visited[currCoord[0]][currCoord[1]]=true;
        for(int dir = 0;dir < direction;dir++){
            int nx = currCoord[0]+dx[dir];
            int ny = currCoord[1]+dy[dir];
            if(isSameCoord(new int[]{nx,ny}, nextCoord) && !visited[nextCoord[0]][nextCoord[1]]){
                visited[nextCoord[0]][nextCoord[1]]=true;
                return true;
            }
        }
        return false;
    }

    private static int[] coordParsher(String str){
        int x= str.charAt(0)-'A';
        int y=str.charAt(1)-'1';
        return new int[]{x,y};
    }

    private static boolean isSameCoord(int[]A,int[]B){
        return A[0]==B[0] && A[1]==B[1];
    }
}