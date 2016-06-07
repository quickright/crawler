package cn.xiaomei.crawler.db;

import cn.xiaomei.crawler.db.bean.PageProgress;
import com.alibaba.fastjson.JSON;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.FilePipeline;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by chunli on 16/3/6.
 */
public class ProxyPipeline extends FilePipeline {

    //todo
    private Logger logger = LoggerFactory.getLogger(getClass());

    public ProxyPipeline(){
       //this.setPath(filepath);
    }
    @Override
    public void process(ResultItems resultItems, Task task) {
        MongoCredential credential = MongoCredential.createCredential("xiaomei", "admin", "xmadmin".toCharArray());
        ServerAddress serverAddress = new ServerAddress("123.57.7.153", 27017);
        MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
        MongoCollection<Document> dbc=null;
        Map<String, Object> result = resultItems.getAll();


        for (Map.Entry<String,Object> entry : result.entrySet()) {

                System.out.println(JSON.toJSONString(entry.getValue()));
                dbc = mongoClient.getDatabase("crawler").getCollection("proxy");
                Document dbObject = Document.parse(JSON.toJSONString(entry.getValue()));
                Document ids = Document.parse("{id:\""+entry.getKey()+"\"}");
                dbc.replaceOne(ids, dbObject,new UpdateOptions().upsert(true));
               // dbc.updateOne("", dbObject);


        }
        mongoClient.close();
       /* String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(getFile(path + DigestUtils.md5Hex(resultItems.getRequest().getUrl()) + ".json")));
            printWriter.write(JSON.toJSONString(resultItems.getAll()));
            printWriter.close();
        } catch (IOException e) {
            logger.warn("write file error", e);
        }*/
    }
}

