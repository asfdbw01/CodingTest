import java.io.IOException;
import java.util.*;
import java.io.*;

class Main {
    static long[] trees;
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        long maxLen = makeTreeArr(br, N);
        System.out.println(binarySearch(M, maxLen));
    }

    private static long binarySearch(long M,long maxLen){
        long left = 0,right = maxLen;
        long mid=0;
        while (left<=right) {
            mid = left+(right-left)/2;
            boolean longgerThenM = isLonggerThenM(mid, M);
            if (longgerThenM) {
                left = mid+1;
            }
            else right = mid-1;
        }

        return right;
    }

    private static boolean isLonggerThenM(long mid,long M){
        long total=0;
        for(long l : trees){
            if(l>mid)total+=(l-mid);
            if( total>= M)return true;
        }
        return false;
    }

    private static long makeTreeArr(BufferedReader br,int N) throws IOException{
        trees = new long[N];
        long maxLen = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            long tree = Long.parseLong(st.nextToken());
            trees[i] = tree;
            maxLen = maxLen>tree?maxLen:tree;
        }
        return maxLen;
    }
}