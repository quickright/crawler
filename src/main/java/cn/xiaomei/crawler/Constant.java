package cn.xiaomei.crawler;

import cn.xiaomei.crawler.db.bean.CityUrl;
import cn.xiaomei.crawler.db.bean.DpCityUrl;
import cn.xiaomei.crawler.db.bean.FECityUrl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chunli on 16/3/8.
 */
public  class Constant {
    public static final String QSite="http://m.diandianv.com";//http://7xrlok.com1.z0.glb.clouddn.com";
    public static final String QAk="_6oZxqstye3bijUyZTHg3dXXMmkzW0nyE6K37_Jo";
    public static final String QSk="xU38t53v3J7IUlFDJZ95rI08BTbqob8tRNpXwo83";


    private static final String CrawlerPath="/data03/apps/crawler/";
    private static final String CrawlerPicJobPath=CrawlerPath+"pic/job/";
    private static final String CrawlerMp4JobPath=CrawlerPath+"mp4/job/";
    private static final String CrawlerQiushiJobPath=CrawlerPath+"qiushi/job/";
    private static final String getCrawlerConfPath=CrawlerPath+"conf/";


    public static final String ChannelNuoMi="nuomi";
    public static final String ChannelMeituan="meituan";

    public static final String MongoUserName="xiaomei";
    public static final String MongoAdminDatabase="admin";
    public static final String MongoPW="xmadmin";
    public static final String MongoHosts="123.57.7.153";
    public static final int MongoPort=27017;
    public static final String MongoCountlyDatabase="countly";
    public static final String MongoTestDatabase="test";




    public static String getCrawlerMp4JobPath(){
        File pic=new File(CrawlerMp4JobPath);
        if(pic.isDirectory())
        {
            pic.mkdirs();
        }
        return CrawlerMp4JobPath;
    }
    public static String getCrawlerPicJobPath(){
        File pic=new File(CrawlerPicJobPath);
        if(pic.isDirectory())
        {
            pic.mkdirs();
        }
        return CrawlerPicJobPath;
    }
    public static String getCrawlerQiushiJobPath(){
        File pic=new File(CrawlerQiushiJobPath);
        if(pic.isDirectory())
        {
            pic.mkdirs();
        }
        return CrawlerQiushiJobPath;
    }
    public static String getCrawlerConfPath(){
        File pic=new File(getCrawlerConfPath);
        if(pic.isDirectory())
        {
            pic.mkdirs();
        }
        return getCrawlerConfPath;
    }

    public static String getCurrentDateTime(){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(new Date());
    }

    public static int get500(){
        int i=(int)(Math.random()*500)+100;
        return i;
    }

