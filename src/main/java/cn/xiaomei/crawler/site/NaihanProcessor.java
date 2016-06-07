package cn.xiaomei.crawler.site;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.db.MysqlQiushibaikePipeline;
import cn.xiaomei.crawler.utils.ExecuteShellComand;
import org.apache.commons.io.FileUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by chunli on 16/3/3.
 */
public class NaihanProcessor implements PageProcessor {

    //public static final String URL_LIST = "http://www.qiushibaike.com/";
    public static final String URL_LIST = "http://neihanshequ.com/pic/";
    public static final String FROM_URL = "http://neihanshequ.com/";
    public static final Map<String, String> urls = new HashMap<String, String>();

    private Site site = Site
            .me()
            .setDomain(FROM_URL)
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {
        System.out.println(page.getUrl().get());
        List<Selectable> jrlist = page.getHtml().xpath("//div[@class=\"content\"]//ul//li//div[@class=\"detail-wrapper\"]").nodes();
        List<Map<String, String>> budejies = new ArrayList<Map<String, String>>();
        for (Selectable jr : jrlist) {
            // System.out.println(jr.get());
            Map<String, String> data = new HashMap<String, String>();
            data.put("content", jr.xpath("//h1[@class=\"title\"]/p/text()").get());
            data.put("type", "0");
            data.put("count_good", jr.xpath("//li[@class=\"digg-wrapper \"]/span/text()").get());
            data.put("count_share", jr.xpath("//span[@class=\"share\"]/text()").get());
            data.put("count_view", jr.xpath("//li[@class=\"bury-wrapper \"]/span/text()").get());
            data.put("count_review", jr.xpath("//li[@class=\"post-comment-btn right\"]/span/text()").get());
            data.put("address", jr.xpath("//div[@class=\"content-wrapper\"]/a//@href").get());
            data.put("from_type", FROM_URL);
            data.put("title", "");
            String id = jr.xpath("//div[@class=\"content-wrapper\"]/a//@data-group-id").get();
            data.put("id", id);
            Selectable selectable = jr.xpath("//img[@id=\"groupImage\"]/@data-src");

            data.put("add_time", Constant.getCurrentDateTime());

            if (selectable.match()) {
                String dataoriginal = selectable.get();
                int last = dataoriginal.lastIndexOf(".");
                if (last > 0) {
                    data.put("pic_type", dataoriginal.substring(last + 1));
                    if (dataoriginal.substring(last + 1).equals("gif"))
                        data.put("type", "1");
                }
                urls.put(data.get("id"), dataoriginal);
                data.put("url", Constant.QSite + dataoriginal.substring(dataoriginal.indexOf("/", 10)));

            }
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
        NaihanProcessor qiushibaikeProcessor = new NaihanProcessor();

        Spider.create(qiushibaikeProcessor).addUrl(URL_LIST)
                .addPipeline(new MysqlQiushibaikePipeline())
                .run();

        Collection<String> urls = qiushibaikeProcessor.urls.values();
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


        //update mp4
        //http://7xrgsk.com1.z0.glb.clouddn.com/2016-03-03_56d8104627fdc.mp4?vframe/jpg/offset/100/w/480/h/264
        //时长:http://7xrgsk.com1.z0.glb.clouddn.com/2016-03-03_56d8104627fdc.mp4?avinfo
    }
}