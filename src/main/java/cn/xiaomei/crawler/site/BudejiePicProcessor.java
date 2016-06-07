package cn.xiaomei.crawler.site;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.db.MysqlPipeline;
import cn.xiaomei.crawler.utils.ExecuteShellComand;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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
public class BudejiePicProcessor implements PageProcessor {

    public static final String URL_LIST = "http://www.budejie.com/new-pic/";

    public static final Map<String, String> picUrls = new HashMap<String, String>();
    public static final String FROM_URL = "http://www.budejie.com";


    private Site site = Site
            .me()
            .setDomain("www.budejie.com")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {
        System.out.println(page.getUrl().get());

        List<Selectable> jrlist = page.getHtml().xpath("//div[@class=\"j-r-list\"]/ul/li").nodes();
        List<Map<String, String>> budejies = new ArrayList<Map<String, String>>();
        for (Selectable jr : jrlist) {
            // System.out.println(jr.get());
            Map<String, String> data = new HashMap<String, String>();
            data.put("add_time", jr.xpath("//span[@class=\"u-time  f-ib f-fr\"]//text()").get());
            data.put("count_good", jr.xpath("//li[@class=\"j-r-list-tool-l-up\"]/span//text()").get());
            String share = jr.xpath("//div[@class=\"j-r-list-tool-ct-share-c\"]/span//text()").get();
            //System.out.println(share.substring(4, share.length() - 2));
            data.put("count_share", share.substring(4, share.length() - 2));
            data.put("count_review", jr.xpath("//span[@class=\"comment-counts\"]//text()").get());
            data.put("count_view", jr.xpath("//li[@class=\"j-r-list-tool-l-down \"]/span//text()").get());
            data.put("address", jr.xpath("//a[@class=\"j-list-comment\"]//@href").get());
            data.put("from_type", FROM_URL);
            data.put("content", jr.xpath("//div[@class=\"j-r-list-c-desc\"]//text()").get());
            data.put("title", jr.xpath("//div[@class=\"j-r-list-tool\"]//@data-title").get());
            data.put("id", jr.xpath("//div[@class=\"j-r-list-tool\"]//@data-id").get());
            System.out.println(jr.xpath("//div[@class=\"j-r-list-c-img\"]//img[@class=\"lazy\"]//@data-original"));
            String dataoriginal = jr.xpath("//div[@class=\"j-r-list-c-img\"]//img[@class=\"lazy\"]//@data-original").get();

            data.put("url", Constant.QSite + dataoriginal.substring(dataoriginal.indexOf("/", 10)));
            picUrls.put(data.get("id"), dataoriginal);
            int last = dataoriginal.lastIndexOf(".");
            if (last > 0) {
                data.put("pic_type", dataoriginal.substring(last + 1));
                data.put("type", "0");
                if (dataoriginal.substring(last + 1).equals("gif"))
                    data.put("type", "1");
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
        BudejiePicProcessor budejiePicProcessor = new BudejiePicProcessor();
        Spider.create(budejiePicProcessor).addUrl(URL_LIST)
                .addPipeline(new MysqlPipeline(1))
                .run();

        Collection<String> urls = budejiePicProcessor.picUrls.values();
        long file_sub = System.currentTimeMillis();
        System.out.println(urls.size());
        File pics = new File(Constant.getCrawlerPicJobPath() + file_sub + ".txt");
        try {
            FileUtils.writeLines(pics, urls);
            System.out.println(FileUtils.readFileToString(pics));

        } catch (IOException e) {
            e.printStackTrace();
        }

        String jobname = "picjob" + file_sub;
        ExecuteShellComand.executeQfetch(Constant.getCrawlerPicJobPath(), pics, jobname);
        //成功上传的条数
        int resultcount = ExecuteShellComand.executeLeveldb(Constant.getCrawlerPicJobPath(), "pic_job_" + file_sub);

        //是否全部上传成功,重试
        System.out.println(urls.size() + "," + resultcount);
        if (resultcount != urls.size()) {
            ExecuteShellComand.executeQfetch(Constant.getCrawlerPicJobPath(), pics, jobname);
        }
        MysqlPipeline mpl = new MysqlPipeline(-1);
        mpl.updatePic(budejiePicProcessor.picUrls.keySet());
        //http://7xrlok.com2.z0.glb.qiniucdn.com/ugc%2F2016%2F03%2F06%2F56dc3836aa1bc.gif?imageInfo
    }
}