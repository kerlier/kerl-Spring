package com.fashion.spring.file;

import com.fashion.spring.watcher.FileWatcher;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/1 16:00
 */
public class FileApplication {

    public static void main(String[] args) throws InterruptedException {
        FileWatcher fileWatcher = new FileWatcher("C:\\Users\\kerl\\Desktop\\a.txt");
        Thread.sleep(1000000000000L);
    }
}
