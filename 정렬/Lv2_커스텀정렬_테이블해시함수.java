/*
문제 요약 - 정수 테이블 해시값 계산

[문제 설명]
- 정수형 테이블이 주어지고, 다음 방식의 해시 함수를 통해 결과값을 계산함

[해시 함수 정의]
1. 정렬 조건:
   - col번째 컬럼 기준 **오름차순 정렬**
   - 만약 같으면 첫 번째 컬럼(기본키) 기준 **내림차순 정렬**

2. S_i 정의 (row_begin ≤ i ≤ row_end):
   - i번째 행의 각 값들을 i로 나눈 나머지들의 합
   - S_i = sum(data[i][j] % i)

3. 최종 해시값:
   - row_begin ~ row_end 구간의 S_i를 모두 XOR 한 값

[입력]
- int[][] data: 테이블 데이터 (행 = 튜플, 열 = 컬럼)
- int col: 정렬 기준이 되는 컬럼 번호 (1-based)
- int row_begin, row_end: 해시 연산 범위 (1-based 인덱스)

[출력]
- int: 해시값 (XOR 결과)

[예시]
- 예: [[2,2,6],[1,5,10],[4,2,9],[3,8,3]], col=2, row_begin=2, row_end=3
- 정렬 결과: [4,2,9], [2,2,6], [1,5,10], [3,8,3]
- S_2 = (2%2)+(2%2)+(6%2) = 0
- S_3 = (1%3)+(5%3)+(10%3) = 4
- Hash = S_2 ^ S_3 = 0 ^ 4 = 4
*/


import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        //정렬
        Arrays.sort(data,(a,b) ->{
                    if(a[col-1]==b[col-1]){
                        return b[0] - a[0];
                    }
                    return a[col-1]-b[col-1];}
                   );
        
        //연산
        for(int i=row_begin;i<=row_end;i++){
            answer ^= colMod(data[i-1],i);
        }
        
        return answer;
    }
    
    private int colMod(int[]data,int mod){
        int result = 0;
        for(int i=0;i<data.length;i++){
            result += data[i]%mod;
        }
        return result;
    }
}
