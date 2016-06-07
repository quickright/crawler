package cn.xiaomei.crawler.db.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by chunli on 16/3/22.
 */
public class Shop {
    private String shop_id;
    private String price;
    private String comment;
    private String sold;
    private String name;
    private String channel;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private String address;
    private String phone;

    public String getShop_id() {
        return shop_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Shop shopBean = (Shop) o;

        return new EqualsBuilder()
                .append(getShop_id(), shopBean.getShop_id())
                .append(name, shopBean.name)
                .append(longitude, shopBean.longitude)
                .append(latitude, shopBean.latitude)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getShop_id())
                .append(name)
                .append(longitude)
                .append(latitude)
                .toHashCode();
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    private String longitude;
    private String latitude;
    private String city_id;
    private String district_id;
    private String link;

       /*   System.out.println(jr.xpath("//a/@href").get());
              System.out.println(jr.xpath("//a/@data-topten").get().substring(2));
              System.out.println(jr.xpath("//span[@class=\"price\"]/text()").get());
              System.out.println(jr.xpath("//span[@class=\"comment\"]/text()").get().substring(0, 3));
              System.out.println(jr.xpath("//span[@class=\"sold\"]/text()").get().substring(2));*/
    //page.addTargetRequest(jr.xpath("//a/@href").get());
          /*    System.out.println(jr.xpath("//a/@href").get());
              String url=DETAIL_LIST+jr.xpath("//a/@data-topten").get().substring(2);
              System.out.println(url);

              String json=HttpClientUtils.response(url);
              System.out.println(url);
              JSONArray jarr=JsonUtils.getPathArray(json,"shop");
              for (int i = 0; i < jarr.length(); i++) {
                  System.out.println(jarr.getJSONObject(i).getString("merchant_id"));
                  System.out.println(jarr.getJSONObject(i).getString("name"));
                  System.out.println(jarr.getJSONObject(i).getString("address"));
                  System.out.println(jarr.getJSONObject(i).getString("phone"));
                  System.out.println(jarr.getJSONObject(i).get("baidu_longitude"));
                  System.out.println(jarr.getJSONObject(i).get("baidu_latitude"));
                  System.out.println(jarr.getJSONObject(i).getString("city_id"));
                  System.out.println(jarr.getJSONObject(i).getString("district_id"));
                  System.out.println(jarr.getJSONObject(i).getString("link"));
              }*/
}
