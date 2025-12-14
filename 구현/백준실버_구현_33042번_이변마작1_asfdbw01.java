/*
마작에는 같은 종류의 패가 4장씩 존재하지만, 
이변이 일어난 마작패는 종류가 바뀌기 때문에 경우에 따라서는 같은 종류의 패가 
5장 이상이 되는 것도 가능합니다.

여러분은 마작패를 늘어놓다가 같은 마작패가 
5장 이상 나왔을 때 이변을 눈치채고 해결해야 합니다. 
이변을 눈치챌 수 있는 시점을 구해 주세요.


*/
import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static HashMap<String,Integer> map = new HashMap<>();
    static int unexceptedMarkerIdx = 0;

    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        makeMap(N, br);
        System.out.println(unexceptedMarkerIdx);
    }


    private static void makeMap(int N, BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int read = 0;
        while(read < N){
            String marker = st.nextToken();
            map.put(marker,map.getOrDefault(marker, 0)+1 );
            if(map.get(marker)>=5){
                unexceptedMarkerIdx = read+1;
                return;
            }
            read++;
        }
    }
}
