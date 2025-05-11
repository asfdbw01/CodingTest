# 당신은 일렬로 나열된 n개의 집에 택배를 배달하려 합니다. 배달할 물건은 모두 크기가 같은 재활용 택배 상자에 담아 배달하며, 배달을 다니면서 빈 재활용 택배 상자들을 수거하려 합니다. 

#배달할 택배들은 모두 재활용 택배 상자에 담겨서 물류창고에 보관되어 있고

#번째 집은 물류창고에서 거리 i만큼 떨어져 있습니다. 또한 i번째 집은 j번째 집과 거리 j - i만큼 떨어져 있습니다.

#트럭에는 재활용 택배 상자를 최대 cap개 실을 수 있습니다

#트럭은 배달할 재활용 택배 상자들을 실어 물류창고에서 출발해 각 집에 배달하면서, 빈 재활용 택배 상자들을 수거해 물류창고에 내립니다. 

#각 집마다 배달할 재활용 택배 상자의 개수와 수거할 빈 재활용 택배 상자의 개수를 알고 있을 때, 트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리를 구하려 합니다. 

# 집에 배달 및 수거할 때, 원하는 개수만큼 택배를 배달 및 수거할 수 있습니다.

def solution(cap, n, deliveries, pickups):
    answer = 0
    while deliveries or pickups :
        deliver_cap, pickup_cap = cap, cap
        while deliveries and deliveries[-1] == 0 :
            deliveries.pop()
        while pickups and pickups[-1] == 0 :
            pickups.pop()
        answer += max(len(deliveries), len(pickups))*2
        
        while deliveries and deliver_cap > 0:
            box = deliveries.pop()
            if box <= deliver_cap :
                deliver_cap -= box
            else :
                deliveries.append(box - deliver_cap)
                break
        while pickups and pickup_cap > 0 :
            box = pickups.pop()
            if box <= pickup_cap :
                pickup_cap -= box
            else :
                pickups.append(box - pickup_cap)
                break
        
    return answer
