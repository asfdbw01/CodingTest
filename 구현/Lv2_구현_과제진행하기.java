/*
 * ✅ 문제 요약
 * - 각 과제는 정해진 시작 시간에 무조건 시작됨
 * - 과제 수행 도중 새 과제를 시작하면 진행 중인 과제는 중단됨
 * - 과제를 끝낸 후에는, 중단된 과제를 최근에 중단된 순서대로 이어서 수행
 * - 과제를 완료한 순서대로 과제 이름을 배열에 담아 반환

 * ✅ 입력 형식
 * - plans: [과제이름, 시작시간("hh:mm"), 소요시간(분)]의 2차원 문자열 배열
 * - 과제 수: 3 ≤ plans.length ≤ 1000

 * ✅ 출력 형식
 * - String[] : 과제 완료 순서대로 이름을 담은 배열

 * ✅ 핵심 로직
 * 1. 시작 시간을 기준으로 plans를 정렬
 * 2. currentTime = 과제 시작 시간 기준으로 시뮬레이션
 * 3. 새 과제를 시작할 때 진행 중인 과제가 있으면 Stack에 저장
 * 4. 과제를 끝낸 후에는 Stack에서 가장 최근 과제를 꺼내 이어서 수행
 * 5. 과제 완료 시 이름을 answerList에 저장
 * 6. 모든 과제를 처리한 후 answerList를 배열로 반환

 * ✅ 자료구조
 * - Stack: 중단된 과제 관리
 * - List<String>: 완료된 과제 순서 기록

 * ✅ 분류
 * - 구현 / 시뮬레이션 / Stack
 */


import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = {};
        //구조체 리스트 생성
        List<Subject> subjects = new ArrayList<>();
        makeSubjectList(subjects,plans);
        //구조체 리스트 정렬
        Collections.sort(subjects,Comparator.comparingInt(s -> s.startTime));
        //결과 리스트 
        List<String> answerList = new ArrayList<>();
        //남은과목 스택
        Stack<Subject> stack = new Stack<>();
        //나머지 
        doHomework(subjects,answerList, stack );
        //answerList 배열로 반환
        answer = answerList.toArray(new String[0]);
        return answer;
    }
    
    class Subject{
        String subjectName;
        int startTime; 
        int remainTime;
        
        public Subject(String subjectName,int startTime, int remainTime){
            this.subjectName = subjectName;
            this.startTime = startTime;
            this.remainTime = remainTime;
        }
    }
    
    private void makeSubjectList(List<Subject> subjects,String[][] plans){
        for(int i=0;i<plans.length;i++){
            String[] startTime = plans[i][1].split(":");
            int hour = Integer.parseInt(startTime[0]);
            int minute = Integer.parseInt(startTime[1]);
            subjects.add(new Subject(plans[i][0],
                       hour*60+minute,
                       Integer.parseInt(plans[i][2])));
        }
    }
    
    private void doHomework(List<Subject> subjects,List<String> answerList, Stack<Subject> stack ){
        for(int i=0; i<subjects.size()-1;i++){
            Subject current = subjects.get(i);
            Subject next = subjects.get(i+1);
            int avalibleTime = next.startTime - current.startTime;
            
            //시간이 남는 경우
            if(avalibleTime >= current.remainTime){
                answerList.add(current.subjectName);
                avalibleTime -= current.remainTime;
                
                //남는시간중 못끝낸거 처리
                while(avalibleTime >0 && !stack.isEmpty()){
                    Subject paused = stack.pop();
                    //남는거 하나 완료
                    if(paused.remainTime<=avalibleTime){
                        answerList.add(paused.subjectName);
                        avalibleTime -= paused.remainTime;
                    }
                    //남는거 완료 못함
                    else {
                        paused.remainTime -= avalibleTime;
                        stack.push(paused);
                        break;
                    }
                }
            }
            //시간 부족
            else {
                current.remainTime -= avalibleTime;
                stack.push(current);
            }
            
        }
        //마지막 과목은 무조건 완료
        answerList.add(subjects.get(subjects.size()-1).subjectName);
        
        //남은 과목 처리
        while(!stack.isEmpty()){
            answerList.add(stack.pop().subjectName);
        }
    }
}
