package Algorithm.test11.BinarySortTree;

/**
 * 二叉排序树  BST
 * 中序遍历从小到大
 * @author YJ Lan
 * @create 2020-02-23-09:56
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {0,7,3,10,12,5,1};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i=0; i<arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.remove(0);

        System.out.println("中序遍历");
        binarySortTree.midList();
    }
}


class BinarySortTree {

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

    public Node(int value) {
        this.value = value;
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

