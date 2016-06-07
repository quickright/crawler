package cn.xiaomei.crawler.site;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.db.FEPersionPipeline;
import cn.xiaomei.crawler.db.bean.*;
import cn.xiaomei.crawler.utils.ShellUtils;
import jdk.nashorn.tools.Shell;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;

import static cn.xiaomei.crawler.utils.ShellUtils.getFEtel;

/**
 * 58个人简历
 * Created by chunli on 16/3/3.
 */
public class FiveEightPersionProcessor implements PageProcessor {

    //餐饮
    public static final String URL_LIST = "http://\\w+.58.com/\\w+/";
    //收银员
    //http://bj.58.com/shouying/
    //餐饮 http://cd.58.com/qzzplvyoujiudian/

    //查看电话http://jianli.58.com/ajax/resumemsg/?operate=zptdown&rid=91299974949389

    public static final String URL_AREA_LIST = "http://\\w+.58.com/\\w+/\\w+/";

    public static final String PAGE_URL_LIST = "http://\\w+.58.com/\\w+/\\w+/pn\\d+";

    public static final String FROM_URL = "http://jianli.58.com/";
    public static final String DETAIL_URL = "http://jianli.58.com/resume/";
    public static final String TEL_URL_ORIGIN = "http://jianli.58.com/ajax/resumemsg/?operate=zptdown&rid=";
    public static final String TEL_URL = TEL_URL_ORIGIN + "\\d+";

    private Map<String, String> areaurl = new HashMap<String, String>();

    //public static final String JOB_DETAIL_URL = "http://m.58.com/\\w+/\\w+/\\d+x.shtml";

    //public static final Map<String, String> urls = new HashMap<String, String>();

    private String cityname;

    public FiveEightPersionProcessor(String cityname) {
        this.cityname = cityname;
    }

