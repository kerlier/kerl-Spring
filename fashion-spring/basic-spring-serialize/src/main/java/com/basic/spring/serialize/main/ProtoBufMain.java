package com.basic.spring.serialize.main;

import com.basic.spring.serialize.pojo.PersonProto;

import java.io.FileOutputStream;

public class ProtoBufMain {
    public static void main(String[] args) {
        PersonProto.Person.Builder builder = PersonProto.Person.newBuilder();
        builder.setAge(1);
        builder.setName("yangyuguang");
        byte[] bytes = builder.build().toByteArray();

        try{
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/kerl/a.txt");
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("protobuf: " + bytes.length);

        try{
            PersonProto.Person person = PersonProto.Person.parseFrom(bytes);
            System.out.println(person.getAge());
            System.out.println(person.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
