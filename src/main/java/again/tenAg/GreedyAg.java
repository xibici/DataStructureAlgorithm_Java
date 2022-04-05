package again.tenAg;
import java.util.*;
public class GreedyAg {

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

        //不同的K方案对应的广播的地区  {k1: k1对应的广播区域的set,k2.........}
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

        while (allAreas.size()!=0){

            int max=0;
            maxValue=null;

            for (Map.Entry<String,HashSet<String>> entry:broadcasts.entrySet()) {
                HashSet<String> valueSet = entry.getValue();
                //最好做下隔离 新变量接收
                valueSet.retainAll(allAreas);

                //原来写法
                /*if(tmpSet.size()>0 ){
                    if(maxValue==null || (  tmpSet.size() > maxSet.size())){
                        //maxSet=tmpSet;  不需要这一步
                        maxValue=oneK;
                    }
                }*/

                if(valueSet.size()>max){
                    max=valueSet.size();
                    maxValue=entry.getKey();

                }

            }

            if(maxValue!=null){
                selects.add(maxValue);
                allAreas.removeAll(broadcasts.get(maxValue));
            }

        }


        System.out.println(selects);

    }



}
