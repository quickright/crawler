package cn.xiaomei.crawler.db;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

/**
 * Created by chunli on 16/4/15.
 */
public class MongoConnect {
    public static MongoConnect getInstance=new MongoConnect();
    private  MongoClient mongoClient = null;;
    private MongoConnect(){
        if(mongoClient==null) {
            MongoCredential credential = MongoCredential.createCredential("xiaomei", "admin", "xmadmin".toCharArray());
            //ServerAddress serverAddress = new ServerAddress("123.57.7.153", 27017);
            ServerAddress serverAddress = new ServerAddress("101.201.210.175", 27017);
            mongoClient = new MongoClient(serverAddress, Arrays.asList(credential), new MongoClientOptions.Builder()
                    .socketKeepAlive(true) // 是否保持长链接
                    .connectionsPerHost(200) // 最大连接数
                    .minConnectionsPerHost(20)// 最小连接数
                    .build());
        }
    }

    public MongoCollection<Document> getCollect(String db,String collectname){

        MongoDatabase mongoDatabase = mongoClient.getDatabase(db);
        MongoCollection<Document> dbc=mongoDatabase.getCollection(collectname);
        return dbc;
    }

    public void close() {
        if(mongoClient!=null)
            mongoClient.close();
    }
}
