package Algorithm.test2.ArrAsQueue;

import java.util.Scanner;

/**
 * 简单的数组模仿队列FIFO （先进先出）
 * 缺点：不能复用，一旦满了就不能使用了。
 * @author YJ Lan
 * @create 2020-02-01-20:46
 */
public class ArrAsQueueDemo1 {

    public static void main(String[] args) {
        ArrQueue arrQueue = new ArrQueue();
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
            switch (key) {
                case 'e':
                    System.out.println(arrQueue.isEmpty());
                    break;
                case 'f':
                    System.out.println(arrQueue.isFull());
                    break;
                case 'a':
                    System.out.println("请输入添加的整数：");
                    arrQueue.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    int index ;
                    try{
                        index = arrQueue.getQueue();
                        System.out.println(index);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'l':
                    arrQueue.showList();
                    break;
                case 'h':
                    arrQueue.showHead();
                    break;
                case 'q':
                    flag = false;
                    break;
                default:
                    System.out.println("无效，请重新输入");
                    break;
            }
        }
    }

}

class ArrQueue{
    private int maxSize ; //队列最大容量
    private int font;//队列头
    private int rear;//队列尾
    private int[] arr; //队列存储数据

    //设置给定大小的队列
    public ArrQueue(int size){
        this.maxSize = size;
        arr = new int[maxSize];
        this.font = -1;//指向队列头部  因为数据第一个元素是0，故空为-1
        this.rear = -1;//队列尾部  理由同上
    }
    //创建默认大小为10的队列
    public ArrQueue(){
        this(10);
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return font == rear;
    }

    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize-1 ;
    }

    //添加数据到队列
    public void addQueue(int n){
        if (isFull()) {
            System.out.println("队列已满，不能添加数据");
            return;
        }
        arr[++rear] = n;
    }

    //获取对列数据，出队列
    public int getQueue(){
        if (isEmpty()) throw new RuntimeException("无数据");
        return arr[++font];
    }

    //显示队列所有数据
    public void showList(){
        if (isEmpty()) System.out.println("[]");
        for (int i = font; i < rear; i++){
            System.out.println(arr[i+1]);
        }
    }

    //显示队列的头数据，并非取出数据
    public void showHead(){
        if (isEmpty()) {
            System.out.println("无数据");
            return;
        }
        System.out.println(arr[font+1]);
    }
}