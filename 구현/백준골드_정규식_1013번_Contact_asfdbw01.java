/*
푸에르토리코 아레시보에 위치한 아레시보 전파망원경(Arecibo radio telescope)은 
수십 년째 존재하지 않을 지도 모르는 외계 문명으로부터의 
전파를 수신하기 위해 밤하늘을 바라보고 있다.

이 망원경이 수집한 전파 속에서 자연적으로 발생하기 힘든 패턴들을 찾아내어, 
그것을 증거로 외계 문명의 존재 여부를 가리려는 노력은 줄곧 이어져왔지만 
아직까지도 그러한 패턴은 발견되지 않았다. 
한국 천문학계의 자존심 김동혁 박사는 국내 기술로 이러한 탐사를 진행하기 위하여 
다음의 전파 표기를 표준으로 삼았다.

전파의 기본 단위는 { 0 , 1 } 두 가지로 구성되어있으며, 
x+ ( ) 는 임의의 개수(최소 1개) x의 반복으로 이루어진 전파의 집합을 나타낸다.

(xyx)+ ( ) 는 괄호 내의 xyx의 반복으로 이루어진 전파의 집합을 뜻한다. 
아래는 이해를 돕기 위한 예제이다.

1+ = { 1, 11, 111, 1111, 11111, … }
10+ = { 10, 100, 1000, 10000, 100000, … }
(01)+ = { 01, 0101, 010101, 01010101, 0101010101, … }
(1001)+ = { 1001, 10011001, 100110011001, … }
10+11 = { 1011, 10011, 100011, 1000011, 10000011, … }
(10+1)+ = { 101, 1001, 10001, 1011001, 1001101, 100011011000001, … }
반복을 의미하는 + 외에도 
or 를 의미하는 | 기호가 있다. 
{ x | y } 는 x 혹은 y 를 의미하는 것으로,
 { 0+ | 1+ } 는 { 0 , 1 , 00 , 11 , 000 , 111 , … } 의 집합을 의미한다. 
아래는 두 기호를 복합적으로 사용한 예이다.

(100 | 11)+ = { 100 , 11 , 10011 , 11100 , 1110011100 , 100111111100100, … }
최근 김동혁 박사는 아레시보 전파망원경에서 
star Vega(직녀성) 으로부터 수신한 전파 기록의 일부를 조사하여 
그 전파들의 패턴을 분석하여 아래와 같이 기록하였다.

(100+1+ | 01)+
김동혁 박사는 다양한 전파 기록 중에서 
위의 패턴을 지니는 전파를 가려내는 프로그램을 필요로 한다. 
이를 수행할 수 있는 프로그램을 작성하라.

입력
입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다. 
그 다음 줄부터 각각의 테스트 케이스에 대해 전파를 표현하는, 
{ 0, 1 }만으로 이루어진 문자열이 공백 없이 주어진다. 
문자열 길이는 (1 ≤ N ≤ 200)의 범위를 갖는다.
*/

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

class Main {
    private static final Pattern P = Pattern.compile("^(100+1+|01)+$");
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int read = 0;
        StringBuilder sb = new StringBuilder();
        while(read<N){
            st = new StringTokenizer(br.readLine());
            String signal = st.nextToken();
            Matcher M = P.matcher(signal);
            String ans = (M.matches())?"YES":"NO";
            
            sb.append(ans+"\n");
            read++;
        }

        System.out.println(sb.toString());
    }

}