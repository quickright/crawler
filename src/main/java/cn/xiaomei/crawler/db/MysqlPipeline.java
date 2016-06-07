package cn.xiaomei.crawler.db;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.utils.HttpClientUtils;
import cn.xiaomei.crawler.utils.User;
import org.json.JSONArray;
import org.json.JSONObject;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.*;
import java.util.*;

/**
 * Created by chunli on 16/3/6.
 */
public class MysqlPipeline implements Pipeline {
    //0 text jpg,1 gif,2 mp4
    private int type;
    public MysqlPipeline(int type){
        this.type=type;
    }
    public void process(ResultItems resultItems, Task task) {
        Map<String,Object> result=resultItems.getAll();

        if(type==0)
        {
            for(Object entrys:result.values()){
               try {
                    addJpg((List<Map<String,String>>)entrys);
                } catch (SQLException e) {
                   e.printStackTrace();
               }
            }
        }else if(type==1)
        {
            for(Object entrys:result.values()){
                try {
                    addGif((List<Map<String, String>>) entrys);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else if(type==2)
        {
            for(Object entrys:result.values()){
                try {
                    addMp4((List<Map<String, String>>) entrys);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private void addJpg(List<Map<String,String>> vs) throws SQLException {
        Connection con = null; //定义一个MYSQL链接对象
        Statement stmt=null; //创建声明
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            con = DriverManager.getConnection("jdbc:mysql://xiaomei2015.mysql.rds.aliyuncs.com:3306/tpx520", "xiaomei2015", "f0FzzVm521qnxmkVGNscFgR42a5yaplP"); //链接本地MYSQL

            stmt = con.createStatement();
            for(Map<String,String> v:vs ) {
                //是否存在
                String isexist="select  old_id from x520_diandian_library_topic_main where old_id='" + v.get("id") + "'";
                ResultSet res = stmt.executeQuery(isexist);

                //新增一条数据
                int ret_id;
                if (!res.next()) {

                    //帖子数据源表
                    int topic_id = stmt.executeUpdate("INSERT INTO x520_diandian_library_topic_main(user_id, title,content,count_share,count_good,count_review,count_view,address,old_id,from_type,type,add_time) " +
                            " VALUES ('"+ User.getRandomUid()+"','" + v.get("title") + "','" + v.get("content") + "','" + v.get("count_share") + "','" + v.get("count_good") + "','"
                            + v.get("count_review") + "','" + v.get("count_view") + "','" + v.get("address") + "','" + v.get("id") + "','" + v.get("from_type") + "','" + type + "','" + v.get("add_time") + "')");

                    //--#帖子数据源 图片  GIF 视频表
               /* stmt.executeUpdate("INSERT INTO x520_diandian_library_topic_photo_main(user_id, topic_id,url,type,add_time,weight,height) " +
                        " VALUES ('123456','"+topic_id+"','"+v.get("url")+"','"+type+"','"+v.get("add_time")+"','"+v.get("height")+"','"
                        +v.get("weight")+"')");*/
                }else {
                    ret_id = res.getInt(1);
                    System.out.println(ret_id);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            if(stmt!=null&& !stmt.isClosed())
            {
                stmt.close();
            }
            if(con!=null&& !con.isClosed())
            {
                con.close();
            }
        }
    }

    private void addGif(List<Map<String,String>> vs) throws SQLException {
        Connection con = null; //定义一个MYSQL链接对象
        Statement stmt=null; //创建声明
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            con = DriverManager.getConnection("jdbc:mysql://xiaomei2015.mysql.rds.aliyuncs.com:3306/tpx520", "xiaomei2015", "f0FzzVm521qnxmkVGNscFgR42a5yaplP"); //链接本地MYSQL
            con.setAutoCommit(true);
            stmt = con.createStatement();
            for(Map<String,String> v:vs ) {
                //新增一条数据
                String isexist="select topic_id from x520_diandian_library_topic_main where old_id='" + v.get("id") + "'";
               System.out.println(isexist);
                ResultSet res = stmt.executeQuery(isexist);
                int ret_id;
                if (!res.next()) {

                    //帖子数据源表
                    stmt.executeUpdate("INSERT INTO x520_diandian_library_topic_main(user_id, title,content,count_share,count_good,count_review,count_view,address,old_id,from_type,type,add_time) " +
                            " VALUES ('"+ User.getRandomUid()+"','" + v.get("title") + "','" + v.get("content") + "','" + v.get("count_share") + "','" + v.get("count_good") + "','"
                            + v.get("count_review") + "','" + v.get("count_view") + "','" + v.get("address") + "','" + v.get("id") + "','" + v.get("from_type") + "','" + v.get("type") + "','" + v.get("add_time") + "')");

                     res = stmt.executeQuery(isexist);
                     int topic_id=0;
                     if(res.next()) {
                         topic_id = res.getInt(1);
                     }
                    System.out.println("topic_id="+topic_id);

                            //--#帖子数据源 图片  GIF 视频表
                            stmt.executeUpdate("INSERT INTO x520_diandian_library_topic_photo_main(user_id, topic_id,url,type,add_time,weight,height,old_id,view_count) " +
                                    " VALUES ('"+ User.getRandomUid()+"','" + topic_id + "','" + v.get("url") + "','" + v.get("type") + "','" + v.get("add_time") + "','0','0','"+v.get("id")+"',"+ Constant.get500()+")");
                }else {
                    ret_id = res.getInt(1);
                    System.out.println(ret_id);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            if(stmt!=null&& !stmt.isClosed())
            {
                stmt.close();
            }
            if(con!=null&& !con.isClosed())
            {
                con.close();
            }
        }
    }
    private void addMp4(List<Map<String,String>> vs) throws SQLException {
        Connection con = null; //定义一个MYSQL链接对象
        Statement stmt=null; //创建声明
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            con = DriverManager.getConnection("jdbc:mysql://xiaomei2015.mysql.rds.aliyuncs.com:3306/tpx520", "xiaomei2015", "f0FzzVm521qnxmkVGNscFgR42a5yaplP"); //链接本地MYSQL
            con.setAutoCommit(true);
            stmt = con.createStatement();
            for(Map<String,String> v:vs ) {
                //新增一条数据
                String isexist="select topic_id from x520_diandian_library_topic_main where old_id='" + v.get("id") + "'";
                System.out.println(isexist);
                ResultSet res = stmt.executeQuery(isexist);
                if (!res.next()) {

                    //帖子数据源表
                    stmt.executeUpdate("INSERT INTO x520_diandian_library_topic_main(user_id, title,content,count_share,count_good,count_review,count_view,address,old_id,from_type,type,add_time) " +
                            " VALUES ('"+ User.getRandomUid()+"','" + v.get("title") + "','" + v.get("content") + "','" + v.get("count_share") + "','" + v.get("count_good") + "','"
                            + v.get("count_review") + "','" + v.get("count_view") + "','" + v.get("address") + "','" + v.get("id") + "','" + v.get("from_type") + "','" + v.get("type") + "','" + v.get("add_time") + "')");

                    res = stmt.executeQuery(isexist);
                    int topic_id=0;
                    if(res.next()) {
                        topic_id = res.getInt(1);
                    }
                    //--#帖子数据源 图片  GIF 视频表
                    stmt.executeUpdate("INSERT INTO x520_diandian_library_topic_photo_main(user_id, topic_id,url,type,add_time,weight,height,old_id,view_count) " +
                            " VALUES ('"+ User.getRandomUid()+"','" + topic_id + "','" + v.get("url") + "','" + v.get("type") + "','" + v.get("add_time") + "',480,264,'"+v.get("id")+"',"+ Constant.get500()+")");
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            if(stmt!=null&& !stmt.isClosed())
            {
                stmt.close();
            }
            if(con!=null&& !con.isClosed())
            {
                con.close();
            }
        }
    }

    public void updateMp42(Set<String> ids)  {
        Connection con = null; //定义一个MYSQL链接对象
        Statement stmt=null; //创建声明
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            con = DriverManager.getConnection("jdbc:mysql://xiaomei2015.mysql.rds.aliyuncs.com:3306/tpx520", "xiaomei2015", "f0FzzVm521qnxmkVGNscFgR42a5yaplP"); //链接本地MYSQL
            con.setAutoCommit(true);
            String isexist1="select old_id from x520_diandian_library_topic_photo_main where type=2";
            stmt = con.createStatement();

            ResultSet res1 = stmt.executeQuery(isexist1);
            Set<String> ii=new HashSet<String>();
            while(res1.next())
            {
                ii.add(res1.getString(1));
            }
            res1.close();;

            for(String id:ii ) {
                    //--#帖子数据源 图片  GIF 视频表
                String isexist="select url from x520_diandian_library_topic_photo_main where old_id='" +id+ "'";
                ResultSet res = stmt.executeQuery(isexist);
                if (res.next()) {
                    String url = res.getString(1);
                    JSONObject obj = new JSONObject(HttpClientUtils.response(url+"?avinfo"));
                    JSONArray streams=obj.getJSONArray("streams");
                    int width=streams.getJSONObject(0).getInt("width");
                    int height=streams.getJSONObject(0).getInt("height");
                    JSONObject ccd=obj.getJSONObject("format");
                    double duration=ccd.getDouble("duration");
                    String suburl="?vframe/jpg/offset/10/w/"+width+"/h/"+height;
                    stmt.executeUpdate("update x520_diandian_library_topic_photo_main set small_url=CONCAT(url,'"+suburl+"'), duration="+duration+",weight="+width+",height="+height+" where old_id="+id);

                }

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try {
                if(stmt!=null&& !stmt.isClosed())
                {
                    stmt.close();
                }
                if(con!=null&& !con.isClosed())
                {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void updateMp4(Set<String> ids)  {
        Connection con = null; //定义一个MYSQL链接对象
        Statement stmt=null; //创建声明
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            con = DriverManager.getConnection("jdbc:mysql://xiaomei2015.mysql.rds.aliyuncs.com:3306/tpx520", "xiaomei2015", "f0FzzVm521qnxmkVGNscFgR42a5yaplP"); //链接本地MYSQL
            con.setAutoCommit(true);
            stmt = con.createStatement();
            for(String id:ids ) {
                //--#帖子数据源 图片  GIF 视频表
                String isexist="select url from x520_diandian_library_topic_photo_main where old_id='" +id+ "'";
                ResultSet res = stmt.executeQuery(isexist);
                if (res.next()) {
                    String url = res.getString(1);
                    JSONObject obj = new JSONObject(HttpClientUtils.response(url+"?avinfo"));
                    JSONArray streams=obj.getJSONArray("streams");
                    int width=streams.getJSONObject(0).getInt("width");
                    int height=streams.getJSONObject(0).getInt("height");
                    JSONObject ccd=obj.getJSONObject("format");
                    double duration=ccd.getDouble("duration");
                    String suburl="?vframe/jpg/offset/10/w/"+width+"/h/"+height;
                    stmt.executeUpdate("update x520_diandian_library_topic_photo_main set small_url=CONCAT(url,'"+suburl+"'), duration="+duration+",weight="+width+",height="+height+" where old_id="+id);

                }

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try {
                if(stmt!=null&& !stmt.isClosed())
                {
                    stmt.close();
                }
                if(con!=null&& !con.isClosed())
                {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String [] args){
        Set<String> url=new HashSet<String>();
        url.add("17452578");
        url.add("17478372");
        MysqlPipeline pi=new MysqlPipeline(-1);
        //pi.updatePic(url);
        pi.updateMp42(url);
    }
    public void updatePic(Set<String> ids)  {
        Connection con = null; //定义一个MYSQL链接对象
        Statement stmt=null; //创建声明
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            con = DriverManager.getConnection("jdbc:mysql://xiaomei2015.mysql.rds.aliyuncs.com:3306/tpx520", "xiaomei2015", "f0FzzVm521qnxmkVGNscFgR42a5yaplP"); //链接本地MYSQL
            con.setAutoCommit(true);
            stmt = con.createStatement();

            for(String id:ids ) {
                //--#帖子数据源 图片  GIF 视频表
                String isexist="select url from x520_diandian_library_topic_photo_main where old_id='" +id+ "'";
                ResultSet res = stmt.executeQuery(isexist);
                if (res.next()) {
                    String url = res.getString(1);
                    JSONObject obj = new JSONObject(HttpClientUtils.response(url+"?imageInfo"));
                    int width=obj.getInt("width");
                    int height=obj.getInt("height");
                    //double duration=ccd.getDouble("duration");
                   stmt.executeUpdate("update x520_diandian_library_topic_photo_main set weight="+width+",height="+height+" where old_id="+id);

                }
                res.close();

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try {
                if(stmt!=null&& !stmt.isClosed())
                {
                    stmt.close();
                }
                if(con!=null&& !con.isClosed())
                {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
