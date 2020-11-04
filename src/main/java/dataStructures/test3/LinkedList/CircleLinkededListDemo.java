package dataStructures.test3.LinkedList;

/**
 * 约瑟夫问题
 *
 * 问题描述：
 * 设编号为1.2.3···n的n个人坐一圈，约定编号为k（1<=k<=n） 的人从1开始报数，
 * 数到m的那个人出列，他的下一个人从1开始数，数到m的出列，一次类推，直到出完
 * 为止，由此产生一个序列编号。
 *
 * @author YJ Lan
 * @create 2020-02-08-14:32
 */
public class CircleLinkededListDemo {

    public static void main(String[] args) {
//        CircleLinkedList list = new CircleLinkedList();
//        HeroNode3 k1 = new HeroNode3(1);
//        HeroNode3 k2 = new HeroNode3(2);
//        HeroNode3 k3 = new HeroNode3(3);
//        HeroNode3 k4 = new HeroNode3(4);
//        list.add(k1);
//        list.add(k2);
//        list.add(k3);
//        list.add(k4);
//        prints(2, list);

        Joseph(2, 2, 5);
    }

    /*
     *
     * @param start   在第几个开始
     * @param k       隔几个出圈
     * @param num     环形链表个数
     */
    public static void Joseph(int start, int k, int num){

        if (start > num || start<1) {
            System.out.println("超出限制");
            return;
        }
        if (num<1) {
            System.out.println("个数有误");
            return;
        }

        CircleLinkedList list = new CircleLinkedList();
        for (int i=1; i<= num; i++) {
            list.add(new HeroNode3(i));
        }

        list.link1 = list.findByNumber(start);
        prints(k, list);

    }

    //从第几个开始，弹出
    public static void prints(int k, CircleLinkedList list){
        HeroNode3 find = list.findByNumber(k);
        HeroNode3 temp = find.next;
        list.remove(find);
        System.out.println(find);
        if (list.size()>0){
            list.link1 = temp;
            prints(k, list);
        } else {
            list.link1 = null;
        }
    }

}


class CircleLinkedList{

    public HeroNode3 link1;

    public int size(){
        if ( link1 == null  ) {
            return 0;
        }
        int number = 0;
        HeroNode3 temp = link1;
        while (true){
            number++;
            if ( temp.next == link1 ){
                break;
            }
            temp = temp.next;
        }
        return number;
    }
    //找到第k个人
    public HeroNode3 findByNumber(int k){
        if ( link1 == null  ) {
            System.out.println("里面没有人");
            return null;
        }
        if ( link1.next.no == link1.no ){
            return link1;
        }
        int number = 1;
        HeroNode3 temp = link1;
        while (number < k){
            number++;
            temp = temp.next;
        }
        return temp;
    }

    //添加
    public void add(HeroNode3 node){
        if (link1 == null) {
            link1 = node;
            node.next = node;
        } else {
            HeroNode3 temp = link1;
            while (true) {
                if (temp.next == link1) {
                    temp.next = node;
                    node.next = link1;
                    break;
                }
                temp = temp.next;
            }
        }
    }

    public void remove(HeroNode3 node){
        if (link1 == null){
            return;
        }
        HeroNode3 temp = link1;
        if (node.no == link1.no && link1.next.no == link1.no) {
            link1 = null;
        } else if (node.no == link1.no ) {
            while (true) {
                if (temp.next.no == link1.no) {
                    break;
                }
                temp = temp.next;
            }
            temp.next = link1.next;
            link1 = link1.next;
            return;
        }

        while (true) {
            if (temp.next.no == node.no) {
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }

    }
    //遍历
    public void list(){
        HeroNode3 temp = link1;
        while (true) {
            System.out.println(temp);
            if (temp.next.no == link1.no) {
                break;
            }
            temp = temp.next;
        }
    }



}


class HeroNode3{
    public int no;
    public HeroNode3 next;

    public HeroNode3(int no){
        this.no = no;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + '}';
    }
}
