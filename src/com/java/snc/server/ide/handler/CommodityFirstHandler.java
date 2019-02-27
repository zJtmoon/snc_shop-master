package snc.server.ide.handler;


import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import org.springframework.stereotype.Controller;
import snc.boot.util.FinalTable;
import snc.boot.util.common.Router;
import snc.boot.util.es.ESQueryBuilderConstructor;
import snc.boot.util.es.ESQueryBuilders;
import snc.boot.util.es.ElasticsearchService;
import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class CommodityFirstHandler extends ChannelInboundHandlerAdapter{
    private static CommodityFirstHandler commodityFirstHandle;
    @PostConstruct
    public void init(){
        commodityFirstHandle = this;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fhr = (FullHttpRequest) msg;
        FullHttpResponse freq = null;
        String uri = fhr.uri();
        switch (uri) {
            //购物车
            case "/snc/shop/commodity":     //首页点击商品页时 会按一个权重排序，此时肯定不是按价钱排序，我们会设置一个热度，这是一个计算公式，后期完善这个公式   参数为 c_page:xxx
            JSONObject o = Router.getMessage(fhr);
            ElasticsearchService service = new ElasticsearchService(FinalTable.ElasticsearchService_ClusterNmae, FinalTable.ElasticsearchService_IP, FinalTable.ElasticsearchService_PORT);
            ESQueryBuilderConstructor constructor = new ESQueryBuilderConstructor();
            constructor.setSize(FinalTable.ES_SIZE_COMMODITY);  //查询返回条数，最大 10000
            constructor.setFrom((Integer.parseInt(o.getString(FinalTable.COMMODITY_PAGE))-1)*10);

            constructor.setDesc(FinalTable.COMMODITY_HOT);
            List<Map<String, Object>> list = service.search(FinalTable.ES_INDEX_COMMODITY,  FinalTable.ES_TYPE_COMMODITY, constructor);


            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String json = null;
            json = "{\"res\":\"true\",\"list\":" + list.toString() + ",\"date\":" + ft.format(dNow) + "}";
            Router.sendMessage("0", json, ctx);
            break;
            case "/snc/shop/commodity/type":           //首页光标移到分类时   参数为 c_type:xxx,c_page:xxx
                JSONObject o1 = Router.getMessage(fhr);
                ElasticsearchService service1 = new ElasticsearchService(FinalTable.ElasticsearchService_ClusterNmae, FinalTable.ElasticsearchService_IP, FinalTable.ElasticsearchService_PORT);
                ESQueryBuilderConstructor constructor1 = new ESQueryBuilderConstructor();
                List<String> lists1 = new ArrayList<>();
                lists1.add(FinalTable.COMMODITY_TYPE);
                constructor1.mutilsearch(new ESQueryBuilders().multiMatch(o1.getString(FinalTable.COMMODITY_TYPE),lists1));
                constructor1.setSize(FinalTable.ES_SIZE_COMMODITY);
                constructor1.setFrom((Integer.parseInt(o1.getString(FinalTable.COMMODITY_PAGE))-1)*10);

                List<Map<String, Object>> list1 = service1.search(FinalTable.ES_INDEX_COMMODITY, FinalTable.ES_TYPE_TYPE, constructor1);
                Date dNow1 = new Date();
                SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String json1 = null;
                json1 = "{\"res\":\"true\",\"list\":" + list1.toString() + ",\"date\":" + ft1.format(dNow1) + "}";
                Router.sendMessage("0", json1, ctx);
                break;
            case "/snc/shop/commodity/type/brand":           //首页从分类选择牌子  参数为 c_brand:xxx,c_page:xxx
                JSONObject o2 = Router.getMessage(fhr);
                ElasticsearchService service2= new ElasticsearchService(FinalTable.ElasticsearchService_ClusterNmae, FinalTable.ElasticsearchService_IP, FinalTable.ElasticsearchService_PORT);
                ESQueryBuilderConstructor constructor2 = new ESQueryBuilderConstructor();
                List<String> lists2 = new ArrayList<>();
                lists2.add(FinalTable.COMMODITY_BRAND);
                constructor2.mutilsearch(new ESQueryBuilders().multiMatch(o2.getString(FinalTable.COMMODITY_BRAND),lists2));
                constructor2.setSize(FinalTable.ES_SIZE_COMMODITY);
                constructor2.setDesc(FinalTable.COMMODITY_HOT);
                constructor2.setFrom((Integer.parseInt(o2.getString(FinalTable.COMMODITY_PAGE))-1)*10);

                List<Map<String, Object>> list2 = service2.search(FinalTable.ES_INDEX_COMMODITY,  FinalTable.ES_TYPE_COMMODITY, constructor2);
                Date dNow2 = new Date();
                SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String json2 = null;
                json2 = "{\"res\":\"true\",\"list\":" + list2.toString() + ",\"date\":" + ft2.format(dNow2) + "}";
                Router.sendMessage("0", json2, ctx);
                break;
            case "/snc/shop/commodity/type/brand/desc":           //首页从分类选择牌子然后进行价格降序  参数为 c_brand:xxx,c_page:xxx
                JSONObject o4 = Router.getMessage(fhr);           //也可以是搜索出不是分类后的排序操作
                ElasticsearchService service4= new ElasticsearchService(FinalTable.ElasticsearchService_ClusterNmae, FinalTable.ElasticsearchService_IP, FinalTable.ElasticsearchService_PORT);
                ESQueryBuilderConstructor constructor4 = new ESQueryBuilderConstructor();
                List<String> lists4 = new ArrayList<>();
                lists4.add(FinalTable.COMMODITY_BRAND);
                constructor4.mutilsearch(new ESQueryBuilders().multiMatch(o4.getString(FinalTable.COMMODITY_BRAND),lists4));
                constructor4.setSize(FinalTable.ES_SIZE_COMMODITY);
                constructor4.setFrom((Integer.parseInt(o4.getString(FinalTable.COMMODITY_PAGE))-1)*10);

                constructor4.setDesc(FinalTable.COMMODITY_PRICE);

                List<Map<String, Object>> list4 = service4.search(FinalTable.ES_INDEX_COMMODITY,  FinalTable.ES_TYPE_COMMODITY, constructor4);
                Date dNow4 = new Date();
                SimpleDateFormat ft4 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String json4 = null;
                json4 = "{\"res\":\"true\",\"list\":" + list4.toString() + ",\"date\":" + ft4.format(dNow4) + "}";
                Router.sendMessage("0", json4, ctx);
                break;
            case "/snc/shop/commodity/type/brand/asc":           //首页从分类选择牌子然后进行价格降序  参数为 c_brand:xxx,c_page:xxx
                JSONObject o5 = Router.getMessage(fhr);             //也可以是搜索出不是分类后的排序操作
                ElasticsearchService service5= new ElasticsearchService(FinalTable.ElasticsearchService_ClusterNmae, FinalTable.ElasticsearchService_IP, FinalTable.ElasticsearchService_PORT);
                ESQueryBuilderConstructor constructor5 = new ESQueryBuilderConstructor();
                List<String> lists5 = new ArrayList<>();
                lists5.add(FinalTable.COMMODITY_BRAND);
                constructor5.mutilsearch(new ESQueryBuilders().multiMatch(o5.getString(FinalTable.COMMODITY_BRAND),lists5));
                constructor5.setSize(FinalTable.ES_SIZE_COMMODITY);
                constructor5.setFrom((Integer.parseInt(o5.getString(FinalTable.COMMODITY_PAGE))-1)*10);

                constructor5.setAsc(FinalTable.COMMODITY_PRICE);

                List<Map<String, Object>> list5 = service5.search(FinalTable.ES_INDEX_COMMODITY,  FinalTable.ES_TYPE_COMMODITY, constructor5);
                Date dNow5 = new Date();
                SimpleDateFormat ft5 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String json5 = null;
                json5 = "{\"res\":\"true\",\"list\":" + list5.toString() + ",\"date\":" + ft5.format(dNow5) + "}";
                Router.sendMessage("0", json5, ctx);
                break;
            case "/snc/shop/commodity/type/brand/solddesc":           //首页从分类选择牌子然后进行价格降序   参数为 c_brand:xxx,c_page:xxx
                JSONObject o6 = Router.getMessage(fhr);                //也可以是搜索出不是分类后的排序操作
                ElasticsearchService service6= new ElasticsearchService(FinalTable.ElasticsearchService_ClusterNmae, FinalTable.ElasticsearchService_IP, FinalTable.ElasticsearchService_PORT);
                ESQueryBuilderConstructor constructor6 = new ESQueryBuilderConstructor();
                List<String> lists6 = new ArrayList<>();
                lists6.add(FinalTable.COMMODITY_BRAND);
                constructor6.mutilsearch(new ESQueryBuilders().multiMatch(o6.getString(FinalTable.COMMODITY_BRAND),lists6));
                constructor6.setSize(FinalTable.ES_SIZE_COMMODITY);
                constructor6.setFrom((Integer.parseInt(o6.getString(FinalTable.COMMODITY_PAGE))-1)*10);

                constructor6.setDesc(FinalTable.COMMODITY_SOLD);

                List<Map<String, Object>> list6 = service6.search(FinalTable.ES_INDEX_COMMODITY,  FinalTable.ES_TYPE_COMMODITY, constructor6);
                Date dNow6 = new Date();
                SimpleDateFormat ft6 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String json6 = null;
                json6 = "{\"res\":\"true\",\"list\":" + list6.toString() + ",\"date\":" + ft6.format(dNow6) + "}";
                Router.sendMessage("0", json6, ctx);
                break;
            case "/snc/shop/commodity/type/brand/pricerange":  //首页从分类选择牌子然后进行价格降序  参数为 c_brand:xxx,c_page:xxx,c_low:xxx,c_high:xxx_
                JSONObject o7 = Router.getMessage(fhr);        //也可以是搜索出不是分类后的排序操作
                ElasticsearchService service7= new ElasticsearchService(FinalTable.ElasticsearchService_ClusterNmae, FinalTable.ElasticsearchService_IP, FinalTable.ElasticsearchService_PORT);
                ESQueryBuilderConstructor constructor7 = new ESQueryBuilderConstructor();
                List<String> lists7 = new ArrayList<>();
                lists7.add(FinalTable.COMMODITY_BRAND);
                constructor7.mutilsearch(new ESQueryBuilders().multiMatch(o7.getString(FinalTable.COMMODITY_BRAND),lists7).range(FinalTable.COMMODITY_PRICE,o7.getString(FinalTable.COMMODITY_PRICE_LOW),o7.getString(FinalTable.COMMODITY_PRICE_HIRH)));
                constructor7.setSize(FinalTable.ES_SIZE_COMMODITY);
                constructor7.setFrom((Integer.parseInt(o7.getString(FinalTable.COMMODITY_PAGE))-1)*10);

                constructor7.setDesc(FinalTable.COMMODITY_PRICE);

                List<Map<String, Object>> list7 = service7.search(FinalTable.ES_INDEX_COMMODITY,  FinalTable.ES_TYPE_COMMODITY, constructor7);
                Date dNow7 = new Date();
                SimpleDateFormat ft7 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String json7 = null;
                json7 = "{\"res\":\"true\",\"list\":" + list7.toString() + ",\"date\":" + ft7.format(dNow7) + "}";
                Router.sendMessage("0", json7, ctx);
                break;
            case "/snc/shop/commodity/search":       //每次搜索都要判断搜索的是不是分类 因为是分类要返回牌子栏
                JSONObject o8 = Router.getMessage(fhr);    //参数为 c_text:xxx,c_page:xxx
                ElasticsearchService service8= new ElasticsearchService(FinalTable.ElasticsearchService_ClusterNmae, FinalTable.ElasticsearchService_IP, FinalTable.ElasticsearchService_PORT);
                ESQueryBuilderConstructor constructor8 = new ESQueryBuilderConstructor();
                List<String> lists8 = new ArrayList<>();
                lists8.add(FinalTable.COMMODITY_TYPE);
                constructor8.mutilsearch(new ESQueryBuilders().multiMatch(o8.getString(FinalTable.COMMODITY_TEXT),lists8));
                constructor8.setSize(FinalTable.ES_SIZE_COMMODITY);
                constructor8.setFrom((Integer.parseInt(o8.getString(FinalTable.COMMODITY_PAGE))-1)*10);

                List<Map<String, Object>> list8 = service8.search(FinalTable.ES_INDEX_COMMODITY,  FinalTable.ES_TYPE_TYPE, constructor8);
                List<Map<String, Object>> list9=new ArrayList<>();
                Date dNow8 = new Date();
                SimpleDateFormat ft8 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String json8 = null;
                if(list8.size()!=0){
                    constructor8.mutilsearch(new ESQueryBuilders().multiMatch(o8.getString(FinalTable.COMMODITY_TEXT),lists8));
                    constructor8.setDesc(FinalTable.COMMODITY_HOT);
                    list9= service8.search("commodity", "commodity", constructor8);
                    json8 = "{\"res\":\"true\",\"list\":" + "["+"type:"+list8+"commodity:"+list9+"]" + ",\"date\":" + ft8.format(dNow8) + "}";
                    Router.sendMessage("0", json8, ctx);
                }else {
                    lists8.add(FinalTable.COMMODITY_NAME);
                    constructor8.mutilsearch(new ESQueryBuilders().multiMatch(o8.getString(FinalTable.COMMODITY_TEXT),lists8));
                    constructor8.setDesc(FinalTable.COMMODITY_HOT);
                    constructor8.setSize(FinalTable.ES_SIZE_COMMODITY);
                    constructor8.setFrom((Integer.parseInt(o8.getString(FinalTable.COMMODITY_PAGE))-1)*10);

                    list9= service8.search("commodity", "commodity", constructor8);
                    json8 = "{\"res\":\"true\",\"list\":" + list9.toString() + ",\"date\":" + ft8.format(dNow8) + "}";
                    Router.sendMessage("0", json8, ctx);
                }
                break;

            case "/snc/shop/commodity/search/desc":           //搜索出为分类时的降序，因为已经从上一步得出牌子栏，前端在上一步时应把牌子栏保存，在排序时牌子栏就不用在从es拿了
                JSONObject o10 = Router.getMessage(fhr);       //参数为 c_text:xxx,c_page:xxx
                ElasticsearchService service10= new ElasticsearchService(FinalTable.ElasticsearchService_ClusterNmae, FinalTable.ElasticsearchService_IP, FinalTable.ElasticsearchService_PORT);
                ESQueryBuilderConstructor constructor10 = new ESQueryBuilderConstructor();
                List<String> lists10 = new ArrayList<>();
                lists10.add(FinalTable.COMMODITY_TYPE);
                constructor10.mutilsearch(new ESQueryBuilders().multiMatch(o10.getString(FinalTable.COMMODITY_TEXT),lists10));
                constructor10.setSize(FinalTable.ES_SIZE_COMMODITY);
                constructor10.setFrom((Integer.parseInt(o10.getString(FinalTable.COMMODITY_PAGE))-1)*10);

                constructor10.setDesc(FinalTable.COMMODITY_PRICE);

                List<Map<String, Object>> list10 = service10.search(FinalTable.ES_INDEX_COMMODITY,  FinalTable.ES_TYPE_COMMODITY, constructor10);
                Date dNow10 = new Date();
                SimpleDateFormat ft10 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String json10 = null;
                json10 = "{\"res\":\"true\",\"list\":" + list10.toString() + ",\"date\":" + ft10.format(dNow10) + "}";
                Router.sendMessage("0", json10, ctx);
                break;
            case "/snc/shop/commodity/search/asc":           //搜索出结果为分类时的价格降序
                JSONObject o11 = Router.getMessage(fhr);       //参数为 c_text:xxx,c_page:xxx
                ElasticsearchService service11= new ElasticsearchService(FinalTable.ElasticsearchService_ClusterNmae, FinalTable.ElasticsearchService_IP, FinalTable.ElasticsearchService_PORT);
                ESQueryBuilderConstructor constructor11 = new ESQueryBuilderConstructor();
                List<String> lists11 = new ArrayList<>();
                lists11.add(FinalTable.COMMODITY_TYPE);
                constructor11.mutilsearch(new ESQueryBuilders().multiMatch(o11.getString(FinalTable.COMMODITY_TEXT),lists11));
                constructor11.setSize(FinalTable.ES_SIZE_COMMODITY);
                constructor11.setFrom((Integer.parseInt(o11.getString(FinalTable.COMMODITY_PAGE))-1)*10);

                constructor11.setAsc(FinalTable.COMMODITY_PRICE);

                List<Map<String, Object>> list11= service11.search(FinalTable.ES_INDEX_COMMODITY,  FinalTable.ES_TYPE_COMMODITY, constructor11);
                Date dNow11 = new Date();
                SimpleDateFormat ft11 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String json11 = null;
                json11= "{\"res\":\"true\",\"list\":" + list11.toString() + ",\"date\":" + ft11.format(dNow11) + "}";
                Router.sendMessage("0", json11, ctx);
                break;
            case "/snc/shop/commodity/search/solddesc":           //搜索出结果为分类时的销量降序
                JSONObject o12 = Router.getMessage(fhr);           //参数为 c_text:xxx,c_page:xxx
                ElasticsearchService service12= new ElasticsearchService(FinalTable.ElasticsearchService_ClusterNmae, FinalTable.ElasticsearchService_IP, FinalTable.ElasticsearchService_PORT);
                ESQueryBuilderConstructor constructor12 = new ESQueryBuilderConstructor();
                List<String> lists12 = new ArrayList<>();
                lists12.add(FinalTable.COMMODITY_TYPE);
                constructor12.mutilsearch(new ESQueryBuilders().multiMatch(o12.getString(FinalTable.COMMODITY_TEXT),lists12));
                constructor12.setSize(FinalTable.ES_SIZE_COMMODITY);
                constructor12.setFrom((Integer.parseInt(o12.getString(FinalTable.COMMODITY_PAGE))-1)*10);

                constructor12.setDesc(FinalTable.COMMODITY_SOLD);

                List<Map<String, Object>> list12 = service12.search(FinalTable.ES_INDEX_COMMODITY,  FinalTable.ES_TYPE_COMMODITY, constructor12);
                Date dNow12 = new Date();
                SimpleDateFormat ft12 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String json12 = null;
                json10 = "{\"res\":\"true\",\"list\":" + list12.toString() + ",\"date\":" + ft12.format(dNow12) + "}";
                Router.sendMessage("0", json10, ctx);
                break;
            case "/snc/shop/commodity/search/pricerange":      //搜索出结果为分类时的价格区间排序
                JSONObject o13 = Router.getMessage(fhr);       //参数为 c_text:xxx,c_page:xxx,c_low:xxx,c_high:xxx
                ElasticsearchService service13= new ElasticsearchService(FinalTable.ElasticsearchService_ClusterNmae, FinalTable.ElasticsearchService_IP, FinalTable.ElasticsearchService_PORT);
                ESQueryBuilderConstructor constructor13 = new ESQueryBuilderConstructor();
                List<String> lists13 = new ArrayList<>();
                lists13.add(FinalTable.COMMODITY_TYPE);
                constructor13.mutilsearch(new ESQueryBuilders().multiMatch(o13.getString(FinalTable.COMMODITY_BRAND),lists13).range(FinalTable.COMMODITY_PRICE,o13.getString(FinalTable.COMMODITY_PRICE_LOW),o13.getString(FinalTable.COMMODITY_PRICE_HIRH)));
                constructor13.setSize(FinalTable.ES_SIZE_COMMODITY);
                constructor13.setFrom((Integer.parseInt(o13.getString(FinalTable.COMMODITY_PAGE))-1)*10);

                constructor13.setDesc(FinalTable.COMMODITY_PRICE);

                List<Map<String, Object>> list13 = service13.search(FinalTable.ES_INDEX_COMMODITY,  FinalTable.ES_TYPE_COMMODITY, constructor13);
                Date dNow13 = new Date();
                SimpleDateFormat ft13 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String json13 = null;
                json13= "{\"res\":\"true\",\"list\":" + list13.toString() + ",\"date\":" + ft13.format(dNow13) + "}";
                Router.sendMessage("0", json13, ctx);
                break;


            default:
                ctx.fireChannelRead(msg);
        }
    }
}
