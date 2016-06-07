package cn.xiaomei.crawler.db.bean;

import java.util.List;

/**
 * Created by chunli on 16/4/5.
 */
public class PageProgress {
    private String pageurl;
    private String flag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String location;
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPageurl() {
        return pageurl;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<String> getDetail() {
        return detail;
    }

    public void setDetail(List<String> detail) {
        this.detail = detail;
    }

    public void setPageurl(String pageurl) {
        this.pageurl = pageurl;
    }

    private int num;
    private String addDate;

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    private List<String> detail;

}
