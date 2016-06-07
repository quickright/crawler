package cn.xiaomei.crawler.site;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.db.MysqlShopPipeline;
import cn.xiaomei.crawler.db.bean.Shop;
import cn.xiaomei.crawler.utils.HttpClientUtils;
import cn.xiaomei.crawler.utils.JsonUtils;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONArray;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;

/**
 * Created by chunli on 16/3/3.
 */
public class NuomiProcessor implements PageProcessor {

    public static final String URL_LIST = "http://bj\\.nuomi\\.com/all/\\d+";
    public static final String PAGE_LIST = "http://bj.nuomi.com/all/307-page\\d+?#j-sort-bar";

    public static final String DETAIL_LIST = "http://www.nuomi.com/pcindex/main/shopchain?dealId=";




    public static final String FROM_URL = "http://bj.nuomi.com/all/307";
    public static final Map<String, String> urls = new HashMap<String, String>();


    private Site site = Site
            .me()
            .setDomain(FROM_URL)
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {
        System.out.println(page.getUrl().get());

        if(page.getUrl().regex(URL_LIST).match()||page.getUrl().regex(PAGE_LIST).match()) {
          List<Selectable> jrlist = page.getHtml().xpath("//ul[@class=\"itemlist clearfix\"]/li/div").nodes();
          for (Selectable jr : jrlist) {

              /*System.out.println(jr.xpath("//h4[@class=\"title\"]/text()").get());
              System.out.println(jr.xpath("//a/@href").get());
              System.out.println(jr.xpath("//a/@data-topten").get().substring(2));*/
              String price=jr.xpath("//span[@class=\"price\"]/text()").get();
              String comment=jr.xpath("//span[@class=\"comment\"]/text()").get();
              if(comment!=null && comment.length()>3)
              {
                  comment=comment.substring(0, 3);
              }
              String sold = jr.xpath("//span[@class=\"sold\"]/text()").get();
              if(sold!=null && sold.length()>2)
              {
                  sold=sold.substring(2);
              }
              //page.addTargetRequest(jr.xpath("//a/@href").get());
              String pid=jr.xpath("//a/@data-topten").get();
              if(pid!=null && pid.length()>2)
              {
                  pid=pid.substring(2);
              }
              String url=DETAIL_LIST+pid;

              //System.out.println(url);

              String json=HttpClientUtils.response(url);

              JSONArray jarr=JsonUtils.getPathArray(json,"data","shop");
              for (int i = 0; i < jarr.length(); i++) {
                    Shop shop =new Shop();
                  shop.setPrice(price);
                  shop.setComment(comment);
                  shop.setSold(sold);
                  shop.setChannel(Constant.ChannelNuoMi);
                  shop.setShop_id(jarr.getJSONObject(i).getString("merchant_id"));
                  shop.setName(jarr.getJSONObject(i).getString("name"));
                  shop.setAddress(jarr.getJSONObject(i).getString("address"));
                  shop.setPhone(jarr.getJSONObject(i).getString("phone"));
                  shop.setLongitude(jarr.getJSONObject(i).get("baidu_longitude")+"");
                  shop.setLatitude(jarr.getJSONObject(i).get("baidu_latitude")+"");
                  shop.setCity_id(jarr.getJSONObject(i).getString("city_id"));
                  shop.setDistrict_id(jarr.getJSONObject(i).getString("district_id"));
                  shop.setLink(jarr.getJSONObject(i).getString("link"));
                  page.putField(shop.getShop_id(),shop);
              }
          }

            List<Selectable> jrs = page.getHtml().xpath("//div[@class=\"ui-pager\"]//a").nodes();

            for (Selectable jr : jrs) {
                page.addTargetRequest(jr.xpath("//a/@href").get());
            }
          //page.putField("", budejies);
      }/*else if(page.getUrl().regex(DETAIL_LIST_1).match()||page.getUrl().regex(DETAIL_LIST_2).match()){

            List<Selectable> jrlist = page.getHtml().xpath("//ul[@class=\"branch-list-content\"]").nodes();

            System.out.println(page.getHtml());
            for (Selectable jr : jrlist) {
                System.out.println(jr.xpath("//a/text()").get());
                System.out.println(jr.xpath("//a/@href").get());
                System.out.println(jr.xpath("//div[@class=\"p-item-info\"]/@mon").get());
                System.out.println(jr.xpath("//span[@class=\"branch-address\"]/text()").get());
                System.out.println(jr.xpath("//span[@class=\"branch-tel\"]/text()").get());
                System.out.println(jr.xpath("//span[@class=\"map-travel clearfix\"]/@data-longitude").get());
                System.out.println(jr.xpath("//span[@class=\"map-travel clearfix\"]/@data-latitude").get());

                //page.addTargetRequest(jr.xpath("//a/@href").get());

            }
        }*/

        /*if (budejies.size() == 0) {
            page.setSkip(true);
        }*/

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        NuomiProcessor qiushibaikeProcessor = new NuomiProcessor();
        Map<String, String> sites = new HashedMap();

        sites.put("http://bj.nuomi.com/all/406", "昌平区");
        sites.put("http://bj.nuomi.com/all/395", "丰台区");
       /* sites.put("http://bj.nuomi.com/all/396", "西城区");
        sites.put("http://bj.nuomi.com/all/394", "东城区");*/
        sites.put("http://bj.nuomi.com/all/409", "大兴区");
        sites.put("http://bj.nuomi.com/all/408", "通州区");
        sites.put("http://bj.nuomi.com/all/6544", "房山区");
        //sites.put("http://bj.nuomi.com/all/6545", "顺义区");
        sites.put("http://bj.nuomi.com/all/410", "石景山区");
       /* sites.put("http://bj.nuomi.com/all/6546", "怀柔区");
        sites.put("http://bj.nuomi.com/all/6548", "密云县");
        sites.put("http://bj.nuomi.com/all/6547", "平谷区");*/
       // sites.put("http://bj.nuomi.com/all/6549", "延庆县");
        sites.put("http://bj.nuomi.com/all/5985", "门头沟区");
        /*sites.put("http://bj.nuomi.com/all/392", "海淀区");
        sites.put("http://bj.nuomi.com/all/307", "朝阳区");*/
        for(String site:sites.keySet()) {
            System.out.println(site);
        try {
            Spider.create(qiushibaikeProcessor).addUrl(site).addPipeline(new MysqlShopPipeline()).thread(6)
                    .run();
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