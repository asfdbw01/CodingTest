# 지나다니는 길을 O, 장애물을 x
# 주어지는 명령 형식 ["방향 거리","방향 거리"]

# 예를 들어 "E 5" 는 로봇강아지가 현재위치에서 동쪽으로 5칸 이동했다는 의미
# 로봇강아지는 명령 수행전 다음 두 가지를 먼저 확인합니다
# 1. 주어진 방향으로 이동할때 공원을 벗어나는지 확인합니다
# 2. 주어진 방향으로 이동 중 장애물을 만나는지 확인합니다
# 위 두가지중 어느 하나라도 해당 된다면, 로봇강아지는 해당 명령을 무시하고 다음 명령수행

# 공원 가로길이가 W, 세로 길이가 H 일때, 공원 좌측 상단의 좌표는 0,0
# 우측 하단 좌표는 ( H-1, W-1) 입니다
# 공원을 나타내는 문자열 배열 park
# 로봇강아지가 수행할 명령이 담긴 무자열 배열 routes가 매개변수
# 로봇강아지가 모든 명령을 수행 후 놓인 위치를 [세로방향 좌표, 가로방향 좌표] 순으로 배열에 담아 return

# park 는 S;시작, o: 이동가능 통로, x: 장애물 로 주어짐, 시작지점은 하나만

# 설계
# 루트 하나 가져와서 방향가져오고, 스페이스바 제거한다음 슬라이싱으로 거리 가져오기
# for 문 돌려서 S 찾기기

def solution(park, routes):
    start=[0,0]
    tmp=[0,0]
    for h,value in enumerate(park):
        for w ,v in enumerate(value):
            if v =="S":
                start[0]=h
                start[1]=w

    for route in routes:

        way, mov = route.split()
        mov= int(mov)

        tmp=start.copy() 

        for _ in range(mov):
            if way =="N":
                tmp[0]-=1
            elif way =="S":
                tmp[0]+=1
            elif way == "W":
                tmp[1]-=1
            elif way == "E":
                tmp[1]+=1
    
            valid=True

            if(tmp[0]>=len(park) or tmp[0]<0 or
                tmp[1]>=len(park[0]) or tmp[1]<0 or
                park[tmp[0]][tmp[1]]=="X"):
                valid = False
                break
        if valid:
            start=tmp
        
    return start
