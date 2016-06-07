package cn.xiaomei.crawler.utils;

import cn.xiaomei.crawler.Constant;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;

/**
 * Created by chunli on 16/4/28.
 */
public class ImportYoutonggao {
    public static void main(String [] args){
       /* if(args.length<3)
        {
            System.err.println("cn.xiaomei.crawler.utils.ImportNuomiSH dir mongoip mongoport");
            return;
        }
        File dir=new File(args[0]);
        String host=args[1];
        int port=Integer.parseInt(args[2]);*/
        MongoCredential credential = MongoCredential.createCredential("xiaomei", "admin", "xmadmin".toCharArray());
        ServerAddress serverAddress = new ServerAddress("101.201.210.175", 27017);
        MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
        MongoCollection<Document> dbc=null;
        dbc = mongoClient.getDatabase("crawler").getCollection("tonggao");
        File  tonggao=new File("/tmp/down/");

            for (File jsonf : tonggao.listFiles(new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    if (name.indexOf("json") > -1) {
                        return true;
                    }
                    else
                        return false;
                }
            })) {
                try {
                    System.out.println(jsonf.getAbsolutePath());

                    String jsonc = FileUtils.readFileToString(jsonf);

                    Object ja1 =  JsonUtils.getPathObject(jsonc, "data");
                    Object status =  JsonUtils.getPathObject(jsonc, "status");

                    if(status.toString().equalsIgnoreCase("success")) {
                        System.out.println(((JSONObject) ja1).toString());
                        Document dbObject = Document.parse(((JSONObject) ja1).toString());

                        dbc.insertOne(dbObject);

                    }else {
                        System.out.println(status.toString());
                    }

                    /*if(JsonUtils.isValid(jsonc)) {
                        JSONArray ja = JsonUtils.getPathArray(jsonc, "data");
                        for (int idx = 0; idx < ja.length(); idx++) {
                            Document dbObject = Document.parse(ja.getJSONObject(idx).toString());
                            System.out.println(dbObject.toJson());
                        }
                    }*/
                    //dbc.insertMany();
                } catch (IOException e) {
                    // e.printStackTrace();
                }
            }

          mongoClient.close();
    }


}
