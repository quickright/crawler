package cn.xiaomei.crawler.utils;

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
import java.util.Arrays;

/**
 * Created by chunli on 16/4/28.
 */
public class ImportNuomiContact {
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
        dbc = mongoClient.getDatabase("crawler").getCollection("nuomi_contect");
        File  tonggao=new File(args[0]);

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

                    if(JsonUtils.isValid(jsonc)) {
                        JSONArray ja1 = JsonUtils.getPathArray(jsonc, "res", "data");

                        for (Object jo : ja1) {
                            System.out.println(jo.toString());
                            Document dbObject = Document.parse(jo.toString());

                            dbObject.append("city", args[1]);
                            dbc.insertOne(dbObject);

                        }
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
