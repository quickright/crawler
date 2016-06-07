package cn.xiaomei.crawler.utils;


import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chunli on 16/3/8.
 */
public class HttpClientUtils {
    private static final String NEXT_LINE = "\r\n";

        public static void main(String args[])
        {
            Map<String,Integer> hosts=new HashMap<String,Integer>();
            hosts.put("163.125.196.161",9999);
            hosts.put("113.69.185.150",9797);
            hosts.put("210.79.104.187",8080);
            hosts.put("120.55.245.47",80);
            hosts.put("114.95.89.7",8118);


            for(Map.Entry<String,Integer> host:hosts.entrySet()) {
                try {
                    System.out.println(host.toString());
                    System.out.println(HttpClientUtils.isConnect(host.getKey(), host.getValue().intValue()));

                } catch (IOException e) {
                    System.out.println("false");
                }
            }
        }


        // System.out.println(HttpClientUtils.response("http://www.nuomi.com/pcindex/main/shopchain?dealId=3815535"));
        //System.out.println(HttpClientUtils.response("http://7xrgsk.com1.z0.glb.clouddn.com/2016-03-03_56d8104627fdc.mp4?avinfo"));

    public static boolean isConnect(String host,int port) throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpHost proxy = new HttpHost(host,port);
        httpClient.getParams()
                .setParameter(ConnRouteParams.DEFAULT_PROXY, proxy)
                .setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2*1000);
        HttpGet httpGet = new HttpGet("http://httpbin.org/ip");
        //执行
        HttpResponse response = httpClient.execute(httpGet);

        HttpEntity entry = response.getEntity();
        System.out.println(response.getStatusLine().getStatusCode());
        /*StringBuffer sb = new StringBuffer();
        if(entry != null)
        {
            InputStreamReader is = new InputStreamReader(entry.getContent());
            BufferedReader br = new BufferedReader(is);
            String str = null;
            while((str = br.readLine()) != null)
            {
                sb.append(str.trim());
            }
            br.close();
        }*/

        return response.getStatusLine().getStatusCode()==200;
    }
    public static String response(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response1 = null;

        try {
            response1=httpclient.execute(httpGet);
            HttpEntity entity1 = response1.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
           // EntityUtils.consume(entity1);
            Reader reader = new InputStreamReader(entity1.getContent());
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            StringBuffer buffer = new StringBuffer();

            while ((line = bufferedReader.readLine()) != null) {
                line = new String(line.getBytes());
                buffer.append(line).append(NEXT_LINE);
            }
            bufferedReader.close();
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            try {
                if (response1 != null)
                    response1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
