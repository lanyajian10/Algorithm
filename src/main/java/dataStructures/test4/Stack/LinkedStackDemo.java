package dataStructures.test4.Stack;

import java.util.Scanner;

/**
 * 链表模拟堆栈
 * @author YJ Lan
 * @create 2020-02-10-19:32
 */
public class LinkedStackDemo {

    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        Scanner scanner = new Scanner(System.in);
        String key = " ";
        boolean flag = true;
        while (flag) {
            System.out.println("输入e：是否为空");
            System.out.println("输入s：大小");
            System.out.println("输入a：添加");
            System.out.println("输入p：查看栈顶元素");
            System.out.println("输入pop：出元素");
            System.out.println("输入l：元素列表");
            key = scanner.next();
            switch (key) {
                case "e" :
                    System.out.println(stack.isEmpty());
                    break;
                case "s":
                    System.out.println(stack.size());
                    break;
                case "a":
                    System.out.println("请输入添加元素");
                    stack.add(scanner.nextInt());
                    break;
                case "pop":
                    System.out.println(stack.pop());
                    break;
                case "p":
                    System.out.println(stack.peek());
                    break;
                case "l":
                    stack.list();
                    break;
                default:
                    flag = false;
                    break;
            }

        }
    }
}

class LinkedStack{

    private int maxSize;
    private Node head = new Node(0);

    //是否空
    public boolean isEmpty(){
        return head.next == null;
    }

    //长度
    public int size(){
        if (isEmpty()) {
            return 0;
        }
        Node temp = head.next;
        int count = 0;
        while (temp != null){
            count++;
            temp = temp.next;
        }
        return count;
    }

    //入栈
    public void add(int i){
        Node newNode = new Node(i);
        newNode.next = head.next;
        head.next = newNode;
    }

    //出栈
    public Node pop(){
        if (isEmpty()) throw new RuntimeException("无元素");
        Node temp = head.next;
        head.next = head.next.next;
        return temp;
    }

    //显示栈顶元素信息
    public Node peek(){
        if (isEmpty()) throw new RuntimeException("无元素");
        return head.next;
    }

    //遍历
    public void list(){
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }
        Node temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }

    }

}


class Node {
    private int no;
    public Node next;

    public Node(int i){
        this.no = i;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }
}
