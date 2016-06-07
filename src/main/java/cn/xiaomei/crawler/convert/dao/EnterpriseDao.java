package cn.xiaomei.crawler.convert.dao;

import cn.xiaomei.crawler.Constant;
import cn.xiaomei.crawler.db.MongoConnect;
import com.alibaba.fastjson.JSON;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chunli on 16/4/12.
 */
public class EnterpriseDao {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://xiaomei2015.mysql.rds.aliyuncs.com/tpx520";

    //  Database credentials
    static final String USER = "xiaomei2015";
    static final String PASS = "f0FzzVm521qnxmkVGNscFgR42a5yaplP";

    public static void main(String[] args) {
        EnterpriseDao enterprise = new EnterpriseDao();
        //enterprise.getEnterprises();
        //enterprise.getMongoEnterprices();
        //enterprise.getDpEnterprises();
       // enterprise.getEnterpriseMongo(2, "思",null);
        enterprise.getMongoEnterpricesNum();

    }

    /*
     *剩余的品牌信息和分店数量
     */
    public List getMongoEnterpricesNum(){
        final List<String> listent=new ArrayList<String>();
        MongoConnect mongoConnect=MongoConnect.getInstance;
        MongoCollection<Document> proxy=mongoConnect.getCollect("test","dianping_company_result");
        BasicDBObject getQuery = new BasicDBObject();
        getQuery.put("total", new BasicDBObject("$gt", 2).append("$lt", 6));
        FindIterable<Document> list = proxy.find(getQuery).sort(Sorts.ascending("total"));
        list.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                try {

                   if(!findMysqlEnterprises( document.get("_id").toString().replaceFirst(" ","").replaceAll("%","").replaceAll("'","")))
                   {
                        System.out.println(Integer.valueOf((document.getDouble("total")+"").replaceAll(".0","")) + "," + document.get("_id").toString());
                       listent.add( document.get("_id").toString().replaceAll(",","")+","+Integer.valueOf((document.getDouble("total")+"").replaceAll(".0","")));
                   }

                }catch (Exception e){
                }
            }
        });
        mongoConnect.close();
        try {
            FileUtils.writeLines(new File("/Users/chunli/Download/enterprise2.csv"),listent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean findMysqlEnterprises(String s) {
        Connection conn = null;
        Statement stmt = null;
        boolean isexists=false;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "select * from x520_xiaomei_brand_main where title like '%"+s+"%'";
            ResultSet rs = stmt.executeQuery(sql);
            List<String> tels=new ArrayList<String>();
            isexists=rs.next();

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return isexists;
    }


    public List getMongoEnterprices(){

        MongoConnect mongoConnect=MongoConnect.getInstance;
        MongoCollection<Document> proxy=mongoConnect.getCollect("test","dianping_company");
        FindIterable<Document> list=proxy.find();
        list.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                try {
                    //System.out.println(document);
                    System.out.println(document.getString("name") + ":" + document.get("id").toString());
                    float comment = document.get("comment") == null && StringUtils.isBlank(document.getString("comment")) ? 0 : Float.parseFloat(document.getString("comment"));
                    float environment = document.get("environment") == null && StringUtils.isBlank(document.getString("environment")) ? 0 : Float.parseFloat(document.getString("environment").replaceAll("服务：", ""));
                    float service = document.get("service") == null && StringUtils.isBlank(document.getString("service")) ? 0 : Float.parseFloat(document.getString("service"));
                    float taste = document.get("taste") == null && StringUtils.isBlank(document.getString("taste")) ? 0 : Float.parseFloat(document.getString("taste"));
                    float avg_pay = 0;
                    if (document.get("percapita") != null && document.getString("percapita").indexOf("-") == -1) {
                        System.out.println(document.get("percapita").toString());
                        System.out.println(document.getString("percapita").indexOf("-") + "");
                        avg_pay = document.get("percapita") == null ? 0 : Float.parseFloat(document.getString("percapita").replaceAll("￥", "").replaceAll("-", ""));
                    } else {
                        avg_pay = document.get("priceText") == null ? 0 : Float.parseFloat(document.getString("priceText").replaceAll("￥", "").replaceAll("/人", ""));
                    }
                    updateEnterprises(Integer.valueOf(document.get("id").toString()), comment, environment, service, taste, avg_pay );
                }catch (Exception e){

                }

                //,float environment,float service,float taste,float avg_pay

            }
        });
        mongoConnect.close();
        return null;
    }
    public void updateEnterprises(Integer id,float comment,float environment,float service,float taste,float avg_pay ) {
        // JDBC driver name and database URL
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
               sql = "select * from x520_xiaomei_company_merchant where id_dp="+id;
            ResultSet rs = stmt.executeQuery(sql);
            List<String> tels=new ArrayList<String>();
            //STEP 5: Extract data from result set
            if(rs.next())
            {
                String updatesql="update x520_xiaomei_company_merchant set comment="+comment+",environment="+environment+",service="+service+",taste="+taste+",avg_pay="+avg_pay+" where id_dp="+id;
                System.out.println(updatesql);
                stmt.executeUpdate(updatesql);
                //"update x520_xiaomei_compa
            }
            /*while (rs.next()) {
                String full_name = rs.getString("full_name");
               //ny_merchant set comment="+comment+",environment="+environment+",service="+service+",taste="+taste+",avg_pay"+avg_pay+" where id_dp="+id);
                //Retrieve by column name

                System.out.println(full_name);

            }*/
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    public void getDpEnterprises() {
        // JDBC driver name and database URL
        Connection conn = null;
        Statement stmt = null;
        List<String> list=new ArrayList<String>();
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            ResultSet rs =null;
           List<String> ents=FileUtils.readLines(new File("/Users/chunli/Downloads/dianping.csv"));
            for (String ent : ents) {
                sql = "select * from x520_xiaomei_brand_main where title like '"+ent.split(",")[0].replace("'","")+"' or description like '"+ ent.split(",")[0].replace("'","")+"'";
                System.out.println(sql);
                rs = stmt.executeQuery(sql);
                List<String> tels = new ArrayList<String>();
                //STEP 5: Extract data from result set
                if (!rs.next()) {
                    list.add(ent);
                    //"update x520_xiaomei_compa
                }
            }
            /*while (rs.next()) {
                String full_name = rs.getString("full_name");
               //ny_merchant set comment="+comment+",environment="+environment+",service="+service+",taste="+taste+",avg_pay"+avg_pay+" where id_dp="+id);
                //Retrieve by column name

                System.out.println(full_name);

            }*/
            rs.close();
            stmt.close();
            conn.close();

            FileUtils.writeLines(new File("/Users/chunli/Download/dianping1.csv"),list);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }
    public void getEnterprises() {
        // JDBC driver name and database URL
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "select a.company_id,a.contact_id,a.mobile deftelephone,b.type,b.channel,b.contact_type,b.title position,b.telephone,b.telephone1,b.telephone2," +
                    " a.company_id,a.title,a.full_name,a.address" +
                    " from   (select * from x520_xiaomei_company_main where user_name not like '88%' and ( city_id='10000' or city like '%北京市%' ) and length(trim(title))>2  ) a left join (select * from x520_xiaomei_contact where type='company' and contact_type='未沟通' ) b on (a.contact_id=b.contact_id)";
            ResultSet rs = stmt.executeQuery(sql);
            List<String> tels=new ArrayList<String>();
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int merchant_id = rs.getInt("company_id");
                int contact_id = rs.getInt("contact_id");
                String deftelephone = rs.getString("deftelephone");
                String type = rs.getString("type");
                String channel = rs.getString("channel");
                String contact_type = rs.getString("contact_type");
                String position = rs.getString("position");
                String telephone = rs.getString("telephone");
                String telephone1 = rs.getString("telephone1");
                String telephone2 = rs.getString("telephone2");
                String company_id = rs.getString("company_id");
                String title = rs.getString("title");
                String full_name = rs.getString("full_name");
                String address = rs.getString("address");
                tels.add(telephone1);
                tels.add(telephone2);
                tels.add(telephone);
                tels.add(deftelephone);

                this.getEnterpriseMongo(merchant_id,title, tels);

            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void getEnterpriseMongo(int id,String name,List<String> tel) {
        MongoCredential credential = MongoCredential.createCredential(Constant.MongoUserName, Constant.MongoAdminDatabase, Constant.MongoPW.toCharArray());
        ServerAddress serverAddress = new ServerAddress(Constant.MongoHosts, Constant.MongoPort);
        MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
        MongoCollection<Document> dbc = null;
        dbc = mongoClient.getDatabase(Constant.MongoTestDatabase).getCollection("fiveeight_ent_new");
        /*BasicDBObject regexQuery = new BasicDBObject();
        regexQuery.put("address",
                new BasicDBObject("$regex", "TestEmployee_[3]")
                        .append("$options", "i"));*/
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        BasicDBObject address = new BasicDBObject();
        address.put("address",
                new BasicDBObject("$regex", name)
                        .append("$options", "i"));
        obj.add(address);
        BasicDBObject namedb = new BasicDBObject();
        namedb.put("name",
                new BasicDBObject("$regex", name)
                        .append("$options", "i"));
        obj.add(namedb);
        BasicDBObject compIntro = new BasicDBObject();
        compIntro.put("compIntro",
                new BasicDBObject("$regex", name)
                        .append("$options", "i"));
        obj.add(compIntro);
        /*obj.add(new BasicDBObject().put("address", new BasicDBObject("$regex",name)
                .append("$options", "i")));
        obj.add(new BasicDBObject("name", name).append("$options", "i"));
        obj.add(new BasicDBObject("compIntro", name).append("$options", "i"));*/
        andQuery.put("$or", obj);


        FindIterable<Document> enters = dbc.find(andQuery);
        for (Document enter : enters) {
            //System.out.println(andQuery.toString());

            NewEnterBean d=new NewEnterBean(id+"",enter.getString("id"),name,enter.getString("name"),enter.getString("address"),enter.getString("compIntro"),enter.getString("contacts"),tel);
          //  System.out.println(id+","+name+","+enter.getString("name")+","+tel.toString()+","+enter.getString("contacts")+","+enter.getString("address")+","+enter.getString("compIntro"));
           //812656382214
            System.out.println(id+","+enter.getString("id"));
            if(!(id==10740128 || id==10341912 || id==1)) {
                Document dbObject = Document.parse(JSON.toJSONString(d));
                MongoCollection<Document> dbcmy = mongoClient.getDatabase(Constant.MongoTestDatabase).getCollection("fiveeight_ent_mysql");

                dbcmy.insertOne(dbObject);
            }
            /*for (Map.Entry<String, Object> e : enter.entrySet())
                System.out.println(e.getKey() + "," + e.getValue());*/
        }
        mongoClient.close();

    }

    class NewEnterBean{
        public NewEnterBean(String id,String pid,String name,String name58,String address58,String compIntro58,String tel58,List<String> tel){
            this.id=id;
            this.pid=pid;
            this.name=name;
            this.name58=name58;

            this.address58=address58;
            this.tel=tel;
            this.tel58=tel58;
            this.compIntro58=compIntro58;
        }
        private String id;

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        private String pid;
        private String name;

        private String name58;
        private List<String> tel;
        private String tel58;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            NewEnterBean that = (NewEnterBean) o;

            return new EqualsBuilder()
                    .append(getId(), that.getId())
                    .append(getName(), that.getName())
                    .append(getName58(), that.getName58())
                    .append(getTel58(), that.getTel58())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(getId())
                    .append(getName())
                    .append(getName58())
                    .append(getTel58())
                    .toHashCode();
        }

        public String getId() {
            return id;

        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName58() {
            return name58;
        }

        public void setName58(String name58) {
            this.name58 = name58;
        }

        public List<String> getTel() {
            return tel;
        }

        public void setTel(List<String> tel) {
            this.tel = tel;
        }

        public String getTel58() {
            return tel58;
        }

        public void setTel58(String tel58) {
            this.tel58 = tel58;
        }

        private String address58;
        private String compIntro58;

        public String getCompIntro58() {
            return compIntro58;
        }

        public void setCompIntro58(String compIntro58) {
            this.compIntro58 = compIntro58;
        }

        public String getAddress58() {
            return address58;
        }

        public void setAddress58(String address58) {
            this.address58 = address58;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
