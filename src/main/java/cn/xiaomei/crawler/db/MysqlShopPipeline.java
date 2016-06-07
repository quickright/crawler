package cn.xiaomei.crawler.db;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.utils.HttpClientUtils;
import cn.xiaomei.crawler.utils.User;
import cn.xiaomei.crawler.db.bean.Shop;

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
public class MysqlShopPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> result = resultItems.getAll();


        for (Object entrys : result.values()) {
            try {
                addConstant((Shop) entrys);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    private void addConstant(Shop shop) throws SQLException {
        Connection con = null; //定义一个MYSQL链接对象
        Statement stmt = null; //创建声明
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            con = DriverManager.getConnection("jdbc:mysql://xiaomei2015.mysql.rds.aliyuncs.com:3306/tpx520", "xiaomei2015", "f0FzzVm521qnxmkVGNscFgR42a5yaplP"); //链接本地MYSQL
            con.setAutoCommit(true);
            stmt = con.createStatement();

                    //新增一条数据
                    String isexist = "select old_company_id from x520_xiaomei_commercial_data_main where old_company_id='" + shop.getShop_id() + "' and channel='" + shop.getChannel() + "'";
                    //System.out.println(isexist);
                    ResultSet res = stmt.executeQuery(isexist);

                    if (!res.next()) {
                        //帖子数据源表
                        stmt.executeUpdate("INSERT INTO x520_xiaomei_commercial_data_main(channel,title,old_company_id,address,lat,lng,content_title,content_tel,sales_count,review_count,price,city,district,area,industry, url_info,add_time) " +
                                " VALUES ('" + shop.getChannel() + "','" + shop.getName() + "'," + shop.getShop_id() + ",'" + shop.getAddress() + "','" + shop.getLatitude() + "','"
                                + shop.getLongitude() + "','','" + shop.getPhone() + "','" + shop.getSold() + "','" + shop.getComment()+"','"+shop.getPrice() + "','" + shop.getCity_id()+"','"+shop.getDistrict_id() + "','','','" + shop.getLink() + "','" + Constant.getCurrentDateTime() + "')");
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
        MysqlShopPipeline pi = new MysqlShopPipeline();
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
