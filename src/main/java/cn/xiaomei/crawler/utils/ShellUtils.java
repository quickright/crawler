package cn.xiaomei.crawler.utils;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;

import java.io.*;
import java.security.KeyStore;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by chunli on 16/4/25.
 */
public class ShellUtils {
    public static void main(String [] args){
            List<String> urls=new ArrayList<String>();
            //获取第一页数据
          /*  String url="wget  --connect-timeout=20 --read-timeout=50 --no-check-certificate --server-response --quiet -x --load-cookies /usr/local/nuomi.cookies --post-data=\"page=0&perPage=100&skipIndex=0&format=json&stationId="+key+"&sortInfo=2\" " +
                    "http://crmv4.nuomi.com/crm/pubPriSeaQuery/queryPubSeaAll.json -O "+initdata+key+"_0.json";
            urls.add(url);*/
            File shellfile=new File("/tmp/a.shell");
            // String url="wget  --connect-timeout=20 --read-timeout=50 --no-check-certificate --server-response --quiet -x --load-cookies /usr/local/nuomi.cookies --post-data=\"page=0&perPage=100&skipIndex=0&format=json&stationId="+key+"&sortInfo=2\" " +
            //        "http://crmv4.nuomi.com/crm/pubPriSeaQuery/queryPubSeaAll.json -O -";
            //String page1= execNuomiTelUrl(url);
                for(int i=4410;i<=40975;i++) {

                    String url = "wget --connect-timeout=20 --read-timeout=50 --no-check-certificate --server-response --quiet -x " +
                            "http://www.youtonggao.com/ytg/ann/front/announcement/single/ann?annId=ann"+i+" -O /tmp/down/"  + i + ".json";
                    urls.add(url);
                }
                    // System.out.println("wget -x --load-cookies /usr/local/nuomi.cookies --post-data=\"page="+i+"&perPage=100&skipIndex="+(i*100)+"&format=json&stationId=2014&sortInfo=2\" http://crmv4.nuomi.com/crm/pubPriSeaQuery/queryPubSeaAll.json -O "+i+".json");

                // System.out.println("sh -x "+shellfile);
                try {
                    FileUtils.writeLines(shellfile,urls);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            //  String count=jo.toString();
            // int totalPage=Integer.parseInt(count);
            /*for (int i = 0; i < totalPage; i++) {
                 url="wget --connect-timeout=20 --read-timeout=50 --no-check-certificate --server-response --quiet -x --load-cookies /usr/local/nuomi.cookies --post-data=\"page="+i+"&perPage=100&skipIndex="+(i*100)+"&format=json&stationId=2014&sortInfo=2\" http://crmv4.nuomi.com/crm/pubPriSeaQuery/queryPubSeaAll.json -O "+i+".json";

                // System.out.println("wget -x --load-cookies /usr/local/nuomi.cookies --post-data=\"page="+i+"&perPage=100&skipIndex="+(i*100)+"&format=json&stationId=2014&sortInfo=2\" http://crmv4.nuomi.com/crm/pubPriSeaQuery/queryPubSeaAll.json -O "+i+".json");
                try {
                    FileUtils.writeStringToFile(file,url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
    }
    public static void main1(String[] args) {

        Map<String, String> stations = new HashMap<String, String>();
        stations.put("1774", "仙桃市代理站");
        stations.put("1776", "桐乡市代理站");
        stations.put("1778", "长春市代理站");
        stations.put("1780", "榆林市代理站");
        stations.put("1782", "呼伦贝尔市代理站");
        stations.put("1784", "洛阳市代理站");
        stations.put("1786", "汕头市代理站");
        stations.put("1788", "周口市代理站");
        stations.put("1790", "昆明市代理站");
        stations.put("1792", "湘潭市代理站");
        stations.put("1794", "白城市代理站");
        stations.put("1796", "海口市直销站");
        stations.put("1798", "绥化市代理站");
        stations.put("1800", "开县直销站");
        stations.put("1802", "台州市代理站");
        stations.put("1804", "滨海新区直销站");
        stations.put("1806", "新郑市直销站");
        stations.put("1808", "南充市代理站");
        stations.put("1810", "泰州市代理站");
        stations.put("1812", "抚州市代理站");
        stations.put("1814", "鹰潭市代理站");
        stations.put("1816", "西宁市代理站");
        stations.put("1818", "安顺市代理站");
        stations.put("1820", "伊春市代理站");
        stations.put("1822", "汤阴县代理站");
        stations.put("1824", "合川区直销站");
        stations.put("1826", "潮州市代理站");
        stations.put("1828", "太仓市直销站");
        stations.put("1830", "邯郸市代理站");
        stations.put("1832", "湖州市代理站");
        stations.put("1834", "桐城市代理站");
        stations.put("1836", "咸阳市代理站");
        stations.put("1838", "登封市直销站");
        stations.put("1840", "珠海市代理站");
        stations.put("1842", "德州市代理站");
        stations.put("1844", "章丘市直销站");
        stations.put("1846", "南川区直销站");
        stations.put("1848", "东莞市直销站");
        stations.put("1850", "西昌市代理站");
        stations.put("1852", "来宾市代理站");
        stations.put("1854", "渭南市代理站");
        stations.put("1856", "桂林市代理站");
        stations.put("1858", "晋城市代理站");
        stations.put("1860", "阳朔市代理站");
        stations.put("1862", "蓬莱市代理站");
        stations.put("1864", "那曲地区代理站");
        stations.put("1866", "中山市代理站");
        stations.put("1868", "抚顺市代理站");
        stations.put("1870", "佛山市直销站");
        stations.put("1872", "晋江市代理站");
        stations.put("1874", "平湖市代理站");
        stations.put("1876", "锡林郭勒盟代理站");
        stations.put("1878", "凉山彝族代理站");
        stations.put("1880", "启东县代理站");
        stations.put("1882", "阜新市代理站");
        stations.put("1884", "茂名市代理站");
        stations.put("1886", "武威市代理站");
        stations.put("1888", "郴州市代理站");
        stations.put("1890", "句容市代理站");
        stations.put("1892", "日喀则地区代理站");
        stations.put("1894", "宝鸡市代理站");
        stations.put("1896", "焦作市代理站");
        stations.put("1898", "十堰市代理站");
        stations.put("1900", "石狮市代理站");
        stations.put("1902", "黄冈市代理站");
        stations.put("1904", "衢州市代理站");
        stations.put("1906", "黄山市代理站");
        stations.put("1908", "海东地区代理站");
        stations.put("1910", "承德市代理站");
        stations.put("1912", "张家界市代理站");
        stations.put("1914", "九寨沟代理站");
        stations.put("1916", "荥阳市直销站");
        stations.put("1918", "淳安市直销站");
        stations.put("1920", "宁波市代理站");
        stations.put("1922", "辉县市代理站");
        stations.put("1924", "阿坝藏族羌族自治州代理站");
        stations.put("1926", "滁州市代理站");
        stations.put("1928", "杭州市直销站");
        stations.put("1930", "盘锦市代理站");
        stations.put("1932", "三沙市直销站");
        stations.put("1934", "和田地区代理站");
        stations.put("1936", "卫辉市代理站");
        stations.put("1938", "鸡西市代理站");
        stations.put("1940", "常熟市直销站");
        stations.put("1942", "辽源市代理站");
        stations.put("1944", "许昌市代理站");
        stations.put("1946", "潜江市代理站");
        stations.put("1948", "金坛市代理站");
        stations.put("1950", "昌都地区代理站");
        stations.put("1952", "聊城市代理站");
        stations.put("1954", "萍乡市代理站");
        stations.put("1956", "鄢陵县代理站");
        stations.put("1958", "常德市代理站");
        stations.put("1960", "儋州市直销站");
        stations.put("1962", "天津市直销站");
        stations.put("1964", "武夷山代理站");
        stations.put("1966", "沧州市代理站");
        stations.put("1968", "吴忠市代理站");
        stations.put("1970", "临安市直销站");
        stations.put("1972", "温岭市代理站");
        stations.put("1974", "慈溪市代理站");
        stations.put("1976", "东方市直销站");
        stations.put("1978", "日照市代理站");
        stations.put("1980", "文昌市直销站");
        stations.put("1982", "临潼市直销站");
        stations.put("1984", "玉溪市代理站");
        stations.put("1986", "伊犁哈萨克自治州代理站");
        stations.put("1988", "琼中县直销站");
        stations.put("1990", "陵水县直销站");
        stations.put("1992", "乐清市直销站");
        stations.put("1994", "诸暨市代理站");
        stations.put("1996", "文登市代理站");
        stations.put("1998", "鹤壁市代理站");
        stations.put("2000", "珲春市代理站");
        stations.put("2002", "乐山市代理站");
        stations.put("2004", "贵港市代理站");
        stations.put("2006", "安丘代理站");
        stations.put("2008", "天长市代理站");
        stations.put("2010", "黔西南布依族苗族自治州代理站");
        stations.put("2012", "南昌市代理站");
        stations.put("2014", "上海市直销站");
        stations.put("2016", "荆门市代理站");
        stations.put("2018", "包头市代理站");
        stations.put("2020", "阳泉市代理站");
        stations.put("2022", "绵阳市代理站");
        stations.put("2024", "临沧市代理站");
        stations.put("2026", "防城港市代理站");
        stations.put("2028", "景德镇市代理站");
        stations.put("2030", "克拉玛依市代理站");
        stations.put("2032", "岳阳市代理站");
        stations.put("2034", "张家口市代理站");
        stations.put("2036", "邳州市代理站");
        stations.put("2038", "西峡县代理站");
        stations.put("2040", "琼海市直销站");
        stations.put("2042", "昌江县直销站");
        stations.put("2044", "顺德区直销站");
        stations.put("2046", "龙岩市代理站");
        stations.put("2048", "辽阳市代理站");
        stations.put("2050", "宁国市代理站");
        stations.put("2052", "汕尾市代理站");
        stations.put("2054", "怒江傈僳族自治州代理站");
        stations.put("2056", "四平市代理站");
        stations.put("2058", "澳门特别行政区直销站");
        stations.put("2060", "钦州市代理站");
        stations.put("2062", "靖江市代理站");
        stations.put("2064", "沛县代理站");
        stations.put("2066", "余杭市直销站");
        stations.put("2068", "晋中市代理站");
        stations.put("2070", "濮阳市代理站");
        stations.put("2072", "长垣县代理站");
        stations.put("2074", "海南藏族自治州代理站");
        stations.put("2076", "锦州市代理站");
        stations.put("2078", "巢湖市直销站");
        stations.put("2080", "阳江市代理站");
        stations.put("2082", "威海市代理站");
        stations.put("2084", "大理白族自治州代理站");
        stations.put("2086", "万宁市直销站");
        stations.put("2088", "新密市直销站");
        stations.put("2090", "海宁市代理站");
        stations.put("2092", "重庆市直销站");
        stations.put("2094", "兴安盟代理站");
        stations.put("2096", "株洲市代理站");
        stations.put("2098", "石家庄市代理站");
        stations.put("2100", "韶关市代理站");
        stations.put("2102", "西安市直销站");
        stations.put("2104", "吕梁市代理站");
        stations.put("2106", "鄂尔多斯市代理站");
        stations.put("2108", "东营市代理站");
        stations.put("2110", "乳山市代理站");
        stations.put("2112", "朝阳市代理站");
        stations.put("2114", "果洛藏族自治州代理站");
        stations.put("2116", "大兴安岭地区代理站");
        stations.put("2118", "林州市代理站");
        stations.put("2120", "三门峡市代理站");
        stations.put("2122", "江津区直销站");
        stations.put("2124", "通辽市代理站");
        stations.put("2126", "厦门市直销站");
        stations.put("2128", "石河子市代理站");
        stations.put("2130", "定西市代理站");
        stations.put("2132", "张家港市直销站");
        stations.put("2134", "伊川县代理站");
        stations.put("2136", "中卫市代理站");
        stations.put("2138", "张掖市代理站");
        stations.put("2140", "定安县直销站");
        stations.put("2142", "铜川市代理站");
        stations.put("2144", "富阳市直销站");
        stations.put("2146", "乌鲁木齐市代理站");
        stations.put("2148", "偃师市代理站");
        stations.put("2150", "贵阳市代理站");
        stations.put("2152", "常州市代理站");
        stations.put("2154", "阿里地区代理站");
        stations.put("2156", "澄迈县直销站");
        stations.put("2158", "迪庆藏族自治州代理站");
        stations.put("2160", "鹤岗市代理站");
        stations.put("2162", "黑河市代理站");
        stations.put("2164", "楚雄彝族自治州代理站");
        stations.put("2166", "娄底市代理站");
        stations.put("2168", "中牟县直销站");
        stations.put("2170", "宝丰县代理站");
        stations.put("2172", "长兴市代理站");
        stations.put("2174", "云浮市代理站");
        stations.put("2176", "梧州市代理站");
        stations.put("2178", "邢台市代理站");
        stations.put("2180", "六安市代理站");
        stations.put("2182", "太原市直销站");
        stations.put("2184", "拉萨市代理站");
        stations.put("2186", "漳州市代理站");
        stations.put("2188", "肇庆市代理站");
        stations.put("2190", "宁德市代理站");
        stations.put("2192", "南阳市代理站");
        stations.put("2194", "永州市代理站");
        stations.put("2196", "黄石市代理站");
        stations.put("2198", "马鞍山市代理站");
        stations.put("2200", "广安市代理站");
        stations.put("2202", "恩施市代理站");
        stations.put("2204", "金昌市代理站");
        stations.put("2206", "葫芦岛市代理站");
        stations.put("2208", "长葛市代理站");
        stations.put("2210", "西双版纳傣族自治州代理站");
        stations.put("2212", "萧山市直销站");
        stations.put("2214", "南平市代理站");
        stations.put("2216", "招远市代理站");
        stations.put("2218", "嘉峪关市代理站");
        stations.put("2220", "瑞安市直销站");
        stations.put("2222", "安吉市代理站");
        stations.put("2224", "婺源县代理站");
        stations.put("2226", "南京市直销站");
        stations.put("2228", "益阳市代理站");
        stations.put("2230", "朔州市代理站");
        stations.put("2232", "苏州市直销站");
        stations.put("2234", "怀化市代理站");
        stations.put("2236", "汝州市代理站");
        stations.put("2238", "东沙群岛直销站");
        stations.put("2240", "成都市直销站");
        stations.put("2242", "呼和浩特市代理站");
        stations.put("2244", "南通市代理站");
        stations.put("2246", "海安县代理站");
        stations.put("2248", "达州市代理站");
        stations.put("2250", "快递事业部直销站");
        stations.put("2252", "松原市代理站");
        stations.put("2254", "鲁山县代理站");
        stations.put("2256", "东阳市代理站");
        stations.put("2258", "赣州市代理站");
        stations.put("2260", "嘉善市代理站");
        stations.put("2262", "仪征市直销站");
        stations.put("2264", "淄博市代理站");
        stations.put("2266", "泉州市代理站");
        stations.put("2268", "德清市代理站");
        stations.put("2270", "运城市代理站");
        stations.put("2272", "遵义市代理站");
        stations.put("2274", "惠州市代理站");
        stations.put("2276", "乌海市代理站");
        stations.put("2278", "象山城代理站");
        stations.put("2280", "屯昌县直销站");
        stations.put("2282", "石嘴山市代理站");
        stations.put("2284", "安康市代理站");
        stations.put("2286", "香港特别行政区直销站");
        stations.put("2288", "唐河县代理站");
        stations.put("2290", "乐东县直销站");
        stations.put("2292", "清远市代理站");
        stations.put("2294", "青岛市直销站");
        stations.put("2296", "寿光市代理站");
        stations.put("2298", "昌吉回族自治州代理站");
        stations.put("2300", "银川市代理站");
        stations.put("2302", "德阳市代理站");
        stations.put("2304", "保亭县直销站");
        stations.put("2306", "镇平县代理站");
        stations.put("2308", "牡丹江市代理站");
        stations.put("2310", "临汾市代理站");
        stations.put("2312", "上饶市代理站");
        stations.put("2314", "邵阳市代理站");
        stations.put("2316", "白沙县直销站");
        stations.put("2318", "咸宁代理站");
        stations.put("2320", "兰溪市代理站");
        stations.put("2322", "南宁市代理站");
        stations.put("2324", "白银市代理站");
        stations.put("2326", "连云港市代理站");
        stations.put("2328", "海门市代理站");
        stations.put("2330", "永川区直销站");
        stations.put("2332", "安庆市代理站");
        stations.put("2334", "随州市代理站");
        stations.put("2336", "峨眉山市代理站");
        stations.put("2338", "吐鲁番地区代理站");
        stations.put("2340", "蚌埠市代理站");
        stations.put("2342", "唐山市代理站");
        stations.put("2344", "信阳市代理站");
        stations.put("2346", "哈密地区代理站");
        stations.put("2348", "九江市代理站");
        stations.put("2350", "阜康市代理站");
        stations.put("2352", "公主岭市代理站");
        stations.put("2354", "龙口代理站");
        stations.put("2356", "嘉兴市代理站");
        stations.put("2358", "兰州市代理站");
        stations.put("2360", "开封市代理站");
        stations.put("2362", "普洱市代理站");
        stations.put("2364", "五指山市直销站");
        stations.put("2366", "崇左市代理站");
        stations.put("2368", "红河哈尼族彝族自治州代理站");
        stations.put("2370", "阿拉尔市代理站");
        stations.put("2372", "建德市直销站");
        stations.put("2374", "郑州市直销站");
        stations.put("2376", "黔南布依族苗族自治州代理站");
        stations.put("2378", "临高县直销站");
        stations.put("2380", "鞍山市代理站");
        stations.put("2382", "郏县代理站");
        stations.put("2384", "图木舒克市代理站");
        stations.put("2386", "曲阜市代理站");
        stations.put("2388", "宜春市代理站");
        stations.put("2390", "吴江市直销站");
        stations.put("2392", "海阳市代理站");
        stations.put("2394", "攀枝花市代理站");
        stations.put("2396", "盐城市代理站");
        stations.put("2398", "池州市代理站");
        stations.put("2400", "哈尔滨市直销站");
        stations.put("2402", "枣庄市代理站");
        stations.put("2404", "宜宾市代理站");
        stations.put("2406", "山南地区代理站");
        stations.put("2408", "长沙市代理站");
        stations.put("2410", "阿勒泰地区代理站");
        stations.put("2412", "温州市直销站");
        stations.put("2414", "乌兰察布市代理站");
        stations.put("2416", "沈阳市直销站");
        stations.put("2418", "鄂州市代理站");
        stations.put("2420", "龙胜市代理站");
        stations.put("2422", "保定市代理站");
        stations.put("2424", "白山市代理站");
        stations.put("2426", "镇江市代理站");
        stations.put("2428", "菏泽市代理站");
        stations.put("2430", "漯河市代理站");
        stations.put("2432", "宜兴市代理站");
        stations.put("2434", "阎良市直销站");
        stations.put("2436", "双鸭山市代理站");
        stations.put("2438", "海北藏族自治州代理站");
        stations.put("2440", "敦化市代理站");
        stations.put("2442", "百色市代理站");
        stations.put("2444", "佳木斯市代理站");
        stations.put("2446", "湘西土家族苗族自治州代理站");
        stations.put("2448", "明光市代理站");
        stations.put("2450", "延安市代理站");
        stations.put("2452", "海西蒙古族藏族自治州代理站");
        stations.put("2454", "平顶山市代理站");
        stations.put("2456", "北海市代理站");
        stations.put("2458", "孟津县代理站");
        stations.put("2460", "本溪市代理站");
        stations.put("2462", "江门市代理站");
        stations.put("2464", "亳州市代理站");
        stations.put("2466", "昭通市代理站");
        stations.put("2468", "大丰县代理站");
        stations.put("2470", "济宁市代理站");
        stations.put("2472", "滨州市代理站");
        stations.put("2474", "昆山市直销站");
        stations.put("2476", "梅州市代理站");
        stations.put("2478", "内江市代理站");
        stations.put("2480", "河池市代理站");
        stations.put("2482", "桦甸市代理站");
        stations.put("2484", "巴彦淖尔市代理站");
        stations.put("2486", "揭阳市代理站");
        stations.put("2488", "义乌市代理站");
        stations.put("2490", "凤凰市代理站");
        stations.put("2492", "神农架林区代理站");
        stations.put("2494", "烟台市代理站");
        stations.put("2496", "宿州市代理站");
        stations.put("2498", "合肥市直销站");
        stations.put("2500", "临夏回族自治州代理站");
        stations.put("2502", "三河市代理站");
        stations.put("2504", "河源市代理站");
        stations.put("2506", "巴中市代理站");
        stations.put("2508", "平凉市代理站");
        stations.put("2510", "莆田市代理站");
        stations.put("2512", "绍兴市代理站");
        stations.put("2514", "临夏市代理站");
        stations.put("2516", "临沂市代理站");
        stations.put("2518", "巴音郭楞蒙古自治州代理站");
        stations.put("2520", "北京市直销站");
        stations.put("2522", "衡水市代理站");
        stations.put("2524", "铁岭市代理站");
        stations.put("2526", "七台河市代理站");
        stations.put("2528", "德宏傣族景颇族自治州代理站");
        stations.put("2530", "保山市代理站");
        stations.put("2532", "大同市代理站");
        stations.put("2534", "林芝地区代理站");
        stations.put("2536", "营口市代理站");
        stations.put("2538", "新余市代理站");
        stations.put("2540", "芜湖市代理站");
        stations.put("2542", "襄阳市代理站");
        stations.put("2544", "万州区直销站");
        stations.put("2546", "上虞市代理站");
        stations.put("2548", "深圳市直销站");
        stations.put("2550", "荣成市代理站");
        stations.put("2552", "天水市代理站");
        stations.put("2554", "六盘水代理站");
        stations.put("2556", "固原市代理站");
        stations.put("2558", "丰县代理站");
        stations.put("2560", "酒泉市代理站");
        stations.put("2562", "丹东市代理站");
        stations.put("2564", "三明市代理站");
        stations.put("2566", "眉山市代理站");
        stations.put("2568", "宿迁市代理站");
        stations.put("2570", "江阴市代理站");
        stations.put("2572", "安阳市代理站");
        stations.put("2574", "禹州市代理站");
        stations.put("2576", "文山壮族苗族自治州代理站");
        stations.put("2578", "吉林市代理站");
        stations.put("2580", "泸州市代理站");
        stations.put("2582", "延吉市代理站");
        stations.put("2584", "庆阳市代理站");
        stations.put("2586", "通化市代理站");
        stations.put("2588", "綦江区直销站");
        stations.put("2590", "溧阳市代理站");
        stations.put("2592", "商丘市代理站");
        stations.put("2594", "广元市代理站");
        stations.put("2596", "永康市代理站");
        stations.put("2598", "天门市代理站");
        stations.put("2600", "巩义市直销站");
        stations.put("2602", "金华市代理站");
        stations.put("2604", "吉安市代理站");
        stations.put("2606", "资阳市代理站");
        stations.put("2608", "淮安市代理站");
        stations.put("2610", "泰安市代理站");
        stations.put("2612", "玉树藏族自治州代理站");
        stations.put("2614", "喀什地区代理站");
        stations.put("2616", "兖州市代理站");
        stations.put("2618", "荆州市代理站");
        stations.put("2620", "塔城地区代理站");
        stations.put("2622", "徐州市代理站");
        stations.put("2624", "余姚市代理站");
        stations.put("2626", "淮南市代理站");
        stations.put("2628", "舟山市代理站");
        stations.put("2630", "延边朝鲜族自治州代理站");
        stations.put("2632", "湛江市代理站");
        stations.put("2634", "五家渠市代理站");
        stations.put("2636", "福清市直销站");
        stations.put("2638", "界首市代理站");
        stations.put("2640", "赤峰市代理站");
        stations.put("2642", "潍坊市代理站");
        stations.put("2644", "济南市直销站");
        stations.put("2646", "甘孜藏族自治州代理站");
        stations.put("2648", "睢宁县代理站");
        stations.put("2650", "青州市代理站");
        stations.put("2652", "福州市直销站");
        stations.put("2654", "自贡市代理站");
        stations.put("2656", "忻州市代理站");
        stations.put("2658", "秦皇岛市代理站");
        stations.put("2660", "遂宁市代理站");
        stations.put("2662", "毕节地区代理站");
        stations.put("2664", "柳州市代理站");
        stations.put("2666", "博塔拉蒙古自治州代理站");
        stations.put("2668", "长治市代理站");
        stations.put("2670", "玉林市代理站");
        stations.put("2672", "莱芜市代理站");
        stations.put("2674", "汉中市代理站");
        stations.put("2676", "商洛市代理站");
        stations.put("2678", "衡阳市代理站");
        stations.put("2680", "无锡市代理站");
        stations.put("2682", "陇南市代理站");
        stations.put("2684", "涿州市代理站");
        stations.put("2686", "贺州市代理站");
        stations.put("2688", "大客户事业部直销站");
        stations.put("2690", "扬州市直销站");
        stations.put("2692", "宜阳县代理站");
        stations.put("2694", "丽江市代理站");
        stations.put("2696", "满洲里市代理站");
        stations.put("2698", "齐齐哈尔市代理站");
        stations.put("2700", "宣城市代理站");
        stations.put("2702", "甘南藏族自治州代理站");
        stations.put("2704", "武汉市代理站");
        stations.put("2706", "宜昌市代理站");
        stations.put("2708", "黄南藏族自治州代理站");
        stations.put("2710", "丽水市代理站");
        stations.put("2712", "淮北市代理站");
        stations.put("2714", "克孜勒苏柯尔克孜自治州代理站");
        stations.put("2716", "如皋县代理站");
        stations.put("2718", "新乡市代理站");
        stations.put("2720", "廊坊市代理站");
        stations.put("2722", "驻马店市代理站");
        stations.put("2724", "阜阳市代理站");
        stations.put("2726", "三亚市直销站");
        stations.put("2728", "雅安市代理站");
        stations.put("2730", "铜陵市代理站");
        stations.put("2732", "济源市代理站");
        stations.put("2734", "黔东南苗族侗族自治州代理站");
        stations.put("2736", "阿克苏地区代理站");
        stations.put("2738", "淅川县代理站");
        stations.put("2740", "阿拉善盟代理站");
        stations.put("2742", "大连市代理站");
        stations.put("2744", "襄城县代理站");
        stations.put("2746", "孝感市代理站");
        stations.put("2748", "铜仁地区代理站");
        stations.put("2750", "大庆市代理站");
        stations.put("2752", "桐庐县直销站");
        stations.put("2754", "临海市代理站");
        stations.put("2756", "曲靖市代理站");
        stations.put("2758", "广州市直销站");
        stations.put("3241", "杨陵区直销");
        stations.put("3271", "平阳直销");
        stations.put("3274", "苍南直销");
        stations.put("3277", "浏阳市代理站");
        stations.put("3356", "奎屯市代理");
        stations.put("3485", "独山子区代理");
        stations.put("3488", "库尔勒市代理");
        stations.put("3491", "乌苏市代理");
        stations.put("3494", "绵竹市代理");
        stations.put("3497", "南部县代理");
        stations.put("3500", "阆中市代理");
        stations.put("3503", "江油县代理");
        stations.put("3506", "什邡市代理");
        stations.put("3509", "广汉市代理");
        stations.put("3544", "长寿区直销");
        stations.put("3547", "涪陵区直销");
        stations.put("3550", "黔江区直销");
        stations.put("3553", "铜梁区直销");
        stations.put("3556", "北碚区直销");
        stations.put("3559", "大足区直销");
        stations.put("3562", "璧山区直销");
        stations.put("3565", "长乐市直销");
        stations.put("3568", "平潭县直销");
        stations.put("3637", "滕州市代理");
        stations.put("3846", "古交直销");
        stations.put("3849", "清徐直销");
        stations.put("4217", "蒙城县代理");
        stations.put("4220", "广德县代理");
        stations.put("4224", "乌镇市代理站（代）");
        stations.put("4811", "乐平市代理");
        stations.put("4965", "文登区代理站");
        String dir="/Users/chunli/Downloads/";
        String initshell=dir+"nuomi/shell/";
        String initdata=dir+"nuomi/initdata/";
        String data=dir+"nuomi/data/";
        //File file=new File(initshell);

        for(Map.Entry<String,String> station:stations.entrySet()) {
            List<String> urls=new ArrayList<String>();
            String key=station.getKey();
            //获取第一页数据
          /*  String url="wget  --connect-timeout=20 --read-timeout=50 --no-check-certificate --server-response --quiet -x --load-cookies /usr/local/nuomi.cookies --post-data=\"page=0&perPage=100&skipIndex=0&format=json&stationId="+key+"&sortInfo=2\" " +
                    "http://crmv4.nuomi.com/crm/pubPriSeaQuery/queryPubSeaAll.json -O "+initdata+key+"_0.json";
            urls.add(url);*/
            File shellfile=new File(initshell+key+".shell");
            File page0=new File(initdata+key+"_0.json");
           // String url="wget  --connect-timeout=20 --read-timeout=50 --no-check-certificate --server-response --quiet -x --load-cookies /usr/local/nuomi.cookies --post-data=\"page=0&perPage=100&skipIndex=0&format=json&stationId="+key+"&sortInfo=2\" " +
            //        "http://crmv4.nuomi.com/crm/pubPriSeaQuery/queryPubSeaAll.json -O -";
            //String page1= execNuomiTelUrl(url);
            try {
                String page0Cont=FileUtils.readFileToString(page0);
                JSONObject jo=(JSONObject)JsonUtils.getPathObject(page0Cont,"res");
                String tdir=data+key+"/";
                FileUtils.forceMkdir(new File(tdir));
                for (int i = 0; i < jo.getInt("totalPage"); i++) {

                    String url="wget --connect-timeout=20 --read-timeout=50 --no-check-certificate --server-response --quiet -x --load-cookies /usr/local/nuomi.cookies --post-data=\"page="+i+"&perPage=100&skipIndex="+(i*100)+"&format=json&stationId="+key+"&sortInfo=2\" " +
                            " http://crmv4.nuomi.com/crm/pubPriSeaQuery/queryPubSeaAll.json -O "+tdir+i+".json";
                    urls.add(url);
                    // System.out.println("wget -x --load-cookies /usr/local/nuomi.cookies --post-data=\"page="+i+"&perPage=100&skipIndex="+(i*100)+"&format=json&stationId=2014&sortInfo=2\" http://crmv4.nuomi.com/crm/pubPriSeaQuery/queryPubSeaAll.json -O "+i+".json");

                }
               // System.out.println("sh -x "+shellfile);
                try {
                    FileUtils.writeLines(shellfile,urls);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

          //  String count=jo.toString();
            // int totalPage=Integer.parseInt(count);
            /*for (int i = 0; i < totalPage; i++) {
                 url="wget --connect-timeout=20 --read-timeout=50 --no-check-certificate --server-response --quiet -x --load-cookies /usr/local/nuomi.cookies --post-data=\"page="+i+"&perPage=100&skipIndex="+(i*100)+"&format=json&stationId=2014&sortInfo=2\" http://crmv4.nuomi.com/crm/pubPriSeaQuery/queryPubSeaAll.json -O "+i+".json";

                // System.out.println("wget -x --load-cookies /usr/local/nuomi.cookies --post-data=\"page="+i+"&perPage=100&skipIndex="+(i*100)+"&format=json&stationId=2014&sortInfo=2\" http://crmv4.nuomi.com/crm/pubPriSeaQuery/queryPubSeaAll.json -O "+i+".json");
                try {
                    FileUtils.writeStringToFile(file,url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }

        String url="http://jianli.58.com/ajax/resumemsg/?operate=zptdown&rid=91185033424653";
        url="http://jianli.58.com/resume/91253982237963";
        url="http://jianli.58.com/resume/91429815652106";
        //http://jianli.58.com/favoriteresumelistui/46?PGTID=0d000000-0000-0e1a-b220-78fcb89f3a0a&ClickID=1
        //execFETelUrl(url);
        //getFEtel(url);
        //  System.out.println(getFEPage("http://jianli.58.com/favoriteresumelistui/42"));
        }

    public static String execNuomiTelUrl(String url){
        StringBuffer text=new StringBuffer();
        Scanner scanner = null;
        int lineCount = 0;
        int exitValue = 0;
        try{
            Process pro=Runtime.getRuntime().exec("/bin/bash -c time -p "+url);
            scanner = new Scanner(pro.getInputStream());
            while (scanner.hasNext()) {
                ++lineCount;
                System.out.println(scanner.nextLine());
            }
            System.out.println("No. of Lines: "+lineCount);
            try {
                pro.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            exitValue = pro.exitValue();
            System.out.println((exitValue == 0?
                    "Operation Successful with exit code 0" : ("Operation Failed with exit code "+exitValue)));
            if(exitValue != 0) {
                scanner = new Scanner(pro.getErrorStream());
                while (scanner.hasNext()) {
                    System.out.println(scanner.nextLine());
                }
            }
            try {
                if(!pro.waitFor(1, TimeUnit.MINUTES)) {
                    //timeout - kill the process.
                    pro.destroy(); // consider using destroyForcibly instead
                }
                else{
                    System.out.println("result");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BufferedInputStream br=new BufferedInputStream(pro.getInputStream());
            BufferedInputStream br1=new BufferedInputStream(pro.getErrorStream());
            try{
                Thread.sleep(1000*1);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            int ch;
            while((ch=br.read())!=-1){
                text.append((char)ch);
            }

            StringBuffer text1=new StringBuffer("获得的错误信息是: \n");
            while((ch=br1.read())!=-1){
                text1.append((char)ch);
            }
            String text2=text.length()>9?text.toString():text1.toString();
            //System.out.println(text2);
        }catch(IOException e){
            e.printStackTrace();
            return text+"";
        }
        return text+"";
    }
public static String execFETelUrl(String url){
        StringBuffer text=new StringBuffer("");
        try{
        Process pro=Runtime.getRuntime().exec("wget -x --load-cookies /apps/xiaomei/58cookies.txt "+url+" -O -");
        BufferedInputStream br=new BufferedInputStream(pro.getInputStream());
        BufferedInputStream br1=new BufferedInputStream(pro.getErrorStream());
            BufferedReader reader=new BufferedReader(new InputStreamReader(br));
            try{
                Thread.sleep(1000*1);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            while(reader.ready()){
                text.append((char)reader.read());
            }
            StringBuffer text1=new StringBuffer("获得的错误信息是: \n");
            int ch;
            while((ch=br1.read())!=-1){
                text1.append((char)ch);
            }
          System.out.println(text1);
        }catch(IOException e){
        e.printStackTrace();
        }
    return text+"";
        }

public static String getFEtel(String url){
        String tel="";
        try{
        Process pro=Runtime.getRuntime().exec("wget -x --load-cookies /apps/xiaomei/cookies.txt "+url+" -O - ");
        BufferedInputStream br=new BufferedInputStream(pro.getInputStream());
        BufferedInputStream br1=new BufferedInputStream(pro.getErrorStream());
        int ch;
        StringBuffer text=new StringBuffer();
        while((ch=br.read())!=-1){
        text.append((char)ch);
        }
        Html html=new Html(text.toString());
        tel=html.xpath("//p[@class=\"llnumber\"]/text()").get();

        StringBuffer text1=new StringBuffer("获得的错误信息是: \n");
        while((ch=br1.read())!=-1){
        text1.append((char)ch);
        }
        String text2=text.length()>9?text.toString():text1.toString();
        //System.out.println(text2);
        }catch(IOException e){
        e.printStackTrace();
        return tel;
        }
        return tel;
        }

public static List<String> getFEPage(String url){
        List<String>tel=new ArrayList<String>();
        try{
        Process pro=Runtime.getRuntime().exec("wget -x --load-cookies /apps/xiaomei/cookies.txt "+url+" -O - ");
        BufferedInputStream br=new BufferedInputStream(pro.getInputStream());
        BufferedInputStream br1=new BufferedInputStream(pro.getErrorStream());
        int ch;
        StringBuffer text=new StringBuffer();
        while((ch=br.read())!=-1){
        text.append((char)ch);
        }
        Html html=new Html(text.toString());
        //for(int i=0;i<)
        tel=html.xpath("//tr[@onmouseout=\"this.style.background='#fff'\"]/td[3]/a/@href").all();

        StringBuffer text1=new StringBuffer("获得的错误信息是: \n");
        while((ch=br1.read())!=-1){
        text1.append((char)ch);
        }
        String text2=text.length()>9?text.toString():text1.toString();
        //System.out.println(text2);
        }catch(IOException e){
        e.printStackTrace();
        return tel;
        }
        return tel;
        }

public static String getHTML(String url){
        StringBuffer text=new StringBuffer("");
        try{
        Process pro=Runtime.getRuntime().exec("wget -x --load-cookies /apps/xiaomei/cookies.txt "+url+" -O -");
        BufferedInputStream br=new BufferedInputStream(pro.getInputStream());
        BufferedInputStream br1=new BufferedInputStream(pro.getErrorStream());
        BufferedReader reader=new BufferedReader(new InputStreamReader(br));
        try{
        Thread.sleep(1000*1);
        }catch(InterruptedException e){
        e.printStackTrace();
        }
        while(reader.ready()){
        text.append((char)reader.read());
        }
        StringBuffer text1=new StringBuffer("获得的错误信息是: \n");
        int ch;
        while((ch=br1.read())!=-1){
        text1.append((char)ch);
        }
        System.out.println(text1);
        }catch(IOException e){
        e.printStackTrace();
        }
        return text.toString();
        }
        }
