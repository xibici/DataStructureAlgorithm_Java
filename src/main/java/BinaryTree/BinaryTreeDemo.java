package BinaryTree;


public class BinaryTreeDemo {

    public static void main(String[] args) {
        BinaryT binaryT=new BinaryT();
        //node1 rootNode
        HeroNode rootNode=new HeroNode(1,"李白");
        HeroNode node2=new HeroNode(2,"白居易");
        HeroNode node3=new HeroNode(3,"杜甫");
        HeroNode node4=new HeroNode(4,"李清照");
        HeroNode node5=new HeroNode(5,"李小龙");
        HeroNode node6=new HeroNode(6,"德玛");
        HeroNode node10=new HeroNode(10,"你爹");
        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setRight(node6);
        node3.setLeft(node10);

        rootNode.setLeft(node2);
        rootNode.setRight(node3);
        binaryT.root=rootNode;

        //doOrder(binaryT);
        //doFind(binaryT);

    }

    private static void doFind(BinaryT binaryT) {
        int sid=3;
        //前序-> id=10  6次  id=3  5次
        binaryT.findPreOrder(sid);
        //中序-> id=10  5次  id=3  6次
        binaryT.findInfixOrder(sid);
        //后序-> id=10  4次  id=3  6次
        binaryT.findPostOrder(sid);
    }

    /*private static void doDel(BinaryT binaryT) {
        int sid=3;
        //前序-> id=10  6次  id=3  5次
        binaryT.delPreOrder(sid);
        //中序-> id=10  5次  id=3  6次
        binaryT.deldInfixOrder(sid);
        //后序-> id=10  4次  id=3  6次
        binaryT.delPostOrder(sid);
    }
*/
    private static void doOrder(BinaryT binaryT) {
        System.out.print("前序:");
        binaryT.preOrder();
        System.out.println();
        System.out.print("中序:");
        binaryT.infixOrder();
        System.out.println();
        System.out.print("后序:");
        binaryT.postOrder();
        System.out.println();
    }
}

class BinaryT{
    HeroNode root;

    public void preOrder(){
        if(root!=null){
            root.preOrder();
        }else {
            System.out.println("root为空 二叉树没有数组");
        }
    }
    public void infixOrder(){
        if(root!=null){
            root.infixOrder();
        }else {
            System.out.println("root为空 二叉树没有数组");
        }
    }
    public void postOrder(){
        if(root!=null){
            root.postOrder();
        }else {
            System.out.println("root为空 二叉树没有数组");
        }
    }

    public void findPreOrder(int id){
        if(root!=null){
            HeroNode preOrder = root.findPreOrder(id);
            System.out.println(preOrder);
        }else {
            System.out.println("root为空 二叉树没有数组");
        }
    }


    public void findInfixOrder(int id) {
        if(root!=null){
            HeroNode infixOrder = root.findInfixOrder(id);
            System.out.println(infixOrder);
        }else {
            System.out.println("root为空 二叉树没有数组");
        }
    }
    public void findPostOrder(int id) {
        if(root!=null){
            HeroNode postOrder = root.findPostOrder(id);
            System.out.println(postOrder);
        }else {
            System.out.println("root为空 二叉树没有数组");
        }
    }
}

class HeroNode{
    private String name;
    private int id;

    private HeroNode left;
    private HeroNode right;

    public HeroNode( int id,String name) {
        this.name = name;
        this.id = id;
    }

    //region Order遍历
    public void preOrder(){
        System.out.printf("id:%d name:%s=>",id,name);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }

    public void infixOrder(){
        if (this.left!=null){
            this.left.infixOrder();
        }
        System.out.printf("id:%d name:%s=>",this.id,this.name);
        if (this.right!=null){
            this.right.infixOrder();
        }
    }


    public void postOrder(){

        if (this.left!=null){
            this.left.postOrder();
        }
        if (this.right!=null){
            this.right.postOrder();
        }
        System.out.printf("id:%d name:%s=>",this.id,this.name);
        //System.out.printf("id:%d name:%s=>",this.id,this.name);

    }
    //endregion Order遍历


    //region find遍历查找
    public HeroNode findPreOrder(int id){
        System.out.print("前序调用1次   ");
        if(this.id==id){
            return this;
        }
        //当前父节点无 找子
        HeroNode resHeroNode=null;
        if(this.left!=null){//左子树不空遍历
            //错误1:这里需要注意 要赋值 我一开始没赋值
            //这里也是找到后返回resHeroNode然后到错误2下面直接结束函数
            resHeroNode=this.left.findPreOrder(id);
        }
        //错误2:如果找到了必须马上返回 不然会进去向右子树遍历的循环 会出事 让他直接结束了
        if(resHeroNode!=null){
            return resHeroNode;
        }
        //左子树空了找右子树
        if(this.right!=null){//左子树不空遍历
            resHeroNode=this.right.findPreOrder(id);
        }
        //都找不到直接返回null了
        return resHeroNode;
    }

    public HeroNode findInfixOrder(int id){


        HeroNode resHeroNode=null;
        if(this.left!=null){//左子树不空遍历
            //错误1:这里需要注意 要赋值 我一开始没赋值
            //这里也是找到后返回resHeroNode然后到错误2下面直接结束函数
            resHeroNode=this.left.findInfixOrder(id);
        }

        //错误2:如果找到了必须马上返回 不然会进去向右子树遍历的循环 会出事 让他直接结束了
        if(resHeroNode!=null){
            return resHeroNode;
        }

        System.out.print("中序调用1次   ");
        if(this.id==id){
            return this;
        }

        //左子树空了找右子树
        if(this.right!=null){//左子树不空遍历
            resHeroNode=this.right.findInfixOrder(id);
        }
        //都找不到直接返回null了
        return resHeroNode;
    }

    public HeroNode findPostOrder(int id){
        //左然后右
        HeroNode resHeroNode=null;
        if(this.left!=null){//左子树不空遍历
            //错误1:这里需要注意 要赋值 我一开始没赋值
            //这里也是找到后返回resHeroNode然后到错误2下面直接结束函数
            resHeroNode=this.left.findPostOrder(id);
        }
        //错误2:如果找到了必须马上返回 不然会进去向右子树遍历的循环 会出事 让他直接结束了
        if(resHeroNode!=null){
            return resHeroNode;
        }
        //左子树空了找右子树
        if(this.right!=null){//左子树不空遍历
            resHeroNode=this.right.findPostOrder(id);
        }
        //都找不到直接返回null了
        if(resHeroNode!=null){
            return resHeroNode;
        }

        System.out.print("后序调用1次   ");
        if(this.id==id){
            resHeroNode= this;
        }
        return resHeroNode;
    }



    //endregion find排序查找


    @Override
    public String toString() {
        return "HeroNode{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }
}
