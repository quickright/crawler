package cn.xiaomei.crawler.site;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.db.DPEntriPipeline;
import cn.xiaomei.crawler.db.bean.CityUrl;
import cn.xiaomei.crawler.db.bean.DpCityUrl;
import cn.xiaomei.crawler.db.bean.ProxyBean;
import cn.xiaomei.crawler.proxy.Peuland;
import cn.xiaomei.crawler.proxy.XmHttpClientDownloader;
import cn.xiaomei.crawler.webmagic.XmSpider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chunli on 16/3/3.
 */
public class DianpingJsonProcessor implements PageProcessor {

    //private static  List<ProxyBean> proxyBeens=new Peuland().randomProxyBean();
    private CityUrl cityurl;
    public String id;



    public String time;

    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }

    public static final String MSITE_URL = "http://m.api.dianping.com/\\w+";
    public static final String MSITE_URL_PRE="http://m.api.dianping.com/searchshop.json?range=-1&sortid=0&cityid=&_=1460185031043&callback=jsonp1&start=";
    public static final String URL_LIST = "http://www.dianping.com/search/category/\\d+/\\d+/\\w+";
    public static final String POI_URL = "http://www.dianping.com/shop/\\d+";
    public static final String POI_URL_PRE = "http://www.dianping.com/shop/";
    public static final Map<String, String> urls = new HashMap<String, String>();

    private Site site = Site
            .me()
            .setDomain("www.dianping.com")
            .setRetryTimes(300000).setRetrySleepTime(5*60*1000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {
        String thisurl = page.getUrl().get();
        System.out.println(thisurl);
        if (page.getUrl().regex(MSITE_URL).match()) {
            String rawtext = page.getRawText();
            /*int findex = rawtext.indexOf("(");
            int lindex = rawtext.lastIndexOf(")");
            String jsontext = rawtext.substring(findex + 1, lindex);*/
            Json json = new Json(rawtext);
            List<Selectable> ents = json.jsonPath("list").nodes();
            List<String> ids = new ArrayList<String>();
            List<String> idsurl = new ArrayList<String>();
            for (Selectable ent : ents) {

                Json j = new Json(ent.get());
                ids.add(j.jsonPath("id").get());
                idsurl.add(POI_URL_PRE + j.jsonPath("id").get());
                page.putField(j.jsonPath("id").get(), j);
            }
            String startindex = json.jsonPath("nextStartIndex").get();//
            page.addTargetRequest(this.cityurl.getUrl()+"&start="+Integer.parseInt(startindex)+"&_="+time);
                    //("http://m.api.dianping.com/searchshop.json?start="+ Integer.parseInt(startindex)+"&range=-1&sortid=0&locatecityid="+cityId+"&cityid="+cityId+"&callback=jsonp1&_=" + time);
        }
            //page.putField("page", json);
            //page.putField(, json);
    }

    public Site getSite()
    {
        Peuland peuland=new Peuland();
          /*  try {
                peuland.saveProxy();
            } catch (IOException e) {

            }*/
        List<ProxyBean> proxyBeens=peuland.randomProxyBean();
        List<String[]> httproxy = new ArrayList<String[]>();
        for(ProxyBean proxyBeen:proxyBeens) {
            httproxy.add(new String[]{proxyBeen.getIp(), proxyBeen.getPort()});
        }
        this.site.enableHttpProxyPool()
                .setRetryTimes(2)
                .setCycleRetryTimes(30)
                .setHttpProxyPool(httproxy).enableHttpProxyPool()
                .setProxyReuseInterval(10);
        return site;
    }

    public static void main(String[] args) {
        XmHttpClientDownloader xmHttpClientDownloader=new XmHttpClientDownloader();
        xmHttpClientDownloader.setTimes(1000);
        xmHttpClientDownloader.setThread(5);
        Map<String,CityUrl> citys=new HashMap<String, CityUrl>();
        long time=System.currentTimeMillis();
        /*for(int i=1;i<7400;i++) {
            String url = "http://m.api.dianping.com/searchshop.json?start=" + (i - 1) * 25 + 1 + "&range=-1&categoryid=10&sortid=0&locatecityid=2&cityid=2&_=" + time + "&callback=jsonp" + i;
        }*/
        List<DpCityUrl> cityUrls = Constant.getDianpingCityUrls();
        for (DpCityUrl site : cityUrls) {
            if(args!=null && site.getCity_id().equalsIgnoreCase(args[0])) {
             /*   citys.put(site.getCitycn());
            }*/
                //Integer.valueOf(args[0])
                //String url = "http://m.api.dianping.com/searchshop.json?start=" +start  + "&range=-1&categoryid=10&sortid=0&locatecityid=2&cityid=2&_=" + time + "&callback=jsonp" + i;
                //.replaceAll("http://m.api.dianping.com/searchshop.json?start=1&range=-1&sortid=0&locatecityid=1&cityid=","").replaceAll("&callback=jsonp1&_=","");
                //String url = "http://m.api.dianping.com/searchshop.json?start=1&range=-1&sortid=0&locatecityid=" + cityid + "&cityid=" + cityid + "&callback=jsonp1&_=" + time;

                DianpingJsonProcessor ddProcessor = new DianpingJsonProcessor();
                ddProcessor.setTime(time + "");
                ddProcessor.setCityurl(site);
                if (site.getIsused()) {
                    XmSpider.create(ddProcessor).addUrl(site.getUrl() + "&start=" + 1 + "&_=" + time).setDownloader(xmHttpClientDownloader)
                            .addPipeline(new DPEntriPipeline()).thread(6)
                            .run();
                }
            }
        }

        System.out.println(System.currentTimeMillis());

    }


    public CityUrl getCityurl() {
        return cityurl;
    }

    public void setCityurl(CityUrl cityurl) {
        this.cityurl = cityurl;
    }
}