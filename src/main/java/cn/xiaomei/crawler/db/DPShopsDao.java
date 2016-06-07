package cn.xiaomei.crawler.db;

import cn.xiaomei.crawler.Constant;
import com.alibaba.fastjson.JSON;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.bson.Document;
import org.json4s.jackson.Json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chunli on 16/5/11.
 */
public class DPShopsDao {
    public List<Json> listShop() {
        //MongoConnect mc = MongoConnect.getInstance;
        // MongoCollection<Document> docs = mc.getCollect("crawler", "dianping_shops");

        // Document filter = Document.parse("{\"is_closed\":\"false\"}");
        // Document dbObject = Document.parse(JSON.toJSONString(filter.getValue));
        Document projection = new Document();

        // MongoCursor<Document> cursor= docs.find().cursorType(CursorType.Tailable).iterator();;
        //  MongoCursor<Document> cursor = docs.find().iterator();
        PreparedStatement stmt = null;

        // stmt = mysqlConnect.conn.createStatement();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/tmp/dianping_上海.csv"));
            String [] shoparr=null;
            try {
                String line="";
                while ((line = reader.readLine()) != null) {
                    shoparr=line.split(",");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
           /* while (cursor.hasNext()) {
                Document doc = cursor.next();*/
            MysqlConnect mysqlConnect = MysqlConnect.instance;

            try {
                Connection conn = mysqlConnect.conn;
                stmt = conn.prepareStatement("");
                String shop_id=shoparr[0];
                //新增一条数据
                String isexist = "select title from x520_xiaomei_merchant_main where id_dp='";// + + "'";
                ResultSet res = stmt.executeQuery(isexist);

                if (res.next()) {
                    // System.out.println(doc.toJson());
                } else {
                    isexist = "select title from x520_xiaomei_merchant_main_source where id_dp='";// + doc.getInteger("shop_id") + "'";
                    ResultSet res_source = stmt.executeQuery(isexist);
                    if (!res_source.next()) {
                        Shop shop = new Shop();
                      /*  Object nameo = doc.get("name");
                        if (nameo != null && StringUtils.isNotBlank(nameo.toString())) {
                            shop.setTitle(nameo.toString());
                        } else {
                            shop.setTitle("");
                        }
                        Object alias = doc.get("alias");
                        if (alias == null || StringUtils.isBlank(alias.toString())) {
                            shop.setAlias(shop.getTitle());
                        } else {
                            String tmp = shop.getTitle();
                            ;
                            shop.setTitle(alias.toString());
                            shop.setAlias(tmp);
                        }
                        Object big_cate = doc.get("big_cate");
                        String big_cate_id = category.get(big_cate != null ? big_cate.toString() : "");
                        if (StringUtils.isNotBlank(big_cate_id)) {
                            shop.setIndustry_id(big_cate_id);
                        } else {
                            shop.setIndustry_id("0");
                        }
                        String province_id = mysqlprovince.get(doc.getString("province"));
                        if (StringUtils.isNotBlank(province_id)) {
                            shop.setProvince_id(province_id);
                        } else {
                            shop.setProvince_id("0");
                        }
                        Object city = doc.get("city");
                        if (city != null && StringUtils.isNotBlank(city.toString())) {
                            shop.setCity(city.toString());
                        } else {
                            shop.setCity("");
                        }

                        String city_id = mysqlcity.get(shop.getCity());
                        if (StringUtils.isNotBlank(city_id)) {
                            shop.setCity_id(city_id);
                        } else {
                            shop.setCity_id("0");
                        }
                        Object area = doc.get("area");
                        if (area != null && StringUtils.isNotBlank(area.toString())) {
                            shop.setArea(area.toString());
                        } else {
                            shop.setArea("");
                        }
                        Object address = doc.get("address");
                        if (address != null && StringUtils.isNotBlank(address.toString())) {
                            shop.setAddress(address.toString());
                        } else {
                            shop.setAddress("");
                        }
                        Object shop_id = doc.get("shop_id");
                        if (shop_id != null && StringUtils.isNotBlank(shop_id.toString())) {
                            shop.setId_dp(shop_id.toString());
                        }
                        Object latitude = doc.get("latitude");
                        if (latitude != null && StringUtils.isNotBlank(latitude.toString())) {
                            shop.setLat(latitude.toString());
                        }
                        Object longitude = doc.get("longitude");
                        if (longitude != null && StringUtils.isNotBlank(longitude.toString())) {
                            shop.setLng(longitude.toString());
                        }
                        Object phone = doc.get("phone");
                        if (phone != null && StringUtils.isNotBlank(phone.toString()) && phone.toString().length() < 15) {
                            shop.setTelephone(phone.toString());
                        }
                        Object product = doc.get("product_rating");
                        if (product != null && StringUtils.isNotBlank(product.toString())) {
                            shop.setTaste(product.toString());

                        }
                        Object env = doc.get("environment_rating");
                        if (env != null && StringUtils.isNotBlank(env.toString())) {
                            shop.setEnvironment(env.toString());

                        }
                        Object service = doc.get("service_rating");
                        if (service != null && StringUtils.isNotBlank(service.toString())) {
                            shop.setService(service.toString());

                        }
                        Object avg = doc.get("avg_price");
                        if (avg != null && StringUtils.isNotBlank(avg.toString())) {
                            shop.setAvg_pay(avg.toString());

                        }
                        Object remarks = doc.get("all_remarks");
                        if (remarks != null && StringUtils.isNotBlank(remarks.toString())) {
                            shop.setComment(remarks.toString());
                        }*/
                        shop.setCompany_id("0");
                    /*db.getCollection('dianping_shops').find({},{"shop_id":1,"name":1,"alias":1,"province":1,    "city_id" : 17,
                    "city":1,"big_cate_id":1,
                    "latitude":1,"longitude":1,"phone":1,"address":1,"product_rating":1,"environment_rating":1,"service_rating":1,
                    "avg_price":1,"stars":1,"all_remarks":1})*/
                        /*name = name
                                .replace("!", "!!")
                                .replace("%", "!%")
                                .replace("_", "!_")
                                .replace("[", "![");
                        String sql="SELECT company_id FROM tpx520.x520_xiaomei_company_main where title LIKE ? ESCAPE '!' ";
                        stmt = conn.prepareStatement(sql);
                        stmt.setString(1,"%"+name+"%");
                        ResultSet rs=stmt.executeQuery();
                        System.out.println(shop.getId_dp()+","+name);

                        if (rs.next()) {
                            shop.setCompany_id(rs.getInt("company_id") + "");
                            System.out.println(shop.getCompany_id());
                        }*/
                        save(shop, conn);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (stmt != null && stmt.isClosed()) {
                        stmt.close();
                    }
                    mysqlConnect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    //  mc.close();
    return null;
}

    private void save(Shop shop, java.sql.Connection conn) throws SQLException {
        String sql = "insert into x520_xiaomei_merchant_main_source (" +
                "contact_id,company_id,title,full_name,industry_id,province_id,city,city_id,district_id,area_id,address,id_dp," +
                "lat,lng,telephone,taste,environment,service,avg_pay,comment,add_time" +
                ") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        // String sql="insert into x520_xiaomei_merchant_main_source(contact_id,company_id) values (1,2)";
        System.out.println(shop.getId_dp());
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, 0);
        stmt.setInt(2, Integer.valueOf(shop.getCompany_id()));
        stmt.setString(3, shop.getTitle());
        stmt.setString(4, shop.getAlias());
        stmt.setString(5, shop.getIndustry_id());
        stmt.setString(6, shop.getProvince_id());
        stmt.setString(7, shop.getCity());
        stmt.setString(8, shop.getCity_id());
        stmt.setString(9, "");
        stmt.setString(10, "");
        stmt.setString(11, shop.getAddress());
        stmt.setString(12, shop.getId_dp());
        stmt.setString(13, shop.getLat());
        stmt.setString(14, shop.getLng());
        stmt.setString(15, shop.getTelephone());
        stmt.setString(16, shop.getTaste());
        stmt.setString(17, shop.getEnvironment());
        stmt.setString(18, shop.getService());
        stmt.setString(19, shop.getAvg_pay());
        stmt.setString(20, shop.getComment());
        stmt.setString(21, Constant.getCurrentDateTime());
        stmt.execute();


    }

    public static void main(String[] args) {
        DPShopsDao shopsDao = new DPShopsDao();
        shopsDao.listShop();
    }

public class Shop {
    private String title = "";
    private String alias = "";
    private String company_id = "";


    private String industry_id = "";
    private String province_id = "";
    private String city = "";
    private String city_id = "";
    private String area = "";
    private String address = "";
    private String id_dp = "";
    private String lat = "";
    private String lng = "";
    private String telephone = "";
    private String taste = "0";
    private String environment = "0";


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        return new EqualsBuilder()
                .append(getIndustry_id(), shop.getIndustry_id())
                .append(getProvince_id(), shop.getProvince_id())
                .append(getCity_id(), shop.getCity_id())
                .append(getId_dp(), shop.getId_dp())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getIndustry_id())
                .append(getProvince_id())
                .append(getCity_id())
                .append(getId_dp())
                .toHashCode();
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(String industry_id) {
        this.industry_id = industry_id;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId_dp() {
        return id_dp;
    }

    public void setId_dp(String id_dp) {
        this.id_dp = id_dp;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAvg_pay() {
        return avg_pay;
    }

    public void setAvg_pay(String avg_pay) {
        this.avg_pay = avg_pay;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    String service = "0";
    String avg_pay = "0";
    String comment = "0";

}

private static Map<String, String> category = new HashMap<String, String>();

private static Map<String, String> mysqlcity = new HashMap<String, String>();
private static Map<String, String> mysqlprovince = new HashMap<String, String>();
static{
        category.put("美食","8801");
        category.put("购物","8803");
        category.put("生活服务","8806");
        category.put("丽人","8805");
        category.put("家装","8812");
        category.put("爱车","8806");
        category.put("酒店","8807");
        category.put("休闲娱乐","8806");
        category.put("医疗健康","8806");
        category.put("亲子","8806");
        category.put("景点","8806");
        category.put("结婚","8806");
        category.put("运动健身","8802");
        category.put("K歌","8802");
        category.put("宠物","8806");
        category.put("电影","8802");
        category.put("订酒店","8807");

        mysqlprovince.put("北京","10000");
        mysqlprovince.put("河北","10001");
        mysqlprovince.put("河南","10002");
        mysqlprovince.put("山西","10003");
        mysqlprovince.put("陕西","10004");
        mysqlprovince.put("辽宁","10005");
        mysqlprovince.put("山东","10006");
        mysqlprovince.put("甘肃","10007");
        mysqlprovince.put("吉林","10008");
        mysqlprovince.put("黑龙江","10009");
        mysqlprovince.put("云南","10010");
        mysqlprovince.put("贵州","10011");
        mysqlprovince.put("福建","10012");
        mysqlprovince.put("广东","10013");
        mysqlprovince.put("海南","10014");
        mysqlprovince.put("四川","10015");
        mysqlprovince.put("湖北","10016");
        mysqlprovince.put("湖南","10017");
        mysqlprovince.put("江西","10018");
        mysqlprovince.put("安徽","10019");
        mysqlprovince.put("江苏","10020");
        mysqlprovince.put("浙江","10021");
        mysqlprovince.put("青海","10022");
        mysqlprovince.put("新疆","10023");
        mysqlprovince.put("内蒙古","10024");
        mysqlprovince.put("宁夏","10025");
        mysqlprovince.put("西藏","10027");
        mysqlprovince.put("广西","10028");
        mysqlprovince.put("天津","10029");
        mysqlprovince.put("重庆","10030");
        mysqlprovince.put("上海","10031");
        mysqlprovince.put("澳门","10032");
        mysqlprovince.put("香港","10033");
        mysqlprovince.put("台湾","10034");

        mysqlcity.put("北京","10000");
        mysqlcity.put("天津","10001");
        mysqlcity.put("重庆","10002");
        mysqlcity.put("上海","10003");
        mysqlcity.put("石家庄","10025");
        mysqlcity.put("唐山","10026");
        mysqlcity.put("秦皇岛","10027");
        mysqlcity.put("邯郸","10028");
        mysqlcity.put("邢台","10029");
        mysqlcity.put("保定","10030");
        mysqlcity.put("张家口","10031");
        mysqlcity.put("承德","10032");
        mysqlcity.put("沧州","10033");
        mysqlcity.put("廊坊","10034");
        mysqlcity.put("衡水","10035");
        mysqlcity.put("郑州","10036");
        mysqlcity.put("洛阳","10037");
        mysqlcity.put("南阳","10038");
        mysqlcity.put("平顶山","10039");
        mysqlcity.put("焦作","10040");
        mysqlcity.put("许昌","10041");
        mysqlcity.put("安阳","10042");
        mysqlcity.put("新乡","10043");
        mysqlcity.put("周口","10044");
        mysqlcity.put("信阳","10045");
        mysqlcity.put("商丘","10046");
        mysqlcity.put("驻马店","10047");
        mysqlcity.put("三门峡","10048");
        mysqlcity.put("濮阳","10049");
        mysqlcity.put("开封","10050");
        mysqlcity.put("漯河","10051");
        mysqlcity.put("鹤壁","10052");
        mysqlcity.put("济源","10053");
        mysqlcity.put("太原","10054");
        mysqlcity.put("长治","10055");
        mysqlcity.put("吕梁","10056");
        mysqlcity.put("临汾","10057");
        mysqlcity.put("运城","10058");
        mysqlcity.put("朔州","10059");
        mysqlcity.put("晋城","10060");
        mysqlcity.put("晋中","10061");
        mysqlcity.put("大同","10062");
        mysqlcity.put("阳泉","10063");
        mysqlcity.put("忻州","10064");
        mysqlcity.put("西安","10065");
        mysqlcity.put("榆林","10066");
        mysqlcity.put("咸阳","10067");
        mysqlcity.put("宝鸡","10068");
        mysqlcity.put("延安","10069");
        mysqlcity.put("渭南","10070");
        mysqlcity.put("汉中","10071");
        mysqlcity.put("安康","10072");
        mysqlcity.put("商洛","10073");
        mysqlcity.put("铜川","10074");
        mysqlcity.put("杨凌区","10075");
        mysqlcity.put("沈阳","10076");
        mysqlcity.put("大连","10077");
        mysqlcity.put("鞍山","10078");
        mysqlcity.put("抚顺","10079");
        mysqlcity.put("本溪","10080");
        mysqlcity.put("丹东","10081");
        mysqlcity.put("锦州","10082");
        mysqlcity.put("营口","10083");
        mysqlcity.put("阜新","10084");
        mysqlcity.put("辽阳","10085");
        mysqlcity.put("铁岭","10086");
        mysqlcity.put("朝阳市","10087");
        mysqlcity.put("盘锦","10088");
        mysqlcity.put("葫芦岛","10089");
        mysqlcity.put("青岛","10090");
        mysqlcity.put("淄博","10091");
        mysqlcity.put("枣庄","10092");
        mysqlcity.put("东营","10093");
        mysqlcity.put("烟台","10094");
        mysqlcity.put("潍坊","10095");
        mysqlcity.put("济宁","10096");
        mysqlcity.put("威海","10098");
        mysqlcity.put("日照","10099");
        mysqlcity.put("滨州","10100");
        mysqlcity.put("德州","10101");
        mysqlcity.put("聊城","10102");
        mysqlcity.put("菏泽","10103");
        mysqlcity.put("莱芜","10104");
        mysqlcity.put("临沂","10106");
        mysqlcity.put("兰州","10107");
        mysqlcity.put("酒泉","10108");
        mysqlcity.put("金昌","10109");
        mysqlcity.put("天水","10110");
        mysqlcity.put("嘉峪关市","10111");
        mysqlcity.put("武威","10112");
        mysqlcity.put("张掖","10113");
        mysqlcity.put("白银","10114");
        mysqlcity.put("平凉","10115");
        mysqlcity.put("庆阳","10116");
        mysqlcity.put("定西","10117");
        mysqlcity.put("陇南","10118");
        mysqlcity.put("临夏","10119");
        mysqlcity.put("甘南","10120");
        mysqlcity.put("长春","10121");
        mysqlcity.put("吉林市","10122");
        mysqlcity.put("四平","10123");
        mysqlcity.put("辽源","10124");
        mysqlcity.put("通化","10125");
        mysqlcity.put("白山","10126");
        mysqlcity.put("白城","10127");
        mysqlcity.put("松原","10128");
        mysqlcity.put("哈尔滨","10130");
        mysqlcity.put("齐齐哈尔","10131");
        mysqlcity.put("牡丹江","10132");
        mysqlcity.put("佳木斯","10133");
        mysqlcity.put("大庆","10134");
        mysqlcity.put("伊春","10135");
        mysqlcity.put("鸡西","10136");
        mysqlcity.put("鹤岗","10137");
        mysqlcity.put("双鸭山","10138");
        mysqlcity.put("七台河","10139");
        mysqlcity.put("绥化","10140");
        mysqlcity.put("黑河","10141");
        mysqlcity.put("昆明","10142");
        mysqlcity.put("曲靖","10143");
        mysqlcity.put("玉溪","10144");
        mysqlcity.put("保山","10145");
        mysqlcity.put("昭通","10146");
        mysqlcity.put("丽江","10147");
        mysqlcity.put("普洱","10148");
        mysqlcity.put("临沧","10149");
        mysqlcity.put("大理","10150");
        mysqlcity.put("西双版纳","10151");
        mysqlcity.put("贵阳","10152");
        mysqlcity.put("六盘水","10153");
        mysqlcity.put("遵义","10154");
        mysqlcity.put("铜仁","10155");
        mysqlcity.put("毕节","10156");
        mysqlcity.put("安顺","10157");
        mysqlcity.put("福州","10159");
        mysqlcity.put("厦门","10160");
        mysqlcity.put("漳州","10161");
        mysqlcity.put("泉州","10162");
        mysqlcity.put("三明","10163");
        mysqlcity.put("莆田","10164");
        mysqlcity.put("南平","10165");
        mysqlcity.put("龙岩","10166");
        mysqlcity.put("宁德","10167");
        mysqlcity.put("平潭","10168");
        mysqlcity.put("珠海","10169");
        mysqlcity.put("汕头","10170");
        mysqlcity.put("汕尾","10171");
        mysqlcity.put("佛山","10172");
        mysqlcity.put("韶关","10173");
        mysqlcity.put("湛江","10174");
        mysqlcity.put("肇庆","10175");
        mysqlcity.put("江门","10176");
        mysqlcity.put("茂名","10177");
        mysqlcity.put("惠州","10178");
        mysqlcity.put("梅州","10179");
        mysqlcity.put("河源","10180");
        mysqlcity.put("阳江","10181");
        mysqlcity.put("清远","10182");
        mysqlcity.put("东莞","10183");
        mysqlcity.put("中山","10184");
        mysqlcity.put("潮州","10185");
        mysqlcity.put("揭阳","10186");
        mysqlcity.put("云浮","10187");
        mysqlcity.put("广州","10188");
        mysqlcity.put("海口","10189");
        mysqlcity.put("三亚","10190");
        mysqlcity.put("三沙","10191");
        mysqlcity.put("成都","10192");
        mysqlcity.put("绵阳","10195");
        mysqlcity.put("自贡","10196");
        mysqlcity.put("攀枝花","10197");
        mysqlcity.put("泸州","10198");
        mysqlcity.put("德阳","10199");
        mysqlcity.put("广元","10200");
        mysqlcity.put("遂宁","10201");
        mysqlcity.put("内江","10202");
        mysqlcity.put("乐山","10203");
        mysqlcity.put("资阳","10204");
        mysqlcity.put("宜宾","10205");
        mysqlcity.put("南充","10206");
        mysqlcity.put("达州","10207");
        mysqlcity.put("雅安","10208");
        mysqlcity.put("眉山","10209");
        mysqlcity.put("巴中","10210");
        mysqlcity.put("广安","10211");
        mysqlcity.put("武汉","10212");
        mysqlcity.put("黄石","10213");
        mysqlcity.put("十堰","10214");
        mysqlcity.put("荆州","10215");
        mysqlcity.put("宜昌","10216");
        mysqlcity.put("襄阳","10217");
        mysqlcity.put("鄂州","10218");
        mysqlcity.put("黄冈","10219");
        mysqlcity.put("荆门","10220");
        mysqlcity.put("孝感","10221");
        mysqlcity.put("咸宁","10222");
        mysqlcity.put("仙桃","10223");
        mysqlcity.put("潜江","10224");
        mysqlcity.put("天门","10225");
        mysqlcity.put("随州","10226");
        mysqlcity.put("株洲","10227");
        mysqlcity.put("湘潭","10228");
        mysqlcity.put("衡阳","10229");
        mysqlcity.put("邵阳","10230");
        mysqlcity.put("岳阳","10231");
        mysqlcity.put("常德","10232");
        mysqlcity.put("张家界","10233");
        mysqlcity.put("益阳","10234");
        mysqlcity.put("娄底","10235");
        mysqlcity.put("郴州","10236");
        mysqlcity.put("永州","10237");
        mysqlcity.put("怀化","10238");
        mysqlcity.put("南昌","10239");
        mysqlcity.put("九江","10240");
        mysqlcity.put("上饶","10241");
        mysqlcity.put("抚州","10242");
        mysqlcity.put("宜春","10243");
        mysqlcity.put("吉安","10244");
        mysqlcity.put("赣州","10245");
        mysqlcity.put("景德镇","10246");
        mysqlcity.put("萍乡","10247");
        mysqlcity.put("新余","10248");
        mysqlcity.put("鹰潭","10249");
        mysqlcity.put("合肥","10250");
        mysqlcity.put("芜湖","10251");
        mysqlcity.put("蚌埠","10252");
        mysqlcity.put("马鞍山","10253");
        mysqlcity.put("安庆","10254");
        mysqlcity.put("淮南","10255");
        mysqlcity.put("铜陵","10256");
        mysqlcity.put("黄山","10257");
        mysqlcity.put("宣城","10258");
        mysqlcity.put("池州","10259");
        mysqlcity.put("滁州","10260");
        mysqlcity.put("淮北","10261");
        mysqlcity.put("阜阳","10262");
        mysqlcity.put("六安","10263");
        mysqlcity.put("宿州","10264");
        mysqlcity.put("亳州","10265");
        mysqlcity.put("南京","10266");
        mysqlcity.put("无锡","10267");
        mysqlcity.put("徐州","10268");
        mysqlcity.put("常州","10269");
        mysqlcity.put("苏州","10270");
        mysqlcity.put("南通","10271");
        mysqlcity.put("连云港","10272");
        mysqlcity.put("淮安","10273");
        mysqlcity.put("盐城","10274");
        mysqlcity.put("扬州","10275");
        mysqlcity.put("镇江","10276");
        mysqlcity.put("泰州","10277");
        mysqlcity.put("宿迁","10278");
        mysqlcity.put("宁波 ","10279");
        mysqlcity.put("温州","10280");
        mysqlcity.put("绍兴","10281");
        mysqlcity.put("湖州","10282");
        mysqlcity.put("嘉兴","10283");
        mysqlcity.put("金华","10284");
        mysqlcity.put("衢州","10285");
        mysqlcity.put("舟山","10286");
        mysqlcity.put("台州","10287");
        mysqlcity.put("丽水","10288");
        mysqlcity.put("西宁","10289");
        mysqlcity.put("海东","10290");
        mysqlcity.put("玉树","10291");
        mysqlcity.put("乌鲁木齐","10292");
        mysqlcity.put("克拉玛依","10293");
        mysqlcity.put("吐鲁番","10294");
        mysqlcity.put("和田市","10295");
        mysqlcity.put("伊宁市","10296");
        mysqlcity.put("石河子","10297");
        mysqlcity.put("阿拉尔","10298");
        mysqlcity.put("图木舒克","10299");
        mysqlcity.put("五家渠","10300");
        mysqlcity.put("双河","10301");
        mysqlcity.put("铁门关","10302");
        mysqlcity.put("北屯","10303");
        mysqlcity.put("呼和浩特","10304");
        mysqlcity.put("包头","10305");
        mysqlcity.put("乌海","10306");
        mysqlcity.put("赤峰","10307");
        mysqlcity.put("通辽","10308");
        mysqlcity.put("鄂尔多斯","10309");
        mysqlcity.put("呼伦贝尔","10310");
        mysqlcity.put("巴彦淖尔","10311");
        mysqlcity.put("乌兰察布","10312");
        mysqlcity.put("银川","10313");
        mysqlcity.put("石嘴山","10314");
        mysqlcity.put("吴忠","10315");
        mysqlcity.put("固原","10316");
        mysqlcity.put("中卫","10317");
        mysqlcity.put("拉萨","10318");
        mysqlcity.put("日喀则","10319");
        mysqlcity.put("南宁","10320");
        mysqlcity.put("柳州","10321");
        mysqlcity.put("桂林","10322");
        mysqlcity.put("梧州","10323");
        mysqlcity.put("北海","10324");
        mysqlcity.put("防城港","10325");
        mysqlcity.put("钦州","10326");
        mysqlcity.put("贵港","10327");
        mysqlcity.put("玉林","10328");
        mysqlcity.put("百色","10329");
        mysqlcity.put("贺州","10330");
        mysqlcity.put("河池","10331");
        mysqlcity.put("来宾","10332");
        mysqlcity.put("崇左","10333");
        mysqlcity.put("深圳","10335");
        mysqlcity.put("杭州","10336");
        mysqlcity.put("济南","10337");
        mysqlcity.put("泰安","10338");
        mysqlcity.put("长沙","10339");
        mysqlcity.put("湘西土家族苗族自治州","10340");
        mysqlcity.put("海北藏族自治州","10341");
        mysqlcity.put("黄南藏族自治州","10342");
        mysqlcity.put("海南藏族自治州","10343");
        mysqlcity.put("果洛藏族自治州","10344");
        mysqlcity.put("海西蒙古族藏族自治州","10345");
        mysqlcity.put("哈密地区","10346");
        mysqlcity.put("昌吉回族自治州","10347");
        mysqlcity.put("博尔塔拉蒙古自治州","10348");
        mysqlcity.put("巴音郭楞蒙古自治州","10349");
        mysqlcity.put("阿克苏地区","10350");
        mysqlcity.put("克孜勒苏柯尔克孜自治州","10351");
        mysqlcity.put("喀什地区","10352");
        mysqlcity.put("伊犁哈萨克自治州","10353");
        mysqlcity.put("塔城地区","10354");
        mysqlcity.put("阿勒泰地区","10355");
        mysqlcity.put("兴安盟","10356");
        mysqlcity.put("锡林郭勒盟","10357");
        mysqlcity.put("阿拉善盟","10358");
        mysqlcity.put("昌都","10359");
        mysqlcity.put("山南地区","10360");
        mysqlcity.put("那曲地区","10361");
        mysqlcity.put("阿里地区","10362");
        mysqlcity.put("林芝地区","10363");
        mysqlcity.put("香港","10364");
        mysqlcity.put("九龙","10365");
        mysqlcity.put("新界","10366");
        mysqlcity.put("襄阳","10367");
        mysqlcity.put("神农架林区","10368");
        mysqlcity.put("恩施土家族苗族自治州","10369");
        mysqlcity.put("延边朝鲜族自治州","10370");
        mysqlcity.put("长白山","10371");
        mysqlcity.put("梅河口","10372");
        mysqlcity.put("公主岭","10373");
        mysqlcity.put("大兴安岭","10374");
        mysqlcity.put("德宏傣族景颇族自治州","10375");
        mysqlcity.put("怒江僳僳族自治州","10376");
        mysqlcity.put("迪庆藏族自治州","10377");
        mysqlcity.put("楚雄彝族自治州","10378");
        mysqlcity.put("红河哈尼族彝族自治州","10379");
        mysqlcity.put("文山壮族苗族自治州","10380");
        mysqlcity.put("黔西南布依族苗族自治州","10381");
        mysqlcity.put("黔东南苗族侗族自治州","10382");
        mysqlcity.put("黔南布依族苗族自治州","10383");
        mysqlcity.put("阿坝藏族羌族自治州","10384");
        mysqlcity.put("甘孜藏族自治州","10385");
        mysqlcity.put("凉山彝族自治州","10386");
        mysqlcity.put("台北","10387");
        mysqlcity.put("新北","10388");
        mysqlcity.put("桃园","10389");
        mysqlcity.put("台中","10390");
        mysqlcity.put("台南","10391");
        mysqlcity.put("高雄","10392");
        mysqlcity.put("基隆","10393");
        mysqlcity.put("新竹","10394");
        mysqlcity.put("嘉义","10395");
        mysqlcity.put("江阴","10396");
        mysqlcity.put("诸暨","10397");
        mysqlcity.put("永康","10398");
        mysqlcity.put("石狮","10399");
        mysqlcity.put("乐清","10400");
        mysqlcity.put("垦丁","10401");
        mysqlcity.put("阿里山","10402");
        mysqlcity.put("澳门","10403");
        mysqlcity.put("武夷山","10404");
        mysqlcity.put("吉林","10405");
        mysqlcity.put("瑞安","10407");
        mysqlcity.put("宜兴","10408");
        mysqlcity.put("慈溪","10409");
        mysqlcity.put("文登","10410");
        mysqlcity.put("临安","10411");
        mysqlcity.put("靖江","10412");
        mysqlcity.put("昆山","10413");
        mysqlcity.put("义乌","10414");
        mysqlcity.put("晋江","10415");
        mysqlcity.put("花莲","10416");
        mysqlcity.put("台湾其他","10417");
        mysqlcity.put("溧阳","10418");
        mysqlcity.put("象山","10419");
        mysqlcity.put("宁海","10420");
        mysqlcity.put("荣成","10421");
        mysqlcity.put("余姚","10422");
        mysqlcity.put("巢湖","10423");
        mysqlcity.put("恩施","10424");
        mysqlcity.put("福鼎","10425");
        mysqlcity.put("长乐","10426");
        mysqlcity.put("富阳","10427");
        mysqlcity.put("苍南","10428");
        mysqlcity.put("福清","10429");
        mysqlcity.put("张家港","10430");
        mysqlcity.put("平阳","10431");
        mysqlcity.put("奉化","10432");
        mysqlcity.put("常熟","10433");
        mysqlcity.put("章丘","10434");
        mysqlcity.put("浏阳","10435");
        mysqlcity.put("龙海","10436");
        mysqlcity.put("太仓","10437");
        mysqlcity.put("澳门","10438");
        mysqlcity.put("花莲","10439");
        mysqlcity.put("垦丁","10440");
        mysqlcity.put("阿里山","10441");
        mysqlcity.put("南投","10442");
        mysqlcity.put("呼和浩特","10443");
        mysqlcity.put("包头","10444");
        mysqlcity.put("章丘","10445");
        mysqlcity.put("荣成","10446");
        mysqlcity.put("文登","10447");
        mysqlcity.put("昆山","10448");
        mysqlcity.put("常熟","10449");
        mysqlcity.put("张家港","10450");
        mysqlcity.put("太仓","10451");
        mysqlcity.put("江阴","10452");
        mysqlcity.put("宜兴","10453");
        mysqlcity.put("靖江","10454");
        mysqlcity.put("溧阳","10455");
        mysqlcity.put("南京","10456");
        mysqlcity.put("义乌","10457");
        mysqlcity.put("慈溪","10458");
        mysqlcity.put("奉化","10459");
        mysqlcity.put("余姚","10460");
        mysqlcity.put("临安","10461");
        mysqlcity.put("富阳","10462");
        mysqlcity.put("诸暨","10463");
        mysqlcity.put("永康","10464");
        mysqlcity.put("东阳","10465");
        mysqlcity.put("瑞安","10466");
        mysqlcity.put("乐清","10467");
        mysqlcity.put("平阳","10468");
        mysqlcity.put("苍南","10469");
        mysqlcity.put("宁海","10470");
        mysqlcity.put("巢湖","10471");
        mysqlcity.put("福清","10472");
        mysqlcity.put("长乐","10473");
        mysqlcity.put("武夷山","10474");
        mysqlcity.put("石狮","10475");
        mysqlcity.put("晋江","10476");
        mysqlcity.put("龙海","10477");
        mysqlcity.put("福鼎","10478");
        mysqlcity.put("恩施","10479");
        mysqlcity.put("浏阳","10480");
        mysqlcity.put("遂宁","10481");


        }

   /* public List<Json> listShop() {
        //MongoConnect mc = MongoConnect.getInstance;
        // MongoCollection<Document> docs = mc.getCollect("crawler", "dianping_shops");

        // Document filter = Document.parse("{\"is_closed\":\"false\"}");
        // Document dbObject = Document.parse(JSON.toJSONString(filter.getValue));
        Document projection = new Document();

        // MongoCursor<Document> cursor= docs.find().cursorType(CursorType.Tailable).iterator();;
        //  MongoCursor<Document> cursor = docs.find().iterator();
        PreparedStatement stmt = null;

        // stmt = mysqlConnect.conn.createStatement();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/tmp/dianping_上海.csv"));
            String line = "";
            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
           *//* while (cursor.hasNext()) {
                Document doc = cursor.next();*//*
            MysqlConnect mysqlConnect = MysqlConnect.instance;

            try {
                Connection conn = mysqlConnect.conn;
                stmt = conn.prepareStatement("");

                //新增一条数据
                String isexist = "select title from x520_xiaomei_merchant_main where id_dp='" + doc.getInteger("shop_id") + "'";
                ResultSet res = stmt.executeQuery(isexist);

                if (res.next()) {
                    // System.out.println(doc.toJson());
                } else {
                    isexist = "select title from x520_xiaomei_merchant_main_source where id_dp='" + doc.getInteger("shop_id") + "'";
                    ResultSet res_source = stmt.executeQuery(isexist);
                    if (!res_source.next()) {
                        Shop shop = new Shop();
                        Object nameo = doc.get("name");
                        if (nameo != null && StringUtils.isNotBlank(nameo.toString())) {
                            shop.setTitle(nameo.toString());
                        } else {
                            shop.setTitle("");
                        }
                        Object alias = doc.get("alias");
                        if (alias == null || StringUtils.isBlank(alias.toString())) {
                            shop.setAlias(shop.getTitle());
                        } else {
                            String tmp = shop.getTitle();
                            ;
                            shop.setTitle(alias.toString());
                            shop.setAlias(tmp);
                        }
                        Object big_cate = doc.get("big_cate");
                        String big_cate_id = category.get(big_cate != null ? big_cate.toString() : "");
                        if (StringUtils.isNotBlank(big_cate_id)) {
                            shop.setIndustry_id(big_cate_id);
                        } else {
                            shop.setIndustry_id("0");
                        }
                        String province_id = mysqlprovince.get(doc.getString("province"));
                        if (StringUtils.isNotBlank(province_id)) {
                            shop.setProvince_id(province_id);
                        } else {
                            shop.setProvince_id("0");
                        }
                        Object city = doc.get("city");
                        if (city != null && StringUtils.isNotBlank(city.toString())) {
                            shop.setCity(city.toString());
                        } else {
                            shop.setCity("");
                        }

                        String city_id = mysqlcity.get(shop.getCity());
                        if (StringUtils.isNotBlank(city_id)) {
                            shop.setCity_id(city_id);
                        } else {
                            shop.setCity_id("0");
                        }
                        Object area = doc.get("area");
                        if (area != null && StringUtils.isNotBlank(area.toString())) {
                            shop.setArea(area.toString());
                        } else {
                            shop.setArea("");
                        }
                        Object address = doc.get("address");
                        if (address != null && StringUtils.isNotBlank(address.toString())) {
                            shop.setAddress(address.toString());
                        } else {
                            shop.setAddress("");
                        }
                        Object shop_id = doc.get("shop_id");
                        if (shop_id != null && StringUtils.isNotBlank(shop_id.toString())) {
                            shop.setId_dp(shop_id.toString());
                        }
                        Object latitude = doc.get("latitude");
                        if (latitude != null && StringUtils.isNotBlank(latitude.toString())) {
                            shop.setLat(latitude.toString());
                        }
                        Object longitude = doc.get("longitude");
                        if (longitude != null && StringUtils.isNotBlank(longitude.toString())) {
                            shop.setLng(longitude.toString());
                        }
                        Object phone = doc.get("phone");
                        if (phone != null && StringUtils.isNotBlank(phone.toString()) && phone.toString().length() < 15) {
                            shop.setTelephone(phone.toString());
                        }
                        Object product = doc.get("product_rating");
                        if (product != null && StringUtils.isNotBlank(product.toString())) {
                            shop.setTaste(product.toString());

                        }
                        Object env = doc.get("environment_rating");
                        if (env != null && StringUtils.isNotBlank(env.toString())) {
                            shop.setEnvironment(env.toString());

                        }
                        Object service = doc.get("service_rating");
                        if (service != null && StringUtils.isNotBlank(service.toString())) {
                            shop.setService(service.toString());

                        }
                        Object avg = doc.get("avg_price");
                        if (avg != null && StringUtils.isNotBlank(avg.toString())) {
                            shop.setAvg_pay(avg.toString());

                        }
                        Object remarks = doc.get("all_remarks");
                        if (remarks != null && StringUtils.isNotBlank(remarks.toString())) {
                            shop.setComment(remarks.toString());
                        }
                        shop.setCompany_id("0");
                    *//*db.getCollection('dianping_shops').find({},{"shop_id":1,"name":1,"alias":1,"province":1,    "city_id" : 17,
                    "city":1,"big_cate_id":1,
                    "latitude":1,"longitude":1,"phone":1,"address":1,"product_rating":1,"environment_rating":1,"service_rating":1,
                    "avg_price":1,"stars":1,"all_remarks":1})*//*
                        *//*name = name
                                .replace("!", "!!")
                                .replace("%", "!%")
                                .replace("_", "!_")
                                .replace("[", "![");
                        String sql="SELECT company_id FROM tpx520.x520_xiaomei_company_main where title LIKE ? ESCAPE '!' ";
                        stmt = conn.prepareStatement(sql);
                        stmt.setString(1,"%"+name+"%");
                        ResultSet rs=stmt.executeQuery();
                        System.out.println(shop.getId_dp()+","+name);

                        if (rs.next()) {
                            shop.setCompany_id(rs.getInt("company_id") + "");
                            System.out.println(shop.getCompany_id());
                        }*//*
                        save(shop, conn);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (stmt != null && stmt.isClosed()) {
                        stmt.close();
                    }
                    mysqlConnect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        //  mc.close();
        return null;
    }*/
        }
