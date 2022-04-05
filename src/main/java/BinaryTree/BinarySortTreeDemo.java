package BinaryTree;

public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr={7,3,10,12,5,1,9,2};

        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.infixOrder();

        System.out.println("---------------------------------");
        /*binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(10);*/
        //binarySortTree.delNode(1);
       /* binarySortTree.delNode(3);
        binarySortTree.delNode(2);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
*/
        /*binarySortTree.delNode(7);

        binarySortTree.delNode(9);
        binarySortTree.delNode(12);*/

        //binarySortTree.delNode(10);

        binarySortTree.infixOrder();
        System.out.println("查找------");
        System.out.println(binarySortTree.find(9));
        System.out.println(binarySortTree.find(7));
        System.out.println(binarySortTree.find(12));
        System.out.println(binarySortTree.find(5));
        System.out.println(binarySortTree.find(1));
    }


}

class Node{

    int value;
    Node left;
    Node right;

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /** 找该节点
     * @param value
     * @return
     */
    public Node search(int value){
        if(value==this.value){
            return this;
        }else {
            if(value<this.value && this.left!=null){
                return this.left.search(value);
            }else if(value>this.value && this.right!=null){
                return this.right.search(value);
            }else {//空的
                return null;
            }
        }

    }

    /** 找该节点的父节点
     *

     */
    public Node searchParent(int value){

        if((this.left!=null&&this.left.value== value)||
                (this.right!=null&&this.right.value==value)){
            return this;
        }else if(value<=this.value){
            if(this.left!=null){
                return this.left.searchParent(value);
            }else {
                return null;
            }
        }else if(value>this.value){
            if(this.right!=null){
                return this.right.searchParent(value);
            }else {
                return null;
            }
        }

        return null;

    }


   /*  我的写法
     * 三种情况
     * 一.删除叶子结点  ps:需要删除的节点没有左右子树
     *   1.找到父节点 判断需要删除的节点是左子树还是右子树
     *   2.然后left或者right置空
     *
     *
     *
    public void delNode(int value){
        //一.删除叶子结点
        Node parentNode=null;
        Node targetNode=this;

        //1.直接是根节点

        //2.不是根节点
        while (true){
            if(value<targetNode.value&&targetNode.left!=null){
                parentNode=targetNode;
                targetNode=targetNode.left;
            }else if(value>targetNode.value&&targetNode.right!=null) {
                parentNode=targetNode;
                targetNode=targetNode.right;
            }else if(value==targetNode.value){
                //找到了
                if(parentNode.left!=null&&parentNode.left.value==value){
                    parentNode.left=null;
                }else if(parentNode.right!=null&&parentNode.right.value==value){
                    parentNode.right=null;
                }
                break;
            }else {
                //System.out.println("左右都没有删除失败");
                return;
            }
        }

    }
*/
    public void infixOrder() {
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    public void add(Node node){

        if(node==null){
            System.out.println("需要添加的节点为空");
        }
        if(node.value<=this.value){
            if( this.left!=null){
                this.left.add(node);
            }else {
                this.left=node;
            }
        }else if(node.value>this.value){
            if( this.right!=null){
                this.right.add(node);
            }else {
                this.right=node;
            }
        }


    }


    public Node find(int value) {
        if(value==this.value){
            return this;
        }

        if(value<this.value && this.left!=null){
            return this.left.find(value);
        }else if(value>this.value && this.right!=null){
            return this.right.find(value);
        }else {
            return null;
        }
        //找不到

    }
}

class BinarySortTree{
    Node root;

    public Node find(int value){
        if(root==null){
            System.out.println("root为空");
            return null;
        }else {
            return root.find(value);
        }
    }

    public void infixOrder(){
        if(root==null){
            System.out.println("root为空");
        }else {
            root.infixOrder();
        }
    }

    public void add(Node node){
        if(node==null){
            System.out.println("需要添加的节点为空");
        }

        if(root==null){
            root=node;
        }else {
            root.add(node);
        }

    }

    /* 我写法
    public void delNode(int value){
        if (root==null){
            System.out.println("root为空");
        }else if(root.value==value&&root.left==null&&root.right==null){
            root=null;
        }else {
            root.delNode(value);
        }
    }*/


