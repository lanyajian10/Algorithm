package Algorithm.test4.Stack;

import java.util.Scanner;

/**
 * 数组模拟堆栈
 *
 * 模拟简单的计算器
 * @author YJ Lan
 * @create 2020-02-10-19:32
 */
public class ArrStackDemo {

    public static void main(String[] args) {
//        checkArrStack();
        cals();

    }
    /*
        验证 数组模拟堆栈代码
     */
    private static void checkArrStack() {
        ArrStack stack = new ArrStack();
        Scanner scanner = new Scanner(System.in);
        String key = " ";
        boolean flag = true;
        while (flag) {
            System.out.println("输入e：是否为空");
            System.out.println("输入f：是否满");
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
                case "f":
                    System.out.println(stack.isFull());
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

    /*
        模拟计算器
     */
    public static void cals(){
        String str = "10+2*7-5";
        ArrStack numStack = new ArrStack();
        ArrStack operStack = new ArrStack();
        int index = 0;
        char ch = ' ';
        //记录数值
        String ss = "";
        while (index<str.length()) {
            //获取元素
            ch = str.substring(index, index + 1).charAt(0);
            if (isOper(ch)){
                if (!operStack.isEmpty()){
                    if (priority(ch) < priority(operStack.peek())) {
                        //若栈顶操作符优先级高于参数
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        int op = operStack.pop();
                        int cal = cal(num1, num2, op);
                        numStack.add(cal);
                    }
                }
                operStack.add(ch);
            } else {
                ss += ch;
                System.out.println(ss);
                //如果是最后一个字符，那么直接入栈返回
                if (index == str.length()-1){
                    numStack.add(Integer.parseInt(ss));
                } else {
                    if (isOper(str.substring(index+1, index+2).charAt(0))) {
                        numStack.add(Integer.parseInt(ss));
                        ss = "";
                    }
                }
//                numStack.add(ch - 48);
            }
            index++;
        }

        while (numStack.size() > 1) {
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            int op = operStack.pop();
            int cal = cal(num1, num2, op);
            numStack.add(cal);
        }
        System.out.printf("最终运算结果:%s = %d", str, numStack.pop());

        //判断是否是操作符
        //是----判断操作符栈有无元素
        //  ----若无，则直接入栈
        //  ----若有，则判断优先级
        //  ----若新的优先级低于 栈中优先级，那么，取出数字栈中两个元素，
        //                                     以及操作符栈栈顶元素进行运算，把结果压入值栈顶，然后新操作符入栈
        //  ----若新的优先级高于 栈中优先级，那么入栈
        //否----压入值栈中去

        //最后，循环取出值栈两个元素和一个操作符运算，把结果放入值栈中，
        //     直到值栈中元素仅有一个的时候，返回最终结果。
    }


    /*
        判断操作优先级
        参数 oper 字符类型
        优先级为越高，执行顺序越靠前
        返回-1表示传入参数有误
     */
    public static int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        }else {
            return -1;
        }
    }

    //判断 字符 是否是运算符
    public static boolean isOper(int oper){
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    //执行运算
    public static int cal(int num1, int num2, int oper){
        int res = 0;
        switch (oper) {
            case '+' :
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                default:
                    break;
        }
        return res;
    }
}


class ArrStack {

    private int maxSize = 10; //最大栈深度
    private int cur = -1; //栈顶指针
    private int[] arr;

    public ArrStack(int size) {
        this.maxSize = size;
        arr = new int[maxSize-1];
    }

    public ArrStack(){
        arr = new int[maxSize-1];
    }

    //是否为空
    public boolean isEmpty(){
        return this.cur == -1;
    }

    //是否满
    public boolean isFull(){
        return this.cur == this.maxSize-1;
    }

    //大小
    public int size(){
        return this.cur + 1;
    }

    //显示栈顶元素
    public int peek(){
        if (isEmpty()) throw new RuntimeException("无元素");
        return arr[cur];
    }

    //遍历
    public void list(){
        if (isEmpty()) throw new RuntimeException("无元素");

        for (int i = cur; i>=0; i--) {
            System.out.printf("arr[%s]=%d\n", i, arr[i]);
        }
    }

    //添加
    public void add(int i){
        if (isFull()) throw new RuntimeException("满了");

        cur++;
        arr[cur] = i;
    }

    //取出
    public int pop(){
        if (isEmpty()) throw new RuntimeException("无元素");
        return arr[cur--];
    }

}
