package cn.xiaomei.crawler.proxy;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.db.MongoConnect;
import cn.xiaomei.crawler.db.bean.ProxyBean;
import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;


/**
 * Created by chunli on 16/4/15.
 */
public abstract class BaseProxy {

    public static void main(String [] args){
       /* class a extends BaseProxy{}
        BaseProxy baseProxy=new a();
        FindIterable<Document> iterableproxybeans=baseProxy.getRandomProxy();
        List<ProxyBean> proxybeans=new ArrayList<ProxyBean>();
        for(Document document:iterableproxybeans){
            ProxyBean proxybean=new ProxyBean();
            proxybean.setAddtime(document.getString("addtime"));
            proxybean.setModifytime(document.getString("modifytime"));
            proxybean.setDomain(document.getString("domain"));
            //proxybean.setIsabled(document.getBoolean("isabled"));
            proxybean.setTimes(document.getInteger("times"));
            proxybean.setPort(document.getString("port"));
            proxybean.setIp(document.getString("ip"));
            proxybean.setRandom(document.getString("random"));
            System.out.println(document);
            proxybeans.add(proxybean);
        }*/
        ProxyBean proxyBean =new ProxyBean();
        proxyBean.setDomain("www.163.com");
        proxyBean.setIp("127.0.0.1");
        proxyBean.setPort("8001");
        //proxyBean.setAddtime(Constant.getCurrentDateTime());
        proxyBean.setModifytime(Constant.getCurrentDateTime());
        MongoConnect mongoConnect=MongoConnect.getInstance;
        MongoCollection<Document> proxy=mongoConnect.getCollect("crawler","test");
            Document dbObject = Document.parse(JSON.toJSONString(proxyBean));
            Document ids = Document.parse("{ip:\""+proxyBean.getIp()+"\",port:\""+proxyBean.getPort()+"\",domain:\""+proxyBean.getDomain()+"\"}");
            System.out.println(ids.toJson());
            proxy.replaceOne(ids,dbObject,new UpdateOptions().upsert(true));

        mongoConnect.close();

    }
    /*
    * 保存代理
     */
    public void saveProxy(List<ProxyBean> proxyBeanList)
    {
        MongoConnect mongoConnect=MongoConnect.getInstance;
        MongoCollection<Document> proxy=mongoConnect.getCollect("crawler","proxy");
        for (ProxyBean proxyBean : proxyBeanList) {
            Document dbObject = Document.parse(JSON.toJSONString(proxyBean));
            Document ids = Document.parse("{ip:\""+proxyBean.getIp()+"\",port:\""+proxyBean.getPort()+"\",domain:\""+proxyBean.getDomain()+"\"}");
            System.out.println(ids.toJson());
            proxy.replaceOne(ids,dbObject,new UpdateOptions().upsert(true));
        }
        mongoConnect.close();
    }

    /*
  * 更新代理
   */
    public void updateProxy(ProxyBean proxyBean)
    {
        MongoConnect mongoConnect=MongoConnect.getInstance;
        MongoCollection<Document> proxy=mongoConnect.getCollect("crawler","proxy");

            Document dbObject = Document.parse(JSON.toJSONString(proxyBean));
            Document ids = Document.parse("{ip:\""+proxyBean.getIp()+"\",port:\""+proxyBean.getPort()+"\",domain:\""+proxyBean.getDomain()+"\"}");
            System.out.println(ids.toJson());
            proxy.replaceOne(ids,dbObject,new UpdateOptions().upsert(false));

        mongoConnect.close();
    }


    /*
  * 保存代理
   */
    public void removeProxy(String domain,String ip,String port)
    {
        MongoConnect mongoConnect=MongoConnect.getInstance;
        MongoCollection<Document> proxy=mongoConnect.getCollect("crawler","proxy");
            Document ids = Document.parse("{ip:\""+ip+"\",port:\""+port+"\",domain:\""+domain+"\"}");
            System.out.println(ids.toJson());
            proxy.deleteOne(ids);
        mongoConnect.close();
    }
    /*
    * 获取有效的随机代理
     */
    public FindIterable<Document> getRandomProxy()
    {

        MongoConnect mongoConnect=MongoConnect.getInstance;
       /* MongoCollection<Document> proxy=mongoConnect.getCollect("crawler","proxy");
            Document dbObject = Document.parse(JSON.toJSONString(proxyBean));
            Document ids = Document.parse("{ip:\""+proxyBean.getIp()+"\",port:\""+proxyBean.getPort()+"\",domain:\""+proxyBean.getDomain()+"\"}");
            System.out.println(ids.toJson());
            proxy.replaceOne(ids,dbObject);*/
        MongoCollection<Document> proxy=mongoConnect.getCollect("crawler","proxy");
        String random=ProxyBean.Random();
        System.out.println(random);
        FindIterable<Document> iterable = proxy.find(gt("random",random));
        if(iterable.first()==null){
            iterable=proxy.find(lte("random",random+""));
            }

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });

        mongoConnect.close();
        return iterable;
    }

    /*
    * 获取有效的代理
     */
    public FindIterable<Document> getProxy(int limit)
    {

        MongoConnect mongoConnect=MongoConnect.getInstance;
       /* MongoCollection<Document> proxy=mongoConnect.getCollect("crawler","proxy");
            Document dbObject = Document.parse(JSON.toJSONString(proxyBean));
            Document ids = Document.parse("{ip:\""+proxyBean.getIp()+"\",port:\""+proxyBean.getPort()+"\",domain:\""+proxyBean.getDomain()+"\"}");
            System.out.println(ids.toJson());
            proxy.replaceOne(ids,dbObject);*/
        MongoCollection<Document> proxy=mongoConnect.getCollect("crawler","proxy");
        String random=ProxyBean.Random();
        System.out.println(random);
        FindIterable<Document> iterable = proxy.find(gt("random",random));
        if(iterable.first()==null){
            iterable=proxy.find().limit(limit);
        }

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });

        mongoConnect.close();
        return iterable;
    }
}