    public Node search(int value){
        if (root==null){
            System.out.println("root为null");
            return null;
        }else {
            return root.search(value);
        }
    }

    public Node searchParent(int value){
        if (root==null){
            System.out.println("root为null");
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    public void delNode(int value){
        //表示待删除节点有两颗子树的时候删除模式
        // -1 以左子树遍历最大的代替待删除节点    1 以右子树遍历最小的代替带删除节点
        //int mode=-1;
        int mode=1;
        if (root==null){
            System.out.println("root为null");
            return;
        }

        //1.删除叶子节点
        Node targetNode = search(value);
        if(targetNode==null){
            System.out.println("targetNode没找到");
            return;
        }

        //叶子节点 没有子树的节点
        //->需要考虑是target就根节点而且根节点没有左右子树的情况
        if(root.left==null&&root.right==null){
            root=null;
            return;
        }

        if(targetNode.left==null&&targetNode.right==null){
            Node parentNode = searchParent(value);
            //->需要考虑是根节点而且根节点没有左右子树的情况
            if(parentNode==null){
                System.out.println("没找到父节点");
                return;
            }else {
                if(parentNode.left!=null&&parentNode.left.value==value){
                    //target节点是parent节点的左子树
                    parentNode.left=null;
                    return;
                }else if(parentNode.right!=null&&parentNode.right.value==value){
                    //target节点是parent节点的右子树
                    parentNode.right=null;
                    return;
                }else {
                    System.out.println("未知错误001");
                }

            }


        } else if (targetNode.left!=null&&targetNode.right!=null){//3.删除有两科颗子树的节点
            //好像不用考虑root的情况
            //已经是target的左右子树不为空的情况了 不需要再判断下去
            //按照左边来
            if(mode==-1){
                //编写一个方法
                // 传进去target.left 然后遍历寻找这个target的左子树里最大的数
                // 删除这个最大的数的叶子结点 并返回这个最大的数
                int leftMax=getLeftMax(targetNode.left);
                targetNode.value=leftMax;
            }else {
                int rightMin=getRightMin(targetNode.right);
                targetNode.value=rightMin;
            }
            return;
        }else {//最后一种情况 2.删除只有一颗子树的节点
            Node parentNode = searchParent(value);
            if(targetNode.left!=null){ //target的左子树不为空
                if (parentNode==null){//应该是根节点
                    //直接移动光标
                    root=targetNode.left;
                }else {//parentNode不为空
                    //再看看 target是parent的哪边子树
                    //判断左边
                    if(parentNode.left!=null&&parentNode.left.value==targetNode.value){
                        parentNode.left=targetNode.left;
                    }else if(parentNode.right!=null&&parentNode.right.value==targetNode.value){//判断右边
                        parentNode.right=targetNode.left;

                    }else {
                        System.out.println("未知错误0021");
                    }
                }

            }else {//target的右子树不为空
                if (parentNode==null){//应该是根节点
                    //直接移动光标
                    root=targetNode.right;
                }else {//parentNode不为空
                    //再看看 target是parent的哪边子树
                    //判断左边
                    if(parentNode.left!=null&&parentNode.left.value==targetNode.value){
                        parentNode.left=targetNode.right;
                    }else if(parentNode.right!=null&&parentNode.right.value==targetNode.value){//判断右边
                        parentNode.right=targetNode.right;

                    }else {
                        System.out.println("未知错误0022");
                    }
                }

            }
            return;
        }




    }

    private int getRightMin(Node node) {
        int min;
        while (true){
            if(node.left!=null){
                node=node.left;
            }
            min=node.value;
            //用回功能就行
            delNode(min);
            return min;
        }
    }

    /**
     *
     * @param node 这个是传进来的target.left  target的左子树
     * @return
     */
    private int getLeftMax(Node node) { //之后一直找就行
        int max;
        while (true){
            if(node.right!=null){
                node=node.right;
            }
            max=node.value;
            //用回功能就行
            delNode(max);
            return max;
        }
    }

}
