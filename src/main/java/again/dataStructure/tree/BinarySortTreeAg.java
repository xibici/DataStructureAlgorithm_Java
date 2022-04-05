package again.dataStructure.tree;

public class BinarySortTreeAg {
    public static void main(String[] args) {

        int[] arr={7,3,10,12,5,1,9};
        BSTree bsTree = new BSTree();
        for (int i = 0; i < arr.length; i++) {
            bsTree.add(new BSTNode(arr[i]));
        }

        //bsTree.infiOrder();

        bsTree.del(3);
        bsTree.del(7);


        bsTree.infiOrder();

    }

}

class BSTree{


    BSTNode root;


    public void add(BSTNode node){
        if(node==null){
            System.out.println("需要添加的节点为空");
            return;
        }
        if(this.root==null){
            //System.out.println("root为null");
            root=node;
        }else {
            this.root.add(node);
        }

    }

    public void infiOrder(){
        if(this.root==null){
            System.out.println("root为null");
        }else {
            this.root.infiOrder();
        }
    }

    public BSTNode searchParent(int value){
        if (root==null){
            System.out.println("root为null");
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    public BSTNode search(int value){
        if (root==null){
            System.out.println("root为null");
            return null;
        }else {
            return root.search(value);
        }
    }

    /**
     * @param node 传进来的这颗是目标的左子树
     * @return  返回最大值 int
     */
    public int getLeftMax(BSTNode node){
        //因为传进来的是左子树 所以需要找左子树的右子树才能找到最大值
        BSTNode tmp=node;
        int max=tmp.value;

        if(tmp==null){
            System.out.println("你传个空的进来干嘛");
            return -1;
        }

        while(tmp.right!=null){
            tmp=tmp.right;
            max=tmp.value;
        }

        return max;
    }


    /**
     * 循环方法而不是递归
     * @param value 传进来一个数 然后删除
     * @return
     */
    public void del(int value){

        if(this.root==null){
            System.out.println("root为null");
            return;
        }

        BSTNode target=search(value);
        if(target==null){
            return;
        }
        BSTNode parent=searchParent(value);
        //没有parent的情况 那就是节点是root节点  而且target找到了 不为空 证明了root是找到的节点
        if(root.left==null &&root.right==null){
            root=null;
            return;
        }

        //需要删除的是叶子结点
        if(target.left==null &&target.right==null){
            if (parent.left != null && parent.left.value == target.value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == target.value) {
                parent.right = null;
            } else{
                System.out.println("未知错误");
                return;
            }
        }else if(target.left!=null && target.right!=null){//需要删除的有左右子树
            //目标有两颗子树  可以找左子树最大或者右子树最小的替换这个值 然后删除最大的那棵树 其他不变
            int leftMax = getLeftMax(target.left);
            if(leftMax!=-1){
                del(leftMax);
                target.value=leftMax;
            }

        }else {//需要删除的只有一颗子树 左或者右
            //那就把子树移上来变成这个点
            //如果要删除的节点没有父节点但是有左右随便一棵子树
            if(parent==null){ //指针往下移  是root只有左右子树
                if(target.left!=null){
                    root=target.left;
                }else {
                    root=target.right;
                }
                return;
            }

            //如果parent的左子树是target
            if(parent.left!=null && parent.left.value==target.value){
                //看看target的子树是左边还是右边
                if(target.left!=null){
                    parent.left=target.left;
                }else {
                    parent.left=target.right;
                }
            }else if(parent.right!=null && parent.right.value==target.value){
                //看看target的子树是左边还是右边
                if(target.left!=null){
                    parent.right=target.left;
                }else {
                    parent.right=target.right;
                }
            }else {
                System.out.println("未知错误");
            }
            return;
        }


    }


}

class BSTNode{
    int value;
    BSTNode left;
    BSTNode right;

    public BSTNode(int value) {
        this.value = value;
    }

    public void add(BSTNode node){
        if(node==null){
            return;
        }

        if(node.value<=this.value ){
            if(this.left!=null){
                this.left.add(node);
            }else {
                this.left=node;
            }
        }else if(node.value>this.value ){
            if(this.right!=null){
                this.right.add(node);
            }else {
                this.right=node;
            }
        }

    }


    //!需要先搜索子和父节点
    public BSTNode search(int value){
        if(value==this.value){
            return this;
        }else if(value<this.value){
            if(this.left!=null){
                return this.left.search(value);
            }else {
                return null;
            }
        }else {//>
            if(this.right!=null){
                return this.right.search(value);
            }else {
                return null;
            }
        }
    }

    //!需要先搜索子和父节点
    public BSTNode searchParent(int value){
        //已经排除root的情况
        if(this.left!=null &&value<this.value){
            if(value==this.left.value){
                return this;
            }else {
                return  this.left.searchParent(value);
            }

        }else if(this.right!=null&&  value>this.value){
            if(value==this.right.value){
                return this;
            }else {
                return this.right.searchParent(value);
            }
        }else {//都为空
            return null;
        }


    }



    public void infiOrder(){//中序遍历 也是数组顺序

        if(this.left!=null){
            this.left.infiOrder();
        }

        System.out.print(this.value+"->");

        if(this.right!=null){
            this.right.infiOrder();
        }

    }

    public void preOrder(){//中序遍历 也是数组顺序
        System.out.print(this.value+"->");

        if(this.left!=null){
            this.left.infiOrder();
        }


        if(this.right!=null){
            this.right.infiOrder();
        }

    }

    public void postOrder(){//中序遍历 也是数组顺序

        if(this.left!=null){
            this.left.infiOrder();
        }


        if(this.right!=null){
            this.right.infiOrder();
        }
        System.out.print(this.value+"->");

    }



}