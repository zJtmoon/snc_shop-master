package snc.boot.util;

import snc.boot.util.es.ElasticsearchService;

/**
 * android and web
 */
public class FinalTable {
    public final static String NIL = "nil";
    public final static String UUID="uid"; //用户唯一id
    public final static String AH_USER_ID = "pid"; //手机用户id
    public final static String GIFT_ID="gid";//礼物唯一id
    public final static String ARM_USER_ID = "aid"; //盒子用户id
    public final static String IDE_ID = "iid";
    public final static String LOGIN_TYPE="type";
    public final static String CLASS_ID = "cid"; //课程id
    public final static String ARM_KEY = "akey"; //盒子密钥
    public final static String AH_USER_PHONE = "ph"; //用户手机号
    public final static String AH_USER_PASSWD = "pwd"; //用户密码
    public final static String AH_PHONE_CPT = "cpt"; //验证码
    public final static String RETURN_STATUS = "r"; //返回的状态码
    public final static String RETURN_DATA = "d"; // 返回的信息
    public final static String RETURN_DATA_ISNULL = "nil"; //data返回为空
    public final static String LIMIT = "limit"; //分页limit
    public final static String PARTITION = "pt"; //积分区间
    public final static String INDEX = "index"; //商品索引条件
    public final static String TYPE = "type"; //商品类型
    public final static String TEXT_INDEX = "text"; //关键字索引
    public final static String SHOP = "shop"; //商品列表
    public final static String CLASS = "class"; //课程
    public final static String RES = "res"; //购买接口的res
    public final static String ORDER_NUM = "onum"; //订单号
    public final static String DATE = "date"; //时间
    public final static String NAME = "N"; //用户姓名
    public final static String BIRTHDAY = "B"; //用户生日
    public final static String EMAIL = "E"; //用户邮箱
    public final static String BEIYONG_PHONe = "BPH"; //备用电话
    public final static String FUNCTION_CLASS = "FC"; //已完成课程数
    public final static String ADDRESS = "ADD"; //用户地址
    public final static String CLASS_MONEY = "CM"; //当前课时数
    public final static String Gift_MONEY = "GM"; //当前积分数
    public final static String BUY_CLASS = "BC"; //已购课程
    public final static String SHIPMEND_NUM = "snum"; //物流单号
    public final static String VM_TYPE = "type"; //购买vm的类型
    public final static String VM_CPU = "cpu"; //vm的cpu大小
    public final static String VM_MEM = "mem"; //vm的内存容量
    public final static String VM_DISK = "disk"; //vm的磁盘容量
    public final static String COMMODITY_PAGE="c_page";
    public final static String COMMODITY_SID="c_sid";//商品id
    public final static String COMMODITY_NAME="c_name";
    public final static String COMMODITY_PRICE="c_price";//购买商品的价格(积分)
    public final static String COMMODITY_COL="c_col";//购买商品的颜色
    public final static String COMMODITY_MOD="c_mod";//购买商品的型号
    public final static String COMMODITY_SIZE="size";//购买商品的大小
    public final static String COMMODITY_BRAND="c_brand";
    public final static String COMMODITY_IMAGE="c_image";
    public final static String COMMODITY_TYPE="c_type";
    public final static String COMMODITY_DETAILS="c_details";
    public final static String COMMODITY_NUM="num";//购买商品的数量
    public final static String COMMODITY_HOT="c_hot";
    public final static String COMMODITY_SOLD="c_sold";
    public final static String COMMODITY_PRICE_LOW="c_low";//前端传来的价格低区间
    public final static String COMMODITY_PRICE_HIRH="c_high";//前端传来的价格高区间
    public final static String COMMODITY_TEXT="c_text";//商品查询条件
    public final static int ES_SIZE_COMMODITY=10;  //查询商品返回的数目

    public final static String ES_INDEX_COMMODITY="commodity";
    public final static String ES_TYPE_COMMODITY="commodity";
    public final static String ES_TYPE_TYPE="type";//es type表
    public final static String ElasticsearchService_ClusterNmae="elasticsearch";
    public final static String ElasticsearchService_IP="127.0.0.1";

    public final static int ElasticsearchService_PORT=9300;



    private String pid;              //用户唯一id
    private String c_sid;            //礼物id
    private String c_name;
    private double c_price;          //按照ue图 购物车里不用显示商品的市场价；
    private String c_col;            //商品颜色
    private String c_mod;            //型号
    private String c_size;           //大小
    private String c_brand;
    private String c_image;
    private String c_type;
    private String c_details;
    private int num;








    public final static String USER_ID = "U_";
    public final static String DOCKER_ID = "D";
    public final static String Prefix_AID = "A_";
    public final static String DEBUG_PORT = "P";
    public final static String SEND_RESULT = "r";
    public final static String SEND_DATA = "d";

    public final static String NO_ERROR="0";
    public final static String ERROR="1";

    public final static String ESindex="index";
    public final static String ESTYPE="type";


}
