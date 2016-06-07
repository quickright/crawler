package cn.xiaomei.crawler.utils;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BasicClientCookie2;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chunli on 16/3/3.
 */
public class Test {
    private BasicClientCookie cnzzdata1253154494;

    public static String readFile() {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void main(String [] args) throws IOException {
        readFile();
        System.setProperty("javax.net.ssl.trustStore",Test.class.getResource("/jssecacerts").getPath());
        CookieStore cookieStore = new BasicCookieStore();
      /*  cookieStore.addCookie(new BasicClientCookie("CNZZDATA1253154494","248704758-1460453175-https%253A%252F%252Fflfq.peuland.com%252F%7C1460637549"));
        cookieStore.addCookie(new BasicClientCookie("Hm_lpvt_b456f622e75aee550ddacdc281571fcd","1460648552"));
        cookieStore.addCookie(new BasicClientCookie("Hm_lvt_b456f622e75aee550ddacdc281571fcd","1460541107,1460606322,1460607462,1460644367"));
        cookieStore.addCookie(new BasicClientCookie("PHPSESSID","pkjimjg9a1197ikphvsh5avad4"));
        cookieStore.addCookie(new BasicClientCookie("peuland_id","35fefe23fedc52da9283ac5ed131cbab"));
        cookieStore.addCookie(new BasicClientCookie("peuland_md5","ca1f57155f5638ade3c28a900fbdbd55"));
        cookieStore.addCookie(new BasicClientCookie("user_nickname","18618324090%40139.com"));
        cookieStore.addCookie(new BasicClientCookie("w_a_h","1080"));
        cookieStore.addCookie(new BasicClientCookie("w_a_w","1920"));
        cookieStore.addCookie(new BasicClientCookie("w_cd","24"));
        cookieStore.addCookie(new BasicClientCookie("w_h","1080"));
        cookieStore.addCookie(new BasicClientCookie("w_w","1920"));*/

        HttpClient httpclient =HttpClientBuilder.create().build();
        HttpPost httppost = new HttpPost("https://proxy.peuland.com/proxy/search_proxy.php");
        httppost.setHeader("Host","proxy.peuland.com");
        httppost.setHeader("Origin","https://proxy.peuland.com");
        httppost.setHeader("Referer","https://proxy.peuland.com/proxy_list_by_category.htm");
        httppost.setHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2692.0 Safari/537.36");
        httppost.setHeader("X-Requested-With","XMLHttpRequest");
        //httppost.setHeader("Cookie","peuland_id=35fefe23fedc52da9283ac5ed131cbab;PHPSESSID=pkjimjg9a1197ikphvsh5avad4;peuland_md5=ca1f57155f5638ade3c28a900fbdbd55;w_h=1080;w_w=1920;w_a_h=1080;w_a_w=1920;php_id=2117079372");
         httppost.setHeader("Cookie","peuland_id=35fefe23fedc52da9283ac5ed131cbab;PHPSESSID=pkm7b65es5ojb8oerc7a9i0q31; peuland_md5=ca1f57155f5638ade3c28a900fbdbd55;w_h=800; w_w=1280; w_cd=24; w_a_h=773; w_a_w=1280; php_id=1792520643");
            //    Cookie: peuland_id=35fefe23fedc52da9283ac5ed131cbab;PHPSESSID=pkm7b65es5ojb8oerc7a9i0q31; peuland_md5=ca1f57155f5638ade3c28a900fbdbd55;w_h=800; w_w=1280; w_cd=24; w_a_h=773; w_a_w=1280; php_id=1792520643

        //httppost.setHeader("Cookie","peuland_id=35fefe23fedc52da9283ac5ed131cbab;PHPSESSID=aljm7eg8t7pt64n11koani1p70; peuland_md5=ca1f57155f5638ade3c28a900fbdbd55;w_h=800; w_w=1280; w_cd=24; w_a_h=773; w_a_w=1280; php_id=1792520643");
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("type","HTTP"));
        param.add(new BasicNameValuePair("country_code","CN"));
        param.add(new BasicNameValuePair("is_clusters",""));
        param.add(new BasicNameValuePair("is_https","NO"));
        param.add(new BasicNameValuePair("level_type","anonymous"));
        param.add(new BasicNameValuePair("search_type","all"));
        param.add(new BasicNameValuePair("page","3"));
        UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(param, "UTF-8");
        httppost.setEntity(postEntity);
        //BasicCookieStore cookie = new BasicCookieStore("JSESSIONID", "");
        //cookie.setDomain("your domain");
        //cookie.setPath("/");
        HttpResponse responsea = httpclient.execute(httppost);
        int statusCode=responsea.getStatusLine().getStatusCode();

        // 获取响应消息实体
        HttpEntity entity = responsea.getEntity();
        // 响应状态
        System.out.println("status:" + responsea.getStatusLine());
        System.out.println("headers:");
        HeaderIterator iterator = responsea.headerIterator();
        while (iterator.hasNext()) {
            System.out.println("\t" + iterator.next());
        }
        // 判断响应实体是否为空
        if (entity != null) {
            String responseString = EntityUtils.toString(entity);
            System.out.println("response length:" + responseString.length());
            System.out.println("response content:"
                    + responseString.replace("\r\n", ""));

            JSONArray jarray=JsonUtils.getPathArray(responseString.replace("\r\n", ""),"data");
            for(int i=0;i<jarray.length();i++){
                JSONObject jobject=jarray.getJSONObject(i);
                System.out.println("------------------------------------------------------------------------------------------------");
                /*String   =new String(Base64.decode(jobject.getString("country"),Base64.DEFAULT), StandardCharsets.UTF_8);
                String   =new String(Base64.decode(jobject.getString("is_https"),Base64.DEFAULT), StandardCharsets.UTF_8);*/
                String   status_cnc=new String(Base64.decode(jobject.getString("status_cnc"),Base64.DEFAULT), StandardCharsets.UTF_8);
                String host= new String(Base64.decode(jobject.getString("ip"),Base64.DEFAULT), StandardCharsets.UTF_8);
                String port=new String(Base64.decode(jobject.getString("port"),Base64.DEFAULT), StandardCharsets.UTF_8);
                System.out.println(host+":"+port);
                String   is_https=new String(Base64.decode(jobject.getString("is_https"),Base64.DEFAULT), StandardCharsets.UTF_8);
                String   type=new String(Base64.decode(jobject.getString("type"),Base64.DEFAULT), StandardCharsets.UTF_8);
                String   level_type=new String(Base64.decode(jobject.getString("level_type"),Base64.DEFAULT), StandardCharsets.UTF_8);
                String   country_code=new String(Base64.decode(jobject.getString("country_code"),Base64.DEFAULT), StandardCharsets.UTF_8);
                String   time_downloadspeed=new String(Base64.decode(jobject.getString("time_downloadspeed"),Base64.DEFAULT), StandardCharsets.UTF_8);
                String   time_total=new String(Base64.decode(jobject.getString("time_total"),Base64.DEFAULT), StandardCharsets.UTF_8);

                String   is_clusters=new String(Base64.decode(jobject.getString("is_clusters"),Base64.DEFAULT), StandardCharsets.UTF_8);
                String   country_zw=new String(Base64.decode(jobject.getString("country_zw"),Base64.DEFAULT), StandardCharsets.UTF_8);
                String   status_ctn=new String(Base64.decode(jobject.getString("status_ctn"),Base64.DEFAULT), StandardCharsets.UTF_8);
                System.out.println(status_cnc + "|" + host + "|" + port + "|" + is_https + "|" + type + "|" + level_type + "|" + country_code + "|" + time_downloadspeed + "|" + time_total + "|" + is_clusters + "|" + country_zw + "|" + status_ctn);
                try {
                    System.out.println(HttpClientUtils.isConnect(host, Integer.parseInt(port)));
                }catch (Exception e){

                }
            }

        }

        //int i=(int)(Math.random()*500)+100;
//int i= new java.util.Random().nextInt(900)+100;也可以
       // System.out.println(i);

       /* String url = "http://www.google.com/search?q=mkyong";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        String USER_AGENT = "Mozilla/5.0";

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());*/


     /*   HttpPost post = new HttpPost("api.qiniu.com");
        post.addHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("param-1", "12345"));
        params.add(new BasicNameValuePair("param-2", "Hello!"));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        StringEntity entity = new StringEntity("{\"key\":\"value\"}");
        post.setEntity(entity);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(post);

        // assert
        System.err.println("RESPONSE: " + response.toString());
        assertEquals(200, response.getStatusLine().getStatusCode());
        System.err.println("\n: " + Util.bytesToHex("\n".getBytes()));

        //api.qiniu.com
        //HttpPost post=
        System.out.println(UrlSafeBase64.encodeToString("7xrlok.com2.z0.glb.qiniucdn.com/video/2015/0807/55c3f6b21d8ef_wpd.mp4"));

       String urlpro="bucket=xiaomei&key=55c3f6b21d8ef_wpd.mp4&fops=avthumb/mp4/ss/20/t/5&force=1";

        String jobname="pic_job_";
        String file="/tmp/file.txt";
        String ak="_6oZxqstye3bijUyZTHg3dXXMmkzW0nyE6K37_Jo";
        String sk="xU38t53v3J7IUlFDJZ95rI08BTbqob8tRNpXwo83";
        String command="qfetch -ak='"+ak+"' -sk='"+sk+"' -bucket='xiaomei' -file='" + file + "' -worker=3 -job='" + jobname+"'";


        bucket=qiniu-ts-demo&key=sample.wav&fops=avthumb%2Fmp3%2Far%2F44100%2Faq%2F3&notifyURL=http%3A%2F%2Ffake.com%2Fqiniu%2Fnotify

        String token3 = auth.uploadToken("lovelivetest", "15a51e91-4850-48b3-a9ce-5174832d6605.amr" , 3600, new StringMap()
                .putNotEmpty("persistentOps", "avthumb/mp3/ab/16k|saveas/"+UrlSafeBase64.encodeToString("lovelivetest:test.mp3"))
                .putNotEmpty("persistentNotifyUrl", "http://requestb.in/zozemrzo")
                .putNotEmpty("persistentPipeline", ""), true);*/
     /*   File cc=new File("/tmp/dd.x");
        System.out.println(cc.getPath());

        String dataoriginal="http://mpic.spriteapp.cn/x/640x400/ugc/2016/03/06/56dc3a4d5c852.jpg";

        System.out.println(dataoriginal.substring(dataoriginal.indexOf("/",10)));

        JSONObject obj = new JSONObject(HttpClientUtils.response("http://7xrgsk.com1.z0.glb.clouddn.com/2016-03-03_56d8104627fdc.mp4?avinfo"));
        JSONObject ccd=obj.getJSONObject("format");
        double duration=ccd.getDouble("duration");*/
        //JSONArray v=obj.getJSONArray("\"format\"");
        //JSONArray ja = jo.getJSONArray("map");


        //System.out.println(duration);
    }
}
