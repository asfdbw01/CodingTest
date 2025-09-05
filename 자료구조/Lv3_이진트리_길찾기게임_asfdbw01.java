/*
문제: 길 찾기 게임
----------------------------------------
- 각 장소를 (x, y) 정수 좌표로 주고, 이 규칙을 만족하는 이진트리를 만든다:
  1) 모든 노드는 서로 다른 x
  2) 같은 레벨의 y는 동일, 자식의 y < 부모의 y
  3) 노드 V의 왼쪽 서브트리는 x < V.x, 오른쪽은 x > V.x
- 트리를 전위(preorder)와 후위(postorder)로 순회한 노드 번호 배열을 반환.

입력
- nodeinfo[i] = [x, y] (i+1번 노드의 좌표), 1 ≤ N ≤ 10,000, 0 ≤ x,y ≤ 100,000
- 트리의 깊이 ≤ 1000 (재귀 안전)

출력
- int[2][N]:
  - [0] = 전위 순회 결과
  - [1] = 후위 순회 결과

*/

import java.util.*;

class Solution {
    public class Node {
        int x;
        int y;
        int value;
        Node left;
        Node right;
        
        public Node (int x,int y, int value,Node left,Node right){
            this.x = x;
            this.y = y;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        int len = nodeinfo.length;
        Node[] node = new Node[len];
        for(int i=0;i<len;i++){
            node[i] = new Node(nodeinfo[i][0],nodeinfo[i][1],i+1,null,null);
        }
        
        //노드 정렬
        Arrays.sort(node,new Comparator<Node>(){
            @Override
            public int compare(Node n1,Node n2){
                if(n1.y==n2.y)return n1.x - n2.x;
                else return n2.y - n1.y;
            }
        });
        
        //트리 만들기
        Node root = node[0];
        for(int i=1;i<node.length;i++){
            insertNode(root,node[i]);
        }
        
        //순회
        int [][] result = new int[2][len];
        int[] idx = new int[1];
        idx[0] = 0;
        preOrder(root,idx,result);
        idx[0] = 0;
        postOrder(root,idx,result);
        return result;
    }
    
    public void insertNode(Node p, Node c){
        if(p.x >c.x){
            if(p.left==null)p.left = c;
            else insertNode(p.left,c);
        }else{
            if(p.right == null)p.right = c;
            else insertNode(p.right,c);
        }
    }
    
    private void preOrder(Node root,int[] idx,int [][] result){
        if(root!=null){
            result[0][idx[0]++] = root.value;
            preOrder(root.left,idx,result);
            preOrder(root.right,idx,result);
        }
    }
    
    private void postOrder(Node root,int[] idx,int [][] result){
        if(root!=null){
            postOrder(root.left,idx,result);
            postOrder(root.right,idx,result);
            result[1][idx[0]++] =  root.value;
        }
    }
}
