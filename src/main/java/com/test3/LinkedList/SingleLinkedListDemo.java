package com.test3.LinkedList;

import java.util.Stack;

/**
 * 有头单向链表
 * 有顺序/无顺序的添加数据
 * @author YJ Lan
 * @create 2020-02-02-20:58
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode n1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode h2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode h3 = new HeroNode(3, "晁盖", "晁天王");
        HeroNode h4 = new HeroNode(4, "武松", "行者");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //无顺序
//        singleLinkedList.add(h3);
//        singleLinkedList.add(n1);
//        singleLinkedList.add(h4);
//        singleLinkedList.add(h2);
//        singleLinkedList.list();

        //有顺序
//        singleLinkedList.addS(h3);
//        singleLinkedList.addS(n1);
//        singleLinkedList.addS(h4);
//        singleLinkedList.addS(h2);
//        singleLinkedList.list();

        //移除链表
//        singleLinkedList.remove(h2);
        //更改链表节点信息
//        singleLinkedList.update(new HeroNode(4, "武松1", "行者"));
//        singleLinkedList.list();
        //获取有效长度
//        System.out.println(singleLinkedList.getSize());
        //获取倒数第K个节点
//        System.out.println(singleLinkedList.getLastIndex(3));

        //反转
//        System.out.println("当前链表");
//        singleLinkedList.list();
//        SingleLinkedListDemo.reversetList(singleLinkedList.head);
//        System.out.println("反转后");
//        singleLinkedList.list();

        //反向打印
//        SingleLinkedListDemo.reverList(singleLinkedList.head);

        HeroNode k1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode k2 = new HeroNode(6, "卢俊义", "玉麒麟");
        HeroNode k3 = new HeroNode(7, "晁盖", "晁天王");
        HeroNode k4 = new HeroNode(8, "武松", "行者");
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addS(k3);
        singleLinkedList.addS(k1);
        singleLinkedList1.addS(k4);
        singleLinkedList1.addS(k2);

        singleLinkedList1.addS(h3);
        singleLinkedList1.addS(n1);
        singleLinkedList1.addS(h4);
        singleLinkedList.addS(h2);

        System.out.println("-------");
        SingleLinkedList list = mergeList(singleLinkedList.head, singleLinkedList1.head);
        list.list();
    }


    /*
        合并两个有序单向列表
     */
    public static SingleLinkedList mergeList(HeroNode head1, HeroNode head2){

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HeroNode head = singleLinkedList.head;
        HeroNode temp1 = head1.next;
        HeroNode temp2 = head2.next;
        HeroNode newTemp = head;

        while (true) {
            //如果head到尾部
            if (temp1 == null) {
                //添加temp2到新链表
                newTemp.next = temp2;
                break;
            }
            if (temp2 == null) {
                newTemp.next = temp1;
                break;
            }
            if (temp1.no > temp2.no) {
                if (newTemp.no > temp1.no) {
                    newTemp.next = temp1;
                    newTemp = newTemp.next;
                    temp1 = temp1.next;
                } else if(newTemp.no < temp2.no){
                    newTemp.next = temp2;
                    newTemp = newTemp.next;
                    temp2 = temp2.next;
                } else if (newTemp.no == temp1.no) {
                    temp1 = temp1.next;
                } else if (newTemp.no == temp2.no) {
                    temp2 = temp2.next;
                }
            } else if(temp1.no < temp2.no) {
                if (newTemp.no > temp2.no) {
                    newTemp.next = temp2;
                    newTemp = newTemp.next;
                    temp2 = temp2.next;
                } else if(newTemp.no < temp1.no){
                    newTemp.next = temp1;
                    newTemp = newTemp.next;
                    temp1 = temp1.next;
                } else if (newTemp.no == temp1.no) {
                    temp1 = temp1.next;
                } else if (newTemp.no == temp2.no) {
                    temp2 = temp2.next;
                }
            } else {
                //相等
                newTemp.next = temp1;
                newTemp = newTemp.next;
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
        }

        return singleLinkedList;
    }
    /*
        反向打印
     */
    public static void reverList(HeroNode head){
        //有无节点
        if (head.next == null) {
            return;
        }
        HeroNode temp = head.next;
        //压入寨中
        Stack<HeroNode> stack = new Stack<>();
        while (true) {
            if (temp == null){
                break;
            }
            stack.add(temp);
            temp = temp.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    /*
        反转单向链表
     */
    public static void reversetList(HeroNode head){
        if (head.next == null || head.next.next == null){
            return;
        }
        //定义前中后三指针
        HeroNode pre = null;
        HeroNode cur = head.next;
        HeroNode next = cur.next;

        while (true){
            cur.next = pre;
            if (next == null){
                head.next = cur;
                break;
            }
            //指针下移
            pre = cur;
            cur = next;
            next = next.next;
        }
    }
}

/*
    不考虑编号顺序的单向链表
 */
class SingleLinkedList{

    //初始化一个头结点，头结点不可变，不存放具体数据
    public  HeroNode head = new HeroNode(0, "", "");

    /*
        查找单链表中倒数第K个节点
     */
    public HeroNode getLastIndex(int k){
        //判空
        if (getSize()<k){
            return null;
        }

        HeroNode temp = head;
        HeroNode first = head;
        int index = 0;
        while (index < k){
            first = first.next;
            index++;
        }
        while (first != null){
            first = first.next;
            temp = temp.next;
        }
        return temp;
    }

    /*
       求单链表中有效节点的个数
    */
    public  int getSize(){
        if (head.next == null){
            return 0;
        }
        HeroNode temp = head.next;
        int size = 0;
        while (temp != null){
            temp = temp.next;
            size++;
        }
        return size;
    }

    public void remove(HeroNode node){

        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("删除失败，未找到" +node);
        }

    }

    //修改节点
    public void update(HeroNode newNode){

        //定义指针
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.nickName = newNode.nickName;
            temp.name = newNode.name;
        } else {
            System.out.println("未找到该元素"+newNode);
        }
    }

    //添加数据到单向链表
    //不考虑编号顺序
    //1.找到最后一个节点
    //2.将最后这个节点的next指向新节点
    public void add(HeroNode node){

        //定义一个指针
        HeroNode temp = head;
        //找到最后的节点
        while (true) {
            //是否是最后一个节点
            if (temp.next == null){
                break;
            }
            //如果不是最后一个节点，那么指针向后移动一位
            temp = temp.next;
        }
        //把node添加到尾节点后面
        temp.next = node;
    }

    //添加数据到单向链表
    //考虑编号顺序
    //1.第一个元素直接添加
    //2.如果到尾部还找不到合适位置，直接添加
    //3.找到合适的节点添加
    //4.将最后这个节点的next指向新节点
    public void addS(HeroNode node){

        //定义一个指针
        HeroNode temp = head;
        //找到最后的节点
        while (true) {
            //如果是尾节点，直接添加
            if (temp.next == null){
                temp.next = node;
                break;
            }
            //是否是合适节点
            if (temp.next.no > node.no){
//                HeroNode n = temp.next;
//                temp.next = node;
//                node.next = n;
                node.next = temp.next;
                temp.next = node;
                break;
            }else if(temp.next.no == node.no){
                System.out.println(node+"已经存在");
                break;
            }
            //如果不是合适一个节点，那么指针向后移动一位
            temp = temp.next;
        }

    }

    //显示链表所有数据
    public void list(){

        //定义一个指针
        HeroNode temp = head.next;
        //遍历
        while (true){
            //指针指到末尾，退出
            if (temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;

        }
    }
}


class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + ", nickName='" + nickName + '}';
    }
}