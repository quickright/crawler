package cn.xiaomei.crawler.site;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.db.DPEntriPipeline;
import cn.xiaomei.crawler.db.bean.CityUrl;
import cn.xiaomei.crawler.db.bean.DianpingEnterBean;
import cn.xiaomei.crawler.db.bean.ProxyBean;
import cn.xiaomei.crawler.proxy.Peuland;
import cn.xiaomei.crawler.proxy.XmHttpClientDownloader;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.MultiPagePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.RedisScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import javax.management.JMException;
import java.io.IOException;
import java.util.*;

/**
 * Created by chunli on 16/3/3.
 */
public class DianpingProcessor implements PageProcessor {
    private static  List<ProxyBean> proxyBeens=new Peuland().randomProxyBean();
    public String cityen;
    public String citycn;


    public static final String SITE_URL = "http://www.dianping.com/search/category/\\d+/\\d+";
    public static final String URL_LIST = "http://www.dianping.com/search/category/\\d+/\\d+/\\w+";
    public static final String DOMAIN = "http://www.dianping.com/";
    public static final String POI_URL = "http://www.dianping.com/shop/\\d+";
     public static final Map<String, String> urls = new HashMap<String, String>();

    public DianpingProcessor(String cityen, String citycn) {
        this.cityen = cityen;
        this.citycn = citycn;
    }

