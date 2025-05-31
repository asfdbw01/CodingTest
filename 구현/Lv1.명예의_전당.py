# 문제: 명예의 전당 (프로그램 문제 설명)
#
# "명예의 전당"이라는 TV 프로그램에서는 매일 1명의 가수가 노래를 부릅니다.
# 시청자들은 문자 투표로 가수에게 점수를 부여합니다.
#
# - 매일 출연한 가수의 점수가 지금까지 출연 가수 중 상위 k번째 이내이면
#   해당 점수를 "명예의 전당"이라는 목록에 올립니다.
#
# - 프로그램 시작 후 처음 k일까지는 모든 가수의 점수가 자동으로 명예의 전당에 올라갑니다.
#
# - k일 이후부터는,
#   현재 가수의 점수가 명예의 전당 목록에서 최하위 점수(즉, k번째 점수)보다 높으면
#   해당 점수는 명예의 전당에 들어가고,
#   기존 최하위 점수는 명예의 전당에서 내려옵니다.
#
# 이 프로그램은 매일 "명예의 전당"의 최하위 점수를 발표합니다.
#
# 예시:
# k = 3
# score = [10, 100, 20, 150, 1, 100, 200]
#
# 명예의 전당 발표 점수: [10, 10, 10, 20, 20, 100, 100]
#
# 입력:
# - k: 명예의 전당에 올라갈 수 있는 점수의 최대 개수 (정수)
# - score: 출연한 가수들의 점수가 담긴 리스트
#
# 출력:
# - 매일 발표된 명예의 전당의 최하위 점수를 리스트로 반환

from itertools import zip_longest

def solution(k, score):
    
    db = [0]*(k+1)
    answer = []
    
    if k<len(score):
        for i,j in zip_longest(score,range(k),fillvalue=k-1):
            db[k]=i
            db.sort(reverse=True)
            db[k]=0
            answer.append(db[j])
        db.pop()
    else:
        for i,j in zip(score,range(k)):
            db[k]=i
            db.sort(reverse=True)
            db[k]=0
            answer.append(db[j])
        for _ in range(len(score)-k):
            answer.pop()

    return answer
