package com.fashion.spring;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class BaseTest  {

    @Test
    public void testImg() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border","no");
        properties.setProperty("kaptcha.border.color","105,179,90,0");
        properties.setProperty("kaptcha.image.width","200");
        properties.setProperty("kaptcha.image.height","100");
        properties.setProperty("kaptcha.textproducer.char.length","200");
        properties.setProperty("kaptcha.session.key","code");
        properties.put("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");
        properties.setProperty("kaptcha.textproducer.font.names","宋体,楷体,微软雅黑");
        properties.setProperty("kaptcha.textproducer.font.color",getColor());
        Random random = new Random();
        properties.setProperty("kaptcha.textproducer.font.size",String.valueOf(100 - random.nextInt(10)));
        DefaultKaptcha producer = new DefaultKaptcha();
        producer.setConfig(new Config(properties));
        //生成文字验证码
//        String text = producer.createText();
//        System.out.println(text);
        //生成图片验证码
        BufferedImage image = producer.createImage("1239");
        //输出，可以是输出本地，也可以输出网络response.getOutputStream();
        ImageIO.write(image, "jpg", new File("C:\\Users\\kerl\\Desktop\\图片\\a\\code2222.jpg"));
    }




    public static String getColor(){
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return r + "," + g + "," + b;
    }
}
