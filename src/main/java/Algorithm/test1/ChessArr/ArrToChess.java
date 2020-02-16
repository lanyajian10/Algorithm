package Algorithm.test1.ChessArr;

import java.io.*;

/**
 * 二维数组---》 稀疏数组
 * @author YJ Lan
 * @create 2020-01-14-20:32
 */
public class ArrToChess {

    public static void main(String[] args) throws Exception{
        //声明二维数组
        int [][] arr = new int[11][11];
        arr[1][5] = 2;
        arr[3][6] = 1;

        System.out.println("---------------------------");
        System.out.println("打印出二维数组如下：");
        for (int[] arr1 : arr){
            for (int i=0; i<arr1.length; i++){
                System.out.print(arr1[i]+" ");
            }
            System.out.println();
        }

        System.out.println("---------------------------");

        int sum = 0;
        for (int[] arr1 : arr){
            for (int i=0; i<arr1.length; i++){
                if (arr1[i] != 0){
                    sum++;
                }
            }
        }
        System.out.println("算出二维数组的有效数组个数"+sum);
        System.out.println("---------------------------");
        System.out.println("二维数组转散列数组并打印并保存到文件中");

        int[][] chessArr = new int[sum+1][3];
        chessArr[0][0] = 11;
        chessArr[0][1] = 11;
        chessArr[0][2] = sum;
        int count = 0;
        for (int j=0; j<11; j++){
            for (int i=0; i<11; i++){
                if (arr[j][i] != 0){
                    count++;
                    chessArr[count][0] = j;
                    chessArr[count][1] = i;
                    chessArr[count][2] = arr[j][i];
                }
            }
        }

        OutputStream os = new FileOutputStream("test.txt");

        for (int[] chessArr1 : chessArr){
            for (int chess : chessArr1){
                System.out.printf("%d\t",chess);
                os.write((chess+",").getBytes("UTF-8"));
            }
            os.write("\n".getBytes("UTF-8"));
            System.out.println();
        }
        System.out.println("---------------------------");
        System.out.println("从文件取出并转成数组");
        InputStream is = new FileInputStream("test.txt");
        BufferedReader bis = new BufferedReader(new InputStreamReader(is));
        String s = bis.readLine();
        String[] split = s.split(",");
        int[][] chessArr1 = new int[Integer.parseInt(split[2])+1][3];
        chessArr1[0][0] = Integer.parseInt(split[0]);
        chessArr1[0][1] = Integer.parseInt(split[1]);
        chessArr1[0][2] = Integer.parseInt(split[2]);

        s = bis.readLine();
        count = 0;
        while (s!= null){
            count++;
            split = s.split(",");
            for (int i=0; i<split.length;i++){
                if(split[i] != null){
                    chessArr1[count][i] = Integer.parseInt(split[i]);
                }
            }
            s = bis.readLine();
        }

        for (int[] chessArr11 : chessArr1){
            for (int chess : chessArr11){
                System.out.printf("%d\t",chess);
            }
            System.out.println();
        }
        System.out.println("---------------------------");
        System.out.println("散列数组转化为二维数组");
        int[][] arr1 = new int[chessArr1[0][0]][chessArr1[0][1]];
        for (int i=1; i<=chessArr1[0][2];i++){
            arr1[chessArr1[i][0]][chessArr1[i][1]] = chessArr1[i][2];
        }

        for (int[] arr11 : arr1){
            for (int a=0; a<arr11.length; a++){
                System.out.print(arr11[a]+" ");
            }
            System.out.println();
        }

    }
}
