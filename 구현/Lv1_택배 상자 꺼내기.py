#1 ~ n의 번호가 있는 택배 상자가 창고에 있습니다. 당신은 택배 상자들을 다음과 같이 정리했습니다.
#왼쪽에서 오른쪽으로 가면서 1번 상자부터 번호 순서대로 택배 상자를 한 개씩 놓습니다.

#가로로 택배 상자를 w개 놓았다면 이번에는 오른쪽에서 왼쪽으로 가면서 그 위층에 택배 상자를 한 개씩 놓습니다. 
#그 층에 상자를 w개 놓아 가장 왼쪽으로 돌아왔다면 또다시 왼쪽에서 오른쪽으로 가면서 그 위층에 상자를 놓습니다.
#이러한 방식으로 n개의 택배 상자를 모두 놓을 때까지 한 층에 w개씩 상자를 쌓습니다.

#다음 날 손님은 자신의 택배를 찾으러 창고에 왔습니다.
#당신은 손님이 자신의 택배 상자 번호를 말하면 해당 택배 상자를 꺼내줍니다. 
#택배 상자 A를 꺼내려면 먼저 A 위에 있는 다른 모든 상자를 꺼내야 A를 꺼낼 수 있습니다. 
#예를 들어, 위 그림에서 8번 상자를 꺼내려면 먼저 20번, 17번 상자를 꺼내야 합니다.
#
#당신은 꺼내려는 상자 번호가 주어졌을 때, 꺼내려는 상자를 포함해 총 몇 개의 택배 상자를 꺼내야 하는지 알고 싶습니다.
#창고에 있는 택배 상자의 개수를 나타내는 정수 n, 
#가로로 놓는 상자의 개수를 나타내는 정수 w와 꺼내려는 택배 상자의 번호를 나타내는 정수 num이 매개변수로 주어집니다.
#이때, 꺼내야 하는 상자의 총개수를 return 하도록 solution 함수를 완성해 주세요.
# 입출력 예
# n	w	num	result
# 22	6	8	3
# 13	3	6	4

def clear(cols, rows):
    arr  = [[0] * rows for _ in range(cols)]
    return arr

def load(n, bottom, rows, arr):
    box_num = 1
    while box_num <= n:
        for i in range(bottom, -1, -1):
            if box_num > n:
                break
            if (bottom - i) % 2 == 0:
                for j in range(rows):
                    if box_num > n:
                        break
                    arr[i][j] = box_num
                    box_num += 1
            else:
                for j in range(rows-1, -1, -1):
                    if box_num > n:
                        break
                    arr[i][j] = box_num
                    box_num += 1
    return arr

def finder(arr, rows, cols, tg):
    for i in range(cols):
        for j in range(rows):
            if arr[i][j] == tg:
                return [i, j]
    return [-1, -1]  

def extract(a, b, arr):
    cnt = a + 1
    for i in range(a):
        if arr[i][b] == 0:
            cnt -= 1
    return cnt

def solution(n, w, num):
    rows = w
    cols = (n + w - 1) // w 
    bottom = cols - 1

    arr = clear(cols, rows)
    arr = load(n, bottom, rows, arr)
    target = finder(arr, rows, cols, num)
    number = extract(target[0], target[1], arr)
    return number
