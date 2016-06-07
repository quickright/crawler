package cn.xiaomei.crawler.utils;

import cn.xiaomei.crawler.Constant;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by chunli on 16/3/8.
 */
public class User {
    public static String getRandomUid()
    {
        File uidfile=new File(Constant.getCrawlerConfPath()+"uid.txt");
        List<String> uids=null;
        try {
           uids=FileUtils.readLines(uidfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String uid=uids.get(RandomUtils.nextInt(10000));
        System.out.println(uid);
        return uid;
    }

    public static void main(String [] args)
    {
        System.out.println(User.getRandomUid());
        System.out.println(User.getRandomUid());
        System.out.println(User.getRandomUid());
    }
}
