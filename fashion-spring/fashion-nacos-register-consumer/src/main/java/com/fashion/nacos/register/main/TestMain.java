//package com.fashion.nacos.register.main;
//
//import com.esotericsoftware.kryo.Kryo;
//import com.esotericsoftware.kryo.io.Output;
//import com.fashion.nacos.register.api.RUserDTO;
//import com.fashion.nacos.register.api.UserDTO;
//import org.apache.dubbo.common.serialize.kryo.utils.KryoUtils;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//
///**
// * @Author: yangyuguang
// * @Date: 2021/6/10 16:39
// */
//public class TestMain {
//
//    public static void main(String[] args) throws FileNotFoundException {
//        Kryo kryo = KryoUtils.get();
//        kryo.register(UserDTO.class);
//        kryo.register(RUserDTO.class);
//        Output output = new Output(new FileOutputStream("C:\\Users\\kerl\\Desktop\\robot\\tet.file"),10240);
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUsername("test");
//        userDTO.setPassword("test");
//        kryo.writeObject(output, userDTO);
////
////        Input input = new Input(new FileInputStream("C:\\Users\\kerl\\Desktop\\robot\\tet.file"),10240);
////        RUserDTO rUserDTO = kryo.readObject(input, RUserDTO.class);
////        System.out.println(JSONObject.toJSONString(rUserDTO));
//    }
//}
