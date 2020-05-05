package Algorithm.test2.ArrAsQueue;

import java.util.Scanner;

/**
 * 数据模拟环形队列
 * @author YJ Lan
 * @create 2020-02-02-00:57
 */
public class CricleArrQueueDemo2 {
    public static void main(String[] args) {

        CricleArrQueue cricleArrQueue = new CricleArrQueue(5);//真正大小为4
        Scanner scanner = new Scanner(System.in);
        char key = ' ';
        boolean flag = true;
        while (flag) {
            System.out.println("查看队列是否有元素输入：e");
            System.out.println("查看队列是否满了输入：f");
            System.out.println("向数据中添加数据输入：a");
            System.out.println("向数据中获取数据输入：g");
            System.out.println("显示数组数据：l");
            System.out.println("显示数据头数据：h");
            System.out.println("输入q，退出程序");
            key = scanner.next().charAt(0);
            switch (key){
                case 'e':
                    System.out.println(cricleArrQueue.isEmpty());
                    break;
                case 'f':
                    System.out.println(cricleArrQueue.isFull());
                    break;
                case 'a':
                    System.out.println("请输入添加的整数：");
                    cricleArrQueue.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    int index ;
                    try{
                        index = cricleArrQueue.getQueue();
                        System.out.println(index);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'l':
                    cricleArrQueue.list();
                    break;
                case 'h':

                    int index1 ;
                    try{
                        index1 = cricleArrQueue.getHead();
                        System.out.println(index1);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'q':
                    flag = false;
                    break;
                case 's':
                    System.out.println(cricleArrQueue.size());
                    break;
                default:
                    System.out.println("无效，请重新输入");
                    break;
            }

        }

    }
}

class CricleArrQueue{

    // 队列大小
    private int maxSize;
    // 队列头 初始0
    private int font;
    // 队列尾 初始0
    private int rest;
    private int[] arr;

    //有参构造方法
    public CricleArrQueue(int size){
        maxSize = size;
        arr = new int[maxSize];
    }

    //无参构造方法
    public CricleArrQueue(){
        this(10);
    }

    //判空
    public boolean isEmpty(){
        return font == rest;
    }

    //判满
    public boolean isFull(){
        return (rest+1) % maxSize == font;
    }

    //添加
    public void addQueue(int n){
        if (isFull()) {
            System.out.println("满了");
        }else{
            arr[rest] = n;
            rest = (rest+1) % maxSize;
        }
    }

    //取出
    public int getQueue(){
        if (isEmpty()) throw new RuntimeException("空的");

        int curre = arr[font];
        font = (font+1) % maxSize;
        return curre;
    }

    //列表
    public void list(){
        if (isEmpty()) {
            System.out.println("[]");
        }else{
            for (int i = font; i < font + size(); i++){
                System.out.printf("arr[%d]=%d\n",i % maxSize, arr[i%maxSize]);
            }
        }
    }

    //获取长度
    public int size(){
        return  (rest + maxSize- font) % maxSize;
    }

    //获取头
    public int getHead(){
        if (isEmpty()) throw new RuntimeException("空的");
        return arr[font];
    }

}
