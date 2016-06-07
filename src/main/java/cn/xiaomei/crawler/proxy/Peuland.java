package cn.xiaomei.crawler.proxy;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.db.bean.ProxyBean;
import cn.xiaomei.crawler.utils.Base64;
import cn.xiaomei.crawler.utils.HttpClientUtils;
import cn.xiaomei.crawler.utils.JsonUtils;
import cn.xiaomei.crawler.utils.Test;
import com.mongodb.client.FindIterable;
import org.apache.commons.io.FileUtils;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chunli on 16/4/15.
 */
public class Peuland extends BaseProxy {
    private String domain="proxy.peuland.com";
    public List<ProxyBean> getProxy() {
        return null;
    }

    public static void main(String [] args)   {
        //http://www.samair.ru/proxy-by-country/China-03.htm
        Peuland peuland=new Peuland();
        List<ProxyBean> ss=peuland.getProxy();
        ss.size();
        HttpPost httppost = new HttpPost("http://www.samair.ru/proxy-by-country/China-03.htm");

        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpResponse responsea = null;
        try {
            responsea = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int statusCode = responsea.getStatusLine().getStatusCode();

        // 获取响应消息实体
        HttpEntity entity = responsea.getEntity();
        try {
            String responseString = EntityUtils.toString(entity);
            System.out.println(responseString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  System.out.println(peuland.getRandomProxy().toString());
       /* try {
            peuland.saveProxy(10);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*try {
            peuland.saveProxy();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void removeProxy(String ip,String port)
    {
        super.removeProxy(domain,ip,port);
    }
    @Override
    public void updateProxy(ProxyBean proxyBean) {
        proxyBean.setTimes(proxyBean.getTimes()+1);
        super.updateProxy(proxyBean);
    }

    public List<ProxyBean> randomProxyBean(){
        FindIterable<Document> iterableproxybeans=this.getProxy(10000);
        List<ProxyBean> proxybeans=new ArrayList<ProxyBean>();
        for(Document document:iterableproxybeans){
            ProxyBean proxybean=new ProxyBean();
            proxybean.setAddtime(document.getString("addtime"));
            proxybean.setModifytime(document.getString("modifytime"));
            proxybean.setDomain(document.getString("domain"));
            //proxybean.setIsabled(document.getBoolean("isabled"));
            proxybean.setTimes(document.getInteger("times"));
            proxybean.setPort(document.getString("port"));
            proxybean.setIp(document.getString("ip"));
            proxybean.setRandom(document.getString("random"));
            System.out.println(document);
            proxybeans.add(proxybean);
        }
        return proxybeans;
    }
    public void saveProxy(int page) throws IOException {
        /*HttpClient httpclient = HttpClientBuilder.create().build();
        HttpResponse responsea = httpclient.execute(proxyHead(page));
        int statusCode = responsea.getStatusLine().getStatusCode();*/

        // 获取响应消息实体
       // HttpEntity entity = responsea.getEntity();
        HttpEntity entity =proxyHead(page);
        // 判断响应实体是否为空
        if (entity != null) {

            String responseString = EntityUtils.toString(entity);
            System.out.println("response content:"
                    + responseString.replace("\r\n", ""));

            JSONArray jarray = JsonUtils.getPathArray(responseString.replace("\r\n", ""), "data");
            List<ProxyBean> proxyBeans=new ArrayList<ProxyBean>();
            for (int i = 0; i < jarray.length(); i++) {
                ProxyBean proxyBean=new ProxyBean();
                JSONObject jobject = jarray.getJSONObject(i);
                System.out.println("------------------------------------------------------------------------------------------------");
                /*String   =new String(Base64.decode(jobject.getString("country"),Base64.DEFAULT), StandardCharsets.UTF_8);
                String   =new String(Base64.decode(jobject.getString("is_https"),Base64.DEFAULT), StandardCharsets.UTF_8);*/
                String status_cnc = new String(Base64.decode(jobject.getString("status_cnc"), Base64.DEFAULT), StandardCharsets.UTF_8);
                String host = new String(Base64.decode(jobject.getString("ip"), Base64.DEFAULT), StandardCharsets.UTF_8);
                String port = new String(Base64.decode(jobject.getString("port"), Base64.DEFAULT), StandardCharsets.UTF_8);
                System.out.println(host + ":" + port);
                String is_https = new String(Base64.decode(jobject.getString("is_https"), Base64.DEFAULT), StandardCharsets.UTF_8);
                String type = new String(Base64.decode(jobject.getString("type"), Base64.DEFAULT), StandardCharsets.UTF_8);
                String level_type = new String(Base64.decode(jobject.getString("level_type"), Base64.DEFAULT), StandardCharsets.UTF_8);
                String country_code = new String(Base64.decode(jobject.getString("country_code"), Base64.DEFAULT), StandardCharsets.UTF_8);
                String time_downloadspeed = new String(Base64.decode(jobject.getString("time_downloadspeed"), Base64.DEFAULT), StandardCharsets.UTF_8);
                String time_total = new String(Base64.decode(jobject.getString("time_total"), Base64.DEFAULT), StandardCharsets.UTF_8);
                String is_clusters = new String(Base64.decode(jobject.getString("is_clusters"), Base64.DEFAULT), StandardCharsets.UTF_8);
                String country_zw = new String(Base64.decode(jobject.getString("country_zw"), Base64.DEFAULT), StandardCharsets.UTF_8);
                String status_ctn = new String(Base64.decode(jobject.getString("status_ctn"), Base64.DEFAULT), StandardCharsets.UTF_8);
                proxyBean.setDomain(domain);
                proxyBean.setAddtime(Constant.getCurrentDateTime());
                proxyBean.setModifytime(Constant.getCurrentDateTime());
                proxyBean.setIp(host);
                proxyBean.setIsabled(true);
                proxyBean.setPort(port);

                System.out.println(status_cnc + "|" + host + "|" + port + "|" + is_https + "|" + type + "|" + level_type + "|" + country_code + "|" + time_downloadspeed + "|" + time_total + "|" + is_clusters + "|" + country_zw + "|" + status_ctn);
                try {
                    boolean isConnect=HttpClientUtils.isConnect(host, Integer.parseInt(port));
                    if(isConnect)
                    {
                        proxyBeans.add(proxyBean);
                    }
                } catch (Exception e) {

                }
            }
            this.saveProxy(proxyBeans);
        }


    }

    private HttpEntity proxyHead(int page) throws UnsupportedEncodingException {
        if(new File("/data03/apps/crawler/jssecacerts").exists()) {
            System.setProperty("javax.net.ssl.trustStore", "/data03/apps/crawler/jssecacerts");//Test.class.getResource("/jssecacerts").getPath());

        }else {
            System.setProperty("javax.net.ssl.trustStore", Test.class.getResource("/jssecacerts").getPath());
        }
        //CookieStore cookieStore = new BasicCookieStore();
        //HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httppost = new HttpPost("https://proxy.peuland.com/proxy/search_proxy.php");
        httppost.setHeader("Host", "proxy.peuland.com");
        httppost.setHeader("Origin", "https://proxy.peuland.com");
        httppost.setHeader("Referer", "https://proxy.peuland.com/proxy_list_by_category.htm");
        httppost.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2692.0 Safari/537.36");
        httppost.setHeader("X-Requested-With", "XMLHttpRequest");
        //httppost.setHeader("Cookie","peuland_id=35fefe23fedc52da9283ac5ed131cbab;PHPSESSID=pkjimjg9a1197ikphvsh5avad4;peuland_md5=ca1f57155f5638ade3c28a900fbdbd55;w_h=1080;w_w=1920;w_a_h=1080;w_a_w=1920;php_id=2117079372");
        httppost.setHeader("Cookie", "peuland_id=35fefe23fedc52da9283ac5ed131cbab;PHPSESSID=pkm7b65es5ojb8oerc7a9i0q31; peuland_md5=ca1f57155f5638ade3c28a900fbdbd55;w_h=800; w_w=1280; w_cd=24; w_a_h=773; w_a_w=1280; php_id=1792520643");
        //    Cookie: peuland_id=35fefe23fedc52da9283ac5ed131cbab;PHPSESSID=pkm7b65es5ojb8oerc7a9i0q31; peuland_md5=ca1f57155f5638ade3c28a900fbdbd55;w_h=800; w_w=1280; w_cd=24; w_a_h=773; w_a_w=1280; php_id=1792520643

        //httppost.setHeader("Cookie","peuland_id=35fefe23fedc52da9283ac5ed131cbab;PHPSESSID=aljm7eg8t7pt64n11koani1p70; peuland_md5=ca1f57155f5638ade3c28a900fbdbd55;w_h=800; w_w=1280; w_cd=24; w_a_h=773; w_a_w=1280; php_id=1792520643");
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("type", "HTTP"));
        param.add(new BasicNameValuePair("country_code", "CN"));
        param.add(new BasicNameValuePair("is_clusters", ""));
        param.add(new BasicNameValuePair("is_https", "NO"));
        param.add(new BasicNameValuePair("level_type", "anonymous"));
        param.add(new BasicNameValuePair("search_type", "all"));
        param.add(new BasicNameValuePair("page", page+""));
        UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(param, "UTF-8");
        httppost.setEntity(postEntity);
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpResponse responsea = null;
        try {
            responsea = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int statusCode = responsea.getStatusLine().getStatusCode();

        // 获取响应消息实体
        HttpEntity entity = responsea.getEntity();
        return entity;
    }
    public void saveProxy() throws IOException {
       // System.out.println(Test.class.getResource("/jssecacerts").getPath());


        HttpEntity entity= proxyHead(1);
        // 获取响应消息实体

        // 判断响应实体是否为空
        if (entity != null) {

            String responseString = EntityUtils.toString(entity);
            System.out.println("response content:"
                    + responseString.replace("\r\n", ""));

            JSONArray jarray = JsonUtils.getPathArray(responseString.replace("\r\n", ""), "data");

            Object maxpage = JsonUtils.getPathObject(responseString.replace("\r\n", ""), "pagination");
            int pages=((JSONObject)maxpage).getInt("maxpage");
            for(int page=0;page<pages;page++){
                saveProxy(page);
            }

        }
    }
}
