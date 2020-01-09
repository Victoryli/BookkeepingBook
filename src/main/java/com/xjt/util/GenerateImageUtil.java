package com.xjt.util;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author liqing
 * @version 1.0
 * @date 2019-12-28 20:53
 */
@Component
public class GenerateImageUtil {
    private static final int WIDTH = 100;

    private static final int HEIGHT = 40;

    private static final int SIZE = 4;

    private static final int LINES = 100;

    private static final String[] CHARS = {"1","2","3","4","5","6","7","8","9",
            "A","B","C","D","E","F","G","H","I","J","K","L","M","N"};

    private static final int FONT_SIZE = 22;

    private static Random in = new Random();

    private static final Color[] colors = {new Color(0,0,0),
            new Color(240,124,21),new Color(112,112,112),
            new Color(88,88,255),new Color(255,88,88)};

    public static Map<String,Object> GenerateVerificationCode(){
        BufferedImage image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        StringBuffer number = new StringBuffer();
        Graphics g = image.getGraphics();
        g.setColor(new Color(234,234,234));
        // 画出矩形区域
        g.fillRect(0, 0, WIDTH,HEIGHT);
        // 随机画字符
        for(int i = 1 ; i <= SIZE ; i++){
            g.setColor(randomCharColor());
            int j = in.nextInt(CHARS.length);
            g.setFont(new Font("宋体",Font.BOLD+Font.ITALIC,FONT_SIZE));
            g.drawString(CHARS[j], (i-1)*WIDTH/SIZE, HEIGHT/2+6);
            number.append(CHARS[j]);
        }
        // 随机画干扰点
        for(int i = 1 ; i <= LINES ; i++){
            int x = in.nextInt(WIDTH) ;
            int y = in.nextInt(HEIGHT) ;
            g.setColor(randomColor());
            g.setFont(new Font("Arial", Font.ITALIC,1));
            g.drawLine(x , y , x , y);
        }
        for (int i = 0; i < 40; i++) {
            int x = in.nextInt(WIDTH);
            int y = in.nextInt(HEIGHT);
            int xl = in.nextInt(12);
            int yl = in.nextInt(12);
            g.setColor(randomColor());
            g.drawLine(x, y, x + xl, y + yl);
        }

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code", number.toString()) ;
        map.put("image",image) ;
        return map;
    }
    /**
     * 获取随机颜色的方法；
     * @return
     */
    private static  Color randomColor(){
        return new Color(in.nextInt(256),in.nextInt(256),in.nextInt(256));
    }

    private static Color randomCharColor(){
        return colors[in.nextInt(colors.length)] ;
    }

}
