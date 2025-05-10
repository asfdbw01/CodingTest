# 사진을 보며 점수를 매길려함
# 사진속에 나오는 인물의 그리움 점수를 모두 합산한 값이 해당 사진의 추억점수가 됨
# 예를 들어 사진속 인물의 이름이 may, kein, kain 
# 그리움 점수가 5,10,1 이면 해당사진 추억점수가 16입니다
# 다른 사진 속 인물의 이름이 kali, mari. don, tony 이고 
# kali mari don 의 그리움 점수가 각각 11,1,55 이고 tony 의 그리움 점수가 없을때
# 이 사진의 추억점수는 3명의 그리움 점수를 합한 67점이다.

#그리워 하는 사람의 이름을 담은 문자열 name, 각 사람별 그리움 점수를 담은 yearning
#각 사진에 찍힌 인물의 이름을 담은 photo, 추억점수를 photo에 주어진 순서대로 배열에 담아 return 하는 함수

def solution(name, yearning, photo):
    
    dic= {name : y for name, y in zip(name,yearning)}
    answer = []
    save=0
    for photos in photo:
        for person in photos:
            if person in dic:
                save +=dic[person]
        answer.append(save)
        save=0
    return answer