    private Site site = Site
            .me()
            .addCookie("58cooper", "\"userid=31350721306890&username=fswgk_p2&cooperkey=4f516987db8f91a7f115f802b72f4e64\"", ".58.com")
            .addCookie("58tj_uuid", "0360ce5e-405d-47ce-bee2-e872fbd6c097", ".58.com")
            //.addCookie("Hm_lpvt_a3013634de7e7a5d307653e15a0584cf", "1461571173", ".jianli.58.com")
            //.addCookie("Hm_lvt_a3013634de7e7a5d307653e15a0584cf", "1461554428,1461554715,1461571064,1461571150", ".jianli.58.com")
            //.addCookie("JSESSIONID", "9A0A45D0E5ACF21519CB6217657FC43D", "jianli.58.com")
            .addCookie("PPU", "\"UID=31350721306890&PPK=271ffd7f&PPT=3f500ba9&SK=887EB60F8846773DB023F6B54864B0F38D8A6EA6465E3EE75&LT=1461571101672&UN=fswgk_p2&LV=510a3f02&PBODY=BY0VprnIUK4iCr0W8-6YRH0wnIt7pIuoPOTi_wqAXZ-IpwEKOYdhGoTFMDHm8ZF6QInLu7IP030C4qsV6bqEVpfXktYgGZ1Q3BscS4ZTop4iT6CcxSwf8APSS-gcETmg1ippe8qXfwITmqlsZy83gNisJCa_YXrqL7fZqvgygWU&VER=1\"", ".58.com")
            .addCookie("als", "0", ".58.com")
            .addCookie("bj58_id58s", "\"MGI4NUtPX0tMYkRXOTgwOA==\"", ".58.com")
            .addCookie("bj58_init_refer", "\"http://bj.58.com/job.shtml?PGTID=0d100000-0000-1c67-3817-677c116dc709&ClickID=3\"", ".58.com")
            .addCookie("bj58_new_session", "0", ".58.com")
            .addCookie("bj58_new_uv", "1", ".58.com")
            .addCookie("city", "bj", ".58.com")
            .addCookie("id58", "c5/nn1cdzdg7t+jWBsP9Ag==", ".58.com")
            .addCookie("init_refer", "http%253A%252F%252Fbj.58.com%252F", ".58.com")
            .addCookie("ipcity", "bj%7C%u5317%u4EAC", ".58.com")
            .addCookie("myfeet_tooltip", "end", ".58.com")
            .addCookie("new_session", "0", ".58.com")
            //.addCookie("new_uv", "1", ".58.com")
            .addCookie("sessionid", "0ff51956-e249-497c-98cd-76c1503ae3ac", ".58.com")
            .addCookie("spm", "", ".58.com")
            .addCookie("utm_source", "", ".58.com")
            .addCookie("www58com", "\"AutoLogin=false&UserID=31350721306890&UserName=fswgk_p2&CityID=0&Email=&AllMsgTotal=0&CommentReadTotal=0&CommentUnReadTotal=0&MsgReadTotal=0&MsgUnReadTotal=0&RequireFriendReadTotal=0&RequireFriendUnReadTotal=0&SystemReadTotal=0&SystemUnReadTotal=0&UserCredit=0&UserScore=0&PurviewID=&IsAgency=false&Agencys=null&SiteKey=6132DEA395E09C43AA7DB92E1F39BA20C20C010A58781461D&Phone=&WltUrl=&UserLoginVer=7A8AA9BF524FE054790A4C9FFA3C81A8D&LT=1461571101672\"", ".58.com")
            .addCookie("xxzl_cp", "94189afead114887ab0a038262c02bec06e", ".58.com")
            .setDomain(FROM_URL)
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Windows7) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {
        String sourceurl = page.getUrl().get();
        Html html = page.getHtml();
        System.out.println(sourceurl);
        if (page.getUrl().regex(PAGE_URL_LIST).match() || page.getUrl().regex(URL_AREA_LIST).match()) {
            System.out.println("1");

            List<Selectable> jrlist = html.xpath("//div[@class=\"tablist\"]/dl").nodes();

            Set<String> urls = new HashSet<String>();
            for (Selectable jr : jrlist) {
                String id = jr.xpath("//dd[@class=\"w20\"]/i/@infoid").get();
                String isphone = jr.xpath("//dt[@class=\"w295\"]/i/text()").get();
                if(isphone!=null){
                    ShellUtils.execFETelUrl(TEL_URL_ORIGIN+id);
                }
                urls.add(DETAIL_URL + id);
            }
           /* for (int i = 0; i < 3; i++)
                page.addTargetRequest(new ArrayList<String>(urls).get(i));*/
            page.addTargetRequests(new ArrayList(urls));
            String nextpage = page.getHtml().xpath("//div[@class=\"pager\"]/a[@class=\"next\"]/@href").get();
            if(nextpage.indexOf("pn1")>0) {
                System.out.println(nextpage+"-----------------------------------------------------------------");
                page.addTargetRequest(nextpage);
            }
            PageProgress pageprogress = new PageProgress();
            pageprogress.setPageurl(sourceurl);
            pageprogress.setNum(urls.size());
            pageprogress.setDetail(new ArrayList<String>(urls));
            pageprogress.setFlag("58persion-first");
            pageprogress.setLocation("bj");
            pageprogress.setAddDate(Constant.getCurrentDateTime());
            page.putField("page", pageprogress);

        }else if (page.getUrl().get().indexOf(DETAIL_URL) > -1) {
            System.out.println("2");
            //是否认证
            Selectable noCertificate = page.getHtml().xpath("//span[@class=\"noCertificate\"]");
            String name = page.getHtml().xpath("//span[@class=\"name\"]/text()").get();
            //String tel = page.getHtml().xpath("//span[@class=\"ored\"]/text()").get();
            String tel =getFEtel(page.getUrl().get());

            //招聘职位
            String id = page.getHtml().xpath("//input[@id=\"hideRid\"]/@value").get();

            String sexage = page.getHtml().xpath("//span[@class=\"sexage\"]/text()").get();
            String[] sexages = sexage.replaceAll("（", "").replaceAll("）", "").split("，");
            String jobgoal = page.getHtml().xpath("//span[@class=\"jobGoal\"]/text()").get();
            String pubdate = page.getHtml().xpath("//a[@class=\"refreshTime f-58\"]/text()").get();
            String modtab = page.getHtml().xpath("//div[@class=\"modtab\"]/span/text()").get();


            List<Selectable> expectTitles = page.getHtml().xpath("//div[@class=\"expectTitle\"]/dl/dd").nodes();

            List<String> tags = page.getHtml().xpath("//div[@class=\"cbbg3\"]/span/text()").all();

            List<String> expectDetail0 = expectTitles.get(0).xpath("//ul[@class=\"expectDetail\"]/li/text()").all();
            List<String> expectDetail1 = expectTitles.get(1).xpath("//ul[@class=\"expectDetail\"]/li/text()").all();

            /*if (noCertificate != null) {
                page.addTargetRequest(TEL_URL_ORIGIN + id);
            }*/
            String intrCon = page.getHtml().xpath("//div[@class=\"intrCon\"]/text()").get();

            List<Selectable> hresumes = page.getHtml().xpath("//div[@class=\"infoview\"]").nodes();
            List<FiveEightResume> resumes = new ArrayList<FiveEightResume>();
            for (Selectable hresume : hresumes) {
                FiveEightResume resume = new FiveEightResume();
                String entri = hresume.xpath("//div[@class=\"infoview\"]/h4/text()").get();
                String experTime = hresume.xpath("//span[@class=\"experTime\"]/text()").get();

                //List<String> all = hresume.xpath("//div/p/std/text()").all();
                List<Selectable> all = hresume.xpath("//div[@class=\"infoview\"]/p/span[@class=\"std\"]/text()").nodes();
                //String jobdesc =hresume.xpath("//div[@class=\"infoview\"]/p[@class=\"pst2\"]/span[@class=\"std\"]/text()").get();
                resume.setEntri(entri);
                resume.setExperDate(all.size() > 0 ? all.get(0).get() : "");
                resume.setSalary(all.size() > 1 ? all.get(1).get() : "");
                resume.setJobgoal(all.size() > 2 ? all.get(2).get() : "");
                resume.setJobdesc(all.size() > 3 ? all.get(3).get() : "");
                resume.setExperTime(experTime);
                resumes.add(resume);
            }

            FiveEightPersion persion = new FiveEightPersion();
            persion.set_id(id);
            persion.setTel(tel);
            persion.setAge(sexages[1]);
            if (expectDetail0.size() > 0) {
                persion.setBasic_college(expectDetail0.get(0));
            }
            if (expectDetail0.size() >= 6) {
                persion.setBasic_huji(expectDetail0.get(6));
            }
            if (expectDetail0.size() >= 2) {
                persion.setBasic_jingyan(expectDetail0.get(2));
            }
            if (expectDetail0.size() >= 4) {
                persion.setBasic_live(expectDetail0.get(4));
            }
            persion.setCity(this.cityname);
            persion.setIntrCon(intrCon);

            if (expectDetail1.size() > 0) {
                persion.setJob_goal(expectDetail1.get(0));
            }
            if (expectDetail1.size() >= 2) {

                persion.setJob_local(expectDetail1.get(2));
            }
            if (expectDetail1.size() >= 4) {

                persion.setJob_salary(expectDetail1.get(4));
            }
            persion.setName(name);
            persion.setPubdate(pubdate);
            persion.setSex(sexages[0]);
            persion.setSiteurl(sourceurl);
            persion.setTitle(jobgoal);
            persion.setExperience(modtab);
            persion.setTags(tags);
            persion.setResumes(resumes);
            page.putField(persion.get_id(), persion);

        }
        else if (page.getUrl().regex(URL_LIST).match()) {
            System.out.println("3");
            List<Selectable> seljobArea = html.xpath("//ul[@class=\"seljobArea\"]/li/a").nodes();
           // page.addTargetRequest(seljobArea.get(1).xpath("//a/@href").get());
            for (int i = 1; i < seljobArea.size(); i++) {
                String href = seljobArea.get(i).xpath("//a/@href").get();
                page.addTargetRequest(href);
                // String area = seljobArea.get(i).xpath("//a/text()").get();
            }
        }


        /* else if (page.getUrl().get().indexOf(TEL_URL_ORIGIN) > -1) {
            //
            String tel = html.xpath("//span[@class=\"f-f1a\"]/text()").get();
            FiveEightPersion persion = new FiveEightPersion();
            persion.set_id(sourceurl.substring(sourceurl.lastIndexOf("/") + 1));
            persion.setTel(tel);
            page.putField(persion.get_id(), persion);
        } */
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        for(FECityUrl feCityUrl:Constant.get58CityUrls()) {
            // for(Map.Entry<String,String> site:sites.entrySet()) {
            FiveEightPersionProcessor fiveEightPersionProcessor = new FiveEightPersionProcessor(feCityUrl.getCitycn());
            if(feCityUrl.getIsused()) {
                Spider.create(fiveEightPersionProcessor).addUrl(feCityUrl.getQzmeirongjianshen())
                        //.addUrl("http://jianli.58.com/resume/91253982237963")//"http://jianli.58.com/resume/91385615415820/?psid=116557027191572198888439897&entinfo=91385615415820_0&iuType=p_0&PGTID=0d303494-0000-1880-7322-9f44ee0b98da&ClickID=2")//URL_LIST)//"http://jianli.58.com/ajax/resumemsg/?operate=zptdown&rid=91381037324554")//"http://jianli.58.com/resume/91385615415820/?psid=192121407191571041953002936&entinfo=91385615415820_0&iuType=p_0&PGTID=0d303494-0000-1689-e11f-992d4590e36f&ClickID=3")
                        //  "http://jianli.58.com/ajax/resumemsg/?operate=zptdown&rid=91382448104202")//http://jianli.58.com/resume/91382448104202")//URL_LIST)
                        .addPipeline(new FEPersionPipeline()).thread(1)
                        .run();
                Spider.create(fiveEightPersionProcessor).addUrl(feCityUrl.getQzshouying())
                        //.addUrl("http://jianli.58.com/resume/91253982237963")//"http://jianli.58.com/resume/91385615415820/?psid=116557027191572198888439897&entinfo=91385615415820_0&iuType=p_0&PGTID=0d303494-0000-1880-7322-9f44ee0b98da&ClickID=2")//URL_LIST)//"http://jianli.58.com/ajax/resumemsg/?operate=zptdown&rid=91381037324554")//"http://jianli.58.com/resume/91385615415820/?psid=192121407191571041953002936&entinfo=91385615415820_0&iuType=p_0&PGTID=0d303494-0000-1689-e11f-992d4590e36f&ClickID=3")
                        //  "http://jianli.58.com/ajax/resumemsg/?operate=zptdown&rid=91382448104202")//http://jianli.58.com/resume/91382448104202")//URL_LIST)
                        .addPipeline(new FEPersionPipeline()).thread(1)
                        .run();
                Spider.create(fiveEightPersionProcessor)
                        .addUrl(feCityUrl.getQzyingyeyuan())//.addUrl("http://jianli.58.com/resume/91253982237963")//"http://jianli.58.com/resume/91385615415820/?psid=116557027191572198888439897&entinfo=91385615415820_0&iuType=p_0&PGTID=0d303494-0000-1880-7322-9f44ee0b98da&ClickID=2")//URL_LIST)//"http://jianli.58.com/ajax/resumemsg/?operate=zptdown&rid=91381037324554")//"http://jianli.58.com/resume/91385615415820/?psid=192121407191571041953002936&entinfo=91385615415820_0&iuType=p_0&PGTID=0d303494-0000-1689-e11f-992d4590e36f&ClickID=3")
                        //  "http://jianli.58.com/ajax/resumemsg/?operate=zptdown&rid=91382448104202")//http://jianli.58.com/resume/91382448104202")//URL_LIST)
                        .addPipeline(new FEPersionPipeline()).thread(1)
                        .run();
                Spider.create(fiveEightPersionProcessor).addUrl(feCityUrl.getQzzplvyoujiudian())//.addUrl("http://jianli.58.com/resume/91253982237963")//"http://jianli.58.com/resume/91385615415820/?psid=116557027191572198888439897&entinfo=91385615415820_0&iuType=p_0&PGTID=0d303494-0000-1880-7322-9f44ee0b98da&ClickID=2")//URL_LIST)//"http://jianli.58.com/ajax/resumemsg/?operate=zptdown&rid=91381037324554")//"http://jianli.58.com/resume/91385615415820/?psid=192121407191571041953002936&entinfo=91385615415820_0&iuType=p_0&PGTID=0d303494-0000-1689-e11f-992d4590e36f&ClickID=3")
                        //  "http://jianli.58.com/ajax/resumemsg/?operate=zptdown&rid=91382448104202")//http://jianli.58.com/resume/91382448104202")//URL_LIST)
                        .addPipeline(new FEPersionPipeline()).thread(1)
                        .run();
            }
        }


       /* FiveEightPersionProcessor fiveEightPersionProcessor = new FiveEightPersionProcessor("广州");
        Spider.create(fiveEightPersionProcessor).addUrl("http://jianli.58.com/resume/91447504488714")
                 //.addUrl("http://jianli.58.com/resume/91253982237963")//"http://jianli.58.com/resume/91385615415820/?psid=116557027191572198888439897&entinfo=91385615415820_0&iuType=p_0&PGTID=0d303494-0000-1880-7322-9f44ee0b98da&ClickID=2")//URL_LIST)//"http://jianli.58.com/ajax/resumemsg/?operate=zptdown&rid=91381037324554")//"http://jianli.58.com/resume/91385615415820/?psid=192121407191571041953002936&entinfo=91385615415820_0&iuType=p_0&PGTID=0d303494-0000-1689-e11f-992d4590e36f&ClickID=3")
                //  "http://jianli.58.com/ajax/resumemsg/?operate=zptdown&rid=91382448104202")//http://jianli.58.com/resume/91382448104202")//URL_LIST)
                .addPipeline(new FEPersionPipeline()).thread(1)
                .run();*/
        //  }
    }
}

