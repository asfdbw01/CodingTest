/*
주어진 숫자 중 소수 갯수 출력
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
	
	static boolean isPrime(int n) {
		if(n<2)return false;
		for(int i=2;i*i<=n;i++) {
			if(n%i==0)return false;
		}
		return true;
	}
	
    public static void main(String[] args) throws IOException {
        // 버퍼를 사용해 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // String line = br.readLine();
        int N = Integer.parseInt(br.readLine());
        
        int cnt = 0,read = 0;
        StringTokenizer st = null;
        // 입력 받은 값을 처리하는 로직 작성

        while(read < N) {
        	if(st == null || !st.hasMoreTokens()) {
        		st = new StringTokenizer(br.readLine());
        		continue;
        	}
        	int x =  Integer.parseInt(st.nextToken());
        	if(isPrime(x))cnt++;
        	read++;
        }
        
        System.out.println(cnt);
    }
}