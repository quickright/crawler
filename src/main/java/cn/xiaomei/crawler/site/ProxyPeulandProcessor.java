package cn.xiaomei.crawler.site;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.db.DPEntriPipeline;
import cn.xiaomei.crawler.db.bean.CityUrl;
import cn.xiaomei.crawler.db.bean.DianpingEnterBean;
import cn.xiaomei.crawler.db.bean.PageProgress;
import cn.xiaomei.crawler.utils.Base64;
import org.apache.http.HttpHost;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chunli on 16/3/3.
 */
public class ProxyPeulandProcessor implements PageProcessor {
    public String domain;



    public ProxyPeulandProcessor(String domain){
        this.domain=domain;
    }
    private Site site = Site
            .me()
            .setRetryTimes(3).setRetrySleepTime(5*60*1000)
            .setDomain("proxy.peuland.com")
            .addCookie("CNZZDATA1253154494","248704758-1460453175-https%253A%252F%252Fflfq.peuland.com%252F%7C1460637549","proxy.peuland.com")
            .addCookie("Hm_lpvt_b456f622e75aee550ddacdc281571fcd","1460642310",".proxy.peuland.com")
            .addCookie("Hm_lvt_b456f622e75aee550ddacdc281571fcd","1460453181,1460541107,1460606322,1460607462",".proxy.peuland.com")
            .addCookie("PHPSESSID","pkjimjg9a1197ikphvsh5avad4","proxy.peuland.com")
            .addCookie("peuland_id","35fefe23fedc52da9283ac5ed131cbab","proxy.peuland.com")
            .addCookie("peuland_md5","ca1f57155f5638ade3c28a900fbdbd55","proxy.peuland.com")
            .addCookie("user_nickname","18618324090%40139.com","proxy.peuland.com")
            .addCookie("w_a_h","1080","proxy.peuland.com")
            .addCookie("w_a_w","1920","proxy.peuland.com")
            .addCookie("w_cd","24","proxy.peuland.com")
            .addCookie("w_h","1080","proxy.peuland.com")
            .addCookie("w_w","1920","proxy.peuland.com")
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {
        String thisurl = page.getUrl().get();
        System.out.println(thisurl);
        System.out.println(page.getHtml().get());
        List<Selectable> trs= page.getHtml().$("tbody.datalist").$("tr").nodes();
        for (Selectable sel:trs
             ) {
            System.out.println(sel.get());
        }
                 //    page.addTargetRequests(idsurl);



    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        System.out.println(Base64.decode("MTIyLjIyNy4xNzEuMjI=", Base64.NO_PADDING).toString());
        System.out.println(ProxyPeulandProcessor.class.getResource("/jssecacerts").getPath());
        String s = new String(Base64.decode("MTIyLjIyNy4xNzEuMjI=", Base64.NO_PADDING), StandardCharsets.UTF_8);
        System.out.println(s);
        System.setProperty("javax.net.ssl.trustStore",ProxyPeulandProcessor.class.getResource("/jssecacerts").getPath());
        //https://proxy.peuland.com/proxy/search_proxy.php
//{"pagination":{"maxpage":13,"current_page":1},"data":[{"ip":"MTIyLjIyNy4xNzEuMjI=","port":"OTc5Nw==","type":"SFRUUA==","is_clusters":"WUVT","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MjQ=","time_downloadspeed":"MA==","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dW5jaGVjaw==","status_ctn":"dXA="},{"ip":"MTI0LjIwNi4yMzYuMTcw","port":"MzEyOA==","type":"SFRUUA==","is_clusters":"Tk8=","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MA==","time_downloadspeed":"MA==","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"ZG93bg=="},{"ip":"NjAuMTkxLjE3MC4xMjI=","port":"MzEyOA==","type":"SFRUUA==","is_clusters":"WUVT","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MTQ=","time_downloadspeed":"MQ==","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"dW5jaGVjaw=="},{"ip":"NjAuMjEuMjA2LjE2NQ==","port":"OTk5OQ==","type":"SFRUUA==","is_clusters":"WUVT","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"Mw==","time_downloadspeed":"Mw==","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"dW5jaGVjaw=="},{"ip":"MTExLjIwNi44MS4yNDg=","port":"ODA=","type":"SFRUUA==","is_clusters":"Tk8=","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MA==","time_downloadspeed":"MA==","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"ZG93bg=="},{"ip":"MTIxLjQwLjEwOC43Ng==","port":"ODA=","type":"SFRUUA==","is_clusters":"WUVT","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MTA=","time_downloadspeed":"MQ==","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"dXA="},{"ip":"MTI0LjE5My4yNy4yNTM=","port":"MzEyOA==","type":"SFRUUA==","is_clusters":"Tk8=","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MA==","time_downloadspeed":"MA==","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"ZG93bg==","status_ctn":"dXA="},{"ip":"MTgzLjIzNy4xNi4yMTU=","port":"OTk5OQ==","type":"SFRUUA==","is_clusters":"Tk8=","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MA==","time_downloadspeed":"MA==","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"ZG93bg=="},{"ip":"MjAyLjEwMC4xNjcuMTQ5","port":"ODA=","type":"SFRUUA==","is_clusters":"WUVT","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MA==","time_downloadspeed":"MjY=","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"dW5jaGVjaw=="},{"ip":"MjIxLjIzMy4yNDIuMTQ1","port":"MzEyOA==","type":"SFRUUA==","is_clusters":"WUVT","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MQ==","time_downloadspeed":"MTQ=","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"dW5jaGVjaw=="},{"ip":"MTEyLjI1My4yLjYx","port":"ODA4MA==","type":"SFRUUA==","is_clusters":"WUVT","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MA==","time_downloadspeed":"NTk=","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"dXA="},{"ip":"MTgzLjYxLjIzNi41Mw==","port":"MzEyOA==","type":"SFRUUA==","is_clusters":"WUVT","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MA==","time_downloadspeed":"NzI=","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"dXA="},{"ip":"MTAxLjY2LjI1My4yMg==","port":"ODA4MA==","type":"SFRUUA==","is_clusters":"WUVT","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MA==","time_downloadspeed":"MTUy","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"dXA="},{"ip":"MTExLjc1LjE3Ny4xOTU=","port":"MzEyOA==","type":"SFRUUA==","is_clusters":"Tk8=","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MA==","time_downloadspeed":"MA==","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"ZG93bg==","status_ctn":"dXA="},{"ip":"MTIxLjY5LjIyLjY=","port":"ODExOA==","type":"SFRUUA==","is_clusters":"WUVT","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MTA=","time_downloadspeed":"MQ==","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"ZG93bg==","status_ctn":"dXA="},{"ip":"MTU3LjEyMi4xMTUuMjEy","port":"ODAwMw==","type":"SFRUUA==","is_clusters":"WUVT","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MTY=","time_downloadspeed":"MQ==","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"dXA="},{"ip":"MTEyLjc0LjIwNy4xOTY=","port":"MzEyOA==","type":"SFRUUA==","is_clusters":"WUVT","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"OA==","time_downloadspeed":"MQ==","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"dXA="},{"ip":"MTE3LjE4NC4xMTcuMjI5","port":"MzEyOA==","type":"SFRUUA==","is_clusters":"WUVT","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MA==","time_downloadspeed":"MzU=","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"dXA="},{"ip":"MTI0LjIwNi4xMzMuMjI3","port":"ODA=","type":"SFRUUA==","is_clusters":"Tk8=","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MA==","time_downloadspeed":"MA==","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"ZG93bg=="},{"ip":"MjE4LjI2LjEyMC4xNzA=","port":"ODA4MA==","type":"SFRUUA==","is_clusters":"WUVT","is_https":"Tk8=","level_type":"YW5vbnltb3Vz","time_total":"MA==","time_downloadspeed":"NTA=","country_code":"Q04=","country":"Q0hJTkE=","country_zw":"5Lit5Zu9","status_cnc":"dXA=","status_ctn":"dW5jaGVjaw=="}],"message":true}
               ProxyPeulandProcessor ddProcessor = new ProxyPeulandProcessor("proxy.peuland.com");
                    Spider.create(ddProcessor).addUrl("https://proxy.peuland.com/proxy/search_proxy.php")//.addUrl("https://proxy.peuland.com/proxy/search_proxy.php").addUrl("https://proxy.peuland.com/proxy/search_proxy.php?type=HTTP&country_code=CN&is_clusters=&is_https=NO&level_type=&search_type=export&limit=50")//(String[]) urls.toArray(new String[0]))
                            //.addPipeline(new DPEntriPipeline()).thread(6)
                            .run();
    }


}