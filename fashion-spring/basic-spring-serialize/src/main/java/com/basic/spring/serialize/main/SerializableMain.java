package com.basic.spring.serialize.main;

import com.basic.spring.serialize.pojo.PersonProto;
import com.basic.spring.serialize.pojo.UserDto;
import com.basic.spring.serialize.util.SerialUtil;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * 可以将输入流输入到另外一个输出流中
 */
public class SerializableMain {
    public static void main(String[] args) {
        //将对象写入到文件中
        UserDto userDto = new UserDto();
        userDto.setUsername("yangyuguang");
//        jdkSerial(userDto);

        byte[] jdkBytes = SerialUtil.jdkSerialToByteArray(userDto);

        byte[] hessianBytes = SerialUtil.hessianSerialToByteArray(userDto);

        byte[] hessian2Bytes = SerialUtil.hessian2SerialToByteArray(userDto);

        byte[] kryoBytes = SerialUtil.kryoSerial(userDto);

        System.out.println("jdk byte array length :" + jdkBytes.length);
        System.out.println("hessian byte array length :" + hessianBytes.length);
        System.out.println("hessian2 byte array length :" + hessian2Bytes.length);
        System.out.println("kryo byte array length :" + kryoBytes.length);


        //output: jdk 106; hessian 69; hessian2: 65, kryo: 13
        //经过对比，kryo > hessian2 > hessian > jdk
        //kryo的效率大大超过了hessian以及jdk


        UserDto  userDto1 = SerialUtil.hessianDSerial(hessianBytes);

        System.out.println(userDto1.getUsername());

        UserDto userDto2 = SerialUtil.kryoDSerial(kryoBytes, UserDto.class);
        System.out.println(userDto2.getUsername());

    }


    public static <T> void jdkSerial(T t) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("./userDto.ser");) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(t);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("userDto is saved to ./useDto.ser ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
