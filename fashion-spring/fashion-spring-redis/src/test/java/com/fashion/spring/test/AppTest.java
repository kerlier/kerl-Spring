package com.fashion.spring.test;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/26 9:34
 */
public class AppTest {

    public static void main(String[] args) throws Exception {
        try{
            int i = 1/0;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("报错");
        }finally {
            System.out.println("执行finally语句");
        }
    }
}
