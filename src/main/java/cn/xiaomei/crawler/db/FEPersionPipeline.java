package cn.xiaomei.crawler.db;

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
public class FEPersionPipeline extends FilePipeline {

    //todo
    private Logger logger = LoggerFactory.getLogger(getClass());

    public FEPersionPipeline(){
       //this.setPath(filepath);
    }
    @Override
    public void process(ResultItems resultItems, Task task) {
        MongoConnect mongoConnect=MongoConnect.getInstance;
        MongoCollection<Document> dbc=null;
        Map<String, Object> result = resultItems.getAll();


        for (Map.Entry<String,Object> entry : result.entrySet()) {
            if(entry.getKey().equalsIgnoreCase("page"))
            {
                dbc = mongoConnect.getCollect("test","fiveeight_url");
                Document dbObject = Document.parse(JSON.toJSONString(entry.getValue()));

               // dbc.updateOne("", dbObject);
            }else
            {
                dbc = mongoConnect.getCollect("test","fiveeight_persion_1");
                Document ids = Document.parse("{id:\""+entry.getKey()+"\"}");
                Document dbObject = Document.parse(JSON.toJSONString(entry.getValue()));
                dbc.replaceOne(ids, dbObject,new UpdateOptions().upsert(true));
            }

        }
        mongoConnect.close();
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

