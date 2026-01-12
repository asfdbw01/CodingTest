/*
문제
666은 종말을 나타내는 수라고 한다. 따라서, 
많은 블록버스터 영화에서는 666이 들어간 제목을 많이 사용한다. 
영화감독 숌은 세상의 종말 이라는 시리즈 영화의 감독이다. 
조지 루카스는 스타워즈를 만들 때, 스타워즈 1, 스타워즈 2, 스타워즈 3, 스타워즈 4, 
스타워즈 5, 스타워즈 6과 같이 이름을 지었고, 
피터 잭슨은 반지의 제왕을 만들 때, 반지의 제왕 1, 반지의 제왕 2, 반지의 제왕 3과 같이 
영화 제목을 지었다. 하지만 숌은 자신이 조지 루카스와 피터 잭슨을 뛰어넘는다는 것을 
보여주기 위해서 영화 제목을 좀 다르게 만들기로 했다.

종말의 수란 어떤 수에 6이 적어도 3개 이상 연속으로 들어가는 수를 말한다. 
제일 작은 종말의 수는 666이고, 그 다음으로 큰 수는 1666, 2666, 3666, .... 이다. 
따라서, 숌은 첫 번째 영화의 제목은 
"세상의 종말 666", 두 번째 영화의 제목은 "세상의 종말 1666"와 같이 이름을 지을 것이다. 
일반화해서 생각하면, N번째 영화의 제목은 세상의 종말 (N번째로 작은 종말의 수) 와 같다.

숌이 만든 N번째 영화의 제목에 들어간 수를 출력하는 프로그램을 작성하시오. 
숌은 이 시리즈를 항상 차례대로 만들고, 다른 영화는 만들지 않는다.

입력
첫째 줄에 N이 주어진다. N은 10,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 N번째 영화의 제목에 들어간 수를 출력한다.
*/

import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int cnt=0;
        long num =666;
        while (true) {
            if (sixsixsix(num)) {
                cnt++;
                if (cnt == N) break;
            }
            num++;
        }
        System.out.println(num);
    }

    private static boolean sixsixsix(long num){
        int sequence = 0;

        while (num > 0) {
            long mod = num % 10;

            if (mod == 6) sequence++;
            else sequence = 0;

            if (sequence >= 3) return true;

            num /= 10;
        }
        return false;
    }

}