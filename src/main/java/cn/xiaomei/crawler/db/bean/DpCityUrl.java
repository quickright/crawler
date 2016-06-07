package cn.xiaomei.crawler.db.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * Created by chunli on 16/4/7.
 */
public class DpCityUrl extends CityUrl {
    private static String baseUrl = "http://m.api.dianping.com/searchshop.json?range=-1&sortid=0";
    //餐饮45%
    private static String canyinUrl = baseUrl + "&categoryid=10";//&locatecityid=7&cityid=7&callback=jsonp1&_=";
    //娱乐7%
    private static String yuleUrl = baseUrl + "&categoryid=30";
    //丽人5%
    private static String lirenUrl = baseUrl + "&categoryid=50";
    //购物33%
    private static String gouwuUrl = baseUrl + "&categoryid=20";
    //健身2%
    private static String jianshenUrl = baseUrl + "&categoryid=45";

    public DpCityUrl(String cityid,String url, String citycn, String cityen, boolean isused) {
        super(url, citycn, cityen, isused);
        setCity_id(cityid);
    }

    public DpCityUrl(String cityid,String url, String citycn, String cityen) {
        super(url, citycn, cityen, true);
        setCity_id(cityid);
    }

    public DpCityUrl(String url, String citycn, String cityen) {
        super(url, citycn, cityen, true);
    }

    public static String getJiudianUrl() {
        return jiudianUrl;
    }

    public static String getJianshenUrl() {
        return jianshenUrl;
    }

    public static String getGouwuUrl() {
        return gouwuUrl;
    }

    public static String getLirenUrl() {
        return lirenUrl;
    }

    public static String getYuleUrl() {
        return yuleUrl;
    }

    public static String getCanyinUrl() {
        return canyinUrl;
    }


    //酒店8%
    private static String jiudianUrl = "http://m.api.dianping.com/searchshop.json?start=1&range=-1&sortid=0&categoryid=60&locatecityid=7&cityid=7&callback=jsonp1&_=";



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DpCityUrl cityUrl = (DpCityUrl) o;

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
