package ten10;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GreedyAlgorithm {

    public static void main(String[] args) {
        //存放所有城市
        HashSet<String> allAreas=new HashSet();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");


        //不同K1 方案对应的广播的地区
        Map<String,HashSet<String>> broadcasts=new HashMap<>();

        //临时储存的set 遍历每个k的时候用来存放k对应的广播地区
        HashSet<String> tmpSet=new HashSet();

        //存放选择的电台集合  被选择了的k1 k2等等
        ArrayList<String> selects=new ArrayList<>();

        //存放最大的那个Max k?
        String maxValue=null;

        HashSet<String> hashSet1=new HashSet();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2=new HashSet();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");


        HashSet<String> hashSet3=new HashSet();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4=new HashSet();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5=new HashSet();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcasts.put("K1",hashSet1);
        broadcasts.put("K2",hashSet2);
        broadcasts.put("K3",hashSet3);
        broadcasts.put("K4",hashSet4);
        broadcasts.put("K5",hashSet5);


        while (allAreas.size()>0){ //allareas没有被清空
            maxValue=null;

            for (String oneK:broadcasts.keySet()) {


                tmpSet=broadcasts.get(oneK);
                //取两个集合的交集并把交集的结果给tmpSet
                tmpSet.retainAll(allAreas);

                HashSet maxSet=null;
                if(maxValue!=null){
                    //获取需要对比的maxSet
                    maxSet=broadcasts.get(maxValue);
                    //对maxSet进行allAreas的交集 防止只有有效个数其实只有个位数
                    maxSet.retainAll(allAreas);
                }


                //老师这里代码有问题   tmpSet.size() > broadcasts.get(maxValue).size()的话
                // 如果后面get(maxvalue)取得的hashSet只是与allArea有一个交集的话 而不是整体大小 代码就会出现严重问题
                //if(tmpSet.size()>0 && (maxValue==null || (  tmpSet.size() > broadcasts.get(maxValue).size() ) )){
                if(tmpSet.size()>0 ){
                    if(maxValue==null || (  tmpSet.size() > maxSet.size())){
                        //maxSet=tmpSet;  不需要这一步
                        maxValue=oneK;
                    }
                }



            }

            if(maxValue!=null){
                //选择里加入优秀解
                selects.add(maxValue);
                allAreas.removeAll(broadcasts.get(maxValue));
                //然后清空 maxValue里面的对应的set
                //broadcasts.put(maxValue,null);

            }
        }

        //所有结束 输出她
        System.out.println(selects);



    }

}
