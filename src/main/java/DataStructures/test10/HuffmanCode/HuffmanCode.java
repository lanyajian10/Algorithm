package DataStructures.test10.HuffmanCode;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码
 * 1.字符串  转化为  字节数组
 * 2.字节数组  转化为  map   key：对应字节   value ：对应此节出现次数
 * 3.map 转化为 list，为转化为赫夫曼树做准备
 * 4.list转化为 赫夫曼树
 * 5.赫夫曼树转化为赫夫曼map  key： 对应字节， value：对应赫夫曼编码
 * 6：赫夫曼map 转化为 十进制的赫夫曼字节数组，也就是压缩后的结果
 * @author YJ Lan
 * @create 2020-02-22-10:04
 */
public class HuffmanCode {

    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        //1.字符串  转化为  字节数组
//        byte[] bytes = content.getBytes();
////        System.out.println("赫夫曼编码前字节数组长度："+bytes.length);  //40
//        byte[] HuffmanByte =HuffmanZip(bytes);
//        //解码
//        byte[] oldbytes = decode(roadMap, HuffmanByte);
//        System.out.println(new String(oldbytes));

        String srcFile = "f://2121.png";
        String desFile = "f://2222.zip";
        String ssrcFile = "f://21222.png";
        //测试压缩文件
        zipFile(srcFile, desFile);
        decodeFile(desFile, ssrcFile);
    }

    /**
     *  解压文件
     * @param srcFile
     * @param desFile
     */
    public static void decodeFile(String srcFile, String desFile){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(new File(srcFile));
            ois = new ObjectInputStream(fis);
            byte[] bytes = (byte[]) ois.readObject();
            Map<Byte,String> map = (Map<Byte, String>) ois.readObject();
            System.out.println("赫夫曼解压前字节数组长度："+bytes.length);  //40
            bytes = decode(map, bytes);
            System.out.println("赫夫曼解压后字节数组长度："+bytes.length);  //40
            fos = new FileOutputStream(new File(desFile));
            fos.write(bytes);
            System.out.println("解压完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                ois.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *  压缩文件
     * @param srcFile
     * @param desFile
     */
    public static void zipFile(String srcFile, String desFile){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fis = new FileInputStream(new File(srcFile));
            byte[] bytes = new byte[fis.available()];
            int read = fis.read(bytes);
            System.out.println("赫夫曼编码前字节数组长度："+bytes.length);  //40
            bytes = HuffmanZip(bytes);
            System.out.println("赫夫曼编码后字节数组长度："+bytes.length);  //40
            fos = new FileOutputStream(new File(desFile));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(bytes);
            oos.writeObject(roadMap);

            System.out.println("压缩完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *  赫夫曼解码
     * @param HuffmanCode   赫夫曼对应字节编码表
     * @param HuffmanByte   赫夫曼字节数组
     * @return  解码后的字节数组
     */
    public static byte[] decode(Map<Byte, String> HuffmanCode, byte[] HuffmanByte){
        //1.   把赫夫曼字节数组  转化为   赫夫曼编码
        StringBuffer sb  = new StringBuffer();
        for (int i=0; i<HuffmanByte.length; i++) {
            boolean flag = (i != HuffmanByte.length-1);
            sb.append(byteToString(flag, HuffmanByte[i]));
        }
        //测试 还原的 赫夫曼编码
//        System.out.println("测试：还原的赫夫曼编码："+sb.toString());

        //2.把  赫夫曼字节编码对照表  改成  赫夫曼编码字节对照表
        Map<String, Byte> stringByteMap = new HashMap<>();
        for (Map.Entry<Byte,String> entry:HuffmanCode.entrySet()) {
            stringByteMap.put(entry.getValue(), entry.getKey());
        }

        //3. 按照对照表，找出原字节数组
        List<Byte> bytes = new ArrayList<>();
        for (int i=0; i<sb.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {

                String key = sb.substring(i,i+count);
                b = stringByteMap.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            bytes.add(b);
            i += count;
        }

        byte[] oldByte = new byte[bytes.size()];
        for (int i=0; i<oldByte.length;i++) {
            oldByte[i] = bytes.get(i);
        }
        return oldByte;
    }


    /**
     *
     *  把 byte 转换成 对应二进制编码的补码
     * @param flag   是否是最后一位
     * @param b      要转化的byte
     * @return       机器码，byte对应二进制的补码
     */
    private static String byteToString(Boolean flag, byte b){
        int s = b;
        if (flag) {
            s = s|256;   //例如： 1 | 256 => 0000 0001 |  1 0000 0000 => 1 0000 0001  不这样，s 就直接等于1
        }
        String str = Integer.toBinaryString(s);
        if (flag) {
            //取 后8位
            str = str.substring(str.length()-8);
        }
        return str;
    }


    /**
     *  哈夫曼编码
     * @param bytes  转化的内容
     * @return 哈夫曼编码压缩后的数组
     */
    public static byte[] HuffmanZip(byte[] bytes) {

        //2.字节数组  转化为  map   key：对应字节   value ：对应此节出现次数
        HashMap<Byte, Integer> map =  bytesToMap(bytes);
        //3.map 转化为 list，为转化为赫夫曼树做准备
        ArrayList<Node> nodes = mapToList(map);
        //4.list转化为 赫夫曼树
        Node root = toHuffmanTree(nodes);
//        HuffmanCode.FrontList(root);
        //5.赫夫曼树转化为赫夫曼map  key： 对应字节， value：对应赫夫曼编码
        Map<Byte,String> huffman = getCode(root);
        //6：赫夫曼map 转化为 十进制的赫夫曼字节数组，也就是压缩后的结果
        byte[] HuffmanByte = zip(bytes, huffman);

        return HuffmanByte;
    }

    private static ArrayList<Node> mapToList(HashMap<Byte, Integer> map) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        for (Map.Entry<Byte,Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     *
     * @param bytes 原始字符串对应byte数组
     * @param huffman   生成的赫夫曼map
     * @return 返回赫夫曼编码处理后的byte[]
     * 10101000（补码） ->  10101000 - 1 => 10100111（反码） => 11011000（原码）  => -88(十进制)
     * (byte) Integer.parseInt(substring, 2)   ==>   2进制=>十进制=>byte
     */
    private static byte[] zip(byte[] bytes,  Map<Byte,String> huffman){
        StringBuffer sb1 = new StringBuffer();
        for (Byte b: bytes) {
            String s = huffman.get(b);
            sb1.append(s);
        }
        //将对应的"1010100010111111110010001"--->byte[]
        int len = (sb1.length()+7)/8;
//        if (sb1.length()%8==0) {
//            len = sb1.length()/8;
//        } else {
//            len = sb1.length()/8 +1;
//        }
        byte[] HuffmanByte = new byte[len];
        int count = 0;
        for (int i=0; i<sb1.length(); i+=8) {
            String substring = "";
            if (i+8<sb1.length()) {
                substring = sb1.substring(i, i + 8);
            } else {
                substring = sb1.substring(i);
            }
            HuffmanByte[count++] = (byte) Integer.parseInt(substring, 2);
        }
        return HuffmanByte;
    }


    /**
     * 重载findContentByCount
     * @param root
     */
    private static Map<Byte,String>  getCode(Node root){
        if (root != null) {
            findContentByCount(root.left, "0", stringBuffer);
            findContentByCount(root.right, "1", stringBuffer);
            return roadMap;
        } else {
            return null;
        }

    }

    static StringBuffer stringBuffer = new StringBuffer();
    //  对应字节和路径
    static Map<Byte,String> roadMap = new HashMap<Byte, String>();

    /**
     *  生成哈夫曼树对应哈夫曼编码的key value
     * @param root  节点
     * @param code  左边 0  右边 1
     * @param sbs
     */
    private static void findContentByCount(Node root,String code,StringBuffer sbs){
        //添加路径
        StringBuffer sb = new StringBuffer(sbs);
        sb.append(code);
        if (root.content == null) {
            //非叶节点
            findContentByCount(root.left, "0", sb);
            findContentByCount(root.right, "1", sb);
        } else {
            //叶节点
            roadMap.put(root.content, sb.toString());
        }
    }

    /**
     * list -> HuffmanTree
     * @param list
     * @return
     */
    private static Node toHuffmanTree(List<Node> list){

        while (list.size()>1) {
            Collections.sort(list, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.count - o2.count;
                }
            });
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parent = new Node(leftNode.count + rightNode.count);
            parent.left = leftNode;
            parent.right = rightNode;
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parent);
        }
        return list.get(0);
    }

    /**
     * byte -> map
     * @param bytes
     * @return
     */
    private static HashMap<Byte, Integer> bytesToMap(byte[] bytes) {
        HashMap<Byte, Integer> map= new HashMap<Byte, Integer>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, count+1);
            }
        }
        return map;
    }


    //霍夫曼前序遍历
    public static void FrontList(Node root){
        if (root != null) {
            root.frontList();
        } else {
            System.out.println("树为空");
        }
    }
}


class Node{

    public Byte content;
    public int count;
    public Node left;
    public Node right;

    public Node(Byte content, int count) {
        this.content = content;
        this.count = count;
    }

    public Node(int count) {
        this.content = content;
        this.count = count;
    }
    @Override
    public String toString() {
        return "Node{" +
                "content=" + content +
                ", count=" + count +
                '}';
    }

    //前序
    public void frontList(){
        System.out.println(this);
        if (this.left != null) {
            this.left.frontList();
        }
        if (this.right != null) {
            this.right.frontList();
        }
    }
}