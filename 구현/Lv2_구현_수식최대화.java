/**
 * 문제 요약:
 * - 수식 문자열이 주어짐 (숫자 + 연산자 '+', '-', '*')
 * - 연산자의 우선순위를 자유롭게 정할 수 있음 (동순위 불가, 순열)
 * - 우선순위에 따라 수식을 계산했을 때 나올 수 있는 결과들의 절댓값 중 최댓값을 구함
 * - 결과가 음수면 절댓값으로 처리
 *
 * 제한 사항:
 * - 수식 길이: 3 이상 100 이하
 * - 연산자는 최소 1개 포함됨
 * - 피연산자는 0~999 범위 정수이며 음수 없음
 * - 수식은 올바른 중위 표기법으로만 주어짐 (예: "100-200*300+20")
 * - 괄호 없음, 공백 없음
 *
 * 예시:
 * 입력: "100-200*300-500+20"
 * 가능한 우선순위 조합: 3! = 6가지
 * 각 우선순위로 계산한 결과들의 절댓값 중 최댓값: 60420
 */
import java.util.*;

class Solution {
    public long solution(String expression) {
        Set<String> operators = makeOperators(expression);
        List<List<String>> operatorOrders = makeOperatorOrders(operators);

        long max = 0;
        for (List<String> order : operatorOrders) {
            long result = evaluate(expression, order);
            max = Math.max(max, Math.abs(result));
        }

        return max;
    }


    private Set<String> makeOperators(String expression) {
        Set<String> operators = new HashSet<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (!Character.isDigit(ch)) {
                operators.add(String.valueOf(ch));
            }
        }
        return operators;
    }

    private List<List<String>> makeOperatorOrders(Set<String> operators) {
        List<List<String>> orders = new ArrayList<>();
        List<String> operatorList = new ArrayList<>(operators);
        boolean[] visited = new boolean[operatorList.size()];
        backtrack(operatorList, new ArrayList<>(), visited, orders);
        return orders;
    }

    private void backtrack(List<String> operatorList, List<String> currentOrder,
                           boolean[] visited, List<List<String>> result) {
        if (currentOrder.size() == operatorList.size()) {
            result.add(new ArrayList<>(currentOrder));
            return;
        }

        for (int i = 0; i < operatorList.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                currentOrder.add(operatorList.get(i));
                backtrack(operatorList, currentOrder, visited, result);
                currentOrder.remove(currentOrder.size() - 1);
                visited[i] = false;
            }
        }
    }
    private long evaluate(String expression, List<String> precedence) {
    // 1. 수식을 숫자/연산자 리스트로 파싱
        List<String> tokens = new ArrayList<>();
        int idx = 0;
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (!Character.isDigit(ch)) {
                tokens.add(expression.substring(idx, i));  // 숫자
                tokens.add(String.valueOf(ch));            // 연산자
                idx = i + 1;
            }
        }
        tokens.add(expression.substring(idx)); // 마지막 숫자

        // 2. 우선순위대로 계산 반복
        for (String op : precedence) {
            List<String> temp = new ArrayList<>();
            int i = 0;
            while (i < tokens.size()) {
                String token = tokens.get(i);
                if (token.equals(op)) {
                    long left = Long.parseLong(temp.remove(temp.size() - 1));
                    long right = Long.parseLong(tokens.get(i + 1));
                    long res = calc(left, right, op);
                    temp.add(String.valueOf(res));
                    i += 2; // 연산자와 오른쪽 피연산자 skip
                } else {
                    temp.add(token);
                    i++;
                }
            }
            tokens = temp; // 다음 우선순위 연산자 처리용
        }

        return Long.parseLong(tokens.get(0));
    }

    private long calc(long a, long b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
        }
        throw new IllegalArgumentException("Invalid operator: " + op);
    }


}
