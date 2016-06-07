package cn.xiaomei.crawler.db.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * Created by chunli on 16/4/9.
 */
public class DianpingEnterBean {
    private String id;
    //修改日期
    private String modifyDate;
    //入库日期
    private String addDate;
    //商户名称
    private String name;
    //认证
    private String vshop;
    //评论
    private String comment;
    //人均
    private String percapita;
    //口味
    private String taste;

    //服务
    private String service;
    //环境
    private String environment;
    //地址
    private String street_address;
    //电话
    private String tel;
    //营业时间
    private String businessHours;
    //区域
    private String area;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String city;
    //分类
    private String category;
    //商户标签
    private List<String> tags;
    //图片连接
    private List<String> photourls;
    //大众标签
    private List<String> consumertag;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public DianpingEnterBean(String id, String shopname, String vshop){
        this.id=id;
        this.name=shopname;
        this.vshop=vshop;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String shopname) {
        this.name = shopname;
    }

    public String getVshop() {
        return vshop;
    }

    public void setVshop(String vshop) {
        this.vshop = vshop;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPercapita() {
        return percapita;
    }

    public void setPercapita(String percapita) {
        this.percapita = percapita;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getPhotourls() {
        return photourls;
    }

    public void setPhotourls(List<String> photourls) {
        this.photourls = photourls;
    }

    public List<String> getConsumertag() {
        return consumertag;
    }

    public void setConsumertag(List<String> consumertag) {
        this.consumertag = consumertag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DianpingEnterBean that = (DianpingEnterBean) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getName(), that.getName())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getName())
                .toHashCode();
    }
}
