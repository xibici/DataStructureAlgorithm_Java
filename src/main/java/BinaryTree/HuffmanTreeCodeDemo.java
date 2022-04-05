package BinaryTree;

import java.util.*;


/**     哈夫曼编码压缩流程
 * 1.
 *
 */
public class HuffmanTreeCodeDemo {
    //等下解码后赋值给他
    private static Map<Byte, String> huffmanCodes;

    public static void main(String[] args) {
        //wpl最小 叶子结点
        String str="i like like like java do you like a java";
        /*String str="iasdlike like like java do you like a java";
        System.out.println(str.substring(0,1));
        System.out.println(str.substring(0,2));*/
        //System.out.println("文本长度:"+str.length());

        byte[] zipBytes = zip(str);
        System.out.println("压缩成byte数组后:"+Arrays.toString(zipBytes));
        byte[] decodeBytes = decode(huffmanCodes, zipBytes);
        for (int i = 0; i < decodeBytes.length; i++) {
            System.out.print((char) decodeBytes[i]);

        }
        //System.out.println(Arrays.toString(decodeBytes));
        //System.out.println(new String(decodeBytes));


    }


    /**
     *
     * @param huffmanCodes byte对应的二进制 {'w':01,...}
     * @param huffmanBytes 这里是codes的map按照文本顺序排序后进行编码 再按8位分割成每个的byte的数组
 *                     即:[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     * @return 返回原来字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        StringBuilder stringBuilder=new StringBuilder();
        //1.先把huffmanBytes转换成原来的长字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            String ch;
            if(i== huffmanBytes.length-1){
                ch=parseOneByte(true,huffmanBytes[i]);
            }else {
                ch=parseOneByte(false,huffmanBytes[i]);
            }
            stringBuilder.append(ch);
        }
        //System.out.println(stringBuilder.toString());
        //翻转一波huffmanCodes
        Map<String,Byte> revHuffmanCodes=new HashMap<>();
        for (Map.Entry<Byte,String> entry :huffmanCodes.entrySet()) {
            revHuffmanCodes.put(entry.getValue(),entry.getKey());

        }

        //得到了原来zip的string字符串  下面解码变成原文本
        List<Byte> resList=new ArrayList<>();
        List<Character> resChList=new ArrayList<>();
        for (int i = 0; i < stringBuilder.length() - 1; ) {
            int count=1;
            //region 写法1
            while(true){
                //{'w':"010100",...}
                Byte oneChar=revHuffmanCodes.get(stringBuilder.substring(i,i+count));
                //Byte包装类可以接受Null
                if(oneChar==null){
                    count++;
                }else {
                    i=i+count;
                    resList.add(oneChar);
                    resChList.add((char)(int)oneChar);
                    break;
                }
            }
            //endregion 写法1

            //region 写法2
            /*boolean flag=true;
            Byte oneChar=null;
            while(flag){
                //{'w':"010100",...}
                oneChar=revHuffmanCodes.get(stringBuilder.substring(i,i+count));
                //Byte包装类可以接受Null
                if(oneChar==null){
                    count++;
                }else {
                    flag=false;

                }
            }
            i=i+count;
            resList.add(oneChar);
            resChList.add((char)(int)oneChar);
            break;*/
            //endregion 写法2

        }

        /*System.out.println(resList);
        //System.out.println(resChList);
        for (Character ch:resChList) {
            System.out.print(ch);
        }*/

        byte[] bytes=new byte[resList.size()];
        for (int j = 0; j < resList.size(); j++) {
            bytes[j]=resList.get(j);
        }
        return bytes;

    }

    /**
     *
     * @param flag 判断是否是末尾的byte 如果是就直接返回不需要补位
     * @param b   需要解析的byte 如数组第一个元素 -80
     */
    private static String parseOneByte(boolean flag,byte b){
        //不转inte不能与运算
        int temp=b;
        //System.out.println(b);
        //b=(byte)(256|b);
        if (flag==false){
            temp |=256;
        }

        //负数返回的是补码   原码反码补码      正数返回本身
        String binaryString = Integer.toBinaryString(temp);

        String resString=binaryString;
        //如果flag为真 不补位 直接返回
        if (flag==false){
            resString=binaryString.substring(binaryString.length()-8);
        }

        return resString;

    }

    //重载 直接获得 封装
    private static byte[] zip(String str){
        //将文本分成byte数组
        byte[] bytes = str.getBytes();
        char[] chars=new char[bytes.length];
        System.out.println(Arrays.toString(bytes));
        for (int i = 0; i < bytes.length; i++) {
            //System.out.print((char)(int)bytes[i]);
            //System.out.print((char)(int)bytes[i]);
            chars[i]=(char)(int)bytes[i];
        }
        System.out.println(Arrays.toString(chars));
        //拿到节点 创建哈夫曼树
        List<HuffmanNode> nodes = createNodes(bytes);
        //System.out.println(nodes);

        HuffmanTree huffmanTree = createHuffmanTree(nodes);
        //得到哈夫曼树 进行编码
        huffmanCodes=generateHuffmanCodey(huffmanTree);
        System.out.println(huffmanCodes);
        //得到编码 压缩成数组
        //左参数对应 原本顺序的byte数组 右边是byte数组编码后
        byte[] zipArray=zip(bytes,huffmanCodes);
        //System.out.println(Arrays.toString(zipArray));
        return zipArray;
    }

    /**
     * 
     * @param bytes
     * @param huffmanCodes
     * @return
     */
    private static byte[] zip(byte[] bytes,Map<Byte, String> huffmanCodes) {
        StringBuilder sb=new StringBuilder();
        for (Byte b:bytes) {
            sb.append(huffmanCodes.get(b));
        }
        System.out.println(sb.toString());
        //System.out.println(sb.length());
        //bytes是字符数组别搞乱了
        //1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
        int len= (sb.length()+7)/8;
        byte[] res=new byte[len];
        int index=0;
        //每隔8个进行一次分割
        for (int i=0;i< sb.length();i+=8){
            String tmpByte;
            if(i+8>sb.length()){//一个参数就是直接到末尾
                tmpByte=sb.substring(i);
            }else {
                tmpByte=sb.substring(i,i+8);
            }

            //意思是以二进制方式这个数
            res[index]=(byte) Integer.parseInt(tmpByte,2);
            index++;
        }

        return res;

    }

    public static List<HuffmanNode> createNodes(byte[] bytes){

        int[] arr=new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            arr[i]=bytes[i];
        }

        //{'w','o',....}
        //也会变成int数组 byte数组{ 119,111,....}
        //System.out.println(Arrays.toString(bytes));
        //System.out.println(Arrays.toString(arr));
        // createHuffmanTree(arr);

        List<HuffmanNode> nodes=new ArrayList<>();
        //'111'=2 也就是'w'=2
        Map<Byte,Integer> countMap=new HashMap();
        //计算字母和权重
        for (int i = 0; i < bytes.length; i++) {
            //int count=countMap.get(bytes[i]);
            //用包装类  可以产生null
            Integer count=countMap.get(bytes[i]);
            if (count==null){
                countMap.put(bytes[i],1 );
            }else {
                countMap.put(bytes[i],count+1 );
            }
        }
        //有了这个Map做成Nodes
        //:后面是countMap.entrySet()而不是countMap
        for (Map.Entry<Byte,Integer> entry:countMap.entrySet()) {

            nodes.add(new HuffmanNode(entry.getValue(),entry.getKey()));
        }
        //同时应该排好序
        Collections.sort(nodes);
        //System.out.println(nodes);
        return nodes;
    }

    private static HuffmanTree createHuffmanTree(List<HuffmanNode> nodes) {

        while (nodes.size()>1){
            HuffmanNode tmpLeft = nodes.get(0);
            HuffmanNode tmpRight = nodes.get(1);
            //合成的父节点没有数字 是null
            HuffmanNode parent=new HuffmanNode(tmpLeft.weight+tmpRight.weight,null);
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
        //System.out.println("最终:"+ nodes);

        //创建哈夫曼树
        HuffmanTree huffmanTree=new HuffmanTree(nodes.get(0));
        //System.out.println("前序遍历开始:");
        //huffmanTree.preOrder();
        return huffmanTree;

    }

    private static Map<Byte,String> generateHuffmanCodey(HuffmanTree huffmanTree) {
        huffmanTree.getCodes(huffmanTree.root,"",new StringBuilder());
        //哈夫曼编码 的Map
        return huffmanTree.huffmanCodes;
    }

}


