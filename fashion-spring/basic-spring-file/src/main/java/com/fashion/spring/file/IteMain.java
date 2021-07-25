package com.fashion.spring.file;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/21 15:01
 */
public class IteMain {
    public static void main(String[] args) {

        List<Object> jsonObjects = new ArrayList<>();
        jsonObjects.add(1);
        jsonObjects.add(2);
        jsonObjects.add(3);
        Iterator<Object> iterator = jsonObjects.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();

            iterator.remove();
        }
        System.out.println(jsonObjects.toString());

//        List<Object> objects = jsonObjects.subList(0, 1);
//        System.out.println(objects.toString());
//        List<Object> objects1 = jsonObjects.subList(1, jsonObjects.size());
//        System.out.println(objects1.toString());

//        BigDecimal zero = BigDecimal.ZERO;
//        BigDecimal one = BigDecimal.ONE;
//        System.out.println(one.compareTo(zero) >= 0);

//        List<Object> objects = new ArrayList<>();
//
//        String serviceFeeRate = "0.01";
//        BigDecimal bigDecimal = new BigDecimal(serviceFeeRate);
//        BigDecimal divide = bigDecimal.divide(new BigDecimal("100"));
//        System.out.println(String.valueOf(divide));

//        BigDecimal originServiceFeeRate = new BigDecimal("0.0001");
//        BigDecimal newServiceFeeRate = originServiceFeeRate.multiply(new BigDecimal("100"));
//        System.out.println(newServiceFeeRate);

//        String str = "你好";
//        System.out.println(str.length());

        String file ="test.xls";
        System.out.println(file.substring(file.lastIndexOf("."),file.length()));

        int i = 0;
//        while(jsonObjects.size()>=1){
//            List<Object> objects = jsonObjects.subList(0, 1);
//            System.out.println("第"+i+"次迭代:" + objects.toString());
//            jsonObjects = jsonObjects.subList(1,jsonObjects.size());
//            i++;
//        }
//        StringBuffer stringBuffer = new StringBuffer();
//        for (Object bankCardDTO : jsonObjects) {
//            stringBuffer.append(bankCardDTO.toString());
//            if(i < jsonObjects.size()-1){
//                stringBuffer.append(";");
//            }
//            i++;
//        }
//        System.out.println(stringBuffer);
    }
}