    public static List<DpCityUrl> getDianpingCityUrls() {
        List<DpCityUrl> cityurls = new ArrayList<DpCityUrl>();
        List<String> cityinfo=new ArrayList<String>();
        cityinfo.add("1~上海~shanghai~true");
        cityinfo.add("2~北京~beijing~true");
        cityinfo.add("3~杭州~hangzhou~true");
        cityinfo.add("4~广州~guangzhou~true");
        cityinfo.add("5~南京~nanjing~true");
        cityinfo.add("6~苏州~suzhou~true");
        cityinfo.add("7~深圳~shenzhen~true");
        cityinfo.add("8~成都~chengdu~true");
        cityinfo.add("9~重庆~chongqing~true");
        cityinfo.add("10~天津~tianjin~true");
        cityinfo.add("11~宁波~ningbo~true");


        cityinfo.add("11~宁波~ningbo~true");

        for(String city:cityinfo) {
            String [] cityarray=city.split("~");
            DpCityUrl dpCityUrl = new DpCityUrl(cityarray[0], DpCityUrl.getCanyinUrl() + "&locatecityid="+cityarray[0]+"&cityid="+cityarray[0], cityarray[1], cityarray[2],Boolean.parseBoolean(cityarray[3]));
            cityurls.add(dpCityUrl);
        }



        //cityurls.add(new CityUrl("http://m.api.dianping.com/searchshop.json?start=1&range=-1&sortid=0&locatecityid=1&cityid=1&callback=jsonp1&_=","上海","shanghai",false));
        //cityurls.add(new CityUrl("http://m.api.dianping.com/searchshop.json?start=1&range=-1&sortid=0&locatecityid=2&cityid=2&callback=jsonp1&_=","北京","beijing",false));
        /*cityurls.add(new CityUrl("http://m.api.dianping.com/searchshop.json?start=1&range=-1&sortid=0&locatecityid=3&cityid=3&callback=jsonp1&_=","杭州","hangzhou",false));
        cityurls.add(new CityUrl("http://m.api.dianping.com/searchshop.json?start=1&range=-1&sortid=0&locatecityid=4&cityid=4&callback=jsonp1&_=","广州","guangzhou",false));
        cityurls.add(new CityUrl("http://m.api.dianping.com/searchshop.json?start=1&range=-1&sortid=0&locatecityid=5&cityid=5&callback=jsonp1&_=","南京","nanjing",false));
        cityurls.add(new CityUrl("http://m.api.dianping.com/searchshop.json?start=1&range=-1&sortid=0&locatecityid=6&cityid=6&callback=jsonp1&_=","苏州","suzhou",false));
        cityurls.add(new CityUrl("http://m.api.dianping.com/searchshop.json?start=1&range=-1&sortid=0&locatecityid=7&cityid=7&callback=jsonp1&_=","深圳","shenzhen",true));
        cityurls.add(new CityUrl("http://m.api.dianping.com/searchshop.json?start=1&range=-1&sortid=0&locatecityid=8&cityid=8&callback=jsonp1&_=","成都","chengdu",false));
        cityurls.add(new CityUrl("http://m.api.dianping.com/searchshop.json?start=1&range=-1&sortid=0&locatecityid=9&cityid=9&callback=jsonp1&_=","重庆","chongqing",false));*/
        //cityurls.add(new CityUrl("http://m.api.dianping.com/searchshop.json?start=1&range=-1&sortid=0&locatecityid=10&cityid=10&callback=jsonp1&_=","天津","tianjin",false));
       // cityurls.add(new CityUrl("http://m.api.dianping.com/searchshop.json?start=1&range=-1&sortid=0&locatecityid=11&cityid=11&callback=jsonp1&_=","宁波","ningbo",false));
       /* cityurls.add(new CityUrl("http://www.dianping.com/search/category/1/10/","上海","shanghai",true));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/2/10/","北京","beijing",true));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/3/10/","杭州","hangzhou",true));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/4/10/","广州","guangzhou",true));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/5/10/","南京","nanjing",true));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/6/10/","苏州","suzhou",true));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/7/10/","深圳","shenzhen",true));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/8/10/","成都","chengdu",true));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/9/10/","重庆","chongqing",true));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/10/10/","天津","tianjin",true));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/11/10/","宁波","ningbo",true));*/

      /*  cityurls.add(new CityUrl("http://www.dianping.com/search/category/2/10/","天津","tianjin",true));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/2/10/","沈阳","shenyang",false));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/2/10/","大连","dalian",false));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/2/10/","长春","changchun",false));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/2/10/","哈尔滨","haerbin",false));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/2/10/","石家庄","shijiazhuang",false));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/2/10/","太原","taiyuan",false));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/2/10/","呼和浩特","huhehaote",false));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/2/10/","廊坊","langfang",false));
        cityurls.add(new CityUrl("http://www.dianping.com/search/category/2/10/","廊坊","langfang",false));

*/

        return cityurls;
    }
    public static List<FECityUrl> get58CityUrls(){

        List<FECityUrl> cityurls=new ArrayList<FECityUrl>();
        List<String> cityinfo=new ArrayList<String>();
        cityinfo.add("1~上海~sh~false");
        cityinfo.add("2~北京~bj~false");
        cityinfo.add("3~广州~gz~false");
        cityinfo.add("23~济南~jn~false");
        cityinfo.add("5~成都~cd~false");
        cityinfo.add("6~杭州~hz~false");
        cityinfo.add("7~南京~nj~false");
        cityinfo.add("8~天津~tj~false");

        cityinfo.add("10~重庆~cq~false");
        cityinfo.add("11~保定~bd~false");
        cityinfo.add("12~郑州~zz~false");
        cityinfo.add("13~西安~xa~false");
        cityinfo.add("14~沈阳~sy~false");
        cityinfo.add("15~福州~fz~false");
        cityinfo.add("16~泉州~qz~false");
        cityinfo.add("17~怀化~hh~false");
        cityinfo.add("18~合肥~hf~false");
        cityinfo.add("19~常州~cz~false");
        cityinfo.add("20~连云港~lyg~false");
        cityinfo.add("21~长沙~cs~false");
        //-------------------------------------------------------------------
        cityinfo.add("22~青岛~qd~false");
        cityinfo.add("9~武汉~wh~true");
        cityinfo.add("4~深圳~sz~true");
        cityinfo.add("24~烟台~yt~false");
        cityinfo.add("25~潍坊~wf~false");
        cityinfo.add("26~临沂~linyi~false");
        cityinfo.add("27~淄博~zb~false");
        for(String city:cityinfo) {
            String [] cityarray=city.split("~");
            FECityUrl feCityUrl = new FECityUrl(cityarray[0], cityarray[1], cityarray[2],Boolean.parseBoolean(cityarray[3]));
            cityurls.add(feCityUrl);
        }
       /* cityurls.add(new CityUrl("http://gz.58.com/zplvyoujiudian/","广州","gz",true));
        cityurls.add(new CityUrl("http://sz.58.com/zplvyoujiudian/","深圳","sz",true));
        cityurls.add(new CityUrl("http://cd.58.com/zplvyoujiudian/","成都","cd",true));
        cityurls.add(new CityUrl("http://hz.58.com/zplvyoujiudian/","杭州","hz",true));
        cityurls.add(new CityUrl("http://nj.58.com/zplvyoujiudian/","南京","nj",true));
        cityurls.add(new CityUrl("http://tj.58.com/zplvyoujiudian/","天津","tj",true));
        cityurls.add(new CityUrl("http://wh.58.com/zplvyoujiudian/","武汉","wh",true));
        cityurls.add(new CityUrl("http://cq.58.com/zplvyoujiudian/","重庆","cq",true));
        cityurls.add(new CityUrl("http://qd.58.com/zplvyoujiudian/","青岛","qd",true));
        cityurls.add(new CityUrl("http://jn.58.com/zplvyoujiudian/","济南","jn",true));
        cityurls.add(new CityUrl("http://yt.58.com/zplvyoujiudian/","烟台","yt",false));
        cityurls.add(new CityUrl("http://wf.58.com/zplvyoujiudian/","潍坊","wf",false));
        cityurls.add(new CityUrl("http://linyi.58.com/zplvyoujiudian/","临沂","linyi",false));
        cityurls.add(new CityUrl("http://zb.58.com/zplvyoujiudian/","淄博","zb",false));
        cityurls.add(new CityUrl("http://jining.58.com/zplvyoujiudian/","济宁","jining",false));
        cityurls.add(new CityUrl("http://ta.58.com/zplvyoujiudian/","泰安","ta",false));
        cityurls.add(new CityUrl("http://lc.58.com/zplvyoujiudian/","聊城","lc",false));
        cityurls.add(new CityUrl("http://weihai.58.com/zplvyoujiudian/","威海","weihai",false));
        cityurls.add(new CityUrl("http://zaozhuang.58.com/zplvyoujiudian/","枣庄","zaozhuang",false));*/
       /* cityinfo.add("~\"济宁\",\"jining\"~true");
        cityinfo.add("~\"泰安\",\"ta\"~true");
        cityinfo.add("~\"聊城\",\"lc\"~true");
        cityinfo.add("~\"威海\",\"weihai\"~true");
        cityinfo.add("~\"枣庄\",\"zaozhuang\"~true");*/

        /*cityurls.add(new FECityUrl("http://bj.58.com/zplvyoujiudian/","北京","bj",true));
        
        cityurls.add(new CityUrl("http://sh.58.com/zplvyoujiudian/","上海","sh",true));
        cityurls.add(new CityUrl("http://gz.58.com/zplvyoujiudian/","广州","gz",true));
        cityurls.add(new CityUrl("http://sz.58.com/zplvyoujiudian/","深圳","sz",true));
        cityurls.add(new CityUrl("http://cd.58.com/zplvyoujiudian/","成都","cd",true));
        cityurls.add(new CityUrl("http://hz.58.com/zplvyoujiudian/","杭州","hz",true));
        cityurls.add(new CityUrl("http://nj.58.com/zplvyoujiudian/","南京","nj",true));
        cityurls.add(new CityUrl("http://tj.58.com/zplvyoujiudian/","天津","tj",true));
        cityurls.add(new CityUrl("http://wh.58.com/zplvyoujiudian/","武汉","wh",true));
        cityurls.add(new CityUrl("http://cq.58.com/zplvyoujiudian/","重庆","cq",true));
        cityurls.add(new CityUrl("http://qd.58.com/zplvyoujiudian/","青岛","qd",true));
        cityurls.add(new CityUrl("http://jn.58.com/zplvyoujiudian/","济南","jn",true));
        cityurls.add(new CityUrl("http://yt.58.com/zplvyoujiudian/","烟台","yt",false));
        cityurls.add(new CityUrl("http://wf.58.com/zplvyoujiudian/","潍坊","wf",false));
        cityurls.add(new CityUrl("http://linyi.58.com/zplvyoujiudian/","临沂","linyi",false));
        cityurls.add(new CityUrl("http://zb.58.com/zplvyoujiudian/","淄博","zb",false));
        cityurls.add(new CityUrl("http://jining.58.com/zplvyoujiudian/","济宁","jining",false));
        cityurls.add(new CityUrl("http://ta.58.com/zplvyoujiudian/","泰安","ta",false));
        cityurls.add(new CityUrl("http://lc.58.com/zplvyoujiudian/","聊城","lc",false));
        cityurls.add(new CityUrl("http://weihai.58.com/zplvyoujiudian/","威海","weihai",false));
        cityurls.add(new CityUrl("http://zaozhuang.58.com/zplvyoujiudian/","枣庄","zaozhuang",false));
        cityurls.add(new CityUrl("http://dz.58.com/zplvyoujiudian/","德州","dz",false));
        cityurls.add(new CityUrl("http://rizhao.58.com/zplvyoujiudian/","日照","rizhao",false));
        cityurls.add(new CityUrl("http://dy.58.com/zplvyoujiudian/","东营","dy",false));
        cityurls.add(new CityUrl("http://heze.58.com/zplvyoujiudian/","菏泽","heze",false));
        cityurls.add(new CityUrl("http://bz.58.com/zplvyoujiudian/","滨州","bz",false));
        cityurls.add(new CityUrl("http://lw.58.com/zplvyoujiudian/","莱芜","lw",false));
        cityurls.add(new CityUrl("http://zhangqiu.58.com/zplvyoujiudian/","章丘","zhangqiu",false));
        cityurls.add(new CityUrl("http://kl.58.com/zplvyoujiudian/","垦利","kl",false));
        cityurls.add(new CityUrl("http://zc.58.com/zplvyoujiudian/","诸城","zc",false));
        cityurls.add(new CityUrl("http://shouguang.58.com/zplvyoujiudian/","寿光","shouguang",false));
        cityurls.add(new CityUrl("http://su.58.com/zplvyoujiudian/","苏州","su",true));
        cityurls.add(new CityUrl("http://nj.58.com/zplvyoujiudian/","南京","nj",false));
        cityurls.add(new CityUrl("http://wx.58.com/zplvyoujiudian/","无锡","wx",false));
        cityurls.add(new CityUrl("http://cz.58.com/zplvyoujiudian/","常州","cz",false));
        cityurls.add(new CityUrl("http://xz.58.com/zplvyoujiudian/","徐州","xz",false));
        cityurls.add(new CityUrl("http://nt.58.com/zplvyoujiudian/","南通","nt",false));
        cityurls.add(new CityUrl("http://yz.58.com/zplvyoujiudian/","扬州","yz",false));
        cityurls.add(new CityUrl("http://yancheng.58.com/zplvyoujiudian/","盐城","yancheng",false));
        cityurls.add(new CityUrl("http://ha.58.com/zplvyoujiudian/","淮安","ha",false));
        cityurls.add(new CityUrl("http://lyg.58.com/zplvyoujiudian/","连云港","lyg",false));
        cityurls.add(new CityUrl("http://taizhou.58.com/zplvyoujiudian/","泰州","taizhou",false));
        cityurls.add(new CityUrl("http://suqian.58.com/zplvyoujiudian/","宿迁","suqian",false));
        cityurls.add(new CityUrl("http://zj.58.com/zplvyoujiudian/","镇江","zj",false));
        cityurls.add(new CityUrl("http://shuyang.58.com/zplvyoujiudian/","沭阳","shuyang",false));
        cityurls.add(new CityUrl("http://dafeng.58.com/zplvyoujiudian/","大丰","dafeng",false));
        cityurls.add(new CityUrl("http://rugao.58.com/zplvyoujiudian/","如皋","rugao",false));
        cityurls.add(new CityUrl("http://qidong.58.com/zplvyoujiudian/","启东","qidong",false));
        cityurls.add(new CityUrl("http://liyang.58.com/zplvyoujiudian/","溧阳","liyang",false));
        cityurls.add(new CityUrl("http://haimen.58.com/zplvyoujiudian/","海门","haimen",false));
        cityurls.add(new CityUrl("http://donghai.58.com/zplvyoujiudian/","东海","donghai",false));
        cityurls.add(new CityUrl("http://yangzhong.58.com/zplvyoujiudian/","扬中","yangzhong",false));
        cityurls.add(new CityUrl("http://xinghuashi.58.com/zplvyoujiudian/","兴化","xinghuashi",false));
        cityurls.add(new CityUrl("http://xinyishi.58.com/zplvyoujiudian/","新沂","xinyishi",false));
        cityurls.add(new CityUrl("http://taixing.58.com/zplvyoujiudian/","泰兴","taixing",false));
        cityurls.add(new CityUrl("http://rudong.58.com/zplvyoujiudian/","如东","rudong",false));
        cityurls.add(new CityUrl("http://pizhou.58.com/zplvyoujiudian/","邳州","pizhou",false));
        cityurls.add(new CityUrl("http://xzpeixian.58.com/zplvyoujiudian/","沛县","xzpeixian",false));
        cityurls.add(new CityUrl("http://jingjiang.58.com/zplvyoujiudian/","靖江","jingjiang",false));
        cityurls.add(new CityUrl("http://jianhu.58.com/zplvyoujiudian/","建湖","jianhu",false));
        cityurls.add(new CityUrl("http://haian.58.com/zplvyoujiudian/","海安","haian",false));
        cityurls.add(new CityUrl("http://dongtai.58.com/zplvyoujiudian/","东台","dongtai",false));
        cityurls.add(new CityUrl("http://danyang.58.com/zplvyoujiudian/","丹阳","danyang",false));
        cityurls.add(new CityUrl("http://hz.58.com/zplvyoujiudian/","杭州","hz",true));
        cityurls.add(new CityUrl("http://nb.58.com/zplvyoujiudian/","宁波","nb",false));
        cityurls.add(new CityUrl("http://wz.58.com/zplvyoujiudian/","温州","wz",false));
        cityurls.add(new CityUrl("http://jh.58.com/zplvyoujiudian/","金华","jh",false));
        cityurls.add(new CityUrl("http://jx.58.com/zplvyoujiudian/","嘉兴","jx",false));
        cityurls.add(new CityUrl("http://tz.58.com/zplvyoujiudian/","台州","tz",false));
        cityurls.add(new CityUrl("http://sx.58.com/zplvyoujiudian/","绍兴","sx",false));
        cityurls.add(new CityUrl("http://huzhou.58.com/zplvyoujiudian/","湖州","huzhou",false));
        cityurls.add(new CityUrl("http://lishui.58.com/zplvyoujiudian/","丽水","lishui",false));
        cityurls.add(new CityUrl("http://quzhou.58.com/zplvyoujiudian/","衢州","quzhou",false));
        cityurls.add(new CityUrl("http://zhoushan.58.com/zplvyoujiudian/","舟山","zhoushan",false));
        cityurls.add(new CityUrl("http://yueqingcity.58.com/zplvyoujiudian/","乐清","yueqingcity",false));
        cityurls.add(new CityUrl("http://ruiancity.58.com/zplvyoujiudian/","瑞安","ruiancity",false));
        cityurls.add(new CityUrl("http://yiwu.58.com/zplvyoujiudian/","义乌","yiwu",false));
        cityurls.add(new CityUrl("http://yuyao.58.com/zplvyoujiudian/","余姚","yuyao",false));
        cityurls.add(new CityUrl("http://zhuji.58.com/zplvyoujiudian/","诸暨","zhuji",false));
        cityurls.add(new CityUrl("http://xiangshanxian.58.com/zplvyoujiudian/","象山","xiangshanxian",false));
        cityurls.add(new CityUrl("http://wenling.58.com/zplvyoujiudian/","温岭","wenling",false));
        cityurls.add(new CityUrl("http://tongxiang.58.com/zplvyoujiudian/","桐乡","tongxiang",false));
        cityurls.add(new CityUrl("http://cixi.58.com/zplvyoujiudian/","慈溪","cixi",false));
        cityurls.add(new CityUrl("http://changxing.58.com/zplvyoujiudian/","长兴","changxing",false));
        cityurls.add(new CityUrl("http://jiashanx.58.com/zplvyoujiudian/","嘉善","jiashanx",false));
        cityurls.add(new CityUrl("http://haining.58.com/zplvyoujiudian/","海宁","haining",false));
        cityurls.add(new CityUrl("http://deqing.58.com/zplvyoujiudian/","德清","deqing",false));
        cityurls.add(new CityUrl("http://hf.58.com/zplvyoujiudian/","合肥","hf",true));
        cityurls.add(new CityUrl("http://wuhu.58.com/zplvyoujiudian/","芜湖","wuhu",false));
        cityurls.add(new CityUrl("http://bengbu.58.com/zplvyoujiudian/","蚌埠","bengbu",false));
        cityurls.add(new CityUrl("http://fy.58.com/zplvyoujiudian/","阜阳","fy",false));
        cityurls.add(new CityUrl("http://hn.58.com/zplvyoujiudian/","淮南","hn",false));
        cityurls.add(new CityUrl("http://anqing.58.com/zplvyoujiudian/","安庆","anqing",false));
        cityurls.add(new CityUrl("http://suzhou.58.com/zplvyoujiudian/","宿州","suzhou",false));
        cityurls.add(new CityUrl("http://la.58.com/zplvyoujiudian/","六安","la",false));
        cityurls.add(new CityUrl("http://huaibei.58.com/zplvyoujiudian/","淮北","huaibei",false));
        cityurls.add(new CityUrl("http://chuzhou.58.com/zplvyoujiudian/","滁州","chuzhou",false));
        cityurls.add(new CityUrl("http://mas.58.com/zplvyoujiudian/","马鞍山","mas",false));
        cityurls.add(new CityUrl("http://tongling.58.com/zplvyoujiudian/","铜陵","tongling",false));
        cityurls.add(new CityUrl("http://xuancheng.58.com/zplvyoujiudian/","宣城","xuancheng",false));
        cityurls.add(new CityUrl("http://bozhou.58.com/zplvyoujiudian/","亳州","bozhou",false));
        cityurls.add(new CityUrl("http://huangshan.58.com/zplvyoujiudian/","黄山","huangshan",false));
        cityurls.add(new CityUrl("http://chizhou.58.com/zplvyoujiudian/","池州","chizhou",false));
        cityurls.add(new CityUrl("http://ch.58.com/zplvyoujiudian/","巢湖","ch",false));
        cityurls.add(new CityUrl("http://hexian.58.com/zplvyoujiudian/","和县","hexian",false));
        cityurls.add(new CityUrl("http://hq.58.com/zplvyoujiudian/","霍邱","hq",false));
        cityurls.add(new CityUrl("http://tongcheng.58.com/zplvyoujiudian/","桐城","tongcheng",false));
        cityurls.add(new CityUrl("http://ningguo.58.com/zplvyoujiudian/","宁国","ningguo",false));
        cityurls.add(new CityUrl("http://tianchang.58.com/zplvyoujiudian/","天长","tianchang",false));
        cityurls.add(new CityUrl("http://sz.58.com/zplvyoujiudian/","深圳","sz",false));
        cityurls.add(new CityUrl("http://gz.58.com/zplvyoujiudian/","广州","gz",false));
        cityurls.add(new CityUrl("http://dg.58.com/zplvyoujiudian/","东莞","dg",false));
        cityurls.add(new CityUrl("http://fs.58.com/zplvyoujiudian/","佛山","fs",false));
        cityurls.add(new CityUrl("http://zs.58.com/zplvyoujiudian/","中山","zs",false));
        cityurls.add(new CityUrl("http://zh.58.com/zplvyoujiudian/","珠海","zh",false));
        cityurls.add(new CityUrl("http://huizhou.58.com/zplvyoujiudian/","惠州","huizhou",false));
        cityurls.add(new CityUrl("http://jm.58.com/zplvyoujiudian/","江门","jm",false));
        cityurls.add(new CityUrl("http://st.58.com/zplvyoujiudian/","汕头","st",false));
        cityurls.add(new CityUrl("http://zhanjiang.58.com/zplvyoujiudian/","湛江","zhanjiang",false));
        cityurls.add(new CityUrl("http://zq.58.com/zplvyoujiudian/","肇庆","zq",false));
        cityurls.add(new CityUrl("http://mm.58.com/zplvyoujiudian/","茂名","mm",false));
        cityurls.add(new CityUrl("http://jy.58.com/zplvyoujiudian/","揭阳","jy",false));
        cityurls.add(new CityUrl("http://mz.58.com/zplvyoujiudian/","梅州","mz",false));
        cityurls.add(new CityUrl("http://qingyuan.58.com/zplvyoujiudian/","清远","qingyuan",false));
        cityurls.add(new CityUrl("http://yj.58.com/zplvyoujiudian/","阳江","yj",false));
        cityurls.add(new CityUrl("http://sg.58.com/zplvyoujiudian/","韶关","sg",false));
        cityurls.add(new CityUrl("http://heyuan.58.com/zplvyoujiudian/","河源","heyuan",false));
        cityurls.add(new CityUrl("http://yf.58.com/zplvyoujiudian/","云浮","yf",false));
        cityurls.add(new CityUrl("http://sw.58.com/zplvyoujiudian/","汕尾","sw",false));
        cityurls.add(new CityUrl("http://chaozhou.58.com/zplvyoujiudian/","潮州","chaozhou",false));
        cityurls.add(new CityUrl("http://taishan.58.com/zplvyoujiudian/","台山","taishan",false));
        cityurls.add(new CityUrl("http://yangchun.58.com/zplvyoujiudian/","阳春","yangchun",false));
        cityurls.add(new CityUrl("http://sd.58.com/zplvyoujiudian/","顺德","sd",false));
        cityurls.add(new CityUrl("http://huidong.58.com/zplvyoujiudian/","惠东","huidong",false));
        cityurls.add(new CityUrl("http://boluo.58.com/zplvyoujiudian/","博罗","boluo",false));
        cityurls.add(new CityUrl("http://fz.58.com/zplvyoujiudian/","福州","fz",true));
        cityurls.add(new CityUrl("http://xm.58.com/zplvyoujiudian/","厦门","xm",false));
        cityurls.add(new CityUrl("http://qz.58.com/zplvyoujiudian/","泉州","qz",false));
        cityurls.add(new CityUrl("http://pt.58.com/zplvyoujiudian/","莆田","pt",false));
        cityurls.add(new CityUrl("http://zhangzhou.58.com/zplvyoujiudian/","漳州","zhangzhou",false));
        cityurls.add(new CityUrl("http://nd.58.com/zplvyoujiudian/","宁德","nd",false));
        cityurls.add(new CityUrl("http://sm.58.com/zplvyoujiudian/","三明","sm",false));
        cityurls.add(new CityUrl("http://np.58.com/zplvyoujiudian/","南平","np",false));
        cityurls.add(new CityUrl("http://ly.58.com/zplvyoujiudian/","龙岩","ly",false));
        cityurls.add(new CityUrl("http://wuyishan.58.com/zplvyoujiudian/","武夷山","wuyishan",false));
        cityurls.add(new CityUrl("http://shishi.58.com/zplvyoujiudian/","石狮","shishi",false));
        cityurls.add(new CityUrl("http://jinjiangshi.58.com/zplvyoujiudian/","晋江","jinjiangshi",false));
        cityurls.add(new CityUrl("http://nananshi.58.com/zplvyoujiudian/","南安","nananshi",false));
        cityurls.add(new CityUrl("http://nn.58.com/zplvyoujiudian/","南宁","nn",true));
        cityurls.add(new CityUrl("http://liuzhou.58.com/zplvyoujiudian/","柳州","liuzhou",false));
        cityurls.add(new CityUrl("http://gl.58.com/zplvyoujiudian/","桂林","gl",false));
        cityurls.add(new CityUrl("http://yulin.58.com/zplvyoujiudian/","玉林","yulin",false));
        cityurls.add(new CityUrl("http://wuzhou.58.com/zplvyoujiudian/","梧州","wuzhou",false));
        cityurls.add(new CityUrl("http://bh.58.com/zplvyoujiudian/","北海","bh",false));
        cityurls.add(new CityUrl("http://gg.58.com/zplvyoujiudian/","贵港","gg",false));
        cityurls.add(new CityUrl("http://qinzhou.58.com/zplvyoujiudian/","钦州","qinzhou",false));
        cityurls.add(new CityUrl("http://baise.58.com/zplvyoujiudian/","百色","baise",false));
        cityurls.add(new CityUrl("http://hc.58.com/zplvyoujiudian/","河池","hc",false));
        cityurls.add(new CityUrl("http://lb.58.com/zplvyoujiudian/","来宾","lb",false));
        cityurls.add(new CityUrl("http://hezhou.58.com/zplvyoujiudian/","贺州","hezhou",false));
        cityurls.add(new CityUrl("http://fcg.58.com/zplvyoujiudian/","防城港","fcg",false));
        cityurls.add(new CityUrl("http://chongzuo.58.com/zplvyoujiudian/","崇左","chongzuo",false));
        cityurls.add(new CityUrl("http://haikou.58.com/zplvyoujiudian/","海口","haikou",true));
        cityurls.add(new CityUrl("http://sanya.58.com/zplvyoujiudian/","三亚","sanya",false));
        cityurls.add(new CityUrl("http://wzs.58.com/zplvyoujiudian/","五指山","wzs",false));
        cityurls.add(new CityUrl("http://sansha.58.com/zplvyoujiudian/","三沙","sansha",false));
        cityurls.add(new CityUrl("http://qh.58.com/zplvyoujiudian/","琼海","qh",false));
        cityurls.add(new CityUrl("http://zz.58.com/zplvyoujiudian/","郑州","zz",true));
        cityurls.add(new CityUrl("http://luoyang.58.com/zplvyoujiudian/","洛阳","luoyang",false));
        cityurls.add(new CityUrl("http://xx.58.com/zplvyoujiudian/","新乡","xx",false));
        cityurls.add(new CityUrl("http://ny.58.com/zplvyoujiudian/","南阳","ny",false));
        cityurls.add(new CityUrl("http://xc.58.com/zplvyoujiudian/","许昌","xc",false));
        cityurls.add(new CityUrl("http://pds.58.com/zplvyoujiudian/","平顶山","pds",false));
        cityurls.add(new CityUrl("http://ay.58.com/zplvyoujiudian/","安阳","ay",false));
        cityurls.add(new CityUrl("http://jiaozuo.58.com/zplvyoujiudian/","焦作","jiaozuo",false));
        cityurls.add(new CityUrl("http://sq.58.com/zplvyoujiudian/","商丘","sq",false));
        cityurls.add(new CityUrl("http://kaifeng.58.com/zplvyoujiudian/","开封","kaifeng",false));
        cityurls.add(new CityUrl("http://puyang.58.com/zplvyoujiudian/","濮阳","puyang",false));
        cityurls.add(new CityUrl("http://zk.58.com/zplvyoujiudian/","周口","zk",false));
        cityurls.add(new CityUrl("http://xy.58.com/zplvyoujiudian/","信阳","xy",false));
        cityurls.add(new CityUrl("http://zmd.58.com/zplvyoujiudian/","驻马店","zmd",false));
        cityurls.add(new CityUrl("http://luohe.58.com/zplvyoujiudian/","漯河","luohe",false));
        cityurls.add(new CityUrl("http://smx.58.com/zplvyoujiudian/","三门峡","smx",false));
        cityurls.add(new CityUrl("http://hb.58.com/zplvyoujiudian/","鹤壁","hb",false));
        cityurls.add(new CityUrl("http://jiyuan.58.com/zplvyoujiudian/","济源","jiyuan",false));
        cityurls.add(new CityUrl("http://mg.58.com/zplvyoujiudian/","明港","mg",false));
        cityurls.add(new CityUrl("http://yanling.58.com/zplvyoujiudian/","鄢陵","yanling",false));
        cityurls.add(new CityUrl("http://yuzhou.58.com/zplvyoujiudian/","禹州","yuzhou",false));
        cityurls.add(new CityUrl("http://changge.58.com/zplvyoujiudian/","长葛","changge",false));
        cityurls.add(new CityUrl("http://wh.58.com/zplvyoujiudian/","武汉","wh",false));
        cityurls.add(new CityUrl("http://yc.58.com/zplvyoujiudian/","宜昌","yc",false));
        cityurls.add(new CityUrl("http://xf.58.com/zplvyoujiudian/","襄阳","xf",false));
        cityurls.add(new CityUrl("http://jingzhou.58.com/zplvyoujiudian/","荆州","jingzhou",false));
        cityurls.add(new CityUrl("http://shiyan.58.com/zplvyoujiudian/","十堰","shiyan",false));
        cityurls.add(new CityUrl("http://hshi.58.com/zplvyoujiudian/","黄石","hshi",false));
        cityurls.add(new CityUrl("http://xiaogan.58.com/zplvyoujiudian/","孝感","xiaogan",false));
        cityurls.add(new CityUrl("http://hg.58.com/zplvyoujiudian/","黄冈","hg",false));
        cityurls.add(new CityUrl("http://es.58.com/zplvyoujiudian/","恩施","es",false));
        cityurls.add(new CityUrl("http://jingmen.58.com/zplvyoujiudian/","荆门","jingmen",false));
        cityurls.add(new CityUrl("http://xianning.58.com/zplvyoujiudian/","咸宁","xianning",false));
        cityurls.add(new CityUrl("http://ez.58.com/zplvyoujiudian/","鄂州","ez",false));
        cityurls.add(new CityUrl("http://suizhou.58.com/zplvyoujiudian/","随州","suizhou",false));
        cityurls.add(new CityUrl("http://qianjiang.58.com/zplvyoujiudian/","潜江","qianjiang",false));
        cityurls.add(new CityUrl("http://tm.58.com/zplvyoujiudian/","天门","tm",false));
        cityurls.add(new CityUrl("http://xiantao.58.com/zplvyoujiudian/","仙桃","xiantao",false));
        cityurls.add(new CityUrl("http://snj.58.com/zplvyoujiudian/","神农架","snj",false));
        cityurls.add(new CityUrl("http://yidou.58.com/zplvyoujiudian/","宜都","yidou",false));
        cityurls.add(new CityUrl("http://cs.58.com/zplvyoujiudian/","长沙","cs",true));
        cityurls.add(new CityUrl("http://zhuzhou.58.com/zplvyoujiudian/","株洲","zhuzhou",false));
        cityurls.add(new CityUrl("http://yiyang.58.com/zplvyoujiudian/","益阳","yiyang",false));
        cityurls.add(new CityUrl("http://changde.58.com/zplvyoujiudian/","常德","changde",false));
        cityurls.add(new CityUrl("http://hy.58.com/zplvyoujiudian/","衡阳","hy",false));
        cityurls.add(new CityUrl("http://xiangtan.58.com/zplvyoujiudian/","湘潭","xiangtan",false));
        cityurls.add(new CityUrl("http://yy.58.com/zplvyoujiudian/","岳阳","yy",false));
        cityurls.add(new CityUrl("http://chenzhou.58.com/zplvyoujiudian/","郴州","chenzhou",false));
        cityurls.add(new CityUrl("http://shaoyang.58.com/zplvyoujiudian/","邵阳","shaoyang",false));
        cityurls.add(new CityUrl("http://hh.58.com/zplvyoujiudian/","怀化","hh",false));
        cityurls.add(new CityUrl("http://yongzhou.58.com/zplvyoujiudian/","永州","yongzhou",false));
        cityurls.add(new CityUrl("http://ld.58.com/zplvyoujiudian/","娄底","ld",false));
        cityurls.add(new CityUrl("http://xiangxi.58.com/zplvyoujiudian/","湘西","xiangxi",false));
        cityurls.add(new CityUrl("http://zjj.58.com/zplvyoujiudian/","张家界","zjj",false));
        cityurls.add(new CityUrl("http://nc.58.com/zplvyoujiudian/","南昌","nc",true));
        cityurls.add(new CityUrl("http://ganzhou.58.com/zplvyoujiudian/","赣州","ganzhou",false));
        cityurls.add(new CityUrl("http://jj.58.com/zplvyoujiudian/","九江","jj",false));
        cityurls.add(new CityUrl("http://yichun.58.com/zplvyoujiudian/","宜春","yichun",false));
        cityurls.add(new CityUrl("http://ja.58.com/zplvyoujiudian/","吉安","ja",false));
        cityurls.add(new CityUrl("http://sr.58.com/zplvyoujiudian/","上饶","sr",false));
        cityurls.add(new CityUrl("http://px.58.com/zplvyoujiudian/","萍乡","px",false));
        cityurls.add(new CityUrl("http://fuzhou.58.com/zplvyoujiudian/","抚州","fuzhou",false));
        cityurls.add(new CityUrl("http://jdz.58.com/zplvyoujiudian/","景德镇","jdz",false));
        cityurls.add(new CityUrl("http://xinyu.58.com/zplvyoujiudian/","新余","xinyu",false));
        cityurls.add(new CityUrl("http://yingtan.58.com/zplvyoujiudian/","鹰潭","yingtan",false));
        cityurls.add(new CityUrl("http://yxx.58.com/zplvyoujiudian/","永新","yxx",false));
        cityurls.add(new CityUrl("http://sy.58.com/zplvyoujiudian/","沈阳","sy",true));
        cityurls.add(new CityUrl("http://dl.58.com/zplvyoujiudian/","大连","dl",false));
        cityurls.add(new CityUrl("http://as.58.com/zplvyoujiudian/","鞍山","as",false));
        cityurls.add(new CityUrl("http://jinzhou.58.com/zplvyoujiudian/","锦州","jinzhou",false));
        cityurls.add(new CityUrl("http://fushun.58.com/zplvyoujiudian/","抚顺","fushun",false));
        cityurls.add(new CityUrl("http://yk.58.com/zplvyoujiudian/","营口","yk",false));
        cityurls.add(new CityUrl("http://pj.58.com/zplvyoujiudian/","盘锦","pj",false));
        cityurls.add(new CityUrl("http://cy.58.com/zplvyoujiudian/","朝阳","cy",false));
        cityurls.add(new CityUrl("http://dandong.58.com/zplvyoujiudian/","丹东","dandong",false));
        cityurls.add(new CityUrl("http://liaoyang.58.com/zplvyoujiudian/","辽阳","liaoyang",false));
        cityurls.add(new CityUrl("http://benxi.58.com/zplvyoujiudian/","本溪","benxi",false));
        cityurls.add(new CityUrl("http://hld.58.com/zplvyoujiudian/","葫芦岛","hld",false));
        cityurls.add(new CityUrl("http://tl.58.com/zplvyoujiudian/","铁岭","tl",false));
        cityurls.add(new CityUrl("http://fx.58.com/zplvyoujiudian/","阜新","fx",false));
        cityurls.add(new CityUrl("http://pld.58.com/zplvyoujiudian/","庄河","pld",false));
        cityurls.add(new CityUrl("http://wfd.58.com/zplvyoujiudian/","瓦房店","wfd",false));
        cityurls.add(new CityUrl("http://hrb.58.com/zplvyoujiudian/","哈尔滨","hrb",true));
        cityurls.add(new CityUrl("http://dq.58.com/zplvyoujiudian/","大庆","dq",false));
        cityurls.add(new CityUrl("http://qqhr.58.com/zplvyoujiudian/","齐齐哈尔","qqhr",false));
        cityurls.add(new CityUrl("http://mdj.58.com/zplvyoujiudian/","牡丹江","mdj",false));
        cityurls.add(new CityUrl("http://suihua.58.com/zplvyoujiudian/","绥化","suihua",false));
        cityurls.add(new CityUrl("http://jms.58.com/zplvyoujiudian/","佳木斯","jms",false));
        cityurls.add(new CityUrl("http://jixi.58.com/zplvyoujiudian/","鸡西","jixi",false));
        cityurls.add(new CityUrl("http://sys.58.com/zplvyoujiudian/","双鸭山","sys",false));
        cityurls.add(new CityUrl("http://hegang.58.com/zplvyoujiudian/","鹤岗","hegang",false));
        cityurls.add(new CityUrl("http://heihe.58.com/zplvyoujiudian/","黑河","heihe",false));
        cityurls.add(new CityUrl("http://yich.58.com/zplvyoujiudian/","伊春","yich",false));
        cityurls.add(new CityUrl("http://qth.58.com/zplvyoujiudian/","七台河","qth",false));
        cityurls.add(new CityUrl("http://dxal.58.com/zplvyoujiudian/","大兴安岭","dxal",false));
        cityurls.add(new CityUrl("http://cc.58.com/zplvyoujiudian/","长春","cc",true));
        cityurls.add(new CityUrl("http://jl.58.com/zplvyoujiudian/","吉林","jl",false));
        cityurls.add(new CityUrl("http://sp.58.com/zplvyoujiudian/","四平","sp",false));
        cityurls.add(new CityUrl("http://yanbian.58.com/zplvyoujiudian/","延边","yanbian",false));
        cityurls.add(new CityUrl("http://songyuan.58.com/zplvyoujiudian/","松原","songyuan",false));
        cityurls.add(new CityUrl("http://bc.58.com/zplvyoujiudian/","白城","bc",false));
        cityurls.add(new CityUrl("http://th.58.com/zplvyoujiudian/","通化","th",false));
        cityurls.add(new CityUrl("http://baishan.58.com/zplvyoujiudian/","白山","baishan",false));
        cityurls.add(new CityUrl("http://liaoyuan.58.com/zplvyoujiudian/","辽源","liaoyuan",false));
        cityurls.add(new CityUrl("http://cd.58.com/zplvyoujiudian/","成都","cd",false));
        cityurls.add(new CityUrl("http://mianyang.58.com/zplvyoujiudian/","绵阳","mianyang",false));
        cityurls.add(new CityUrl("http://deyang.58.com/zplvyoujiudian/","德阳","deyang",false));
        cityurls.add(new CityUrl("http://nanchong.58.com/zplvyoujiudian/","南充","nanchong",false));
        cityurls.add(new CityUrl("http://yb.58.com/zplvyoujiudian/","宜宾","yb",false));
        cityurls.add(new CityUrl("http://zg.58.com/zplvyoujiudian/","自贡","zg",false));
        cityurls.add(new CityUrl("http://ls.58.com/zplvyoujiudian/","乐山","ls",false));
        cityurls.add(new CityUrl("http://luzhou.58.com/zplvyoujiudian/","泸州","luzhou",false));
        cityurls.add(new CityUrl("http://dazhou.58.com/zplvyoujiudian/","达州","dazhou",false));
        cityurls.add(new CityUrl("http://scnj.58.com/zplvyoujiudian/","内江","scnj",false));
        cityurls.add(new CityUrl("http://suining.58.com/zplvyoujiudian/","遂宁","suining",false));
        cityurls.add(new CityUrl("http://panzhihua.58.com/zplvyoujiudian/","攀枝花","panzhihua",false));
        cityurls.add(new CityUrl("http://ms.58.com/zplvyoujiudian/","眉山","ms",false));
        cityurls.add(new CityUrl("http://ga.58.com/zplvyoujiudian/","广安","ga",false));
        cityurls.add(new CityUrl("http://zy.58.com/zplvyoujiudian/","资阳","zy",false));
        cityurls.add(new CityUrl("http://liangshan.58.com/zplvyoujiudian/","凉山","liangshan",false));
        cityurls.add(new CityUrl("http://guangyuan.58.com/zplvyoujiudian/","广元","guangyuan",false));
        cityurls.add(new CityUrl("http://ya.58.com/zplvyoujiudian/","雅安","ya",false));
        cityurls.add(new CityUrl("http://bazhong.58.com/zplvyoujiudian/","巴中","bazhong",false));
        cityurls.add(new CityUrl("http://ab.58.com/zplvyoujiudian/","阿坝","ab",false));
        cityurls.add(new CityUrl("http://ganzi.58.com/zplvyoujiudian/","甘孜","ganzi",false));
        cityurls.add(new CityUrl("http://km.58.com/zplvyoujiudian/","昆明","km",true));
        cityurls.add(new CityUrl("http://qj.58.com/zplvyoujiudian/","曲靖","qj",false));
        cityurls.add(new CityUrl("http://dali.58.com/zplvyoujiudian/","大理","dali",false));
        cityurls.add(new CityUrl("http://honghe.58.com/zplvyoujiudian/","红河","honghe",false));
        cityurls.add(new CityUrl("http://yx.58.com/zplvyoujiudian/","玉溪","yx",false));
        cityurls.add(new CityUrl("http://lj.58.com/zplvyoujiudian/","丽江","lj",false));
        cityurls.add(new CityUrl("http://ws.58.com/zplvyoujiudian/","文山","ws",false));
        cityurls.add(new CityUrl("http://cx.58.com/zplvyoujiudian/","楚雄","cx",false));
        cityurls.add(new CityUrl("http://bn.58.com/zplvyoujiudian/","西双版纳","bn",false));
        cityurls.add(new CityUrl("http://zt.58.com/zplvyoujiudian/","昭通","zt",false));
        cityurls.add(new CityUrl("http://dh.58.com/zplvyoujiudian/","德宏","dh",false));
        cityurls.add(new CityUrl("http://pe.58.com/zplvyoujiudian/","普洱","pe",false));
        cityurls.add(new CityUrl("http://bs.58.com/zplvyoujiudian/","保山","bs",false));
        cityurls.add(new CityUrl("http://lincang.58.com/zplvyoujiudian/","临沧","lincang",false));
        cityurls.add(new CityUrl("http://diqing.58.com/zplvyoujiudian/","迪庆","diqing",false));
        cityurls.add(new CityUrl("http://nujiang.58.com/zplvyoujiudian/","怒江","nujiang",false));
        cityurls.add(new CityUrl("http://gy.58.com/zplvyoujiudian/","贵阳","gy",true));
        cityurls.add(new CityUrl("http://zunyi.58.com/zplvyoujiudian/","遵义","zunyi",false));
        cityurls.add(new CityUrl("http://qdn.58.com/zplvyoujiudian/","黔东南","qdn",false));
        cityurls.add(new CityUrl("http://qn.58.com/zplvyoujiudian/","黔南","qn",false));
        cityurls.add(new CityUrl("http://lps.58.com/zplvyoujiudian/","六盘水","lps",false));
        cityurls.add(new CityUrl("http://bijie.58.com/zplvyoujiudian/","毕节","bijie",false));
        cityurls.add(new CityUrl("http://tr.58.com/zplvyoujiudian/","铜仁","tr",false));
        cityurls.add(new CityUrl("http://anshun.58.com/zplvyoujiudian/","安顺","anshun",false));
        cityurls.add(new CityUrl("http://qxn.58.com/zplvyoujiudian/","黔西南","qxn",false));
        cityurls.add(new CityUrl("http://lasa.58.com/zplvyoujiudian/","拉萨","lasa",true));
        cityurls.add(new CityUrl("http://rkz.58.com/zplvyoujiudian/","日喀则","rkz",false));
        cityurls.add(new CityUrl("http://sn.58.com/zplvyoujiudian/","山南","sn",false));
        cityurls.add(new CityUrl("http://linzhi.58.com/zplvyoujiudian/","林芝","linzhi",false));
        cityurls.add(new CityUrl("http://changdu.58.com/zplvyoujiudian/","昌都","changdu",false));
        cityurls.add(new CityUrl("http://nq.58.com/zplvyoujiudian/","那曲","nq",false));
        cityurls.add(new CityUrl("http://al.58.com/zplvyoujiudian/","阿里","al",false));
        cityurls.add(new CityUrl("http://sjz.58.com/zplvyoujiudian/","石家庄","sjz",true));
        cityurls.add(new CityUrl("http://bd.58.com/zplvyoujiudian/","保定","bd",false));
        cityurls.add(new CityUrl("http://ts.58.com/zplvyoujiudian/","唐山","ts",false));
        cityurls.add(new CityUrl("http://lf.58.com/zplvyoujiudian/","廊坊","lf",false));
        cityurls.add(new CityUrl("http://hd.58.com/zplvyoujiudian/","邯郸","hd",false));
        cityurls.add(new CityUrl("http://qhd.58.com/zplvyoujiudian/","秦皇岛","qhd",false));
        cityurls.add(new CityUrl("http://cangzhou.58.com/zplvyoujiudian/","沧州","cangzhou",false));
        cityurls.add(new CityUrl("http://xt.58.com/zplvyoujiudian/","邢台","xt",false));
        cityurls.add(new CityUrl("http://hs.58.com/zplvyoujiudian/","衡水","hs",false));
        cityurls.add(new CityUrl("http://zjk.58.com/zplvyoujiudian/","张家口","zjk",false));
        cityurls.add(new CityUrl("http://chengde.58.com/zplvyoujiudian/","承德","chengde",false));
        cityurls.add(new CityUrl("http://dingzhou.58.com/zplvyoujiudian/","定州","dingzhou",false));
        cityurls.add(new CityUrl("http://gt.58.com/zplvyoujiudian/","馆陶","gt",false));
        cityurls.add(new CityUrl("http://zhangbei.58.com/zplvyoujiudian/","张北","zhangbei",false));
        cityurls.add(new CityUrl("http://zx.58.com/zplvyoujiudian/","赵县","zx",false));
        cityurls.add(new CityUrl("http://zd.58.com/zplvyoujiudian/","正定","zd",false));
        cityurls.add(new CityUrl("http://ty.58.com/zplvyoujiudian/","太原","ty",true));
        cityurls.add(new CityUrl("http://linfen.58.com/zplvyoujiudian/","临汾","linfen",false));
        cityurls.add(new CityUrl("http://dt.58.com/zplvyoujiudian/","大同","dt",false));
        cityurls.add(new CityUrl("http://yuncheng.58.com/zplvyoujiudian/","运城","yuncheng",false));
        cityurls.add(new CityUrl("http://jz.58.com/zplvyoujiudian/","晋中","jz",false));
        cityurls.add(new CityUrl("http://changzhi.58.com/zplvyoujiudian/","长治","changzhi",false));
        cityurls.add(new CityUrl("http://jincheng.58.com/zplvyoujiudian/","晋城","jincheng",false));
        cityurls.add(new CityUrl("http://yq.58.com/zplvyoujiudian/","阳泉","yq",false));
        cityurls.add(new CityUrl("http://lvliang.58.com/zplvyoujiudian/","吕梁","lvliang",false));
        cityurls.add(new CityUrl("http://xinzhou.58.com/zplvyoujiudian/","忻州","xinzhou",false));
        cityurls.add(new CityUrl("http://shuozhou.58.com/zplvyoujiudian/","朔州","shuozhou",false));
        cityurls.add(new CityUrl("http://linyixian.58.com/zplvyoujiudian/","临猗","linyixian",false));
        cityurls.add(new CityUrl("http://qingxu.58.com/zplvyoujiudian/","清徐","qingxu",false));
        cityurls.add(new CityUrl("http://hu.58.com/zplvyoujiudian/","呼和浩特","hu",true));
        cityurls.add(new CityUrl("http://bt.58.com/zplvyoujiudian/","包头","bt",false));
        cityurls.add(new CityUrl("http://chifeng.58.com/zplvyoujiudian/","赤峰","chifeng",false));
        cityurls.add(new CityUrl("http://erds.58.com/zplvyoujiudian/","鄂尔多斯","erds",false));
        cityurls.add(new CityUrl("http://tongliao.58.com/zplvyoujiudian/","通辽","tongliao",false));
        cityurls.add(new CityUrl("http://hlbe.58.com/zplvyoujiudian/","呼伦贝尔","hlbe",false));
        cityurls.add(new CityUrl("http://bycem.58.com/zplvyoujiudian/","巴彦淖尔市","bycem",false));
        cityurls.add(new CityUrl("http://wlcb.58.com/zplvyoujiudian/","乌兰察布","wlcb",false));
        cityurls.add(new CityUrl("http://xl.58.com/zplvyoujiudian/","锡林郭勒盟","xl",false));
        cityurls.add(new CityUrl("http://xam.58.com/zplvyoujiudian/","兴安盟","xam",false));
        cityurls.add(new CityUrl("http://wuhai.58.com/zplvyoujiudian/","乌海","wuhai",false));
        cityurls.add(new CityUrl("http://alsm.58.com/zplvyoujiudian/","阿拉善盟","alsm",false));
        cityurls.add(new CityUrl("http://hlr.58.com/zplvyoujiudian/","海拉尔","hlr",false));
        cityurls.add(new CityUrl("http://xa.58.com/zplvyoujiudian/","西安","xa",true));
        cityurls.add(new CityUrl("http://xianyang.58.com/zplvyoujiudian/","咸阳","xianyang",false));
        cityurls.add(new CityUrl("http://baoji.58.com/zplvyoujiudian/","宝鸡","baoji",false));
        cityurls.add(new CityUrl("http://wn.58.com/zplvyoujiudian/","渭南","wn",false));
        cityurls.add(new CityUrl("http://hanzhong.58.com/zplvyoujiudian/","汉中","hanzhong",false));
        cityurls.add(new CityUrl("http://yl.58.com/zplvyoujiudian/","榆林","yl",false));
        cityurls.add(new CityUrl("http://yanan.58.com/zplvyoujiudian/","延安","yanan",false));
        cityurls.add(new CityUrl("http://ankang.58.com/zplvyoujiudian/","安康","ankang",false));
        cityurls.add(new CityUrl("http://sl.58.com/zplvyoujiudian/","商洛","sl",false));
        cityurls.add(new CityUrl("http://tc.58.com/zplvyoujiudian/","铜川","tc",false));
        cityurls.add(new CityUrl("http://xj.58.com/zplvyoujiudian/","乌鲁木齐","xj",true));
        cityurls.add(new CityUrl("http://changji.58.com/zplvyoujiudian/","昌吉","changji",false));
        cityurls.add(new CityUrl("http://bygl.58.com/zplvyoujiudian/","巴音郭楞","bygl",false));
        cityurls.add(new CityUrl("http://yili.58.com/zplvyoujiudian/","伊犁","yili",false));
        cityurls.add(new CityUrl("http://aks.58.com/zplvyoujiudian/","阿克苏","aks",false));
        cityurls.add(new CityUrl("http://ks.58.com/zplvyoujiudian/","喀什","ks",false));
        cityurls.add(new CityUrl("http://hami.58.com/zplvyoujiudian/","哈密","hami",false));
        cityurls.add(new CityUrl("http://klmy.58.com/zplvyoujiudian/","克拉玛依","klmy",false));
        cityurls.add(new CityUrl("http://betl.58.com/zplvyoujiudian/","博尔塔拉","betl",false));
        cityurls.add(new CityUrl("http://tlf.58.com/zplvyoujiudian/","吐鲁番","tlf",false));
        cityurls.add(new CityUrl("http://ht.58.com/zplvyoujiudian/","和田","ht",false));
        cityurls.add(new CityUrl("http://shz.58.com/zplvyoujiudian/","石河子","shz",false));
        cityurls.add(new CityUrl("http://kzls.58.com/zplvyoujiudian/","克孜勒苏","kzls",false));
        cityurls.add(new CityUrl("http://ale.58.com/zplvyoujiudian/","阿拉尔","ale",false));
        cityurls.add(new CityUrl("http://wjq.58.com/zplvyoujiudian/","五家渠","wjq",false));
        cityurls.add(new CityUrl("http://tmsk.58.com/zplvyoujiudian/","图木舒克","tmsk",false));
        cityurls.add(new CityUrl("http://lz.58.com/zplvyoujiudian/","兰州","lz",true));
        cityurls.add(new CityUrl("http://tianshui.58.com/zplvyoujiudian/","天水","tianshui",false));
        cityurls.add(new CityUrl("http://by.58.com/zplvyoujiudian/","白银","by",false));
        cityurls.add(new CityUrl("http://qingyang.58.com/zplvyoujiudian/","庆阳","qingyang",false));
        cityurls.add(new CityUrl("http://pl.58.com/zplvyoujiudian/","平凉","pl",false));
        cityurls.add(new CityUrl("http://jq.58.com/zplvyoujiudian/","酒泉","jq",false));
        cityurls.add(new CityUrl("http://zhangye.58.com/zplvyoujiudian/","张掖","zhangye",false));
        cityurls.add(new CityUrl("http://wuwei.58.com/zplvyoujiudian/","武威","wuwei",false));
        cityurls.add(new CityUrl("http://dx.58.com/zplvyoujiudian/","定西","dx",false));
        cityurls.add(new CityUrl("http://jinchang.58.com/zplvyoujiudian/","金昌","jinchang",false));
        cityurls.add(new CityUrl("http://ln.58.com/zplvyoujiudian/","陇南","ln",false));
        cityurls.add(new CityUrl("http://linxia.58.com/zplvyoujiudian/","临夏","linxia",false));
        cityurls.add(new CityUrl("http://jyg.58.com/zplvyoujiudian/","嘉峪关","jyg",false));
        cityurls.add(new CityUrl("http://gn.58.com/zplvyoujiudian/","甘南","gn",false));
        cityurls.add(new CityUrl("http://yinchuan.58.com/zplvyoujiudian/","银川","yinchuan",true));
        cityurls.add(new CityUrl("http://wuzhong.58.com/zplvyoujiudian/","吴忠","wuzhong",false));
        cityurls.add(new CityUrl("http://szs.58.com/zplvyoujiudian/","石嘴山","szs",false));
        cityurls.add(new CityUrl("http://zw.58.com/zplvyoujiudian/","中卫","zw",false));
        cityurls.add(new CityUrl("http://guyuan.58.com/zplvyoujiudian/","固原","guyuan",false));
        cityurls.add(new CityUrl("http://xn.58.com/zplvyoujiudian/","西宁","xn",true));
        cityurls.add(new CityUrl("http://hx.58.com/zplvyoujiudian/","海西","hx",false));
        cityurls.add(new CityUrl("http://haibei.58.com/zplvyoujiudian/","海北","haibei",false));
        cityurls.add(new CityUrl("http://guoluo.58.com/zplvyoujiudian/","果洛","guoluo",false));
        cityurls.add(new CityUrl("http://haidong.58.com/zplvyoujiudian/","海东","haidong",false));
        cityurls.add(new CityUrl("http://huangnan.58.com/zplvyoujiudian/","黄南","huangnan",false));
        cityurls.add(new CityUrl("http://ys.58.com/zplvyoujiudian/","玉树","ys",false));
        cityurls.add(new CityUrl("http://hainan.58.com/zplvyoujiudian/","海南","hainan",false));
        cityurls.add(new CityUrl("http://hk.58.com/zplvyoujiudian/","香港","hk",false));
        cityurls.add(new CityUrl("http://am.58.com/zplvyoujiudian/","澳门","am",false));
        cityurls.add(new CityUrl("http://tw.58.com/zplvyoujiudian/","台湾","tw",false));
        cityurls.add(new CityUrl("http://diaoyudao.58.com/","钓鱼岛","diaoyudao",false));
        cityurls.add(new CityUrl("http://cn.58.com/zplvyoujiudian/","其他","cn",false));*/
        return cityurls;
    }


}
