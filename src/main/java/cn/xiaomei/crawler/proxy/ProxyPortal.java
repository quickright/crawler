package cn.xiaomei.crawler.proxy;

import java.io.IOException;

/**
 * Created by chunli on 16/4/20.
 */
public class ProxyPortal {
    public  static void  main(String [] args) throws IOException {
        Peuland peuland=new Peuland();
        for(int i=10;i>0;i--) {
            peuland.saveProxy(i);
        }
    }
}
