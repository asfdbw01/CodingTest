/*
8*8크기의 체스판에 왕이 하나 있다. 킹의 현재 위치가 주어진다.
체스판에서 말의 위치는 다음과 같이 주어진다. 
알파벳 하나와 숫자 하나로 이루어져 있는데, 알파벳은 열을 상징하고, 
숫자는 행을 상징한다. 열은 가장 왼쪽 열이 A이고, 가장 오른쪽 열이 H까지 이고, 
행은 가장 아래가 1이고 가장 위가 8이다. 예를 들어, 
왼쪽 아래 코너는 A1이고, 그 오른쪽 칸은 B1이다.

킹은 다음과 같이 움직일 수 있다.

R : 한 칸 오른쪽으로
L : 한 칸 왼쪽으로
B : 한 칸 아래로
T : 한 칸 위로
RT : 오른쪽 위 대각선으로
LT : 왼쪽 위 대각선으로
RB : 오른쪽 아래 대각선으로
LB : 왼쪽 아래 대각선으로

입력으로 킹이 어떻게 움직여야 하는지 주어진다. 
입력으로 주어진 대로 움직여서 킹이나 돌이 체스판 밖으로 나갈 경우에는 
그 이동은 건너 뛰고 다음 이동을 한다.

킹과 돌의 마지막 위치를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 킹의 위치, 돌의 위치, 움직이는 횟수 N이 주어진다. 
둘째 줄부터 N개의 줄에는 킹이 어떻게 움직여야 하는지 주어진다. 
N은 50보다 작거나 같은 자연수이고, 움직이는 정보는 위에 쓰여 있는 8가지 중 하나이다.

출력
첫째 줄에 킹의 마지막 위치, 둘째 줄에 돌의 마지막 위치를 출력한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static int[] king;
    static int[] rock;
    final static int[] dy = { 0,  0, -1, +1, +1, +1, -1, -1};
    final static int[] dx = {+1, -1,  0,  0, +1, -1, +1, -1};
    static List<String> list = new ArrayList<>();
    final static int boardSize = 8;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[][] board = new int[boardSize][boardSize];
        king  = coordParsher(st.nextToken());
        rock  = coordParsher(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        list.add("R");
        list.add("L");
        list.add("B");
        list.add("T");
        list.add("RT");
        list.add("LT");
        list.add("RB");
        list.add("LB");
        board[king[0]][king[1]] = 1;
        board[rock[0]][rock[1]] = 2;
        run(br, N, board);
        StringBuilder sb = new StringBuilder();
        sb.append(returnCoord(king)).append('\n');
        sb.append(returnCoord(rock)).append('\n');
        //System.out.println(king[0]+" "+king[1]+"\n"+rock[0]+" "+rock[1]);
        System.out.print(sb.toString());
        
    }

    private static void run(BufferedReader br,int N,int[][] board) throws IOException{
        int read = 0;
        while (read<N) {
            read++;
            StringTokenizer st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            int[] nextKing = king.clone();
            int[] nextRock = rock.clone();
            nextKing = moveNext(order, nextKing, board);
            if(isSameLoc(nextKing,king))continue;
            if(isSameLoc(nextKing, nextRock)){
                nextRock = moveNext(order, nextRock, board);
                if(isSameLoc(rock, nextRock)) continue;
            }
            king =nextKing;
            rock = nextRock;
        }

    }

    

    private static boolean isSameLoc(int[] curr,int[] next){     
        return curr[0]==next[0] && curr[1] == next[1];
    }

    private static int[] moveNext(String str,int [] piece,int[][] board){
        int nextIdx = list.indexOf(str);
        int ny = piece[0] + dy[nextIdx], nx = piece[1] + dx[nextIdx];
        boolean canMove = (0 <= ny && ny < boardSize) && (0 <= nx && nx < boardSize);
        if(!canMove) return piece;
        return new int[]{ny, nx};
    }
    
    private static int[] coordParsher(String str){
        int x = str.charAt(0) - 'A';
        int y = str.charAt(1) - '1';
        //System.out.println(x+" "+y);
        return new int[]{y, x};
    }

    private static String returnCoord(int[] piece){
        char col = (char)('A' + piece[1]);
        char row = (char)('1' + piece[0]);
        return "" + col + row;
    }
}