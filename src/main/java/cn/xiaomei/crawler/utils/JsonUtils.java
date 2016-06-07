package cn.xiaomei.crawler.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chunli on 16/3/22.
 */
public class JsonUtils {

    public static void main(String [] args){
        //System.out.println(getPathObject("{\"id\":8503936,\"pos\":1}", "id"));
        Set<String> urls=new HashSet<String>();
        urls.add("2");
        urls.add("2");
        urls.add("1");
        urls.add("2");
        System.out.println(new ArrayList(urls));
        //getPathArray(HttpClientUtils.response("http://www.nuomi.com/pcindex/main/shopchain?dealId=3815535"),"shop");
    }

    public static Object getPathObject(String json,String path) {
        JSONObject jsonobj = new JSONObject(json);
        Object obj = jsonobj.get(path);
        return obj;
    }

    public static JSONObject getPathObject(String json,String ppath,String cpath) {
        JSONObject jsonobj = new JSONObject(json);
        JSONObject obj = jsonobj.getJSONObject(ppath).getJSONObject(cpath);
        return obj;
    }

  /*  public static String getPathObject(String json,String path) {
        System.out.println(json);
        JSONObject obj = new JSONObject(json);
        JSONObject pageName = obj.getJSONObject("data");
        System.out.println(pageName.get("count"));
        JSONArray arr= obj.getJSONObject("data").getJSONArray("shop");
        System.out.println(arr);
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("merchant_id");
            System.out.println(post_id);

        }
        return "";
    }*/
    public static JSONArray getPathArray(String json,String  path) {
        JSONObject obj = new JSONObject(json);
        JSONArray arr= obj.getJSONArray(path);
        /*System.out.println(arr);
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("merchant_id");
            System.out.println(post_id);

        }*/
        return arr;
    }

    public static JSONArray getPathArray(String json,String ppath,String  cpath) {
        JSONObject obj = new JSONObject(json);
        JSONArray arr= obj.getJSONObject(ppath).getJSONArray(cpath);
        /*System.out.println(arr);
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("merchant_id");
            System.out.println(post_id);

        }*/
        return arr;
    }

    public static boolean isValid(String jsonstr){
        boolean isValid=true;
        try{
            JSONObject jsonobj = new JSONObject(jsonstr);
        }
        catch(Exception jse){
            isValid=false;
        }
        return isValid;
    }

}
