package com.basic.spring.serialize.main;

import com.basic.spring.serialize.pojo.UserDto;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

//反序列化
public class DSerializableMain {
    public static void main(String[] args) {
        //从文件中读取字节数组，并转换成对象
        jdkDSerial(UserDto.class);
    }

    public static <T> void jdkDSerial(Class<T> t){
        try(FileInputStream inputStream = new FileInputStream("./userDto.ser");){
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            T t1 = (T) objectInputStream.readObject();
            System.out.println(t1.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
