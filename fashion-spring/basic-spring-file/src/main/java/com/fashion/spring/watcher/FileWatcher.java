package com.fashion.spring.watcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/1 16:00
 */
public class FileWatcher {

    private final ScheduledExecutorService executor;

    private Boolean stopped = false;

    private Long lastModified;

    public FileWatcher(String filePath){
        executor=new ScheduledThreadPoolExecutor(1);
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行1");
                System.out.println("lastModified:" + lastModified);
                synchronized (this){
                    if(stopped){
                        return ;
                    }
                    boolean reload = false;
                    File file = new File(filePath);
                    if(!Objects.equals(lastModified,file.lastModified())){
                        System.out.println("执行2");
                        reload = true;
                        lastModified = file.lastModified();
                    }

                    if(reload){
                        System.out.println("执行3");
                        print(file);
                    }
                }
            }
        },1L,3L, TimeUnit.SECONDS);
    }

    public void print(File file){
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
