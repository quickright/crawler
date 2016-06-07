package cn.xiaomei.crawler.db;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.utils.HttpClientUtils;
import cn.xiaomei.crawler.utils.User;
import org.apache.commons.lang.math.RandomUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by chunli on 16/3/6.
 */
public class MysqlQiushibaikePipeline implements Pipeline {
    //0 text jpg,1 gif,2 mp4

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> result = resultItems.getAll();


        for (Object entrys : result.values()) {
            try {
                addConstant((List<Map<String, String>>) entrys);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    private void addConstant(List<Map<String, String>> vs) throws SQLException {
        Connection con = null; //定义一个MYSQL链接对象
        Statement stmt = null; //创建声明
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            con = DriverManager.getConnection("jdbc:mysql://xiaomei2015.mysql.rds.aliyuncs.com:3306/tpx520", "xiaomei2015", "f0FzzVm521qnxmkVGNscFgR42a5yaplP"); //链接本地MYSQL
            con.setAutoCommit(true);
            stmt = con.createStatement();
            for (Map<String, String> v : vs) {
                //新增一条数据
                String isexist = "select topic_id from x520_diandian_library_topic_main where old_id='" + v.get("id") + "' and from_type='" + v.get("from_type") + "'";
                System.out.println(isexist);
                ResultSet res = stmt.executeQuery(isexist);
                int ret_id;
                if (!res.next()) {
                    //帖子数据源表
                    stmt.executeUpdate("INSERT INTO x520_diandian_library_topic_main(user_id, title,content,count_share,count_good,count_review,count_view,address,old_id,from_type,type,add_time) " +
                            " VALUES ('" + User.getRandomUid() + "','" + v.get("title") + "','" + v.get("content") + "','" + v.get("count_share") + "','" + v.get("count_good") + "','"
                            + v.get("count_review") + "','" + v.get("count_view") + "','" + v.get("address") + "','" + v.get("id") + "','" + v.get("from_type") + "','" + v.get("type") + "','" + v.get("add_time") + "')");

                    if (v.get("pic_type") != null) {
                        res = stmt.executeQuery(isexist);
                        int topic_id = 0;
                        if (res.next()) {
                            topic_id = res.getInt(1);
                        }
                        //--#帖子数据源 图片  GIF 视频表
                        stmt.executeUpdate("INSERT INTO x520_diandian_library_topic_photo_main(user_id, topic_id,url,type,add_time,weight,height,old_id,view_count) " +
                                " VALUES ('" + User.getRandomUid() + "','" + topic_id + "','" + v.get("url") + "','" + v.get("type") + "','" + v.get("add_time") + "','0','0','" + v.get("id") + "',"+ Constant.get500()+")");
                    }
                } else {
                    ret_id = res.getInt(1);
                    System.out.println(ret_id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
    }


    public static void main(String[] args) {
        Set<String> url = new HashSet<String>();
        url.add("17452578");
        url.add("17478372");
        MysqlQiushibaikePipeline pi = new MysqlQiushibaikePipeline();
        //pi.updatePic(url);
        pi.update(url, "");
    }

    public void update(Set<String> ids, String url) {
        Connection con = null; //定义一个MYSQL链接对象
        Statement stmt = null; //创建声明
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            con = DriverManager.getConnection("jdbc:mysql://xiaomei2015.mysql.rds.aliyuncs.com:3306/tpx520", "xiaomei2015", "f0FzzVm521qnxmkVGNscFgR42a5yaplP"); //链接本地MYSQL
            con.setAutoCommit(true);
            stmt = con.createStatement();

            for (String id : ids) {
                //--#帖子数据源 图片  GIF 视频表
                String isexist = "select url,topic_id from x520_diandian_library_topic_photo_main where old_id in ( select old_id from x520_diandian_library_topic_main where old_id='" + id + "' and from_type='" + url + "' ) ";
                ResultSet res = stmt.executeQuery(isexist);
                if (res.next()) {
                    String picurl = res.getString(1);
                    String topic_id = res.getString(2);
                    JSONObject obj = new JSONObject(HttpClientUtils.response(picurl + "?imageInfo"));
                    int width = obj.getInt("width");
                    int height = obj.getInt("height");
                    String format = obj.getString("format");
                    String type="0";
                    if(format.equalsIgnoreCase("gif"))
                        type="1";
                    //double duration=ccd.getDouble("duration");
                    stmt.executeUpdate("update x520_diandian_library_topic_main set type="+type+" where old_id='" + id + "' and from_type='" + url + "'");
                    stmt.executeUpdate("update x520_diandian_library_topic_photo_main set type="+type+",weight=" + width + ",height=" + height + " where topic_id=" + topic_id);

                }
                res.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
