# 두 정수 left와 right가 매개변수로 주어집니다.
# left부터 right까지의 모든 수들 중에서, 약수의 개수가 짝수인 수는 더하고, 
# 약수의 개수가 홀수인 수는 뺀 수를 return 하도록 solution 함수를 완성해주세요.

# 약수의 갯수는 제곱수의 의하여 홀/짝 나뉨
def solution(left, right):
    answer = 0
    for i in range(left, right+1):
        if int(i ** 0.5) ** 2 == i:
            answer -= i
        else:
            answer += i
    return answer
