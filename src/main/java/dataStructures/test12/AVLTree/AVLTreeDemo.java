package dataStructures.test12.AVLTree;

/**
 * @author YJ Lan
 * @create 2020-02-24-10:40
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr  = {4,3,6,5,7,8};  //一次左旋转可以解决
//        int[] arr  = {10,12,8,9,7,6};  //一次右旋转可以解决
        int[] arr  = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }


        Node root = avlTree.root;
        System.out.println("AVL当前根节点："+root);
        System.out.println("AVL树高度："+root.AVLheight());
        System.out.println("AVL左子树高度："+root.left.AVLheight());
        System.out.println("AVL右子树高度："+root.right.AVLheight());

    }
}

class AVLTree{

    public Node root ;
    //添加
    public void add(Node node){
        if (root != null) {
            root.add(node);
        } else {
            root = node;
        }
    }

    //中序遍历
    public void midList(){
        if (root != null) {
            root.midList();
        } else {
            System.out.println("是空的");
        }
    }

    public void remove(int value){
        //1.找出该节点
        if (root == null ) {
            System.out.println("空的");
            return;
        }
        Node targetNode = root.findNode(value);
        if (targetNode == null) {
            System.out.println("未找到父节点，是根节点");

        }
        //找到并且只有一个节点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        //2.找出父节点
        Node parentNode = root.findParentNode(value);

        //3. 情况1：叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            if (parentNode.left.value == value) {
                parentNode.left = null;
            } else if (parentNode.right.value == value){
                parentNode.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            //情况2：有两个节点
            int small = findSmallNode(targetNode.right);
            targetNode.value = small;
        } else {
            //情况3：只有一个左子节点  或者  只有一个右子节点
            if (parentNode == null) {
                //如果这个目标节点是根节点，则移动根节点
                if (targetNode.left == null) {
                    root = targetNode.right;
                }else {
                    root = targetNode.left;
                }
            }else {
                //如果这个目标节点是非根节点
                if (targetNode.left != null) {
                    if (parentNode.left.value == targetNode.value) {
                        parentNode.left = targetNode.left;
                    } else {
                        parentNode.right = targetNode.left;
                    }
                } else {
                    if (parentNode.left.value == targetNode.value) {
                        parentNode.left = targetNode.right;
                    } else {
                        parentNode.right = targetNode.right;
                    }
                }
            }
        }
    }

    /**
     * 找出目标节点
     * @param value
     * @return
     */
    public Node findNode(int value){
        if (root != null) {
            return root.findNode(value);
        } else {
            System.out.println("空的");
            return null;
        }
    }

    /**
     * 找出目标节点的父节点
     * @param value
     * @return
     */
    public Node findParentNode(int value) {
        if (root != null) {
            return root.findParentNode(value);
        } else {
            System.out.println("空的");
            return null;
        }
    }
    /**
     * 找出右子树的最小节点，删除并返回Node的值
     * @return
     */
    public int findSmallNode(Node node){
        Node temp = node;
//        System.out.println(temp);
        while (temp.left != null) {
            temp = temp.left;
        }
        remove(temp.value);
        return temp.value;
    }
}

class Node{
    public int value;
    public Node left;
    public Node right;
    public Node(){

    }

    public Node(int value) {
        this.value = value;
    }

    // AVL特有：返回左子树高度
    public int AVLleftHeight(){
        if (left == null) return 0;
        return this.left.AVLheight();
    }
    //AVL特有：返回右子树高度
    public int AVLrightHeight(){
        if (right==null) return 0;
        return this.right.AVLheight();
    }

    //AVL特有：返回以当前节点为根节点的树高度
    public int AVLheight(){
        return Math.max(this.left == null? 0 : this.left.AVLheight(),
                this.right == null? 0 : this.right.AVLheight())+1;
    }

    /**
     * 左旋转步骤(右子树高度-左子树高度>1)：
     * 1. 创建一个新节点，值等于当前根节点的值
     * 2. 把新节点的左子树设置为 当前节点的左子树
     * 3. 把新节点的右子树设置为 当前节点右子树的左子树
     * 4. 把当前节点的值设置为右节点的值
     * 5. 把当前节点的右子树，设置为当前节点的右子树的右子树
     * 6. 把当前节点的左子树，设置为新节点
     */
    public void AVLleftRotate(){
        Node node = new Node(value);

        node.left = left;

        node.right = right.left;

        value = right.value;

        right = right.right;

        left = node;
    }

    /**
     * 右旋转步骤(左子树高度-右子树高度>1)：
     * 1. 创建一个新节点，值等于当前根节点的值
     * 2. 把新节点的右子树设置为 当前节点的右子树
     * 3. 把新节点的左子树设置为 当前节点左子树的右子树
     * 4. 把当前节点的值设置为左节点的值
     * 5. 把当前节点的右子树，设置为当前节点的左子树的左子树
     * 6. 把当前节点的右子树，设置为新节点
     */
    public void AVLrightRotate(){
        Node node = new Node(value);

        node.right = right;

        node.left = left.right;

        value = left.value;

        left = left.left;

        right = node;
    }

    /**
     * 找出目标节点
     * @param value
     * @return
     */
    public Node findNode(int value){
        if (this.value == value) {
            return this;
        }
        if (this.left != null && this.value > value) {
            return this.left.findNode(value);
        } else if(this.right != null && this.value <= value){
            return this.right.findNode(value);
        }
        return null;
    }

    /**
     * 找出目标节点的父节点
     * @param value
     * @return
     */
    public Node findParentNode(int value) {
        //该节点  为 父节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value )) {
            return this;
        } else {
            //如果比该节点小，那么他的父节点在该节点的左边
            if (value < this.value && this.left != null) {
                return this.left.findParentNode(value);
            } else if (value >= this.value && this.right != null){
                return this.right.findParentNode(value);
            } else {
                return null;
            }
        }
    }

    //添加
    public void add(Node node){
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        //AVL旋转判定
        if (AVLrightHeight() - AVLleftHeight() > 1) {
            //如果他的右子树中，左子树高度大于右子树高度，
            if (right!=null && right.AVLleftHeight() > right.AVLrightHeight()) {
                //先对 当前节点右子树进行右旋转
                right.AVLleftRotate();
                //再对 当前节点左旋转
            }
            AVLleftRotate();
            return;  //必须要
        }
        if (AVLleftHeight() - AVLrightHeight() > 1){
            //如果他的左子树中，右子树高度大于左子树高度，
            if (left!=null && left.AVLrightHeight() > left.AVLleftHeight()) {
                //先对 当前节点左子树进行左旋转
                left.AVLleftRotate();
                //再对 当前节点右旋转
            }
            AVLrightRotate();
        }

    }

    //中序遍历
    public void midList(){
        if (this.left != null) {
            this.left.midList();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midList();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

}
