package algorithm.test17.greed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法
 * 核心： 每次找到map中与allcity交集最多的那个key
 * @author YJ Lan
 * @create 2020-05-08-20:35
 */
public class greedAlgorithm {

    public static void main(String[] args) {
        HashMap<String,HashSet<String>> map = new HashMap<String,HashSet<String>>();
        // 盛放所有城市
        HashSet<String> allcity = new HashSet<>();
        //初始化
        init(map, allcity);
        //贪心算法
        greed(map, allcity);

    }

    private static void greed(HashMap<String, HashSet<String>> map, HashSet<String> allcity) {
        //临时存储
        HashSet<String> tool = new HashSet<>();
        //临时存储maxkey
        int maxsize = 0;

        //选择的key
        ArrayList<String> select = new ArrayList<>();

        String maxkey = null;
        while (allcity.size()>0) {
            maxkey = null;
            maxsize = 0;
            for (String key : map.keySet()) {
                //清除此临时存储交集集合
                tool.clear();
                tool = new HashSet<>();
                HashSet<String> index = map.get(key);
                tool.addAll(index);
                tool.retainAll(allcity); //去两个的交集给tool

                //体现贪心算法： 每次都取交集最多的那个key
                if (tool.size()>0 && (maxkey == null || tool.size() > maxsize)) {
                    maxkey = key;
                    //记录最大的key 交集个数
                    maxsize = tool.size();
                }

            }
            if (maxkey != null) {
                select.add(maxkey);
                allcity.removeAll(map.get(maxkey));
            }
        }

        select.stream().forEach(item->{
            System.out.println(item);
        });
    }

    /**
     * 初始化map数据
     * @param map
     * @param allcity
     */
    private static void init(HashMap<String, HashSet<String>> map, HashSet<String> allcity) {
        HashSet<String> k1 = new HashSet<>();
        k1.add("北京");
        k1.add("上海");
        k1.add("天津");
        HashSet<String> k2 = new HashSet<>();
        k2.add("广州");
        k2.add("北京");
        k2.add("深圳");
        HashSet<String> k3 = new HashSet<>();
        k3.add("成都");
        k3.add("上海");
        k3.add("杭州");
        HashSet<String> k4 = new HashSet<>();
        k4.add("上海");
        k4.add("天津");
        HashSet<String> k5 = new HashSet<>();
        k5.add("杭州");
        k5.add("大连");
        map.put("k1", k1);
        map.put("k2", k2);
        map.put("k3", k3);
        map.put("k4", k4);
        map.put("k5", k5);
        allcity.addAll(k1);
        allcity.addAll(k2);
        allcity.addAll(k3);
        allcity.addAll(k4);
        allcity.addAll(k5);
    }


}
