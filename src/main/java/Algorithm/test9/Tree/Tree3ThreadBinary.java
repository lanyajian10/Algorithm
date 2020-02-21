package Algorithm.test9.Tree;

/**
 * 线索二叉树
 * @author YJ Lan
 * @create 2020-02-20-15:34
 */
public class Tree3ThreadBinary {
    public static void main(String[] args) {
        TNode root = new TNode(7, "根");
        TNode node21 = new TNode(5, "左2");
        TNode node22 = new TNode(10, "右2");
        TNode node31 = new TNode(1, "左左1");
        TNode node32 = new TNode(6, "左右2");
        TNode node33 = new TNode(9, "右左3");
        TNode node34 = new TNode(12, "右右4");
        root.left = node21;
        root.right = node22;
        node21.left = node31;
        node21.right = node32;
        node22.left = node33;
//        node22.right = node34;

        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree();

//        threadBinaryTree.createFrontThreadThree(root);
//        threadBinaryTree.createMidThreadTree(root);
        threadBinaryTree.createLaterThreadThree(root);
//        System.out.println(root.left);
//        System.out.println(root.right);
//        System.out.println(node22.left);
//        System.out.println(node22.right);
//        System.out.println(node31.left);
//        System.out.println(node31.right);

        threadBinaryTree.root = root;
//        threadBinaryTree.frontList();
//        threadBinaryTree.midList();

        //特别注意：这块难点
        node21.parent = root;
        node22.parent = root;
        threadBinaryTree.laterList();

    }
}


class ThreadBinaryTree{

    public TNode root;

    public TNode pre = null;

    //前序
    public void frontList(){
        TNode temp = root;
        while (temp != null) {

            //找到做节点是子节点的节点
            while (temp.leftType == 0) {
                System.out.println(temp);
                temp = temp.left;
            }
            //输出
            System.out.println(temp);
            //移动到后继节点
            temp = temp.right;
        }
    }

    //中序遍历
    public void midList(){
        TNode temp = root;
        while (temp != null) {

            //找到第一个做节点是前继节点的节点
            while (temp.leftType != 1) {
                temp = temp.left;
            }
            //输出
            System.out.println(temp);
            //如果 右节点是后继节点，一直输出
            while (temp.rightType == 1) {
                temp = temp.right;
                System.out.println(temp);
            }

            //如果不是后继节点，那么指针移动到右节点上，再次执行循环，去找左边第一个节点
            temp = temp.right;
        }
    }

    //后序遍历   需要获取父节点的
    public void laterList(){
        TNode temp = root;
        //找到左节点为前继节点的节点
        while (temp.leftType != 1) {
            temp = temp.left;
        }
        while (temp != null) {

            //右节点是线索
            if (temp.rightType == 1) {
                System.out.println(temp);
                pre = temp;
                temp = temp.right;
            } else {
                if (temp.right == pre) {
                    System.out.println(temp);
                    if (temp == root ) {
                        return;
                    }
                    pre = temp;
                    temp = temp.parent;
                } else {
                    temp = temp.right;
                    while (temp != null && temp.leftType == 0){
                        temp = temp.left;
                    }
                }
            }

        }
    }

    //按照前序创建线索二叉树
    public void createFrontThreadThree(TNode node){

        if (node == null) {
            return;
        }
        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }
        if (pre != null && pre.right == null){
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;
        if (node.leftType == 0) {
            createFrontThreadThree(node.left);
        }

        if (node.rightType == 0) {
            createFrontThreadThree(node.right);
        }



    }

    //按照中序创建线索二叉树
    public void createMidThreadTree(TNode node){

        if (node == null) {
            return;
        }

        createMidThreadTree(node.left);

        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }

        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }

        pre = node;

        createMidThreadTree(node.right);
    }

    //按照后序创建线索二叉树
    public void createLaterThreadThree(TNode node){

        if (node == null) {
            return;
        }

        createLaterThreadThree(node.left);

        createLaterThreadThree(node.right);

        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }
        if (pre != null && pre.right == null){
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;
    }
}

class TNode{
    public int no;
    public String name;
    public TNode left;
    public TNode right;

    public TNode parent;

    // 0：左子节点  1：前驱结点
    public int leftType = 0;
    // 0：右子节点  1：后继结点
    public int rightType = 0;

    public TNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}