package snc.boot.util.es;

import com.alibaba.fastjson.JSONObject;
import snc.boot.util.FinalTable;
import snc.boot.util.common.Router;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class Test {
    @org.junit.Test
    public void stest() {
        try {
            ElasticsearchService service = new ElasticsearchService("elasticsearch", "127.0.0.1", 9300);
            ESQueryBuilderConstructor constructor = new ESQueryBuilderConstructor();
            List<String> lists = new ArrayList<>();
            lists.add("c_name");
            lists.add("c_type");
            constructor.mutilsearch(new ESQueryBuilders().multiMatch("苹果",lists).range("c_price",6000,6200));
            constructor.setSize(10);  //查询返回条数，最大 10000
            constructor.setFrom(1);//设置页数
            constructor.setDesc("c_hot");
            List<Map<String, Object>> list = service.search("commodity", "commodity", constructor);
            System.out.println(list.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ElasticsearchService service8 = new ElasticsearchService(FinalTable.ElasticsearchService_ClusterNmae, FinalTable.ElasticsearchService_IP, FinalTable.ElasticsearchService_PORT);
//        ESQueryBuilderConstructor constructor8 = new ESQueryBuilderConstructor();
//
//        List<String> lists8 = new ArrayList<>();
//        lists8.add(FinalTable.COMMODITY_TYPE);
//        constructor8.mutilsearch(new ESQueryBuilders().multiMatch("数码产品", lists8));
//        constructor8.setSize(FinalTable.ES_SIZE_COMMODITY);
//        constructor8.setFrom(0);
////        constructor8.setFrom((Integer.parseInt(o8.getString(FinalTable.COMMODITY_PAGE)) - 1) * 10);
//
//        List<Map<String, Object>> list8 = service8.search(FinalTable.ES_INDEX_COMMODITY, FinalTable.ES_TYPE_TYPE, constructor8);
//        List<Map<String, Object>> list9 = new ArrayList<>();
//        Date dNow8 = new Date();
//        SimpleDateFormat ft8 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String json8 = null;
//        if (list8.size() != 0) {
//            constructor8.mutilsearch(new ESQueryBuilders().multiMatch("数码产品", lists8));
//            constructor8.setDesc(FinalTable.COMMODITY_HOT);
//            list9 = service8.search("commodity", "commodity", constructor8);
//            json8 = "{\"res\":\"true\",\"list\":" + "[" + "type:" + list8 + "commodity:" + list9 + "]" + ",\"date\":" + ft8.format(dNow8) + "}";
//            System.out.println(json8);
//        } else {
//            lists8.add(FinalTable.COMMODITY_NAME);
//            constructor8.mutilsearch(new ESQueryBuilders().multiMatch("数码产品", lists8));
//            constructor8.setDesc(FinalTable.COMMODITY_HOT);
//            constructor8.setSize(FinalTable.ES_SIZE_COMMODITY);
//            constructor8.setFrom(0);
//
//            list9 = service8.search("commodity", "commodity", constructor8);
//            json8 = "{\"res\":\"true\",\"list\":" + list9.toString() + ",\"date\":" + ft8.format(dNow8) + "}";
//            System.out.println(json8);
//        }
//        System.out.println("xc");
//        ElasticsearchService service = new ElasticsearchService("elasticsearch", "127.0.0.1", 9300);
//        MultiMatchQueryBuilder builder = QueryBuilders.multiMatchQuery("三星", "c_type", "c_details","c_brand");
//        SearchResponse response = service.getClient().prepareSearch("commodity").setTypes("commodity")
//                .setQuery(builder)
//                .setSize(3)
//                .get();

//        MultiSearchRequest request = service.getClient().multiSearch()

//        SearchHits hits = response.getHits();
//
//        for (SearchHit hit : hits){
//            System.out.println(hit.getSourceAsString());
//
//            Map<String, Object> map = hit.getSourceAsMap();
//
//            for (String key : map.keySet()){
//                System.out.println(key +" = " +map.get(key));
//            }
//        }


    }
}