class HuffmanTree{

    HuffmanNode root;

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

    Map<Byte,String> huffmanCodes=new HashMap<>();

    StringBuilder stringBuilder=new StringBuilder();

    public void getCodes(HuffmanNode node,String code,StringBuilder stringBuilder) {

        StringBuilder stringBuilder2=new StringBuilder(stringBuilder);

        stringBuilder2.append(code);

        if(node!=null){  //传进来的node不为空的话就是继续
            if(node.data==null){//该节点不为叶子结点
                //左子树为0 右子树1   哈夫曼编码
                //左递归
                getCodes(node.leftNode,"0",stringBuilder2);
                //右递归
                //左递归node的左子树结束后 回来node  用node继续对右子树递归
                getCodes(node.rightNode,"1",stringBuilder2);
            }else {//叶子结点就直接加入Map
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }

    }



}

class HuffmanNode implements Comparable<HuffmanNode>{

    int weight;//权重
    HuffmanNode leftNode;
    HuffmanNode rightNode;
    Byte data; //'w'=>119  'o'=>111


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

    public HuffmanNode(int weight, Byte data) {
        this.weight = weight;
        this.data = data;
    }

    @Override
    public String toString() {
        if(this.data==null){
            return "HuffmanNode{" +
                    "weight=" + weight +
                    ", data=" + data +
                    '}';
        }else {
            return "HuffmanNode{" +
                    "weight=" + weight +
                    //", data=" + data +   改成字符也输出
                    ", data="+"("+((char)(int)data)+")" +data+
                    '}';
        }

    }

    @Override
    public int compareTo(HuffmanNode o) {
        //传进来一个节点和本节点对比
        //从大到小
        //return ;
        //从小到大

        return this.weight-o.weight;
    }
}