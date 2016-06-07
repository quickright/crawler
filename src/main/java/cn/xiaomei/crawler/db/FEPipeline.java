package cn.xiaomei.crawler.db;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.FilePipeline;

import java.util.*;

/**
 * Created by chunli on 16/3/6.
 */
public class FEPipeline extends FilePipeline {

    //todo
    private Logger logger = LoggerFactory.getLogger(getClass());

    public FEPipeline(){
       //this.setPath(filepath);
    }
    @Override
    public void process(ResultItems resultItems, Task task) {

        MongoCollection<Document> dbc=null;
        Map<String, Object> result = resultItems.getAll();
        MongoConnect mongoUtils= MongoConnect.getInstance;
        for (Map.Entry<String,Object> entry : result.entrySet()) {
            if(entry.getKey().indexOf("x")!=-1) {
                dbc = mongoUtils.getCollect("test","fiveeight_job_new");
                Document dbObject = Document.parse(JSON.toJSONString(entry.getValue()));
                Document ids = Document.parse("{id:\""+entry.getKey()+"\"}");
                dbc.replaceOne(ids, dbObject, new UpdateOptions().upsert(true));
            }else if(entry.getKey().equalsIgnoreCase("page"))
            {
                dbc = mongoUtils.getCollect("test","fiveeight_url");
                Document dbObject = Document.parse(JSON.toJSONString(entry.getValue()));
                dbc.insertOne(dbObject);
            }else
            {
                dbc = mongoUtils.getCollect("test","fiveeight_ent_new");
                Document dbObject = Document.parse(JSON.toJSONString(entry.getValue()));
                 Document ids = Document.parse("{id:\""+entry.getKey()+"\"}");
                dbc.replaceOne(ids, dbObject,new UpdateOptions().upsert(true));
             }

        }
        mongoUtils.close();
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