    private Site site = Site
            .me()
            .setDomain("www.dianping.com")
            //.setRetryTimes(3)
            .setSleepTime(60 * 1000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {

        String thisurl = page.getUrl().get();
        System.out.println(thisurl);
        if (page.getUrl().regex(URL_LIST).match()) {
            List<String> ids = page.getHtml().xpath("//div[@id=\"shop-all-list\"]/ul/li/div[@class=\"pic\"]/a/@href").all();


            page.addTargetRequests(ids);
            String nextpage = page.getHtml().xpath("//a[@class=\"next\"]/@href").get();
            page.addTargetRequest(nextpage);

        } else if (page.getUrl().regex(SITE_URL).match()) {


            List<Selectable> regions = page.getHtml().xpath("//div[@id=\"region-nav\"]/a").nodes();
            for (int i = 0; i < regions.size(); i++) {
                String regionurl = regions.get(i).xpath("//a/@href").get();
                String regionname = regions.get(i).xpath("//a/span/text()").get();
                //System.out.println(i + "," + regionname + "," + regionurl.split("#")[0]);
                page.addTargetRequest(regionurl.split("#")[0]);
            }
        } else if (page.getUrl().regex(POI_URL).match()) {
            processorDetailPage(page);
        }

    }

    public Site getSite()
    {

         List<String[]> httproxy = new ArrayList<String[]>();
       /*  httproxy.add(new String[]{"180.250.74.3", "8080"});
        httproxy.add(new String[]{"103.244.186.94", "8080"});
        httproxy.add(new String[]{"190.29.22.247", "8080"});
        httproxy.add(new String[]{"203.189.130.125", "8080"});*/

        for(ProxyBean proxyBeen:proxyBeens) {
            System.out.println(proxyBeen.getIp()+":"+ proxyBeen.getPort());
            httproxy.add(new String[]{proxyBeen.getIp(), proxyBeen.getPort()});
        }
        this.site.setRetryTimes(2)
                .setCycleRetryTimes(30)
                .setHttpProxyPool(httproxy)
                 .enableHttpProxyPool()
                .setProxyReuseInterval(10);
        return site;
    }

    public static void main(String[] args) throws IOException, JMException {
        XmHttpClientDownloader downloader=new XmHttpClientDownloader();
        downloader.setThread(5);
       // List<CityUrl> cityUrls = Constant.getDianpingCityUrls();
        /*for (CityUrl site : cityUrls) {
            DianpingProcessor ddProcessor = new DianpingProcessor(site.getCityen(), site.getCitycn());
            if (site.getIsused()) {*/



        DianpingProcessor ddProcessor = new DianpingProcessor("f", "d");
                Spider spider = Spider.create(ddProcessor)
                        //.addUrl("http://www.dianping.com/shop/21254649")
                         .addUrl("http://www.dianping.com/search/category/2/10")
                           .setDownloader(downloader)
                          .scheduler(new RedisScheduler("127.0.0.1"))
                         .addPipeline(new DPEntriPipeline())
                       .thread(1);

                SpiderMonitor.instance().register(spider);
       // MultiPagePipeline a;
                spider.start();


        /*    }
        }*/

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

    public String getCityen() {
        return cityen;
    }

    public void setCityen(String cityen) {
        this.cityen = cityen;
    }

    public String getCitycn() {
        return citycn;
    }

    public void setCitycn(String citycn) {
        this.citycn = citycn;
    }

    public List<ProxyBean> randomProxyBean(){
        Peuland peuland=new Peuland();
        List<ProxyBean> proxyBeanList= peuland.randomProxyBean();
        return proxyBeanList;
    }


    private void processorDetailPage(Page page){
        Html html = page.getHtml();
        String thisurl=page.getUrl().get();
        String shopname = html.xpath("//h1[@class=\"shop-name\"]/text()").get();
        String vshop = html.xpath("//a[@class=\"icon v-shop\"]").get();
        vshop = (vshop == null ? "0" : "1");
        List<String> brief = html.xpath("//div[@class=\"brief-info\"]/span[@class=\"item\"]/text()").all();
        String comment = brief.size() > 0 ? brief.get(0).replaceAll("条评论", "") : "";
        String percapita = brief.size() > 1 ? brief.get(1).replaceAll("人均：", "").replaceAll("元", "") : "";
        String taste = brief.size() > 2 ? brief.get(2).replaceAll("口味：", "") : "";
        String environment = brief.size() > 3 ? brief.get(3).replaceAll("环境：", "") : "";
        String service = brief.size() > 4 ? brief.get(4).replaceAll("服务：", "") : "";
        String region = html.xpath("//span[@itemprop=\"locality region\"]/text()").get();

        String street_address = html.xpath("//span[@itemprop=\"street-address\"]/text()").get();
        String tel = html.xpath("//span[@itemprop=\"tel\"]/text()").get();
        String businessHours = html.xpath("//p[@class=\"info info-indent\"]/span[@class=\"item\"]/text()").get();
        List<String> consumertag = html.xpath("//span[@class=\"good J-summary\"]/a/@date-type").replace("_1", "").all();
        List<String> breadcrumb = html.xpath("//div[@class=\"breadcrumb\"]/a/text()").all();
        String area = "";
        String category = "";
        if (breadcrumb.size() > 3) {
            area = breadcrumb.get(2);
            category = breadcrumb.get(3);
        } else if (breadcrumb.size() == 3) {
            area = breadcrumb.get(1);
            category = breadcrumb.get(2);
        }
        List<String> tags = html.xpath("//p[@class=\"info info-indent\"]/span[@class=\"item\"]/a/text()").all();

        String[] ars = thisurl.split("/");
        String id = ars.length > 0 ? ars[ars.length - 1] : "";
        List<String> photosurl = null;

        String phototxt = html.xpath("//div[@id=\"shop-tabs\"]/script").replace("<script type=\"text/panel\" class=\"J-panels\">", "").replace("</script>", "").get();
        if (phototxt != null) {
            Html photohtml = new Html(phototxt);
            photosurl = photohtml.xpath("//a[@class=\"item\"][@rel=\"nofollow\"]/@href").all();
        }

        DianpingEnterBean deb = new DianpingEnterBean(id, shopname, vshop);
        deb.setAddDate(Constant.getCurrentDateTime());
        deb.setBusinessHours(businessHours);
        deb.setComment(comment);
        deb.setConsumertag(tags);
        deb.setEnvironment(environment);
        deb.setModifyDate(Constant.getCurrentDateTime());
        deb.setPercapita(percapita);
        deb.setPhotourls(photosurl);
        deb.setStreet_address(region + street_address);
        deb.setTags(tags);
        deb.setTaste(taste);
        deb.setTel(tel);
        deb.setService(service);

        deb.setArea(area);
        deb.setCity(this.citycn);
        deb.setCategory(category);
        deb.setConsumertag(consumertag);
        page.putField(deb.getId(), deb);
    }
}