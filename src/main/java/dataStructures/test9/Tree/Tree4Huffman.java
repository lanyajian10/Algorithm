package Algorithm.test9.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 赫/霍夫曼树
 * WPL最小
 * @author YJ Lan
 * @create 2020-02-21-14:57
 */
public class Tree4Huffman {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        HNode root = createHuffmanTree(arr);
        root.frontList();
    }

    public static HNode createHuffmanTree(int[] arr){

        List<HNode> list = new ArrayList<HNode>();
        for (int i:arr) {
            list.add(new HNode(i));
        }
        Collections.sort(list, new Comparator<HNode>() {
            @Override
            public int compare(HNode o1, HNode o2) {
                return o1.value - o2.value;
            }
        });

        System.out.println(list.toString());

        while (list.size()>1) {

            HNode leftNode = list.get(0);
            HNode rightNode = list.get(1);
            HNode parent = new HNode(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parent);

            Collections.sort(list, new Comparator<HNode>() {
                @Override
                public int compare(HNode o1, HNode o2) {
                    return o1.value - o2.value;
                }
            });
        }

        return list.get(0);
    }

}

//节点
class HNode{
    public int value;
    public HNode left;
    public HNode right;

    public HNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HNode{" +
                "value='" + value + '\'' +
                '}';
    }

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