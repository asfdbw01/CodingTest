# 문제 설명:
# 모든 명함을 수납할 수 있는 가장 작은 지갑 크기를 구하라.
# 명함은 회전이 가능하므로, 각 명함은 긴 쪽을 가로로 눕힌다고 가정한다.
# 각 명함을 [w, h]라 할 때, max(w, h)는 가로로, min(w, h)는 세로로 본다.
# 이후 모든 명함 중 최대 가로, 최대 세로를 곱하면 최소 지갑 크기다.

def solution(sizes):

    widths = []
    heights = []

    for w, h in sizes:
 
        width = max(w, h)
        height = min(w, h)

        widths.append(width)
        heights.append(height)

    return max(widths) * max(heights)
