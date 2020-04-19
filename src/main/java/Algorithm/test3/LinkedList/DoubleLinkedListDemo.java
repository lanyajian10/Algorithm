package Algorithm.test3.LinkedList;

/**
 * @author YJ Lan
 * @create 2020-02-05-15:00
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNodeD h1 = new HeroNodeD(1, "宋江", "及时雨");
        HeroNodeD h2 = new HeroNodeD(2, "卢俊义", "玉麒麟");
        HeroNodeD h3 = new HeroNodeD(3, "晁盖", "晁天王");
        HeroNodeD h4 = new HeroNodeD(4, "武松", "行者");
        DoubleLinkedList list = new DoubleLinkedList();
//        list.add(h1);list.add(h2);list.add(h3);list.add(h4);
//        System.out.println("原列表");
//        list.list();
//        list.remove(h1);
//        System.out.println("移除"+h1+"后列表");
//        list.list();
//        list.update(new HeroNodeD(2, "卢俊义1", "玉麒麟1"));
//        System.out.println("修改"+h2+"后列表");
//        list.list();


        list.addS(h4);list.addS(h2);list.addS(h1);list.addS(h3);
        list.list();
    }
}


class DoubleLinkedList{

    private HeroNodeD head = new HeroNodeD(0, " ", " ");


    //增
    public void add(HeroNodeD node){

        HeroNodeD temp = head;

        while (true) {
            if (temp.next == null) {
                temp.next = node;
                node.pre = temp;
                break;
            }
            temp = temp.next;
        }
    }

    public void addS(HeroNodeD node){

        HeroNodeD temp = head;

        while (true) {
            if (temp.next == null) {
                temp.next = node;
                node.pre = temp;
                break;
            }
            if (temp.next.no > node.no) {
                node.next = temp.next;
                temp.next.pre = node;
                temp.next = node;
                node.pre = temp;
                break;
            } else if(temp.no == node.no) {
                System.out.println(node+"已存在");
                break;
            }

            temp = temp.next;
        }
    }

    //删
    public  void remove(HeroNodeD node){

        HeroNodeD temp = head.next;

        while (true) {
            if (temp == null){
                System.out.println("没有找到，删除失败");
                break;
            }

            if (temp.no == node.no) {
                node.next.pre = node.pre;
                node.pre.next = node.next;
                break;
            }
            temp = temp.next;
        }
    }

    //改
    public void update(HeroNodeD node){

        HeroNodeD temp = head.next;

        while (true){
            if ( temp == null ){
                System.out.println("没有找到，更改失败");
                break;
            }
            if ( temp.no == node.no ){
                temp.nickName = node.nickName;
                temp.name = node.name;
                break;
            }
            temp = temp.next;
        }
    }

    //list
    public void list(){
        if (head.next == null){
            System.out.println("[]");
            return;
        }

        HeroNodeD temp = head.next;

        while (true) {
            if (temp == null) break;

            System.out.println(temp);
            temp = temp.next;
        }
    }
}


/*
    单链表节点
 */
class HeroNodeD {

    public int no ;
    public String name;
    public String nickName;
    public HeroNodeD next;
    public HeroNodeD pre;

    public HeroNodeD(int no, String name, String nickName){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNodeD{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
