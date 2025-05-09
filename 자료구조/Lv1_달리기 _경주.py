# 해설진들은 선수들이 자기 바로 앞의 선수 추월할때 
# 추월한 선수의 이름을 부름
# 예를 들어 mumu soe poe 가 순서대로 달리고 있을때
# 해설진이 soe 를 불렀다면 2등인 seo 가 1등인 mumu 를 추월했다는것
# 즉, soe 선수가 1등 mumu 가 2 등으로 바뀜

# 1등부터 등수가 담긴 문자열 배열 Player
# 해설진이 부른 이름을 담은 문자열 배열 callings 

# 경주가 끝났을대 선수들의 이름을 1등부터 등수 순서대로 배열을 담아 return 하는 solution 함수
# 1등인 선수의 이름은 불리지 않습니다

# calling 순서중요 
# 시간복잡도 고려하기 

def solution(players, callings):
    
    player= {name : i for i, name in enumerate(players)}
    
    for calling in callings:
        rank_num = player[calling]
        
        #원래선두
        frontplayer = players[rank_num-1]
        
        # 리스트갱신 
        players[rank_num],players[rank_num-1] = players[rank_num-1],players[rank_num]
        
        # 딕셔너리 갱신 
        player[calling]-=1
        player[frontplayer]+=1
        
    answer = []
    answer= players
    return answer

players = ["mumu", "soe", "poe", "kai", "mine"]
callings = ["kai", "kai", "mine", "mine"]
solution(players,callings)
