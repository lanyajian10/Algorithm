package Algorithm.test8.HashTable;

import java.util.Scanner;

/**
 * 数组+链表=HashTable
 * @author YJ Lan
 * @create 2020-02-16-23:15
 */
public class ArrAndLinkedToHT {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        HashTable hashTable = new HashTable(7);

        while (true) {
            System.out.println("输入l查看列表：");
            System.out.println("输入a添加元素：");
            System.out.println("输入g添加元素：");
            char key = scanner.next().charAt(0);
            switch (key) {
                case 'l':
                    hashTable.list();
                    break;
                case 'a':
                    System.out.println("请输入编号：");
                    int no = scanner.nextInt();
                    System.out.println("请输入姓名");
                    String name = scanner.next();
                    Node node = new Node(no, name);
                    hashTable.add(node);
                    break;
                case 'g':
                    System.out.println("请输入编号：");
                    int no1 = scanner.nextInt();
                    System.out.println(hashTable.get(no1));
                    break;
                default:
                    break;
            }
        }
    }

}

//数组
class HashTable{
    private NodeLinked[] arr;

    private int size;
    public HashTable(int size){
        this.size = size;
        arr = new NodeLinked[size];
        for (int i=0; i<arr.length; i++) {
            arr[i] = new NodeLinked();
        }
    }

    public void add(Node node){
        int make = make(node.no);
        arr[make].add(node);
    }

    public Node get(int i){
        int make = make(i);
        return arr[make].get(i);
    }

    public void list(){
        for (int i=0; i<size; i++) {
            arr[i].list(i);
        }
    }

    private int make(int no) {
        return no%size;
    }
}

//链表
class NodeLinked {
    private Node head;

    public Node get(int no){

        if (head == null) {
            return null;
        }
        Node temp = head;
        while (temp != null) {
            if (temp.no == no) break;
            temp = temp.next;
        }
        return temp;
    }

    //增
    public void add(Node node){
        if (head == null) {
            head = node;
            return;
        }
        Node temp = head;
        while (head.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    public void list(int n){
        System.out.printf("第%d个数组元素为：",n);
        if (head == null) {
            System.out.println("[]");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.printf(temp.toString());
            temp = temp.next;
        }
        System.out.println();
    }
}

//实体节点
class Node{
    public int no;
    public String name;
    public Node next;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
