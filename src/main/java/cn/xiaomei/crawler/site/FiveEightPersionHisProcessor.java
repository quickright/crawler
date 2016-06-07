package cn.xiaomei.crawler.site;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.db.FEPersionPipeline;
import cn.xiaomei.crawler.db.bean.FECityUrl;
import cn.xiaomei.crawler.db.bean.FiveEightPersion;
import cn.xiaomei.crawler.db.bean.FiveEightResume;
import cn.xiaomei.crawler.db.bean.PageProgress;
import cn.xiaomei.crawler.utils.ShellUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;

import static cn.xiaomei.crawler.utils.ShellUtils.getFEtel;

/**
 * 下载历史的简历.
 * Created by chunli on 16/3/3.
 */
public class FiveEightPersionHisProcessor implements PageProcessor {

    //餐饮
    public static final String URL_LIST = "http://\\w+.58.com/\\w+/";
    //收银员
    //http://bj.58.com/shouying/
    //餐饮 http://cd.58.com/qzzplvyoujiudian/

    //查看电话http://jianli.58.com/ajax/resumemsg/?operate=zptdown&rid=91299974949389

    public static final String URL_AREA_LIST = "http://\\w+.58.com/\\w+/\\d+/";

    public static final String PAGE_URL_LIST = "http://\\w+.58.com/\\w+/\\w+/pn\\d+";

    public static final String FROM_URL = "http://jianli.58.com/";
    public static final String DETAIL_URL = "http://jianli.58.com/resume/";
    public static final String TEL_URL_ORIGIN = "http://jianli.58.com/ajax/resumemsg/?operate=zptdown&rid=";
    public static final String TEL_URL = TEL_URL_ORIGIN + "\\d+";

    private Map<String, String> areaurl = new HashMap<String, String>();

    //public static final String JOB_DETAIL_URL = "http://m.58.com/\\w+/\\w+/\\d+x.shtml";

    //public static final Map<String, String> urls = new HashMap<String, String>();

    private String cityname;

    public FiveEightPersionHisProcessor(String cityname) {
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
        Html html = new Html(ShellUtils.getHTML(sourceurl));
        System.out.println(sourceurl);


        //是否认证
        Selectable noCertificate = html.xpath("//span[@class=\"noCertificate\"]");
        String name = html.xpath("//span[@class=\"name\"]/text()").get();
        //String tel = html.xpath("//span[@class=\"ored\"]/text()").get();
        if (name == null) {
            savePersion(page, html, sourceurl);
        } else {
            String tel = getFEtel(page.getUrl().get());

            //招聘职位
            String id = html.xpath("//input[@id=\"hideRid\"]/@value").get();

            String sexage = html.xpath("//span[@class=\"sexage\"]/text()").get();
            String[] sexages = sexage.replaceAll("（", "").replaceAll("）", "").split("，");
            String jobgoal = html.xpath("//span[@class=\"jobGoal\"]/text()").get();
            String pubdate = html.xpath("//a[@class=\"refreshTime f-58\"]/text()").get();
            String modtab = html.xpath("//div[@class=\"modtab\"]/span/text()").get();
            String city = html.xpath("//div[@class=\"rp_nav\"]/span/a[0]/text()").get();

            List<Selectable> expectTitles = html.xpath("//div[@class=\"expectTitle\"]/dl/dd").nodes();

            List<String> tags = html.xpath("//div[@class=\"cbbg3\"]/span/text()").all();

            List<String> expectDetail0 = expectTitles.get(0).xpath("//ul[@class=\"expectDetail\"]/li/text()").all();
            List<String> expectDetail1 = expectTitles.get(1).xpath("//ul[@class=\"expectDetail\"]/li/text()").all();

            /*if (noCertificate != null) {
                page.addTargetRequest(TEL_URL_ORIGIN + id);
            }*/
            String intrCon = html.xpath("//div[@class=\"intrCon\"]/text()").get();

            List<Selectable> hresumes = html.xpath("//div[@class=\"infoview\"]").nodes();
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
            persion.setCity(city);
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
        //  for(FECityUrl feCityUrl:Constant.get58CityUrls()) {
        // for(Map.Entry<String,String> site:sites.entrySet()) {
        FiveEightPersionHisProcessor fiveEightPersionProcessor = new FiveEightPersionHisProcessor("");
        // if(feCityUrl.getIsused()) {
        //   Spider.create(fiveEightPersionProcessor).addUrl("http://jianli.58.com/favoriteresumelistui/1").addPipeline(new FEPersionPipeline()).thread(2)
        //          .run();

        for (int i = 200; i < 317; i++) {
            List<String> urls = ShellUtils.getFEPage("http://jianli.58.com/favoriteresumelistui/" + i);
            try {
                Thread.sleep(500 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i + "--------------------------------------------------------------");
            //http://jianli.58.com/showresumeinfo/?rid=88733054010890&t=1461666063097
            for(String url:urls) {
                String resumeurl = DETAIL_URL + url.substring(url.indexOf("rid=") + 4, url.indexOf("&"));
                // if(feCityUrl.getIsused()) {
                Spider.create(fiveEightPersionProcessor).addUrl(resumeurl).addPipeline(new FEPersionPipeline())
                        .run();
            }
        }
        //   }
        //   }
        //  }

    }

    private void savePersion(Page page, Html html, String url) {
        Selectable noCertificate = html.xpath("//span[@class=\"noCertificate\"]");
        List<Selectable> content = html.xpath("//ul[@class=\"contact-list\"]/li").nodes();
        List<Selectable> fields = html.xpath("//div[@class=\"field\"]/dl/dd").nodes();
        String qiuzhiCity = html.xpath("//div[@id=\"detailNav\"]/span/a[2]/text()").get();
        String name = content.get(0).xpath("//li/text()").get();
        //String tel = html.xpath("//span[@class=\"ored\"]/text()").get();
        if (name != null) {
            String tel = getFEtel(url);

            //招聘职位
            String id = url.substring(url.lastIndexOf("/")).replaceAll("/", "");

            String sex = content.size() > 2 ? content.get(2).xpath("//li/text()").get() : "";
            String age = content.size() > 4 ? content.get(4).xpath("//li/text()").get() : "";
            String college = fields.size() > 3 ? fields.get(3).xpath("//dd/p/text()").get() : "";
            String jobgoal = fields.size() > 2 ? fields.get(2).xpath("//dd/a/text()").get() : "";
            String salary = fields.size() > 0 ? fields.get(0).xpath("//dd/text()").get() : "";
            String joblocal = fields.size() > 1 ? fields.get(1).xpath("//dd/a/text()").get() : "";

            String pubdate = html.xpath("//span[@class=\"last-modified\"]/text()").get();
            String modtab = "";
            String city = qiuzhiCity.replaceAll("求职信息", "");



            /*if (noCertificate != null) {
                page.addTargetRequest(TEL_URL_ORIGIN + id);
            }*/


            FiveEightPersion persion = new FiveEightPersion();
            persion.set_id(id);
            persion.setTel(tel);
            persion.setAge(age);
            persion.setBasic_college(college);
            persion.setCity(city);
            persion.setJob_goal(jobgoal);

            persion.setJob_local(joblocal);

            persion.setJob_salary(salary);
            persion.setName(name);
            persion.setPubdate(pubdate);
            persion.setSex(sex);
            persion.setSiteurl(url);
            persion.setTitle(jobgoal);
            persion.setExperience(modtab);


            page.putField(persion.get_id(), persion);
        }
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