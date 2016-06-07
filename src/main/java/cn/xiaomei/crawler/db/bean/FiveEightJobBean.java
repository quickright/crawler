package cn.xiaomei.crawler.db.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * Created by chunli on 16/3/22.
 */
public class FiveEightJobBean {

    //企业id
    private String pid;
    //主键
    private String id;
    //是否认证
    private boolean isyz;

    public String getSiteurl() {
        return siteurl;
    }

    public void setSiteurl(String siteurl) {
        this.siteurl = siteurl;
    }

    //站点
    private String siteurl;
    //private List<String> mobile;
    //职位名称
    private String title;
    //薪资
    private String pay;
    //薪资单位
    private String payyy;
    //发布日期
    private String pubdate;
    //职位
    private String position;
    //要求
    private String req;
    //地点
    private String location;

    private String longitude;
    private String latitude;
    //福利
    private List<String> fulivalues;
    //联系人
    private String contact;
    //电话
    private String phone;
    //职位描述
    private String position_dis;



    public boolean isyz() {
        return isyz;
    }

    public void setIsyz(boolean isyz) {
        this.isyz = isyz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        FiveEightJobBean that = (FiveEightJobBean) o;

        return new EqualsBuilder()
                .append(pid, that.pid)
                .append(id, that.id)
                .append(title, that.title)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(pid)
                .append(id)
                .append(title)
                .toHashCode();
    }

    //


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getPayyy() {
        return payyy;
    }

    public void setPayyy(String payyy) {
        this.payyy = payyy;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public List<String> getFulivalues() {
        return fulivalues;
    }

    public void setFulivalues(List<String> fulivalues) {
        this.fulivalues = fulivalues;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition_dis() {
        return position_dis;
    }

    public void setPosition_dis(String position_dis) {
        this.position_dis = position_dis;
    }




}
