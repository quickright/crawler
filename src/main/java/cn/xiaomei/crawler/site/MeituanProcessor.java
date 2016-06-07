package cn.xiaomei.crawler.site;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.db.MysqlShopPipeline;
import cn.xiaomei.crawler.db.bean.Shop;
import cn.xiaomei.crawler.utils.JsonUtils;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONArray;
import org.json.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;

/**
 * Created by chunli on 16/3/3.
 */
public class MeituanProcessor implements PageProcessor {

    //public static final String URL_LIST = "http://www.qiushibaike.com/";
    public static final String URL_LIST = "http://\\w+.meituan.com/category/all/\\w+";
    public static final String PAGE_LIST = "http://\\w+.meituan.com/category/all/\\w+/\\w+";
    public static final String DETAIL_LIST_1 = "http://www.nuomi.com/deal/\\w+.html";
    public static final String DETAIL_LIST_2 = "http://\\w.nuomi.com/\\w/goods/detail?tiny_url=\\w+";

    public static final String POI_LIST_URL="http://www.meituan.com/deal/showbizlogin/";
    public static final String POI_URL = "http://bj.meituan.com/shop/";


    public static final String FROM_URL = "http://www.meituan.com";
    public static final Map<String, String> urls = new HashMap<String, String>();

    private Site site = Site
            .me()
            .setDomain(FROM_URL)
            .setRetryTimes(3)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {
        System.out.println(page.getUrl().get());
        if(page.getUrl().regex(URL_LIST).match()||page.getUrl().regex(PAGE_LIST).match()) {
          List<Selectable> jrlist = page.getHtml().xpath("//div[@data-component=\"deal-list\"]/div").nodes();
            Set<String> urls=new HashSet<String>();
//
            ////*[@id="content"]/div[3]/ul/li[6]/a
          for (Selectable jr : jrlist) {
              String idjson=jr.xpath("//div[@class=\"deal-tile\"]/@data-mteventnd").get();
              String id=JsonUtils.getPathObject(idjson,"id")+"";
              String url=POI_LIST_URL+id;
              urls.add(url);
          }
            List<Selectable> pagelist = page.getHtml().xpath("//div[@class=\"paginator-wrapper\"]/ul/li").nodes();
            for (Selectable pageurl : pagelist) {
                String pageu=pageurl.xpath("//li/a/@href").get();
                System.out.println(pageu);
                if(pageu!=null) {
                    page.addTargetRequest(pageu);//"http://bj.meituan.com"+pageurl.xpath("//li/a/@href").get());
                }
                }
            page.addTargetRequests(new ArrayList(urls));
      }else if(page.getUrl().get().indexOf(POI_LIST_URL)>-1){

            //poi 列表
            String components=page.getHtml().xpath("//div[@data-component=\"soso-map\"]/@data-component-params").get();
            Map<String,Shop> shops=new HashMap<String,Shop>();

            JSONObject jarryobject=JsonUtils.getPathObject(components, "mapData", "shops");
                for(String key:jarryobject.keySet())
                {
                    Shop shop =new Shop();
                    String url=POI_URL+key;
                    String cjson=jarryobject.getJSONObject(key.toString())+"";
                    String city =JsonUtils.getPathObject(cjson, "city")+"";
                    String address =JsonUtils.getPathObject(cjson, "address")+"";
                    String phone =JsonUtils.getPathObject(cjson, "phone")+"";
                    String name =JsonUtils.getPathObject(cjson, "name")+"";
                    JSONArray position =JsonUtils.getPathArray(cjson, "position");
                    String latitude=position.get(0)+"";
                    String longitude=position.get(1)+"";
                    shop.setCity_id(city);
                    shop.setDistrict_id("");
                    shop.setAddress(address);
                    shop.setPhone(phone);
                    shop.setName(name);
                    shop.setShop_id(key);
                    shop.setLatitude(latitude);
                    shop.setChannel(Constant.ChannelMeituan);
                    shop.setLongitude(longitude);
                    shop.setLink(url);
                    shops.put(key, shop);

                }
               // JsonUtils.getPathObject(jo.toString())


            List<Selectable> jrlist = page.getHtml().xpath("//ul[@id=\"biz-address-list\"]/li").nodes();


            for (Selectable jr : jrlist) {

                String id=jr.xpath("//p[@class=\"has-poi-correct\"]/a/@data-id").get();
                String comment=jr.xpath("//p[@class=\"poi-level\"]/a/text()").get();
                String star=jr.xpath("//p[@class=\"poi-level\"]/strong/text()").get();
                Shop shop=shops.get(id);
                shop.setPrice(star);
                if(comment!=null&&comment.length()>3) {
                    shop.setComment(comment.substring(0,comment.length()-3));
                }
                shop.setSold("-1");
                shops.put(id, shop);
            }

            for(Map.Entry<String,Shop> shop:shops.entrySet())
            {
                page.putField(shop.getKey(), shop.getValue());
            }
           String next = page.getHtml().xpath("//li[@class=\"next\"]/a/@href").get();
            String pageurl="http://www.meituan.com"+next;
            if(next!=null) {
                page.addTargetRequest(pageurl);
            }
        }/*else if(){

        }*/

        /*if (budejies.size() == 0) {
            page.setSkip(true);
        }*/

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        MeituanProcessor meituanProcessor = new MeituanProcessor();
        Map<String, String> sites = new HashedMap();

        sites.put("http://bj.meituan.com/category/all/haidianqu", "海淀区");
        sites.put("http://bj.meituan.com/category/all/fengtaiqu", "丰台区");
        sites.put("http://bj.meituan.com/category/all/changpingqu", "昌平区");
        sites.put("http://bj.meituan.com/category/all/xichengqu", "西城区");
        sites.put("http://bj.meituan.com/category/all/dongchengqu", "东城区");
        sites.put("http://bj.meituan.com/category/all/daxingqu", "大兴区");
       // sites.put("http://bj.meituan.com/category/all/tongzhouqu", "通州区");
        sites.put("http://bj.meituan.com/category/all/fangshan", "房山区");
        sites.put("http://bj.meituan.com/category/all/shunyi", "顺义区");
        sites.put("http://bj.meituan.com/category/all/shijingshanqu", "石景山区");
        sites.put("http://bj.meituan.com/category/all/huairou", "怀柔区");
        sites.put("http://bj.meituan.com/category/all/miyunxian", "密云县");
        sites.put("http://bj.meituan.com/category/all/pinggu", "平谷区");
        sites.put("http://bj.meituan.com/category/all/yanqingxian", "延庆县");
        sites.put("http://bj.meituan.com/category/all/mentougou", "门头沟区");
        sites.put("http://bj.meituan.com/category/all/chaoyangqu", "朝阳区");
       for(String site:sites.keySet()) {
            System.out.println(site);
      //  System.out.println(args[0]);
        try{
            Spider.create(new MeituanProcessor()).addUrl(site).addPipeline(new MysqlShopPipeline()).thread(5).run();
    }catch (Exception e){
        e.printStackTrace();
    }
        }

       /* Collection<String> urls = qiushibaikeProcessor.urls.values();
        long file_sub = System.currentTimeMillis();
        System.out.println(urls.size());
        File mp4s = new File(Constant.getCrawlerQiushiJobPath() + file_sub + ".txt");
        try {
            FileUtils.writeLines(mp4s, urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jobname = "Qiushijob" + file_sub;

        System.out.println(jobname);
        ExecuteShellComand.executeQfetch(Constant.getCrawlerQiushiJobPath(), mp4s, jobname);
        //成功上传的条数
        int count = ExecuteShellComand.executeLeveldb(Constant.getCrawlerQiushiJobPath(), jobname);

        //是否全部上传成功,重试
        System.out.println(urls.size() + "," + count);

        MysqlQiushibaikePipeline mpl = new MysqlQiushibaikePipeline();
        mpl.update(qiushibaikeProcessor.urls.keySet(), FROM_URL);
*/

        //update mp4
        //http://7xrgsk.com1.z0.glb.clouddn.com/2016-03-03_56d8104627fdc.mp4?vframe/jpg/offset/100/w/480/h/264
        //时长:http://7xrgsk.com1.z0.glb.clouddn.com/2016-03-03_56d8104627fdc.mp4?avinfo
    }
}