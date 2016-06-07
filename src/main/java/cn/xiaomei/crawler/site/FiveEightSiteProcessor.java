package cn.xiaomei.crawler.site;

import cn.xiaomei.crawler.db.FEPipeline;
import cn.xiaomei.crawler.db.bean.*;
import org.apache.http.HttpHost;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;

/**
 * 58招聘信息
 * Created by chunli on 16/3/3.
 */
public class FiveEightSiteProcessor implements PageProcessor {

    //public static final String URL_LIST = "http://www.qiushibaike.com/";
    public static final String URL_LIST = "http://bj.58.com/\\w+/zplvyoujiudian/";
    public static final String PAGE_URL_LIST = "http://m.58.com/\\w+/zplvyoujiudian/pn\\d+";

    public static final String FROM_URL = "http://m.58.com/";
    public static final String DETAIL_URL = "http://qy.m.58.com/m_detail/";
    public static final String JOB_DETAIL_URL = "http://m.58.com/\\w+/\\w+/\\d+x.shtml";

    //public static final Map<String, String> urls = new HashMap<String, String>();

    private String cityname;

    public FiveEightSiteProcessor(String cityname) {
        this.cityname = cityname;
    }

    private Site site = Site
            .me()
            .setDomain("www.58.com")
            .setRetryTimes(3).setRetrySleepTime(5 * 60 * 1000)
            .setHttpProxy(new HttpHost("115.159.5.247",8080))
            .setUserAgent(
                    "IE/7.0 (Macintosh; Intel Windows8) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {
        String sourceurl=page.getUrl().get();

        if (page.getUrl().regex(URL_LIST).match() || page.getUrl().regex(PAGE_URL_LIST).match()) {
            List<Selectable> jrlist = page.getHtml().xpath("//input[@name=\"uid\"]/@uid").nodes();
            Set<String> urls = new HashSet<String>();
            for (Selectable jr : jrlist) {
                String[] ids = jr.get().split("_");
                for (String id : ids) {
                    if (id.length() > 6) {
                        urls.add(DETAIL_URL + id);
                    }
                }
            }
           /* for (int i = 0; i < 3; i++)
                page.addTargetRequest(new ArrayList<String>(urls).get(i));*/
            page.addTargetRequests(new ArrayList(urls));
            String[] ids = jrlist.get(0).get().split("_");

           // page.addTargetRequest(DETAIL_URL + ids[0]);
            String nextpage = page.getHtml().xpath("//div[@class=\"pager\"]/a[@class=\"next\"]/@href").get();
            if(nextpage==null){
                System.out.println("source:"+sourceurl);
            }
            page.addTargetRequest(nextpage);
            PageProgress pageprogress=new PageProgress();
            pageprogress.setPageurl(page.getUrl().get());
            pageprogress.setNum(urls.size());
            pageprogress.setDetail(new ArrayList<String>(urls));
            pageprogress.setFlag("first");
            pageprogress.setLocation("bj");
            page.putField("page", pageprogress);

        } else if (page.getUrl().get().indexOf(DETAIL_URL) > -1) {
            FiveEightEnterBean fe = new FiveEightEnterBean();
            String name = page.getHtml().xpath("//div[@class=\"titArea\"]/h2/text()").get();
            Selectable isauth = page.getHtml().xpath("//span[@class=\"busLic\"]");
            fe.setIsAuth(isauth!=null);
            fe.setName(name == null ? "" : name.replaceAll("公司全称：", ""));
            fe.setCity(this.cityname);
            //fe.setPageno(page.getUrl().);
            List<Selectable> detAreas = page.getHtml().xpath("//div[@class=\"detArea\"]/dl/dd").nodes();
            String pageurl = page.getUrl().get();
            fe.setId(pageurl.substring(pageurl.lastIndexOf("/") + 1));
            if (detAreas.size() > 2) {
                fe.setNature(detAreas.get(0).xpath("//dd/text()").get());
                fe.setScale(detAreas.get(1).xpath("//dd/text()").get());

                fe.setCategory(detAreas.get(2).xpath("//dd/a/text()").all());
                fe.setAddress(detAreas.get(3).xpath("//dd/text()").get());
            }

            List<Selectable> addB = page.getHtml().xpath("//div[@class=\"addB compTouch\"]/dl/dd").nodes();
           // vip-yan
            String compIntro = page.getHtml().xpath("//div[@class=\"compSum\"]/div[@class=\"txtArea\"]/p/span/text()").get();
            fe.setCompIntro(compIntro);
            if (addB.size() >= 1) {
                Selectable contacts = addB.get(0).xpath("//dd/p/span/text()");
                if (contacts != null) {
                    fe.setContacts(contacts.all().toString());
                }
                Selectable email = addB.size() > 1 ? addB.get(1).xpath("//dd/text()") : null;
                if (email != null) {
                    fe.setEmail(email.get());
                }
                fe.setSite(pageurl);

            }

            List<Selectable> jobsurl = page.getHtml().xpath("//ul[@class=\"jobList\"]/li").nodes();
            Set<String> urls = new HashSet<String>();

            for (Selectable joburl : jobsurl) {
                String url = joburl.xpath("//li/a/@href").get();
                urls.add(url);
                page.addTargetRequest(url);
            }
            System.out.println(sourceurl);
            String [] ids=sourceurl.split("/");
            fe.setId(ids[ids.length-1]);
            fe.setJobs(jobsurl.size());
            page.putField(fe.getId(), fe);

            PageProgress pageprogress=new PageProgress();
            pageprogress.setPageurl(page.getUrl().get());
            pageprogress.setNum(urls.size());
            pageprogress.setDetail(new ArrayList<String>(urls));
            pageprogress.setFlag("jobs");
            pageprogress.setLocation("bj");
            page.putField("page", pageprogress);


        }/* else if (page.getUrl().regex(JOB_DETAIL_URL).match()) {
            //是否认证
            String isyz = page.getHtml().xpath("//span[@class=\"yz\"]").get();
            //招聘职位
            String title = page.getHtml().xpath("//span[@class=\"d_title\"]/text()").get();

            String pay = page.getHtml().xpath("//span[@class=\"pay\"]/text()").get();

            String pay_yy = page.getHtml().xpath("//span[@class=\"pay_yy\"]/text()").get();
            List<Selectable> jobcons = page.getHtml().xpath("//section[@class=\"job_con\"]/ul/li").nodes();
            String positionhref = page.getHtml().xpath("//a[@class=\"position\"]/@href").get();
            //for(Selectable jobcon:jobcons){
            String position = jobcons.size()>0?jobcons.get(0).xpath("//span[@class=\"attrValue\"]/a/text()").get():null;
            String req = jobcons.size()>1?jobcons.get(1).xpath("//span[@class=\"attrValue\"]/text()").get():null;
            String location = jobcons.size()>2?jobcons.get(2).xpath("//span[@class=\"attrValue dizhiValue\"]/a/text()").get():null;
//http://zp.service.58.com/api/urljump?url=http%3A%2F%2Fc.58cdn.com.cn%2Folympia%2Fhtml%2Fmap.html%3Flongitude%3D116.47864%26latitude%3D39.940279
            String longitude ="";
            String latitude ="";
            if(positionhref!=null) {
                int paralongitude = positionhref.indexOf("longitude");
                int paralatitude = positionhref.indexOf("latitude");

                longitude = positionhref.substring(paralongitude + 12, paralatitude - 3);
                latitude = positionhref.substring(paralatitude + 11);
            }

            List<String> fuli = jobcons.size()>3?jobcons.get(3).xpath("//div[@class=\"fulivalue attrValue\"]/span/text()").all():null;

            String urlpid = page.getHtml().xpath("//h2[@class=\"c_tit c_tit_ell\"]/a/@href").get();
            String phoneno = page.getHtml().xpath("//a[@id=\"contact_phone\"]/@phoneno").get();

            String dis_con = page.getHtml().xpath("//div[@class=\"dis_con\"]/p/text()").get();
            String id="";
            if(sourceurl!=null) {
                int lastindex1 = sourceurl.lastIndexOf("/");
                int lastindex2 = sourceurl.lastIndexOf(".");
                  id = sourceurl.substring(lastindex1 + 1, lastindex2);
            }
            FiveEightJobBean job = new FiveEightJobBean();
            job.setIsyz(isyz != null);
            job.setId(id);
            job.setTitle(title);
            job.setPay(pay);
            job.setPayyy(pay_yy);
            job.setPosition(position);
            job.setReq(req);
            job.setLocation(location);
            job.setFulivalues(fuli);
            job.setLongitude(longitude);
            job.setLatitude(latitude);
            if(urlpid!=null) {
                String[] urlpida = urlpid.split("/");
                job.setPid(urlpida[urlpida.length - 1]);
            }
            job.setPhone(phoneno);
            job.setPosition_dis(dis_con);
            job.setSiteurl(page.getUrl().get());
            page.putField(job.getId(),job);

            // }

        }*/
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Map<String, String> sites = new HashMap<String, String>();
        sites.put("", "");
        // for(Map.Entry<String,String> site:sites.entrySet()) {
       List<CityUrl> cityurls=new ArrayList<CityUrl>();
        cityurls.add(new CityUrl("http://bj.58.com/chaoyang/zplvyoujiudian/","朝阳","chaoyang",false));
        cityurls.add(new CityUrl("http://bj.58.com/haidian/zplvyoujiudian/","海淀","haidian",false));
        cityurls.add(new CityUrl("http://bj.58.com/dongcheng/zplvyoujiudian/","东城","dongcheng",false));
        cityurls.add(new CityUrl("http://bj.58.com/xicheng/zplvyoujiudian/","西城","xicheng",false));
        cityurls.add(new CityUrl("http://bj.58.com/chongwen/zplvyoujiudian/","崇文","chongwen",false));
        cityurls.add(new CityUrl("http://bj.58.com/xuanwu/zplvyoujiudian/","宣武","xuanwu",false));
        cityurls.add(new CityUrl("http://bj.58.com/fengtai/zplvyoujiudian/","丰台","fengtai",false));
        cityurls.add(new CityUrl("http://bj.58.com/tongzhouqu/zplvyoujiudian/","通州","tongzhouqu",false));
        cityurls.add(new CityUrl("http://bj.58.com/shijingshan/zplvyoujiudian/","石景山","shijingshan",false));
        cityurls.add(new CityUrl("http://bj.58.com/fangshan/zplvyoujiudian/","房山","fangshan",false));
        cityurls.add(new CityUrl("http://bj.58.com/changping/zplvyoujiudian/","昌平","changping",false));
        cityurls.add(new CityUrl("http://bj.58.com/daxing/zplvyoujiudian/","大兴","daxing",false));
        cityurls.add(new CityUrl("http://bj.58.com/shunyi/zplvyoujiudian/","顺义","shunyi",false));
        cityurls.add(new CityUrl("http://bj.58.com/miyun/zplvyoujiudian/","密云","miyun",false));
        cityurls.add(new CityUrl("http://bj.58.com/huairou/zplvyoujiudian/","怀柔","huairou",false));
        cityurls.add(new CityUrl("http://bj.58.com/yanqing/zplvyoujiudian/","延庆","yanqing",false));
        cityurls.add(new CityUrl("http://bj.58.com/pinggu/zplvyoujiudian/","平谷","pinggu",false));
        cityurls.add(new CityUrl("http://bj.58.com/mentougou/zplvyoujiudian/","门头沟","mentougou",false));
        cityurls.add(new CityUrl("http://bj.58.com/bjyanjiao/zplvyoujiudian/","燕郊","bjyanjiao",false));
        cityurls.add(new CityUrl("http://bj.58.com/beijingzhoubian/zplvyoujiudian/","北京周边","beijingzhoubian",false));

        FiveEightSiteProcessor  feProcessor = new FiveEightSiteProcessor("北京");

      for(CityUrl url:cityurls)
        Spider.create(feProcessor).addUrl(url.getUrl())
                .addPipeline(new FEPipeline()).thread(5)
                .run();
        //  }
    }
}