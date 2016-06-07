package cn.xiaomei.crawler.db.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by chunli on 16/4/7.
 */
public class FECityUrl extends CityUrl {
    //http://bj.58.com/qzmeirongjianshen/
    public static String http="http://";
    //美容美发保健健身
    private static String qzmeirongjianshen =  ".58.com/qzmeirongjianshen/";//&locatecityid=7&cityid=7&callback=jsonp1&_=";
    //餐饮酒店旅游
    private static String qzzplvyoujiudian =  ".58.com/qzzplvyoujiudian/";
    //收银员
    private static String qzshouying =  ".58.com/qzshouying/";
    //店员\营业员
    private static String qzyingyeyuan =".58.com/qzyingyeyuan";

    public  String getQzyingyeyuan() {
        return http+getCityen()+qzyingyeyuan;
    }

    public  String getQzmeirongjianshen() {
        return http+getCityen()+qzmeirongjianshen;
    }

    public  String getQzzplvyoujiudian() {
        return http+getCityen()+qzzplvyoujiudian;
    }

    public  String getQzshouying() {
        return http+getCityen()+qzshouying;
    }


    public FECityUrl(String cityid, String citycn, String cityen, boolean isused) {
        super("", citycn, cityen, isused);
        setCity_id(cityid);
    }

    public FECityUrl(String cityid,  String citycn, String cityen) {
        super("", citycn, cityen, true);
        setCity_id(cityid);
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        FECityUrl cityUrl = (FECityUrl) o;

        return new EqualsBuilder()
                .append(getCitycn(), cityUrl.getCitycn())
                .append(getCityen(), cityUrl.getCityen())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getCitycn())
                .append(getCityen())
                .toHashCode();
    }

}
