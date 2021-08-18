package com.fashion.spring.controller;


import com.fashion.spring.ServiceException;
import com.fashion.spring.pojo.ApplicationRequest;
import com.fashion.spring.service.PropertyService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/info")
public class ReadController {

    @Resource
    private PropertyService propertyService;

    private LoadingCache<Long, String> build;

    @PostConstruct
    public void init(){
        build = CacheBuilder.newBuilder()
                .expireAfterWrite(5L, TimeUnit.SECONDS)
                .maximumSize(10000)
                .build(new CacheLoader<Long, String>() {
                    @Override
                    public String load(Long uid) throws Exception {
                        System.out.println("缓存值" + uid);
                        if(Objects.equals(uid,2L)){
                            System.out.println("报错");
                            throw new ServiceException("缓存出错");
                        }
                        return "success";
                    }
                });
    }

    @GetMapping("/getApplicationName")
    public String getApplicationName(@RequestBody ApplicationRequest applicationRequest){
        return applicationRequest.getName();
    }

    @RequestMapping("/cache")
    public String getFromCache(@RequestParam Long uid){
        return build.getUnchecked(uid);
    }

    @GetMapping("/copy")
    public String copy() throws IOException {
        String source = "C:\\software\\nacos-server-2.0.1.zip";
        String target = "C:\\software\\nacos-server-2.0.4.zip";
        copy(source,target);
        return "success";
    }
    @GetMapping("/copy1")
    public String copy1() throws IOException {
        String source = "C:\\software\\nacos-server-2.0.1.zip";
        String target = "C:\\software\\nacos-server-2.0.4.zip";
        copyWithBuffer(source,target);
        return "success";
    }

    public static void copy(String source,String target) throws IOException {
        Path path = Paths.get(source);
        Path path1 = Paths.get(target);

        InputStream inputStream = Files.newInputStream(path);
        OutputStream outputStream = Files.newOutputStream(path1);

        byte[] bytes = new byte[1024];

        int len = 0;
        while((len=inputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,len);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public static void copyWithBuffer(String source,String target) throws IOException {
        Path path = Paths.get(source);
        Path path1 = Paths.get(target);

        if(!Files.exists(path1)){
            Files.createFile(path1);
        }
        FileChannel open = FileChannel.open(path, StandardOpenOption.READ);
        FileChannel open1 = FileChannel.open(path1, StandardOpenOption.WRITE,StandardOpenOption.READ);
        long size = open.size();
        MappedByteBuffer map = open.map(FileChannel.MapMode.READ_ONLY, 0, size);
        MappedByteBuffer map1 = open1.map(FileChannel.MapMode.READ_WRITE, 0, size);
        System.out.println("执行到这里" + size);
        for (int i = 0; i < size; i++) {
            byte b = map.get(i);
            map1.put(i,b);
        }

        System.out.println("执行完了");
    }
}
