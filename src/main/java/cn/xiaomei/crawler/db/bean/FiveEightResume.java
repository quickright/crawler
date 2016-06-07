package cn.xiaomei.crawler.db.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by chunli on 16/3/22.
 */
public class FiveEightResume {

    //单位
    private String entri;
    //工作时间
    private String experDate;
    //时长
    private String experTime;
    //薪资

    public String getEntri() {
        return entri;
    }

    public void setEntri(String entri) {
        this.entri = entri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        FiveEightResume that = (FiveEightResume) o;

        return new EqualsBuilder()
                .append(getEntri(), that.getEntri())
                .append(getJobgoal(), that.getJobgoal())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getEntri())
                .append(getJobgoal())
                .toHashCode();
    }

    public String getExperDate() {
        return experDate;
    }

    public void setExperDate(String experDate) {
        this.experDate = experDate;
    }

    public String getExperTime() {
        return experTime;
    }

    public void setExperTime(String experTime) {
        this.experTime = experTime;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getJobgoal() {
        return jobgoal;
    }

    public void setJobgoal(String jobgoal) {
        this.jobgoal = jobgoal;
    }

    public String getJobdesc() {
        return jobdesc;
    }

    public void setJobdesc(String jobdesc) {
        this.jobdesc = jobdesc;
    }

    private String salary;
    //职位
    private String jobgoal;
    //工作描述
    private String jobdesc;


}
