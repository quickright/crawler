package cn.xiaomei.crawler.site;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.db.MysqlPipeline;
import cn.xiaomei.crawler.utils.ExecuteShellComand;
import cn.xiaomei.crawler.utils.HttpClientUtils;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * Created by chunli on 16/3/3.
 */
public class BudejieMp4Processor implements PageProcessor {

    public static final String URL_LIST = "http://www.budejie.com/new-video/";
    public static final String URL_POST = "http://www\\.budejie\\.com/detail-\\d+\\.html";
    public static final String FROM_URL="http://www.budejie.com";

    public static final   Map<String,String> mp4Urls = new HashMap<String, String>();

    private Site site = Site
            .me()
            .setDomain("www.budejie.com")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {
        System.out.println(page.getUrl().get());
        List<Selectable> jrlist = page.getHtml().xpath("//div[@class=\"j-r-list\"]/ul/li").nodes();
        List<Map<String,String>> budejies = new ArrayList<Map<String,String>>();
        for (Selectable jr : jrlist) {
            // System.out.println(jr.get());
            Map<String,String> data=new HashMap<String, String>();
            data.put("add_time", jr.xpath("//span[@class=\"u-time  f-ib f-fr\"]//text()").get());
            data.put("count_good", jr.xpath("//li[@class=\"j-r-list-tool-l-up\"]/span//text()").get());
            String share=jr.xpath("//div[@class=\"j-r-list-tool-ct-share-c\"]/span//text()").get();
            data.put("count_share", share.substring(4, share.length() - 2));
            data.put("count_review", jr.xpath("//span[@class=\"comment-counts\"]//text()").get());
            data.put("count_view", jr.xpath("//li[@class=\"j-r-list-tool-l-down \"]/span//text()").get());
            data.put("address", jr.xpath("//a[@class=\"j-list-comment\"]//@href").get());
            data.put("from_type", FROM_URL);
            data.put("content", jr.xpath("//div[@class=\"j-r-list-c-desc\"]//text()").get());
            data.put("title", jr.xpath("//div[@class=\"j-r-list-tool\"]//@data-title").get());
            data.put("id", jr.xpath("//div[@class=\"j-r-list-tool\"]//@data-id").get());
            System.out.println(jr.xpath("//div[@class=\" j-video\"]//@data-mp4"));
            String dataoriginal=jr.xpath("//div[@class=\" j-video\"]//@data-mp4").get();
            mp4Urls.put(data.get("id"),dataoriginal);
            data.put("url", Constant.QSite+dataoriginal.substring(dataoriginal.indexOf("/",10)));
            data.put("type", "2");

            budejies.add(data);
        }
        page.putField("", budejies);
        if (budejies.size() == 0) {
            page.setSkip(true);
        }

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        BudejieMp4Processor budejiePicProcessor=new BudejieMp4Processor();

        Spider.create(budejiePicProcessor).addUrl(URL_LIST)
                .addPipeline(new MysqlPipeline(2))
                .run();
        Collection<String> urls= budejiePicProcessor.mp4Urls.values();
        long file_sub=System.currentTimeMillis();
        System.out.println(urls.size());
        File mp4s=new File(Constant.getCrawlerMp4JobPath()+file_sub+".txt");
        try {
            FileUtils.writeLines(mp4s, urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jobname="mp4job"+file_sub;

        System.out.println(jobname);
        ExecuteShellComand.executeQfetch(Constant.getCrawlerMp4JobPath(),mp4s, jobname);
        //成功上传的条数
       int count=ExecuteShellComand.executeLeveldb(Constant.getCrawlerMp4JobPath(), jobname);

        //是否全部上传成功,重试
        System.out.println(urls.size()+","+count);
       /* if(count!=urls.size())
        {
            ExecuteShellComand.executeQfetch(Constant.getCrawlerMp4JobPath(),mp4s,jobname);
        }*/

        MysqlPipeline mpl=new MysqlPipeline(-1);
        mpl.updateMp4(budejiePicProcessor.mp4Urls.keySet());


        //update mp4
        //http://7xrgsk.com1.z0.glb.clouddn.com/2016-03-03_56d8104627fdc.mp4?vframe/jpg/offset/100/w/480/h/264
        //时长:http://7xrgsk.com1.z0.glb.clouddn.com/2016-03-03_56d8104627fdc.mp4?avinfo
    }
}