package again.dataStructure.tree;

import java.util.*;

public class HuffmanTreeDemoAg {

    public static Map<Byte, String> huffmanCodeMap=new HashMap<>();
    public static Map<Character, String> huffmanCodeMapChar=new HashMap<>();
    public static byte[] zipCodeBytes;

    public static void main(String[] args) {

        String str="i like like like java do you like a java";
        //String str="i like like like567t54ttguy87t7stgfbgnjkug8it7665yrtj,iugy7d5yrx m, k8guoi7fxhdfbchjk.io88thfgn vbmkjiu java do you like a j83484884345678aa213343154y6364265467456i876i7o7ikjgyyjkfjfcgnfdgxfga454663463aa488a.151435211";
        //String str="13513513";
        //String str="Firstly, it saves people's time to pay bills. The use of online payment is carried out with a smart phone. Especially when we go to the supermarket, there are always a lot of people waiting in the long line. The process of costing customers' time is to take out the cash or use the credit card, because both customers and cashiers need to count the money or check the bills. But now the cashier can just scan the phone and the bill will be paid and the customer can check quickly.";

        //创作huffmanCode的01 map
        int zipCodeLength = zipCode(str);
        String deCodeStr = deCode(zipCodeBytes,huffmanCodeMap,zipCodeLength);
        //System.out.println(Arrays.toString(zipCodeBytes));


    }

    private static String deCode(byte[] zipCodeBytes,Map<Byte, String> huffmanCodeMap,int zipCodeLength) {
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < zipCodeBytes.length; i++) {
            byte oneBye=zipCodeBytes[i];
            String binaryString="";
            if(i==zipCodeBytes.length-1){//最后一位小于 8位 直接返回
                binaryString= Integer.toBinaryString((int) oneBye);
                //需要根据zipcodelength末尾补全
                int difference=zipCodeLength%8-binaryString.length();
                //System.out.println(zipCodeLength%8+"*****");
                //System.out.println(difference+"*****");
                StringBuilder sbDifference=new StringBuilder(binaryString);
                if(difference>0){
                    for (int j = 0; j < difference; j++) {
                        sbDifference.insert(0,"0");
                    }
                }
                binaryString=sbDifference.toString();
            }else {//正数的时候需要补0
                int o=(int)oneBye | 256;
                binaryString= Integer.toBinaryString((int) o);
                binaryString = binaryString.substring(binaryString.length() - 8);
            }

            //System.out.println(binaryString);

            sb.append(binaryString);
        }
        String decodeStr=sb.toString();
        System.out.println(decodeStr);

        String resStr = translateDecodeStr(decodeStr, huffmanCodeMap);

        //再翻译成原文本

