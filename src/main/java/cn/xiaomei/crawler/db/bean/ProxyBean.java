package cn.xiaomei.crawler.db.bean;

import java.util.Random;

/**
 * Created by chunli on 16/4/14.
 */
public class ProxyBean {
    private String domain;
    private String ip;
    private String port;
    private Boolean isabled;
    private String addtime;
    private String modifytime;
    private String  random;
    private int times=1;

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public static void main(String [] args){
        String random=Math.round(Math.random()*1000)+"";
        System.out.println(random);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProxyBean proxyBean = (ProxyBean) o;

        if (domain != null ? !domain.equals(proxyBean.domain) : proxyBean.domain != null) return false;
        if (ip != null ? !ip.equals(proxyBean.ip) : proxyBean.ip != null) return false;
        return port != null ? port.equals(proxyBean.port) : proxyBean.port == null;

    }

    public static String Random(){
       return  Math.round(Math.random()*10000)+"";
    }
    public String getRandom() {
        random=Random();
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    @Override
    public int hashCode() {
        int result = domain != null ? domain.hashCode() : 0;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        return result;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean isabled() {
        return isabled;
    }

    public void setIsabled(boolean isabled) {
        this.isabled = isabled;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }
}
