/*
package BinaryTree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HuffmanTreeDemo {

    public static void main(String[] args) {
        //wpl最小 叶子结点
        String n1="wo cao";
        byte[] bytes = n1.getBytes();
        System.out.println(Arrays.toString(bytes));
        //createHuffmanTree();
    }

    private static void createHuffmanTree() {
        //正序
        //int[] arr={1,3,6,7,8,13,29};
        //乱序
        int[] arr={7,8,13,1,3,6,29};

        createHuffmanTree(arr);
    }

    private static void createHuffmanTree(int[] arr) {

        List<HuffmanNode> nodes=new ArrayList();
        for (int i: arr) {
            nodes.add(new HuffmanNode(i));
        }
        System.out.println("排序前:"+nodes);
        //是这样 不是nodes.sort()
        Collections.sort(nodes);
        System.out.println("排序后:"+nodes);

        while (nodes.size()>1){
            HuffmanNode tmpLeft = nodes.get(0);
            HuffmanNode tmpRight = nodes.get(1);
            HuffmanNode parent=new HuffmanNode(tmpLeft.value+tmpRight.value );
            parent.leftNode=tmpLeft;
            parent.rightNode=tmpRight;

            //先删第二个 不然先删第一个会报错
            //或者删除指定元素
            nodes.remove(tmpLeft);
            nodes.remove(tmpRight);

            nodes.add(parent);
            //因为加入parent之后可能乱序 继续sort一下
            Collections.sort(nodes);
            //最后nodes就剩下一个parent了
        }
        System.out.println("最终:"+ nodes);

        //创建哈夫曼树
        HuffmanTree huffmanTree=new HuffmanTree(nodes.get(0));
        System.out.println("前序遍历开始:");
        huffmanTree.preOrder();
    }


}

class HuffmanTree{

    private HuffmanNode root;

    public HuffmanTree(HuffmanNode root) {
        this.root = root;
    }

    public void preOrder() {
        if(root!=null){
            this.root.preOrder();
        }else {
            System.out.println("哈夫曼树root节点为空");
        }
    }

}

class HuffmanNode implements Comparable<HuffmanNode>{

    int value;
    HuffmanNode leftNode;
    HuffmanNode rightNode;


    //前序遍历
    public void preOrder(){
        System.out.println(this);
        //if 而不是while   if是递归用法 而while是死循环
        if (this.leftNode!=null){
            this.leftNode.preOrder();
        }

        if (this.rightNode!=null){
            this.rightNode.preOrder();
        }

    }

    public HuffmanNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "value=" + value +
                '}';
    }


    @Override
    public int compareTo(HuffmanNode o) {
        //传进来一个节点和本节点对比
        //从大到小
        //return ;
        //从小到大
        return this.value-o.value;
    }
}*/
