package cn.xiaomei.crawler.db.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * Created by chunli on 16/4/7.
 */
public class CityUrl {
    private String citycn;
    private String cityen;
    private String url;
    private boolean isused;


    public boolean getIsused() {
        return isused;
    }

    public void setIsused(boolean isused) {
        this.isused = isused;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CityUrl cityUrl = (CityUrl) o;

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

    public CityUrl(String url,String citycn,String cityen,boolean isused)
    {
        this.url=url;
        this.citycn=citycn;
        this.cityen=cityen;
        this.isused=isused;
    }

    private String city_id;

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCitycn() {
        return citycn;
    }

    public void setCitycn(String citycn) {
        this.citycn = citycn;
    }

    public String getCityen() {
        return cityen;
    }

    public void setCityen(String cityen) {
        this.cityen = cityen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