/*.addCookie("58cooper", "\"userid=31350721306890&username=fswgk_p2&cooperkey=4f516987db8f91a7f115f802b72f4e64\"", ".58.com")
        .addCookie("58tj_uuid", "0360ce5e-405d-47ce-bee2-e872fbd6c097", "")
        .addCookie("PPU", "\"UID=31350721306890&PPK=271ffd7f&PPT=e49876e2&SK=7A8AA9BF524FE0547C4BBDC134A5CDCBB741970435CD38143&LT=1459929469015&UN=fswgk_p2&LV=93400af3&PBODY=h1tD3FZ2JqDzrJZGU3t26p76kRxN5_MtbLyj2hbeerij9ZVZ-RuLy3mt80CEdzOMyNLr0jR4MdzZaHS1yQqfPfjP7zuN1_Cqe_IOB-9PzllOtTUOHJAk3I4gpxn3FxFkFvKS8lZFMt_gJYH812PnMlO28YwQmqsPGLBGsGdEDso&VER=1\"", "")
        .addCookie("cookieuid1", "c5/nplcEwVyFowCmAwOFAg==", "")
        .addCookie("als", "0", ".58.com")
        .addCookie("f", "n", "m.58.com")
        .addCookie("new_session", "0", ".58.com")
        .addCookie("mlogintype", "1", ".58.com")
        .addCookie("m58outoffline", "6", ".58.com")
        .addCookie("m58offline", "6", ".58.com")
        .addCookie("m58comvp", "t06v115.159.229.14", ".58.com")
        .addCookie("init_refer", "http%253A%252F%252Fm.m.58.com%252Findex%252F%253FPGTID%253D0d40349f-0000-18ac-e01d-495044ad1335%2526ClickID%253D2\t", ".58.com")
        .addCookie("id58", "c5/nn1cEwVwRXMCGCc7eAg==", "")
        .addCookie("new_uv", "1", ".58.com")
        .addCookie("www58com", "\"AutoLogin=true&UserID=31350721306890&UserName=fswgk_p2&CityID=0&Email=&AllMsgTotal=0&CommentReadTotal=0&CommentUnReadTotal=0&MsgReadTotal=0&MsgUnReadTotal=0&RequireFriendReadTotal=0&RequireFriendUnReadTotal=0&SystemReadTotal=0&SystemUnReadTotal=0&UserCredit=0&UserScore=0&PurviewID=&IsAgency=false&Agencys=null&SiteKey=4E2D7FDC8FC545758B7A95E46C579711AB83CF3752A9AD297&Phone=&WltUrl=&UserLoginVer=3FFB2E44A074375AABFA41D997FE47062&LT=1459929469018\"", "")
         */