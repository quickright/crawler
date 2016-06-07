package cn.xiaomei.crawler.site;

import cn.xiaomei.crawler.db.MysqlPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chunli on 16/3/3.
 */
public class BudejieTextProcessor implements PageProcessor {

    public static final String URL_LIST = "http://www.budejie.com/new-text/";

    public static final String URL_POST = "http://www\\.budejie\\.com/detail-\\d+\\.html";
    public static final String FROM_URL="http://www.budejie.com";

    private Site site = Site
            .me()
            .setDomain("www.budejie.com")
            .addStartUrl(URL_LIST)
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
            //System.out.println(share.substring(4, share.length() - 2));
            data.put("count_share", share.substring(4, share.length() - 2));
            data.put("count_review", jr.xpath("//span[@class=\"comment-counts\"]//text()").get());
            data.put("count_view", jr.xpath("//li[@class=\"j-r-list-tool-l-down \"]/span//text()").get());
            data.put("address", jr.xpath("//a[@class=\"j-list-comment\"]//@href").get());
            data.put("from_type",FROM_URL);
            data.put("content", jr.xpath("//div[@class=\"j-r-list-c-desc\"]//text()").get());
            data.put("title", jr.xpath("//div[@class=\"j-r-list-tool\"]//@data-title").get());
            data.put("id", jr.xpath("//div[@class=\"j-r-list-tool\"]//@data-id").get());
            budejies.add(data);
        }
        page.putField("",budejies);
        if (budejies.size() == 0) {
            page.setSkip(true);
        }
    }

    public Site getSite() {

        return site;
    }

    public static void main(String[] args) {
        Spider.create(new BudejieTextProcessor()).addUrl(URL_LIST).addPipeline(new MysqlPipeline(0))
                .run();

    }
}