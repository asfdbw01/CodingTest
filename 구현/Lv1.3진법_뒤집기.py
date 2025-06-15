# 자연수 n이 매개변수로 주어집니다. n을 3진법 상에서 앞뒤로 뒤집은 후, 
# 이를 다시 10진법으로 표현한 수를 return 하도록 solution 함수를 완성해주세요.

def solution(n):
    # q= 몫, r = 나머지 
    a=[]
    while n>0:
        n,r =divmod(n,3)
        a.append(r)
        
    answer = 0
    for i,val in enumerate(reversed(a)):
        answer += val * ( 3** i)
        
    return answer

n = 45

print(solution(n))