        return resStr;
    }

    public static String translateDecodeStr(String decodeStr,Map<Byte, String> huffmanCodeMap){
        Map<String, Byte> reverseHuffmanCodeMap=getReverseHuffmanCodeMap(huffmanCodeMap);

        int index=0;
        int nextArea=1;
        StringBuilder sb=new StringBuilder();
        System.out.println(reverseHuffmanCodeMap);

        while (index<decodeStr.length()){
            String substring;
            substring = decodeStr.substring(index, index+nextArea);

            Byte matchByte=reverseHuffmanCodeMap.get(substring);
            if(matchByte!=null){//直接返回文本 也可以选择返回最初的文本逐个的bytes\
                char c=(char)(int)matchByte;
                sb.append(c);
                index=index+nextArea;
                nextArea=1;
            }else {
                nextArea++;
            }

        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private static Map<String, Byte> getReverseHuffmanCodeMap(Map<Byte, String> huffmanCodeMap) {
        Map<String, Byte> reverseHuffmanCodeMap=new HashMap<>();
        for (Map.Entry<Byte, String> entry:huffmanCodeMap.entrySet()) {
            reverseHuffmanCodeMap.put(entry.getValue(),entry.getKey());
        }
        return reverseHuffmanCodeMap;
    }


    /**
     * 本来是返回byte[] 但是会引发问题 于是改成byte[]全局变量 最后返回原压缩文本的长度
     * @param str
     * @return
     */
    private static int zipCode(String str) {
        HuffmanTreeAg huffmanTree = createHuffmanTree(str);
        generateHuffmanCode(huffmanTree.root,"");
        String translateStr = translateStr(str, huffmanCodeMap);
        System.out.println(translateStr);
        //byte[] transbyteArray=spliteArrays(translateStr);
        zipCodeBytes=spliteArrays(translateStr);
        return translateStr.length();
    }

    private static byte[] spliteArrays(String translateStr) {
        int len=(translateStr.length()+7)/8;
        byte[] bytes=new byte[len];
        int index=0;
        String substring="";
        for (int i = 0; i < translateStr.length(); i=i+8) {
            if(i+8>translateStr.length()){
                substring = translateStr.substring(i);
            }else {
               substring = translateStr.substring(i, i + 8);
            }
            int transbyte = Integer.parseInt(substring, 2);
            bytes[index]=(byte) transbyte;
            index++;
        }
        return bytes;

    }

    private static String translateStr(String str, Map<Byte, String> huffmanCodeMap) {
        StringBuilder sb=new StringBuilder();
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(huffmanCodeMap.get(bytes[i]));
        }
        return sb.toString();
    }

    private static void generateHuffmanCode(HNode node,String str) {

        if(node!=null){
            if(node.strByte==null){
                generateHuffmanCode(node.left,str+"0");
                generateHuffmanCode(node.right, str + "1");

            }else {//左右子树都为空 叶子结点
                huffmanCodeMap.put(node.strByte,str);
                huffmanCodeMapChar.put((char)(int)node.strByte,str);
            }
        }


    }

    private static HuffmanTreeAg createHuffmanTree(String str) {
        byte[] bytes = str.getBytes();
        /*int[] arr={13,7,8,3,29,6,1};
        Arrays.sort(arr);*/
        //做成频度countMap
        Map<Byte,Integer> countMap=new HashMap<>();

        for (int i = 0; i < bytes.length; i++) {
            Integer count=countMap.get(bytes[i]);
            if(count==null){
                countMap.put(bytes[i],1);
            }else {
                countMap.put(bytes[i],count+1);
            }
        }
        System.out.println(countMap);

        List<HNode> nodes=new ArrayList<>();
        for (Map.Entry entry:countMap.entrySet()) {
            nodes.add(new HNode((byte)entry.getKey(),(int)entry.getValue()));
        }
        Collections.sort(nodes);
        HuffmanTreeAg huffmanTreeAg=createHuffmanTree(nodes);
        //huffmanTreeAg.preOrder();
        return huffmanTreeAg;
    }


    private static HuffmanTreeAg createHuffmanTree(List<HNode> nodes) {
        List<HNode> resNodes = getHuffman(nodes);
        HNode hNode = resNodes.get(0);
        HuffmanTreeAg huffmanTreeAg=new HuffmanTreeAg(hNode);
        return huffmanTreeAg;
    }

    public static List<HNode> getHuffman(List<HNode> nodes){

        while (nodes.size()>1){
            HNode r1 = nodes.remove(0);
            HNode r2 = nodes.remove(0);

            //增加的 用null
            HNode mul = new HNode(null,r1.value + r2.value);
            mul.left=r1;
            mul.right=r2;
            nodes.add(mul);
            Collections.sort(nodes);
        }
        return nodes;

    }


}


class HuffmanTreeAg{

    HNode root;

    public HuffmanTreeAg(HNode root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "HuffmanTreeAg{" +
                "root=" + root +
                '}';
    }

    public void preOrder(){
        if(root==null){
            System.out.println("root为null");
        }else {
            this.root.preOrder();
        }
    }
}

class HNode implements Comparable<HNode>{
    Byte strByte;
    int value;
    HNode left;
    HNode right;

    public HNode(Byte strByte, int value) {
        this.strByte = strByte;
        this.value = value;
    }

    @Override
    public String toString() {
        return "HNode{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(HNode o) {
        return this.value-o.value;
    }

    public void preOrder(){
        System.out.print(this.value+"->");

        if(this.left!=null){
            this.left.preOrder();
        }

        if(this.right!=null){
            this.right.preOrder();
        }
    }

}


