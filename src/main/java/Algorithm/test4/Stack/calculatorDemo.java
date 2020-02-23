package Algorithm.test4.Stack;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 *  1+((2+3)*4)-5 => [1,+,(,(,2,+,3,),*,4,),-,5]
 * 1.中缀表达式--->数组
 * [1,+,(,(,2,+,3,),*,4,),-,5] => [1,2,3,+,4,*,+,5,-]
 * 2.数组---->逆波兰表达式
 *  （3+4）*5-6 => 3 4 + 5 * 6 -  => 29
 * 3.逆波兰表达式（后缀表达式）--->结果
 *
 * 综合：中缀->结果
 * @author YJ Lan
 * @create 2020-02-11-20:48
 */
public class calculatorDemo {

    public static void main(String[] args) {
        String str = "1+((2+3)*4)-15";
        //转数组
        List<String> list = strToList(str);
        System.out.println(list);
        //转逆波兰表达式
        List<String> list1 = listToList(list);
        System.out.println(list1);
        cal(list1);
    }
    // 逆波兰表达式 计算结果
    public static void cal(List<String> list){
        Stack<String> stack = new Stack<String>();

        for (String str : list) {
            if (str.matches("\\d+")) {
                //如果是数字，压入栈中
                stack.push(str);
            } else if (str.charAt(0) > 57 || str.charAt(0) < 48){
                //如果是运算符,则取出  栈顶 和 次栈顶元素  进行运算， 把结果压入栈顶
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result = 0;
                switch (str) {
                    case "+" :
                        result = num2 + num1;
                        break;
                    case "-":
                        result = num2 - num1;
                        break;
                    case "*":
                        result = num2 * num1;
                        break;
                    case "/":
                        result = num2 / num1;
                        break;
                        default:break;
                }
                stack.push(result+"");
            }
        }
        System.out.printf("最终结果为：%s=%s", list, stack.pop());
    }

    //数组  转  逆波兰表达式
    public static List<String> listToList(List<String> list){
        //符号栈
        Stack<String> stack1 = new Stack<String>();
        //只进不出栈
        List<String> stack2 = new LinkedList<String>();
        for (String item : list) {


            if (item.matches("\\d+")){
                //如果是数字，直接入栈2
                stack2.add(item);
            } else if ("(".equals(item) ) {
                //如果是(，直接入符号栈
                stack1.push(item);
            } else if (")".equals(item)) {
                //如果是），则取出符号栈顶元素放入栈2，再去看符号栈次栈顶元素，直到碰到（结束
                while ( ! stack1.peek().equals("(") && stack1.size()>0 ) {
                    stack2.add(stack1.pop());
                }
                //移除符号栈的（
                stack1.pop();
            } else {
                //如果是符号
                //如果符号栈栈顶有元素，则比较
                //如果 符号栈栈顶 运算顺序 大于等于 item ，则取出符号栈栈顶元素移入 栈2，
                //重新比较，直到 栈顶元素 小于 item 结束
                while (stack1.size()>0 && isOper(stack1.peek()) >= isOper(item)) {
                    stack2.add(stack1.pop());
                }
                //放入 item符号
                stack1.push(item);
            }
        }
        //把符号栈剩余元素依次放入 栈2
        while (stack1.size()>0) {
            stack2.add(stack1.pop());
        }
        return stack2;
    }
    //判断优先级，（）这类优先级为最低0
    public static int isOper(String i){
        int res = 1;
        switch (i){
            case "+":
                res = 1;
                break;
            case "-":
                res = 1;
                break;
            case "*":
                res = 2;
                break;
            case "/":
                res = 2;
                break;
            default:
                res = 0;
        }
        return res;
    }
    //数字 转 集合
    public static List<String> strToList(String str){
        List<String> list = new ArrayList<>();
        //定义指针
        int i = 0;
        while (i<str.length()) {
            //如果指针位置为符号
            if (str.charAt(i) < 48 || str.charAt(i) > 57) {
                //直接添加进数组
                list.add(""+str.charAt(i));
                i++;
            } else {
                //如果指针位置为数字，则直接添加到字符串中，直到最后或者该位置为符号的时候
                String num = "";
                while (i<str.length() && str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    num += str.charAt(i);
                    i++;
                }
                //把字符串添加到数组中
                list.add(num);
            }
        }
        return list;
    }


}
