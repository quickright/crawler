package cn.xiaomei.crawler.db.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * Created by chunli on 16/3/22.
 */
public class FiveEightPersion {

    //姓名
    private String name;
    //主键
    private String _id;
    //是否性别
    private String sex;
    //年龄
    private String age;
    //求职意向_标题
    private String title;
    //学校
    private String basic_college;
    //居住地

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String basic_live;
    //工作经验
    private String basic_jingyan;
    //户籍
    private String basic_huji;
    //求职意向
    private String job_goal;
    //意向地点
    private String job_local;
    //期望薪资
    private String job_salary;
    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    //城市
    private String city;
    //简历
    private String intrCon;

    private List<String> tags;
    private List<FiveEightResume> resumes;
    public String getExperience() {
        return experience;
    }

    public List<FiveEightResume> getResumes() {
        return resumes;
    }

    public void setResumes(List<FiveEightResume> resumes) {
        this.resumes = resumes;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    private String experience;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        FiveEightPersion that = (FiveEightPersion) o;

        return new EqualsBuilder()
                .append(getName(), that.getName())
                .append(get_id(), that.get_id())
                .isEquals();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getName())
                .append(get_id())
                .toHashCode();
    }

    //站点
    private String siteurl;
    //发布日期
    private String pubdate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }



    public String getBasic_college() {
        return basic_college;
    }

    public void setBasic_college(String basic_college) {
        this.basic_college = basic_college;
    }

    public String getBasic_live() {
        return basic_live;
    }

    public void setBasic_live(String basic_live) {
        this.basic_live = basic_live;
    }

    public String getBasic_jingyan() {
        return basic_jingyan;
    }

    public void setBasic_jingyan(String basic_jingyan) {
        this.basic_jingyan = basic_jingyan;
    }

    public String getBasic_huji() {
        return basic_huji;
    }

    public void setBasic_huji(String basic_huji) {
        this.basic_huji = basic_huji;
    }

    public String getJob_goal() {
        return job_goal;
    }

    public void setJob_goal(String job_goal) {
        this.job_goal = job_goal;
    }

    public String getJob_local() {
        return job_local;
    }

    public void setJob_local(String job_local) {
        this.job_local = job_local;
    }

    public String getJob_salary() {
        return job_salary;
    }

    public void setJob_salary(String job_salary) {
        this.job_salary = job_salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIntrCon() {
        return intrCon;
    }

    public void setIntrCon(String intrCon) {
        this.intrCon = intrCon;
    }

    public String getSiteurl() {
        return siteurl;
    }

    public void setSiteurl(String siteurl) {
        this.siteurl = siteurl;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }
}
