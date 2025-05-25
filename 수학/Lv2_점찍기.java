/*
문제 요약 📘
- 두 양의 정수 k, d가 주어졌을 때,
- (x, y) = (a×k, b×k) 형태의 점들 중에서,
- 원점 (0,0)으로부터 거리가 d 이하인 모든 점의 개수를 구하라

조건 📐
- x = a*k, y = b*k (단, a, b는 0 이상의 정수)
- √(x² + y²) ≤ d → x² + y² ≤ d² 인 경우만 점 찍기
- k 단위 격자 위에서 원 안에 있는 격자점 수 구하기 문제

입력 🎯
- k: 격자 간격 (1 ≤ k ≤ 1,000,000)
- d: 거리 제한 (1 ≤ d ≤ 1,000,000)

출력 🧾
- 조건을 만족하는 점의 총 개수 (long 타입)

핵심 로직 💡
- x = 0, k, 2k, ..., dk 범위에서
  → 각 x에 대해 가능한 y 값의 개수를 구함
  → maxY = sqrt(d² - x²)
  → y는 0, k, 2k, ..., maxY 까지 가능
    ⇒ 개수는 (maxY / k) + 1

- 전체 점 수 = ∑_{x=0..d, step=k} ((int)(sqrt(d² - x²) / k) + 1)

예시 ✍️
k = 2, d = 4  
x = 0 → y ≤ 4 → y = 0,2,4 → 3개  
x = 2 → y ≤ sqrt(12) ≈ 3.46 → y = 0,2 → 2개  
x = 4 → y = 0 → 1개  
총합 = 6

*/
class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        for(int i=0;i<=d;i+=k){
            answer += (long)(Math.sqrt((Math.pow(d,2) - Math.pow(i,2)))/k)+1;
        }
        
        return answer;
    }
}
