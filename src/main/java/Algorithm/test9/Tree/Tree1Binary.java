package Algorithm.test9.Tree;

/**
 * 二叉树
 * @author YJ Lan
 * @create 2020-02-19-11:48
 */
public class Tree1Binary {
    public static void main(String[] args) {

        Node root = new Node(7, "根");
        Node node21 = new Node(5, "左2");
        Node node22 = new Node(10, "右2");
        Node node31 = new Node(1, "左左1");
        Node node32 = new Node(6, "左右2");
        Node node33 = new Node(9, "右左3");
        Node node34 = new Node(12, "右右4");
        root.left = node21;
        root.right = node22;
        node21.left = node31;
        node21.right = node32;
        node22.left = node33;
        node22.right = node34;


        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);
//        System.out.println("前序遍历");
//        binaryTree.front();
//        System.out.println("中序遍历");
//        binaryTree.mid();
//        System.out.println("后序遍历");
//        binaryTree.later();

//        Node node = null;
//        System.out.println("前序查找");
//        node = binaryTree.findFront(9);
//        System.out.println("中序查找");
//        node = binaryTree.findMid(9);
//        System.out.println("后序查找");
//        node = binaryTree.findLater(9);
//        System.out.println("查找结果");
//        System.out.println(node);


        binaryTree.remove(10);
        binaryTree.front();
    }

}

class BinaryTree{

    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    public void remove(int no){
        if (root == null) {
            return;
        }
        if (root.no == no) {
            root = null;
            return;
        }
        root.remove(no);

    }

    //后续遍历  先左，再右，最后父
    public void later(){
        if (this.root != null) {
            this.root.later();
        } else {
            System.out.println("root为空");
        }
    }
    //前序遍历 --- 先父，再左，最后右
    public void front(){
        if (this.root != null) {
            this.root.front();
        } else {
            System.out.println("root为空");
        }
    }

    //中序遍历 --- 先左，再父，最后右
    public void mid(){
        if (this.root != null) {
            this.root.mid();
        } else {
            System.out.println("root为空");
        }
    }


    //前序查找
    public Node findFront(int no){
        if (this.root != null) {
            return root.findFront(no);
        } else {
            return null;
        }
    }

    //中序查找
    public Node findMid(int no){
        if (this.root != null) {
            return root.findMid(no);
        } else {
            return null;
        }
    }

    //后序查找
    public Node findLater(int no){
        if (this.root != null) {
            return root.findLater(no);
        } else {
            return null;
        }
    }
}

class Node{
    public int no;
    public String name;
    public Node left;
    public Node right;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public void remove(int no){
        if (this.left == null || this.right == null) {
            return;
        }
        if (this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right.no == no) {
            this.right = null;
            return;
        }
        this.left.remove(no);
        this.right.remove(no);
    }

    //前序遍历 --- 先父，再左，最后右
    public void front(){
        System.out.println(this);
        if (this.left != null) {
            this.left.front();
        }
        if (this.right != null) {
            this.right.front();
        }
    }

    //中序遍历 --- 先左，再父，最后右
    public void mid(){
        if (this.left != null) {
            this.left.front();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.front();
        }

    }

    //后续遍历  先左，再右，最后父
    public void later(){
        if (this.left != null) {
            this.left.later();
        }
        if (this.right != null) {
            this.right.later();
        }
        System.out.println(this);
    }

    //前序查找
    public Node findFront(int no){
        System.out.println(this);
        if (this.no == no) {
            return this;
        }
        Node temp = null;
        if (this.left != null) {
            temp = this.left.findFront(no);
        }
        if (temp!= null) {
            return temp;
        }
        if (this.right != null) {
            temp = this.right.findFront(no);
        }
        return temp;
    }

    //中序查找
    public Node findMid(int no){

        Node temp = null;
        if (this.left != null) {
            temp = this.left.findMid(no);
        }
        if (temp!= null) {
            return temp;
        }
        System.out.println(this);
        if (this.no == no) {

            return this;
        }
        if (this.right != null) {
            temp = this.right.findMid(no);
        }
        return temp;
    }

    //后序查找
    public Node findLater(int no){
        Node temp = null;
        if (this.left != null) {
            temp = this.left.findLater(no);
        }
        if (temp!= null) {
            return temp;
        }

        if (this.right != null) {
            temp = this.right.findLater(no);
        }
        if (temp!= null) {
            return temp;
        }
        System.out.println(this);
        if (this.no == no) {

            return this;
        }
        return temp;
    }
}