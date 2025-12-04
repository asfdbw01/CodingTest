/*
비어 있는 배열 A가 있다. 당신은 다음 두 종류의 질의를 총 Q개 처리해야 한다.

1 x: 배열의 가장 뒤에 정수 x를 삽입한다.
2: 현재 배열의 길이를 N이라 하자. 배열을 앞 N/2개의 원소와 뒤 N/2개의 원소 두 부분으로 나눈 후, 
원소들의 합이 더 작은 부분을 배열에서 삭제한다. 만약 두 부분의 합이 같을 경우, 
앞 N/2개의 원소를 삭제한다. 이후 삭제된 부분의 원소의 합을 출력한다. 
이 형식의 질의는 배열의 길이가 2 이상일 때만 주어진다.
모든 질의를 올바르게 처리하고, 그 후 배열 
A에 저장된 모든 원소를 차례대로 출력하는 프로그램을 작성하여라.
*/
import java.io.*;
import java.util.*;

class Main {
    static List<Integer> list = new ArrayList<>();
    static List<Long> prefix = new ArrayList<>();
    static int left = 0, right = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int read = 0;

        prefix.add(0L);

        StringBuilder sb = new StringBuilder();

        while (read < N) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            if (order == 1) {
                int num = Integer.parseInt(st.nextToken());
                orderIsOne(num);
            } else {
                sb.append(orderIsTwo()).append('\n');
            }
            read++;
        }

        for (int i = left; i < right; i++) {
            sb.append(list.get(i));
            if (i + 1 < right) sb.append(' ');
        }
        System.out.print(sb.toString());
    }

    private static void orderIsOne(int num) {
        
        if (right < list.size()) {
            list.set(right, num);
        } else {
            list.add(num);
        }

        
        long newSum = prefix.get(right) + num;
        if (right + 1 < prefix.size()) {
            prefix.set(right + 1, newSum);
        } else {
            prefix.add(newSum);
        }

        right++;
    }

    private static long orderIsTwo() {
        int mid = left + (right - left) / 2;

        long lSum = prefix.get(mid) - prefix.get(left);
        long rSum = prefix.get(right) - prefix.get(mid);

        if (lSum > rSum) {
            
            right = mid;
            return rSum;
        } else {
            
            left = mid;
            return lSum;
        }
    }
}