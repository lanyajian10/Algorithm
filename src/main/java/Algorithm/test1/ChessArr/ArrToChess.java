package Algorithm.test1.ChessArr;

import java.io.*;
import java.util.Arrays;

/**
 * 二维数组---》 稀疏数组
 * @author YJ Lan
 * @create 2020-01-14-20:32
 */
public class ArrToChess {

    public static void main(String[] args) {
        //声明二维数组
        int [][] arr = new int[11][11];
        arr[1][5] = 2;
        arr[3][6] = 1;
        //打印二维数组
        ArraystoString(arr);
        int sum = 0;
        for (int[] arr1 : arr){
            for (int i : arr1) {
                if (i != 0){
                    sum++;
                }
            }
        }


        System.out.print("------------------Array >>>> Chess AND ");
//
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

        System.out.println("Chess >>> File--------------------");
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            os = new FileOutputStream("test.txt");
            oos = new ObjectOutputStream(os);
            for (int[] chessArr1 : chessArr){
                    oos.writeObject(chessArr1);
                System.out.println(Arrays.toString(chessArr1));
            }
        } catch (Exception e) {
        } finally {
            try {
                oos.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        System.out.println("--------------------File >>> Chess--------------------");
        InputStream is = null;
        ObjectInputStream ois = null;
        int[][] chessArr1 = null;
        try {
            is = new FileInputStream("test.txt");
            ois = new ObjectInputStream(is);
            int[] s = (int[])ois.readObject();
            chessArr1 = new int[s[2]+1][3];
            chessArr1[0][0] = s[0];
            chessArr1[0][1] = s[1];
            chessArr1[0][2] = s[2];
            count = 0;
            while (true) {
                count++;
                try {
                    s = (int[])ois.readObject();
                } catch (EOFException e) {
                    break;
                }
                chessArr1[count][0]=s[0];
                chessArr1[count][1]=s[1];
                chessArr1[count][2]=s[2];
            }
        }catch (Exception ex){

        }finally {
            try {
                ois.close();
                is.close();
            } catch (Exception e) {

            }
        }
        ArraystoString(chessArr1);


        System.out.println("--------------------Chess >>> Array--------------------");
        int[][] arr1 = new int[chessArr1[0][0]][chessArr1[0][1]];
        for (int i=1; i<=chessArr1[0][2];i++){
            arr1[chessArr1[i][0]][chessArr1[i][1]] = chessArr1[i][2];
        }
        ArraystoString(arr1);


    }

    private static void ArraystoString(int[][] arr) {
        for (int[] arr1 : arr){
            System.out.println(Arrays.toString(arr1));
        }
    }
}
