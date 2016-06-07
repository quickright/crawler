package cn.xiaomei.crawler.utils;

import cn.xiaomei.crawler.Constant;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.util.List;

/**
 * Created by chunli on 16/3/8.
 */
public class ExecuteShellComand {

    public static void main(String[] args) {

        ExecuteShellComand obj = new ExecuteShellComand();

        String jobname="pic_job_";
        String file="/tmp/file.txt";
        String ak="_6oZxqstye3bijUyZTHg3dXXMmkzW0nyE6K37_Jo";
        String sk="xU38t53v3J7IUlFDJZ95rI08BTbqob8tRNpXwo83";
        String command="qfetch -ak='"+ak+"' -sk='"+sk+"' -bucket='xiaomei' -file='"+file+"' -worker=3 -job='"+jobname+"'";

        String output = obj.executeCommand(command);

        System.out.println(output);

    }
    /*
       * 执行qfetch
       * basedir 执行命令的路径
       * jobname 任务名称
       *
       */
    public static String executeQfetch(String basedir,File file,String jobname)
    {
        ExecuteShellComand obj = new ExecuteShellComand();
        //String command="/usr/bin/qfetch -ak='"+ak+"' -sk='"+sk+"' -bucket='xiaomei' -file="+file.getPath()+" -worker=3 -job='"+jobname+"'";
        String command="/usr/bin/qfetch -ak="+ Constant.QAk+" -sk="+Constant.QSk+" -bucket=xiaomei -file="+file.getPath()+" -worker=40 -job="+jobname;
        System.out.println(command);
        String output = obj.executeCommand(command);
        System.out.println(output);
        return output;
    }

    /*
    * 执行leveldb
    * basedir 执行命令的路径
    * jobname 任务名称
    *
    */
    public static int executeLeveldb(String basedir,String jobname)
    {
        ExecuteShellComand obj = new ExecuteShellComand();
        //String command="/usr/bin/qfetch -ak='"+ak+"' -sk='"+sk+"' -bucket='xiaomei' -file="+file.getPath()+" -worker=3 -job='"+jobname+"'";
        //String command="/usr/bin/leveldb  -export=."+jobname+".job >> ";
        System.out.println("/usr/bin/leveldb"+"-export"+"."+jobname+".job");
        String job="."+jobname+".job";
        ProcessBuilder builder = new ProcessBuilder("/usr/bin/leveldb","-export",job);
        File file = new File(basedir+jobname+".txt");
        System.out.println(file.getAbsolutePath());

        builder.redirectOutput(file);
        int count=0;
        try {
            Process p = builder.start();
            List<String> line=FileUtils.readLines(file);
            for(String l:line)
            {
                System.out.println("--------"+l);
            }
            count=line.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        System.out.println(command);
        String output = obj.executeCommand(command);
        BreakIterator breakIterator=BreakIterator.getLineInstance();
        breakIterator.setText(output);
        System.out.println(breakIterator.last());
        System.out.println(output);*/
        return count;
    }
    private  String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }
}
